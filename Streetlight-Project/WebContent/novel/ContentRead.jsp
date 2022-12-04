<%@page import="dao.PreferDAO"%>
<%@page import="dao.RecommendDAO"%>
<%@ include file="/default/header.jsp"%>
<%@page import="dao.NovelDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.NovelContentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<body>
	<%
	String idx = request.getParameter("idx");
	NovelContentDAO dao = new NovelContentDAO();
	NovelDAO ndao = new NovelDAO();
	PreferDAO pd = new PreferDAO();
	String novel_title = "";
	String title = "";
	String content = "";
	int next_idx = 0;
	int past_idx = 0;
	int novel_id = 0;
	String user = session.getAttribute("loginOK").toString();
	novel_id = ndao.getNovelId(idx);
	novel_title = ndao.getNovelTitle(Integer.toString(novel_id));
	title = dao.getNovelTitle(idx);
	content = dao.getNovelContent(idx).replace("\r\n","<br>");
	next_idx = dao.getNextNovelContentIdx(idx, Integer.toString(novel_id));
	past_idx = dao.getPastNovelContentIdx(idx, Integer.toString(novel_id));
	
	%>

	<div class="card">
		<div class="card-header">
			<h3><%=novel_title%></h3>
		</div>
		<div class="card-body">

			<h5 class="card-title"><%=title%></h5>
			<p style="margin-top: 20px;" class="card-text"><%=content%></p>

		</div>

	</div>

	<div class="w-50 align-items-center justify-content-center flex-wrap"
		style="margin-left: 35%; margin-top: 20px;">
		<%
			if(past_idx == 0){
				out.print("<button type='button' class='btn btn-primary' style='width: 75px;' disabled>이전화</button>");
			}else{
				out.print("<a href='/novel/ContentRead.jsp?idx=");
				out.print(past_idx);
				out.print("' a><button type='button' class='btn btn-primary' style='width: 75px;'>이전화</button></a> ");
			}
		%>
		<a href = "/novel/novelPopularAdd.jsp?id=<%=idx%>"><button type="button" class="btn btn-primary" style="width: 75px;">추천</button></a>
			
		<%
			if(pd.checkPrefer(user, Integer.toString(novel_id))){
				out.print("<a href='/novel/novelPreferAdd.jsp?id=");
				out.print(novel_id);
				out.print("'><button type='button' class='btn btn-secondary' style='width: 75px;'>선호작</button></a>");
			}else{
				out.print("<a href='/novel/novelPreferAdd.jsp?id=");
				out.print(novel_id);
				out.print("'><button type='button' class='btn btn-primary' style='width: 75px;'>선호작</button></a>");
			}
		%>
		<%
			if(next_idx == 0){
				out.print("<button type='button' class='btn btn-primary' style='width: 75px;' disabled>다음화</button>");
			}else{
				out.print("<a href='/novel/ContentRead.jsp?idx=");
				out.print(next_idx);
				out.print("' a><button type='button' class='btn btn-primary' style='width: 75px;'>다음화</button></a> ");
			}
		
		%>
		
		<a href="/novel/contentList.jsp?id=<%=novel_id%>"><button
				type="button" class="btn btn-primary" style="width: 75px;">
				목록</button> </a>
	</div>


	<%@ include file="/default/footer.jsp"%>