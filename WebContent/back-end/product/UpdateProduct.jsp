<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //ProductServlet.java (Concroller), 存入req的productVO物件 (包括幫忙取出的productVO, 也包括輸入資料錯誤時的productVO物件)
%>
<html>
<head>
<title>修改商品資料修改 - UpdateProduct.jsp</title>

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





			<FORM METHOD="post" ACTION="product.do" name="form1" enctype="multipart/form-data">

			<table border='1' bordercolor='#CCCCFF' id="mainTable" cellspacing="0" >
			
				<tr class='h2-style'>
					<td colspan="2" id="center" align="center" valign="center"><h2>修改商品資料</h2></td>
				</tr>
				
				<tr>
					<td>商品編號：${productVO.prod_no}</td>
					<td>商品名稱：<input type="TEXT" name="prod_name" value="<%=productVO.getProd_name()%>" /></td>
				</tr>
				
				<tr>
					<td>上架日期：<input type="date" name="shelf_date" value="<%=productVO.getShelf_date()%>"></td>
					
					<td>下架日期：<input type="date" name="remove_date" value="<%=productVO.getRemove_date()%>"></td>
				</tr>
				
				<tr>
					<td>銷售數量：<input type="TEXT" name="sales_volume" value="<%=productVO.getSales_volume()%>" /></td>
					<td>庫存數量：<input type="TEXT" name="stor_capacity" value="<%=productVO.getStor_capacity()%>" /></td>
				</tr>
										
				<tr>
					<td>商品單價：<input type="TEXT" name="unit_price" value="<%=productVO.getUnit_price()%>" /></td>
					<td>優惠價格：<input type="TEXT" name="disc_price" value="<%=productVO.getDisc_price()%>" /></td>
				</tr>
										
				<tr>
					<td>
						商品類別：
						<select size="1" name="prod_type">
							<option value="SPACE BAG" <%= ((productVO.getProd_type()).equals("SPACE BAG"))?"selected":"" %> >太空包
							<option value="TABLEWARE" <%= ((productVO.getProd_type()).equals("TABLEWARE"))?"selected":"" %> >餐具
							<option value="KITCHENWARE" <%= ((productVO.getProd_type()).equals("KITCHENWARE"))?"selected":"" %> >廚具
						</select>
					</td>
					<td id="searchTd">
						銷售狀態：
						<select size="1" name="sell_status">
							<option value="0" <%= ((productVO.getSell_status()).equals("0"))?"selected":"" %> >缺貨中
							<option value="1" <%= ((productVO.getSell_status()).equals("1"))?"selected":"" %> >販售中
						</select>
					</td>
				</tr>
											
				<tr>
					<td id="searchTd">
						商品狀態：
						<select size="1" name="prod_status">
							<option value="0" <%= ((productVO.getProd_status()).equals("0"))?"selected":"" %> >下架
							<option value="1" <%= ((productVO.getProd_status()).equals("1"))?"selected":"" %> >上架
							<option value="2" <%= ((productVO.getProd_status()).equals("2"))?"selected":"" %> >不再販售
						</select>
					</td>
					<td id="searchTd">
						優惠狀態：
						<select size="1" name="disc_status">
							<option value="0" <%= ((productVO.getDisc_status()).equals("0"))?"selected":"" %> >非特價
							<option value="1" <%= ((productVO.getDisc_status()).equals("1"))?"selected":"" %> >特價中
						</select>
					</td>
				</tr>
								
				<tr>
					<td>優惠起始日期：<input type="date" name="disc_start_date" value="<%=productVO.getDisc_start_date()%>"></td>
					<td>優惠結束日期：<input type="date" name="disc_end_date" value="<%=productVO.getDisc_end_date()%>"></td>
				</tr>
											
				<tr>
					<td>商品照片：
						<input accept="image/*" type="FILE" name="prod_picture" id="prod_picture" />
						<div id="center"><img id="img" src="/AA105G3/ProductDBGifReader.do?name=${productVO.prod_no}"></div></td>
					<td>商品描述：<br>
						<textarea id="productTextarea" name="prod_description" cols="50" rows="5">
							<%=productVO.getProd_description()%>
						</textarea>
					</td>
				</tr>
				
			</table>

				<br>

				<div class="text-center btn-style">
					<a class="btn btn-default" href="/AA105G3/back-end/product/MarketManagement.jsp">取消修改</a>
					
					
					
					<input type="hidden" name="action" value="update">
					<input type="hidden" name="prod_no" value="<%=productVO.getProd_no()%>">
					<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
					<input class="btn btn-primary" type="submit" value="確認修改">
				</div>

			</FORM>





		</div>
	</div>
</div>






		
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>





</body>
</html>