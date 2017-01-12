<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_order_list.model.*"%>

<%
    ProductService productSvc = new ProductService();
    List<ProductVO> list = productSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>

<title>Cart.jsp</title>
 
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
		/* 對應skin */
		position : absolute;
		bottom : 0px;
		width : 100%;
	}
	.list-style{
		padding-top : 125px;
		padding-left : 175px;
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





<c:import url="/front-end/frontNavbar.jsp"></c:import>





<div class="container">
		<div class="row">
			<div class="list-style">





<!-- <img src="images/Logo.png"> --> <font size="+3">目前您購物車的內容如下： </font>
<p>

<table border="1" width="800">
	<tr bgcolor="#f5deb3">
		<th width="200" height="30">商品名稱</th>
		<th width="200" height="30">商品單價</th>
		<th width="200" height="30">購買數量</th>
		<th width="200" height="30">移除</th>
	</tr>
	
	<%
		Vector<Product_order_listVO> buylist = (Vector<Product_order_listVO>) session.getAttribute("shoppingcart");
		pageContext.setAttribute("buylist",buylist);
		String amount =  (String) session.getAttribute("amount");
		String quantity =  (String) session.getAttribute("quantity");
	%>
	
	<c:forEach var="Product_order_listVO" items="${buylist}">
		<c:forEach var="ProductVO" items="${list}">
			<c:if test="${Product_order_listVO.prod_no==ProductVO.prod_no}">
				<tr>
					<td width="200" height="30"><div align="center"><b>${ProductVO.prod_name}</b></div></td>
					<td width="200" height="30"><div align="center"><b>$	${Product_order_listVO.unit_price}</b></div></td>
					<td width="200" height="30"><div align="center"><b>${Product_order_listVO.prod_quantity}</b></div></td>
					<td width="200"><div align="center">
						<form name="deleteForm" action="/AA105G3/product_order_list/product_order_list.do" method="POST">
							<input type="hidden" name="action" value="DELETE_PRODUCT">
							<input type="hidden" name="del" value="${ProductVO.prod_no}">
							<input type="submit" class="btn btn-primary btn-style" value="移除">
						</form></div>
					</td>
				</tr>
			</c:if>
		</c:forEach>
	</c:forEach>
	
	
	<tr>
		<td colspan="4" height="30">
			<div align="center"><font color="red"><b>總金額：$	<%= (amount==null)? "0" : amount%></b></font></div>
		</td>
	</tr>
</table>

<p>

<div class="col-xs-12 col-sm-2">
	<a href="/AA105G3/front-end/product/Market.jsp" class="btn btn-primary">繼續購物</a>
</div>

<%
	HttpSession memberlist = request.getSession();
	String mem_no = (String) memberlist.getAttribute("mem_no");
%>

<div class="col-xs-12 col-sm-2">
	<c:if test="<%= amount!=null %>">
		<c:if test='<%= !amount.toString().equals("0") %>'>
			<form METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do">
				<input type="submit" class="btn btn-danger" value="結帳">
				<input type="hidden" name="mem_no" value="${mem_no}">
				<input type="hidden" name="action" value="getOne_For_List">
			</form>
		</c:if>
	</c:if>	
</div>


</p>





		</div>
	</div>
</div>





<footer id="theFooter">
	Copyright &copy; 2016 Java Team 3 
</footer>


<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>




</div>
</body>
</html>