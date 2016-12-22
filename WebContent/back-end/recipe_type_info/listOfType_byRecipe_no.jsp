<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.recipe_type_info.model.*"%>
<%@ page import="com.recipe.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<jsp:useBean id="recipe_type_infoSvc" scope="page" class="com.recipe_type_info.model.Recipe_type_infoService" />
<jsp:useBean id="recipeSvc" scope="page" class="com.recipe.model.RecipeService" />
<jsp:useBean id="one_recipe_type_info" scope="page" class="java.util.ArrayList" />
<html>
<head>
<title>食譜類別資料 - listOfType_byRecipe_no.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>食譜類別資料 - listOfType_byRecipe_no.jsp</h3>
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
<h3>食譜編號:${param.recipe_no}</h3>
<h3>食譜名稱:${recipeSvc.getOneRecipe(param.recipe_no).recipe_name}</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/recipe_type_info/addRecipe_type_info.jsp?recipe_no=${param.recipe_no}'>Add</a> a new Recipe_type_info.</li>
</ul>
<table border='1' bordercolor='#CCCCFF' width='1200'>
	<tr>
		<th>食譜類別名稱</th>
		<th>類別範圍</th>
		<th>刪除</th>
	</tr>
	<c:forEach var="recipe_type_infoVO" items="${list}">
		<tr align='center' valign='middle'>
			<td>${recipe_type_infoVO.recipe_type_no}</td>
			<td>${recipe_type_infoVO.type_range==1 ? '中類別' : '小類別'}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/recipe_type_info/recipe_type_info.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="recipe_no" value="${recipe_type_infoVO.recipe_no}">
			    <input type="hidden" name="recipe_type_no" value="${recipe_type_infoVO.recipe_type_no}">
			    <input type="hidden" name="action"value="deleteOne"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>
