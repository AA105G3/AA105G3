<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.recipe.model.*"%>
<%@ page import="com.recipe_cont.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.recipe.model.*"%>

<% 		
	String typeName= request.getParameter("typeName");
	String recipe_type_no= request.getParameter("recipe_type_no");
%>
<jsp:useBean id="list" scope="request" type="java.util.List" />

<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />

<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>${typeName}</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<style type="text/css">
			
			body{
				background: #efede8;
				padding-top: 50px;
			}
			
			.recipe-search-wrapper{
				margin-bottom:20px;
			}
			.recipe-search{
		
				text-align: right;
			}
		
			.recipe-select{
				height: 50px;
			}
			.recipe-text{
				height: 50px;
				width:375px !important;
			}
			
			.recipe-btn{
				height: 50px;
				color:#D9230F;
				width: 50px;
			}
			.recipe-header-right{
				text-align: right;
				padding-right: 0px;
			}
			.write-recipe{
				height: 50px;
				font-weight: bold;
			}
			
			.page-top-title-wrapper{
				background: #fff;
				margin-bottom: 5px;
				border:1px solid #d3d0c9;
			}
			.title-item-top{
				margin:15px 5px;
				
			}
			.recipe-row-wrapper{
				margin-bottom: 10px;
			}
			.recipe-row-wrapper img{
				width:230px;
				height:200px;
			}
			.recipe-item-wrapper{
				background: #fff;
				padding: 15px;
				border:1px solid #d3d0c9;
				height:230px;
			}
			.recipe-item-wrapper-right{
				padding: 15px 15px 15px 15px;
				background: #fff;
				border:1px solid #d3d0c9;
				height:230px;
			}
			.recipe-item-wrapper h3{
				margin:0px 0px 5px 0px;	
			}
			.recipe-item-wrapper-right h3{
				margin:0px 0px 5px 0px;
			}
			.recipe-item-left{
				padding:0px;
			}
			.recipe-item-right{
				padding:0px 0px 0px 10px;
			}
			.recipe-intro{
				color:#706864;
				height:60px;
				word-break:break-all;
			}
			.recipe-food-mater{
				color:#b6b0a5;
				word-break:break-all;
				height:40px;
			}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="row recipe-search-wrapper">
					<div class="col-xs-12 col-sm-8 ">
						<div class="recipe-search">
								<form class="form-inline" action="<%=request.getContextPath()%>/recipe/recipe.do" method="POST">
								    <select class="form-control recipe-select" name="searchCondition">
								        <option value="recipe_name">找食譜名</option>
								        <option value="food_mater">找食材</option>
								    </select>
								   <div class="input-group recipe-search-form">
								    <input type="text" class="form-control recipe-text" placeholder="Search Recipe" name="searchInput">
								        <span class="input-group-btn">
								            <button class="btn btn-default recipe-btn" type="submit" name="action" value="search"><i class="glyphicon glyphicon-search"></i></button>
								        </span>
								    </div>
								</form>
							</div>
					</div>
					<div class="col-xs-12 col-sm-4 recipe-header-right">
						
							<a href="<%=request.getContextPath()%>/front-end/recipe/addRecipe.jsp">
							<button class="btn btn-default write-recipe">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							寫食譜</button></a>
						
					</div>
				</div>
		
		
			<div class="row">
				<div class="col-xs-12 col-sm-12 page-top-title-wrapper">
					<h2 class="title-item-top"> ${typeName}</h2>
				</div>
			</div>
	<%@ include file="pages/pageForSearch1.file" %> 
	
	<c:forEach var="recipeVO" items="${list}" varStatus="s" step="2" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> 
			<div class="row recipe-row-wrapper">
				<div class="col-xs-12 col-sm-6 recipe-item-wrapper">
					<div class="col-xs-12 col-sm-5 recipe-item-left">
					<a href="<%=request.getContextPath()%>/recipe/recipe.do?action=getOne_For_Display&recipe_no=${recipeVO.recipe_no}">
						<img src="<%=request.getContextPath()%>/recipe/showRecipe_pic.do?recipe_no=${recipeVO.recipe_no}">
					</a>
					</div>
					<div class="col-xs-12 col-sm-7 recipe-item-right">
					<a href="<%=request.getContextPath()%>/recipe/recipe.do?action=getOne_For_Display&recipe_no=${recipeVO.recipe_no}">
						<h3>${recipeVO.recipe_name}</h3>
						</a>
						<p>by <a href="#">${memberSvc.getOneMember(recipeVO.mem_no).mem_name}</a></p>
						<p class="recipe-intro">${recipeVO.recipe_intro}</p>
						<p class="recipe-food-mater">食材：${recipeVO.food_mater}</p>
						<p>
							<i class="glyphicon glyphicon-eye-open">${recipeVO.recipe_total_views}</i>
							<i class="glyphicon glyphicon-heart">${recipeVO.recipe_like}</i>
						</p>
					</div>
				</div>
				<c:if test="${s.index+1 < list.size()}">
				<div class="col-xs-12 col-sm-6 recipe-item-wrapper-right">
					<div class="col-xs-12 col-sm-5 recipe-item-left">
					<a href="<%=request.getContextPath()%>/recipe/recipe.do?action=getOne_For_Display&recipe_no=${list.get(s.index+1).recipe_no}">
						<img src="<%=request.getContextPath()%>/recipe/showRecipe_pic.do?recipe_no=${list.get(s.index+1).recipe_no}">
					</a>
					</div>
					<div class="col-xs-12 col-sm-7 recipe-item-right">
						<a href="<%=request.getContextPath()%>/recipe/recipe.do?action=getOne_For_Display&recipe_no=${list.get(s.index+1).recipe_no}">
							<h3>${list.get(s.index+1).recipe_name}</h3>
						</a>
						<p>by <a href="#">${memberSvc.getOneMember(list.get(s.index+1).mem_no).mem_name}</a></p>
						<p class="recipe-intro">${list.get(s.index+1).recipe_intro}</p>
						<p class="recipe-food-mater">食材：${list.get(s.index+1).food_mater}</p>
						<p>
							<i class="glyphicon glyphicon-eye-open">${list.get(s.index+1).recipe_total_views}</i>
							<i class="glyphicon glyphicon-heart">${list.get(s.index+1).recipe_like}</i>
						</p>
					</div>
				</div>
				</c:if>
			</div>
		</c:forEach>

			

		<%@ include file="pages/pageForSearch2.file" %>
		</div>
		
		
		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>