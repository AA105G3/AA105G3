<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.product_order_list.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="listPOList_ByProd_ord_no" scope="request" type="java.util.Set" />
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />

<html>
<head>
<title>商品訂單明細資料</title>
</head>
</head>
<body>

<font size="+3">商品明細： </font>

<table border='1' bordercolor='#CCCCFF' width='1500'>
	<tr>
		<th>訂單編號</th>
		<th>商品名稱</th>
		<th>單價</th>
		<th>數量</th>
		<th>訂單明細狀態</th>
		<th>商品出貨時間</th>
	</tr>

	<c:forEach var="product_order_listVO" items="${listPOList_ByProd_ord_no}">
		<tr align='center' valign='middle'>
			<td>${product_order_listVO.prod_ord_no}</td>
			<td>
				<c:forEach var="productVO" items="${productSvc.all}">
					<c:if test="${product_order_listVO.prod_no==productVO.prod_no}">
						${productVO.prod_name}
					</c:if>
				</c:forEach>
			</td>
			<td>${product_order_listVO.unit_price}</td>
			<td>${product_order_listVO.prod_quantity}</td>
			<td>
				<c:if test="${product_order_listVO.deli_status == '0'}" >
					未出貨
				</c:if>
				<c:if test="${product_order_listVO.deli_status == '1'}" >
					出貨中
				</c:if>
				<c:if test="${product_order_listVO.deli_status == '2'}" >
					已出貨
				</c:if>
				<c:if test="${product_order_listVO.deli_status == '3'}" >
					已退貨已退款
				</c:if>
				<c:if test="${product_order_listVO.deli_status == '4'}" >
					已退貨未退款
				</c:if>
			</td>
			<td>${product_order_listVO.deli_time}</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>