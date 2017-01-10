<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>會員頁面</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/AA105G3/css/frontpageCSS.css">

<style type="text/css" media="screen">
	.header-style{
		padding-top: 80px;
	}
	.search-style{
		padding-bottom: 50px;
	}
	.member-style{
		padding-top: 20px;
		padding-bottom: 20px;
		background: white;
	}
	.count-style{
		padding-top: 10px;
		padding-bottom: 30px;
	}
	.content-style{
		background: white;
		margin-top: 25px;
	}
	#href-style{
		color: red;
		border-bottom: 3px red solid;
	}
	
	
	
	
	body{
		background: #efede8;
		padding-top: 50px;
		height : 100%;
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
	#page-content{
		min-height:700px;
	}
	footer{
		position : absolute;
	    bottom : 0px;
		width : 100%;
	}
</style>

</head>
<body>

 <div class="navbar navbar-default navbar-fixed-top navbar-inverse mu-main-navbar" >
     <div class="container">
       <div class="navbar-header">
         <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
           <span class="sr-only">Toggle navigation</span>
           <span class="icon-bar"></span>
           <span class="icon-bar"></span>
           <span class="icon-bar"></span>
         </button>
         <a href="#home" class="foodtime"><img alt="FoodTime" src="<%=request.getContextPath()%>/images/Logo.png">分享食光</a>
       </div>
       <div class="collapse navbar-collapse" id="navbar-ex-collapse">
         <ul class="nav navbar-nav navbar-right mu-main-nav">
           <li >
             <a href="#home">首頁</a>
           </li>
           <li>
             <a href="#mu-recipe">食譜</a>
           </li>
           <li>
             <a href="#mu-video">影音</a>
           </li>
           <li>
             <a href="#mu-chef">私廚</a>
           </li>
           <li>
             <a href="#mu-stream">實況</a>
           </li>
           <li>
             <a href="#mu-market">市集</a>
           </li>
           <li>
             <a href="#mu-contact">聯絡我們</a>
           </li>
           <li>
			<a href="#" class="dropdown-toggle" data-toggle="dropdown">UserID<b class="caret"></b></a>
			<ul class="dropdown-menu">
				<li><a href="#">編輯個人資訊</a></li>
				<li><a href="#">個人頁面</a></li>
				<li><a href="#">我的最愛</a></li>
				<li><a href="#">登出</a></li>
			</ul>
		</li>
		<li>
			<a href="#">註冊</a>
		</li>
         </ul>
       </div>
     </div>
   </div>


   <div class="container">
   	<div class="row">









		<header class="header-style">
			<div class="container" id="page-content">
				<div class="row">
					<div class="col-xs-12 col-sm-6 search-style">
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
					
					<div class="col-xs-12 col-sm-4 recipe-header-right search-style">
						
							<a href="addRecipe.jsp">
							<button class="btn btn-default write-recipe">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							寫食譜</button></a>
						
					</div>
					
				</div>
				
				<div class="col-xs-12 col-sm-8">
	   				
		   				<div role="tabpanel">
							<nav class="nav navbar-default">
						        <div class="container-fluid">
						            <ul class="nav navbar-nav">
						                <li><a data-toggle="tab" href="#home" id="href-style">食譜</a></li>
						                <li><a data-toggle="tab" href="#menu1">收藏</a></li>
						                <li><a data-toggle="tab" href="#menu2">好友</a></li>
						            </ul>
						        </div>
						    </nav>
						    
						    <div class="col-xs-12 col-sm-12 content-style">
								內容區
								<br>
								<br>
								<br>
								<br>
								<br>
								<br>
								<br>
								<br>
								<br>
								<br>
								<br>
								<br>
								<br>
							</div>		    
						</div>	

	    		</div>
	    		<div class="col-xs-12 col-sm-3 col-sm-push-1 text-center member-style">
			    	<img src="/AA105G3/MemberDBGifReader.do?name=${mem_no}" width='150' height='150'>
			    	<h3>${mem_name}</h3>
			
					<div class="col-xs-12 col-sm-6 count-style">
				    	<div>食譜數：0</div>
				    </div>
				    <div class="col-xs-12 col-sm-6 count-style">
				    	<div>追隨數：0</div>
				    </div>
				    
				    <div class="col-xs-12 col-sm-6">
				    	<a class="btn btn-primary">加入追隨</a>
				    </div>
				    <div class="col-xs-12 col-sm-6">
				    	<a class="btn btn-primary">加入好友</a>
				    </div>
			
			    	
			    </div>
			</div>
		</header>
		
		
		
	</div>
</div>
		











   		

    		

    		


<footer>
	Copyright &copy; 2016 Java Team 3 
</footer>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>