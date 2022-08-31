<%@page import="dao.PreferDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.NovelDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/default/header.jsp"%>


<body>
	<% 
		
		PreferDAO pd = new PreferDAO();
		NovelDAO dao=new NovelDAO(); 
		ArrayList<String> novels = new ArrayList<>();
		String user = session.getAttribute("loginOK").toString();
		novels = pd.GetUserPrefer(user);
		
	%>
	<div class="novels">
		
	<div class='row mb-3' style='margin-top: 20px;'>
	<% for (int i=0; i < novels.size(); i++) { 
			out.print("<div class='col-4 themed-grid-col'>");
			out.print("<div class='card'>");
			out.print("<div class='card-body'>");
			out.print("<img src='/image/");
			out.print(dao.getNovelImage(novels.get(i)));
			out.print("' class='img-thumbnail' alt='...'>");
			out.print("<h5 class='card-title'>");
			out.print(dao.getNovelTitle(novels.get(i)));
			out.print("</h5>");
			out.print("<p class='card-text'>작가 : ");
			out.print(dao.getNovelWritter(novels.get(i)));
			out.print("</p>");
			out.print("<a href='/novel/contentList.jsp?id=");
			out.print(novels.get(i));
			out.print("' class='btn btn-primary'>보기</a>");
			out.print("<a style='margin-left:20px;' href='/novel/novelPreferAdd.jsp?id=");
			out.print(novels.get(i));
			out.print("&room=1' class='btn btn-primary'>선호작 해제하기</a>");
			out.print("</div>");
			out.print("</div>");
			out.print("</div>");
			}
		%>
	</div>	
	</div>
	<%@ include file="/default/footer.jsp"%>