<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.product.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>商品詳細資料 - DisplayProduct.jsp</title>

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
.table-style{
	padding-top : 50px;
}
.btn-style{
	padding-top : 50px;
}
.h2-style{
	background: #e2fede;
}

</style>

</head>
<body>

<c:import url="/back-end/TopNavBar.jsp"></c:import>



<c:import url="/back-end/LeftNavBar.jsp"></c:import>





<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-sm-push-1 table-style">





			<table border='1' bordercolor='#CCCCFF' id="mainTable" cellspacing="0" >
			
				<tr class='h2-style'>
					<td colspan="2" id="center" align="center" valign="center"><h2>商品詳細資料</h2></td>
				</tr>
				
				<tr>
					<td>商品編號：${productVO.prod_no}</td>
					<td>商品名稱：${productVO.prod_name}</td>	
				</tr>
				
				<tr>
					<td>上架日期：${productVO.shelf_date}</td>
					<td>下架日期：${productVO.shelf_date}</td>
				</tr>
				
				<tr>
					<td>銷售數量：${productVO.sales_volume}</td>
					<td>庫存數量：${productVO.stor_capacity}</td>
				</tr>
										
				<tr>
					<td>商品單價：${productVO.unit_price}</td>
					<td>優惠價格：${productVO.disc_price}</td>
				</tr>
										
				<tr>
					<td>
						商品類別：
						<c:if test="${productVO.prod_type == 'SPACE BAG'}" >
							太空包
						</c:if>
						<c:if test="${productVO.prod_type == 'TABLEWARE'}" >
							餐具
						</c:if>
						<c:if test="${productVO.prod_type == 'KITCHENWARE'}" >
							廚具
						</c:if>
					</td>
					<td id="searchTd">
						銷售狀態：${productVO.sell_status==0 ? '缺貨中' : '販售中'}
					</td>
				</tr>
											
				<tr>
					<td id="searchTd">
						商品狀態：
						<c:if test="${productVO.prod_status == '0'}" >
							下架
						</c:if>
						<c:if test="${productVO.prod_status == '1'}" >
							上架
						</c:if>
						<c:if test="${productVO.prod_status == '2'}" >
							不再販售
						</c:if>
					</td>
					<td id="searchTd">
						優惠狀態：${productVO.disc_status==0 ? '非特價' : '特價中'}
					</td>
				</tr>
								
				<tr>
					<td>優惠期使日期：${productVO.disc_start_date}</td>
					<td>優惠結束日期：${productVO.disc_end_date}</td>
				</tr>
											
				<tr>
					<td>商品照片：
						<div id="center"><img id="img" src="/AA105G3/ProductDBGifReader.do?name=${productVO.prod_no}"></div></td>
					<td>商品描述：<br>
						<textarea id="productTextarea" readonly="readonly">
							${productVO.prod_description}
						</textarea>
					</td>
				</tr>
				
			</table>

<div class="text-center btn-style">
	<form>
		<a class="btn btn-primary" href="/AA105G3/back-end/product/MarketManagement.jsp">返回</a>
	</form>
</div>







		</div>
	</div>
</div>





		
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>