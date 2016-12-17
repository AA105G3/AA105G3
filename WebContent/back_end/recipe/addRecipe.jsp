<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.recipe.model.*"%>
<%
/* RecipeVO recipeVO = (RecipeVO) request.getAttribute("recipeVO"); */
%>
<jsp:useBean id="recipeVO" scope="request" class="com.recipe.model.RecipeVO"/>
<html>
<head>
<title>食譜新增頁面 - addRecipe.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>食譜新增頁面 - addRecipe.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp">回首頁</a>
	    </td>
	</tr>
</table>

<h3>食譜資料:</h3>
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

<FORM METHOD="post" ACTION="recipe.do" name="form1" enctype="multipart/form-data">
<table border="0">

	<tr>
		<td>食譜名稱:</td>
		<td><input type="TEXT" name="recipe_name" size="45" value="${recipeVO.recipe_name}" placeholder="例如:黃金蛋炒飯"></td>
	</tr>
	<tr>
		<td>食譜簡介:</td>
		<td><textarea name="recipe_intro" rows="6" cols="80">${recipeVO.recipe_intro}</textarea></td>
	</tr>
	<tr>
		<td>食材:</td>
		<td><textarea name="food_mater" rows="6" cols="80">${recipeVO.food_mater}</textarea></td>
	</tr>
	<tr>
		<td>食譜圖片:</td>
		<td><input type="file" name="recipe_pic"></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="hidden" name="mem_no" value="M00000003"><!-- ${sessionScope.xxx.mem_no} -->
<input type="submit" value="送出新增"></FORM>
</body>

</html>
