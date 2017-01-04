<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>

<html>
<head>

<title>會員註冊</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/AA105G3/css/frontpageCSS.css">
<script src = "/AA105G3/js/memberSignUp.js"></script>

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
	.title-style{
		padding-top : 50px;
	}
	.list-style{
		padding-top : 75px;
		padding-left : 150px;
	}
	.div-style{
		padding-top : 25px;
	}
	.span-style{
		padding-top : 32px;
	}
	.buttonStyle{
		padding-top : 25px;
		padding-right : 150px;
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




<div class="col-xs-12 col-sm-12 text-center title-style">
	<h1>會員註冊</h1>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li>${message}</li>
			</c:forEach>
		</ul>
		</font>
	</c:if>
</div>



<form METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" name="form1" enctype="multipart/form-data">
	<div class="container">
		<div class="row">
			<div class="list-style">

<div class="col-xs-12 col-sm-12">
	<div class="col-xs-12 col-sm-6 div-style">
		<div class="input-group">
			<div class="input-group-addon">
				會員帳號
			</div>
			<input type="text" id="memAc" placeholder="請輸入帳號" class="form-control"
			name="mem_ac" value="${memberVO==null ? 'FoodTime' : memberVO.mem_ac}">
		</div>
	</div>
	<div class="col-xs-12 col-sm-6 span-style">
		<span id="spanAc"></span>
	</div>
</div>

<div class="col-xs-12 col-sm-12">
	<div class="col-xs-12 col-sm-6 div-style">
		<div class="input-group">
			<div class="input-group-addon">
				會員暱稱
			</div>
			<input type="text" id="memName" placeholder="請輸入暱稱" class="form-control" 
			name="mem_name" value="${memberVO==null ? '新的會員' : memberVO.mem_name}">
		</div>
	</div>
	<div class="col-xs-12 col-sm-6 span-style">
		<span id="spanName"></span>
	</div>
</div>

<div class="col-xs-12 col-sm-12">
	<div class="col-xs-12 col-sm-6 div-style">
		<div class="input-group">
			<div class="input-group-addon">
				會員密碼
			</div>
			<input type="password" id="memPw1" placeholder="請輸入密碼" class="form-control" maxlength="12"
			name="mem_pw" value="${memberVO==null ? 'foodtime123' : memberVO.mem_pw}" >
		</div>
	</div>
	<div class="col-xs-12 col-sm-6 span-style">
		<span id="spanPw1"></span>
	</div>
</div>

<div class="col-xs-12 col-sm-12">
	<div class="col-xs-12 col-sm-6 div-style">
		<div class="input-group">
			<div class="input-group-addon">
				確認密碼
			</div>
			<input type="password" id="memPw2" placeholder="請再次輸入密碼" class="form-control" maxlength="12">
		</div>
	</div>
	<div class="col-xs-12 col-sm-6 span-style">
		<span id="spanPw2"></span>
	</div>
</div>

<div class="col-xs-12 col-sm-12">
	<div class="col-xs-12 col-sm-12 div-style">
		<div class="input-group">
			性別：
			<label class="radio-inline">
				<input type="radio" name="mem_sex" value="1">男
			</label>
			<label class="radio-inline">
				<input type="radio" name="mem_sex" value="0">女
			</label>
		</div>
	</div>
</div>

<div class="col-xs-12 col-sm-12">
	<div class="col-xs-12 col-sm-6 div-style">
		<div class="input-group">
			<div class="input-group-addon">
				電子郵件
			</div>
			<input type="text" id="memEmail" placeholder="請輸入信箱" class="form-control"
			name="mem_email" value="${memberVO==null ? 'foodtime@gmail.com' : memberVO.mem_email}">
		</div>
	</div>
	<div class="col-xs-12 col-sm-6 span-style">
		<span id="spanEmail"></span>
	</div>
</div>

<div class="col-xs-12 col-sm-12">
	<div class="col-xs-12 col-sm-6 div-style">
		<div class="input-group">
			<div class="input-group-addon">
				手機
			</div>
			<input type="text" id="memPhone" placeholder="請輸入手機號碼" class="form-control"
			name="mem_phone" value="${memberVO==null ? '0963258741' : memberVO.mem_phone}">
		</div>
	</div>
	<div class="col-xs-12 col-sm-6 span-style">
		<span id="spanPhone"></span>
	</div>
</div>

<div class="col-xs-12 col-sm-12">
	<div class="col-xs-12 col-sm-6 div-style">
		<div class="input-group">
			<div class="input-group-addon">
				住址
			</div>
			<input type="text" id="memAdrs" placeholder="請輸入住址" class="form-control"
			name="mem_adrs" value="${memberVO==null ? '太陽系第三行星-地球' : memberVO.mem_adrs}">
		</div>
	</div>
</div>

<div class="col-xs-12 col-sm-12">
	<div class="col-xs-12 col-sm-6 div-style">
		<div class="input-group">
			申請私廚資格：
			<label class="radio-inline">
				<input type="radio" name="mem_own" value="0" checked>不申請
			</label>
			<label class="radio-inline">
				<input type="radio" name="mem_own" value="1">我要申請
			</label>
		</div>
	</div>
	<div class="col-xs-12 col-sm-6 span-style">
		<span id="spanOwn"></span>
	</div>
</div>

<div class="col-xs-12 col-sm-12">
	<div class="col-xs-12 col-sm-6 div-style">
		<div class="input-group">
			上傳會員照片：<input accept="image/*" id="uploadImage" name="mem_image" type="file">
		</div>
		<span id="spanImage"></span>
		<div id = "fileInfo"></div>
	</div>
	<div class="col-xs-12 col-sm-6 span-style">
		<br><img id="img" src="<%=request.getContextPath()%>/front-end/member/images/No-image-found.png" height='150'><br>
	</div>
</div>


<div class="col-xs-12 col-sm-12 text-center">
	<div class="col-xs-12 col-sm-12 buttonStyle">
		<input type="button" value="取消">&nbsp;
		<input type="reset" value="重置" >&nbsp;
		<input type="submit" value="確定">
		<input type="hidden" name="action" value="insert">
		<input type="hidden" name="mem_history" value="F00000001">
		<input type="hidden" name="mem_online" value="1">
	</div>
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