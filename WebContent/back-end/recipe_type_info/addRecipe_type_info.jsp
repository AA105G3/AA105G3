<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.recipe_type_info.model.*"%>



<jsp:useBean id="recipe_type_infoVO" scope="page" class="com.recipe_type_info.model.Recipe_type_infoVO" />
<html>
<head>
<title>食譜類別新增頁面 - addRecipe_type_info.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>食譜類別新增頁面 - addRecipe_type_info.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/back-end/recipe_type_info/select_page.jsp">回首頁</a>
	    </td>
	</tr>
</table>

<h3>資料權限:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/recipe_type_info/recipe_type_info.do" name="form1">
<table border="0">

	<tr>
		<td>食譜類別編號:</td>
		<td><input type="TEXT" name="recipe_type_no" size="45" placeholder="請輸入食譜類別編號"
			 />
		</td>
	</tr>
	<tr>
		<td>食譜類別範圍:</td>
		<td><input type="TEXT" name="type_range" size="45" placeholder="請輸入食譜類別範圍"
			 />
		</td>
	</tr>
</table>
<br>

<input type="hidden" name="recipe_no" value="${param.recipe_no}">
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
