package com.example.mbboard.service;

import java.util.List;

import com.example.mbboard.dto.BoardDto;
import com.example.mbboard.dto.Page;

public interface IBoardService {
	
    // 게시글 목록 조회
    List<BoardDto> getBoardList();

    // 게시글 등록
    int addBoard(BoardDto board);

    // 게시글 상세 조회
    BoardDto getBoardOne(int boardNo);

    // 게시글 수정
    int modifyBoard(BoardDto board);

    // 게시글 삭제
    int removeBoard(int boardNo);
    // 페이징
    List<BoardDto> getBoardListByPage(int currentPage, int rowPerPage);
    int getBoardCount();
    
    // 페이징, 검색
    List<BoardDto> getBoardListByPage(Page page);
    int getBoardCount(Page page);
}
	
