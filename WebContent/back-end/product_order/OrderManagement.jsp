<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.product_order.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	Product_orderService product_orderSvc = new Product_orderService();
	List<Product_orderVO> list = (List) request.getAttribute("list");
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>訂單資料</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/AA105G3/css/backpageCSS.css">

<style>
.table-style{
	padding-top: 75px;
}
th{
	text-align: center;
	height: 50px;
}
.search-style{
	padding-bottom: 30px;
}
.btn-style{
	margin-top : 15px;
}
.th-style{
	background: #e2fede;
}
.th-style th{
	font-size: 17px;
}
.page-style{
	padding-top: 30px;
}
.list-style{
	padding-bottom : 100px;
}
</style>

</head>
<body>





<div id="skin">





<c:import url="/back-end/TopNavBar.jsp"></c:import>



<c:import url="/back-end/LeftNavBar.jsp"></c:import>






<div class="col-xs-12 col-sm-8 col-sm-push-1 table-style">





<div class="list-style">
<font size="+3">商品訂單： </font>
<p>

<table border='1' bordercolor='#CCCCFF' width='1200'>
	<tr class="th-style">
		<th>訂單編號</th>
		<th>訂單成立時間</th>
		<th>信用卡卡號</th>
		<th>訂單總金額</th>
		<th>收件人姓名</th>
		<th>郵遞區號</th>
		<th>寄送地址</th>
		<th>聯絡手機</th>
		<th>聯絡市話</th>
		<th>查詢明細</th>
	</tr>
	
	<%@ include file="pages/page1_ByCompositeQuery.file" %>
	<c:forEach var="product_orderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(product_orderVO.prod_ord_no==param.prod_ord_no) ? 'bgcolor=#f5deb3':''}>
			<td>${product_orderVO.prod_ord_no}</td>
			<jsp:useBean id="product_orderVO" scope="page" class="com.product_order.model.Product_orderVO" />
			<td><%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(product_orderVO.getProd_ord_time())%></td>
			
			<td>${product_orderVO.cred_card_no.subSequence(0,4)}********${product_orderVO.cred_card_no.subSequence(12,16)}</td>
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
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">
			    <input type="hidden" name="action" value="getOne_For_Backpage">
			    </FORM>
			</td>
			
	</tr>
	</c:forEach>
	<%@ include file="pages/page2_ByCompositeQuery.file" %>
</table>

<br>
<br>
<br>

<%if (request.getAttribute("listPOList_ByProd_ord_no")!=null){%>
       <jsp:include page="ListProductOrderList.jsp" />
<%} %>
</div>






</div>






		
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>		





</div>
</body>
</html>