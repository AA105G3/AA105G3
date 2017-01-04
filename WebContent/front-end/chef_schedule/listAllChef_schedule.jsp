<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef_schedule.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    Chef_scheduleService chef_scheduleSvc = new Chef_scheduleService();
    List<Chef_scheduleVO> list = chef_scheduleSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有私廚行事曆資料 - listAllChef_schedule.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有私廚行事曆資料 - ListAllChef_schedule.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/chef_schedule/select_page.jsp"><img src="/AA105G3/images/chef/back1.gif" width="100" height="32" border="0">回首頁</a>
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
		<th>私廚編號</th>
		<th>非空閒日期</th>
		<th>非空閒時段</th>
		<th>行事曆內容</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="chef_scheduleVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${chef_scheduleVO.chef_no}</td>
			<td>${chef_scheduleVO.chef_scd_date}</td>
			<td>${chef_scheduleVO.chef_scd_mae}</td>
			<td>${chef_scheduleVO.chef_scd_cnt}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef_schedule/chef_schedule.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="chef_no" value="${chef_scheduleVO.chef_no}">
			     <input type="hidden" name="chef_scd_date" value="${chef_scheduleVO.chef_scd_date}">
			     <input type="hidden" name="chef_scd_mae" value="${chef_scheduleVO.chef_scd_mae}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef_schedule/chef_schedule.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="chef_no" value="${chef_scheduleVO.chef_no}">
			    <input type="hidden" name="chef_scd_date" value="${chef_scheduleVO.chef_scd_date}">
			    <input type="hidden" name="chef_scd_mae" value="${chef_scheduleVO.chef_scd_mae}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
