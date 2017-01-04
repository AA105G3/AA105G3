<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adv.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	AdvService advSvc = new AdvService();
    List<AdvVO> list = advSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有廣告資料 - listAllAdv.jsp</title>
</head>
<body>

<table border='1' cellpadding='5' cellspacing='0' width='1500'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有廣告資料 - listAllAdv.jsp</h3>
		<a href="<%=request.getContextPath()%>/back-end/adv/select_page.jsp">
		<img src="<%=request.getContextPath()%>/back-end/adv/images/back.png" width="70" height="70" border="0">回首頁</a>
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

<table border='1' bordercolor='#CCCCFF' width='1500'>
	<tr>
		<th>廣告編號</th>
		<th>員工編號</th>
		<th>廣告名稱</th>
		<th>圖片名稱</th>
		<th>廣告圖片</th>
		<th>廣告連結</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>

	<c:forEach var="advVO" items="${list}">
		<tr align='center' valign='middle'>
			<td>${advVO.adv_no}</td>
			<td>${advVO.emp_no}</td>
			<td>${advVO.adv_name}</td>
			<td>${advVO.adv_image_name}</td>
			<td><img src="/AA105G3/AdvDBGifReader.do?name=${advVO.adv_no}" width='100'></td>
			<td>${advVO.adv_url}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adv/adv.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="adv_no" value="${advVO.adv_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adv/adv.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="adv_no" value="${advVO.adv_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>