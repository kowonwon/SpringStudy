package com.springstudy.bbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springstudy.bbs.dao.BoardDao;
import com.springstudy.bbs.domain.Board;

// 이 클래스가 서비스 계층(비즈니스 로직)의 컴포넌트(Bean) 임을 선언하고 있다.
@Service
public class BoardServiceImpl implements BoardService {

	// 한 페이지에 보여 줄 게시 글의 수를 상수로 선언
	private static final int PAGE_SIZE = 10;
	
	/* 한 페이지에 보여질 페이지 그룹의 수를 상수로 선언
	 * [이전] 1 2 3 4 5 6 7 8 9 10 [다음]	
	 **/
	private static final int PAGE_GROUP = 10;
	
	/* 인스턴스 필드에 @Autowired annotation을 사용하면 접근지정자가 
	 * private이고 setter 메서드가 없다 하더라도 문제없이 주입 된다.
	 * 하지만 우리는 항상 setter 메서드를 준비하는 습관을 들일 수 있도록 하자.
	 * 
	 * setter 주입 방식은 스프링이 기본 생성자를 통해 이 클래스의 인스턴스를
	 * 생성한 후 setter 주입 방식으로 BoardDao 타입의 객체를 주입하기 때문에  
	 * 기본 생성자가 존재해야 하지만 이 클래스에 다른 생성자가 존재하지 않으므로
	 * 컴파일러에 의해 기본 생성자가 만들어 진다.
	 **/
	@Autowired
	private BoardDao boardDao;
	
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	/* BoardDao를 이용해 게시판 테이블에서 한 페이지에 해당하는 게시 글
	 * 리스트와 페이징 처리에 필요한 데이터를 Map 객체로 반환 하는 메소드
	 **/
	@Override
	public Map<String, Object> boardList(int pageNum) {
		
		// 요청 파라미터의 pageNum을 현재 페이지로 설정
		int currentPage = pageNum;
				
		/* 요청한 페이지에 해당하는 게시 글 리스트의 첫 번째 행의 값을 계산
		 * MySQL에서 검색된 게시 글 리스트의 row에 대한 index는 0부터 시작한다.
		 * 현재 페이지가 1일 경우 startRow는 0, 2페이지일 경우 startRow는 10이 된다.
		 * 
		 * 예를 들어 3페이지에 해당하는 게시 글 리스트를 가져 온다면 한 페이지에 보여줄
		 * 게시 글 리스트의 수가 10개로 지정되어 있으므로 startRow는 20이 된다. 
		 * 즉 아래의 공식에 의해 startRow(20) = (3 - 1) * 10;
		 * 첫 번째 페이지 startRow = 0, 두 번째 페이지 startRow = 10이 된다.
		 **/		
		int startRow = (currentPage - 1) * PAGE_SIZE;
		
		// BoardDao를 이용해 전체 게시 글 수를 얻어온다.		
		int listCount = boardDao.getBoardCount();		
		
		
		/* 현재 페이지에 해당하는 게시 글 리스트를 BoardDao를 이용해 DB에서 읽어온다. 
		 * Oracle에서는 페이징 처리를 위해 의사컬럼인 ROWNUM을 사용했지만
		 * MySQL은 검색된 데이터에서 특정 행 번호부터 지정한 개수 만큼 행을 읽어오는
		 * LIMIT 명령을 제공하고 있다. LIMIT의 첫 번째 매개변수에 가져올 데이터의
		 * 시작 행을 지정하고 두 번째 매개변수에 가져올 데이터의 개수를 지정하면 된다.
		 **/
		List<Board> boardList = boardDao.boardList(startRow, PAGE_SIZE);
		
		/* 페이지 그룹 이동 처리를 위해 전체 페이지를 계산 한다.
		 * [이전] 11 12 13...   또는   ... 8 9 10 [다음] 과 같은 페이징 처리
		 * 전체 페이지 = 전체 게시 글 수 / 한 페이지에 표시할 게시 글 수가 되는데 
		 * 이 계산식에서 나머지가 존재하면 전체 페이지 수는 전체 페이지 + 1이 된다.
		 **/	
		int pageCount = 
				listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);
		
