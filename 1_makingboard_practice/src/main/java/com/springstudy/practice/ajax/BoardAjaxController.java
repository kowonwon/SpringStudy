package com.springstudy.practice.ajax;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springstudy.practice.domain.Reply;
import com.springstudy.practice.service.BoardService;

@Controller
public class BoardAjaxController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping("replyUpdate.ajax")
	@ResponseBody
	public List<Reply> updateReply(Reply reply) {
		boardService.updateReply(reply);
		return boardService.replyList(reply.getBbsNo());
	}
	
	@RequestMapping("replyWrite.ajax")
	@ResponseBody
	public List<Reply> addReply(Reply reply) {
		boardService.addReply(reply);
		return boardService.replyList(reply.getBbsNo());
	}
	
	@RequestMapping("recommend.ajax")
	@ResponseBody
	public Map<String, Integer> recommend(int no, String recommend) {
		return boardService.recommend(no, recommend);
	}
}
