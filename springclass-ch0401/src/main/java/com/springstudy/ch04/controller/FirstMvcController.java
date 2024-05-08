package com.springstudy.ch04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springstudy.ch04.service.FirstMvcService;

@Controller
@RequestMapping("/first")
public class FirstMvcController {
	
	@Autowired
	private FirstMvcService service;
	
	@RequestMapping("/postRequest")
	public String request(HttpServletRequest request, String name) throws Exception {
//		request.setCharacterEncoding("utf-8");
//		System.out.println("name : " + request.getParameter("name"));
		System.out.println("name : " + name);
		return "main";
	}
	
	@RequestMapping({"/", "/intro", "/index"})
	public String index() {
		
		// /WEB-INF/views/ + main + .jsp
		return "/main";
	}
	
	@RequestMapping(value="/detail")
	public String detail(HttpServletRequest request, Model model,
			@RequestParam(value="num", defaultValue="1") int no, String id) {
		
		String msg = service.getMessage(no, id);
		model.addAttribute("msg", msg);
		model.addAttribute("title", "명준이의 디테일 당부");
		model.addAttribute("comment", "정말루 ~ 스프링 공부 열심히 해야 해여~~ %_%");
		
		// /WEB-INF/index.jsp?body=views/ + detail + .jsp
		
		return "detail";
	}
}