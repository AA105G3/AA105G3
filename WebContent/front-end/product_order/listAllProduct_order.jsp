<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.product_order.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	Product_orderService product_orderSvc = new Product_orderService();
    List<Product_orderVO> list = product_orderSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<%-- <jsp:useBean id="product_orderSvc" scope="page" class="com.product_order.model.Product_orderService" /> --%>

<html>
<head>
<title>所有商品訂單資料 - listAllProduct_order.jsp</title>
</head>
<body>

<table border='1' cellpadding='5' cellspacing='0' width='1500'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有商品資料 - listAllProduct_order.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/product_order/select_page.jsp">
		<img src="<%=request.getContextPath()%>/front-end/product_order/images/back.png" width="70" height="70" border="0">回首頁</a>
		</td>
	</tr>
</table>

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

<table border='1' bordercolor='#CCCCFF' width='1500'>
	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
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
		<th>修改</th>	
		<!-- <th>查詢明細</th> -->
	</tr>
 
	<c:forEach var="product_orderVO" items="${list}" >
		<tr align='center' valign='middle' ${(product_orderVO.prod_ord_no==param.prod_ord_no) ? 'bgcolor=#CCCCFF':''}>
			<td>${product_orderVO.prod_ord_no}</td>
			<td>${product_orderVO.mem_no}</td>
			
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
			     <input type="submit" value="修改">
			     <input type="hidden" name="prod_ord_no" value="${product_orderVO.prod_ord_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<%-- <td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_order/product_order.do">
			    <input type="submit" value="送出查詢"> 
			    <input type="hidden" name="prod_ord_no" value="${product_orderVO.prod_ord_no}">
			    <input type="hidden" name="action" value="getPart_For_Display_By_One_PK"></FORM>
			</td> --%>
		</tr>
	</c:forEach>
</table>

<%if (request.getAttribute("listPOList_ByProd_ord_no")!=null){%>
       <jsp:include page="listPartProduct_order_list.jsp" />
<%} %>

</body>
</html>