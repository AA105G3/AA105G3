<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.recipe.model.*"%>
<%@ page import="com.recipe_cont.model.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:useBean id="recipeSvc" scope="page" class="com.recipe.model.RecipeService" />
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="recipe_l_typeSvc" scope="page" class="com.recipe_l_type.model.Recipe_l_typeService" />
<jsp:useBean id="recipe_m_typeSvc" scope="page" class="com.recipe_m_type.model.Recipe_m_typeService" />
<jsp:useBean id="recipe_s_typeSvc" scope="page" class="com.recipe_s_type.model.Recipe_s_typeService" />



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
	#recipe-home{
		margin-bottom:20px;
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
		width:190px;
		padding:8px 0px;
	}
	
	.list-group{
		list-style-type: none;
		padding-left: 7%;
	}
	.recipe-list-title{
		font-size: 18px;
		list-style-type: none;
		color: #564e4a;
		margin-left:5px;
	}
	.recipe-list-item{
		color: #89817d;
		margin-left:10px;
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
	.panel-body{
		background:#f9f7f2;
		padding:10px 15px;
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
	<c:forEach var="aLtype" items="${recipe_l_typeSvc.all}">
		<div class="container recipe-type-wrapper">
			<div class="row">
				<div class="recipe-typename-wrapper">
					<h4 class="title-recipe-type">${aLtype.l_type_name }</h4>
				</div>
				<div class="panel-body">

	                  <table class="borderless">
	                  <c:forEach var="count" items="${recipe_l_typeSvc.getM_typesByL_Type_No(aLtype.recipe_l_type_no)}" varStatus="s" step="6">
	                  	<tr>
	                  	
	                  	<c:forEach var="aMtype" items="${recipe_l_typeSvc.getM_typesByL_Type_No(aLtype.recipe_l_type_no)}" varStatus="x" begin="${s.index}" end="${s.index+5}">
	                  
	                  		<c:if test="${x.index>=s.index}">
		                  		<td><a class="recipe-list-title" href="<%=request.getContextPath()%>/recipe_type_info/recipe_type_info.do?&action=findByTypeNo&recipe_type_no=${aMtype.recipe_m_type_no}">
		                  		<i class="glyphicon glyphicon-triangle-right" aria-hidden="true">
		                  			</i>${aMtype.m_type_name}</a>
		                  		<c:if test="${recipe_m_typeSvc.getS_typesByM_Type_No(aMtype.recipe_m_type_no).size()>0}">
			                  		<ul class="list-group">
									<c:forEach var="aStype" items="${recipe_m_typeSvc.getS_typesByM_Type_No(aMtype.recipe_m_type_no)}" >                  		
			                  			<li><a href="<%=request.getContextPath()%>/recipe_type_info/recipe_type_info.do?&action=findByTypeNo&recipe_type_no=${aStype.recipe_s_type_no}" class="recipe-list-item">${aStype.s_type_name}</a></li>
			                  		</c:forEach>
			                  		</ul>
			                  	</c:if>
		                  		</td>
	                  		</c:if>
	                  		<c:if test="${aMtype.m_type_name=='' && x.index<s.index+5}">
	                  			<td></td>
	                  		</c:if>
	                  	</c:forEach>
	                  	</tr>
	                  </c:forEach>
	                  </table>
	              </div>
			</div>
		</div>
	</c:forEach>
	</section>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>