package com.springstudy.bbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springstudy.bbs.dao.BoardDao;
import com.springstudy.bbs.domain.Board;

@Service
public class BoardServiceImpl implements BoardService {
	
	// 한 페이지에 보여질 게시 글의 수
	private static final int PAGE_SIZE = 10;
	
	// 한 페이지에 보여질 페이지네이션의 수 - 페이지 그룹
	private static final int PAGE_GROUP = 10;

	// BoardDao 주입
	@Autowired
	private BoardDao boardDao;
	
	@Override
	public Map<String, Object> boardList(int pageNum) {
		
		int currentPage = pageNum;
		
		// 1=0, 2=10, 3=20, 4=30
		// int startRow = currentPage * PAGE_SIZE - PAGE_SIZE;
		int startRow = (currentPage -1) * PAGE_SIZE;
		List<Board> bList = boardDao.boardList(startRow, PAGE_SIZE);
		
		int listCount = boardDao.getBoardCount();
		int pageCount = listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);
		
		// 1: 1 ~ 2p :
		int startPage = currentPage / PAGE_GROUP * PAGE_GROUP + 1
						- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
		
		// 10, 20, 30
		int endPage = startPage + PAGE_GROUP -1;
		
		// 1 ~ 10, 1, 11 ~ 20, 31 ~ 40
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("bList", bList);
		modelMap.put("pageCount", pageCount);
		modelMap.put("startPage", startPage);
		modelMap.put("endPage", endPage);
		modelMap.put("currentPage", currentPage);
		modelMap.put("listCount", listCount);
		modelMap.put("pageGroup", PAGE_GROUP);
		
		return modelMap;
	}

	@Override
	public Board getBoard(int no) {
		return boardDao.getBoard(no);
	}

	@Override
	public void insertBoard(Board board) {
		boardDao.insertBoard(board);
	}

	@Override
	public boolean isPassCheck(int no, String pass) {
		boolean result = false;
		String dbPass = boardDao.isPassCheck(no, pass);
		
		if(dbPass.equals(pass)) {
			result = true;
		}
		return result;
	}

	@Override
	public void updateBoard(Board board) {		
		boardDao.updateBoard(board);
	}

	@Override
	public void deleteBoard(int no) {
		boardDao.deleteBoard(no);
	}

}
