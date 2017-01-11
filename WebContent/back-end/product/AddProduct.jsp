<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>
<title>商品資料新增 - addProduct.jsp</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/AA105G3/css/backpageCSS.css">

<style>

#mainTable{
	width : 800px;
	height : 600px;
	margin : 0px auto;
	/* background-color : #f5deb3; */
}
th, td {
	height : 50px;
	max-width : 200px;
	padding-left : 60px;
}
#center{
	padding-left : 0px;
	text-align : center;
}
#productTextarea{
	resize : none;
	width : 250px;
	height : 150px;
}
#img{
	height : 150px;
	width : auto;
}
.btn-style{
	padding-top : 50px;
}

</style>

</head>
<script language="JavaScript" src="<%=request.getContextPath()%>/back-end/product/js/product_picture.js"></script>
<div id="popupcalendar" class="text"></div>

<body>





<c:import url="/back-end/TopNavBar.jsp"></c:import>



<c:import url="/back-end/LeftNavBar.jsp"></c:import>





<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-sm-push-1 table-style">
		
		
		
		
		
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





			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" name="form1" enctype="multipart/form-data">

			<table border='1' bordercolor='#CCCCFF' id="mainTable" cellspacing="0" >
			
				<tr>
					<td colspan="2" id="center" align="center" valign="center"><h2>新增商品資料</h2></td>
				</tr>
				
				<tr>
					<td>商品編號：${productVO.prod_no}</td>
					<td>商品名稱：<input type="TEXT" name="prod_name" value="<%= (productVO==null)? "新的商品" : productVO.getProd_name()%>" /></td>
				</tr>
				
				<tr>
					<td>上架日期：<input type="date" name="shelf_date"></td>
					
					<td>下架日期：<input type="date" name="remove_date"></td>
				</tr>
				
				<tr>
					<td>銷售數量：<input type="TEXT" name="sales_volume" value="<%= (productVO==null)? "10" : productVO.getSales_volume()%>" /></td>
					<td>庫存數量：<input type="TEXT" name="stor_capacity" value="<%= (productVO==null)? "100" : productVO.getStor_capacity()%>" /></td>
				</tr>
										
				<tr>
					<td>商品單價：<input type="TEXT" name="unit_price" value="<%= (productVO==null)? "50" : productVO.getUnit_price()%>" /></td>
					<td>優惠價格：<input type="TEXT" name="disc_price" value="<%= (productVO==null)? "25" : productVO.getDisc_price()%>" /></td>
				</tr>
										
				<tr>
					<td>
						商品類別：
						<select size="1" name="prod_type">
							<option value="SPACE BAG" >太空包
							<option value="TABLEWARE" >餐具
							<option value="KITCHENWARE" >廚具
						</select>
					</td>
					<td id="searchTd">
						銷售狀態：
						<select size="1" name="sell_status">
							<option value="0" >缺貨中
							<option value="1" >販售中
						</select>
					</td>
				</tr>
											
				<tr>
					<td id="searchTd">
						商品狀態：
						<select size="1" name="prod_status">
							<option value="0" >下架
							<option value="1" >上架
							<option value="2" >不再販售
						</select>
					</td>
					<td id="searchTd">
						優惠狀態：
						<select size="1" name="disc_status">
							<option value="0" >非特價
							<option value="1" >特價中
						</select>
					</td>
				</tr>
								
				<tr>
					<td>優惠起始日期：<input type="date" name="disc_start_date"></td>
					<td>優惠結束日期：<input type="date" name="disc_end_date"></td>
				</tr>
											
				<tr>
					<td>商品照片：
						<input accept="image/*" type="FILE" name="prod_picture" id="prod_picture" />
						<div id="center"><img id="img" src="<%=request.getContextPath()%>/back-end/product/images/No-image-found.png"></div></td>
					<td>商品描述：<br>
						<textarea id="productTextarea" name="prod_description" cols="50" rows="5">
							<%= (productVO==null)? "最新的太空包上市！" : productVO.getProd_description()%>
						</textarea>
					</td>
				</tr>
				
			</table>

				<br>

				<div class="text-center btn-style">
					<a class="btn btn-default" href="/AA105G3/back-end/product/MarketManagement.jsp">取消新增</a>
					
					
					<input type="hidden" name="action" value="insert">
					<input class="btn btn-primary" type="submit" value="確認新增">
				</div>

			</FORM>





		</div>
	</div>
</div>







		
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>





</body>
</html>