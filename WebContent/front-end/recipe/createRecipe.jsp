<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.recipe.model.*"%>


<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<style type="text/css">
			h3{
				padding-bottom: 5px;
				border-bottom: 1px solid black;
			}
		</style>
	</head>
	<body>
	
	<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
	
	
		
		<div class="container">
			<div class="row">
			<div class="col-xs-12 col-sm-offset-2 col-sm-8 col-sm-offset-2">
				<h3>食譜名稱</h3>
				
				<form class="form" action="<%=request.getContextPath()%>/recipe/recipe.do" method="POST">
					<input type="text" class="form-control recipe-text" placeholder="例如:海鮮燉飯" name="recipe_name">
					<div>
						<button class="btn btn-primary createRecipe-btn" type="submit">開始編輯食譜</button>
						<input type="hidden" name="action" value="create">
						<input type="hidden" name="mem_no" value="M00000003">
					</div>
				</form>
			</div>
				


			</div>
		</div>
		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>