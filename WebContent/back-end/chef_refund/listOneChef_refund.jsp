<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.chef_refund.model.*"%>
<%
Chef_refundVO chef_refundVO = (Chef_refundVO) request.getAttribute("chef_refundVO"); //Chef_refundServlet.java(Concroller), 存入req的chef_refundVO物件
%>
<html>
<head>
<title>員工資料 - listOneChef_refund.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料 - ListOneChef_refund.jsp</h3>
		<a href="<%=request.getContextPath()%>/back-end/chef_refund/select_page.jsp"><img src="/AA105G3/images/chef/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>退款編號</th>
		<th>訂單編號</th>
		<th>員工編號</th>
		<th>退款百分比</th>
		<th>會員匯款帳號銀行代碼</th>
		<th>會員匯款帳號</th>
		<th>退款審核狀態</th>
		<th>審核內容</th>
	</tr>
	<tr align='center' valign='middle'>
		<td>${chef_refundVO.chef_rfd_no}</td>
		<td>${chef_refundVO.chef_ord_no}</td>
		<td>${chef_refundVO.emp_no}</td>
		<td>${chef_refundVO.chef_rfd_per}</td>
		<td>${chef_refundVO.mem_bnk}</td>
		<td>${chef_refundVO.mem_bnk_ac}</td>
		<td>${chef_refundVO.chef_ord_chk_cond}</td>
		<td>${chef_refundVO.chef_ord_red_cnt}</td>
	</tr>
</table>

</body>
</html>
