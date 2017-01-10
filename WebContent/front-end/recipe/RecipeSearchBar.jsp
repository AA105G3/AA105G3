<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>
		
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<style type="text/css">
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

		</style>
	</head>
	<body>
		
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
						
							<a href="<%=request.getContextPath()%>/front-end/recipe/addRecipe.jsp">
							<button class="btn btn-default write-recipe">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							寫食譜</button></a>
						
					</div>
				</div>
			</div>
		
		
		
	</body>
</html>