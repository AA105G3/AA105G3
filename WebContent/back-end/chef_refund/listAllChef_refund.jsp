<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef_refund.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    Chef_refundService chef_refundSvc = new Chef_refundService();
    List<Chef_refundVO> list = chef_refundSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有退款資料 - listAllChef_refund.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有退款資料 - ListAllChef_refund.jsp</h3>
		<a href="<%=request.getContextPath()%>/back-end/chef_refund/select_page.jsp"><img src="/AA105G3/images/chef/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

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

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>退款編號</th>
		<th>訂單編號</th>
		<th>員工編號</th>
		<th>退款百分比</th>
		<th>會員匯款帳號銀行代碼</th>
		<th>會員匯款帳號</th>
		<th>退款審核狀態</th>
		<th>審核內容</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="chef_refundVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>"${chef_refundVO.chef_rfd_no}">${chef_refundVO.chef_rfd_no}</td>
			<td>${chef_refundVO.chef_ord_no}</td>
			<td>${chef_refundVO.emp_no}</td>
			<td>${chef_refundVO.chef_rfd_per}</td>
			<td>${chef_refundVO.mem_bnk}</td>
			<td>${chef_refundVO.mem_bnk_ac}</td>
			<td>${chef_refundVO.chef_ord_chk_cond}</td>
			<td>${chef_refundVO.chef_ord_red_cnt}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef_refund/chef_refund.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="chef_rfd_no" value="${chef_refundVO.chef_rfd_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef_refund/chef_refund.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="chef_rfd_no" value="${chef_refundVO.chef_rfd_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
