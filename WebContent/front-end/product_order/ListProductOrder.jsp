<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.product_order.model.*"%>
<%@ page import="com.product_order_list.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	Product_orderService product_orderSvc = new Product_orderService();
	String mem_no =(String) session.getAttribute("mem_no");
	List list = product_orderSvc.getProduct_order_By_Mem_no(mem_no);
	pageContext.setAttribute("list", list);	
%>

<%
	
%>

<jsp:useBean id="Product_order_listSvc" scope="page" class="com.product_order_list.model.Product_order_listService" />

<html>
<head>
<title>訂單資料</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/AA105G3/css/frontpageCSS.css">

<style type="text/css" media="screen">
	html, body{
		background: #efede8;
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
		padding-left : 100px;
		padding-bottom : 100px;
	}
	th{
		text-align : center;
		height: 50px;
	}
	td{
		height: 50px;
	}
	.btn-style{
		margin-top : 15px;
	}
	.title-style{
		padding : 0px;
	}
</style>

</head>
<body>





<div id="skin">





<c:import url="/front-end/frontNavbar.jsp"></c:import>





<div class="list-style">



<div class="col-xs-12 col-sm-12 title-style">
	<div class="col-xs-12 col-sm-4 title-style">
		<font size="+3">您所擁有的商品訂單： </font>
	</div>
	
	<div class="col-xs-12 col-sm-2 col-sm-push-5">
		<a href="/AA105G3/front-end/member/memberInfo.jsp" class="btn btn-primary">返回個人頁面</a>
	</div>
</div>
<p>

<table border='1' bordercolor='#CCCCFF' width='1500' style="background: white;">
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
	
	<%@ include file="pages/page1_ByCompositeQuery.file" %>
	<c:forEach var="product_orderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<c:set var="productFlag" value="false" />
		
		<c:forEach var="list_info" items="${Product_order_listSvc.getProduct_order_list_By_One_PK(product_orderVO.prod_ord_no)}">
			<c:if test="${list_info.deli_status != '0'}" >
				<c:set var="productFlag" value="true" />
			</c:if>
		</c:forEach>
	
		<tr align='center' valign='middle' ${(product_orderVO.prod_ord_no==param.prod_ord_no) ? 'bgcolor=#f5deb3':''}>
			<td>${product_orderVO.prod_ord_no}</td>
			<%-- <td>${product_orderVO.mem_no}</td> --%>
			
			<%-- <td>${product_orderVO.prod_ord_time}</td> --%>
			<jsp:useBean id="product_orderVO" scope="page" class="com.product_order.model.Product_orderVO" />
			<td><%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(product_orderVO.getProd_ord_time())%></td>
			
			<td>${product_orderVO.cred_card_no.subSequence(0,4)}********${product_orderVO.cred_card_no.subSequence(12,16)}</td>
			<td>
				<fmt:formatDate value="${product_orderVO.valid_date}" var="formatDate_valid_date" 
					type="date" pattern="yyyy-MM" />
					${formatDate_valid_date} 
			</td>
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
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">
			    <input type="hidden" name="action" value="getPart_For_Display_By_One_PK"></FORM>
			</td>
			<td>
			<c:if test="${productFlag}">
				無法修改
			</c:if>
			<c:if test="${!productFlag}">
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_order/product_order.do">
			     <input type="submit" class="btn btn-primary btn-style" value="修改">
			     <input type="hidden" name="prod_ord_no" value="${product_orderVO.prod_ord_no}">
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</c:if>
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





<c:import url="/front-end/chat/inviteChat.jsp" ></c:import>

<footer id="theFooter">
	Copyright &copy; 2016 Java Team 3 
</footer>


<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>





</div>
</body>
</html>