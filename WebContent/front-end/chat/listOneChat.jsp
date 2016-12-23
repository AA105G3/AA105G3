<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.chat.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>訊息紀錄資料 - listOneChat.jsp</title>
</head>
<body>

<table border='1' cellpadding='5' cellspacing='0' width='1500'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>訊息紀錄資料 - listOneChat.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/chat/select_page.jsp">
		<img src="<%=request.getContextPath()%>/front-end/chat/images/back.png" width="70" height="70" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='1500'>
	<tr>
		<th>訊息編號</th>
		<th>發送人編號</th>
		<th>接收人編號</th>
		<th>訊息內容</th>
		<th>發送時間</th>
		<th>刪除</th>
	</tr>
	<tr align='center' valign='middle'>
		<td>${chatVO.chat_no}</td>
		<td>${chatVO.send_no}</td>
		<td>${chatVO.get_no}</td>
		<td>${chatVO.chat_text}</td>
		<td>${chatVO.chat_date}</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chat/chat.do">
				<input type="submit" value="刪除">
				<input type="hidden" name="chat_no" value="${chatVO.chat_no}">
				<input type="hidden" name="action"value="delete"></FORM>
		</td>
	</tr>
</table>

</body>
</html>