<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader("Expires",0);
if (request.getProtocol().equals("HTTP/1.1"))
	response.setHeader("Cache-Control", "no-cache");
%> 
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">

<% String image_dir=request.getSession().getServletContext().getRealPath("./") + java.io.File.separator
				+ "customCss.jsp" ; %>


</head>

<body>
	<% session=request.getSession(); String data=null;
				if(session.getAttribute("loginOK")!=null){data=session.getAttribute("loginOK").toString();}%>
	<% MemberDAO dao2=new MemberDAO(); String image=dao2.getUserProfileImage(data); %>
	<div class="container">
		<header class="p-3 mb-3 border-bottom">
			<div class="container">
				<div
					class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
					<a href="/"
						class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
						<h2>LNovel</h2>
					</a>

					<ul
						class="nav col-16 col-md-auto me-md-auto mb-2 justify-content-right mb-md-0">
						<li><a href="#" class="nav-link px-6 link-dark">추천작</a></li>
						<li><a href="#" class="nav-link px-5 link-dark">랭킹</a></li>
						<li><a href="/novel/novelPreferRoom.jsp" class="nav-link px-5 link-dark">선호작</a></li>
					</ul>

					<a href='/search/hashTag.jsp' style="margin-right:30px;"><button class = "btn btn-primary" >검색하기</button></a>

					<div class="dropdown text-end">
						<a href="#"
							class="d-block link-dark text-decoration-none dropdown-toggle"
							id="dropdownUser1" data-bs-toggle="dropdown"
							aria-expanded="false"> <img src="/profile/<%= image %>"
							alt="mdo" width="32" height="32" class="rounded-circle">
						</a>
						<ul class="dropdown-menu text-small"
							aria-labelledby="dropdownUser1">

							<%if(data==null){
								out.print("<li><a class='dropdown-item' href='/login/login.jsp'>로그인</a></li><li><a class='dropdown-item' href='/sign/signup.jsp'>회원가입</a></li>");
							%>
							<%}else{out.print("<li><a class='dropdown-item' href='#'>"+data+"</a></li<li><a class='dropdown-item' href='/novel/novelPreferRoom.jsp?room=1'>선호작 보기</a></li><li><a class='dropdown-item' href='/write/novelWrite.jsp'>신규 소설등록</a></li><a class='dropdown-item' href='/write/novelWriteRoom.jsp'>내 작품관리</a></li><li><hr class='dropdown-divider'></li><li><a class='dropdown-item' href='/login/logout.jsp'>Sign out</a></li>");} %>
						</ul>

					</div>
				</div>
			</div>
		</header>