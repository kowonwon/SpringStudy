package com.springstudy.after.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springstudy.after.domain.Board;
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
	
	@RequestMapping("/kakaopay.cls")
	@ResponseBody
	public String kakaopay() {
		try {
			URL 주소 = new URL("https://kapi.kakao.com/...");
			HttpURLConnection 서버연결 = (HttpURLConnection) 주소.openConnection();
			서버연결.setRequestMethod("POST");
			서버연결.setRequestProperty("Authorization", "kakaoAK {adminkey}");
			서버연결.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			서버연결.setDoOutput(true);
			String 파라미터 = "cid=TC0ONETIME&partnerOrderId=...";
			OutputStream 주는애 = 서버연결.getOutputStream();
			DataOutputStream 데이터주는애 = new DataOutputStream(주는애);
			데이터주는애.writeBytes(파라미터);
			데이터주는애.close();
			
			int 결과 = 서버연결.getResponseCode();
			
			InputStream 받는애;
			if(결과 == 200) {
				받는애 = 서버연결.getInputStream();
			} else {
				받는애 = 서버연결.getErrorStream();
			}
			InputStreamReader 읽는애 = new InputStreamReader(받는애);
			BufferedReader 형변환하는애 = new BufferedReader(읽는애);
			return 형변환하는애.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{\"result\":\"NO\"}";
	}
}