<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chef.model.*"%>
<%
	ChefVO chefVO = (ChefVO) request.getAttribute("chefVO"); //EmpServlet.java (Concroller), �s�Jreq��chefVO���� (�]�A�������X��chefVO, �]�]�A��J��ƿ��~�ɪ�chefVO����)
%>
<html>
<head>
<title>�p�p��ƭק� - update_chef_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="/AA105G3/css/calendar.css">
<script language="JavaScript" src="/AA105G3/css/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�p�p��ƭק� - update_chef_input.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/chef/select_page.jsp"><img src="/AA105G3/images/chef/back1.gif" width="100" height="32" border="0">�^����</a></td>
	</tr>
</table>

<h3>��ƭק�:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
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
		<td>�p�p�s��:<font color=red><b>*</b></font></td>
		<td><%=chefVO.getChef_no()%></td>
	</tr>

	<tr>
		<td>�״ڱb���Ȧ�N�X:</td>
		<td><input type="TEXT" name="chef_bnk" size="45"	value="<%=chefVO.getChef_bnk()%>" /></td>
	</tr>
	<tr>
		<td>�״ڱb��:</td>
		<td><input type="TEXT" name="chef_bnk_ac" size="45"	value="<%=chefVO.getChef_bnk_ac()%>" /></td>
	</tr>
	<tr>
		<td>�p�p�M��:</td>
		<td><input type="TEXT" name="chef_skill" size="45" value="<%=chefVO.getChef_skill()%>" /></td>
	</tr>
	
	<tr>
		<td>�p�p�ҷ�:</td>
		<td><input type="File" name="chef_lic" size="45" value="<%=chefVO.getChef_lic()%>" /></td>
	</tr>
	<tr>
		<td>�p�p�Ӥ�:</td>
		<td><input type="File" name="chef_image" size="45" value="<%=chefVO.getChef_image()%>" /></td>
	</tr>
	<tr>
		<td>�ӽмv���@:</td>
		<td><input type="File" name="chef_movie1" size="45" value="<%=chefVO.getChef_movie1()%>" /></td>
	</tr>
	<tr>
		<td>�ӽмv���G:</td>
		<td><input type="File" name="chef_movie2" size="45" value="<%=chefVO.getChef_movie2()%>" /></td>
	</tr>
	
	<tr>
		<td>�p�p�m�W:</td>
		<td><input type="TEXT" name="chef_name" size="45" value="<%=chefVO.getChef_name()%>" /></td>
	</tr>
	<tr>
		<td>���ʰϰ�:</td>
		<td><input type="TEXT" name="chef_area" size="45" value="<%=chefVO.getChef_area()%>" /></td>
	</tr>
	<tr>
		<td>�p�p²��:</td>
		<td><input type="TEXT" name="chef_intr" size="45" value="<%=chefVO.getChef_intr()%>" /></td>
	</tr>
	<tr>
		<td>�Ѧҵ��:</td>
		<td><input type="TEXT" name="chef_menu" size="45" value="<%=chefVO.getChef_menu()%>" /></td>
	</tr>
	<tr>
		<td>���Ϥ��@:</td>
		<td><input type="File" name="chef_reci_image1" size="45" value="<%=chefVO.getChef_reci_image1()%>" /></td>
	</tr>
	<tr>
		<td>���Ϥ��G:</td>
		<td><input type="File" name="chef_reci_image2" size="45" value="<%=chefVO.getChef_reci_image2()%>" /></td>
	</tr>
	<tr>
		<td>���Ϥ��T:</td>
		<td><input type="File" name="chef_reci_image3" size="45" value="<%=chefVO.getChef_reci_image3()%>" /></td>
	</tr>
	<tr>
		<td>���Ϥ��|:</td>
		<td><input type="File" name="chef_reci_image4" size="45" value="<%=chefVO.getChef_reci_image4()%>" /></td>
	</tr>
	<tr>
		<td>���Ϥ���:</td>
		<td><input type="File" name="chef_reci_image5" size="45" value="<%=chefVO.getChef_reci_image5()%>" /></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="chef_no" value="<%=chefVO.getChef_no()%>">
<input type="hidden" name="chef_id" value="<%=chefVO.getChef_id()%>">
<%-- <input type="hidden" name="chef_intr" value="<%=chefVO.getChef_intr()%>"> --%>
<%-- <input type="hidden" name="chef_menu" value="<%=chefVO.getChef_menu()%>"> --%>
<input type="submit" value="�e�X�ק�"></FORM>

</body>
</html>
