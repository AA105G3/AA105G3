<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef_order_list.model.*"%>
<%@ page import="com.chef.model.*"%>
<%
    Chef_order_listService chef_order_listSvc = new Chef_order_listService();
	List<Chef_order_listVO> list = chef_order_listSvc.getAll();

//	  List<Chef_order_listVO> listALL = chef_order_listSvc.getAll();    
//    List<Chef_order_listVO> list=new ArrayList<Chef_order_listVO>();    
//     for(Chef_order_listVO chef_order_listVO:listALL){
//     	if(chef_order_listVO.getChef_ord_con().equals("0")){
//     		list.add(chef_order_listVO);
//     	}
//     }
//	先濾掉不要的資料,使list不致虛張,使下方page不秀出值卻佔位

	pageContext.setAttribute("list",list);
%>

<%
    ChefService chefSvc = new ChefService();
    List<ChefVO> chef_list = chefSvc.getAll();
    pageContext.setAttribute("chef_list",chef_list);
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
		/* 對應skin */
		position : absolute;
		bottom : 0px;
		width : 100%;
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
<!--     <section id="top-img"> -->
<!--         <div class="col-xs-12 col-sm-12"> -->
<!--             <div class="top-img"></div> -->
<!--         </div> -->
<!--     </section> -->
    <ol class="breadcrumb">
        <li>
            <a href="/AA105G3/front-end/chef_order_list/becomeChef.jsp">成為私廚</a>
        </li>
    </ol>
    <div class="container">
        <div class="row">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 text-center">
                        <h2>私廚訂單</h2>
                    </div>
                    
                    <div class="col-xs-12 col-sm-12 text-center select-style">
                		<div class="col-xs-12 col-sm-4 text-center">
                			<a href="/AA105G3/front-end/chef_order_list/chefOrderListOfMem.jsp" class="btn btn-primary">全部訂單</a>
                		</div>
                		
                		<div class="col-xs-12 col-sm-4 text-center">
                			<a href="/AA105G3/front-end/chef_order_list/chefOrderListOfMem_Undone.jsp" class="btn btn-primary">未同意訂單</a>
                		</div>
                		
                		<div class="col-xs-12 col-sm-4 text-center">
                			<a href="/AA105G3/front-end/chef_order_list/chefOrderListOfMem_Done.jsp" class="btn btn-primary">已同意訂單</a>
                		</div>
                	</div>
                    
                </div>
            </div>
            <table class="table table-hover table-striped table-bordered table-condensed">
                <caption id="table_title">私廚訂單列表</caption>
                <thead>
                    <tr>
                        <th>服務私廚</th>
                        <th>下訂時間</th>
                        <th>執行時間</th>
                        <th>執行地點</th>
                        <th>金額</th>
                        <th>訂單狀態</th>
                        <th>操作</th>
                    </tr>
				</thead>
                <%@ include file="page1.file" %>
                    <c:forEach var="chef_order_listVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<%--                         <c:if test="${chef_order_listVO.chef_ord_con.equals('0')}"> --%>
                        <tbody>
                            <tr align='center' valign='middle'>
                                
                                <c:forEach var="chefVO" items="${chef_list}">
									<c:if test="${chef_order_listVO.chef_no==chefVO.chef_no}">
                    					<div class="col-xs-12 col-sm-6">
                        					<td>${chefVO.chef_name}</td>                    
                    					</div>                		
                					</c:if>
								</c:forEach>
                                <td>${chef_order_listVO.chef_ord_date}</td>
                                <td>${chef_order_listVO.chef_act_date}</td>
                                <td>${chef_order_listVO.chef_ord_place}</td>
                                <td>${chef_order_listVO.chef_ord_cost}</td>
                                <td>
                                	<c:if test="${chef_order_listVO.chef_ord_con == '0'}" >
										未同意
									</c:if>
									<c:if test="${chef_order_listVO.chef_ord_con == '1'}" >
										已修改
									</c:if>
									<c:if test="${chef_order_listVO.chef_ord_con == '2'}" >
										已同意
									</c:if>
                                </td>
                                
                                <td>
                                    <div class="btn btn-danger btn-xs"><a href="<%=request.getContextPath()%>/chef_order_list/chef_order_list.do?action=getOne_For_Display&chef_ord_no=${chef_order_listVO.chef_ord_no}">瀏覽明細</a></div>
                                </td>
                            </tr>
                        </tbody>
<%--                         </c:if> --%>
                    </c:forEach>
            </table>
            
<!--             <div class="col-xs-12 col-sm-12"> -->
<!--                 <ul class="pager"> -->
<!--                     <li class="previous"><a href="#">&larr; 上一頁</a></li> -->
<!--                     <li>第1頁 / 共X頁</li> -->
<!--                     <li class="next"><a href="#">下一頁 &rarr;</a></li> -->
<!--                 </ul> -->
<!--             </div> -->

			<%@ include file="page2.file" %>
        </div>
    </div>
    <footer id="theFooter">
        Copyright &copy; 2016 Java Team 3
    </footer>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>

</html>
