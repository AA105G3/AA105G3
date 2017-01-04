<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>管理者登入頁面</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<style>
		body, html{
		     height: 100%;
		 	background-repeat: no-repeat;
		 	background-color: #d3d3d3;
		 	font-family: 'Oxygen', sans-serif;
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
		}
		.errorMsg{
			color: red;
			margin-left: 15%;
		}
		</style>
	</head>
	<body>
		
			<div class="container">
			<div class="row main">
				<div class="panel-heading">
	               <div class="panel-title text-center">
						<p class="sec-title">－管理者登入頁面－</p>
	               		<h1 class="title">FoodTime</h1>
						
	               		<hr />
	               	</div>
	            </div> 
				<div class="main-login main-center">
					<form class="form-horizontal" method="post" action="/AA105G3/BLoginHandler">
						<div class="form-group">
							<label for="emp_account" class="cols-sm-2 control-label">Account</label>
							<span class="errorMsg">${errorMsg}</span>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
									<input type="text" class="form-control" name="emp_account" id="emp_account"  placeholder="請輸入帳號"/>
								</div>
								<div class="errorMsg"></div>
							</div>
						</div>

						<div class="form-group">
							<label for="emp_password" class="cols-sm-2 control-label">Password</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
									<input type="password" class="form-control" name="emp_password" id="emp_password"  placeholder="請輸入密碼"/>
									<input type="hidden" name="action" value="blogin">
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
		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
</html>