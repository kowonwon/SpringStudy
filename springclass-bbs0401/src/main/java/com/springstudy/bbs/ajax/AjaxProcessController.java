package com.springstudy.bbs.ajax;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springstudy.bbs.service.MemberService;

@Controller
public class AjaxProcessController {
	@Autowired
	private MemberService memberService;
	
	// 응답 데이터 유형 - json
	@RequestMapping("/passCheck.ajax")
	@ResponseBody
	public Map<String, Boolean> memberPassCheck(String id, String pass) {
		
		Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
		boolean result = memberService.memberPassCheck(id, pass);
		resultMap.put("result", result);
		
		return resultMap;
	}
}
