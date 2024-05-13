package com.springstudy.bbs.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springstudy.bbs.domain.Board;
import com.springstudy.bbs.service.BoardService;

@Controller
public class BoardController {

	// BoardService 주입
	@Autowired
	private BoardService boardService;
	
	// 게시 글 삭제하기 요청을 처리하는 메서드
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(HttpServletResponse response, 
			PrintWriter out, int no, String pass) {
		
		boolean result = boardService.isPassCheck(no, pass);
		
		if(!result) { // 비밀번호가 맞지 않으면
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>");
			out.println("	alert('비밀번호가 맞지 않음');");
			out.println("	history.back();");
			out.println("</script>");
			
			return null;
		}		
		
		// 비밀번호가 맞으면 게시 글을 삭제
		boardService.deleteBoard(no);
		
		// 삭제하기가 완료되면 게시글 리스트로 리다이렉트
		return "redirect:boardList";
	}
	
	// 게시 글 수정 폼으로부터 게시 글 수정 요청을 받아 처리하는 메서드
	@PostMapping("/updateProcess")
	public String updateProcess(HttpServletResponse response,
			PrintWriter out, Board board) {
		
		boolean result = boardService.isPassCheck(board.getNo(), board.getPass());
		
		if(!result) { // 비밀번호가 맞지 않으면
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>");
			out.println("	alert('비밀번호가 맞지 않음');");
			out.println("	history.back();");
			out.println("</script>");
			
			return null;
		}
		
		// 비밀번호가 맞으면 - 게시 글을 수정
		boardService.updateBoard(board);
		
		// 게시 글 수정이 끝나면 - 게시 글 리스트로 리다이렉트
		return "redirect:boardList";
	}
	
	
	// 게시 글 수정 폼 요청 처리 메서드
	@PostMapping("/update")
	public String updateBoard(Model model, HttpServletResponse response,
			PrintWriter out, int no, String pass) {
		
		boolean result = boardService.isPassCheck(no, pass);
		
		if(!result) { // 비밀번호가 맞지 않으면
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>");
			out.println("	alert('비밀번호가 맞지 않음');");
			out.println("	history.back();");			
			out.println("</script>");
			
			return null;
		}
		
		// 비밀번호가 맞으면 - no에 해당하는 게시 글을 읽어와 화면에 출력
		Board board = boardService.getBoard(no);
		model.addAttribute("board", board);
		
		return "updateForm";
	}
	
	
	// 게시 글 쓰기 폼에서 들어오는 게시 글 쓰기 요청을 처리하는 메서드
	//@RequestMapping(value="/writeProcess", method=RequestMethod.POST)
	@PostMapping("/writeProcess")
	public String insertBoard(Board board) {
		boardService.insertBoard(board);
		
		System.out.println("board.no : " + board.getNo());
		
		return "redirect:boardList";
	}
	
	
	// 게시 글 상세보기 요청을 처리하는 메서드
	@RequestMapping("/boardDetail")
	public String boardDetail(Model model, int no) {
		
		Board board = boardService.getBoard(no);
		model.addAttribute("board", board);
		
		// viewResolver   /WEB-INF/index.jsp?body=views/ + boardDetail + .jsp
		return "boardDetail";
	}
	
	
	// 게시 글 리스트 요청을 처리하는 메서드
	//@RequestMapping(value={"/boardList", "/list"}, method=RequestMethod.GET)
	@GetMapping({"/boardList", "/list"})
	public String boardList(Model model,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum) {
		
		Map<String, Object> modelMap = boardService.boardList(pageNum);
		model.addAllAttributes(modelMap);
	
		// viewResolver에 설정되어 있는 prefix, suffix
		// /WEB-INF/views/ + boardList + .jsp
		return "boardList";
	}
	
}
