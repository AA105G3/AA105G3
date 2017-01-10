<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_order_list.model.*"%>
<%@ page import="com.product_order.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%
Product_orderVO product_orderVO = (Product_orderVO) request.getAttribute("product_orderVO");
String amount =  (String) session.getAttribute("amount");
int total = Integer.parseInt(amount);
String quantity =  (String) session.getAttribute("quantity");

MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");

Vector<Product_order_listVO> buylist = (Vector<Product_order_listVO>) session.getAttribute("shoppingcart");
pageContext.setAttribute("buylist",buylist);
%>

<%@ page import="com.member.model.*"%>

<html>
<head>
<title>商品訂單資料新增 - addMember.jsp</title>

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
		padding-top : 100px;
		padding-left : 320px;
	}
	th{
		text-align : center;
	}
	tr{
		height: 50px;
	}
</style>

</head>

<body>





<div id="skin">





<!--START SCROLL TOP BUTTON -->
<a class="scrollToTop" href="#">
	<i class="fa fa-angle-up"></i>
	<span>Top</span>
</a>
<!-- END SCROLL TOP BUTTON -->

<div class="navbar navbar-default navbar-fixed-top navbar-inverse mu-main-navbar" >
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
				<span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
			</button>
			<a href="#home" class="foodtime"><img alt="FoodTime" src="<%=request.getContextPath()%>/images/Logo.png">分享食光</a>
		</div>
		<div class="collapse navbar-collapse" id="navbar-ex-collapse">
			<ul class="nav navbar-nav navbar-right mu-main-nav">
				<li >
					<a href="#home">首頁</a>
	            </li>
	            <li>
					<a href="#mu-recipe">食譜</a>
	            </li>
	            <li>
					<a href="#mu-video">影音</a>
	            </li>
	            <li>
					<a href="#mu-chef">私廚</a>
	            </li>
	            <li>
					<a href="#mu-stream">實況</a>
	            </li>
	            <li>
					<a href="#mu-market">市集</a>
	            </li>
	            <li>
					<a href="#mu-contact">聯絡我們</a>
	            </li>
	            <li>
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">UserID<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#">編輯個人資訊</a></li>
						<li><a href="#">個人頁面</a></li>
						<li><a href="#">我的最愛</a></li>
						<li><a href="#">登出</a></li>
					</ul>
				</li>
				<li>
					<a href="#">註冊</a>
				</li>
			</ul>
		</div>
	</div>
</div>





<div class="container">
		<div class="row">
			<div class="list-style">





<font size="+3">請填寫訂單資料： </font>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_order/product_order.do" name="form1">
<table border="0">

	<tr>
		<td>會員帳號：</td>
		<td>${memberVO.mem_ac}</td>
	</tr>
	<tr>
		<td>信用卡卡號：</td>
		<td>
			<input type="TEXT" id="text1" size="1" maxlength="4" value="">
			-
			<input type="TEXT" id="text2" size="1" maxlength="4" value="">
			-
			<input type="TEXT" id="text3" size="1" maxlength="4" value="">
			-
			<input type="TEXT" id="text4" size="1" maxlength="4" value="">
			
			<input type="hidden" name="cred_card_no" id="cred_card_no" value="" />
		</td>
	</tr>
	<tr>
		<td>信用卡有效時期：</td>
		<td><input type="TEXT" name="valid_date" size="3"></td>
	</tr>
	<tr>
		<td>信用卡驗證碼：</td>
		<td><input type="TEXT" name="valid_no" size="3" maxlength="3"
			value="<%= (product_orderVO==null)? "" : product_orderVO.getValid_no()%>" /></td>
	</tr>
	<tr>
		<td>信用卡卡別：</td>
		<td><select size="1" name="cred_card_type">
				<option value="0" >VISA
				<option value="1" >MASTER
				<option value="2" >JCB
			</select></td>
	</tr>
	<tr>
		<td>收件人姓名：</td>
		<td><input type="TEXT" name="ship_name" size="45"
			value="${memberVO.mem_name}" /></td>
	</tr>
	<tr>
		<td>郵遞區號：</td>
		<td><input type="TEXT" name="post_code" size="3" maxlength="5"
			value="<%= (product_orderVO==null)? "" : product_orderVO.getPost_code()%>" /></td>
	</tr>
	<tr>
		<td>寄送地址：</td>
		<td><input type="TEXT" name="mem_adrs" size="45"
			value="${memberVO.mem_adrs}" /></td>
	</tr>
	<tr>
		<td>聯絡手機：</td>
		<td><input type="TEXT" name="cell_phone" size="45" maxlength="10"
			value="${memberVO.mem_phone}" /></td>
	</tr>
	<tr>
		<td>聯絡市話：</td>
		<td><input type="TEXT" name="tel_phone" size="45" maxlength="10"
			value="<%= (product_orderVO==null)? "" : product_orderVO.getTel_phone()%>" /></td>
	</tr>
	<tr>
		<td>訂單總金額：</td>
		<td>NT$ <font color="red"><b><%=amount%></b></font></td>
	</tr>

