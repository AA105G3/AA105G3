<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.recipe.model.*"%>
<%@ page import="com.recipe_cont.model.*"%>
<%@ page import="java.util.Set"%>
<%
	/* Recipe_contService recipe_contSvc =new Recipe_contService();
	String recipe_no = request.getParameter("recipe_no");
	Set set = recipe_contSvc.getRecipe_cont(recipe_no);
 	int len = set.size();
 */

%>
<jsp:useBean id="recipe_cont_set" scope="request" type="java.util.Set" />
<jsp:useBean id="recipeVO" scope="request" class="com.recipe.model.RecipeVO"/>
<jsp:useBean id="recipeSvc" scope="request" class="com.recipe.model.RecipeService"/>

<html>
<head>
<title>食譜步驟內容資料修改 - update_recipe_cont_input.jsp</title></head>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/showImg.js"></script>
<script src="https://code.jquery.com/jquery.js"></script>
<body>
<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>食譜步驟內容資料修改 - update_recipe_cont_input.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/recipe_cont/select_page.jsp">回首頁</a></td>
	</tr>
</table>

<h3>資料修改:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/recipe_cont/recipe_cont.do" name="form1" enctype="multipart/form-data">
<table border="1">
	
	<tr>
		<td>食譜編號:${param.recipe_no}</td>
	</tr>
	<tr>
		<td>食譜名稱:${recipeSvc.getOneRecipe(param.recipe_no).recipe_name}</td>
	</tr>
	<tr>
		<td>步驟:</td>
	</tr>
	
	<c:forEach var="Recipe_contVO" items="${recipe_cont_set}" varStatus="s">
	<tr>
		<td width="200">
			<c:if test="${Recipe_contVO.step_pic != null}">

				<image  style="width:200px;" id="image${Recipe_contVO.step}" src="<%=request.getContextPath()%>/recipe_cont/showRecipe_cont_pic.do?recipe_no=${param.recipe_no}&step=${Recipe_contVO.step}"/>
			</c:if>
			<c:if test="${Recipe_contVO.step_pic == null}">
				<image style="width:200px;" id="image${Recipe_contVO.step}">
			</c:if>
		<input type="file" name="step_pic" id = "upLoadFile${Recipe_contVO.step}" value=<%=request.getContextPath()%>/recipe_cont/showRecipe_cont_pic.do?recipe_no=${param.recipe_no}&step=${Recipe_contVO.step} onchange="showImage(${Recipe_contVO.step})">
		
		<a href="<%=request.getContextPath()%>/recipe_cont/recipe_cont.do?action=deleteImage&recipe_no=${param.recipe_no}&step=${Recipe_contVO.step}">刪除圖片</a>
		 
		

		</td>
		<td>
		${Recipe_contVO.step}<br>
		<input type="hidden" name="step" value="${Recipe_contVO.step}">
		<textarea name="step_cont" rows="6" cols="80">${Recipe_contVO.step_cont}</textarea>
		</td>
	</tr>
	</c:forEach>
	
		
	
	
	
	
	
	
	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="recipe_no" value=${param.recipe_no}>
<input type="submit" value="送出修改"></FORM>


</body>
</html>