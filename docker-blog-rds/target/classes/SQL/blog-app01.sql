---############################################################################################################
---####### Blog APP 만들기
---############################################################################################################


-- blog-app 구현에 필요한 테이블
-- 카테고리 테이블, 포스트 테이블, 댓글 테이블
-- 별도 추가 테이블 : 회원 테이블


---############################################################################################################
-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS spring;
use spring;


---############################################################################################################
-- 포스트 카테고리 테이블
-- 카테고리번호, 카테고리명, 카테고리설명
DROP TABLE IF EXISTS categories;
CREATE TABLE IF NOT EXISTS categories (
	category_no   INTEGER AUTO_INCREMENT PRIMARY KEY,
	category_name VARCHAR(20)  NOT NULL,
	description   VARCHAR(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categories(category_name, description) VALUES('HTML5', 'HTML 문법 및 활용에 대한 정보');
INSERT INTO categories(category_name, description) VALUES('CSS3', '웹 페이지 레이아웃 및 스타일과 디자인에 대한 정보');
INSERT INTO categories(category_name, description) VALUES('JavaScript', '자바스크립트 활용에 대한 기술정보');
INSERT INTO categories(category_name, description) VALUES('Database', '오라클 및 MySQL에 대한 DB 운영 및 관리');
INSERT INTO categories(category_name, description) VALUES('Java', '자바 프로그래밍 문법 및 활용');
INSERT INTO categories(category_name, description) VALUES('JSP', '자바 웹 프로그램 SERVLET & JSP 활용');
INSERT INTO categories(category_name, description) VALUES('Spring', '스프링프레임워크 활용에 대한 정보');
INSERT INTO categories(category_name, description) VALUES('R통계분석', '통계패키지 R활용에 대한 정보');
INSERT INTO categories(category_name, description) VALUES('PythonML', 'Python을 활용한 데이터 분석 및 머신러닝 기법에 대한 정보');
INSERT INTO categories(category_name, description) VALUES('etc', '카테고리에 해당되지 않는 포스트');

COMMIT;
SELECT * FROM categories;


---############################################################################################################
-- 블로그 포스트 테이블
-- 1차 : 글번호, 제목, 내용, 작성자, 메인이미지, 첨부파일, 읽은횟수, 최초작성일(Regstration Date), 최종수정일(Modified date)
-- 1차 : no, title, writer, content, main_img, file1, read_count, reg_date, mod_date
-- 2차 : 작성자(회원 로그인 상태에서 자동 입력되도록), 카테고리, 해시태그, 공개여부 등
-- 블로그 포스트 작성에 사용한 이미지 파일 정보를 저장하는 테이블, 첨부파일 정보를 저장하는 테이블 테이블, 댓글 테이블, 공감 테이블, 이웃 테이블 등이 추가로 필요함
-- TEXT 최대 65536Byte(한글 21,844자), MEDIUMTEXT 최대 16777215Byte(약 16MB), LOGNTEXT 최대 4,294,967,295Byte(약 4GB)
DROP TABLE IF EXISTS posts CASCADE;
SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE IF NOT EXISTS posts(
	post_no 	INTEGER AUTO_INCREMENT PRIMARY KEY,
	title 		VARCHAR(50) 	NOT NULL,
    writer 		VARCHAR(10) 	NOT NULL,
	content 	TEXT 			NOT NULL,
    main_img 	VARCHAR(100),	-- 블로그 포스트 작성 기능을 추가할 때 NOT NULL 제약조건 적용 검토
    file1 		VARCHAR(100),
    read_count 	INTEGER(5) 		NOT NULL,
    reg_date 	TIMESTAMP 		NOT NULL,
    mod_date 	TIMESTAMP 		NOT NULL,
    category_no	INTEGER 		NOT NULL,
    CONSTRAINT posts_category_fk FOREIGN KEY(category_no) REFERENCES categories(category_no)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO posts(title, writer, content, main_img, file1, read_count, reg_date, mod_date, category_no) 
	VALUES('MySQL 최적의 데이터 타입 선택 방법', 'FlowerPig', 
		'MySQL은 다양한 데이터 타입을 지원하고 있는데, 데이터를 저장하는 데 적합한 데이터 타입을\r\n고르는 것은 좋은 성능을 얻는 데 필수적이다. MySQL 최적의 데이터 타입 선택 하는 방법에\r\n관하여 다양한 타입과 특징에 대해서 살펴보자.\r\n\r\n\r\n
        타입은 작을수록 좋다\r\n일반적으로 데이터를 저장하고 표현하는 데 문제가 없는 데이터 타입 중 가장 작은 것을 골라야\r\n한다. 보통 작은 데이터 타입일수록 디스크나 메모리, CPU 캐시에 더 적은공간을 사용하기\r\n때문에 더 빠르다. 게다가 작은 데이터 타입일수록 CPU도 덜 소비한다.\r\n\r\n
        하지만 저장할 값의 크기를 너무 작게 추정하지 않도록 주의해야 한다. 스키마의 이곳저곳에서\r\n데이터 타입의 범위를 늘리는 것은 어렵고, 시간을 많이 소용하게 되는 작업이기 때문이다.\r\n\r\n
        타입은 단순한 게 좋다\r\n보통 간단한 데이터 타입을 처리할수록 CPU 사이클로 덜 소비한다. 예로 문자 비교보다는 정수\r\n비교가 비용이 더 저렴하다. 문자 비교는 문자 셋 (Character set)과 콜레이션 (Collation) 으로\r\n인해 복잡해지기 때문이다. 날짜와 시간은 문자열로 저장하지 말고, MySQL의 내장 형식에\r\n저장해야 하며 IP 주소는 정수를 이용해 저장해야 한다.\r\n\r\n', 
		NULL, NULL, 17, '2022-06-01 11:32:18', '2022-07-08 11:29:39', 4); 
INSERT INTO posts(title, writer, content, main_img, file1, read_count, reg_date, mod_date, category_no) 
	VALUES('jQuery 선택자에 대해서...', 'FlowerPig', 
		'안녕하세요\r\njQuery에서 선택자가 어쩌구 저쩌구 해서리...\r\n\r\n\r\r\n\n이렇게 jQuery 선택자를 활용해 DOM에 쉽게 접근할 수 있도록 선택자를 바꾸게 되었습니다.\r\n\r\n웹 문서에서 문서 객체를 다룰 때 css 선택자를 활용하면 편리하기 때문에\r\n여러분도 이용해 참고 하시기 바랍니다.\r\n\r\n
        기본 선택자\r\n\r\n- $(\'h1\').css(\'color\', \'red\');\r\n- (\'h1\') 선택자와, css(\'color\', \'red\') 메서드\r\n- 문서 객체를 다룰 때 사용하는 형태\r\n- CSS 선택자와 유사\r\n\r\n... 중략 ... \r\n\r\n감사합니다.', 
		NULL, NULL, 17, '2022-06-03 12:44:58', '2022-06-09 01:12:15', 3);
INSERT INTO posts(title, writer, content, main_img, file1, read_count, reg_date, mod_date, category_no) 
	VALUES('[MySQL] PRIMARY KEY, FOREIGN KEY 설정하는 다양한 방법', 'FlowerPig', 
		'PRIMARY KEY
        
        기본키 설정을 하면 해당 필드는 NOT NULL과 UNIQUE 제약 조건의 특징을 모두 가진다.

 
1) CREATE문으로 설정(생성)

CREATE TABLE 테이블이름
(
    필드이름 필드타입 PRIMARY KEY,
    ...
)

CREATE TABLE 테이블이름
(
    필드이름 필드타입,
    ...,
    [CONSTRAINT 제약조건이름] PRIMARY KEY (필드이름)
)


2) ALTER문으로 설정(추가, 수정)

-- 추가
ALTER TABLE 테이블이름
ADD 필드이름 필드타입 PRIMARY KEY

ALTER TABLE 테이블이름
ADD [CONSTRAINT 제약조건이름] PRIMARY KEY (필드이름)

-- 수정
ALTER TABLE 테이블이름
MODIFY COLUMN 필드이름 필드타입 PRIMARY KEY

ALTER TABLE 테이블이름
MODIFY COLUMN [CONSTRAINT 제약조건이름] PRIMARY KEY (필드이름)
\r\n\r\n', 
NULL, NULL, 29, '2022-06-04 10:11:35', '2022-06-20 16:30:59', 4); 

commit;
SELECT * FROM posts ORDER BY post_no DESC;




---############################################################################################################
-- 블로그 포스트 댓글 테이블
-- 댓글번호, 댓글내용, 작성자, 작성일, 포스트글번호
DROP TABLE IF EXISTS post_replys;
CREATE TABLE IF NOT EXISTS post_replys (
	reply_no INTEGER	AUTO_INCREMENT PRIMARY KEY,
	content  VARCHAR(200) NOT NULL,
	writer   VARCHAR(20)  NOT NULL,
	reg_date TIMESTAMP    NOT NULL,
	post_no  INTEGER      NOT NULL,
    CONSTRAINT post_replys_fk FOREIGN KEY(post_no) REFERENCES posts(post_no)
);

INSERT INTO post_replys(post_no, content, writer, reg_date) VALUES(3, '포스트 내용이 너무 유익해서 읽으면서 감사한 마음이 생기네요...^_^\r\n감사합니다.', '방랑시인', '2022-06-08 13:41:21');
INSERT INTO post_replys(post_no, content, writer, reg_date) VALUES(3, '제 포스트의 내용이 유익하였다니 다행입니다.\r\n가끔 블로그에 들러 글 읽어 주시면 감사하겠습니다.\r\n항상 건강하고 행복하세요', 'FlowerPig', '2022-06-09 10:13:59');
INSERT INTO post_replys(post_no, content, writer, reg_date) VALUES(3, '감사합니다. 가끔 들러서 인사하겠습니다.', '방랑시인', '2022-06-10 17:33:16');

commit;
SELECT * FROM post_replys;



