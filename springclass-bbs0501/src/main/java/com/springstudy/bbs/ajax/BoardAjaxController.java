package com.springstudy.bbs.ajax;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springstudy.bbs.domain.Reply;
import com.springstudy.bbs.service.BoardService;

@Controller
public class BoardAjaxController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/replyWrite.ajax")
	@ResponseBody
	public List<Reply> addReply(Reply reply) {
		
		// 댓글을 추가
		boardService.addReply(reply);
		
		// 갱신된 댓글 리스트를 읽어와서 반환
		// [{"no": 1, "nnsNo": 200, "replyContent": "오늘 뭐 먹dj"}, {}, {}]
		return boardService.replyList(reply.getBbsNo());
	}
	
	@RequestMapping("recommend.ajax")
	@ResponseBody
	public Map<String, Integer> recommend(int no, String recommend) {
		// { "recommend": 20, "thank": 30}
		return boardService.recommend(no, recommend);
	}
}
