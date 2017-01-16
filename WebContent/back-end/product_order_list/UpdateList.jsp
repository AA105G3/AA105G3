<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_order_list.model.*"%>
<%
	Product_order_listVO product_order_listVO = (Product_order_listVO) request.getAttribute("product_order_listVO"); //Product_order_listServlet.java (Concroller), 存入req的product_order_listVO物件 (包括幫忙取出的product_order_listVO, 也包括輸入資料錯誤時的product_order_listVO物件)
%>
<html>
<head>
<title>商品訂單明細資料修改 - update_product_order_list_input.jsp</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/AA105G3/css/backpageCSS.css">

<style>
.table-style{
	padding-top: 75px;
	padding-left: 250px;
}
.h3-style{
	padding-bottom: 25px;
}
.tr-style td{
	padding-bottom: 25px;
}
</style>

</head>
<div id="popupcalendar" class="text"></div>

<body>





<c:import url="/back-end/TopNavBar.jsp"></c:import>



<c:import url="/back-end/LeftNavBar.jsp"></c:import>





<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-sm-push-2 table-style">





<h3 class="h3-style">訂單明細修改:</h3>

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

<FORM METHOD="post" ACTION="product_order_list.do" name="form1">
<table border="0" class="tr-style">
	<tr>
		<td>訂單編號：</td>
		<td><%=product_order_listVO.getProd_ord_no()%></td>
	</tr>
	<tr>
		<td>商品編號：</td>
		<td><%=product_order_listVO.getProd_no()%></td>
	</tr>
	<tr>
		<td>單價：</td>
		<td><%=product_order_listVO.getUnit_price()%></td>
	</tr>
	<tr>
		<td>數量：</td>
		<td><%=product_order_listVO.getProd_quantity()%></td>
	</tr>
	
	<jsp:useBean id="productSvc" scope="page" class="com.product_order_list.model.Product_order_listService" />
	<tr>
		<td>訂單明細狀態：</td>
		<td><select size="1" name="deli_status">
				<option value="0" <%= ((product_order_listVO.getDeli_status()).equals("0"))?"selected":"" %> >未出貨
				<option value="1" <%= ((product_order_listVO.getDeli_status()).equals("1"))?"selected":"" %> >出貨中
				<option value="2" <%= ((product_order_listVO.getDeli_status()).equals("2"))?"selected":"" %> >已出貨
				<option value="3" <%= ((product_order_listVO.getDeli_status()).equals("3"))?"selected":"" %> >已退貨已退款
				<option value="4" <%= ((product_order_listVO.getDeli_status()).equals("4"))?"selected":"" %> >已退貨未退款
			</select></td>
	</tr>
	
	<tr>
		<td>上架日期：</td>
		<td><input type="date" name="deli_time" value="<%=product_order_listVO.getDeli_time()%>"></td>
	</tr>
	
</table>
<br>
<a class="btn btn-default" href="/AA105G3/product_order/product_order.do?action=getPartForDisplay">取消修改</a>
<input type="hidden" name="action" value="update">
<input type="hidden" name="prod_ord_no" value="<%=product_order_listVO.getProd_ord_no()%>">
<input type="hidden" name="prod_no" value="<%=product_order_listVO.getProd_no()%>">
<input type="hidden" name="unit_price" value="<%=product_order_listVO.getUnit_price()%>">
<input type="hidden" name="prod_quantity" value="<%=product_order_listVO.getProd_quantity()%>">
<input type="submit" value="送出修改" class="btn btn-primary"></FORM>





		</div>
	</div>
</div>






		
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>





</body>
</html>