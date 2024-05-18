package com.springstudy.practice.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springstudy.practice.domain.Board;
import com.springstudy.practice.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	private void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@RequestMapping(value="/updateProcess", method=RequestMethod.POST)
	public String updateBoard(HttpServletResponse response,
			PrintWriter out, Board board, RedirectAttributes reAttrs,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum,
			@RequestParam(value="type", defaultValue="null") String type,
			@RequestParam(value="keyword", defaultValue="null") String keyword) throws Exception {
		
		boolean result = boardService.isPassCheck(board.getNo(), board.getPass());
		
		if(! result) {
			response.setContentType("text/html: charset=utf-8");
			out.println("<script>");
			out.println(" alert('비밀번호가 맞지 않습니다.');");
			out.println(" history.back();");
			out.println("</script>");
			return null;
		}
		
		boolean searchOption = (type.equals("null") || keyword.equals("null")) ? false : true;
		
		boardService.updateBoard(board);
		reAttrs.addAttribute("pageNum", pageNum);
		reAttrs.addAttribute("searchOption", searchOption);
		
		if(searchOption) {
			reAttrs.addAttribute("type", type);
			reAttrs.addAttribute("keyword", keyword);
		}
		
		return "redirect:boardList";
	}
		
	
	@RequestMapping("/update")
	public String updateBoard(Model model, HttpServletResponse response,
			PrintWriter out, int no, String pass,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum,
			@RequestParam(value="type", defaultValue="null") String type,
			@RequestParam(value="keyword", defaultValue="null") String keyword) {
		boolean result = boardService.isPassCheck(no, pass);
		
		if(! result) {
			response.setContentType("text/html: charset=utf-8");
			out.println("<script>");
			out.println(" alert('비밀번호가 맞지 않습니다.');");
			out.println(" history.back();");
			out.println("</script>");
			
			return null;
		}
		
		boolean searchOption = (type.equals("null") || keyword.equals("null")) ? false : true;
		
		Board board = boardService.getBoard(no, false);
		model.addAttribute("board", board);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("searchOption", searchOption);
		
		if(searchOption) {
			model.addAttribute("type", type);
			model.addAttribute("keyword", keyword);
		}
		
		return "updateForm";
	}
	
	@RequestMapping("/boardDetail")
	public String boardDetail(Model model, int no,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum,
			@RequestParam(value="type", defaultValue="null") String type,
			@RequestParam(value="keyword", defaultValue="null") String keyword) throws Exception {
		
		boolean searchOption = (type.equals("null") || keyword.equals("null")) ? false : true;
		
		Board board = boardService.getBoard(no, true);
		
		model.addAttribute("board", board);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("searchOption", searchOption);
		
		if(searchOption) {
			model.addAttribute("type", type);
			model.addAttribute("keyword", keyword);
		}
		
		return "boardDetail";
	}
	
	@RequestMapping({"/delete", "deleteBoard"})
	public String deleteBoard(HttpServletResponse response,
			PrintWriter out, int no, String pass,
			RedirectAttributes reAttrs,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum,
			@RequestParam(value="type", defaultValue="null") String type,
			@RequestParam(value="keyword", defaultValue="null") String keyword) throws Exception {
		
		boolean result = boardService.isPassCheck(no, pass);
		
		if(! result) {
			response.setContentType("text/html: charset=utf-8");
			out.println("<script>");
			out.println(" alert('비밀번호가 맞지 않습니다.');");
			out.println(" history.back();");
			out.println("</script>");
			return null;
		}
		
		boolean searchOption = (type.equals("null") || keyword.equals("null")) ? false : true;
		
		boardService.deleteBoard(no);
		
		reAttrs.addAttribute("pageNum", pageNum);
		reAttrs.addAttribute("searchOption", searchOption);
		
		if(searchOption) {
			reAttrs.addAttribute("type", type);
			reAttrs.addAttribute("keyword", keyword);
		}
		
		return "redirect:boardList";
	}
	
	@RequestMapping(value="/writeProcess", method=RequestMethod.POST)
	public String insertBoard(Board board) {
		boardService.insertBoard(board);
		return "redirect:boardList";
	}
	
	//@GetMapping({"/boardList", "/list"})
	@RequestMapping(value={"/boardList", "/list"}, method=RequestMethod.GET)
	public String boardList(Model model,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum,
			@RequestParam(value="type", defaultValue="null") String type,
			@RequestParam(value="keyword", defaultValue="null") String keyword) {
		Map<String, Object> modelMap = boardService.boardList(pageNum, type, keyword);
		
		model.addAllAttributes(modelMap);
		
		return "boardList";
	}
}