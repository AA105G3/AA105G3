<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef_order_list.model.*"%>
<%@ page import="com.member.model.*"%>

<%
    Chef_order_listService chef_order_listSvc = new Chef_order_listService();
    List<Chef_order_listVO> list = chef_order_listSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<%
    MemberService memberSvc = new MemberService();
    List<MemberVO> mem_list = memberSvc.getAll();
    pageContext.setAttribute("mem_list",mem_list);
%>

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
            <img src="/AA105G3/images/Logo.png" href="#" id="logo">
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
                    <h2>私廚訂單明細</h2>
                </div>
            </div>
        </div>
        
        <div class="col-xs-12 col-sm-10 col-sm-offset-1">
                <div class="row" style="border-bottom: 2px solid #d3d4d5;">
                    <div class="col-xs-12 col-sm-4">
                        <h3 style="margin-top:0px"><b>下訂會員</b></h3>
                    </div>
<%--                     <c:forEach var="chef_order_listVO" items="${list}"> --%>
                    	<c:forEach var="memVO" items="${mem_list}">
                    		<c:if test="${chef_order_listVO.mem_no==memVO.mem_no}">
                    			<div class="col-xs-12 col-sm-6">
                        			<b style="font-size:12pt">${memVO.mem_ac}</b>                    
                    			</div>
                    		</c:if>
                    	</c:forEach>
<%--                     </c:forEach> --%>
                </div>
                <br>
                <div class="row" style="border-bottom: 2px solid #d3d4d5">
                    <div class="col-xs-12 col-sm-4">
                        <h3 style="margin-top:0px"><b>執行時間</b></h3>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <b style="font-size:12pt">${chef_order_listVO.chef_act_date}</b>                    
                    </div>
                </div>
                <br>
                <div class="row" style="border-bottom: 2px solid #d3d4d5">
                    <div class="col-xs-12 col-sm-4">
                        <h3 style="margin-top:0px"><b>執行地點</b></h3>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <b style="font-size:12pt">${chef_order_listVO.chef_ord_place}</b>                    
                    </div>
                </div>
                <br>
                <div class="row" style="border-bottom: 2px solid #d3d4d5">
                    <div class="col-xs-12 col-sm-4">
                        <h3 style="margin-top:0px"><b>金額</b></h3>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <b style="font-size:12pt">${chef_order_listVO.chef_ord_cost}</b>
                    </div>
                </div>
                <div class="row" style="border-bottom: 2px solid #d3d4d5">
                    <div class="col-xs-12 col-sm-4">
                        <h3 style="margin-top:0px"><b>訂單內容說明</b></h3>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <b style="font-size:12pt">${chef_order_listVO.chef_ord_cnt}</b>
                    </div>
                </div>
                <div class="row" style="border-bottom: 2px solid #d3d4d5">
                    <div class="col-xs-12 col-sm-4">
                        <h3 style="margin-top:0px"><b>評價</b></h3>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                    	<div class="col-xs-12 col-sm-12">
                        	<b style="font-size:12pt">${chef_order_listVO.chef_appr}</b>
                        </div>	
                        <div class="col-xs-12 col-sm-12">
                        	<b style="font-size:12pt">${chef_order_listVO.chef_appr_cnt}</b>
                        </div>
                    </div>
                </div>
                <div class="row" style="border-bottom: 2px solid #d3d4d5">
                    <div class="col-xs-12 col-sm-4">
                        <h3 style="margin-top:0px"><b>下訂時間</b></h3>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <b style="font-size:12pt">${chef_order_listVO.chef_ord_date}</b>
                    </div>
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
