<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chat.model.*"%>
<%
ChatVO chatVO = (ChatVO) request.getAttribute("chatVO");
%>

<html>
<head>
<title>訊息紀錄資料新增 - addChat.jsp</title></head>
<div id="popupcalendar" class="text"></div>

<body>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>訊息紀錄資料新增 - addChat.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/front-end/chat/select_page.jsp">
		   <img src="<%=request.getContextPath()%>/front-end/chat/images/back.png" width="70" height="70" border="0">回首頁</a>
	    </td>
	</tr>
</table>

<h3>資料會員:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chat/chat.do" name="form1">
<table border="0">

	<tr>
		<td>發送人編號:</td>
		<td><input type="TEXT" name="send_no" size="45" 
			value="<%= (chatVO==null)? "M00000001" : chatVO.getSend_no()%>" /></td>
	</tr>
	
	<tr>
		<td>接收人編號:</td>
		<td><input type="TEXT" name="get_no" size="45" 
			value="<%= (chatVO==null)? "M00000002" : chatVO.getGet_no()%>" /></td>
	</tr>
	
	<tr>
		<td>訊息內容:</td>
		<td><input type="TEXT" name="chat_text" size="45" 
			value="<%= (chatVO==null)? "安安你好" : chatVO.getChat_text()%>" /></td>
	</tr>

</table>

<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>
</html>