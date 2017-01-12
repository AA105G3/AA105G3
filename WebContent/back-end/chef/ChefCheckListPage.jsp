<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef.model.*"%>

<%
    ChefService chefSvc = new ChefService();
    List<ChefVO> list = chefSvc.getAll();
    pageContext.setAttribute("list",list);
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
    <div class="col-xs-12 col-sm-10">
        <!-- <form id="search">
                    <div class="input-group">
                        <div class="input-group-btn">                               
                            <a href="#" class="btn btn-default dropdown-toggle" data-toggle="dropdown">請選擇 <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">從私廚搜尋</a></li>
                                <li><a href="#">從會員帳號</a></li>
                            </ul>   
                        </div>
                        <input type="text" name="" id="insert" class="form-control">
                        <div class="input-group-btn">
                            <button class="btn btn-info">搜尋</button>
                        </div>
                    </div>
                </form> -->
        <!-- <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="請輸入關鍵字">
            </div>
            <button type="submit" class="btn btn-default">搜尋</button>
        </form> -->
        
        <%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
			</font>
		</c:if>
        
        <table class="table table-hover table-striped table-bordered table-condensed">
            <caption id="table_title">私廚資格審核清單</caption>
            <thead>
                <tr>
                    <th>私廚編號</th>
                    <th>會員編號</th>
                    <th>私廚審核情況</th>
                    <th>真實姓名</th>
                    <th>操作</th>
                </tr>
            </thead>
            <%@ include file="page1.file" %>
                <c:forEach var="chefVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                    <tbody>
                        <tr align='center' valign='middle'>
                            <td>${chefVO.chef_no}</td>
                            <td>${chefVO.mem_no}</td>
                            <td>${chefVO.chef_chk_cond}</td>
                            <td>${chefVO.chef_name}</td>
                            <td>
                                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef/chef.do">
			     					<input type="submit" value="進入審核">
			     					<input type="hidden" name="chef_no" value="${chefVO.chef_no}">
<%-- 			     				<input type="hidden" name="chef_chk_cond" value="${chefVO.chef_chk_cond}"> --%>
			     					<input type="hidden" name="action"	value="getOne_For_Check"></FORM>
<%--                            <div class="btn btn-danger btn-xs" href="<%=request.getContextPath()%>/back-end/chef/ChefCheckPage.jsp">進入審核</div> --%>
                            </td>
                        </tr>
                    </tbody>
                </c:forEach>
        </table>
        <%@ include file="page2.file" %>
<!--         <ul class="pagination"> -->
<!--             <li><a href="#">&laquo;</a></li> -->
<!--             <li><a href="#">1</a></li> -->
<!--             <li><a href="#">2</a></li> -->
<!--             <li><a href="#">3</a></li> -->
<!--             <li><a href="#">4</a></li> -->
<!--             <li><a href="#">5</a></li> -->
<!--             <li><a href="#">&raquo;</a></li> -->
<!--         </ul> -->
    </div>
<!--     <footer id="the_footer">
        <p class="lightcolor">All Content Copyright &copy; 2016 TomCat Inc</p>
    </footer> -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>

</html>
