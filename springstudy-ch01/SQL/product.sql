-- MySQL

CREATE database spring;

use spring;
DROP TABLE IF EXISTS product;
CREATE TABLE IF NOT EXISTS product(
	code VARCHAR(15) PRIMARY KEY,
	name VARCHAR(15) NOT NULL,
	price INT NOT NULL,
	description TEXT(1000) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




-- Oracle
DROP TABLE product;
CREATE TABLE product(
	code VARCHAR2(15 CHAR) PRIMARY KEY,
	name VARCHAR2(15 CHAR) NOT NULL,
	price NUMBER NOT NULL,
	description VARCHAR2(500) NOT NULL
);






INSERT INTO product VALUES('LG-F1352NCZ', '트롬세탁기', 658900, '13kg, 6가지 코스, 두드리기/주무르기/비비기/풀어주기/꼭꼭짜기/흔들기세탁, 통세척');
INSERT INTO product VALUES('SAM-H5632SCM', '하우젠세탁기', 649800, '14kg, 건조8kg, 파워버블샷/스포츠버블/베이비버블/원스탑버블/나이트버블/에어살균/에어탈취');
INSERT INTO product VALUES('LG-T1207W7', '통돌이세탁기', 598000, '12Kg, 8가지코스, 다이아몬드글라스도어, 표준/울/섬세/급속/이불/조용조용/속옷/내마음세척, 통세척');
INSERT INTO product VALUES('LG-F13D9NK', '꼬망스세탁기', 489800, '12Kg, 8가지코스, 다이아몬드글라스도어, 표준/울/섬세/급속/이불/조용조용/속옷/내마음세척, 통세척');
INSERT INTO product VALUES('DWD-900WNB', '클라쎄세탁기', 529800, '9kg, 빌트인, 9가지세탁코스, ABS도어/디지털제어모터/삶는세탁/나노실버/애벌세탁/구김방지/예약기능/화이트');
commit;

SELECT * FROM product;