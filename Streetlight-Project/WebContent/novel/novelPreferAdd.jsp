<%@page import="dao.NovelContentDAO"%>
<%@page import="dao.PreferDAO"%>
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
String id = request.getParameter("id");

String user = session.getAttribute("loginOK").toString();
int room = 0;
try{
	room=Integer.parseInt(request.getParameter("room"));
}catch(Exception e){
	
}
NovelContentDAO ncd = new NovelContentDAO();

String idx = ncd.getNovelId(id);


PreferDAO pd = new PreferDAO();
out.print(room==1);
if(pd.checkPrefer(user, id)){
	pd.deletePrefer(user, id);
	if(room==1){
		out.print("<script>alert('선호작이 해제되었습니다.');</script>");
		response.sendRedirect("/novel/novelPreferRoom.jsp");
	}else{
		out.print("<script>alert('선호작이 해제되었습니다.');location.href = '/novel/ContentRead.jsp?idx=" + idx + "';</script>");
	}
}else{
	pd.addPrefer(user, id);
	if(room==1){
		out.print("<script>alert('선호작이 완료되었습니다.');</script>");
		response.sendRedirect("/novel/novelPreferRoom.jsp");
	}else{
		out.print("<script>alert('선호작이 완료되었습니다.');location.href = '/novel/ContentRead.jsp?idx=" + idx + "';</script>");
	}
}


%>
</body>
</html>