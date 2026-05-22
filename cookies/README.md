# 쿠키 레시피 프로젝트

## MySQL DB 쿼리
```
CREATE DATABASE cookies DEFAULT CHARACTER SET utf8mb4;
CREATE USER 'cookies_user'@'%' IDENTIFIED BY 'cookies9876!';
GRANT ALL PRIVILEGES ON cookies.* TO 'cookies_user'@'%';
FLUSH PRIVILEGES;
```

### 물리테이블 설계서
```
category (재료 분류 마스터 테이블)
이름			데이터형 길이	null		제약조건					comment
id			bigint		not null	auto_increment	pkey	분류 기본키
name		varchar	15	not null	unique key				분류 이름


ingredient (과자 재료 마스터 테이블)
이름			데이터형 길이	null		제약조건					comment
id			bigint		not null	auto_increment	pkey	재료 기본키
name		varchar	30	not null	unique key				재료 이름
category_id	bigint		not null	foreign key (category.id)	분류 외래키


cookie (과자 마스터 테이블)
이름			데이터형 길이	null		제약조건	
id			bigint		not null	auto_increment	pkey	과자 기본키
name		varchar	30	not null	unique key				과자 이름


recipe (레시피 정보 테이블)
id			bigint		not null	auto_increment	pkey	레시피 기본키
cookie_id	bigint		not null	foreign key (cookie.id)	과자 외래키
ingre_id	bigint		not null	foreign key (ingredient.id)	재료 외래키
unit		varchar(5)	not null							단위(g/ml/ea)
weight		numeric(5,2)not null							중량/용량/개수


cooking (과자 만드는 설명 테이블)
id			bigint		not null	auto_increment	pkey	설명 기본키
description	text											설명을 저장한다.
cookie_id	bigint		not null	foreign key (cookie.id)	과자 외래키


attach (과자 베이킹의 첨부파일 테이블)
id			bigint		not null	auto_increment	pkey	파일 기본키
file_name	varchar(200)not null							파일명
size		int			not null							파일사이즈
ext			varchar(10)	not null							파일확장명
store_name	varchar(500)not null							저장된파일명
path		varchar(100)not null							폴더,구분명
cookie_id	bigint		not null	foreign key (cookie.id)	과자 외래키
```

- 목록1
- 목록