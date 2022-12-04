<%@page import="dao.TagDAO"%>
<%@page import="java.util.ArrayList"%>
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
	NovelTagDAO ntd = new NovelTagDAO();
	TagDAO td = new TagDAO();
	String writter = dao.getNovelWritter(novelIdx);
	String title = dao.getNovelTitle(novelIdx);
	String intro = dao.getNovelIntro(novelIdx);
	ArrayList<String> tagIdxs = new ArrayList<>();
	ArrayList<String> tagName = new ArrayList<>();
	tagIdxs = ntd.getNovelTag(novelIdx);
	
	for (String tags : tagIdxs) {
		tagName.add(td.getTagName(tags));
	}
	String str = String.join(",", tagName);
	
	
	if(!userId.equals(writter)){
		out.print("<script>alert('소설 수정은 작가만 가능합니다.');history.back();</script>");
	}
%>

<body>
	<form action="/EditNovel" method="post" enctype="multipart/form-data">
		<div class="form-group">
	        사진 : 
	        <input type="file" name="image">
	    </div>
		<input type="hidden" name = "novelId" value = "<%= novelIdx %>">
		<div class="mb-3">
			<label for="novelTitle" class="form-label">소설 제목</label> <input
				type="text" name="title" class="form-control" id="novelTitle"
				placeholder="제목을 입력해 주세요" value="<%=title%>">
		</div>
		<div class="mb-3">
			<label for="novelIntro" class="form-label">소설 소개</label> <input
				type="text" name="intro" class="form-control" id="novelIntro"
				placeholder="소설 소개를 입력해 주세요" value="<%= intro %>">
		</div>
		<div class="mb-3">
			<label for="novelTag" class="form-label">소설 태그</label> <input
				name="tag" type="text" class="form-control" id="novelTag"
				placeholder="쉼표로 구분해주세요 (예시 : SF,판타지)" value="<%= str %>">
		</div>
		<button type="submit">작성하기</button>
	</form>
<%@ include file="/default/footer.jsp"%>