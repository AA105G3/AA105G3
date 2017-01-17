<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_order_list.model.*"%>

<%
	ProductService productSvc = new ProductService();
	ProductVO productVO = productSvc.getOneProduct("prod_no");
	pageContext.setAttribute("productVO",productVO);
%>

<%
	Vector<Product_order_listVO> buylist = (Vector<Product_order_listVO>) session.getAttribute("shoppingcart");
	//String amount =  (String) request.getAttribute("amount");
	String amount =  (String) session.getAttribute("amount");
	String quantity =  (String) session.getAttribute("quantity");
%>

<html>
<head>

<title>商品.jsp</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel ="stylesheet" href="/AA105G3/css/frontpageCSS.css">

<style type="text/css" media="screen">
	.first-col{
		padding-top: 125px;
	}
	.first-col img{
		width: 650px;
		height: 450px;
	}
	h4{
		font-size: 46px;
		text-align: center;
	}
	.date-style{
		font-size:24px;
		padding-top: 25px;
	}
	.description-style{
		font-size: 36px;
		padding-top: 25px;
		padding-bottom: 25px;
	}
	.price-style{
		color: red;
	}
	.card-style img{
		width: 15%;
		height: 5.9%;
		padding-bottom: 25px;
	}
	.select-style{
		font-size: 20px;
		padding-left: 30px;
	}
	.font-style{
		font-size: 20px;
	}
	.btn-style img{
		width: 8%;
		height: 2.7%;
	}
	.tab-style{
		padding-top: 75px;
		padding-bottom: 195px;
	}
	.shopping-cart{
		position: absolute;
		position: fixed;
		top: 433px;
		left: 25px;
		width: 250px;
		list-style: none;
		margin: 0;
		padding: 0;
		font-size: 16px;
	}
	.shopping-cart li{
		padding-bottom: 15px;
	}
	</style>

</head>
<body>





<c:import url="/front-end/adv/Adv.jsp"></c:import>





<c:import url="/front-end/frontNavbar.jsp"></c:import>





<div id="sidebar-wrapper">
	<ul class="shopping-cart">
		<li>
			<a href="<%=request.getContextPath()%>/front-end/product_order_list/Cart.jsp">
				<img src="<%=request.getContextPath()%>/images/Market/shopping-cart2.png" height="175" weight="175">
			</a>
		</li>
		<li>目前購物車內有：<font color="red"><%= (quantity==null)? "0" : quantity%></font>個商品</li>
		<li>小計：$	<font color="red"><%= (amount==null)? "0" : amount%></font>元</li>
		<li><a class="btn btn-primary" href="<%=request.getContextPath()%>/front-end/product_order_list/Cart.jsp">查看購物車內容</a></li>
	</ul>
</div>




<form name="shoppingForm" action="/AA105G3/product_order_list/product_order_list.do" method="POST">

<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-8 first-col">
			<img src="/AA105G3/ProductDBGifReader.do?name=${productVO.prod_no}" class="img-responsive">
		</div>
		<div class="col-xs-12 col-sm-4 first-col">
			<h4>${productVO.prod_name}</h4>
			<div class="date-style">
				限時販售
				<br>開始：${productVO.shelf_date} (00:00)
				<br>結束：${productVO.remove_date} (23:59)
			</div>
			<div class="description-style">
				售價：<span class="price-style">NT$ ${productVO.unit_price}</span>
			</div>
			<div class="card-style">
				<img src="<%=request.getContextPath()%>/images/Market/visa.jpg">
				<img src="<%=request.getContextPath()%>/images/Market/mastercard.jpg">
				<img src="<%=request.getContextPath()%>/images/Market/jcb.jpg">
				
				<span class="select-style">
					購買數量：
					<select name="prod_quantity">
						<option value="1">1
						<option value="2">2
						<option value="3">3
						<option value="4">4
						<option value="5">5
						<option value="6">6
						<option value="7">7
						<option value="8">8
						<option value="9">9
						<option value="10">10
					</select>
				</span>
				
			</div>
			
			<%-- <div type="submit" name="Submit" class="btn btn-danger btn-lg btn-block btn-style"
			onclick="javascript:location.href='<%=request.getContextPath()%>/product_order_list/product_order_list.do?action=ADD_PRODUCT&prod_no=${productVO.prod_no}'">
				<img src="<%=request.getContextPath()%>/front-end/web_page/images/shopping-cart.png">
				加入購物車
			</div> --%>

			<input class="btn btn-danger btn-lg btn-block btn-style" type="submit" name="Submit" 
			onClick="alert('成功加入到購物車！')"
			value="加入購物車">
			
			<input type="hidden" name="prod_no" value="${productVO.prod_no}">
			<input type="hidden" name="unit_price" value="${productVO.unit_price}">
			<input type="hidden" name="action" value="ADD_PRODUCT">	
			
		</div>
	</div>
</div>

</form>

		



<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-8 col-sm-offset-2 tab-style">
			<div role="tabpanel">
				<!-- 標籤面板：標籤區 -->
				<ul class="nav nav-tabs font-style" role="tablist">
					<li role="presentation" class="active">
						<a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">商品介紹</a>
					</li>
					<li role="presentation">
						<a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">付款方式</a>
					</li>
					<li role="presentation">
						<a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">注意事項</a>
					</li>
			    </ul>
					
				<!-- 標籤面板：內容區 -->
				<div class="tab-content font-style">
					<div role="tabpanel" class="tab-pane active" id="tab1">
						${productVO.prod_description}
					</div>
					<div role="tabpanel" class="tab-pane" id="tab2">
						一、 本網站只接受使用 VISA、MasterCard、JCB付款，款項在您的信用卡帳單將顯示「FOODTIME」的名稱。
						<br>
						<br>二、 刷卡時，請特別注意帳單或商品/服務合約上所列之交易金額與日期、付款方式（載明信用卡卡號）、個人資料及購買之商品/服務內容是否完整無誤，若購買非銀貨兩訖（預付型）商品/服務時，更應注意商品/服務提供有效期間及條件是否明確記載，務必於交易時確認商品/服務或合約內容完整無誤後，才刷卡簽帳。
					</div>
					<div role="tabpanel" class="tab-pane" id="tab3">
						商品運送：
						<br>一、 全商品採用新竹貨運寄送，限台灣本島 
						<br>二、 即日起下單付款者，將於確認結帳後3～5日出貨，如遇假日則順延出貨
						<br>
						<br>退換貨規則：
						<br>除商品未使用過者、新品商品本身之瑕疵、非人為因素造成損害外，基於食品安全衛生考量，一經拆封、食用、人為造成之外盒變形、失溫、過期或保存不良導致變質，恕無法退換貨。若商品有瑕疵，請您收到商品後七天內，備妥您的訂單編號或訂購人相關資料，與愛料理聯繫，我們會立即為您處理退換貨事宜。
			        </div>
			    </div>
			</div>
		</div>
	</div>
</div>

	

<c:import url="/front-end/chat/inviteChat.jsp" ></c:import>
<c:if test="${sessionScope.mem_ac !=null}">
		<c:import url="/front-end/frd_list/friendBar.jsp" ></c:import>
	</c:if>
<footer>
	Copyright &copy; 2016 Java Team 3 
</footer>


<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>