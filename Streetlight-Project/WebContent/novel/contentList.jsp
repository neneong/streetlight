<%@page import="dao.RecommendDAO"%>
<%@page import="dao.TagDAO"%>
<%@page import="dao.NovelTagDAO"%>
<%@page import="dao.NovelDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.NovelContentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/default/header.jsp"%>

<body>
	<%
		String user_id = session.getAttribute("loginOK").toString();
		String id = request.getParameter("id");
		NovelContentDAO dao = new NovelContentDAO();
		NovelDAO nd = new NovelDAO();
		NovelTagDAO ntd = new NovelTagDAO();
		TagDAO td = new TagDAO();
		RecommendDAO rd = new RecommendDAO();
		int recommend = 0;
		recommend = rd.getRecommendCount(id);
		String content = "";
		ArrayList<String> title = new ArrayList<>();
		ArrayList<String> idx = new ArrayList<>();
		String writter = nd.getNovelWritter(id);
		String novelImage = nd.getNovelImage(id);
		String novelTitle = nd.getNovelTitle(id);
		String novelIntro = nd.getNovelIntro(id);
		title = dao.getNovelContentNameList(id);
		idx = dao.getNovelContentIdxList(id);
		ArrayList<String> tagIdxs = new ArrayList<>();
		ArrayList<String> tagName = new ArrayList<>();
		tagIdxs = ntd.getNovelTag(id);
		
		for (String tags : tagIdxs) {
			tagName.add(td.getTagName(tags));
		}
		String str = String.join(",", tagName);
		
		
	%>
	<div class="row g-0" style="margin-top: 30px;">
		<div class="col-md-1">
			<img src = "/image/<%= novelImage %>" width="100" height="150">
		</div>
		<div style="margin-left: 3%;" class="col-md-10">
			<div class="card-body">
				<h5 class="card-title"><%= novelTitle %></h5>
				<p class="card-text"><%= novelIntro %></p>
				<p class="card-text">
					<small class="text-muted"><%= writter %></small>
				</p>
				<p class="card-text">
					<small class="text-muted">태그 : <%= str %>&nbsp;&nbsp;&nbsp;추천수 : <%= recommend %></small>
				</p>
				<%
					if(user_id.equals(writter)){
						out.print("<p class='card-text'>");
						out.print("<a href='/write/novelContentWrite.jsp?id=");
						out.print(id.toString());
						out.print("'>");
						out.print("<button class = 'btn btn-primary'>회차쓰기</button>");
						out.print("</a>");
						out.print("</p>");
					}
				%>
			</div>
		</div>
	</div>
	
	<ul class="list-group" style="margin-top: 30px;">
		<%
			
			if(title.size()==0){
				out.print("<li class='list-group-item'>");
				out.print("작품 내용이 없습니다.");
				out.print("</li>");
			}
			if(user_id.equals(writter)){
				for (int i=0; i<title.size(); i++) {
					out.print("<a href = /novel/ContentRead.jsp?idx=");
					out.print(idx.get(i));
					out.print(">");
					out.print("<li class='list-group-item'>");
					out.print((i+1)+"화	"+title.get(i));
					out.print("<a style='float:right;margin-right:20px;' href='/novel/novelContentEdit.jsp?id=");
					out.print(idx.get(i));
					out.print("'>");
					out.print("<button class = 'btn btn-primary btn-sm'>수정</button>");
					out.print("</a>");
					out.print("<a style='float:right;margin-left:10px;' href='/novel/DeleteNovelContent.jsp?id=");
					out.print(idx.get(i));
					out.print("'>");
					out.print("<button class = 'btn btn-primary btn-sm'>삭제</button>");
					out.print("</a>");
					out.print("</li>");
					
					out.print("</a>");
				}
			}else{
				for (int i=0; i<title.size(); i++) {
					out.print("<a href = /novel/ContentRead.jsp?idx=");
					out.print(idx.get(i));
					out.print(">");
					out.print("<li class='list-group-item'>");
					out.print((i+1)+"화	"+title.get(i));
					out.print("</li>");
					out.print("</a>");
				}
			}
			
		%>
	</ul>
	<%@ include file="/default/footer.jsp"%>