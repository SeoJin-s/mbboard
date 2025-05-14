package com.example.mbboard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.mbboard.dto.BoardDto;
import com.example.mbboard.dto.Page;

@Mapper
public interface BoardMapper {

    // 페이징 조회
	List<BoardDto> selectBoardListByPage(Map<String, Object> map);
	int countBoard();

    // 게시글 전체 조회
    List<BoardDto> selectBoardList();

    // 게시글 등록
    int insertBoard(BoardDto board);

    // 게시글 상세 조회
    BoardDto selectBoardOne(int boardNo);

    // 게시글 수정
    int updateBoard(BoardDto board);

    // 게시글 삭제
    int deleteBoard(int boardNo);
    
    // 페이지 기반 목록
    List<BoardDto> selectBoardListByPage(Page page);
    
    // 페이지 기반 게시글 수 조회
    int countBoard(Page page);
}
