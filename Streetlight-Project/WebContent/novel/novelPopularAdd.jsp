<%@page import="dao.RecommendDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String idx = request.getParameter("id");

String user = session.getAttribute("loginOK").toString();


RecommendDAO rd = new RecommendDAO();

if(rd.checkRecommend(user, idx)){
	out.print("<script>alert('추천은 한번만 하실 수 있습니다.');history.back();</script>");
	
}else{
	rd.addRecommend(user, idx);
	out.print("<script>alert('추천이 완료되었습니다.');history.back();</script>");
}


%>
</body>
</html>