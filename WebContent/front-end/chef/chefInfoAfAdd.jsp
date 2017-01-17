<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef.model.*"%>
<!DOCTYPE html>
<html lang="">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>frontpage-chef</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/AA105G3/css/frontpageCSS.css">
    <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    <style type="text/css" media="screen">
    .first-col {
        padding-top: 25px;
        padding-bottom: 50px;
    }
    
    .first-col img {
        width: 350px;
        height: 250px;
    }
    
    .next-col {
        padding-bottom: 50px;
    }
    
    .next-col img {
        width: 350px;
        height: 250px;
    }
    
    #top-img {
        background-image: url(/AA105G3/images/chef/chef_title.jpg);
        height: 500px;
        /* background-size: cover; */
        background-position: center;
    }
    
    .front-style {
        background: #f5deb3;
        width: 175px;
    }
    
    .btn-style {
        background: #f5deb3;
        width: 175px;
        text-align: center;
        padding-top: 18px;
        padding-bottom: 18px;
    }
	html,body{
				background: #efede8;
				
				height : 100%;
				
			}
			.page{
				min-height:100%;
				   position: relative;
				   padding-top:50px;
				   padding-bottom:50px;
			}
	#theFooter{
				position : absolute;
				bottom : 0px;
				width : 100%;
				
				background: #222222;
				color:#fff ;
				font-size: 26px;
				font-family: Reklame;
				text-align: center;
			}
	#img_zone img{
    width:100%;
    height:100%;
	}
	#img_zone div{
    padding: 1px;
	}
	.chef_zone img{
    		height : 225px;
    		width:300px;
    }
    .successInfo{
    	width:600px;
    	height:250px;
    	margin:100px auto;
    	font-size:24px;
    	padding-top:80px;
    	border:1px solid;
    	background:#fff;
    }
    .successInfo p{
    	margin-top:10px;
    	font-size:16px;
    }
    .successInfo a{
    	font-size:20px;
    	font-weight:bold;
    }
    </style>
</head>

<body>
   	<div class="page">
    <header>
    	<c:import url="/front-end/frontNavbar.jsp"></c:import>
    </header>
    
    
	<div class="container">
	<div class="row ">
	<div class="text-center">
		<div class="successInfo ">
			您的申請已送出!我們會盡快完成審核。
		<p>請按<a href="/AA105G3/front-end/chef/chefList2.jsp">這裡</a>跳轉私廚首頁。</p>
		</div>
	</div>
	
		
	
	</div>
	</div>

    <footer id="theFooter">
		Copyright &copy; 2016 Java Team 3 
	</footer>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</div>
</body>

</html>
