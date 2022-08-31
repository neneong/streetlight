<%@page import="dao.NovelContentDAO"%>
<%@page import="java.io.PrintWriter"%>
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
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	
    String ContentId;
    NovelContentDAO dao = new NovelContentDAO();
    
    int n = 0;

    ContentId = request.getParameter("id");

    
    
    n = dao.deleteNovelContent(Integer.parseInt(ContentId));

    if(n>0) {
    	out.print("<script>alert('회차 삭제가 완료되었습니다.'</script>");
        response.sendRedirect("/write/novelWriteRoom.jsp");
    }
    else
        out.print("<script>alert('오류 발생');history.back();</script>");
	%>
</body>
</html>