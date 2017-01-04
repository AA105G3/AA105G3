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
    <title>Title Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/AA105G3/css/BackChefPage_main.css">
    <script src=></script>
    <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
</head>

<body>
    <nav class="navbar navbar-default" id="top_header">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">選單切換</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <img src="/AA105G3/images/New_Logo2.0.png" href="#" id="logo">
        </div>
        <!-- 手機隱藏選單區 -->
        <div class="collapse navbar-collapse navbar-ex1-collapse" id="top_header">
            <!-- 左選單 -->
            <ul class="nav navbar-nav">
                <li class="active"><a href="#" id=hyper_link>FOOD TIME分享食光</a></li>
            </ul>
            <!-- 右選單 -->
            <ul class="nav navbar-nav navbar-right">
                <li><img src="/AA105G3/images/chef/icon1.png" href="#" id="head_icon"></li>
                <li><a href="#">傑利鼠您好</a></li>
                <li><a href="#">登出</a></li>
            </ul>
        </div>
        <!-- 手機隱藏選單區結束 -->
    </nav>
    <div class="col-xs-12 col-sm-2" id="menu_zone">
        <div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
            <!-- 區塊1 -->
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="panel1">
                    <h4 class="panel-title">
                                <i class=" glyphicon glyphicon-user"></i>
                                <a href="#aaa" data-parent="#accordion2" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="aaa">
                                會員管理
                                </a>
                            </h4>
                </div>
                <div id="aaa" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel1">
                    <div class="list-group">
                        <a href="#" class="list-group-item">會員資料查詢</a>
                        <a href="#" class="list-group-item">私廚資格審核</a>
                        <a href="#" class="list-group-item">私廚資料管理</a>
                    </div>
                </div>
            </div>
            <!-- 區塊2 -->
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="panel2">
                    <h4 class="panel-title">
                                <i class=" glyphicon glyphicon-pawn"></i>
                                <a href="#bbb" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="bbb">
                                員工管理
                                </a>
                            </h4>
                </div>
                <div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2">
                    <div class="list-group">
                        <a href="#" class="list-group-item">員工權限管理</a>
                        <a href="#" class="list-group-item">員工資料管理</a>
                    </div>
                </div>
            </div>
            <!-- 區塊3 -->
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="panel3">
                    <h4 class="panel-title">
                                <i class=" glyphicon glyphicon-exclamation-sign"></i>
                                <a href="#ccc" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ccc">
                                檢舉管理
                                </a>
                            </h4>
                </div>
                <div id="ccc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel3">
                    <div class="list-group">
                        <a href="#" class="list-group-item">文章檢舉審核</a>
                        <a href="#" class="list-group-item">食譜檢舉審核</a>
                        <a href="#" class="list-group-item">實況檢舉審核</a>
                        <a href="#" class="list-group-item">影片檢舉審核</a>
                    </div>
                </div>
            </div>
            <!-- 區塊4 -->
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="panel4">
                    <h4 class="panel-title">
                                <i class=" glyphicon glyphicon-book"></i>
                                <a href="#ddd" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ddd">
                                食譜管理
                                </a>
                            </h4>
                </div>
                <div id="ddd" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel4">
                    <div class="list-group">
                        <a href="#" class="list-group-item">食譜類別管理</a>
                        <a href="#" class="list-group-item">未分類食譜管理</a>
                    </div>
                </div>
            </div>
            <!-- 區塊5 -->
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="panel5">
                    <h4 class="panel-title">
                                <i class=" glyphicon glyphicon-paste"></i>
                                <a href="#eee" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="eee">
                                前端網頁管理
                                </a>
                            </h4>
                </div>
                <div id="eee" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel5">
                    <div class="list-group">
                        <a href="#" class="list-group-item">廣告管理</a>
                        <a href="#" class="list-group-item">公告管理</a>
                    </div>
                </div>
            </div>
            <!-- 區塊6 -->
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="panel6">
                    <h4 class="panel-title">
                                <i class=" glyphicon glyphicon-equalizer"></i>
                                <a href="#fff" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="fff">
                                市集管理
                                </a>
                            </h4>
                </div>
                <div id="fff" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel6">
                    <div class="list-group">
                        <a href="#" class="list-group-item">商品上下架</a>
                        <a href="#" class="list-group-item">商品資料管理</a>
                    </div>
                </div>
            </div>
            <!-- 區塊7 -->
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="panel7">
                    <h4 class="panel-title">
                                <i class=" glyphicon glyphicon-list-alt"></i>
                                <a href="#ggg" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ggg">
                                商品訂單管理
                                </a>
                            </h4>
                </div>
                <div id="ggg" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel7">
                    <div class="list-group">
                        <a href="#" class="list-group-item">商品訂單查詢</a>
                    </div>
                </div>
            </div>
            <!-- 區塊8 -->
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="panel8">
                    <h4 class="panel-title">
                                <i class=" glyphicon glyphicon-cutlery"></i>
                                <a href="#hhh" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="hhh">
                                私廚平台管理
                                </a>
                            </h4>
                </div>
                <div id="hhh" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel8">
                    <div class="list-group">
                        <a href="#" class="list-group-item">使用平台金流</a>
                        <a href="#" class="list-group-item">私廚訂單查詢</a>
                        <a href="#" class="list-group-item">仲介金流</a>
                        <a href="#" class="list-group-item">退款審核</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-sm-10" id="chefInfo">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-12 text-center">
                    <h2>私廚資格審核</h2>
                </div>
            </div>
        </div>
        <div class="col-xs-12 col-sm-6 col-sm-offset-3">
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
                    <source src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_movie2=123">
                </video>
            </div>
            <div>
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef/chef.do">
			     	<input type="submit" value="通過審核">
			     	<input type="hidden" name="chef_no" value="${chefVO.chef_no}">
			     	<input type="hidden" name="chef_chk_cond"	value="1">
			     	<input type="hidden" name="action"	value="update_chk_cond">
			    </FORM>
            </div>
            <div>
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef/chef.do">
			    	<input type="submit" value="不通過刪除">
			    	<input type="hidden" name="chef_no" value="${chefVO.chef_no}">
			    	<input type="hidden" name="action"value="delete">
			    </FORM>
            </div>
        </div>
    </div>
    <!--     <footer id="the_footer">
        <p class="lightcolor">All Content Copyright &copy; 2016 TomCat Inc</p>
    </footer> -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>

</html>
