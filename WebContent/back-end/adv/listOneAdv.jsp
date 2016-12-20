<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.adv.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>廣告資料 - listOneAdv.jsp</title>
</head>
<body>

<table border='1' cellpadding='5' cellspacing='0' width='1500'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>廣告資料 - listOneAdv.jsp</h3>
		<a href="<%=request.getContextPath()%>/back-end/adv/select_page.jsp">
		<img src="<%=request.getContextPath()%>/back-end/adv/images/back.png" width="70" height="70" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='1500'>
	<tr>
		<th>廣告編號</th>
		<th>員工編號</th>
		<th>廣告名稱</th>
		<th>圖片名稱</th>
		<th>廣告圖片</th>
		<th>廣告連結</th>
	</tr>
	<tr align='center' valign='middle'>
		<td>${advVO.adv_no}</td>
		<td>${advVO.emp_no}</td>
		<td>${advVO.adv_name}</td>
		<td>${advVO.adv_image_name}</td>
		<td><img src="/AA105G3/AdvDBGifReader.do?name=${advVO.adv_no}" width='100'></td>
		<td>${advVO.adv_url}</td>
	</tr>
</table>

</body>
</html>