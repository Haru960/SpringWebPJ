<%@page import="com.javaEdu.pj.dao.QADao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%
	String id = null; 
	String searchType;
	String search;
	String PN;
	if(request.getParameter("PN") != null){
		PN = request.getParameter("PN");
	}else{
		PN = "1";
	}
	if(request.getParameter("searchType") != null){
		searchType = request.getParameter("searchType");
	}else{
		searchType = "title";
	}
	if(request.getParameter("search") != null){
		search = request.getParameter("search");
	}else{
		search = "";
	}
	try{
		Integer.parseInt(PN);
	}catch(Exception e){
		session.setAttribute("messageType", "오류");
		session.setAttribute("messageContent", "페이지 번호가 잘못되었습니다.");
		response.sendRedirect("list"); 
	}
%>
<head>
<meta charset="UTF-8">
<!-- css 파일 -->
<link href="${pageContext.request.contextPath}/resources/css/css_QAlist.css?v=3"
	rel="stylesheet" type="text/css">

<!-- bootstrap 추후 바꿔야 함-->
<meta name="viewport" content="width=dievice-width" initial-scale="1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">


<title>Main list</title>
</head>
<body>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/myPage.js"></script>
	
	
	<!-- bootstrap 추후 바꿔야 함--> 
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<!-- bootstrap 맨위 홈버튼-->
			<div class="navbar-brand-div">
				<a class="navbar-brand" href="Main">
					<img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="" width="160px" height="100%">
				</a>
			</div>
			
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<!-- bootstrap 홈버튼 오른쪽 기타 side menu-->
			<ul class="nav navbar-nav">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"> 커뮤니티 <span class="caret"></span></a>
					<ul class="dropdown-menu">
					<!-- li class="active" 해당 위치일 경우 액티브로 활성화 시켜야한다 -->
						<li><a href="list">자료실</a></li>
						<li><a href="QAlist">Q/A</a></li>
					</ul>
				</li>
			</ul>
			<!-- bootstrap 최상단 오른쪽 드롭박스 메뉴-->
			<ul class="nav navbar-nav navbar-right">
				<%
					if (session.getAttribute("ValidMem") != null) {
						id = (String)session.getAttribute("id");
				%>
				<li><a href="logout">로그아웃</a></li>
				<li><a href="user_modify">마이 페이지</a></li>
				<%
					} else {
				%>

				<li><a href="login">로그인</a></li>
				<li><a href="join">회원가입</a></li>
				<%
					}
				%>

				
			</ul>
		</div>
	</nav>

	
	<div id="sub_visual">
		<div>
			<h2><b>커뮤니티</b></h2>
		</div>
	</div>
	
	<div class="grid_container">


		<div class="left_side">
			<input type="radio" id="list1" name="show" onclick="list1_click()"  /> <br/>
 			<input type="radio" id="list2" name="show"  onclick="list2_click()" checked="checked"/><br/>
 			<div class="tab">
			    <label for="list1"> 자료실 </label>
			    <label for="list2"> Q/A </label> 
			</div>	
		</div>

		<div class="search">
			<form class="table-from" action="QAlist?PN=<%= 1 %>" method="get">
			
				<select name="searchType">
					<option value="title" <% if(searchType.equals("title")) out.print("selected"); %>> 제목 </option>
					<option value="writerId" <% if(searchType.equals("writerId")) out.print("selected"); %>> 작성자 </option>
				</select>
				<input class="data_input" type = "text" name="search" size = "20" value="<%= search %>">
				<input class="btn btn-search" type="submit" value="검색"/>
				
			</form>
		</div>

		<div class="right_side">
			<div class="board-div">
				<table class="board">
					<tr id="tr_first">
						<td width="8%">번호</td>
						<td width="50%" class="border_td">제목</td>
						<td width="15%" class="border_td">작성자</td>
						<td width="10%" class="border_td">날짜</td>
						<td width="8%">조회</td>
					</tr>
					<c:forEach items="${list}" var="dto">
						<tr class="trs">
							<td id="td_num">${dto.qId}</td>
							<td class="border_td" id="td_title">
								<div class="title_bd">
									<div>
										<a href="qa_content_view?qId=${dto.qId}">
											${dto.qTitle}
										</a>
									</div>
									
									<div class="answer">
										<c:if test="${dto.aId != 0}">
											<strong id="yes">답변 완료 </strong>
										</c:if>
										<c:if test="${dto.aId == 0}">
											<strong id="no">미 답변 </strong>
										</c:if>     
									</div>
								</div>
							</td>
							<td class="border_td" id="td_uName">${dto.id}</td>
							<td class="border_td" id="td_date">${ (dto.qDate).toString().substring(2, 10) }</td>
							<td  id="td_hit">${dto.qHit}</td>
						</tr>
					</c:forEach>
					<tr id="tr_final">
						<td colspan="5"><a href="qa_write_view">글작성</a></td>
					</tr>
				</table>
				</div>
			<div class="paging">
				<ul class="pagination">
					<% 
						String firstPage = "1";
						int startPage = (Integer.parseInt(PN) / 10) * 10 + 1;
						if(Integer.parseInt(PN) % 10 == 0) startPage -= 10;
						int targetPage = new QADao().targetPage(PN, search, searchType, id);
						
					%>
					
						<li><a href="QAlist?PN=<%= firstPage %>&search=<%=search%>&searchType=<%=searchType%>" class="bt"> &lt;&lt;  </a> </li>
						
					<%
						if(Integer.parseInt(PN) != 1) {
					%>
					
						<li><a href="QAlist?PN=<%= Integer.parseInt(PN) - 1 %>&search=<%=search%>&searchType=<%=searchType%>" class="bt"> &lt;  </a> </li>
						
					<%
						} else {
					%>
					
						<li><a href="QAlist?PN=<%= Integer.parseInt(PN) %>&search=<%=search%>&searchType=<%=searchType%>" class="bt"> &lt;  </a> </li>
						
					<%
						}
					%>
					
					&nbsp;
					
					<%
						for(int i = startPage; i < Integer.parseInt(PN); i++){
					%>
						<li><a href="QAlist?PN=<%= i %>&search=<%=search%>&searchType=<%=searchType%>" class="bt"><%= i %> </a> </li>
					<%
						}
					%>
						<li class="active"><a href="QAlist?PN=<%= PN %>&search=<%=search%>&searchType=<%=searchType%>" class="bt"><%= PN %> </a> </li>
					<%
						for(int i = Integer.parseInt(PN) + 1; i <= targetPage; i++){
							if(i < startPage + 10) {
							
					%>
							<li><a href="QAlist?PN=<%= i %>&search=<%=search%>&searchType=<%=searchType%>" class="bt"><%= i %> </a> </li>
					<%
							}
						}
					%>
						&nbsp;
					<li><a href="QAlist?PN=<%=Integer.parseInt(PN) + 1 %>&search=<%=search%>&searchType=<%=searchType%>" class="bt"> &gt; </a> </li>
					<% 
					if(targetPage + Integer.parseInt(PN) > startPage + 9){
					%>
					<li><a href="QAlist?PN=<%= startPage+10 %>&search=<%=search%>&searchType=<%=searchType%>" class="bt"> &gt;&gt; </a> </li>
					<%
					} else {
					%>
					<li><a href="QAlist?PN=<%=PN%>&search=<%=search%>&searchType=<%=searchType%>" class="bt"> &gt;&gt; </a> </li>
					<%
					}
					%>
				</ul>
			</div>
		</div>
	</div>
	<div class="under_side">
		<div class="al_wrap">
			<div>
				<img src="${pageContext.request.contextPath}/resources/img/under_logo.png"></img>
			</div>
			<div>
				<address>  충청남도 홍성군 홍북면 충남대로 </address>
				<address><span>Help desk 070.1234.5678, 070.1586.3578</span></address>
				<p class="copy"> Copyright (C) 충청남도 로컬푸드 학교급식 지원센터. All rights reserved </p>
			</div>
			
		</div>
	</div>

</body>
</html>