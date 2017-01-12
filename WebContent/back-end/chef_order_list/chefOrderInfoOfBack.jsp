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
    
    <nav>
    	<c:import url="/back-end/TopNavBar.jsp"></c:import>
    </nav>
    
    	<c:import url="/back-end/LeftNavBar.jsp"></c:import>
    
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
