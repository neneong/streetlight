/* 유저 */
CREATE TABLE user_tbl(
id VARCHAR2(10) not null, -- 아이디
name VARCHAR2(10) not null, -- 이름
mail VARCHAR2(30) not null, -- 이메일
password VARCHAR2(30) not null, -- 비밀번호
profile VARCHAR2(3000), -- 프로필
date_of_birth date not null, -- 생년월일 
CONSTRAINT pk_user_tbl PRIMARY KEY (id, mail) -- 기본키 여러개 해주는거
);

/* 장르 */
CREATE TABLE genre_tbl (
genre VARCHAR2(5) PRIMARY KEY -- 장르
);

/* 작품 */
CREATE TABLE work_tbl (
work_id VARCHAR2(10) not null, -- 작가_아이디
work_title VARCHAR2(20) PRIMARY KEY,
posting_date date, -- 게시일
genre VARCHAR2(5), -- 장르
work_content VARCHAR2(4000) not null -- 내용 _ 한글 : 2000자 / 영어 : 4000자
);

/* 댓글 */
CREATE TABLE comments_tbl (
id VARCHAR2(10) not null, -- 댓글 단 사람
pathways VARCHAR2(3000) not null, -- 경로
content VARCHAR2(300) not null -- 내용
);

/* 태그 */
CREATE TABLE tags_tbl (
tags VARCHAR2(5) PRIMARY KEY -- 태그
);

/* 이모티콘 */
CREATE TABLE emoticon (
emoticon_id VARCHAR2(10) not null,
emoticon_title VARCHAR2(7) PRIMARY KEY,
tags VARCHAR2(5)
);

/* 조회수 */
CREATE TABLE hits_tbl (
work_id VARCHAR2(10) not null, -- 작가
work_title VARCHAR2(20) PRIMARY KEY, -- 작품명
hits int -- 조회수
);

