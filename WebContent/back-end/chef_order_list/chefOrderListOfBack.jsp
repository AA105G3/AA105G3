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
    <title>私廚訂單列表</title>
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
    
  <div class="col-xs-12 col-sm-10">
       <div class="container">
				<div class="row"> 
        
        <table class="table table-hover table-striped table-bordered table-condensed">
            <caption id="table_title">私廚訂單列表</caption>
            <thead>
                <tr>
                    <th>下訂會員</th>
                    <th>執行時間</th>
                    <th>執行地點</th>
                    <th>金額</th>
                    <th>操作</th>
                </tr>
            </thead>
            <%@ include file="page1.file" %>
                <c:forEach var="chef_order_listVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                    <tbody>
                        <tr align='center' valign='middle'>
                           <c:forEach var="memVO" items="${mem_list}">
                                <c:if test="${chef_order_listVO.mem_no==memVO.mem_no}">
                                	<td>${memVO.mem_ac}</td>
                                </c:if>
                           </c:forEach>
                           <td>${chef_order_listVO.chef_act_date}</td>
                           <td>${chef_order_listVO.chef_ord_place}</td>
                           <td>${chef_order_listVO.chef_ord_cost}</td>
                           <td>
                                <div class="btn btn-danger btn-xs"><a href="<%=request.getContextPath()%>/chef_order_list/chef_order_list.do?action=getOne_For_Back&chef_ord_no=${chef_order_listVO.chef_ord_no}">瀏覽明細</a></div>
                           </td>
                        </tr>
                    </tbody>
                </c:forEach>
        </table>
        <%@ include file="page2.file" %>

    </div>
<!--     <footer id="the_footer">
        <p class="lightcolor">All Content Copyright &copy; 2016 TomCat Inc</p>
    </footer> -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>

</html>
