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
            <!-- <img src="/AA105G3/images/title.jpg" height="500px" width="100%"> -->
        </div>
    </section>
    <div class="container" id="mainpage">
        <div class="row">
            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef/chef.do" name="form1" enctype="multipart/form-data">
            	<div class="container">
                	<div class="row">
                    	<div class="col-xs-12 col-sm-12 text-center">
                        	<h2>私廚基本資訊</h2>
                    	</div>
                	</div>
            	</div>
            	
            	<c:if test="${not empty errorMsgs}">
					<font color='red'>請修正以下錯誤:
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li>${message}</li>
							</c:forEach>
						</ul>
					</font>
				</c:if>
            
                <div class="col-xs-12 col-sm-6 col-sm-offset-3">
                    <div class="form-group">
                        <h4>私廚照片</h4>
                        <input type="File" name="chef_image" size="45" value="${chefVO.chef_image}" />
                    </div>
                    <div class="form-group">
                        <h4>私廚證照</h4>
                        <input type="File" name="chef_lic" size="45" value="${chefVO.chef_lic}" />
                    </div>
                    <div class="form-group form-inline">
                        <h4>真實姓名</h4>
                        <input type="text" name="chef_name" id="xxx" value="${chefVO.chef_name}" class="form-control">
                    </div>
                    <div class="form-group form-inline">
                        <h4>身分證字號</h4>
                        <input type="text" name="chef_id" id="" value="${chefVO.chef_id}" class="form-control" maxlength="10">
                    </div>
                    <div class="form-group form-inline">
                        <h4>匯款帳戶銀行代碼</h4>
                        <input type="text" name="chef_bnk" id="" value="${chefVO.chef_bnk}" class="form-control" maxlength="5">
                    </div>
                    <div class="form-group form-inline">
                        <h4>匯款帳號</h4>
                        <input type="text" name="chef_bnk_ac" id="" value="${chefVO.chef_bnk_ac}" class="form-control">
                    </div>
                    <div class="form-group">
                        <h4>服務地區</h4>
                        <textarea id="" name="chef_area" class="form-control">${chefVO.chef_area}</textarea>
                    </div>
                    <div class="form-group">
                        <h4>擅長料理風格</h4>
                        <textarea id="" name="chef_skill" class="form-control">${chefVO.chef_skill}</textarea>
                    </div>
                    <div class="form-group">
                        <h4>私廚簡介</h4>
                        <textarea id="" name="chef_intr" class="form-control">${chefVO.chef_intr}</textarea>
                    </div>
                    <div class="form-group">
                        <h4>參考菜單</h4>
                        <textarea id="" name="chef_menu" class="form-control">${chefVO.chef_menu}</textarea>
                    </div>
                    <div class="form-group">
                        <h4>參考菜單圖片一</h4>
                        <input type="File" name="chef_reci_image1" size="45" value="${chefVO.chef_reci_image1}" />
                    </div>
                    <div class="form-group">
                        <h4>參考菜單圖片二</h4>
                        <input type="File" name="chef_reci_image2" size="45" value="${chefVO.chef_reci_image2}" />
                    </div>
                    <div class="form-group">
                        <h4>參考菜單圖片三</h4>
                        <input type="File" name="chef_reci_image3" size="45" value="${chefVO.chef_reci_image3}" />
                    </div>
                    <div class="form-group">
                        <h4>參考菜單圖片四</h4>
                        <input type="File" name="chef_reci_image4" size="45" value="${chefVO.chef_reci_image4}" />
                    </div>
                    <div class="form-group">
                        <h4>參考菜單圖片五</h4>
                        <input type="File" name="chef_reci_image5" size="45" value="${chefVO.chef_reci_image5}" />
                    </div>
                    <br>
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="chef_no" value="${chefVO.chef_no}">
<%--                <input type="hidden" name="chef_id" value="${chefVO.chef_id}"> --%>
                    <input type="hidden" name="chef_movie1" value="${chefVO.chef_movie1}">
                    <input type="hidden" name="chef_movie2" value="${chefVO.chef_movie2}">
                    <input type="submit" value="送出修改">
                </div>
            </FORM>
        </div>
    </div>
    <c:import url="/front-end/chat/inviteChat.jsp" ></c:import>
    <footer id="theFooter">
		Copyright &copy; 2016 Java Team 3 
	</footer>
    </body>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</html>
