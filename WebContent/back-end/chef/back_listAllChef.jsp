<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    ChefService chefSvc = new ChefService();
    List<ChefVO> list = chefSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有私廚資料 - listAllChef.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有私廚資料 - ListAllChef.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/chef/select_page.jsp"><img src="/AA105G3/images/chef/back1.gif" width="100" height="32" border="0">回首頁</a>
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

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>私廚編號</th>
		<th>會員編號</th>
		<th>私廚審核情況</th>
		<th>匯款帳號銀行代碼</th>
		<th>匯款帳號</th>
		<th>私廚專長</th>
		<th>私廚證照</th>
		<th>私廚照片</th>
		<th>申請影片一</th>
		<th>申請影片一</th>
		<th>身份證字號</th>
		<th>真實姓名</th>
		<th>活動地區</th>
		<th>私廚簡介</th>
		<th>參考菜單</th>
		<th>菜單圖片一</th>
		<th>菜單圖片二</th>
		<th>菜單圖片三</th>
		<th>菜單圖片四</th>
		<th>菜單圖片五</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="chefVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${chefVO.chef_no}</td>
			<td>${chefVO.mem_no}</td>
			<td>${chefVO.chef_chk_cond}</td>
			<td>${chefVO.chef_bnk}</td>
			<td>${chefVO.chef_bnk_ac}</td>
			<td>${chefVO.chef_skill}</td>
			<td><img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_lic=123"></td>
			<td><img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_image=123"></td>
			<td>
				<video id = "chef_movie1" controls>
					<source src = "<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_movie1=123" >
				</video>
			</td>
			<td>
				<video id = "chef_movie2" controls>
					<source src = "<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_movie2=123" >
				</video>
			</td>			
			<td>${chefVO.chef_id}</td>
			<td>${chefVO.chef_name}</td>
			<td>${chefVO.chef_area}</td>
			<td>${chefVO.chef_intr}</td>
			<td>${chefVO.chef_menu}</td>
			<td><img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image1=123"></td>
			<td><img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image2=123"></td>
			<td><img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image3=123"></td>
			<td><img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image4=123"></td>
			<td><img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image5=123"></td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef/chef.do">
			     <input type="submit" value="通過審核">
			     <input type="hidden" name="chef_no" value="${chefVO.chef_no}">
			     <input type="hidden" name="chef_chk_cond"	value="1">
			     <input type="hidden" name="action"	value="update_chk_cond"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef/chef.do">
			    <input type="submit" value="不通過刪除">
			    <input type="hidden" name="chef_no" value="${chefVO.chef_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
