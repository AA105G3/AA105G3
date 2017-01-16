<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>

<jsp:useBean id="chef_order_listVO" scope="request" class="com.chef_order_list.model.Chef_order_listVO" />
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="chefSvc" scope="page" class="com.chef.model.ChefService" />



<html>
<head>
<title>交易完成</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/AA105G3/css/frontpageCSS.css">

<style type="text/css" media="screen">
	html, body{
		background : white;
		height : 100%;
	}

	#skin{
		/* border : solid red; */
		/* background : #f5f5dc; */
		width : 100%;
		margin : 0px auto;
				
		/* 填滿skin */
		min-height : 100%;
		position : relative;
	}
	#id_wrapper{
		min-height: 100%;
		position: relative;
	}
	#id_footer{
		position: absolute;
    	bottom: 0;
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
	.list-style{
		padding-top : 100px;
		padding-left : 320px;
	}
	th{
		text-align : center;
	}
	tr{
		height: 50px;
	}
	body{
		background: #efede8;
		padding-top: 50px;
		position : relative;
		height : 100%;
	}
	html{
		height : 100%;
	}
	h3{
		color:red;
		text-align:center;
	}
	table{
		margin:20px auto;
	}
	td,th{
		font-size:26px;
		text-align:left;
		padding:5px;
	}
</style>

</head>

<body>





<div id="skin">

<header>
	<c:import url="/front-end/frontNavbar.jsp"></c:import>
</header>



<div class="container">
		<div class="row">





<h3>交易成功!</h3>	
<div class="text-center"><font size="+3">本次訂單資料：</font></div>



<table>

	<tr>
		<th>執行私廚：</th>
		<td>
			${chefSvc.getOneChef(chef_order_listVO.chef_no).chef_name}	
		</td>
	</tr>
	<tr>
		<th>交易金額：</th>
		<td><fmt:parseNumber var="dollar" integerOnly="true" type="number" value="${chef_order_listVO.chef_ord_cost}" />
				<c:set var="money" value="${dollar}" />$
				${money}
		</td>
	</tr>
	<tr>
		<th>訂單執行時間：</th>
		<td><fmt:formatDate value="${chef_order_listVO.chef_act_date}" var="formattedDate" 
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
							</c:if></td>
	</tr>
	<tr>
		<th>訂單執行地點：</th>
		<td>${chef_order_listVO.chef_ord_place}</td>
	</tr>
	<tr>
		<th>訂單內容說明：</th>
		<td>${chef_order_listVO.chef_ord_cnt}</td>
	</tr>
	
	
	
	
	
</table>


<div class="col-xs-12 col-sm-12 text-center">
	<a href="/AA105G3/front-end/chef_order_list/chefOrderListOfMem.jsp" class="btn btn-warning">回到訂單頁面</a>
</div>



		
	</div>
</div>

<footer id="theFooter">
		Copyright &copy; 2017 Java Team 3 
</footer>
<c:import url="/front-end/chat/inviteChat.jsp" ></c:import>


<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>





</div>
</body>

</html>