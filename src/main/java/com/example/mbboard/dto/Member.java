package com.example.mbboard.dto;

import lombok.Data;

@Data
public class Member {
	String memberId;
	String memberPw;
	String memberRole;
	private String saveIdCk;
	private String email;
	private String pwcktime;
	
}