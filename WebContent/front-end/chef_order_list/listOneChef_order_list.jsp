<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.chef_order_list.model.*"%>
<%
Chef_order_listVO chef_order_listVO = (Chef_order_listVO) request.getAttribute("chef_order_listVO"); //Chef_order_listServlet.java(Concroller), 存入req的chef_order_listVO物件
%>
<html>
<head>
<title>私廚訂單資料 - listOneChef_order_list.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>私廚訂單資料 - ListOneChef_order_list.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/chef_order_list/select_page.jsp"><img src="/AA105G3/images/chef/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
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
	</tr>
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
	</tr>
</table>

</body>
</html>
