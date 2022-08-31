<%@page import="dao.RecommendDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.NovelDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/default/header.jsp"%>

<div id="carouselExampleIndicators" class="carousel slide"
	data-bs-ride="true" data-ride="carousel">
	<div class="carousel-indicators">
		<button type="button" data-bs-target="#carouselExampleIndicators"
			data-bs-slide-to="0" class="active" aria-current="true"
			aria-label="Slide 1"></button>
		<button type="button" data-bs-target="#carouselExampleIndicators"
			data-bs-slide-to="1" aria-label="Slide 2"></button>
		<button type="button" data-bs-target="#carouselExampleIndicators"
			data-bs-slide-to="2" aria-label="Slide 3"></button>
	</div>
	<div class="carousel-inner">
		<div class="carousel-item active" data-ride="carousel">
			<svg class="bd-placeholder-img bd-placeholder-img-lg d-block w-100"
				width="800" height="400" xmlns="http://www.w3.org/2000/svg"
				role="img" aria-label="Placeholder: First slide"
				preserveAspectRatio="xMidYMid slice" focusable="false">
<title>Placeholder</title>
<rect width="100%" height="100%" fill="#777"></rect>
<text x="50%" y="50%" fill="#555" dy=".3em">First slide</text>
							</svg>

		</div>
		<div class="carousel-item" data-ride="carousel">
			<svg class="bd-placeholder-img bd-placeholder-img-lg d-block w-100"
				width="800" height="400" xmlns="http://www.w3.org/2000/svg"
				role="img" aria-label="Placeholder: Second slide"
				preserveAspectRatio="xMidYMid slice" focusable="false">
<title>Placeholder</title>
<rect width="100%" height="100%" fill="#666"></rect>
<text x="50%" y="50%" fill="#444" dy=".3em">Second slide</text>
							</svg>

		</div>
		<div class="carousel-item" data-ride="carousel">
			<svg class="bd-placeholder-img bd-placeholder-img-lg d-block w-100"
				width="800" height="400" xmlns="http://www.w3.org/2000/svg"
				role="img" aria-label="Placeholder: Third slide"
				preserveAspectRatio="xMidYMid slice" focusable="false">
<title>Placeholder</title>
<rect width="100%" height="100%" fill="#555"></rect>
<text x="50%" y="50%" fill="#333" dy=".3em">Third slide</text>
							</svg>

		</div>
	</div>
	<button class="carousel-control-prev" type="button"
		data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
		<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="visually-hidden">Previous</span>
	</button>
	<button class="carousel-control-next" type="button"
		data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
		<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="visually-hidden">Next</span>
	</button>
</div>

<div class="recommend">
	<h4 style="margin-top: 30px;">최다 추천작 모음</h4>
	<% 
		NovelDAO dao=new NovelDAO(); 
		RecommendDAO rd = new RecommendDAO();
		ArrayList<String> recommend = new ArrayList<>();
		recommend = rd.getRecommendNovelId();
	%>
	<div class='row mb-3' style='margin-top: 20px;'>
		<% for (int i=0; i < recommend.size(); i++) { 
			out.print("<div class='col-4 themed-grid-col'>");
			out.print("<div class='card'>");
			out.print("<div class='card-body'>");
			out.print("<img style='width:400px;height:190px' src='/image/");
			out.print(dao.getNovelImage(recommend.get(i)));
			out.print("' class='img-thumbnail' alt='...'>");
			out.print("<h5 class='card-title'>");
			out.print(dao.getNovelTitle(recommend.get(i)));
			out.print("</h5>");
			out.print("<p class='card-text'><small class='text-muted'>");
			out.print(dao.getNovelIntro(recommend.get(i)));
			out.print("</small></p>");
			out.print("<p class='card-text'>작가 :");
			out.print(dao.getNovelWritter(recommend.get(i)));
			out.print("</p>");
			out.print("<a href='/novel/contentList.jsp?id=");
			out.print(recommend.get(i));
			out.print("' class='btn btn-primary'>읽으러 가기</a>");
			out.print("</div>");
			out.print("</div>");
			out.print("</div>");

			}
		%>
	</div>
</div>


<div class="popular">
	<h4 style="margin-top: 30px;">인기작 모음</h4>
	<% 
		ArrayList<String> popular = new ArrayList<>();
		popular = dao.getPopularNovelId();
	%>
	<div class='row mb-3' style='margin-top: 20px;'>
		<% for (int i=0; i < popular.size(); i++) { 
			out.print("<div class='col-4 themed-grid-col'>");
			out.print("<div class='card'>");
			out.print("<div class='card-body'>");
			out.print("<img style='width:400px;height:190px' src='/image/");
			out.print(dao.getNovelImage(popular.get(i)));
			out.print("' class='img-thumbnail' alt='...'>");
			out.print("<h5 class='card-title'>");
			out.print(dao.getNovelTitle(popular.get(i)));
			out.print("</h5>");
			out.print("<p class='card-text'><small class='text-muted'>");
			out.print(dao.getNovelIntro(popular.get(i)));
			out.print("</small></p>");
			out.print("<p class='card-text'>작가 :");
			out.print(dao.getNovelWritter(popular.get(i)));
			out.print("</p>");
			out.print("<a href='/novel/contentList.jsp?id=");
			out.print(popular.get(i));
			out.print("' class='btn btn-primary'>읽으러 가기</a>");
			out.print("</div>");
			out.print("</div>");
			out.print("</div>");

			}
		%>
	</div>
</div>
</div>

<%@ include file="/default/footer.jsp"%>