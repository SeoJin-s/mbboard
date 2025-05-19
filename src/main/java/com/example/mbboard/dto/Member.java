package com.example.mbboard.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Member {
	String memberId;
	String memberPw;
	String memberRole;
	private String saveIdCk;
	private String email;
	private LocalDateTime pwcktime;
		
    public LocalDateTime getPwcktime() {
        return pwcktime;
    }

    public void setPwcktime(LocalDateTime pwcktime) {
        this.pwcktime = pwcktime;
    }
}