<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>


<jsp:useBean id="chef_order_listVO" scope="request" class="com.chef_order_list.model.Chef_order_listVO" />
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="chefSvc" scope="page" class="com.chef.model.ChefService" />

<html>
<head>
<title>填寫交易資料</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/AA105G3/css/frontpageCSS.css">

<style type="text/css" >
	html, body{
		background : white;
		height : 100%;
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
	html,body{
		background: #efede8;
		/* padding-top: 50px; */
		height : 100%;
		/* position : relative; */
		
		
	}
	.page{
		min-height:100%;
		   position: relative;
		   padding-top:60px;
		   padding-bottom:50px;
	}
		
	form{
		display:inline;
	}
	.button-wrapper{
		padding-left:110px;
	}
</style>

</head>

<body>


<div class="page">




<header>
	<c:import url="/front-end/frontNavbar.jsp"></c:import>
</header>



<div class="container">
		<div class="row">
			<div class="list-style">





<font size="+3">請填寫交易資料： </font>
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
		<td>執行私廚：</td>
		<td>
			${chefSvc.getOneChef(chef_order_listVO.chef_no).chef_name}	
		</td>
	</tr>
	<tr>
		<td>交易金額：</td>
		<td><fmt:parseNumber var="dollar" integerOnly="true" type="number" value="${chef_order_listVO.chef_ord_cost}" />
				<c:set var="money" value="${dollar}" />$
				${money}
		</td>
	</tr>
	<tr>
		<td>信用卡卡號：</td>
		<td><input type="TEXT" id="text1" size="1" maxlength="4" value="">
			-
			<input type="TEXT" id="text2" size="1" maxlength="4" value="">
			-
			<input type="TEXT" id="text3" size="1" maxlength="4" value="">
			-
			<input type="TEXT" id="text4" size="1" maxlength="4" value="">
			
			<input type="hidden" name="cred_card_no" id="cred_card_no" value="" /></td>
	</tr>
	<tr>
		<td>信用卡驗證碼：</td>
		<td><input type="TEXT" name="valid_no" id="valid_no" size="3" maxlength="3"
			value="" /></td>
	</tr>
	<tr>
		<td>信用卡有效時期：</td>
		<td><input type="TEXT" name="valid_date" id="valid_date" size="3" maxlength="5"></td>
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
		<td><fmt:formatDate value="${chef_order_listVO.chef_act_date}" var="formattedDate" 
               				 type="date" pattern="yyyy/MM/dd" />
							${formattedDate} &nbsp 
						<fmt:formatDate value="${chef_order_listVO.chef_act_date}" var="formattedTime" 
               				 type="date" pattern="HH:mm" />
               				 <c:set var="time" value="${formattedTime}" />
               				<c:if test="${time == '10:00'}"> 
							${time}~14:00 
							</c:if>
							 <c:set var="time2" value="${formattedTime}" />
							<c:if test="${time2 == '16:00'}"> 
							${time2}~20:00 
							</c:if></td>
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
		<td><fmt:formatDate value="${chef_order_listVO.chef_ord_date}" var="formattedDate2" 
               				 type="date" pattern="yyyy/MM/dd HH:mm" />
							${formattedDate2} </td>
	</tr>
</table>
<br>

<div class="col-xs-12 col-sm-12 button-wrapper">
	<a href="/AA105G3/front-end/chef_order_list/chefOrderListOfMem.jsp" class="btn btn-danger">取消</a>




<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef_order_list/chef_order_list.do" name="form1">
	<input type="hidden" name="action" value="accept_ord">
	<input type="submit" class="btn btn-primary" value="結帳" onClick="alert('感謝您的光顧！')">
	<input type="hidden" name="chef_ord_no" value="<%=chef_order_listVO.getChef_ord_no()%>">
	<input type="hidden" name="mem_no" value="<%=chef_order_listVO.getMem_no()%>">
	<input type="hidden" name="chef_no" value="<%=chef_order_listVO.getChef_no()%>">
	<input type="hidden" name="chef_ord_cost" value="<%=chef_order_listVO.getChef_ord_cost()%>">
	<input type="hidden" name="chef_act_date" value="<%=chef_order_listVO.getChef_act_date()%>">
	<input type="hidden" name="chef_ord_place" value="<%=chef_order_listVO.getChef_ord_place()%>">
	<input type="hidden" name="chef_ord_cnt" value="<%=chef_order_listVO.getChef_ord_cnt()%>">
	<input type="hidden" name="chef_ord_con" value="2">
	<input type="hidden" name="chef_ord_date" value="<%=chef_order_listVO.getChef_ord_date()%>">
	<button type="button" id="magic">   </button>
	
</FORM>	
	
</div>

		</div>
	</div>
</div>

<footer id="theFooter">
		Copyright &copy; 2016 Java Team 3 
</footer>

</div>
<c:import url="/front-end/chat/inviteChat.jsp" ></c:import>


<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
$().ready(function(){
	$('#magic').click(function(){
		$('#text1').val('111');
		$('#text2').val('222');
		$('#text3').val('333');
		$('#text4').val('444');
		$('#valid_no').val('101');
		$('#valid_date').val('12/30');
		
	})
	
})

</script>




</div>
</body>

</html>