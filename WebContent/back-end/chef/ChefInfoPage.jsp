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
    <title>私廚資訊</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/AA105G3/css/BackChefPage_main.css">
    <script src=></script>
    <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    
    <style>
    	table{
    		margin:40px auto 20px auto;
    	}
    	.chef_image-wrapper img{
    		width: 250px;
			height: 250px;
    	}
    	.chef_lic-wrapper img{
			width: 590px;
			height: 250px;
		}
		.menu-img-top{
			padding: 0px 1px 0px 1px;
		}
		.menu-img-top img{
			height: 250px;
			width: 300px;
		}
		.menu-img-bottom{
			padding:3px 1px;
		}
		.menu-img-bottom img{
			height: 160px;
			width: 200px;
		}
		td.chef-video-wraper{
			padding: 1px;
		}
		td.chef-video-wraper video{
			height: 300px;
			width: 590px;
			border:1px solid;
		}
		form{
			display:inline;
		}
		td.submit-wrapper{
			width:300px;
			text-align:right;
			padding-right:10px;
			padding-top:10px;
		}
		td.cancel-wrapper{
			text-align:left;
			padding-top:10px;
		}
		td.info{
			font-size:18px;
		}
		td.gobackWrapper{
			padding:10px;
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
       <div class="col-xs-12 col-sm-12">
        <table>
	        <tr>
	        	<td class="info">
					<div class="form-group ">
	                    <label class="control-label">會員編號：</label>
	                    <div>${chefVO.mem_no}</div>
	                </div>
				</td>
	    		
				<td rowspan="4" class="chef_image-wrapper">
					<img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_image=123" class="img-circle">
				</td>
	        </tr>
	         <tr>
				<td class="info">
	    			<div class="form-group ">
	                   <label class="control-label">私廚編號：</label>
	                   <div>${chefVO.chef_no}</div>
	               </div>
				</td>
			</tr>
	        <tr>
				<td class="info">
					<div class="form-group ">
	                    <label class="control-label">真實姓名：</label>
	                    <div>${chefVO.chef_name}</div>
	                </div>
				</td>
			</tr>
			<tr>
				<td class="info">
					<div class="form-group ">
	                    <label class="control-label">匯款帳戶：</label>
	                    <div>${chefVO.chef_bnk}-${chefVO.chef_bnk_ac}</div>
	                </div>
				</td>
			</tr>
	  		<tr>
				<td colspan="2" class="info">
					<div id="chef_area">
	                       <label class=" control-label">願意服務的地區：</label>${chefVO.chef_area}
	                </div>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="info">
					<div id="chef_skill">
	                      <label class=" control-label">擅長的料理風格：</label>${chefVO.chef_skill}
	                </div>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="info">
					<div id="chef_skill">
	                      <label class=" control-label">私廚簡介：</label>${chefVO.chef_intr}
	                </div>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="info">
					<div id="chef_menu">
	                      <label class=" control-label">參考菜單：</label>${chefVO.chef_menu}
	                </div>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="chef_lic-wrapper">
					<div class="text-center"><label>廚藝相關證照:</label></div>
					<label >
						<img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_lic=123">
					</label>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="text-center"><label>菜單參考圖片:</label></div>
					<div class="row">
						<div class="col-xs-12 col-sm-6 menu-img-top">
						<label >
							<img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image1=123">
						</label>
						</div>
						<div class="col-xs-12 col-sm-6 menu-img-top">
						<label >
							<img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image2=123">
						</label>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-sm-4 menu-img-bottom">
						<label >
							<img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image3=123">	
						</label>
						</div>
						<div class="col-xs-12 col-sm-4 menu-img-bottom">
						<label >
							<img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image4=123">
						</label>
						</div>
						<div class="col-xs-12 col-sm-4 menu-img-bottom">
						<label >
							<img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image5=123">
						</label>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="text-center"><label>上傳的參考影片：</label></td>
			</tr>
			<tr>
				<td class="chef-video-wraper" colspan="2">
					<video id="chef_movie1" controls>
                    <source src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_movie1=123">
                </video>
				</td>
			</tr>
			<tr>
				<td class="chef-video-wraper" colspan="2">
					<video id="chef_movie1" controls>
                    <source src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_movie2=123">
                </video>
				</td>
			</tr>
			<tr>
				<td class=" text-center gobackWrapper" colspan="2">
					<button type="button" id="goBack" class="btn btn-success btn-lg">回上頁</button>
				</td>
			</tr>
        </table>
      </div>
    </div>  
    <!--     <footer id="the_footer">
        <p class="lightcolor">All Content Copyright &copy; 2016 TomCat Inc</p>
    </footer> -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script>
	    $('#goBack').click(function(){
			window.history.go(-1);
		}) 	
    </script>
   
</body>

</html>
