package com.springstudy.bbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springstudy.bbs.domain.Board;
import com.springstudy.bbs.service.BoardService;

@Controller
public class BoardController {

	// BoardService 주입
	@Autowired
	private BoardService boardService;
	
	
	// 게시 글 리스트 요청을 처리하는 메서드
	//@RequestMapping(value={"/boardList", "/list"}, method=RequestMethod.GET)
	@GetMapping({"/boardList", "/list"})
	public String boardList(Model model) {
		
		List<Board> bList = boardService.boardList();
		model.addAttribute("bList", bList);		
	
		// viewResolver에 설정되어 있는 prefix, suffix
		// /WEB-INF/views/ + boardList + .jsp
		return "boardList";
	}
	
}
