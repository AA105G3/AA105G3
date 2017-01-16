<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef_order_list.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="chefSvc" scope="page" class="com.chef.model.ChefService" />
<%
    Chef_order_listService chef_order_listSvc = new Chef_order_listService();
    List<Chef_order_listVO> list = chef_order_listSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>私廚訂單列表</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/AA105G3/css/BackChefPage_main.css">
    <script src=></script>
    <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <style>
     	#table_title{
     		font-size:30px;
     	}
     	th{
     		text-align:center;
     		font-size:16px;
     	}
     	tbody td{
     		font-size:16px;
     		vertical-align: middle !important;
     		text-align:center;
     	}
     </style>
</head>

<body>
    
    <nav>
    	<c:import url="/back-end/TopNavBar.jsp"></c:import>
    </nav>
    
    	<c:import url="/back-end/LeftNavBar.jsp"></c:import>
    
  <div class="col-xs-12 col-sm-10">
       <div class="container">
				<div class="row"> 
        
        <table class="table table-hover table-striped table-bordered table-condensed">
            <caption id="table_title">私廚訂單列表</caption>
            <thead>
                <tr>
                    <th>下訂會員</th>
                    <th>執行私廚</th>
                    <th>執行時間</th>
                    <th>執行地點</th>
                    <th>金額</th>
                    <th>操作</th>
                </tr>
            </thead>
            <%@ include file="page1.file" %>
                <c:forEach var="chef_order_listVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                    <tbody>
                        <tr>
                           <td>${memberSvc.getOneMember(chef_order_listVO.mem_no).mem_ac}</td>
                           <td>
                           <c:set var="chefVO" value="${chefSvc.getOneChef(chef_order_listVO.chef_no)}" />
                           ${chefVO.chef_name} (${memberSvc.getOneMember(chefVO.mem_no).mem_ac})  
                           </td>
                           <td>
                           	<fmt:formatDate value="${chef_order_listVO.chef_act_date}" var="formattedDate" 
               				 type="date" pattern="yyyy/MM/dd" />
							${formattedDate} &nbsp 
							<fmt:formatDate value="${chef_order_listVO.chef_act_date}" var="formattedTime" 
               				 type="date" pattern="HH:mm" />
               				 <c:set var="time" value="${formattedTime}" />
               				<c:if test="${time == '10:00'}"> 
							${time}~14:00 
							</c:if>
							 <c:set var="time2" value="${formattedTime}" />
							<c:if test="${time2 == '16:00'}"> 
							${time2}~20:00 
							</c:if>
                           
                           </td>
                           <td>${chef_order_listVO.chef_ord_place}</td>
                           <td>
                           	<fmt:parseNumber var="dollar" integerOnly="true" type="number" value="${chef_order_listVO.chef_ord_cost}" />
							<c:set var="money" value="${dollar}" />
							<c:if test="${chef_order_listVO.chef_ord_con !='0'}">$
							${money}
							</c:if>
							<c:if test="${chef_order_listVO.chef_ord_con == '0'}">
							待私廚填寫
							</c:if>
                           </td>
                           <td>
                                <a class="btn btn-info" href="<%=request.getContextPath()%>/chef_order_list/chef_order_list.do?action=getOne_For_Back&chef_ord_no=${chef_order_listVO.chef_ord_no}">瀏覽明細</a>
                           </td>
                        </tr>
                    </tbody>
                </c:forEach>
        </table>
        <%@ include file="page2.file" %>

    	</div>
	</div>
</div>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>

</html>
