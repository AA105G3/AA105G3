<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chef_order_list.model.*"%>
<%
Chef_order_listVO chef_order_listVO = (Chef_order_listVO) request.getAttribute("chef_order_listVO");
%>

<html>
<head>
<title>私廚訂單資料新增 - addChef_order_list.jsp</title>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jQuery UI Datepicker - Restrict date range</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker({ minDate: 0, maxDate: "+1M +10D" });
  } );
  $( function() {
	    $( "#datepicker" ).datepicker();
	    $( "#format" ).on( "change", function() {
	      $( "#datepicker" ).datepicker( "option", "dateFormat", $( this ).val() );
	    });
	  } );
  </script>

</head>
<link rel="stylesheet" type="text/css" href="/AA105G3/css/calendar.css">
<script language="JavaScript" src="/AA105G3/js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>私廚訂單資料新增 - addChef_order_list.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/front-end/chef_order_list/select_page.jsp"><img src="/AA105G3/images/chef/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>私廚訂單資料:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef_order_list/chef_order_list.do" name="form1">
<table border="0">

<%-- 	<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>會員編號:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="mem_no"> -->
<%-- 			<c:forEach var="memberVO" items="${memberSvc.all}"> --%>
<%-- 				<option value="${memberVO.mem_no}" ${(chef_order_listVO.mem_no==memberVO.mem_no)? 'selected':'' } >${memberVO.mem_name} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->
	<tr>
		<td>下訂會員編號:</td>
		<td><input type="TEXT" name="mem_no" size="45" 
			value="<%= (chef_order_listVO==null)? "M00000001" : chef_order_listVO.getMem_no()%>" /></td>
	</tr>
	
	<jsp:useBean id="chefSvc" scope="page" class="com.chef.model.ChefService" />
	<tr>
		<td>服務私廚編號:<font color=red><b>*</b></font></td>
		<td><select size="1" name="chef_no">
			<c:forEach var="chefVO" items="${chefSvc.all}">
				<option value="${chefVO.chef_no}" ${(chef_order_listVO.chef_no==chefVO.chef_no)? 'selected':'' } >${chefVO.chef_no}
			</c:forEach>
		</select></td>
	</tr>
	
	<tr>
		<td>私廚訂單金額:</td>
		<td><input type="TEXT" name="chef_ord_cost" size="45"
			value="<%= (chef_order_listVO==null)? "2000" : chef_order_listVO.getChef_ord_cost()%>" /></td>
	</tr>
		
	<tr>
		<%Timestamp appoint_date = new Timestamp(System.currentTimeMillis());%>
		<td>約定日期:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="chef_act_date" value="<%= (chef_order_listVO==null)? appoint_date : chef_order_listVO.getChef_act_date()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','chef_act_date','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="/AA105G3/images/chef/btn_date_up.gif" width="22" height="17" alt="開始日期"></a>
		</td>
	</tr>
	
	<tr>
		<td>約定地點:</td>
		<td><input type="TEXT" name="chef_ord_place" size="45"
			value="<%= (chef_order_listVO==null)? "台北市" : chef_order_listVO.getChef_ord_place()%>" /></td>
	</tr>
	<tr>
		<td>訂單內容:</td>
		<td><input type="TEXT" name="chef_ord_cnt" size="45"
			value="<%= (chef_order_listVO==null)? "台灣小吃" : chef_order_listVO.getChef_ord_con()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

<p>日期: <input type="text" id="datepicker" placeholder="請點擊" value="111"></p>

<p>Format options:<br>
  <select id="format">
    <option value="mm/dd/yy">Default - mm/dd/yy</option>
    <option value="yy-mm-dd">ISO 8601 - yy-mm-dd</option>
    <option value="d M, y">Short - d M, y</option>
    <option value="d MM, y">Medium - d MM, y</option>
    <option value="DD, d MM, yy">Full - DD, d MM, yy</option>
  </select>
</p>

<p>
  <select id="hour">
    <option value="01">01</option>
    <option value="02">02</option>
    <option value="03">03</option>
    <option value="04">04</option>
    <option value="05">05</option>
    <option value="06">06</option>
    <option value="07">07</option>
    <option value="08">08</option>
    <option value="09">09</option>
    <option value="10">10</option>
    <option value="11">11</option>
    <option value="12">12</option>
    <option value="13">13</option>
    <option value="14">14</option>
    <option value="15">15</option>
    <option value="16">16</option>
    <option value="17">17</option>
    <option value="18">18</option>
    <option value="19">19</option>
    <option value="20">20</option>
    <option value="21">21</option>
    <option value="22">22</option>
    <option value="23">23</option>
    <option value="00">00</option>    
  </select>
  	 時
  <select id="minute">
    <option value="00">00</option>
    <option value="10">10</option>
    <option value="20">20</option>
    <option value="30">30</option>
    <option value="40">40</option>
    <option value="50">50</option>
  </select>
 	 分
</p>

<script>
var app_time
app_time=document.getElementById("hour").value 
</script>
 
<input type="text" id="" value="app_time">



</body>
</html>

