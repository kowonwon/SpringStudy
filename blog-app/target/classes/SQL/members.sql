---############################################################################################################
---####### Blog App 만들기
---############################################################################################################


-- 1차 구현에 필요한 테이블
-- 카테고리 테이블, 포스트 테이블, 댓글 테이블
-- 별도 추가 테이블 : 회원 테이블


---############################################################################################################
-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS spring;
use spring;


---############################################################################################################
-- 회원 테이블
-- 회원아이디, 이름, 닉네임, 비밀번호, 이메일, 연락처, 우편번호, 기본주소, 상세주소, 등록일
-- member_id, name, nickname, pass, email, mobile, zipcode, address1, address2, reg_date

DROP TABLE IF EXISTS members;
CREATE TABLE IF NOT EXISTS members(	
	member_id VARCHAR(20) PRIMARY KEY, 
	name VARCHAR(10) NOT NULL,
    nickname VARCHAR(20) NOT NULL,
	pass VARCHAR(20) NOT NULL,    
	email VARCHAR(30) NOT NULL,    
	mobile VARCHAR(13) NOT NULL,
	zipcode VARCHAR(5) NOT NULL,
	address1 VARCHAR(80) NOT NULL,
	address2 VARCHAR(60),
	reg_date TIMESTAMP NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


# 회원 정보 추가
INSERT INTO members VALUES('midas', '홍길동', 'FlowerPig', '1234', 
	'midas22@naver.com', '010-1234-5678', '14409', 
	'경기 부천시 오정구 수주로 18 (고강동, 동문미도아파트)', 
    '미도아파트 1동 513호', '2022-08-02 12:10:30');
INSERT INTO members VALUES('admin', '이순신', '블랙이글', '1234', 
	'admin33@naver.com', '010-4321-8765', '08292', 
	'서울 구로구 구로중앙로34길 33-4(구로동, 영림빌딩)', 
    '경영기술개발원 교육센터 1층 교무실', '2022-08-04 11:20:50');
INSERT INTO members VALUES('servlet', '강감찬', '현명한여행자', '1234', 
	'servlet@daum.net', '010-5687-5678', '06043', 
	'서울 강남구 강남대로146길 28 (논현동, 논현아파트)', 
    '논현신동아파밀리에아파트 103동 302호', '2022-08-05 12:10:30');
INSERT INTO members VALUES('bolgservice', '왕호감', '방랑시인', '1234', 
	'blogservice@daum.net', '010-9652-1234', '05225', 
	'서울특별시 강동구 동남로 918 (고덕동, 강동구자전거서비스센터)', 
    '1층 3호', '2022-08-06 12:10:30');
INSERT INTO members VALUES('blogpost', '어머나', 'bloghera', '1234', 
	'blogservice@daum.net', '010-9652-1234', '07641', 
	'서울특별시 강서구 수명로2길 50 (내발산동, 강서구영유아플라자)', 
    '3층 303호', '2022-08-07 16:21:53');
    
commit;
SELECT * FROM members;
