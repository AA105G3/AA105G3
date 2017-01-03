<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product_order_list.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	Product_order_listService product_order_listSvc = new Product_order_listService();
    List<Product_order_listVO> list = product_order_listSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />

<html>
<head>
<title>所有商品訂單明細資料</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/AA105G3/back-end/web_page/css/backpageCSS.css">

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
.btn-style{
	margin-top : 15px;
}
.th-style{
	background: #e2fede;
}
.th-style th{
	font-size: 17px;
}
.page-style{
	padding-top: 30px;
}
</style>

</head>
<body>





<nav class="navbar navbar-default" id="top_header">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
			<span class="sr-only">選單切換</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<img src="<%=request.getContextPath()%>/back-end/web_page/images/Logo.png" href="#" id="logo">				
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





			<div class="search-style">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_order_list/product_order_list.do">
				
					<div class="col-xs-12 col-sm-5 input-group">
						<div class="input-group-addon">
							輸入商品訂單編號：
						</div>
						<input type="text" name="prod_ord_no" class="form-control">
						<input type="hidden" name="action" value="getPart_For_Display_By_One_PK">
						<div class="input-group-btn">
							<button class="btn btn-primary">查詢資料</button>
						</div>
					</div>
				
				</FORM>
			</div>
			
			
			
			
			
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





			<table border='1' bordercolor='#CCCCFF' width='1000'>
				<tr class='th-style'>
					<th>訂單編號</th>
					<th>商品名稱</th>
					<th>單價</th>
					<th>數量</th>
					<th>訂單明細狀態</th>
					<th>商品出貨時間</th>
					<th>修改</th>
				</tr>
			
				<%@ include file="pages/page1.file" %> 
				<c:forEach var="product_order_listVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr align='center' valign='middle' ${(product_order_listVO.prod_ord_no==param.prod_ord_no) ? 'bgcolor=#f5deb3':''}>
						<td>${product_order_listVO.prod_ord_no}</td>
						<td>
							<c:forEach var="productVO" items="${productSvc.all}">
			                    <c:if test="${product_order_listVO.prod_no==productVO.prod_no}">
				                    ${productVO.prod_name}
			                    </c:if>
			                </c:forEach>
						</td>
						<td>${product_order_listVO.unit_price}</td>
						<td>${product_order_listVO.prod_quantity}</td>
						<td>
							<c:if test="${product_order_listVO.deli_status == '0'}" >
								未出貨
							</c:if>
							<c:if test="${product_order_listVO.deli_status == '1'}" >
								出貨中
							</c:if>
							<c:if test="${product_order_listVO.deli_status == '2'}" >
								已出貨
							</c:if>
							<c:if test="${product_order_listVO.deli_status == '3'}" >
								已退貨已退款
							</c:if>
							<c:if test="${product_order_listVO.deli_status == '4'}" >
								已退貨未退款
							</c:if>
						</td>
						<td>${product_order_listVO.deli_time}</td>
						<td>
						  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_order_list/product_order_list.do">
						     <input type="submit" value="修改" class="btn btn-primary btn-style">
						     <input type="hidden" name="prod_ord_no" value="${product_order_listVO.prod_ord_no}">
						     <input type="hidden" name="prod_no" value="${product_order_listVO.prod_no}">
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