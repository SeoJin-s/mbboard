package com.example.mbboard.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.mbboard.MbboardApplication;
import com.example.mbboard.dto.Member;
import com.example.mbboard.mapper.LoginMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class LoginService implements ILoginService{
	@Autowired LoginMapper loginMapper;
	
	@Autowired JavaMailSender javaMailSender;


	public Member login(Member paramMember) {
		return loginMapper.login(paramMember);
	}
	
	public String selectId(String memberId) {
		return loginMapper.selectId(memberId);
	}
	
	public List<Member> selectMember() {
		return loginMapper.selectMember();
	}
	
	public Member selectMemberOne(String memberId) {
		return loginMapper.selectMemberOne(memberId);
	}

	public int insert(Member paramMember) {
		return loginMapper.insert(paramMember);
	}
	
	public int updateMember(Member paramMember) {
		return loginMapper.updateMember(paramMember);
		
	}

	@Override
	public void changeMemberPwByAdmin(Member member) {
	    // 기존 memberId 기준으로 DB에서 진짜 member 조회
	    Member dbMember = loginMapper.selectMemberOne(member.getMemberId());

	    if (dbMember == null || dbMember.getEmail() == null) {
	        log.warn("이메일 정보 없음 - 비번 초기화 실패");
	        return;
	    }

	    String randomPw = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
	    dbMember.setMemberPw(randomPw);

	    // 🔥 진짜 이메일 기준으로 업데이트 시도
	    int row = loginMapper.updateMemberPwByAdmin(dbMember);
	    log.info("업데이트 결과 row: {}", row);

	    if (row == 1) {
	        log.info("변경된 비밀번호 : " + randomPw);

	        SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setFrom("admin@localhost.com");
	        msg.setTo(dbMember.getEmail());
	        msg.setSubject("변경된 비밀번호 입니다.");
	        msg.setText("변경된 비밀번호는 [" + randomPw + "] 입니다.\n10분안에 로그인하여 수정하셔야합니다.");
	        javaMailSender.send(msg);
	    }
	}

	@Override
	public int updateNewPwAfterMail(String memberId, String oldPw, String newPw) {
	    Member member = loginMapper.selectMemberOne(memberId);

	    log.info("입력된 memberId: {}", memberId);
	    log.info("입력된 임시비번: {}", oldPw);
	    log.info("DB의 현재 비번: {}", member.getMemberPw());

	    if (member == null || member.getMemberPw() == null || !member.getMemberPw().equals(oldPw)) {
	        log.warn("비번 일치 실패 또는 사용자 없음");
	        return 0;
	    }

	    if (member.getPwcktime() == null) {
	        log.warn("pwcktime 없음");
	        return 0;
	    }

	    long minutes = ChronoUnit.MINUTES.between(member.getPwcktime(), LocalDateTime.now());
	    if (minutes > 10) {
	        log.warn("pwcktime 10분 초과");
	        return 0;
	    }

	    member.setMemberPw(newPw);
	    member.setPwcktime(null);

	    log.info("비밀번호 변경 조건 통과! 실제 업데이트 진행");

	    return loginMapper.updateMemberPwAfterMail(member);
	}

}