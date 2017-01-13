<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>FoodTime</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<!-- <script src="js/modernizr.custom.js"></script> -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<style type="text/css">
			/* @font-face {
				font-family: 'anyoldicon';
				src:url('../fonts/anyoldicon/anyoldicon.eot');
				src:url('../fonts/anyoldicon/anyoldicon.eot?#iefix') format('embedded-opentype'),
					url('../fonts/anyoldicon/anyoldicon.woff') format('woff'),
					url('../fonts/anyoldicon/anyoldicon.ttf') format('truetype'),
					url('../fonts/anyoldicon/anyoldicon.svg#anyoldicon') format('svg');
				font-weight: normal;
				font-style: normal;
			} */

			/* General grid styles */
			.cbp-ig-grid {
				list-style: none;
				padding: 0 0 0 0;
				margin: 0;
			}

			/* Clear floats */
			.cbp-ig-grid:before, 
			.cbp-ig-grid:after { 
				content: " "; 
				display: table; 
			}

			.cbp-ig-grid:after { 
				clear: both; 
			}

			/* grid item */
			.cbp-ig-grid li {
				width: 33%;
				float: left;
				height: 420px;
				text-align: center;
				border-top: 1px solid #ddd;
			}

			/* we are using a combination of borders and box shadows to control the grid lines */
			.cbp-ig-grid li:nth-child(-n+3){
				border-top: none;
			}

			.cbp-ig-grid li:nth-child(3n-1),
			.cbp-ig-grid li:nth-child(3n-2) {
				box-shadow: 1px 0 0 #ddd;
			}

			/* anchor style */
			.cbp-ig-grid li.odd > a {
				display: block;
				height: 100%;
				color: #fff;
				background: #e3b13e;
				-webkit-transition: background 0.2s;
				-moz-transition: background 0.2s;
				transition: background 0.2s;
			}
			.cbp-ig-grid li.even > a {
				display: block;
				height: 100%;
				color: #e3b13e;
				-webkit-transition: background 0.2s;
				-moz-transition: background 0.2s;
				transition: background 0.2s;
				background: #fff;
			}

			.cbp-ig-grid li#Logo > a {
				display: block;
				height: 100%;
				color: #e3b13e;
				-webkit-transition: background 0.2s;
				-moz-transition: background 0.2s;
				transition: background 0.2s;
				background: #fff;
			}

			/* the icon with pseudo class for icon font */
			.cbp-ig-icon {
				padding: 60px 0 10px 0;
				display: block;

				-webkit-transition: -webkit-transform 0.2s;
				transition: -moz-transform 0.2s;
				transition: transform 0.2s;
				margin-bottom: 40px;
			}

			.cbp-ig-icon:before {
				font-size: 12em;
				speak: none;
				font-style: normal;
				font-weight: normal;
				font-variant: normal;
				text-transform: none;
				line-height: 1;
				-webkit-font-smoothing: antialiased;
				

			}

			.cbp-ig-icon-shoe:before {
				content: "\e000";
			}

			.cbp-ig-icon-ribbon:before {
				content: "\e001";
			}

			.cbp-ig-icon-milk:before {
				content: "\e002";
			}

			.cbp-ig-icon-whippy:before {
				content: "\e003";
			}

			.cbp-ig-icon-spectacles:before {
				content: "\e004";
			}

			.cbp-ig-icon-doumbek:before {
				content: "\e007";
			}

			/* title element */
			.cbp-ig-grid .cbp-ig-title {
				margin: 20px 0 10px 0;
				padding: 20px 0 0 0;
				font-size: 3em;
				position: relative;
				-webkit-transition: -webkit-transform 0.2s;
				-moz-transition: -moz-transform 0.2s;
				transition: transform 0.2s;
			}

			.cbp-ig-grid li#Logo .cbp-ig-title {
				margin: 0px 0 30px 0;
				padding: 10px 0 0 0;
				font-size: 3em;
				position: relative;
				-webkit-transition: -webkit-transform 0.2s;
				-moz-transition: -moz-transform 0.2s;
				transition: transform 0.2s;
			}

			.cbp-ig-grid li.odd .cbp-ig-title:before {
				content: '';
				position: absolute;
				background: #fff;
				width: 180px;
				height: 6px;
				top: 0px;
				left: 50%;
				margin: -10px 0 0 -90px;
				-webkit-transition: margin-top 0.2s; /* top or translate does not seem to work in Firefox */
				-moz-transition: margin-top 0.2s;
				transition: margin-top 0.2s;
			}

			.cbp-ig-grid li.even .cbp-ig-title:before {
				content: '';
				position: absolute;
				background: #e3b13e;
				width: 180px;
				height: 6px;
				top: 0px;
				left: 50%;
				margin: -10px 0 0 -90px;
				-webkit-transition: margin-top 0.2s; /* top or translate does not seem to work in Firefox */
				-moz-transition: margin-top 0.2s;
				transition: margin-top 0.2s;
			}
			.cbp-ig-grid li#Logo .cbp-ig-title:before {
				content: '';
				position: absolute;
				background: #e3b13e;
				width: 220px;
				height: 6px;
				top: 50px;
				left: 50%;
				margin: 10px 0 0 -110px;
				
			}

			.cbp-ig-grid li.odd .cbp-ig-category {
				text-transform: uppercase;
				display: inline-block;
				font-size: 20px;
				letter-spacing: 1px;
				font-weight: bold;
				color: #fff;
				-webkit-transform: translateY(10px);
				-moz-transform: -moz-translateY(10px);
				-ms-transform: -ms-translateY(10px);
				transform: translateY(10px);
				opacity: 0;
				-webkit-transition: -webkit-transform 0.3s, opacity 0.2s;
				-moz-transition: -moz-transform 0.3s, opacity 0.2s;
				-webkit-transition: transform 0.3s, opacity 0.2s;
			}
			.cbp-ig-grid li.even .cbp-ig-category {
				text-transform: uppercase;
				display: inline-block;
				font-size: 20px;
				letter-spacing: 1px;
				color: #fff;
				-webkit-transform: translateY(10px);
				-moz-transform: -moz-translateY(10px);
				-ms-transform: -ms-translateY(10px);
				transform: translateY(10px);
				opacity: 0;
				-webkit-transition: -webkit-transform 0.3s, opacity 0.2s;
				-moz-transition: -moz-transform 0.3s, opacity 0.2s;
				-webkit-transition: transform 0.3s, opacity 0.2s;
				font-weight: bold;
			}

			.cbp-ig-grid li#Logo .cbp-ig-category {
				
				display: inline-block;
				font-size: 28px;
				letter-spacing: 1px;
				color: #e3b13e;
				padding:5px 10px;
				border: 3px dashed #e3b13e;
				-webkit-transition: -webkit-background 0.5s,-webkit-border 0.5s,-webkit-color 0.5s,-webkit-padding 0.5s;
				-moz-transition: -moz-background 0.5s,-moz-border 0.5s,-moz-color 0.5s,-moz-padding 0.5s;
				-webkit-transition: background 0.5s,border 0.5s,color 0.5s,padding 0.5s;
				transition: background 0.5s,color 0.5s,padding 0.5s;
				
				/*opacity: 1;*/
				
			}
			.cbp-ig-grid li#Logo .homepagelogout {
				
				display: inline-block;
				font-size: 28px;
				letter-spacing: 1px;
				color: #fff;
				background: #e3b13e;
				padding:5px 10px;
				border: 3px dashed #fff;
				-webkit-transition: -webkit-background 0.5s,-webkit-border 0.5s,-webkit-color 0.5s,-webkit-padding 0.5s;
				-moz-transition: -moz-background 0.5s,-moz-border 0.5s,-moz-color 0.5s,-moz-padding 0.5s;
				-webkit-transition: background 0.5s,border 0.5s,color 0.5s,padding 0.5s;
				transition: background 0.5s,color 0.5s,padding 0.5s;
				
				opacity: 1;
				
			}

			.cbp-ig-grid li.odd:hover .cbp-ig-category,
			.touch .cbp-ig-grid li.odd .cbp-ig-category,
			.cbp-ig-grid li.even:hover .cbp-ig-category,
			.touch .cbp-ig-grid li.even .cbp-ig-category {
				opacity: 1;
				-webkit-transform: translateY(0px);
				-moz-transform: translateY(0px);
				-ms-transform: translateY(0px);
				transform: translateY(0px);
			}

			/* Hover styles */

		 	 .cbp-ig-grid li.even > a:hover {
				background: #edcc81;
				color:#edcc81;
				text-decoration: none;
			} 
			/* .cbp-ig-grid li.even > a:link {
				background: #edcc81;
				color:#e3b13e;
				text-decoration: none;
			}  */

			.cbp-ig-grid li.odd > a:hover {
				background: #edcc81;
				color:#edcc81;
				text-decoration: none;
			}
			/* .cbp-ig-grid li.odd > a:link {
				background: #edcc81;
				color:#fff;
				text-decoration: none;
			} */
			.cbp-ig-grid li.odd > a:hover .cbp-ig-icon {
				-webkit-transform: translateY(10px);
				-moz-transform: translateY(10px);
				-ms-transform: translateY(10px);
				transform: translateY(10px);
				
			}
			.cbp-ig-grid li.even > a:hover .cbp-ig-icon {
				-webkit-transform: translateY(10px);
				-moz-transform: translateY(10px);
				-ms-transform: translateY(10px);
				transform: translateY(10px);
				
			}

			.cbp-ig-grid li#Logo > a .cbp-ig-icon {
				/*-webkit-transform: translateY(10px);
				-moz-transform: translateY(10px);
				-ms-transform: translateY(10px);
				transform: translateY(10px);*/
				
			}




			.cbp-ig-grid li.even > a:hover .cbp-ig-icon:before,
			.cbp-ig-grid li.even > a:hover .cbp-ig-title {

				color: #fff;
			}

			.cbp-ig-grid li.odd > a:hover .cbp-ig-icon:before,
			.cbp-ig-grid li.odd > a:hover .cbp-ig-title {
				color: #fff;

			}

			.cbp-ig-grid li.even > a:hover .cbp-ig-title {
				-webkit-transform: translateY(-30px);
				-moz-transform: translateY(-30px);
				-ms-transform: translateY(-30px);
				transform: translateY(-30px);
			}
			.cbp-ig-grid li.odd > a:hover .cbp-ig-title {
				-webkit-transform: translateY(-30px);
				-moz-transform: translateY(-30px);
				-ms-transform: translateY(-30px);
				transform: translateY(-30px);


			}


			.cbp-ig-grid li.odd > a:hover .cbp-ig-title:before {
				background: #fff;
				margin-top: 80px;

			}

			.cbp-ig-grid li.even > a:hover .cbp-ig-title:before {
				background: #fff;
				margin-top: 80px;


			}
			.cbp-ig-grid li#Logo > a:hover{
				text-decoration: none;
				color:#e3b13e;
			}
			.cbp-ig-grid li#Logo > a:link{
				text-decoration: none;
				color:#e3b13e;
			}
			.cbp-ig-grid li#Logo > a:hover .cbp-ig-category{
				border: 2px dashed #fff;
				background: #e3b13e;
				color:#fff;
				padding:5px 20px;
				text-decoration: none;
				-webkit-transition: -webkit-background 0.5s,-webkit-border 0.5s,-webkit-color 0.5s,-webkit-padding 0.5s;
				-moz-transition: -moz-background 0.5s,-moz-border 0.5s,-moz-color 0.5s,-moz-padding 0.5s;
				-webkit-transition: background 0.5s,border 0.5s,color 0.5s,padding 0.5s;
				transition: background 0.5s,border 0.5s,color 0.5s,padding 0.5s;
				
			}
			
			.cbp-ig-grid li#Logo > a:hover .homepagelogout{
				border: 2px dashed #e3b13e;
				background: #fff;
				color:#e3b13e;
				padding:5px 20px;
				text-decoration: none;
				-webkit-transition: -webkit-background 0.5s,-webkit-border 0.5s,-webkit-color 0.5s,-webkit-padding 0.5s;
				-moz-transition: -moz-background 0.5s,-moz-border 0.5s,-moz-color 0.5s,-moz-padding 0.5s;
				-webkit-transition: background 0.5s,border 0.5s,color 0.5s,padding 0.5s;
				transition: background 0.5s,border 0.5s,color 0.5s,padding 0.5s;
				
			}
			
			
			#Logo img{
				margin-top: 50px;
				margin-bottom: 10px;
				width: 250px;
				height: 220px;

			}

			@media screen and (max-width: 62.75em) {
				.cbp-ig-grid li {
					width: 50%;
				}

				/* reset the grid lines */
				.cbp-ig-grid li:nth-child(-n+3){
					border-top: 1px solid #ddd;
				}

				.cbp-ig-grid li:nth-child(3n-1),
				.cbp-ig-grid li:nth-child(3n-2) {
					box-shadow: none;
				}

				.cbp-ig-grid li:nth-child(-n+2){
					border-top: none;
				}

				.cbp-ig-grid li:nth-child(2n-1) {
					box-shadow: 1px 0 0 #ddd;
				}
			}

			@media screen and (max-width: 41.6em) { 
				.cbp-ig-grid li {
					width: 100%;
				}

				.cbp-ig-grid li:nth-child(-n+2){
					border-top: 1px solid #ddd;
				}

				.cbp-ig-grid li:nth-child(2n-1) {
					box-shadow: none
				}

				.cbp-ig-grid li:first-child {
					border-top: none;
				}
			}

			@media screen and (max-width: 25em) { 
				.cbp-ig-grid {
					font-size: 80%;
				}

				.cbp-ig-grid .cbp-ig-category {
					margin-top: 20px;
				}
			}
		</style>
	</head>
	<body>
		
				
			<div class="main">
				<ul class="cbp-ig-grid">
					<li class="odd">
						<a href="/AA105G3/front-end/recipe/RecipeHome.jsp">
							<i class="fa fa-book cbp-ig-icon" aria-hidden="true"></i>
							<h3 class="cbp-ig-title">Recipe</h3>
							<span class="cbp-ig-category">逛食譜</span>
						</a>
					</li>
					<li id="Logo">
					<c:if test="${sessionScope.mem_no ==null}">
						<a href="/AA105G3/Login/Flogin.jsp">
						<img src="<%=request.getContextPath()%>/images/Logo.png">
							
							<h3 class="cbp-ig-title">－FoodTime－</h3>
						
							<span class="cbp-ig-category">Login</span>
						</a>
					</c:if>
					<c:if test="${sessionScope.mem_no !=null}">
						<a href="<%=request.getContextPath()%>/member/member.do?action=signOut">
						<img src="<%=request.getContextPath()%>/images/Logo.png">
							
							<h3 class="cbp-ig-title">－FoodTime－</h3>
						
							<span class="cbp-ig-category homepagelogout">Logout</span>
						</a>
					</c:if>
						
					</li>
					<li class="odd">
						<a href="/AA105G3/front-end/product/Market.jsp">
							<!-- <i class="fa fa-shopping-bag cbp-ig-icon" aria-hidden="true"></i> -->
							<i class="fa fa-shopping-basket cbp-ig-icon" aria-hidden="true"></i>
							<!-- <i class="fa fa-cart-plus cbp-ig-icon" aria-hidden="true"></i> -->
							<!-- <i class="fa fa-shopping-cart cbp-ig-icon" aria-hidden="true"></i> -->
							<!-- <i class="fa fa-cart-arrow-down cbp-ig-icon" aria-hidden="true"></i> -->
							<h3 class="cbp-ig-title">Market</h3>
							<span class="cbp-ig-category">購物</span>
						</a>
					</li>
					<li class="even">
						<a href="/AA105G3/front-end/ContactUs.jsp">
							<i class="fa fa-paper-plane-o cbp-ig-icon" aria-hidden="true"></i>
							<h3 class="cbp-ig-title">Contact Us</h3>
							<span class="cbp-ig-category">聯絡我們</span>
						</a>
					</li>
					<li class="odd">
						<a href="/AA105G3/front-end/chef/chefList2.jsp">
							<i class="fa fa-spoon cbp-ig-icon" aria-hidden="true"></i>
							<h3 class="cbp-ig-title">Chef</h3>
							<span class="cbp-ig-category">找私廚</span>
						</a>
					</li>
					<li class="even">
					<c:if test="${sessionScope.mem_no ==null}">

						 <a href="/AA105G3/front-end/member/MemberSignUp.jsp">
							<span class="cbp-ig-icon glyphicon glyphicon-registration-mark"></span>
							<h3 class="cbp-ig-title">Register</h3>
							<span class="cbp-ig-category">立即註冊</span>
						</a>
					</c:if>
					<c:if test="${sessionScope.mem_no !=null}">
						<a href="/AA105G3/front-end/member/memberInfo.jsp">
							<span class="fa fa-user cbp-ig-icon" aria-hidden="true"></span>
							<h3 class="cbp-ig-title">Profile</h3>
							<span class="cbp-ig-category">前往個人頁面</span>
						</a>
					</c:if>
					</li>
				</ul>
			</div>
		
		<c:import url="/front-end/chat/inviteChat.jsp" ></c:import>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$(window).ready(function(){
				$('.cbp-ig-grid li').height(($(window).height())/2);
			})

		</script>
	</body>
</html>