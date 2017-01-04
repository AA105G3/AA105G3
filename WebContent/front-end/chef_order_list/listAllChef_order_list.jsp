<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef_order_list.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    Chef_order_listService chef_order_listSvc = new Chef_order_listService();
    List<Chef_order_listVO> list = chef_order_listSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有私廚訂單資料 - listAllChef_order_list.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有私廚訂單資料 - ListAllChef_order_list.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/chef_order_list/select_page.jsp"><img src="/AA105G3/images/chef/back1.gif" width="100" height="32" border="0">回首頁</a>
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
		<th>私廚訂單編號</th>
		<th>會員編號</th>
		<th>私廚編號</th>
		<th>訂單金額</th>
		<th>約定時間</th>
		<th>約定地點</th>
		<th>訂單內容</th>
		<th>訂單執行狀態</th>
		<th>私廚評價</th>
		<th>評價內容</th>
		<th>訂單產生時間</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="chef_order_listVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${chef_order_listVO.chef_ord_no}</td>
			<td>${chef_order_listVO.mem_no}</td>
			<td>${chef_order_listVO.chef_no}</td>
			<td>${chef_order_listVO.chef_ord_cost}</td>
			<td>${chef_order_listVO.chef_act_date}</td>
			<td>${chef_order_listVO.chef_ord_place}</td>
			<td>${chef_order_listVO.chef_ord_cnt}</td>
			<td>${chef_order_listVO.chef_ord_con}</td>
			<td>${chef_order_listVO.chef_appr}</td>
			<td>${chef_order_listVO.chef_appr_cnt}</td>
			<td>${chef_order_listVO.chef_ord_date}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef_order_list/chef_order_list.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="chef_ord_no" value="${chef_order_listVO.chef_ord_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef_order_list/chef_order_list.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="chef_ord_no" value="${chef_order_listVO.chef_ord_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
