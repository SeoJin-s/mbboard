package com.example.mbboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mbboard.dto.ConnectCount;
import com.example.mbboard.dto.Member;
import com.example.mbboard.service.ILoginService;
import com.example.mbboard.service.IRootService;
import com.example.mbboard.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

    private final LoginService loginService_1;
	@Autowired ILoginService loginService;
	@Autowired IRootService rootService;

    LoginController(LoginService loginService_1) {
        this.loginService_1 = loginService_1;
    }

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(HttpSession session, Member paramMember, HttpServletResponse response) {
	    Member loginMember = loginService.login(paramMember);
	    
	    if (loginMember != null) {
	        log.info(paramMember.toString());
	        session.setAttribute("loginMember", loginMember); // 세션 등록

	        // 💾 쿠키 저장 (아이디 저장 체크된 경우)
	        if (paramMember.getSaveIdCk() != null) {
	            Cookie c = new Cookie("saveId", paramMember.getMemberId());
	            c.setMaxAge(60 * 60 * 24); // 1일 유효
	            c.setPath("/"); // 모든 경로에서 사용 가능
	            response.addCookie(c); // ✅ 세미콜론 추가됨!
	        } else {
	            // 체크 안 되어 있으면 쿠키 삭제
	            Cookie c = new Cookie("saveId", "");
	            c.setMaxAge(0);
	            c.setPath("/");
	            response.addCookie(c);
	        }

	        // 📊 접속 카운트 처리
	        ConnectCount cc = new ConnectCount();
	        cc.setMemberRole(loginMember.getMemberRole());
	        if (rootService.getConnectCountByKey(cc) == null) {
	            rootService.addConnectCount(cc);
	        } else {
	            rootService.modifyConnectCount(cc);
	        }

	        return "redirect:/member/info";
	    }

	    // ❌ 로그인 실패
	    return "redirect:/login?error=true";
	}
	
	@GetMapping("/joinMember")
	public String joinMember() {
		return "joinMember";
	}
	
	@PostMapping("/joinMember")
	public String joinMember(HttpSession session, Member member) {
	    loginService.insert(member);

	    // 회원가입 후 바로 로그인 처리
	    Member loginMember = loginService.login(member);
	    session.setAttribute("loginMember", loginMember);

	    return "redirect:/member/info";
	}
	
	// 로그인 상태에서 요청가능 -> 필터1
	@GetMapping("/member/info")
	public String info() {
		return "/member/info";	
	}
	
	@GetMapping("/member/changePw")
	public String changePw(Model model
			, @RequestParam String memberId) {
		Member member = loginService.selectMemberOne(memberId);
		model.addAttribute("member", member);
		return "/member/changePw";
	}
	
	@PostMapping("/member/changePw")
	public String changePw(Member member) {
		loginService.updateMember(member);
		return "redirect:/logout";
	}
	
	// 로그인 상태이고 role이 'ADMIN' 요청가능 -> 필터2
	@GetMapping("/admin/adminHome")
	public String adminHome(Model model) {
		List<Member> memberList = loginService.selectMember();
		model.addAttribute("memberList", memberList);
		return "/admin/adminHome";
	}
	
	@GetMapping("/admin/changeRole")
	public String changeRole(Model model
			, @RequestParam String memberId) {
		Member member = loginService.selectMemberOne(memberId);
		if (member.getMemberRole().equals("MEMBER")) {
			member.setMemberRole("ADMIN");
		} else {
			member.setMemberRole("MEMBER");
		}
		
		loginService.updateMember(member);
		
		List<Member> memberList = loginService.selectMember();
		model.addAttribute("memberList", memberList);
		return "/admin/adminHome";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	@GetMapping("/findMemberPw")
	public String findMemberPw() {
		log.info("findMemberPw...");
		return "findMemberPw";
		
	}
	@PostMapping("/findMemberPw")
	public String findMemberPw(Member member) {
		// 비밀번호 변경
		loginService.changeMemberPwByAdmin(member);
		// 메일로 전송
		// 비밀번호 변경페이지로 리다이덱트
		return "rechangeMemberPw";
		
	}
	
	@PostMapping("/rechangeMemberPw")
	public String rechangeMemberPw(
	        @RequestParam("memberId") String memberId,
	        @RequestParam("memberPw") String oldPw,
	        @RequestParam("newMemberPw") String newPw
	) {
	    int row = loginService.updateNewPwAfterMail(memberId, oldPw, newPw);

	    if (row == 1) {
	        return "redirect:/login"; // 변경 성공 시 로그인 페이지로
	    } else {
	        return "rechangeMemberPw"; // 변경 실패 시 다시 비번 변경 폼으로
	    }
	}
	
}