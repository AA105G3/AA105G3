<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>會員頁面</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/AA105G3/css/frontpageCSS.css">

<style type="text/css" media="screen">
	.header-style{
		padding-top: 150px;
	}
	.top-style{
		padding-top: 75px;
		padding-bottom: 100px;
	}
	.count-style{
		padding-top: 10px;
		padding-bottom: 30px;
	}
</style>

</head>
<body>

<!--START SCROLL TOP BUTTON -->
    <a class="scrollToTop" href="#">
      <i class="fa fa-angle-up"></i>
      <span>Top</span>
    </a>
  <!-- END SCROLL TOP BUTTON -->

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











   		<div class="col-xs-12 col-sm-7 top-style">
   			<div role="tabpanel">
			    <!-- 標籤面板：標籤區 -->
			    <ul class="nav nav-tabs" role="tablist">
			        <li role="presentation" class="active">
			            <a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">食譜</a>
			        </li>
			        <li role="presentation">
			            <a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">收藏</a>
			        </li>
			        <li role="presentation">
			            <a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">好友</a>
			        </li>
			    </ul>
			
			    <!-- 標籤面板：內容區 -->
				    <div class="tab-content">
				        <div role="tabpanel" class="tab-pane active" id="tab1">目前沒有任何的食譜或料理</div>
				        <div role="tabpanel" class="tab-pane" id="tab2">收藏標籤的內容</div>
				        <div role="tabpanel" class="tab-pane" id="tab3">好友標籤的內容</div>
				    </div>
				</div>
    		</div>

    		

    		<div class="col-xs-12 col-sm-5 text-center top-style">
    			<img src="/AA105G3/MemberDBGifReader.do?name=${mem_no}" width='150' height='150'>
    			<h3>${mem_name}</h3>

				<div class="col-xs-12 col-sm-6 count-style">
	    			<div>食譜數：0</div>
	    		</div>
	    		<div class="col-xs-12 col-sm-6 count-style">
	    			<div>追隨數：0</div>
	    		</div>

    			<a class="btn btn-primary">追隨我</a>
    		</div>
    	</div>
    </div>


<footer>
	Copyright &copy; 2016 Java Team 3 
</footer>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>