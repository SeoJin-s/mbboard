package com.example.mbboard.controller;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mbboard.dto.BoardDto;
import com.example.mbboard.dto.Page;
import com.example.mbboard.mapper.BoardMapper;
import com.example.mbboard.service.BoardService;
import com.example.mbboard.service.IBoardService;

@Controller
public class BoardController {

    private final BoardService boardService_1;
	
	@Autowired
	private IBoardService boardService;

    BoardController(BoardService boardService_1) {
        this.boardService_1 = boardService_1;
    }
	
	// 글 등록
	@PostMapping("/addBoard")
	public String addBoard(BoardDto board) {
		System.out.println("addBoard POST 요청 받음!");
		boardService.addBoard(board); 
		return "addBoard";
	}
	
	// 글 작성
	@GetMapping("/boardForm")
	public String boardForm() {
		return "boardForm";
	}
	
	// 페이징 + 목록
	@GetMapping("/boardList")
	public String boardList(Model model,
	                        @RequestParam(defaultValue = "1") int currentPage
	                      , @RequestParam(required = false) String searchWord) {
		
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setRowPerPage(10);
		page.setSearchWord(searchWord);
		
	    int rowPerPage = 10;	// 게시글 수
	    int totalCount = boardService.getBoardCount(page); // 총 게시글
	    int lastPage = (int) Math.ceil((double) totalCount / rowPerPage); // 마지막페이지번호
	    
	    int pageGroup = 10; // 페이징 수
	    int startPage = ((currentPage -1 ) / pageGroup) * pageGroup +1;
	    int endPage = Math.min(startPage + pageGroup -1, lastPage);
	   

	    // rawList 정의
	    List<BoardDto> rawList = boardService.getBoardListByPage(page);
	   
	    // 날짜 포멧터
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    List<Map<String, Object>> boardList = rawList.stream().map(dto -> {
	        Map<String, Object> map = new HashMap<>();
	        map.put("boardNo", dto.getBoardNo());
	        map.put("boardTitle", dto.getBoardTitle());
	        map.put("boardUser", dto.getBoardUser());
	        map.put("createdate", dto.getCreatedate().format(formatter));
	        map.put("updatedate", dto.getUpdatedate().format(formatter));
	        return map;
	    }).collect(Collectors.toList());
	    
	    model.addAttribute("boardList", boardList);
	    model.addAttribute("currentPage", currentPage);
	    model.addAttribute("lastPage", lastPage);
	    model.addAttribute("startPage", startPage);
	    model.addAttribute("endPage", endPage);
	    model.addAttribute("searchWord", searchWord);

	    return "boardList";	// boardList.jsp 로 이동
	}
	
	@GetMapping("/boardOne")
	public String boardOne(@RequestParam("boardNo") int boardNo, Model model) {
	    BoardDto board = boardService.getBoardOne(boardNo);
	    model.addAttribute("board", board);
	    return "boardOne";
	}
	
	@GetMapping("/updateBoard")
	public String updateBoardForm(@RequestParam("boardNo") int boardNo, Model model) {
	    BoardDto board = boardService.getBoardOne(boardNo);
	    model.addAttribute("board", board);
	    return "updateBoard"; // → updateBoard.jsp
	}

	@PostMapping("/updateBoard")
	public String updateBoard(BoardDto board) {
	    boardService.modifyBoard(board); // 수정 처리
	    return "redirect:/boardOne?boardNo=" + board.getBoardNo(); // 상세 페이지로 이동
	}
	
	@PostMapping("/removeBoard")
	public String removeBoard(@RequestParam("boardNo") int boardNo) {
		boardService.removeBoard(boardNo);
		return "redirect:/boardList";
	}
}
