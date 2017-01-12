<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef.model.*"%>
<!DOCTYPE html>
<html lang="">
<%ChefVO chefVO=(ChefVO)request.getAttribute("chefVO"); %>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/AA105G3/css/BackChefPage_main.css">
    <script src=></script>
    <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    
    <style>
    	.chef_zone img{
    		height : 225px;
    		width:300px;
    	}
    </style>
    
</head>

<body>
    
    <nav>
    	<c:import url="/back-end/TopNavBar.jsp"></c:import>
    </nav>
    
    	<c:import url="/back-end/LeftNavBar.jsp"></c:import>
    
    
    <div class="col-xs-12 col-sm-10" id="chefInfo">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-12 text-center">
                    <h2>私廚詳情</h2>
                </div>
            </div>
        </div>
        <div class="col-xs-12 col-sm-6 col-sm-offset-3 chef_zone">
            <div>
                私廚編號:${chefVO.chef_no}
            </div>
            <div>
                會員編號:${chefVO.mem_no}
            </div>
            <div>
                真實姓名:${chefVO.chef_name}
            </div>
            <div>
                私廚審核情況:${chefVO.chef_chk_cond}
            </div>
            <div>
                匯款帳號銀行代碼:${chefVO.chef_bnk}
            </div>
            <div>
                匯款帳號:${chefVO.chef_bnk_ac}
            </div>
            <div>
                服務範圍:${chefVO.chef_area}
            </div>
            <div>
                擅長料理風格:${chefVO.chef_skill}
            </div>
            <div>
                私廚證照:<img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_lic=123">
            </div>
            <div>
                私廚照片:<img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_image=123">
            </div>
            <div>
                私廚簡介:${chefVO.chef_intr}
            </div>
            <div>
                參考菜單:${chefVO.chef_menu}
            </div>
            <div>
                參考菜單圖片一:<img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image1=123">
            </div>
            <div>
                參考菜單圖片二:<img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image2=123">
            </div>
            <div>
                參考菜單圖片三:<img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image3=123">
            </div>
            <div>
                參考菜單圖片四:<img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image4=123">
            </div>
            <div>
                參考菜單圖片五:<img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image5=123">
            </div>
            <div>
                上傳DEMO影片一:
                <video id="chef_movie1" controls>
                    <source src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_movie1=123">
                </video>
            </div>
            <div>
                上傳DEMO影片二:
                <video id="chef_movie2" controls>
                    <source src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_movie2=123" type="video/mp4">
                </video>
            </div>
            </div>
            	
            </div>
      
		      
        </div>
    </div>
    <!--     <footer id="the_footer">
        <p class="lightcolor">All Content Copyright &copy; 2016 TomCat Inc</p>
    </footer> -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script>
    	document.getElementById("delete_msg").style.display="none";
    
    	function reason(){
    		document.getElementById("delete_msg").style.display="";
    	}
    
    	document.getElementById("del_btn").onclick=reason;
    </script>
    <script>
    	
    </script>
</body>

</html>
