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



<c:import url="/back-end/TopNavBar.jsp"></c:import>



<c:import url="/back-end/LeftNavBar.jsp"></c:import>



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
					<div class="col-xs-12 col-sm-3">
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
				
				<div class="col-xs-12 col-sm-4 search-style">
					<input type="submit" id="sendMessage" class="btn btn-primary" value="發布新產品" onclick="sendMessage();"/>
					<input type="hidden" id="prod_no" value="${prod_no}"/>
				</div>
				
				<%-- <a href="<%=request.getContextPath()%>/product_order/product_order.do?action=getPartForDisplay" class="btn btn-primary">測試訂單</a> --%>

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




		
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>