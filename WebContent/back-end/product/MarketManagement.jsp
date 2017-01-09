<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    ProductService productSvc = new ProductService();
    List<ProductVO> list = productSvc.getAll();
    pageContext.setAttribute("list",list);
    
    /* 計算筆數 */
    int listsize = list.size()-1;
    /* 取得最後一筆的產品編號 */
    String prod_no = list.get(listsize).getProd_no();
    /* 存入page，以利下面程式取得 */
    pageContext.setAttribute("prod_no",prod_no);
%>

<html>
<head>

<title>市集管理 - MarketManagement.jsp</title>

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
.th-style{
	background: #e2fede;
}
.page-style{
	padding-top: 30px;
}
</style>

<script>
   var MyPoint = "/product/InformNewProduct";
   var host = window.location.host;
   var path = window.location.pathname;
   var webCtx = path.substring(0, path.indexOf('/', 1));
   var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
   
   var webSocket;
   
   function connect() {
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
		
		//建立連線
		webSocket.onopen = function(event) {
			document.getElementById('sendMessage').disabled = false;	
		};
		//收到server傳來的訊息
		webSocket.onmessage = function(event) {
	       var jsonObj = JSON.parse(event.data);
	       var message = jsonObj.message + "\r\n"; 
	       alert("通知成功");
		};		
      }
 	
   //clinet發送的訊息
   var prod_no = null;
   function sendMessage() {
	   prod_no = document.getElementById("prod_no").value;
	   var jsonObj = {"prod_no": prod_no, "message" : "有新品上市囉，趕緊去看看!!"};
	   webSocket.send(JSON.stringify(jsonObj));
   }
   
</script>

</head>
<body onload="connect();" onunload="disconnect();">



<nav class="navbar navbar-default" id="top_header">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
			<span class="sr-only">選單切換</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<img src="<%=request.getContextPath()%>/images/Logo.png" href="#" id="logo">				
	</div>
		
	<!-- 手機隱藏選單區 -->
	<div class="collapse navbar-collapse navbar-ex1-collapse" id="top_header">
		<!-- 左選單 -->
		<ul class="nav navbar-nav">
			<li class="active"><a href="#" id=hyper_link>FOOD TIME分享食光</a></li>				
		</ul>
		
		<!-- 搜尋表單 -->
		<!-- <form class="navbar-form navbar-left" role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="請輸入關鍵字">
			</div>
			<button type="submit" class="btn btn-default">搜尋</button>
		</form> -->
		
		<!-- 右選單 -->
		<ul class="nav navbar-nav navbar-right">
			<li><img src="images/頭像icon1.png" href="#" id="head_icon"></li>
			<li><a href="#">菜鳥會員您好</a></li>
			<li><a href="#">登出</a></li>
		</ul>
	</div>
	<!-- 手機隱藏選單區結束 -->
</nav>



<div class="col-xs-12 col-sm-2" id="menu_zone">
	<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
	<!-- 區塊1 -->
		<div class="panel panel-default">
	    	<div class="panel-heading" role="tab" id="panel1">
	      		<h4 class="panel-title">
					<i class=" glyphicon glyphicon-user"></i>
					<a href="#aaa" data-parent="#accordion2" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="aaa">
						會員管理
					</a>
				</h4>
			</div>
			<div id="aaa" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel1">
				<div class="list-group">
					<a href="#" class="list-group-item">會員資料查詢</a>
					<a href="#" class="list-group-item">私廚資格審核</a>
					<a href="#" class="list-group-item">私廚資料管理</a>
				</div>
			</div>
		</div>
	<!-- 區塊2 -->
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="panel2">
				<h4 class="panel-title">
					<i class=" glyphicon glyphicon-pawn"></i>
					<a href="#bbb" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="bbb">
						員工管理
					</a>
				</h4>
			</div>
			<div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2">
				<div class="list-group">
					<a href="#" class="list-group-item">員工權限管理</a>
					<a href="#" class="list-group-item">員工資料管理</a>					     
				</div>
			</div>
		</div>
	<!-- 區塊3 -->
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="panel3">
				<h4 class="panel-title">
					<i class=" glyphicon glyphicon-exclamation-sign"></i>
					<a href="#ccc" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ccc">
						檢舉管理
					</a>
				</h4>
			</div>
			<div id="ccc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel3">
				<div class="list-group">
					<a href="#" class="list-group-item">文章檢舉審核</a>
					<a href="#" class="list-group-item">食譜檢舉審核</a>
					<a href="#" class="list-group-item">實況檢舉審核</a>
					<a href="#" class="list-group-item">影片檢舉審核</a>
				</div>
			</div>
		</div>
	<!-- 區塊4 -->
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="panel4">
				<h4 class="panel-title">
					<i class=" glyphicon glyphicon-book"></i>
					<a href="#ddd" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ddd">
						食譜管理
					</a>
				</h4>
			</div>
			<div id="ddd" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel4">
				<div class="list-group">
					<a href="#" class="list-group-item">食譜類別管理</a>
					<a href="#" class="list-group-item">未分類食譜管理</a>
				</div>
			</div>
		</div>
	<!-- 區塊5 -->
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="panel5">
				<h4 class="panel-title">
					<i class=" glyphicon glyphicon-paste"></i>
					<a href="#eee" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="eee">
						前端網頁管理
					</a>
				</h4>
			</div>
			<div id="eee" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel5">
				<div class="list-group">
					<a href="#" class="list-group-item">廣告管理</a>
					<a href="#" class="list-group-item">公告管理</a>
				</div>
			</div>
		</div>
	<!-- 區塊6 -->
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="panel6">
				<h4 class="panel-title">
					<i class=" glyphicon glyphicon-equalizer"></i>
					<a href="#fff" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="fff">
						市集管理
					</a>
				</h4>
			</div>
			<div id="fff" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel6">
				<div class="list-group">
					<a href="#" class="list-group-item">商品上下架</a>
					<a href="#" class="list-group-item">商品資料管理</a>
				</div>
			</div>
		</div>
	<!-- 區塊7 -->
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="panel7">
				<h4 class="panel-title">
					<i class=" glyphicon glyphicon-list-alt"></i>
					<a href="#ggg" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ggg">
						商品訂單管理
					</a>
				</h4>
			</div>
			<div id="ggg" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel7">
				<div class="list-group">
					<a href="#" class="list-group-item">商品訂單查詢</a>				      	
				</div>
			</div>
		</div>
	<!-- 區塊8 -->
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="panel8">
				<h4 class="panel-title">
					<i class=" glyphicon glyphicon-cutlery"></i>
					<a href="#hhh" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="hhh">
						私廚平台管理
					</a>
				</h4>
			</div>
			<div id="hhh" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel8">
				<div class="list-group">
					<a href="#" class="list-group-item">使用平台金流</a>
					<a href="#" class="list-group-item">私廚訂單查詢</a>
					<a href="#" class="list-group-item">仲介金流</a>
					<a href="#" class="list-group-item">退款審核</a>
				</div>
			</div>
		</div>
	</div>
