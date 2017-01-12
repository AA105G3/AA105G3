<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>會員登入</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/AA105G3/css/FloginCSS.css">

<style>
	.errorMsg{
		color : red;
	}
	body{
		height : 100%;
	}
	footer{
		position : absolute;
	    bottom : 0px;
		width : 100%;
	}
	#myModal{
		padding-top : 220px;
	}
</style>

</head>



<body>





<c:import url="/front-end/frontNavbar.jsp"></c:import>





	<div class="mu-login">
		<div class="container">
			<h1>Welcome</h1>
			
			<div>
				<span class="errorMsg">${errorMsg}</span>
			</div>
			
			<form class="form" action="/AA105G3/Floginhandler" method="post">
				<input type="text" name="mem_ac" placeholder="Username">
				<input type="password" name="mem_pw" placeholder="Password">
				<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
				<button type="submit" id="login-button">Login</button><br>
				<div class="wrapper-register">
					<a href="#" data-toggle="modal" data-target="#myModal">忘記密碼</a>
					
					<a href="/AA105G3/front-end/member/MemberSignUp.jsp">立即註冊</a>
				</div>
					
			</form>
		</div>
		
		<ul class="bg-bubbles">
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>
	</div>
	
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
						<span class="sr-only">Close</span>
					</button>
					<h3 class="text-center">請輸入註冊時所使用的信箱</h3>
				</div>

				<form METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do">
					<div class="modal-body">
						<div class="input-group">
							<div class="input-group-addon">
								請輸入信箱
							</div>
							<input type="text" name="mem_email" class="form-control" value="${memberVO.mem_email}">
						</div>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
						<button type="submit" class="btn btn-primary">送出</button>
						<input type="hidden" name="action" value="forgetPW">
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