<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chef_schedule.model.*"%>
<%
	Chef_scheduleVO chef_scheduleVO = (Chef_scheduleVO) request.getAttribute("chef_scheduleVO"); //Chef_scheduleServlet.java (Concroller), 存入req的chef_scheduleVO物件 (包括幫忙取出的chef_scheduleVO, 也包括輸入資料錯誤時的chef_scheduleVO物件)
%>
<html>
<head>
<title>私廚行事曆修改 - update_chef_schedule_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="/AA105G3/css/calendar.css">
<script language="JavaScript" src="/AA105G3/js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>私廚行事曆修改 - update_chef_schedule_input.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/chef_schedule/select_page.jsp"><img src="/AA105G3/images/chef/back1.gif" width="100" height="32" border="0">回首頁</a></td>
	</tr>
</table>

<h3>資料修改:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef_schedule/chef_schedule.do" name="form1">
<table border="0">
	<tr>
		<td>私廚編號:<font color=red><b>*</b></font></td>
		<td><%=chef_scheduleVO.getChef_no()%></td>
	</tr>
	<tr>
		<td>非空閒日期:<font color=red><b>*</b></font></td>
		<td><%=chef_scheduleVO.getChef_scd_date()%></td>
	</tr>
	<tr>
		<td>非空閒時段:<font color=red><b>*</b></font></td>
		<td><%=chef_scheduleVO.getChef_scd_mae()%></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>非空閒日期:</td> -->
<!-- 		<td bgcolor="#CCCCFF"> -->
<!-- 		    <input class="cal-TextBox" -->
<%-- 			onFocus="this.blur()" size="9" readonly type="text" name="chef_scd_date" value="<%=chef_scheduleVO.getChef_scd_date()%>"> --%>
<!-- 			<a class="so-BtnLink" -->
<!-- 			href="javascript:calClick();return false;" -->
<!-- 			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);" -->
<!-- 			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);" -->
<!-- 			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','chef_scd_date','BTN_date');return false;"> -->
<!-- 		    <img align="middle" border="0" name="BTN_date"	src="/AA105G3/images/chef/btn_date_up.gif" width="22" height="17" alt="開始日期"></a> -->
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>非空閒時段:</td> -->
<%-- 		<td><input type="TEXT" name="chef_scd_mae" size="45"	value="<%=chef_scheduleVO.getChef_scd_mae()%>" /></td> --%>
<!-- 	</tr> -->
	<tr>
		<td>行事曆內容:</td>
		<td><input type="TEXT" name="chef_scd_cnt" size="45" value="<%=chef_scheduleVO.getChef_scd_cnt()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="chef_no" value="<%=chef_scheduleVO.getChef_no()%>">
<input type="hidden" name="chef_scd_date" value="<%=chef_scheduleVO.getChef_scd_date()%>">
<input type="hidden" name="chef_scd_mae" value="<%=chef_scheduleVO.getChef_scd_mae()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
