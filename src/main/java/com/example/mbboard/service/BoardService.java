package com.example.mbboard.service;

import com.example.mbboard.dto.BoardDto;
import com.example.mbboard.dto.Page;
import com.example.mbboard.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService implements IBoardService {

    @Autowired
    private BoardMapper boardMapper;

    // 게시글 목록 전체
    @Override
    public List<BoardDto> getBoardList() {
        return boardMapper.selectBoardList();
    }

    // 게시글 등록
    @Override
    public int addBoard(BoardDto board) {
        return boardMapper.insertBoard(board);
    }

    // 게시글 상세 조회
    @Override
    public BoardDto getBoardOne(int boardNo) {
        return boardMapper.selectBoardOne(boardNo);
    }

    // 게시글 수정
    @Override
    public int modifyBoard(BoardDto board) {
        return boardMapper.updateBoard(board);
    }

    // 게시글 삭제
    @Override
    public int removeBoard(int boardNo) {
        return boardMapper.deleteBoard(boardNo);
    }

    // 기존 방식 (Map 기반)
    @Override
    public List<BoardDto> getBoardListByPage(int currentPage, int rowPerPage) {
        int beginRow = (currentPage - 1) * rowPerPage;
        Map<String, Object> map = new HashMap<>();
        map.put("beginRow", beginRow);
        map.put("rowPerPage", rowPerPage);
        return boardMapper.selectBoardListByPage(map);
    }

    @Override
    public int getBoardCount() {
        return boardMapper.countBoard();
    }

    // 검색 포함한 방식 (Page 기반)
    @Override
    public List<BoardDto> getBoardListByPage(Page page) {
        return boardMapper.selectBoardListByPage(page);
    }

    @Override
    public int getBoardCount(Page page) {
        return boardMapper.countBoard(page);
    }
}
