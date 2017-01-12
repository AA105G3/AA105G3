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
	String mem_no = (String) session.getAttribute("mem_no");
    List<Chef_order_listVO> list = chef_order_listSvc.findByMem_no(mem_no);
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

		position : absolute;
		bottom : 0px;
		width : 100%;
		
		background: #222222;
		color:#fff ;
		font-size: 26px;
		font-family: Reklame;
		text-align: center;
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
	body{
		background: #efede8;
		padding-top: 50px;
		position : relative;
		height : 100%;
	}
	html{
		height : 100%;
	}
</style>

</head>

<body>





<div id="skin">

<header>
	<c:import url="/front-end/frontNavbar.jsp"></c:import>
</header>



<div class="container">
		<div class="row">
			<div class="list-style">





<font size="+3">本次訂單資料：</font>
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
<c:forEach var="chef_ord_listVO" items="${list}" begin="0" end="0">
	<c:forEach var="chefVO" items="${chef_list}">
		<c:if test="${chef_ord_listVO.chef_no==chefVO.chef_no}"> 
		
		
		
	<tr>
		<td>服務私廚：</td>
		<td>
	    	${chefVO.chef_name} 
		</td>
	</tr>
	<tr>
		<td>訂單金額：</td>
		<td>${chef_ord_listVO.chef_ord_cost}</td>
	</tr>
	<tr>
		<td>訂單執行時間：</td>
		<td>${chef_ord_listVO.chef_act_date}</td>
	</tr>
	<tr>
		<td>訂單執行地點：</td>
		<td>${chef_ord_listVO.chef_ord_place}</td>
	</tr>
	<tr>
		<td>訂單內容說明：</td>
		<td>${chef_ord_listVO.chef_ord_cnt}</td>
	</tr>
	<tr>
		<td>訂單成立時間：</td>
		<td>${chef_ord_listVO.chef_ord_date}</td>
	</tr>
	
	
	
		</c:if>
	</c:forEach>
</c:forEach>	
</table>


<!-- <table border="0"> -->

<!-- 	<tr> -->
<!-- 		<td>服務私廚：</td> -->
<!-- 		<td> -->
<%-- 			<c:forEach var="chefVO" items="${chef_list}"> --%>
<%-- 				<c:if test="${chef_order_listVO.chef_no==chefVO.chef_no}">                    --%>
<%--                 	${chefVO.chef_name}                                                     		 --%>
<%--                 </c:if> --%>
<%-- 			</c:forEach>		 --%>
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>訂單金額：</td> -->
<%-- 		<td>${chef_order_listVO.chef_ord_cost}</td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>信用卡卡號：</td> -->
<!-- 		<td><input type="TEXT" name="credr_cad_no" size="45" -->
<!-- 			value="" /></td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>信用卡有效時期：</td> -->
<!-- 		<td><input type="TEXT" name="valid_date" ></td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>信用卡驗證碼：</td> -->
<!-- 		<td><input type="TEXT" name="valid_no" size="45" -->
<!-- 			value="" /></td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>信用卡卡別：</td> -->
<!-- 		<td><select size="1" name="cred_card_type"> -->
<!-- 				<option value="0" >VISA -->
<!-- 				<option value="1" >MASTER -->
<!-- 				<option value="2" >JCB -->
<!-- 			</select></td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>訂單執行時間：</td> -->
<%-- 		<td>${chef_order_listVO.chef_act_date}</td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>訂單執行地點：</td> -->
<%-- 		<td>${chef_order_listVO.chef_ord_place}</td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>訂單內容說明：</td> -->
<%-- 		<td>${chef_order_listVO.chef_ord_cnt}</td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>訂單成立時間：</td> -->
<%-- 		<td>${chef_order_listVO.chef_ord_date}</td> --%>
<!-- 	</tr> -->
<!-- </table> -->
<!-- <br> -->

<div class="col-xs-12 col-sm-6">
	<a href="/AA105G3/front-end/chef_order_list/chefOrderListOfMem.jsp" class="btn btn-warning">回到訂單頁面</a>
</div>

<div class="col-xs-12 col-sm-2">
	
	
</div>

		</div>
	</div>
</div>
<c:import url="/front-end/chat/inviteChat.jsp" ></c:import>

<footer id="theFooter">
		Copyright &copy; 2016 Java Team 3 
</footer>


<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>





</div>
</body>

</html>