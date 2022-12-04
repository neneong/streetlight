<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/default/header.jsp"%>
<!-- 소설 작성하기 -->

<%
	String userId = session.getAttribute("loginOK").toString();
	String novelIdx = request.getParameter("id");
%>

<body>
	<form action="/AddNovelContent" method="post">
		<input type="hidden" name = "novelId" value = "<%= novelIdx %>">
		<div class="mb-3">
			<label for="novelTitle" class="form-label">회차 제목</label> <input
				type="text" name="title" class="form-control" id="novelTitle"
				placeholder="회차 제목을 입력해 주세요">
		</div>
		<div class="mb-3">
			<label for="novelContent" class="form-label">소설 내용</label>
		<textarea class="form-control" name = "content" id="novelContent" rows="10"></textarea>
		</div>
		<button type="submit">작성하기</button>
	</form>
<%@ include file="/default/footer.jsp"%>