<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.recipe_type_info.model.*"%>
<%@ page import="com.recipe.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	RecipeService recipeSvc = new RecipeService();
    List<RecipeVO> list = recipeSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="recipe_type_infoSvc" scope="page" class="com.recipe_type_info.model.Recipe_type_infoService" />

<html>
<head>
<title>所有食譜類別資料 - listAllRecipe_type_info.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有食譜類別資料 - listAllRecipe_type_info.jsp</h3>
		<a href="<%=request.getContextPath()%>/back-end/recipe_type_info/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
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

<table border='1' bordercolor='#CCCCFF' width='1200'>
	<tr>
		<th>食譜編號</th>
		<th>食譜名稱</th>
		<th>食譜類別名稱(類別範圍)</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="recipeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${recipeVO.recipe_no}</td>
			<td>${recipeVO.recipe_name}</td>
			<td>
				<c:forEach var="recipe_type_infoVO" items="${recipe_type_infoSvc.getRecipe_type_infoByRecipe_no(recipeVO.recipe_no)}">
	                    ${recipe_type_infoVO.recipe_type_no}【<font color=orange>${recipe_type_infoVO.type_range==1 ? '中類別' : '小類別'}</font>】<br>
                </c:forEach>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/recipe_type_info/recipe_type_info.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="recipe_no" value="${recipeVO.recipe_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
