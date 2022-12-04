<%@page import="dao.NovelTagDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.TagDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/default/header.jsp" %>
<%@page import="dao.NovelDAO"%>
	<%
		String tag = (String)request.getParameter("tag");
		NovelDAO nd = new NovelDAO();
		TagDAO td = new TagDAO();
		NovelTagDAO ntd = new NovelTagDAO();

		ArrayList<String> tags = nd.getNovelListByTag(tag);
		out.print(tag);
		ArrayList<String> tagName = new ArrayList<String>();
		for (String tag2 : tags) {
			tagName.add(td.getTagName(tag2));
		}
		String str = String.join(",", tagName);
		
		
		
	%>
	
	<div class="mb-3">	
      	<form action="/search/hashTag.jsp" class="input-group mb-3">
          <input type="text" name="tag" class="form-control" placeholder="검색할 글자를 입력해주세요">
          <button type="submit" id="basic-addon2" class="input-group-text btn btn-secondary ">검색</button>
        </form>
    </div>
    
        <nav class="nav nav-pills">
          <a class="nav-link" href="/search/keyword.jsp">제목</a>
          <a class="nav-link active" aria-current="page" href="/search/hashTag.jsp">태그</a>
          <a class="nav-link" href="/search/writer.jsp">작가</a>
        </nav>
        
        
        <%
        	for(String ta:tags){
        		out.print("<a href = '/novel/contentList.jsp?id=");
        		out.print(ta);
        		out.print("'>");
        		out.print("<div class='row g-0' style='margin-top: 30px;'>");
            	out.print("<div class='col-md-1'>");
            	out.print("<img src = '/image/");
            	out.print(nd.getNovelImage(ta));
            	out.print("' width='100' height='150'>");
            	out.print("</div>");
            	out.print("<div style='margin-left: 3%;' class='col-md-10'>");
            	out.print("<div class='card-body'>");
            	out.print("<h5 class='card-title'>제목 :");
            	out.print(nd.getNovelTitle(ta));
            	out.print("</h5>");
            	out.print("<p class='card-text'>소개 :");
            	out.print(nd.getNovelIntro(ta));
            	out.print("</p>");
            	out.print("<p class='card-text'>");
            	out.print("<small class='text-muted'>작가 : ");
            	out.print(nd.getNovelWritter(ta));
            	out.print("</small>");
            	out.print("</p>");
            	out.print("<p class='card-text'>");
            	out.print("<small class='text-muted'>태그 :");
            	ArrayList<String> tagIdxs = ntd.getNovelTag(ta);
            	ArrayList<String> tagNames = new ArrayList<String>();
            	for (String tagId : tagIdxs) {
        			tagNames.add(td.getTagName(tagId));
        		}
        		String str2 = String.join(",", tagNames);
            	out.print(String.join(",", str2));
            	out.print(" </small>");
            	out.print("</p>");
            	out.print("</div>");
            	out.print("</div>");
            	out.print("</div>");
            	out.print("</a>");
        	}
        	
        %>
		
			
		
		
			
				
				 
				
					
				
				
					
				
			
		
	
        
    
<%@ include file="/default/footer.jsp" %>