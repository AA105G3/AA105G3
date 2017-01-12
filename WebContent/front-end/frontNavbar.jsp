<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>frontNavbar</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->

<%-- <%
	String requestURL = request.getRequestURI();
	session.setAttribute("location", requestURL);
%> --%>

<style>
.navbar-right li>a{
	color: #fff !important;
	background: #222222;	
}

.navbar-right li>a:hover,
.navbar-right li>a:active,
.navbar-right li>a:focus
{
	color: #c1a35f !important;
}

.navbar-right ul li>a,{
	color: #9d9d9d;
}

.navbar-right ul{
	background:#222222;
}

.navbar-right ul li>a:hover{
	background: #fff;
}

.foodtime>img{
	border:1px solid #b4b5b6;
	height: 50px;
}

.foodtime{
	font-size: 18px;
	color: #fff;
	font-weight: bold;
}

.foodtime:hover{
	color:#c1a35f;
	text-decoration: none;
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
			<a href="/AA105G3/front-end/index.jsp" class="foodtime"><img alt="FoodTime" src="<%=request.getContextPath()%>/images/Logo.png">分享食光</a>
		</div>
		<div class="collapse navbar-collapse" id="navbar-ex-collapse">
			<ul class="nav navbar-nav navbar-right mu-main-nav">
				<li >
					<a href="/AA105G3/front-end/index.jsp">首頁</a>
	            </li>
	            <li>
					<a href="/AA105G3/front-end/recipe/RecipeHome.jsp">食譜</a>
	            </li>
	            <!-- <li>
					<a href="#mu-video">影音</a>
	            </li> -->
	            <li>
					<a href="/AA105G3/front-end/chef/chefList2.jsp">私廚</a>
	            </li>
	            <!-- <li>
					<a href="#mu-stream">實況</a>
	            </li> -->
	            <li>
					<a href="/AA105G3/front-end/product/Market.jsp">市集</a>
	            </li>
	            <li>
					<a href="#mu-contact">聯絡我們</a>
	            </li>
	            <c:if test="${mem_name==null}">
		            <li>
						<a href="#">訪客</a>
		            </li>
		            <li>
						<a href="/AA105G3/front-end/member/MemberSignUp.jsp">註冊</a>
					</li>
					<li>
						<a href="/AA105G3/Login/Flogin.jsp">登入</a>
					</li>
	            </c:if>
				<c:if test="${mem_name!=null}">
		            <li>
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">${mem_name}<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="<%=request.getContextPath()%>/member/member.do?action=getOne_For_Update&mem_no=${mem_no}">編輯個人資訊</a></li>
							<li><a href="/AA105G3/front-end/member/memberInfo.jsp">個人頁面</a></li>
							<li><a href="<%=request.getContextPath()%>/member/member.do?action=signOut">登出</a></li>
						</ul>
					</li>
				</c:if>	
			</ul>
		</div>
	</div>
</div>


</body>
<!-- <script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
</html>