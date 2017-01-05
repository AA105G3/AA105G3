<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>會員登入</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/AA105G3/css/frontpageCSS.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

<style type="text/css" media="screen">
	body{
		height : 100%;
	}
	.select-menu{
		text-align: center;
		background: #f5f5dc;
		width: 100%;
	}
	.select-item{
		padding-left: 30px;
		padding-right: 30px;
		font-size: 24px;
	}
	.href-style{
		color: black;
	}
	.first-col{
		padding-top:5px;
		padding-bottom: 50px;
	}
	.first-col img{
		width: 350px;
		height: 250px;
	}
	.next-col{
		padding-bottom: 50px;
	}
	.next-col img{
		width: 350px;
		height: 250px;
	}
	#top-img{
		background-image: url(/AA105G3/images/Market/product_title.jpg);
		height: 500px;
		/* background-size: cover; */
		background-position: center;
	}
	footer{
		position : absolute;
	    bottom : 0px;
		width : 100%;
	}
	.title-style{
		padding-top: 100px;
	}
	
	
	
	.main{
		 	margin-top: 70px;
		}

		h1.title { 
			font-size: 50px;
			font-family: 'Passion One', cursive; 
			font-weight: 400;
			margin: 0px 0px; 
		}
		p.sec-title{
			margin: 0px;
		}

		hr{
			width: 10%;
			color: #fff;
			margin-bottom: 10px;
			margin-top: 10px;
		}

		.form-group{
			margin-bottom: 15px;
		}

		label{
			margin-bottom: 15px;
		}

		input,
		input::-webkit-input-placeholder {
		    font-size: 11px;
		    padding-top: 3px;
		}

		.main-login{
		 	background-color: #fff;
		    /* shadows and rounded borders */
		    -moz-border-radius: 2px;
		    -webkit-border-radius: 2px;
		    border-radius: 2px;
		    -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
		    -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
		    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);

		}

		.main-center{
		 	margin-top: 30px;
		 	margin: 0 auto;
		 	max-width: 330px;
		    padding: 40px 40px;

		}

		.login-button{
			margin-top: 5px;
		}

		.login-register{
			font-size: 11px;
			text-align: center;
			padding-top: 10px;
		}
		.errorMsg{
			color: red;
			margin-left: 30%;
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
		<div class="row main">
			<div class="panel-heading title-style">
               <div class="panel-title text-center">
					<p class="sec-title">－會員登入－</p>
               		<h1 class="title">FoodTime</h1>
					
               		<hr />
               	</div>
            </div> 
			<div class="main-login main-center">
				<form class="form-horizontal" action="/AA105G3/Floginhandler" method="post">
					<div class="form-group">
						<div>
							<span class="errorMsg">${errorMsg}</span>
						</div>
						<label for="mem_ac" class="cols-sm-2 control-label">會員帳號：SUPERCAT2017</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
								<input type="text" class="form-control" name="mem_ac" id="mem_ac"  placeholder="請輸入帳號"/>
							</div>
							<div class="errorMsg"></div>
						</div>
					</div>

					<div class="form-group">
						<label for="mem_pw" class="cols-sm-2 control-label">會員密碼：123</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
								<input type="password" class="form-control" name="mem_pw" id="mem_pw"  placeholder="請輸入密碼"/>
							</div>
							<div class="errorMsg"></div>
						</div>
					</div>

					<div class="form-group ">
						<input type="submit" class="btn btn-primary btn-lg btn-block login-button" value="登入">
						
					</div>
					<div class="login-register">
			            <a href="">忘記密碼</a>
			         </div>
				</form>
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