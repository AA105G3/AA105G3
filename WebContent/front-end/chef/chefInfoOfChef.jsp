<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef.model.*"%>
<%@ page import="com.member.model.*"%>

<%ChefVO chefVO2=(ChefVO)request.getAttribute("chefVO2"); %>
<%MemberVO memberVO=(MemberVO)request.getAttribute("memberVO"); %>

<%
    ChefService chefSvc = new ChefService();
	String mem_no = (String)(session.getAttribute("mem_no"));
	ChefVO chefVO = chefSvc.getOneChefByMem_no(mem_no);
	String chef_no = chefVO.getChef_no();
    pageContext.setAttribute("chefVO",chefVO);
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css">
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
	.shopping-cart{
		position: absolute;
		position: fixed;
		top: 40%;
		rightt: 25px;
		width: 250px;
		list-style: none;
		margin: 0;
		padding: 0;
		font-size: 16px;
	}
	.shopping-cart li{
		padding-bottom: 15px;
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
	body{
		background: #efede8;
		padding-top: 50px;
		position : relative;
		height : 100%;
	}
    </style>
</head>

<body>
    
    <header>
    	<c:import url="/front-end/frontNavbar.jsp"></c:import>
    </header>
    
    <section id="top-img">
        <div class="col-xs-12 col-sm-12">
            <div class="top-img"></div>

        </div>
    </section>
    
    <div class="container">
        <div class="row">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 text-center">
                        <h2>私廚個人資訊</h2>
                    </div>
                </div>
            </div>
            <br>
            
     		<div class="col-xs-12 col-sm-12">
            
            <div class="col-xs-12 col-sm-10 col-sm-offset-1" id="chefInfo_zone">
                <div class="row" style="border-bottom: 2px solid #d3d4d5">
                    <div class="col-xs-12 col-sm-4 col-sm-offset-4" >
                        <div class="thumbnail">
                            <img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_image=123" class="img-circle" style="width:150px;height:200px">
                            <div class="caption">
                                <h3 align="center">${chefVO.chef_name}</h3>
                                <p></p>
                            </div>
                        </div>
                    </div>

                </div>
                <br>

                <div class="row" style="border-bottom: 2px solid #d3d4d5;">
                    <div class="col-xs-12 col-sm-4">
                        <h3 style="margin-top:0px"><b>服務地區</b></h3>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <b style="font-size:12pt">${chefVO.chef_area}</b>                    
                    </div>
                </div>
                <br>
                <div class="row" style="border-bottom: 2px solid #d3d4d5">
                    <div class="col-xs-12 col-sm-4">
                        <h3 style="margin-top:0px"><b>擅長料理風格</b></h3>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <b style="font-size:12pt">${chefVO.chef_skill}</b>                    
                    </div>
                </div>
                <br>
                <div class="row" style="border-bottom: 2px solid #d3d4d5">
                    <div class="col-xs-12 col-sm-4">
                        <h3 style="margin-top:0px"><b>私廚簡介</b></h3>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <b style="font-size:12pt">${chefVO.chef_intr}</b>                    
                    </div>
                </div>
                <br>
                <div class="row" style="border-bottom: 2px solid #d3d4d5">
                    <div class="col-xs-12 col-sm-4">
                        <h3 style="margin-top:0px"><b>參考菜單(建議標上價格)</b></h3>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <b style="font-size:12pt">${chefVO.chef_menu}</b>
                    </div>
                </div>
            </div>
            <br>
            <div class="col-xs-12 col-sm-12 text-center">
                <h3>參考圖片</h3>
            </div>
            <br>
            <div class="row" id="img_zone">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1">
                    <div class="col-xs-12 col-sm-6" style="padding:1px">
                        <img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image1=123" class="img-responsive" style="width:472px;height:315px">
                    </div>
                    <div class="col-xs-12 col-sm-6" style="padding:1px">
                        <img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image2=123" class="img-responsive" style="width:472px;height:315px">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-10 col-sm-offset-1">
                    <div class="col-xs-12 col-sm-4" style="padding:1px">
                        <img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image3=123" class="img-responsive" style="width:310px;height:206px" >
                    </div>
                    <div class="col-xs-12 col-sm-4" style="padding:1px">
                        <img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image4=123" class="img-responsive" style="width:310px;height:206px">
                    </div>
                    <div class="col-xs-12 col-sm-4" style="padding:1px">
                        <img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image5=123" class="img-responsive" style="width:310px;height:206px">
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-12 text-center">
            	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef/chef.do" align>
			     	<input type="submit" value="修改">
			     	<input type="hidden" name="chef_no" value="${chefVO.chef_no}">
			     	<input type="hidden" name="action"	value="getOne_For_Update">
				</FORM>
            </div>
            	<div class="btn btn-danger btn-xs"><a href="<%=request.getContextPath()%>/chef_order_list/chef_order_list.do?action=getChefNo_For_ViewOrd&chef_no=${chefVO.chef_no}">瀏覽訂單</a></div>
            </div>
                        
			
			
			

        </div>
    </div>
    <c:import url="/front-end/chat/inviteChat.jsp" ></c:import>
    <footer id="theFooter">
		Copyright &copy; 2016 Java Team 3 
	</footer>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript">
    $('#chef_act_date').datepicker({
        format: "yyyy/mm/dd",
        startDate: '+3d',
        endDate: '+1m +3d',
        maxViewMode: 0,
        todayBtn: "linked",
        clearBtn: true,
//         daysOfWeekDisabled: "0,6",
        orientation: "bottom right"

    });
    </script>
</body>

</html>
