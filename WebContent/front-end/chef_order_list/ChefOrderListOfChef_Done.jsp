<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef_order_list.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.chef.model.*"%>
<%
	Chef_order_listService chef_order_listSvc = new Chef_order_listService();
	
	ChefService chefSvc = new ChefService();
	String mem_no = (String)(session.getAttribute("mem_no"));
	ChefVO chefVO = chefSvc.getOneChefByMem_no(mem_no);
	String chef_no = chefVO.getChef_no();

	List<Chef_order_listVO> list = chef_order_listSvc.findByChef_no(chef_no);
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
    <title>frontpage-chef</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/AA105G3/css/frontpageCSS.css">
    <!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
    <style type="text/css" media="screen">
    html, body{
		background : white;
		height : 100%;
	}
    
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
	.th-style th{
		text-align: center;
	}
	.select-style{
		padding-top: 25px;
        padding-bottom: 25px;
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
<br>
<br>
<br>
    <div class="container">
        <div class="row">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 text-center">
                        <h2>待執行的私廚訂單</h2>
                    </div>
                    
                    <div class="col-xs-12 col-sm-12 text-center select-style">
                		<div class="col-xs-12 col-sm-4 text-center">
                			<a href="/AA105G3/front-end/chef_order_list/ChefOrderListOfChef2.jsp" class="btn btn-primary">未定價訂單</a>
                		</div>
                		
                		<div class="col-xs-12 col-sm-4 text-center">
                			<a href="/AA105G3/front-end/chef_order_list/ChefOrderListOfChef_Undone.jsp" class="btn btn-primary">待同意訂單</a>
                		</div>
                		
                		<div class="col-xs-12 col-sm-4 text-center">
                			<a href="/AA105G3/front-end/chef_order_list/ChefOrderListOfChef_Done.jsp" class="btn btn-primary">待執行訂單</a>
                		</div>
                	</div>
                    
                </div>
            </div>
            <table class="table table-hover table-striped table-bordered table-condensed">
                <caption id="table_title">私廚訂單列表</caption>
                <thead>
                    <tr class="th-style">
                        <th>下訂會員</th>
                        <th>訂單產生時間</th>
                        <th>執行時間</th>
                        <th>執行地點</th>
                        <th>金額</th>
                        <th>訂單執行狀態</th>
                        <th>查詢明細</th>
                    </tr>
				</thead>
				<%@ include file="page1.file" %>
                    <c:forEach var="chef_order_listVO" items="${list}">
<%--                         <c:if test="${chef_order_listVO.chef_act_date > 'sysdate'}"> --%>
                        <c:if test="${chef_order_listVO.chef_ord_con == '2'}">
                        <tbody>
                            <tr align='center' valign='middle'>
                                <c:forEach var="memVO" items="${mem_list}">
                                	<c:if test="${chef_order_listVO.mem_no==memVO.mem_no}">
                                		<td>${memVO.mem_ac}</td>
                                	</c:if>
                                </c:forEach>
                                <td>${chef_order_listVO.chef_ord_date}</td>
                                <td>${chef_order_listVO.chef_act_date}</td>
                                <td>${chef_order_listVO.chef_ord_place}</td>
                                <td>${chef_order_listVO.chef_ord_cost}</td>
                                <td>
									<c:if test="${chef_order_listVO.chef_ord_con == '0'}" >
										未定價
									</c:if>
									<c:if test="${chef_order_listVO.chef_ord_con == '1'}" >
										待同意
									</c:if>
									<c:if test="${chef_order_listVO.chef_ord_con == '2'}" >
										待執行
									</c:if>
								</td>
                                <td>
                                    <div class="btn btn-danger btn-xs"><a href="<%=request.getContextPath()%>/chef_order_list/chef_order_list.do?action=getOne_For_Chef&chef_ord_no=${chef_order_listVO.chef_ord_no}">瀏覽明細</a></div>
                                </td>
                            </tr>
                        </tbody>
                        </c:if>
<%--                         </c:if> --%>
                    </c:forEach>
            </table>
            <%@ include file="page2.file" %>  
<!--             <div class="col-xs-12 col-sm-12"> -->
<!--                 <ul class="pager"> -->
<!--                     <li class="previous"><a href="#">&larr; 上一頁</a></li> -->
<!--                     <li>第1頁 / 共X頁</li> -->
<!--                     <li class="next"><a href="#">下一頁 &rarr;</a></li> -->
<!--                 </ul> -->
<!--             </div> -->

        </div>
    </div>
    <c:import url="/front-end/chat/inviteChat.jsp" ></c:import>
    <footer id="theFooter">
		Copyright &copy; 2016 Java Team 3 
	</footer>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>

</html>
