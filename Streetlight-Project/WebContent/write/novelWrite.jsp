<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/default/header.jsp"%>
<!-- 소설 작성하기 -->

<%
	String userId = session.getAttribute("loginOK").toString();
%>

<body>
	<form action="/AddNovel" method="post">
		<input type="hidden" name = "userId" value = "<%= userId %>">
		<div class="mb-3">
			<label for="novelTitle" class="form-label">소설 제목</label> <input
				type="text" name="title" class="form-control" id="novelTitle"
				placeholder="제목을 입력해 주세요">
		</div>
		<div class="mb-3">
			<label for="novelIntro" class="form-label">소설 소개</label> <input
				type="text" name="intro" class="form-control" id="novelIntro"
				placeholder="소설 소개를 입력해 주세요">
		</div>
		<div class="mb-3">
			<label for="novelTag" class="form-label">소설 태그</label> <input
				name="tag" type="text" class="form-control" id="novelTag"
				placeholder="쉼표로 구분해주세요 (예시 : SF,판타지)">
		</div>
		<button type="submit">작성하기</button>
	</form>
	<%@ include file="/default/footer.jsp"%>