</table>
<br>

<div class="col-xs-12 col-sm-6">
	<a href="/AA105G3/front-end/product/Market.jsp" class="btn btn-default">取消</a>
</div>

<div class="col-xs-12 col-sm-2">
	<input type="hidden" name="action" value="insertWithList">
	<input type="submit" class="btn btn-danger" value="結帳" onClick="alert('感謝您的購買！')">
	<input type="hidden" name="mem_no" value="${memberVO.mem_no}">
	<input type="hidden" name="mem_email" value="${memberVO.mem_email}">
	<input type="hidden" name="total_money" value="<%=total%>">
	
	<c:forEach var="Product_order_listVO" items="${buylist}">
		<input type="hidden" name="prod_no" value="${Product_order_listVO.prod_no}">
		<input type="hidden" name="unit_price" value="${Product_order_listVO.unit_price}">
		<input type="hidden" name="prod_quantity" value="${Product_order_listVO.prod_quantity}">
		<input type="hidden" name="deli_status" value="0">
		<input type="hidden" name="deli_time" value="2017-12-31">	
	</c:forEach>
	
	<%-- <%
	 for (int index = 0; index < buylist.size(); index++) {
		Product_order_listVO order = buylist.get(index);
	%>
	<input type="hidden" name="prod_no" value="<%=order.getProd_no()%>">
	<input type="hidden" name="unit_price" value="<%=order.getUnit_price()%>">
	<input type="hidden" name="prod_quantity" value="<%=order.getProd_quantity()%>">
	
	<input type="hidden" name="deli_status" value="0">
	<input type="hidden" name="deli_time" value="2017-12-31">
	
	<%}%> --%>
	
</div>

</FORM>





		</div>
	</div>
</div>





<footer id="theFooter">
	Copyright &copy; 2016 Java Team 3 
</footer>

<script type="text/javascript">

function doFirst(){
	
	var count1 = document.getElementById('text1');
	var count2 = document.getElementById('text2');
	var count3 = document.getElementById('text3');
	var count4 = document.getElementById('text4');
	
	count1.addEventListener('keyup', countCredCardNo, false);
	count2.addEventListener('keyup', countCredCardNo, false);
	count3.addEventListener('keyup', countCredCardNo, false);
	count4.addEventListener('keyup', countCredCardNo, false);
	
	count1.addEventListener('blur', countCredCardNo, false);
	count2.addEventListener('blur', countCredCardNo, false);
	count3.addEventListener('blur', countCredCardNo, false);
	count4.addEventListener('blur', countCredCardNo, false);
	
	function countCredCardNo(){
		
		var text1 = document.getElementById('text1').value;
		var text2 = document.getElementById('text2').value;
		var text3 = document.getElementById('text3').value;
		var text4 = document.getElementById('text4').value;
		
		var cred_card_no = text1+text2+text3+text4;
		
		document.getElementById('cred_card_no').value = cred_card_no;
		
	}
	
}

window.addEventListener('load', doFirst, false);

</script>

<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>





</div>
</body>

</html>