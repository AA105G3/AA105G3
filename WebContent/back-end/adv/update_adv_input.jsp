<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.adv.model.*"%>
<%
	AdvVO advVO = (AdvVO) request.getAttribute("advVO"); //AdvServlet.java (Concroller), 存入req的advVO物件 (包括幫忙取出的advVO, 也包括輸入資料錯誤時的advVO物件)
%>
<html>
<head>
<title>廣告資料修改 - update_adv_input.jsp</title></head>
<script language="JavaScript" src="<%=request.getContextPath()%>/back-end/adv/js/adv_image.js"></script>
<div id="popupcalendar" class="text"></div>

<body>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>廣告資料修改 - update_adv_input.jsp</h3>
		<a href="<%=request.getContextPath()%>/back-end/adv/select_page.jsp">
		<img src="<%=request.getContextPath()%>/back-end/adv/images/back.png" width="70" height="70" border="0">回首頁</a></td>
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

<FORM METHOD="post" ACTION="adv.do" name="form1" enctype="multipart/form-data">
<table border="0">
	<tr>
		<td>廣告編號:<font color=red><b>*</b></font></td>
		<td><%=advVO.getAdv_no()%></td>
	</tr>
	<tr>
		<td>員工編號:</td>
		<td><input type="TEXT" name="emp_no" size="45" value="<%=advVO.getEmp_no()%>" /></td>
	</tr>
	
	<tr>
		<td>廣告名稱:</td>
		<td><input type="TEXT" name="adv_name" size="45" value="<%=advVO.getAdv_name()%>" /></td>
	</tr>
	
	<tr>
		<td>圖片名稱:</td>
		<td><input type="TEXT" name="adv_image_name" size="45" value="<%=advVO.getAdv_image_name()%>" />
	</tr>
	
	<tr>
		<td>廣告圖片:</td>
		<td><input accept="image/*" type="FILE" name="adv_image" id="adv_image" /></td>
	</tr>

	<tr>
		<td>廣告連結:</td>
		<td><input type="TEXT" name="adv_url" size="45" value="<%=advVO.getAdv_url()%>" />
	</tr>
	
	<tr>
		<img id="img" src="<%=request.getContextPath()%>/back-end/adv/images/No-image-found.png" width='100'>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="adv_no" value="<%=advVO.getAdv_no()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>