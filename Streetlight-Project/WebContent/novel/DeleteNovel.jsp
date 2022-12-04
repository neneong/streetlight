<%@page import="dao.NovelDAO"%>
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

String NovelId;
NovelDAO dao = new NovelDAO();

int n = 0;

NovelId = request.getParameter("id");



n = dao.deleteNovel(Integer.parseInt(NovelId));

if(n>0) {
	out.print("<script>alert('소설 삭제가 완료되었습니다.'</script>");
    response.sendRedirect("/write/novelWriteRoom.jsp");
}
else
    out.print("<script>alert('입력 양식에 오류가 있습니다.');history.back();</script>");

%>
</body>
</html>