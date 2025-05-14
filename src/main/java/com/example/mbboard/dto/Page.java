package com.example.mbboard.dto;

import lombok.Data;

@Data	// Lombok을 이용해서 get,set,toString,equals, hashCode 자동생성
public class Page {
	private int rowPerPage;	// 게시글수
	private int currentPage; // 현재 페이지
	private int totalCount; // 전체 게시글
	private String searchWord; // 검색
	
	// 현재 페이지 기준으로 시작 행 번호 계산
	public int getBeginRow( ) {
		return (this.currentPage-1)*this.rowPerPage;
		
	}
	// 전체 게시글 수에 따른 마지막 페이지 번호 계산
	public int getLastPage() {
		int lastPage = this.totalCount / this.rowPerPage;
		if(this.totalCount % this.rowPerPage !=0 ) {	// 기본값 1페이지보장
			lastPage += 1;
		}
		return lastPage;
	}
}
