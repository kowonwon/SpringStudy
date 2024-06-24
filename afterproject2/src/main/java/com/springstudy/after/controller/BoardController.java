package com.springstudy.after.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springstudy.after.domain.Board;
import com.springstudy.after.domain.Payment;
import com.springstudy.after.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value= {"/boardList", "/list"}, method=RequestMethod.GET)
	public String boardList(Model model) {
		List<Board> bList = boardService.boardList();
		model.addAttribute("bList", bList);
		return "boardList";
	}
	
	@RequestMapping(value="/payProcess", method=RequestMethod.POST)
	public String insertPayment(Payment payment) {
		boardService.insertPayment(payment);
		return "redirect:boardList";
	}
}