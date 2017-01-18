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





			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" name="form1" enctype="multipart/form-data">

			<table border='1' bordercolor='#CCCCFF' id="mainTable" cellspacing="0" >
			
				<tr class='h2-style'>
					<td colspan="2" id="center" align="center" valign="center"><h2>新增商品資料</h2></td>
				</tr>
				
				<tr>
					<td colspan="2" id="center" align="center"><font color=red>所有項目為必填項目</font></td>
				</tr>
				
				<tr>
					<td>商品編號：</td>
					<td>商品名稱：<input type="TEXT" name="prod_name" id="prod_name" value="<%= (productVO==null)? "" : productVO.getProd_name()%>" /></td>
				</tr>
				
				<tr>
					<td>上架日期：<input type="date" name="shelf_date" id="shelf_date"></td>
					
					<td>下架日期：<input type="date" name="remove_date" id="remove_date"></td>
				</tr>
				
				<tr>
					<td>銷售數量：<input type="TEXT" name="sales_volume" id="sales_volume" value="<%= (productVO==null)? "" : productVO.getSales_volume()%>" /></td>
					<td>庫存數量：<input type="TEXT" name="stor_capacity" id="stor_capacity" value="<%= (productVO==null)? "" : productVO.getStor_capacity()%>" /></td>
				</tr>
										
				<tr>
					<td>商品單價：<input type="TEXT" name="unit_price" id="unit_price" value="<%= (productVO==null)? "" : productVO.getUnit_price()%>" /></td>
					<td>優惠價格：<input type="TEXT" name="disc_price" id="disc_price" value="<%= (productVO==null)? "" : productVO.getDisc_price()%>" /></td>
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
					<td>優惠起始日期：<input type="date" name="disc_start_date" id="disc_start_date"></td>
					<td>優惠結束日期：<input type="date" name="disc_end_date" id="disc_end_date"></td>
				</tr>
											
				<tr>
					<td>商品照片：
						<input accept="image/*" type="FILE" name="prod_picture" id="prod_picture" />
						<div id="center"><img id="img" src="<%=request.getContextPath()%>/back-end/product/images/No-image-found.png"></div></td>
					<td>商品描述：<br>
						<textarea id="productTextarea" name="prod_description" cols="50" rows="5">
							<%= (productVO==null)? "" : productVO.getProd_description()%>
						</textarea>
					</td>
				</tr>
				
			</table>

				<br>

				<div class="text-center btn-style">
					<a class="btn btn-default" href="/AA105G3/back-end/product/MarketManagement.jsp">取消新增</a>
					
					
					<input type="hidden" name="action" value="insert">
					<input class="btn btn-primary" type="submit" value="確認新增">
					
					<input type="button" id="incredibleButton" class="btn btn-default" value="懶人包">
				</div>

			</FORM>





		</div>
	</div>
</div>







		
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>





<script>
	$(document).ready(function(){
		$('#incredibleButton').click(function(e){
			$('#prod_name').val('過年年菜禮盒');
			$('#shelf_date').attr('value','2017-01-19');
			$('#remove_date').attr('value','2017-03-19');
			$('#sales_volume').val('0');
			$('#stor_capacity').val('100');
			$('#unit_price').val('300');
			$('#disc_price').val('250');
			$('#disc_start_date').attr('value','2017-02-19');
			$('#disc_end_date').attr('value','2017-03-19');
			document.getElementById("productTextarea").value = "過年必吃的人氣限量商品，精心挑選出6道特色餐點，豐盛氣派簡易烹煮，每道年菜都是精心挑選的，讓你過年圍爐輕鬆料理也能嘗盡美食!!<br><br>內容物：<br>1.大白菜...1大顆<br>2.下港排骨酥...1斤<br>3.筍絲...1斤<br>4.海篸...1斤<br>5.魚皮...1斤<br>6.鮑魚...8顆<br>7.蹄筋...1斤<br>8.紅棗...15顆<br>9.鳥蛋...30顆<br>10.香菇...9朵<br>11.大蒜...2支<br>12.蝦米...1大匙<br>13.辣椒...1支<br>14.水...適量<br>15.白胡椒粉...多<br>16.醬油...適量<br>17.烏醋...少許<br>18.米酒...少許<br>19.蒜頭...15顆<br>20.芋頭...1顆";
		}); 
	});
</script>





</body>
</html>