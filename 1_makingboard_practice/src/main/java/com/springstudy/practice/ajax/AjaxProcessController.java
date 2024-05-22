package com.springstudy.practice.ajax;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springstudy.practice.service.MemberService;

@Controller
public class AjaxProcessController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/passCheck.ajax")
	@ResponseBody
	public Map<String, Boolean> memberPassCheck(String id, String pass) {
		boolean result = memberService.memberPassCheck(id, pass);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("result", result);
		return map;
	}
}
