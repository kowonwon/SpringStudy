package com.springstudy.practice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springstudy.practice.dao.BoardDao;
import com.springstudy.practice.domain.Board;
import com.springstudy.practice.domain.Reply;

@Service
public class BoardServiceImpl implements BoardService {
	
	private static final int PAGE_SIZE = 10;
	private static final int PAGE_GROUP = 10;
	
	@Autowired
	private BoardDao boardDao;
	
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	@Override
	public Map<String, Object> boardList(int pageNum, String type, String keyword) {
		int currentPage = pageNum;
		
		int startRow = (currentPage - 1) * PAGE_SIZE;
		
		boolean searchOption = (type.equals("null") || keyword.equals("null")) ? false : true;
		
		int listCount = 0;
		listCount = boardDao.getBoardCount(type, keyword);
		
		List<Board> bList = boardDao.boardList(startRow, PAGE_SIZE, type, keyword);
		
		int pageCount = listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);
		
		int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
				- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
		
		int endPage = startPage + PAGE_GROUP - 1;
		
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
		modelMap.put("searchOption", searchOption);
		
		if(searchOption) {
			modelMap.put("type", type);
			modelMap.put("keyword", keyword);
		}
		
		return modelMap;
	}

	@Override
	public Board getBoard(int no) {
		return boardDao.getBoard(no);
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
	public void insertBoard(Board board) {
		boardDao.insertBoard(board);
	}

	@Override
	public void updateBoard(Board board) {
		boardDao.updateBoard(board);
	}

	@Override
	public void deleteBoard(int no) {
		boardDao.deleteBoard(no);
	}

	@Override
	public Board getBoard(int no, boolean isCount) {
		if(isCount) {
			boardDao.incrementReadCount(no);
		}
		return boardDao.getBoard(no);
	}

	@Override
	public List<Reply> replyList(int no) {
		return boardDao.replyList(no);
	}

	@Override
	public Map<String, Integer> recommend(int no, String recommend) {
		
		boardDao.updateRecommend(no, recommend);
		Board board = boardDao.getRecommend(no);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("recommend", board.getRecommend());
		map.put("thank", board.getThank());
		return map;
	}

	@Override
	public void addReply(Reply reply) {
		boardDao.addReply(reply);
	}

	@Override
	public void updateReply(Reply reply) {
		boardDao.updateReply(reply);
	}
}
