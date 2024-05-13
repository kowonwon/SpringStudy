package com.springstudy.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springstudy.practice.domain.Board;
import com.springstudy.practice.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	private void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@RequestMapping("/boardDetail")
	public String boardDetail(Model model, int no) {
		Board board = boardService.getBoard(no);
		model.addAttribute("board", board);
		return "boardDetail";
	}
	
	//@GetMapping({"/boardList", "/list"})
	@RequestMapping(value={"/boardList", "/list"}, method=RequestMethod.GET)
	public String boardList(Model model) {
		List<Board> bList = boardService.boardList();
		
		model.addAttribute("bList", bList);
		
		return "boardList";
	}
	
	
}