package com.springstudy.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springstudy.bbs.dao.MemberDao;
import com.springstudy.bbs.domain.Member;


 // 이 클래스가 서비스(비즈니스 로직) 계층의 컴포넌트임을 선언한다. 
@Service
public class MemberServiceImpl implements MemberService {
	
	/* 인스턴스 필드에 @Autowired annotation을 사용하면 접근지정자가 
	 * private이고 setter 메서드가 없다고 하더라도 문제없이 주입 할 수 있다.
	 * 기본 생성자가 반드시 존재해야 스프링이 이 클래스의 인스턴스를 생성한 후
	 * setter 주입 방식으로 주입해 준다. 이 클래스에는 다른 생성자가 존재하지
	 * 않으므로 컴파일러에 의해 자동으로 기본 생성자가 만들어 진다.
	 **/
	@Autowired
	private MemberDao memberDao;
	
	/* 회원 비밀번호에 대한 암호화 인코딩관련 스프링 시큐리티의 PasswordEncoder
	 * 회원 로그인 요청시 DB 테이블에 암호화 인코딩되어 저장된 비밀번호와 사용자가
	 * 입력한 일반 문자열 비밀번호를 비교하는데 사용되고 회원 가입과 회원 정보 수정에서
	 * 사용자가 입력한 비밀번호를 암호화 인코딩하여 저장하는데도 사용된다.
	 **/
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	// MemberDao를 이용해 로그인 요청 처리 결과를 반환하는 메서드
	@Override
	public int login(String id, String pass) {		
		
		Member m = memberDao.getMember(id);
		
		int result = -1;		
		
		// id가 존재하지 않으면
		if(m == null) {
			return result;
		}
		
		/* 로그인 성공
		 * BCryptPasswordEncoder 객체의 matches 메소드를 이용해 암호가 맞는지 확인
		 * matches() 메소드의 첫 번 인수로 인코딩이 않된 문자열, 두 번째 인수로 인코딩된 
		 * 문자열을 지정하면 두 문자열의 원본 데이터가 같을 경우 true를 반환해 준다.
		 **/		
		if(passwordEncoder.matches(pass, m.getPass())) {
			result = 1;
			
		} else { // 비밀번호가 틀리면
				result = 0;
		}
		
		return result;
	}
	
	// MemberDao를 이용해 id에 해당하는 회원 정보를 가져오는 메서드
	@Override
	public Member getMember(String id) {		
		return memberDao.getMember(id);
	}
	
	// 회원 가입시 아이디 중복을 체크하는 메서드
	@Override
	public boolean overlapIdCheck(String id) {
		Member member = memberDao.getMember(id);
		System.out.println("overlapIdCheck - member : " + member);
		if(member == null) {
			return false;
		}
		return true;
	}
	
	// 회원 정보를 DAO를 이용해 회원 테이블에 저장하는 메서드
	@Override
	public void addMember(Member member) {
		
		// BCryptPasswordEncoder 객체를 이용해 비밀번호를 암호화한 후 저장
		member.setPass(passwordEncoder.encode(member.getPass()));
		
		/* 아래는 문자열 "1234"를 BCryptPasswordEncoder 객체를 사용해
		 * 암호화한 것으로 동일한 문자열을 암호화 하더라도 그 결과는 모두 다를 수 있다. 
		 **/ 
		// $2a$10$aWYm2BGI/0iMuemBeF4Y8.7WZeVKAoudv/VzgQx697lYlZgQxr/pe
		// $2a$10$b3t8sn6QZGHYaRx3OS5KUuPxzWZdY5yHPRxlSdAgByQ7v0BlCLzrO
		// $2a$10$.g6l.wyIFO1.j4u4gvVtKOnG9ACBUT1GRlDwlMZcjBxZPrCAURLaG
		// $2a$10$l37iiJWozST9.2EI11vjqOmSk9rus.5cawhTiPuQagCzVNTZcoFDa
		// $2a$10$qtxKvIgk.URhyqgx93KYFuw4e8Rh3IhIkR0CTZhd.HazLal7d3rqK
		System.out.println(member.getPass());
		memberDao.addMember(member);
	}
	
	// 회원 정보 수정 시에 기존 비밀번호가 맞는지 체크하는 메서드
	public boolean memberPassCheck(String id, String pass) {		
		
		String dbPass = memberDao.memberPassCheck(id);		
		boolean result = false;		

		/* 비밀번호가 맞으면 true를 반환하도록 작성한다.
		 * BCryptPasswordEncoder 객체의 matches 메소드를 이용해 암호가 맞는지 확인
		 * matches() 메소드의 첫 번 인수로 인코딩이 않된 문자열, 두 번째 인수로 인코딩된 
		 * 문자열을 지정하면 두 문자열의 원본 데이터가 같을 경우 true를 반환해 준다.
		 **/		
		if(passwordEncoder.matches(pass, dbPass)) {
			result = true;	
		}
		return result;	
	}
	
	// 회원 정보를 DAO를 이용해 회원 테이블에서 수정하는 메서드
	public void updateMember(Member member) {
		
		// BCryptPasswordEncoder 객체를 이용해 비밀번호를 암호화한 후 저장
		member.setPass(passwordEncoder.encode(member.getPass()));
		System.out.println(member.getPass());
		
		memberDao.updateMember(member);
	}
}
