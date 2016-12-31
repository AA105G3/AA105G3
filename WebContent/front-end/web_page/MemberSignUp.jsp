<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>

<title>會員登入</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/AA105G3/front-end/web_page/css/frontpageCSS.css">
<script src = "/AA105G3/front-end/web_page/js/memberSignUp.js"></script>

<style type="text/css" media="screen">
	html, body{
		background : white;
		height : 100%;
	}

	#skin{
		/* border : solid red; */
		/* background : #f5f5dc; */
		width : 100%;
		margin : 0px auto;
				
		/* 填滿skin */
		min-height : 100%;
		position : relative;
	}
	#id_wrapper{
		min-height: 100%;
		position: relative;
	}
	#id_footer{
		position: absolute;
    	bottom: 0;
	}
	#theFooter{
		/* 對應skin */
		position : absolute;
		bottom : 0px;
		width : 100%;
	}
	.list-style{
		padding-top : 75px;
		padding-left : 175px;
	}
	.div-style{
		padding-top : 25px;
	}
</style>

</head>
<body>
<div id="skin">

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
			<a href="#home" class="foodtime"><img alt="FoodTime" src="<%=request.getContextPath()%>/front-end/web_page/images/Logo.png">分享食光</a>
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





<form>
	<div class="container">
		<div class="row">
			<div class="list-style">


<h1>會員註冊</h1>

<div class="col-xs-12 col-sm-6">
	<div class="input-group div-style">
		<div class="input-group-addon">
			會員帳號
		</div>
		<input type="text" id="memAc" placeholder="請輸入帳號" class="form-control">
	</div>
</div>
<div class="col-xs-12 col-sm-12">
	<span id="spanAc"></span>
</div>

<div class="col-xs-12 col-sm-6">
	<div class="input-group div-style">
		<div class="input-group-addon">
			會員密碼
		</div>
		<input type="text" id="memPw1" placeholder="請輸入密碼" class="form-control" maxlength="12">
	</div>
</div>
<div class="col-xs-12 col-sm-12">
	<span id="spanAc"></span>
</div>


			</div>
		</div>
	</div>
</form>








<footer id="theFooter">
	Copyright &copy; 2016 Java Team 3 
</footer>


<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>




</div>
</body>
</html>