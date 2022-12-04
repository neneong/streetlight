<%@page import="java.util.ArrayList"%>
<%@page import="dao.NovelContentDAO"%>
<%@page import="dao.TagDAO"%>
<%@page import="dao.NovelTagDAO"%>
<%@page import="dao.NovelDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/default/header.jsp"%>
<!-- 소설 수정하기 -->

<%
	String userId = session.getAttribute("loginOK").toString();
	String novelIdx = request.getParameter("id");
	NovelDAO dao = new NovelDAO();
	NovelContentDAO ncd = new NovelContentDAO();
	NovelTagDAO ntd = new NovelTagDAO();
	TagDAO td = new TagDAO();
	String novelId = ncd.getNovelId(novelIdx);
	String writter = dao.getNovelWritter(novelId);
	String title = ncd.getNovelTitle(novelIdx);
	String content = ncd.getNovelContent(novelIdx).replace("<br>","\r\n");

	
	if(!userId.equals(writter)){
		out.print("<script>alert('소설 수정은 작가만 가능합니다.');history.back();</script>");
	}
%>

<body>
	<form action="/EditNovelContent" method="post">
		<input type="hidden" name = "novelId" value = "<%= novelIdx %>">
		<div class="mb-3">
			<label for="novelTitle" class="form-label">회차 제목</label> <input
				type="text" name="title" class="form-control" id="novelTitle"
				placeholder="회차 제목을 입력해 주세요" value = "<%= title %>">
		</div>
		<div class="mb-3">
			<label for="novelContent" class="form-label">소설 내용</label>
		<textarea class="form-control" name = "content" id="novelContent" rows="10"><%= content %></textarea>
		</div>
		<button type="submit">작성하기</button>
	</form>
<%@ include file="/default/footer.jsp"%>