		/* 페이지 그룹 처리를 위해 페이지 그룹별 시작 페이지와 마지막 페이지를 계산
		 * 페이지 그룹 별 시작 페이지 : 1, 11, 21, 31...
		 * 첫 번째 페이지 그룹에서 페이지 리스트는 1 ~ 10이 되므로 currentPage가
		 * 1 ~ 10 사이에 있으면 startPage는 1이 되고 11 ~ 20 사이면 11이 된다.
		 * 
		 * 정수형 연산의 특징을 이용해 startPage를 아래와 같이 구할 수 있다.
		 * 아래 연산식으로 계산된 결과를 보면 현재 그룹의 마지막 페이지일 경우
		 * startPage가 다음 그룹의 시작 페이지가 나오게 되므로 삼항 연자자를
		 * 사용해 현재 페이지가 속한 그룹의 startPage가 되도록 조정 하였다.
		 * 즉 currentPage가 10일 경우 다음 페이지 그룹의 시작 페이지가 되므로
		 * 삼항 연산자를 사용하여 PAGE_GROUP으로 나눈 나머지가 0이면
		 * PAGE_GROUP을 차감하여 현재 그룹의 시작 페이지가 되도록 하였다.
		 **/
		int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
				- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);		
					
		// 현재 페이지 그룹의 마지막 페이지 : 10, 20, 30...
		int endPage = startPage + PAGE_GROUP - 1;			
		
		/* 위의 식에서 endPage를 구하게 되면 endPage는 항상 PAGE_GROUP의
		 * 크기만큼 증가(10, 20, 30 ...) 되므로 맨 마지막 페이지 그룹의 endPage가
		 * 정확하지 못할 경우가 발생하게 된다. 다시 말해 전체 페이지가 53페이지라고
		 * 가정하면 위의 식에서 계산된 endPage는 60 페이지가 되지만 실제로 
		 * 60페이지는 존재하지 않는 페이지이므로 문제가 발생하게 된다.
		 * 그래서 맨 마지막 페이지에 대한 보정이 필요하여 아래와 같이 endPage와
		 * pageCount를 비교하여 현재 페이지 그룹에서 endPage가 pageCount 보다
		 * 크다면 pageCount를 endPage로 지정 하였다. 즉 현재 페이지 그룹이
		 * 마지막 페이지 그룹이면 endPage는 전체 페이지 수가 되도록 지정한 것이다.
		 **/
		if(endPage > pageCount) {
			endPage = pageCount;
		}	
		
		/* View 페이지에서 필요한 데이터를 Map에 저장한다.
		 * 현재 페이지, 전체 페이지 수, 페이지 그룹의 시작 페이지와 마지막 페이지
		 * 게시 글 리스트의 수, 한 페이지에 보여 줄 게시 글 리스트의 데이터를 Map에
		 * 저장해 컨트롤러로 전달한다.
		 **/
		Map<String, Object> modelMap = new HashMap<String, Object>();		
		
		modelMap.put("boardList", boardList);
		modelMap.put("pageCount", pageCount);
		modelMap.put("startPage", startPage);
		modelMap.put("endPage", endPage);
		modelMap.put("currentPage", currentPage);
		modelMap.put("listCount", listCount);
		modelMap.put("pageGroup", PAGE_GROUP);
		
		return modelMap;
	}

	/* BoardDao를 이용해 게시판 테이블에서
	 * no에 해당하는 게시 글 을 읽어와 반환하는 메서드 
 	 * isCount == true 면 게시 상세보기, false 면 게시 글 수정 폼 요청임 
	 **/
	@Override
	public Board getBoard(int no, boolean isCount) {
		
		// 게시 글 상세보기 요청만 게시 글 읽은 횟수를 증가시킨다.
		if(isCount) {
			boardDao.incrementReadCount(no);
		}
		return boardDao.getBoard(no);
	}

	// BoardDao를 이용해 새로운 게시 글을 추가하는 메서드
	@Override
	public void insertBoard(Board board) {
		boardDao.insertBoard(board);
	}

	/* 게시 글 수정과 삭제 할 때 비밀번호가 맞는지 체크하는 메서드	
	 * 
	 * - 게시 글의 비밀번호가 맞으면 : true를 반환
	 * - 게시 글의 비밀번호가 맞지 않으면 : false를 반환
	 **/
	public boolean isPassCheck(int no, String pass) {	
		
		boolean result = false;
		
		// BoardDao를 이용해 DB에서 no에 해당하는 비밀번호를 읽어온다.
		String dbPass = boardDao.isPassCheck(no, pass);
		
		if(dbPass.equals(pass)) {
			result = true;		
		}
		
		// 비밀번호가 맞으면 true, 맞지 않으면 false가 반환된다.
		return result;
	}
	
	// BoardDao를 이용해 게시 글을 수정하는 메서드
	@Override
	public void updateBoard(Board board) {
		boardDao.updateBoard(board);
	}

	// BoardDao를 이용해 no에 해당하는 게시 글을 삭제하는 메서드
	@Override
	public void deleteBoard(int no) {
		boardDao.deleteBoard(no);
	}
}
