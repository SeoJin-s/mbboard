package com.example.mbboard.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardUser;
	private LocalDateTime updatedate;
	private LocalDateTime createdate;
}