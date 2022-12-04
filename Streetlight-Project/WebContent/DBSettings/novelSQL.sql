DROP TABLE novel CASCADE CONSTRAINTS;
DROP TABLE novelContent CASCADE CONSTRAINTS;
DROP table member CASCADE CONSTRAINTS;
DROP TABLE novel_tag;
DROP TABLE tag;
DROP TABLE prefer;
DROP TABLE recommend;

CREATE TABLE member(
	user_id varchar2(10),
	user_pwd varchar2(20) NOT NULL,
	nick_name varchar2(10) NOT NULL,
	user_image varchar2(255) NOT NULL,
	birthday varchar2(8),
	CONSTRAINT member_pk PRIMARY KEY (user_id)
);

CREATE TABLE novel(
	id INT NOT NULL,
	image varchar(255) NOT NULL,
	writter varchar(20) REFERENCES member(user_id) NOT NULL,
	novel_name varchar(100) NOT NULL,
	novel_intro varchar(255) NOT NULL,
	popular INT NOT NULL,
	prefer_count INT NOT NULL,
	read_count INT NOT NULL,
	CONSTRAINT novel_id PRIMARY KEY(id)
);

CREATE TABLE novelContent(
	idx INT NOT NULL,
	novel_idx INT NOT NULL,
	id INT REFERENCES novel(id) ON DELETE CASCADE,
	content_name varchar(255) NOT NULL,
	novel_content clob NOT NULL,
	update_date date NOT NULL,
	CONSTRAINT content_id PRIMARY KEY(idx)
);

CREATE TABLE tag(
	id INT NOT NULL,
	name varchar(255)NOT NULL,
	CONSTRAINT tag_id PRIMARY KEY(id)
);


CREATE TABLE novel_tag(
	id INT REFERENCES novel(id) ON DELETE CASCADE,
	tag_id INT REFERENCES tag(id)
	
);

CREATE TABLE prefer(
	id varchar2(50) REFERENCES member(user_id) ON DELETE CASCADE,
	prefer_id INT REFERENCES novel(id)
	
);

CREATE TABLE recommend(
	id varchar2(50) REFERENCES member(user_id) ON DELETE CASCADE,
	recommend_id INT REFERENCES novel(id)
	
);


INSERT INTO MEMBER values('1234','1234','nick','default.png','20050701');
INSERT INTO novel values ('1','default.png','1234', 'aaaaa', 'bbbbbbbb',10,1000, 2000);
INSERT INTO novel values ('2','default.png','1234', 'aaaaa', 'bbbbbbbb',10,1000, 2000);
INSERT INTO NOVELCONTENT VALUES(1,1,1,'alpha','성자신손 오백 년은 우리 황실이요
산수 고려 동반도는 우리 본국일세
무궁화 삼천리 화려강산
조선 사람 조선으로 길이 보존하세

이천만 오직 한 마음 나라 사랑하여
사농공상 상하 없이 제 직분 다하세
무궁화 삼천리 화려강산
조선 사람 조선으로 길이 보존하세
성자신손 오백 년은 우리 황실이요
산수 고려 동반도는 우리 본국일세
무궁화 삼천리 화려강산
조선 사람 조선으로 길이 보존하세

이천만 오직 한 마음 나라 사랑하여
사농공상 상하 없이 제 직분 다하세
무궁화 삼천리 화려강산
조선 사람 조선으로 길이 보존하세
성자신손 오백 년은 우리 황실이요
산수 고려 동반도는 우리 본국일세
무궁화 삼천리 화려강산
조선 사람 조선으로 길이 보존하세

이천만 오직 한 마음 나라 사랑하여
사농공상 상하 없이 제 직분 다하세
무궁화 삼천리 화려강산
조선 사람 조선으로 길이 보존하세',(SELECT SYSDATE FROM DUAL));
INSERT INTO NOVELCONTENT VALUES(2,2,1,'alpha','성자신손 오백 년은 우리 황실이요
산수 고려 동반도는 우리 본국일세
무궁화 삼천리 화려강산
조선 사람 조선으로 길이 보존하세

이천만 오직 한 마음 나라 사랑하여
사농공상 상하 없이 제 직분 다하세
무궁화 삼천리 화려강산
조선 사람 조선으로 길이 보존하세
성자신손 오백 년은 우리 황실이요
산수 고려 동반도는 우리 본국일세
무궁화 삼천리 화려강산
조선 사람 조선으로 길이 보존하세

이천만 오직 한 마음 나라 사랑하여
사농공상 상하 없이 제 직분 다하세
무궁화 삼천리 화려강산
조선 사람 조선으로 길이 보존하세
성자신손 오백 년은 우리 황실이요
산수 고려 동반도는 우리 본국일세
무궁화 삼천리 화려강산
조선 사람 조선으로 길이 보존하세

이천만 오직 한 마음 나라 사랑하여
사농공상 상하 없이 제 직분 다하세
무궁화 삼천리 화려강산
조선 사람 조선으로 길이 보존하세',(SELECT SYSDATE FROM DUAL));
INSERT INTO tag (id, name) VALUES ('1', '순애');
INSERT INTO tag (id, name) VALUES ('2', '얀데레');
INSERT INTO novel_tag values ('1','1');
INSERT INTO novel_tag values ('1', '2');
INSERT INTO recommend values('1234',2);


select * from member;
select * from novel;
select * from novelcontent;
select * from tag;
select * from NOVEL_TAG;
select * from recommend;



