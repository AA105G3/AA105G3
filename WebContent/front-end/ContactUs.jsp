<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>聯絡我們</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel ="stylesheet" href="/AA105G3/css/contactCSS.css">

<style>
body{
	background: #efede8;
	padding-top: 90px;
	position : relative;
	height : 100%;
}

#theFooter{
	/* 對應skin */
	position : absolute;
	bottom : 0px;
	width : 100%;
	
	background: #222222;
	color:#fff ;
	font-size: 26px;
	font-family: Reklame;
	text-align: center;
}
</style>

</head>

<body>





<c:import url="/front-end/frontNavbar.jsp"></c:import>





<div class="container">
	<div class="row">





<!--聯絡我們區塊-->
	<section id="mu-contact">
		<div class="container mu-contact-bg" data-scroll="toggle(.scaleDownIn, .scaleDownOut) centerVertical once">
			<div class="row">
				<div class="col-xs-12 col-sm-8 contact-left">
					<div class="mu-contact-content">
						<form METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do">
							<div class="col-xs-12 col-sm-4">
								<div class="form-group">
			                        <label for="name">您的名字</label>
			                        <input type="text" class="form-control" id="name" name="name" placeholder="Name">
		                      	</div>
							</div>
							<div class="col-xs-12 col-sm-4">
								<div class="form-group">
			                        <label for="email">您的E-mail</label>
			                        <input type="text" class="form-control" id="email" name="email" placeholder="Email">
		                      	</div>
							</div>
							<div class="col-xs-12 col-sm-4">
								<div class="form-group">
			                        <label for="subject">標題</label>
			                        <input type="text" class="form-control" id="subject" name="subject" placeholder="Subject">
		                      	</div>
							</div>
							<div class="col-xs-12 col-sm-12">
								<div class="form-group message">
		                        	<label for="message">訊息</label>                        
		                        	<textarea class="form-control" id="message" name="message" cols="30" rows="10" placeholder="Your Message"></textarea>
		                   		 </div>
							</div>
							<div class="col-xs-12 col-sm-12 text-center">
								<button type="submit" class="mu-send-btn">送出訊息</button>
							</div>
							<input type="hidden" name="action" value="contact">
						</form>
					</div>
				</div>
				<div class="col-xs-12 col-sm-4 contact-right">
					<h1>聯絡資訊:</h1>
					<div id="phone"><img src="<%=request.getContextPath()%>/images/ContactUs/phone.png"><span class="contact-info">09xx-xxx-xxx</span></div>
					<div id="address"><img src="<%=request.getContextPath()%>/images/ContactUs/location.png"><span class="contact-info">中央大學</span></div>
					<div id="mail"><img src="<%=request.getContextPath()%>/images/ContactUs/mail.png"><span class="contact-info">foodtime@gmail.com</span></div>
	
				</div>
			</div>
		</div>
	</section>
<!--聯絡我們區塊-->





	</div>
</div>






<footer id="theFooter">
	Copyright &copy; 2016 Java Team 3 
</footer>




<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>