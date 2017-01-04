<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chef_refund.model.*"%>
<%
	Chef_refundVO chef_refundVO = (Chef_refundVO) request.getAttribute("chef_refundVO"); //EmpServlet.java (Concroller), 存入req的chef_refundVO物件 (包括幫忙取出的chef_refundVO, 也包括輸入資料錯誤時的chef_refundVO物件)
%>
<html>
<head>
<title>退款資料修改 - update_chef_refund_check.jsp</title></head>
<link rel="stylesheet" type="text/css" href="/AA105G3/css/calendar.css">
<script language="JavaScript" src="/AA105G3/js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>退款資料修改 - update_chef_refund_check.jsp</h3>
		<a href="<%=request.getContextPath()%>/back-end/chef_refund/select_page.jsp"><img src="/AA105G3/images/chef/back1.gif" width="100" height="32" border="0">回首頁</a></td>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef_refund/chef_refund.do" name="form1">
<table border="0">
	<tr>
		<td>退款編號:<font color=red><b>*</b></font></td>
		<td><%=chef_refundVO.getChef_rfd_no()%></td>
	</tr>
	<tr>
		<td>退款百分比:</td>
		<td><input type="TEXT" name="chef_rfd_per" size="45" value="<%=chef_refundVO.getChef_rfd_per()%>" /></td>
	</tr>
	<tr>
		<td>退款審核狀態:</td>
		<td><input type="TEXT" name="chef_ord_chk_cond" size="45" value="<%=chef_refundVO.getChef_ord_chk_cond()%>" /></td>
	</tr>
	<tr>
		<td>退款內容:</td>
		<td><input type="TEXT" name="chef_ord_red_cnt" size="45" value="<%=chef_refundVO.getChef_ord_red_cnt()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update_back">
<input type="hidden" name="chef_rfd_no" value="<%=chef_refundVO.getChef_rfd_no()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
