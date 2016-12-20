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
<jsp:useBean id="recipeVO" scope="request" class="com.recipe.model.RecipeVO"/>
<jsp:useBean id="recipe_contSvc" scope="request" class="com.recipe_cont.model.Recipe_contService"/>
<jsp:useBean id="recipeSvc" scope="request" class="com.recipe.model.RecipeService"/>

<html>
<head>
<title>食譜步驟新增頁面 - addRecipe_cont.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/showImg.js"></script>
<script src="https://code.jquery.com/jquery.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>食譜步驟新增頁面 - addRecipe_cont.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/front-end/recipe_cont/select_page.jsp">回首頁</a>
	    </td>
	</tr>
</table>

<h3>食譜步驟資料:</h3>
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
<table border="0">
	
	<tr>
		<td>食譜編號:${param.recipe_no}</td>
	</tr>
	<tr>
		<td>食譜名稱:${recipeSvc.getOneRecipe(param.recipe_no).recipe_name}</td>
	</tr>
	<tr>
		<td>步驟:</td>
	</tr>
	<tr>
		<td width="200"><image  style="width:200px;" id="image1">
		<input type="file" name="recipe_pic" id = "upLoadFile1" onchange="showImage(1)">
		</td>
		<td>
		${recipe_contSvc.getRecipe_cont(param.recipe_no).size()+1}<br>
		<input type="hidden" name="step" value="${recipe_contSvc.getRecipe_cont(param.recipe_no).size()+1}">
		<textarea name="step_cont" rows="6" cols="80"></textarea>
		</td>
	</tr>
	<tr>
		<td width="200"><image  style="width:200px;" id="image2">
		<input type="file" name="recipe_pic" id = "upLoadFile2" onchange="showImage(2)">
		</td>
		<td>
		${recipe_contSvc.getRecipe_cont(param.recipe_no).size()+2}<br>
		<input type="hidden" name="step" value="${recipe_contSvc.getRecipe_cont(param.recipe_no).size()+2}">
		<textarea name="step_cont" rows="6" cols="80"></textarea>
		</td>
	</tr>
	<tr>
		
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="hidden" name="recipe_no" value="${param.recipe_no}">
<input type="submit" value="送出新增"></FORM>
<p>
		
  </p>
</body>
<script type="text/javascript">

function showImage(idx){
	var file = document.getElementById("upLoadFile"+idx).files[0];				
	var read = new FileReader();
	read.onload=function(){
			var image = document.getElementById('image'+idx);
				image.src=read.result;
				
			};
	read.readAsDataURL(file);	
} 
</script>
</html>
