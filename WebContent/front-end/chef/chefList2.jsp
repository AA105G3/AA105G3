<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef.model.*"%>

<%
    ChefService chefSvc = new ChefService();
    List<ChefVO> list = chefSvc.getAll();
    pageContext.setAttribute("list",list);
    session.setAttribute("mem_no","M00000001");
%>

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
/*         width: 175px; */
    }
    
    .btn-style {
        background: #f5deb3;
        width: 175px;
        text-align: center;
        padding-top: 18px;
        padding-bottom: 18px;
    }
    .shopping-cart{
		position: absolute;
		position: fixed;
		top: 40%;
		left: 25px;
		width: 250px;
		list-style: none;
		margin: 0;
		padding: 0;
		font-size: 16px;
	}
	.shopping-cart li{
		padding-bottom: 15px;
	}
	.chefInfo{
		background: #f5deb3;
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
    <div class="navbar navbar-default navbar-fixed-top navbar-inverse mu-main-navbar">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="#home" class="foodtime"><img alt="FoodTime" src="/AA105G3/images/Logo.png">分享食光</a>
            </div>
            <div class="collapse navbar-collapse" id="navbar-ex-collapse">
                <ul class="nav navbar-nav navbar-right mu-main-nav">
                    <li>
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
    <section id="top-img">
        <div class="col-xs-12 col-sm-12">
            <div class="top-img"></div>
            <!-- <img src="/AA105G3/images/title.jpg" height="500px" width="100%"> -->
        </div>
    </section>
    <ol class="breadcrumb">
        <li>
            <a href="#">成為私廚</a>
        </li>
    </ol>
    
    <c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
    
    
    
<div class="container">
	<div class="row">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12 text-center">
					<h2>精選私廚</h2>
				</div>
			</div>
		</div>
	            
	            
	            
		<c:forEach var="chefVO" items="${list}" varStatus="s" step="3">    
		
		
		        
			<div class="col-xs-12 col-sm-4 first-col">
				<a href="<%=request.getContextPath()%>/chef/chef.do?action=getOne_For_Display&chef_no=${chefVO.chef_no}">
				<img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_image=123" class="img-responsive" style="margin:0px"></a>
				<div class="col-xs-12 col-sm-12 front-style" style="width:350px;height:">
					<h4>${chefVO.chef_name}</h4>
					<p>服務範圍:${chefVO.chef_area}</p>
					<p>擅長料理風格:${chefVO.chef_skill}</p>
					
					<a href="<%=request.getContextPath()%>/chef/chef.do?action=getOne_For_Display&chef_no=${chefVO.chef_no}">	
						<div class="btn btn-primary" style="margin-bottom:15px">了解詳情</div>
					</a>
											
				</div>									
			</div>
						
						
						
						
						
		<c:if test="${s.index+1<list.size()}">
			<div class="col-xs-12 col-sm-4 first-col">
				<a href="<%=request.getContextPath()%>/chef/chef.do?action=getOne_For_Display&chef_no=${list.get(s.index+1).chef_no}">
				<img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${list.get(s.index+1).chef_no}&chef_image=123" class="img-responsive"></a>
				<div class="col-xs-12 col-sm-12 front-style" style="width:350px">
					<h4>${list.get(s.index+1).chef_name}</h4>
					<p>服務範圍:${list.get(s.index+1).chef_area}</p>
					<p>擅長料理風格:${list.get(s.index+1).chef_skill}</p>
					<a href="<%=request.getContextPath()%>/chef/chef.do?action=getOne_For_Display&chef_no=${list.get(s.index+1).chef_no}">
						<div class="btn btn-primary" style="margin-bottom:15px">了解詳情</div>
					</a>	
				</div>					
			</div>
		</c:if>
					
					
					
					
					
		<c:if test="${s.index+2<list.size()}">
			<div class="col-xs-12 col-sm-4 first-col">
				<a href="<%=request.getContextPath()%>/chef/chef.do?action=getOne_For_Display&chef_no=${list.get(s.index+2).chef_no}">
				<img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${list.get(s.index+2).chef_no}&chef_image=123" class="img-responsive"></a>
				<div class="col-xs-12 col-sm-12 front-style" style="width:350px">
					<h4>${list.get(s.index+2).chef_name}</h4>
					<p>服務範圍:${list.get(s.index+2).chef_area}</p>
					<p>擅長料理風格:${list.get(s.index+2).chef_skill}</p>						
					<a href="<%=request.getContextPath()%>/chef/chef.do?action=getOne_For_Display&chef_no=${list.get(s.index+2).chef_no}">
						<div class="btn btn-primary" style="margin-bottom:15px">了解詳情</div>
					</a>
				</div>
			</div>
		</c:if>
	                
	                
	                
		</c:forEach>

	</div>
</div>
    
    <footer>
        Copyright &copy; 2016 Java Team 3
    </footer>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>

</html>
