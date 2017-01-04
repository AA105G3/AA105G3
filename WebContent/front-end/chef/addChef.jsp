<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chef.model.*"%>
<%
ChefVO chefVO = (ChefVO) request.getAttribute("chefVO");
%>

<html>
<head>
<title>私廚資料新增 - addChef.jsp</title></head>
<link rel="stylesheet" type="text/css" href="/AA105G3/css/calendar.css">
<script language="JavaScript" src="<%=request.getContextPath()%>/front-end/chef/js/chef_image.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>私廚資料新增 - addChef.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/front-end/chef/select_page.jsp"><img src="/AA105G3/images/chef/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>私廚資料:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef/chef.do" name="form1" enctype="multipart/form-data">
<table border="0">
	
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="mem_no" size="45" 
			value="<%= (chefVO==null)? "M00000006" : chefVO.getMem_no()%>" /></td>
	</tr>
	<tr>
		<td>私廚匯款帳號銀行代碼:</td>
		<td><input type="TEXT" name="chef_bnk" size="45" 
			value="<%= (chefVO==null)? "700" : chefVO.getChef_bnk()%>" /></td>
	</tr>
	<tr>
		<td>私廚匯款帳號:</td>
		<td><input type="TEXT" name="chef_bnk_ac" size="45"
			value="<%= (chefVO==null)? "01230230263847" : chefVO.getChef_bnk_ac()%>" /></td>
	</tr>
	<tr>
		<td>私廚專長:</td>
		<td><input type="TEXT" name="chef_skill" size="45" 
			value="<%= (chefVO==null)? "各式料理" : chefVO.getChef_skill()%>" /></td>
	</tr>
	
	<tr>
		<td>私廚證照圖:</td>
		<td><input accept="image/*" type="File" name="chef_lic" id="chef_lic" /></td>
	</tr>
	
	<tr>
		<td>私廚照片:</td>
		<td><input accept="image/*" type="File" name="chef_image" id="chef_image" /></td>
	</tr>
	
	<tr>
		<td>私廚申請影片一:</td>
		<td><input accept="" type="File" name="chef_movie1" id="chef_movie1" /></td>
	</tr>
	
	<tr>
		<td>私廚申請影片二:</td>
		<td><input accept="" type="File" name="chef_movie2" id="chef_movie2" /></td>
	</tr>
	
	<tr>
		<td>私廚身分證字號:</td>
		<td><input type="TEXT" name="chef_id" size="45" 
			value="<%= (chefVO==null)? "A123456788" : chefVO.getChef_id()%>" /></td>
	</tr>
	<tr>
		<td>私廚姓名:</td>
		<td><input type="TEXT" name="chef_name" size="45" 
			value="<%= (chefVO==null)? "史提芬周" : chefVO.getChef_name()%>" /></td>
	</tr>
	<tr>
		<td>私廚活動地區:</td>
		<td><input type="TEXT" name="chef_area" size="45" 
			value="<%= (chefVO==null)? "台北市" : chefVO.getChef_area()%>" /></td>
	</tr>
	<tr>
		<td>私廚簡介:</td>
		<td><input type="TEXT" name="chef_intr" size="45" 
			value="<%= (chefVO==null)? "食神" : chefVO.getChef_intr()%>" /></td>
	</tr>
	<tr>
		<td>私廚菜單:</td>
		<td><input type="TEXT" name="chef_menu" size="45" 
			value="<%= (chefVO==null)? "無菜單料理" : chefVO.getChef_menu()%>" /></td>
	</tr>
	<tr>
		<td>私廚菜單圖片一:</td>
		<td><input accept="image/*" type="File" name="chef_reci_image1" id="chef_reci_image1" /></td>
	</tr>
	<tr>
		<td>私廚菜單圖片二:</td>
		<td><input accept="image/*" type="File" name="chef_reci_image2" id="chef_reci_image2" /></td>
	</tr>
	<tr>
		<td>私廚菜單圖片三:</td>
		<td><input accept="image/*" type="File" name="chef_reci_image3" id="chef_reci_image3" /></td>
	</tr>
	<tr>
		<td>私廚菜單圖片四:</td>
		<td><input accept="image/*" type="File" name="chef_reci_image4" id="chef_reci_image4" /></td>
	</tr>
	<tr>
		<td>私廚菜單圖片五:</td>
		<td><input accept="image/*" type="File" name="chef_reci_image5" id="chef_reci_image5" /></td>
	</tr>
	
	<tr>
		<img id="img" src="<%=request.getContextPath()%>/front-end/chef/images/No-image-found.png" width='100'>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
