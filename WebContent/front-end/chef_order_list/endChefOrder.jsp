<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chef_order_list.model.*"%>
<%@ page import="com.chef.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>

<% 
	
%>

<%
	Chef_order_listVO chef_order_listVO = (Chef_order_listVO) request.getAttribute("chef_order_listVO"); //Chef_order_listServlet.java (Concroller), 存入req的chef_order_listVO物件 (包括幫忙取出的chef_order_listVO, 也包括輸入資料錯誤時的chef_order_listVO物件)
%>
<%
    Chef_order_listService chef_order_listSvc = new Chef_order_listService();
    List<Chef_order_listVO> list = chef_order_listSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<%
    MemberService memberSvc = new MemberService();
    List<MemberVO> mem_list = memberSvc.getAll();
    pageContext.setAttribute("mem_list",mem_list);
    
  	MemberVO memberVO = memberSvc.getOneMember("M00000001");
 	pageContext.setAttribute("memberVO",memberVO);
%>
<%
    ChefService chefSvc = new ChefService();
    List<ChefVO> chef_list = chefSvc.getAll();
    pageContext.setAttribute("chef_list",chef_list);
%>



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


<table border="0">

	<tr>
		<td>會員帳號：</td>
		<td>${chef_order_listVO.mem_no}</td>
	</tr>
	<tr>
		<td>訂單金額：</td>
		<td>${chef_order_listVO.chef_ord_cost}</td>
	</tr>
	<tr>
		<td>信用卡卡號：</td>
		<td><input type="TEXT" name="credr_cad_no" size="45"
			value="" /></td>
	</tr>
	<tr>
		<td>信用卡有效時期：</td>
		<td><input type="TEXT" name="valid_date" ></td>
	</tr>
	<tr>
		<td>信用卡驗證碼：</td>
		<td><input type="TEXT" name="valid_no" size="45"
			value="" /></td>
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
		<td>訂單執行時間：</td>
		<td>${chef_order_listVO.chef_act_date}</td>
	</tr>
	<tr>
		<td>訂單執行地點：</td>
		<td>${chef_order_listVO.chef_ord_place}</td>
	</tr>
	<tr>
		<td>訂單內容說明：</td>
		<td>${chef_order_listVO.chef_ord_cnt}</td>
	</tr>
	<tr>
		<td>訂單成立時間：</td>
		<td>${chef_order_listVO.chef_ord_date}</td>
	</tr>
</table>
<br>

<div class="col-xs-12 col-sm-6">
	<a href="/AA105G3/front-end/chef_order_list/chefOrderListOfMem.jsp" class="btn btn-default">取消</a>
</div>

<div class="col-xs-12 col-sm-2">

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef_order_list/chef_order_list.do" name="form1">
	<input type="hidden" name="action" value="accept_ord">
	<input type="submit" class="btn btn-danger" value="結帳" onClick="alert('感謝您的光顧！')">
	<input type="hidden" name="chef_ord_no" value="<%=chef_order_listVO.getChef_ord_no()%>">
	<input type="hidden" name="mem_no" value="<%=chef_order_listVO.getMem_no()%>">
	<input type="hidden" name="chef_no" value="<%=chef_order_listVO.getChef_no()%>">
	<input type="hidden" name="chef_ord_cost" value="<%=chef_order_listVO.getChef_ord_cost()%>">
	<input type="hidden" name="chef_act_date" value="<%=chef_order_listVO.getChef_act_date()%>">
	<input type="hidden" name="chef_ord_place" value="<%=chef_order_listVO.getChef_ord_place()%>">
	<input type="hidden" name="chef_ord_cnt" value="<%=chef_order_listVO.getChef_ord_cnt()%>">
	<input type="hidden" name="chef_ord_con" value="2">
	<input type="hidden" name="chef_ord_date" value="<%=chef_order_listVO.getChef_ord_date()%>">
	<input type="hidden" name="mem_name" value="<%=memberVO.getMem_name()%>">
	<input type="hidden" name="mem_email" value="<%=memberVO.getMem_email()%>">
</FORM>	
	
</div>

		</div>
	</div>
</div>


<footer id="theFooter">
	Copyright &copy; 2016 Java Team 3 
</footer>


<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>





</div>
</body>

</html>