</div>



<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-sm-push-2 table-style">
		
		
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
		
		
			<div class="search-style">
				<div class="col-xs-12 col-sm-8">
					<div class="col-xs-12 col-sm-2">
						<a class="btn btn-primary" href="/AA105G3/back-end/product/AddProduct.jsp">新增商品</a>
					</div>
					
				
				
				
				
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do">
					
						<div class="col-xs-12 col-sm-8 input-group">
							<div class="input-group-addon">
								輸入商品編號：
							</div>
							<input type="text" name="prod_no" class="form-control">
							<input type="hidden" name="action" value="getOne_For_Backpage">
							<div class="input-group-btn">
								<button class="btn btn-primary">查詢資料</button>
							</div>
						</div>
					
					</FORM>
				</div>
				
				<div class="col-xs-12 col-sm-2">
					<input type="submit" id="sendMessage" class="btn btn-primary" value="發布新產品" onclick="sendMessage();"/>
					<input type="hidden" id="prod_no" value="${prod_no}"/>
				</div>
				
				<a href="<%=request.getContextPath()%>/product_order/product_order.do?action=getPartForDisplay" class="btn btn-primary">測試訂單</a>

			</div>




			<table border='1' bordercolor='#CCCCFF' width='1000'>
				<tr class='th-style'>
					<th>商品圖片</th>
					<th>商品編號</th>
					<th>商品名稱</th>
					<th>商品類別</th>
					<th>銷售數量</th>
					<th>庫存數量</th>
					<th>單價</th>
					<th>商品狀態</th>
					<th>優惠狀態</th>
					<th>銷售狀態</th>
					<th>查詢詳情</th>
					<th>修改資料</th>
					
				</tr>
			
				<%@ include file="pages/page1.file" %> 
				<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr align='center' valign='middle' ${(productVO.prod_no==param.prod_no) ? 'bgcolor=#f5deb3':''}>
					
						<td><img src="/AA105G3/ProductDBGifReader.do?name=${productVO.prod_no}" width='100' height='75'></td>
						<td>${productVO.prod_no}</td>
						<td>${productVO.prod_name}</td>
						<td>
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
						<td>${productVO.sales_volume}</td>
						<td>${productVO.stor_capacity}</td>
						<td>${productVO.unit_price}</td>
						<td>
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
						<td>${productVO.disc_status==0 ? '非特價' : '特價中'}</td>
						<td>${productVO.sell_status==0 ? '缺貨中' : '販售中'}</td>
						
						<td>
						  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do">
						    <input type="submit" value="查詢" class="btn btn-primary"> 
						    <input type="hidden" name="prod_no" value="${productVO.prod_no}">
						    <input type="hidden" name="action" value="getOne_For_Backpage"></FORM>
						</td>
						
						<td>
						  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do">
						     <input type="submit" value="修改" class="btn btn-primary">
						     <input type="hidden" name="prod_no" value="${productVO.prod_no}">
						     <input type="hidden" name="whichPage"	value="<%=whichPage%>">
						     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
						</td>
						
					</tr>
				</c:forEach>
			</table>
			
			<div class="col-xs-12 col-sm-4 col-sm-push-4 page-style">
				<%@ include file="pages/page2.file" %>
			</div>

		</div>
	</div>
</div>



<footer id="the_footer">
	<p class="lightcolor">Copyright &copy; 2016 Java Team 3</p>
</footer>
		
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>