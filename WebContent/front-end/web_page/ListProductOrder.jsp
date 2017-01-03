<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.product_order.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="product_orderSvc" scope="page" class="com.product_order.model.Product_orderService" />

<html>
<head>
<title>訂單資料</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/AA105G3/front-end/web_page/css/frontpageCSS.css">

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
		/* 對應skin */
		position : absolute;
		bottom : 0px;
		width : 100%;
	}
	.list-style{
		padding-top : 100px;
		padding-left : 225px;
	}
	th{
		text-align : center;
	}
	.btn-style{
		margin-top : 15px;
	}
</style>

</head>
<body>





<div id="skin">





<!--START SCROLL TOP BUTTON -->
<a class="scrollToTop" href="#">
	<i class="fa fa-angle-up"></i>
	<span>Top</span>
</a>
<!-- END SCROLL TOP BUTTON -->

<div class="navbar navbar-default navbar-fixed-top navbar-inverse mu-main-navbar" >
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
				<span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
			</button>
			<a href="#home" class="foodtime"><img alt="FoodTime" src="<%=request.getContextPath()%>/front-end/web_page/images/Logo.png">分享食光</a>
		</div>
		<div class="collapse navbar-collapse" id="navbar-ex-collapse">
			<ul class="nav navbar-nav navbar-right mu-main-nav">
				<li >
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





			<div class="list-style">




<font size="+3">您所擁有的商品訂單： </font>
<p>

<table border='1' bordercolor='#CCCCFF' width='1500'>
	<tr>
		<th>訂單編號</th>
		<!-- <th>會員編號</th> -->
		<th>訂單成立時間</th>
		<th>信用卡卡號</th>
		<th>信用卡有效日期</th>
		<!-- <th>信用卡驗證碼</th> -->
		<th>信用卡卡別</th>
		<th>訂單總金額</th>
		<th>收件人姓名</th>
		<th>郵遞區號</th>
		<th>寄送地址</th>
		<th>聯絡手機</th>
		<th>聯絡市話</th>
		<th>查詢明細</th>
		<th>修改訂單</th>
	</tr>
	
	<c:forEach var="product_orderVO" items="${product_orderSvc.getProduct_order_By_Mem_no(param.mem_no)}">
		<tr align='center' valign='middle' ${(product_orderVO.prod_ord_no==param.prod_ord_no) ? 'bgcolor=#f5deb3':''}>
			<td>${product_orderVO.prod_ord_no}</td>
			<%-- <td>${product_orderVO.mem_no}</td> --%>
			
			<%-- <td>${product_orderVO.prod_ord_time}</td> --%>
			<jsp:useBean id="product_orderVO" scope="page" class="com.product_order.model.Product_orderVO" />
			<td><%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(product_orderVO.getProd_ord_time())%></td>
			
			<td>${product_orderVO.cred_card_no.subSequence(0,4)}********${product_orderVO.cred_card_no.subSequence(12,16)}</td>
			<td>${product_orderVO.valid_date}</td>
			<%-- <td>${product_orderVO.valid_no}</td> --%>
			<td>
				<c:if test="${product_orderVO.cred_card_type == '0'}" >
					VISA
				</c:if>
				<c:if test="${product_orderVO.cred_card_type == '1'}" >
					MASTER
				</c:if>
				<c:if test="${product_orderVO.cred_card_type == '2'}" >
					JCB
				</c:if>
			</td>
			<td>${product_orderVO.total_money}</td>
			<td>${product_orderVO.ship_name}</td>
			<td>${product_orderVO.post_code}</td>
			<td>${product_orderVO.mem_adrs}</td>
			<td>${product_orderVO.cell_phone}</td>
			<td>${product_orderVO.tel_phone}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_order/product_order.do">
			    <input type="submit" class="btn btn-primary btn-style" value="查詢"> 
			    <input type="hidden" name="prod_ord_no" value="${product_orderVO.prod_ord_no}">
			    <input type="hidden" name="mem_no" value="${product_orderVO.mem_no}">
			    <input type="hidden" name="action" value="getPart_For_Display_By_One_PK"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_order/product_order.do">
			     <input type="submit" class="btn btn-primary btn-style" value="修改">
			     <input type="hidden" name="prod_ord_no" value="${product_orderVO.prod_ord_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
	</tr>
	</c:forEach>
</table>

<br>
<br>
<br>

<%if (request.getAttribute("listPOList_ByProd_ord_no")!=null){%>
       <jsp:include page="ListProductOrderList.jsp" />
<%} %>





		</div>





<footer id="theFooter">
	Copyright &copy; 2016 Java Team 3 
</footer>


<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>





</div>
</body>
</html>