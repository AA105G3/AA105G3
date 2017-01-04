<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.chef_schedule.model.*"%>
<%
Chef_scheduleVO chef_scheduleVO = (Chef_scheduleVO) request.getAttribute("chef_scheduleVO"); //Chef_scheduleServlet.java(Concroller), 存入req的chef_scheduleVO物件
%>
<html>
<head>
<title>私廚行事曆資料 - listOneChef_schedule.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>私廚行事曆資料 - ListOneChef_schedule.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/chef_schedule/select_page.jsp"><img src="/AA105G3/images/chef/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>私廚編號</th>
		<th>非空閒日期</th>
		<th>非空閒時段</th>
		<th>行事曆內容</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=chef_scheduleVO.getChef_no()%></td>
		<td><%=chef_scheduleVO.getChef_scd_date()%></td>
		<td><%=chef_scheduleVO.getChef_scd_mae()%></td>
		<td><%=chef_scheduleVO.getChef_scd_cnt()%></td>
	</tr>
</table>

</body>
</html>
