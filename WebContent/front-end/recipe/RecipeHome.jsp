<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.recipe.model.*"%>
<%@ page import="com.recipe_cont.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="recipeSvc" scope="page" class="com.recipe.model.RecipeService" />
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />

<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>食譜首頁</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<!-- <link rel="stylesheet" type="text/css" href="css/homeRecipeCSS.css"> -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	<style type="text/css">
	body{
		background: #efede8;
		padding-top: 50px;
	}

	.recipe-search{
		
		text-align: center;
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

	.recipe-title-wrapper{
		background: #fff;
		margin-bottom: 3px;
		margin-top: 20px;
	}
	.title-item-top{

	}
	.recipe-title-wrapper .title-more{
		text-align: right;

	}
	.recipe-title-wrapper a{
		color: #595b5a;
		text-decoration: none;
	}
	.recipe-title-wrapper a:hover{
		color: red;
	}

	.news-recipe-wrapper{
		background: #fff;
		padding-top: 10px;
	}
	.news-recipe{
		text-align: center;
		width: 250px;
		background: #f6f6f6;
	}
	.news-recipe>img{
		width: 250px;
		height: 200px;
	}
	.recipe-content{
		text-align: left;
		/* padding-left: 10px */;
	}

	.top-recipe-wrapper{
		background: #fff;
		padding-top: 10px;
	}
	.top-recipe{
		text-align: center;
		width: 250px;
		background: #f6f6f6;
	}
	.home-recipe-images{
		width:250px;
		height:200px;
	}
	.recipe-type-wrapper{
		background: #fff;
	}
	.recipe-typename-wrapper{
		padding-left: 15px;
	}
	.title-recipe-type{
		font-weight: bold;
	}
	.borderless td,.borderless tr{
		border:none !important;
	}
	.list-group{
		list-style-type: none;
		padding-left: 7%;
	}
	.recipe-list-title{
		font-size: 18px;
		list-style-type: none;
		color: #564e4a;
	}
	.recipe-list-item{
		color: #89817d;
	}
	.glyphicon-triangle-right{
		font-size: 2px;
		color: #f04646;
	}
	.recipe-author{
		color:#9d9f9e;
	}
	.glyphicon-menu-right{
		font-size: 16px;
	}
	
	.top-recipe a,.news-recipe a{
		color:#000;
	}
	.top-recipe a:hover,.news-recipe a:hover{
		text-decoration:none;	
	}
	
	</style>
	
	</head>
	<body>
	<section id="recipe-home">
		<header>
			<div class="container">
				<div class="row">
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
						
							<a href="addRecipe.jsp">
							<button class="btn btn-default write-recipe">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							寫食譜</button></a>
						
					</div>
				</div>
			</div>
		</header>
		<div class="container recipe-title-wrapper">
			<div class="row ">
				<div class="col-xs-12 col-sm-6">
					<h4 class="title-item-top">最新食譜</h4>
				</div>
				<div class="col-xs-12 col-sm-6">
					<a href="<%=request.getContextPath()%>/front-end/recipe/RecipeNewest.jsp"><h4 class="title-more">更多內容 <i class="glyphicon glyphicon-menu-right"></i></h4>
					
					</a>
				</div>
			</div>
		</div>
		<div class="container news-recipe-wrapper">
			
			<div class="row">
			<c:forEach var="recipeVO" items="${recipeSvc.all}" begin="0" end="3">
				<div class="col-xs-12 col-sm-3 ">
					<div class="news-recipe">
						<a href="<%=request.getContextPath()%>/recipe/recipe.do?action=getOne_For_Display&recipe_no=${recipeVO.recipe_no}">
						<img class="home-recipe-images" src="<%=request.getContextPath()%>/recipe/showRecipe_pic.do?recipe_no=${recipeVO.recipe_no}" title="${recipeVO.recipe_name }">
						</a>
						<div class="recipe-content">
							<a href="<%=request.getContextPath()%>/recipe/recipe.do?action=getOne_For_Display&recipe_no=${recipeVO.recipe_no}">
							<h4>${recipeVO.recipe_name}</h4>
							</a>
							<p>by <a href="" class="recipe-author">${memberSvc.getOneMember(recipeVO.mem_no).mem_name}</a></p>
						</div>
					</div>
				</div>
			</c:forEach>	
			</div>
		</div>

		<div class="container recipe-title-wrapper">
			<div class="row ">
				<div class="col-xs-12 col-sm-6">
					<h4 class="title-item-top">人氣食譜</h4>
				</div>
				<div class="col-xs-12 col-sm-6">
					<a href="<%=request.getContextPath()%>/front-end/recipe/RecipeTopViews.jsp"><h4 class="title-more">更多內容 <i class="glyphicon glyphicon-menu-right"></i></h4></a>
				</div>
			</div>
		</div>

		<div class="container top-recipe-wrapper">
		
			<div class="row">
			<c:forEach var="recipeVO" items="${recipeSvc.topViewsRecipe()}" begin="0" end="3">
				<div class="col-xs-12 col-sm-3 ">
					<div class="top-recipe">
						<a href="<%=request.getContextPath()%>/recipe/recipe.do?action=getOne_For_Display&recipe_no=${recipeVO.recipe_no}">
						<img class="home-recipe-images" src="<%=request.getContextPath()%>/recipe/showRecipe_pic.do?recipe_no=${recipeVO.recipe_no}" title="${recipeVO.recipe_name }">
						<div class="recipe-content">
							<h4>${recipeVO.recipe_name}</h4>
							</a>
							<p>by <a href="" class="recipe-author">${memberSvc.getOneMember(recipeVO.mem_no).mem_name}</a></p>
						</div>
					</div>
				</div>
			</c:forEach>
				
			</div>
		</div>
		
		<div class="container recipe-title-wrapper">
			<div class="row ">
				<div class="col-xs-12 col-sm-6">
					<h4 class="title-item-top">全部分類</h4>
				</div>
				<div class="col-xs-12 col-sm-6">
					<h4 class="title-more">
						數不清的食譜等著你來逛
					</h4>
				</div>
			</div>
		</div>
		<div class="container recipe-type-wrapper">
			<div class="row">
				<div class="recipe-typename-wrapper">
					<h4 class="title-recipe-type">家常菜</h4>
				</div>
				<div class="panel-body">

	                  <table class="table borderless">
	                  	<tr>
	                  		<td><a class="recipe-list-title" href="#">
	                  		<i class="glyphicon glyphicon-triangle-right" aria-hidden="true"></i>涼拌</a></td>
	                  		<td><a class="recipe-list-title" href="#"><i class="glyphicon glyphicon-triangle-right" aria-hidden="true"></i>炸物</a></td>
	                  		<td><a class="recipe-list-title" href="#"><i class="glyphicon glyphicon-triangle-right" aria-hidden="true"></i>沙拉</a></td>
	                  		<td><a class="recipe-list-title" href="#"><i class="glyphicon glyphicon-triangle-right" aria-hidden="true"></i>披薩</a></td>
	                  		<td><a class="recipe-list-title" href="#"><i class="glyphicon glyphicon-triangle-right" aria-hidden="true"></i>滷肉</a></td>
	                  		<td><a class="recipe-list-title" href="#"><i class="glyphicon glyphicon-triangle-right" aria-hidden="true"></i>烤肉</a></td>
	                  	</tr>
	                  	<tr>
	                  		
	                  		<td><a class="recipe-list-title" href="#"><i class="glyphicon glyphicon-triangle-right" aria-hidden="true"></i>焗烤</a>
	                  		</td>
	                  		<td><a class="recipe-list-title" href="#"><i class="glyphicon glyphicon-triangle-right" aria-hidden="true"></i>咖哩</a></td>
	                  		<td><a class="recipe-list-title" href="#"><i class="glyphicon glyphicon-triangle-right" aria-hidden="true"></i>熱炒</a></td>
	                  		<td><a class="recipe-list-title" href="#"><i class="glyphicon glyphicon-triangle-right" aria-hidden="true"></i>鍋物</a></td>
	                  		<td><a class="recipe-list-title" href="#"><i class="glyphicon glyphicon-triangle-right" aria-hidden="true"></i>素食</a>
							<ul class="list-group">
	                  			<li><a href="#" class="recipe-list-item">全素</a></li>
	                  			<li><a href="#" class="recipe-list-item">奶蛋素</a></li>
	                  		</ul>
	                  		</td>
	                  		<td><a class="recipe-list-title" href="#"><i class="glyphicon glyphicon-triangle-right" aria-hidden="true"></i>米食</a>
	                  		<ul class="list-group">
	                  			<li><a href="#" class="recipe-list-item">炒飯</a></li>
	                  			<li><a href="#" class="recipe-list-item">粥</a></li>
	                  		</ul>
	                  		</td>
	                  	</tr>
	                  	<tr>
	                  		<td><a class="recipe-list-title" href="#"><i class="glyphicon glyphicon-triangle-right" aria-hidden="true"></i>麵食</a></td>
	                  		<td><a class="recipe-list-title" href="#"><i class="glyphicon glyphicon-triangle-right" aria-hidden="true"></i>湯</a></td>
	                  		<td><a class="recipe-list-title" href="#"></a></td>
	                  	</tr>
	                  </table>
	              </div>
			</div>
		</div>
		
	</section>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>