<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.film.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	FilmService filmSvc = new FilmService();
    List<FilmVO> list = filmSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有FILM</title>
</head>
<body>

<table border='1' cellpadding='5' cellspacing='0' width='1500'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有會員資料 - listAllMember.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/member/select_page.jsp">
		<img src="<%=request.getContextPath()%>/front-end/member/images/back.png" width="70" height="70" border="0">回首頁</a>
		</td>
	</tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<table border='1' bordercolor='#CCCCFF' width='1500'>
	<tr>
		<th>會員編號</th>
		<th>影片</th>
	</tr>

	<c:forEach var="filmVO" items="${list}">


		<tr align='center' valign='middle'>
			<td>${filmVO.recipe_no}</td>
			<td>
				<video id="chef_movie2" controls>
                    <source src="/AA105G3/FilmDBGifReader.do?name=${filmVO.recipe_no}">
                </video>
			</td>
		</tr>
	</c:forEach>
</table>


</body>
</html>