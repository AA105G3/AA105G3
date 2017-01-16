<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    
	table{
		background: white;
	}
	html{
		height:100%;
	}
	body{
		background: #efede8;
		padding-top: 20px;
		position : relative;
		height : 100%;
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
	.select-style{
		padding-top : 20px;
		padding-bottom : 20px;
	}
	.th-style th{
		text-align: center;
		height: 50px;
		font-size: 16px;
	}
	.td-style td{
		font-size: 16px;
	}
	td{
		height: 50px;
	}
	.title-style{
		padding-top : 30px;
		padding-bottom : 10px;
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
                
                	<div class="col-xs-12 col-sm-2 col-sm-push-10">
						<a href="/AA105G3/front-end/member/memberInfo.jsp" class="btn btn-primary">返回個人頁面</a>
					</div>
                
                    <div class="col-xs-12 col-sm-8 text-center">
                        <h1>我的會員訂單</h1>
                    </div>
                    
                    <div class="col-xs-12 col-sm-12 text-center select-style">
                		<div class="col-xs-12 col-sm-4 text-center">
                			<a href="/AA105G3/front-end/chef_order_list/ChefOrderListOfChef2.jsp" class="btn btn-primary btn-lg">未定價</a>
                		</div>
                		
                		<div class="col-xs-12 col-sm-4 text-center">
                			<a href="/AA105G3/front-end/chef_order_list/ChefOrderListOfChef_Undone.jsp" class="btn btn-primary btn-lg">未同意</a>
                		</div>
                		
                		<div class="col-xs-12 col-sm-4 text-center">
                			<a href="/AA105G3/front-end/chef_order_list/ChefOrderListOfChef_Done.jsp" class="btn btn-danger btn-lg">未執行</a>
                		</div>
                	</div>
                    
                </div>
            </div>
            <table border='1' bordercolor='#CCCCFF' width='1200'>
                <thead>
                    <tr class="th-style">
                        <th>下訂會員</th>
                        <th>訂單產生時間</th>
                        <th>執行時間</th>
                        <th>執行地點</th>
                        <th>金額</th>
                        <!-- <th>訂單執行狀態</th> -->
                        <th>查詢明細</th>
                    </tr>
				</thead>
				<h2 class="title-style text-center">未執行的會員訂單列表</h2>
				<%@ include file="page1.file" %>
                    <c:forEach var="chef_order_listVO" items="${list}">
<%--                         <c:if test="${chef_order_listVO.chef_act_date > 'sysdate'}"> --%>
                        <c:if test="${chef_order_listVO.chef_ord_con == '2'}">
                        <tbody>
                            <tr align='center' valign='middle' class="td-style">
                                <c:forEach var="memVO" items="${mem_list}">
                                	<c:if test="${chef_order_listVO.mem_no==memVO.mem_no}">
                                		<td>${memVO.mem_ac}</td>
                                	</c:if>
                                </c:forEach>
                                <td>
                                	<fmt:formatDate value="${chef_order_listVO.chef_ord_date}" var="formatDate_chef_ord_date" 
									type="date" pattern="yyyy/MM/dd HH:mm" />
                               		${formatDate_chef_ord_date}
                                </td>
                                <td>
                                	<fmt:formatDate value="${chef_order_listVO.chef_act_date}" var="formatDate_chef_act_date1" 
			               				 type="date" pattern="yyyy/MM/dd" />
										${formatDate_chef_act_date1} &nbsp 
									<fmt:formatDate value="${chef_order_listVO.chef_act_date}" var="formatDate_chef_act_date2" 
			               				 type="date" pattern="HH:mm" />
			               				 <c:set var="time1" value="${formatDate_chef_act_date2}" />
			               				<c:if test="${time1 == '10:00'}"> 
										${time1}~14:00 
										</c:if>
										 <c:set var="time2" value="${formatDate_chef_act_date2}" />
										<c:if test="${time2 == '16:00'}"> 
										${time2}~20:00 
										</c:if>
                                </td>
                                <td>${chef_order_listVO.chef_ord_place}</td>
                                <td>$
									<fmt:parseNumber var="dollar" integerOnly="true" type="number" value="${chef_order_listVO.chef_ord_cost}" />
										${dollar}
								</td>
                                <%-- <td>
									<c:if test="${chef_order_listVO.chef_ord_con == '0'}" >
										未定價
									</c:if>
									<c:if test="${chef_order_listVO.chef_ord_con == '1'}" >
										待同意
									</c:if>
									<c:if test="${chef_order_listVO.chef_ord_con == '2'}" >
										待執行
									</c:if>
								</td> --%>
                                <td>
                                    <a href="<%=request.getContextPath()%>/chef_order_list/chef_order_list.do?action=getOne_For_Chef&chef_ord_no=${chef_order_listVO.chef_ord_no}"><div class="btn btn-primary">瀏覽明細</div></a>
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
