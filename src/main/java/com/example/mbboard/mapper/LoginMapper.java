package com.example.mbboard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.mbboard.dto.Member;

@Mapper
public interface LoginMapper {
	Member login(Member member);
	String selectId(String memberId);
	List<Member> selectMember();
	Member selectMemberOne(String memberId);
	int insert(Member member);
	int updateMember(Member member);

	int updateMemberPwByAdmin(Member member);
	int updateMemberPwAfterMail(@Param("memberId") String memberId,
            @Param("memberPw") String newPw,
            @Param("oldPw") String oldPw);
}
