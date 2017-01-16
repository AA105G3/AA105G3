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

<%
	Vector<Product_order_listVO> buylist = (Vector<Product_order_listVO>) session.getAttribute("shoppingcart");
	String amount =  (String) session.getAttribute("amount");
	String quantity =  (String) session.getAttribute("quantity");
%>

<html>
<head>

<title>市集.jsp</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sweetalert.css">
<link rel="stylesheet" href="/AA105G3/css/frontpageCSS.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/sweetalert.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/sweetalert-dev.js"></script>

<style type="text/css" media="screen">
	.select-menu{
		text-align: center;
		background: #f5f5dc;
		width: 100%;
	}
	.select-item{
		padding-left: 30px;
		padding-right: 30px;
		font-size: 24px;
	}
	.href-style{
		color: black;
	}
	.first-col{
		padding-top:5px;
		padding-bottom: 50px;
	}
	.first-col img{
		width: 350px;
		height: 250px;
	}
	.next-col{
		padding-bottom: 50px;
	}
	.next-col img{
		width: 350px;
		height: 250px;
	}
	#top-img{
		background-image: url(/AA105G3/images/Market/product_title.jpg);
		height: 500px;
		/* background-size: cover; */
		background-position: center;
	}
	.front-style{
		background: #f5deb3;
		width: 175px;
	}
	.shopping-cart{
		position: absolute;
		position: fixed;
		top: 57%;
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
	.search-style{
		padding-top: 50px;
		margin: center;
	}
	body{
		background: #efede8;
	}
</style>

<script>
   var ProductMyPoint = "/product/InformNewProduct";
   var host = window.location.host;
   var path = window.location.pathname;
   var webCtx = path.substring(0, path.indexOf('/', 1));
   var endPointURL = "ws://" + window.location.host + webCtx + ProductMyPoint;
   
   var webSocket;
   
   function connect() {
	   var prod_no = null;
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
		
		//收到server傳來的訊息
		webSocket.onmessage = function(event) {
	       var jsonObj = JSON.parse(event.data);
	       var message = jsonObj.message + "\r\n"; 
	       prod_no = jsonObj.prod_no + "\r\n"; 
	       swal({
	    	   title: "全新商品上架了~！",
	    	   text: message,
	    	   showCancelButton: true,
	    	   confirmButtonColor: "red",
	    	   confirmButtonText: "前去購買！",
	    	   cancelButtonColor: "blue",
	    	   cancelButtonText: "等會再買！",
	    	   closeOnConfirm: false
	    	 },function(){
	    	/* 	$('.thumbnail').first().focus(function(){ 
	    			$(this).css("background", "red").blur(function(){getOne_For_Display&prod_no=${productVO.prod_no}
	    				  $(this).css("background", "#fff"); 
	    			}); 
	    		 }); */
	    		 window.location.href = '<%=request.getContextPath()%>/product/product.do?action=getOne_For_Display&prod_no='+prod_no+'';
	    	 });
		  };		
      }	

</script>

</head>
<body onload="connect();" onunload="disconnect();">





<c:import url="/front-end/adv/Adv.jsp"></c:import>





<c:import url="/front-end/frontNavbar.jsp"></c:import>





<section id="top-img">
	<div class="col-xs-12 col-sm-12">
		<div class="top-img"></div>
		<!-- <img src="images/title.jpg" height="500px" width="100%"> -->
	</div>
	<div>
				
	</div>
</section>

<!-- <div class="col-xs-12 col-sm-12 select-menu">
	<nobr class="select-item">
		<a href="#" class="href-style">最新商品</a>
	</nobr>
	<nobr class="select-item">
		<a href="#" class="href-style">特價商品</a>
	</nobr>
	<nobr class="select-item">
		<a href="#" class="href-style">太空包</a>
	</nobr>
	<nobr class="select-item">
		<a href="#" class="href-style">廚具</a>
	</nobr>
	<nobr class="select-item">
		<a href="#" class="href-style">餐具</a>
	</nobr>	
</div> -->





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





<div class="container">
	<div class="row">
		<div class="container">
			<div class="row">
			
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
			
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do">
				
					<div class="col-xs-12 col-sm-4 col-sm-push-4 input-group search-style">
						<div class="input-group-addon">
							搜尋商品：
						</div>
						<input id="product-search" type="text" name="searchInput" class="form-control" onkeypress="checkValue()" onkeyup="checkValue()">
						<input type="hidden" name="action" value="search">
						<div class="input-group-btn">
							<button class="btn btn-primary" id="product-search-btn" disabled="disabled">查詢</button>
						</div>
					</div>
				
				</FORM>
			
				<div class="col-xs-12 col-sm-12">
					<h2>最新商品</h2>
				</div>
				
			</div>
		</div>

<c:forEach var="productVO" items="${list}">

<c:if test="${productVO.prod_status == '1'}">

	<div class="col-xs-12 col-sm-4 first-col">
		<a href='<%=request.getContextPath()%>/product/product.do?action=getOne_For_Display&prod_no=${productVO.prod_no}'>
			<img src="/AA105G3/ProductDBGifReader.do?name=${productVO.prod_no}" width='350' height='250'>
		</a>
		<div class="col-xs-12 col-sm-6 front-style">
			<h4>${productVO.prod_name}</h4>
		</div>
		<div class="col-xs-12 col-sm-6 text-right front-style">
			<h4>NT$ ${productVO.unit_price}</h4>
		</div>
	</div>
	
</c:if>
	
</c:forEach>



	</div>
</div>





<c:import url="/front-end/frontFooter.jsp"></c:import>





<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
	
	$('body').click(checkValue);

	function checkValue(){
		var s = document.getElementById("product-search").value;
		
		var btn = document.getElementById("product-search-btn");
		if(s.trim()!=""){
			btn.disabled = false;
		}else{
			btn.disabled = true;
		}
	}

</script>

<%-- <c:import url="/front-end/chat/inviteChat.jsp" ></c:import> --%>

</body>
</html>