<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.chef.model.*"%>
<%
ChefVO chefVO = (ChefVO) request.getAttribute("chefVO"); //ChefServlet.java(Concroller), 存入req的chefVO物件
%>
<html>
<head>
<title>私廚資料 - listOneChef.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>私廚資料 - ListOneChef.jsp</h3>
		<a href="<%=request.getContextPath()%>/front-end/chef/select_page.jsp"><img src="/AA105G3/images/chef/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>私廚編號</th>
		<th>會員編號</th>
		<th>私廚審核情況</th>
		<th>匯款帳號銀行代碼</th>
		<th>匯款帳號</th>
		<th>私廚專長</th>
		<th>私廚證照</th>
		<th>私廚照片</th>
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
	<tr align='center' valign='middle'>
		<td><%=chefVO.getChef_no()%></td>
		<td><%=chefVO.getMem_no()%></td>
		<td><%=chefVO.getChef_chk_cond()%></td>
		<td><%=chefVO.getChef_bnk()%></td>
		<td><%=chefVO.getChef_bnk_ac()%></td>
		<td><%=chefVO.getChef_skill()%></td>
		<td><img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_lic=123"></td>
		<td><img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_image=123"></td>
		<td><%=chefVO.getChef_id()%></td>
		<td><%=chefVO.getChef_name()%></td>
		<td><%=chefVO.getChef_area()%></td>
		<td><%=chefVO.getChef_intr()%></td>
		<td><%=chefVO.getChef_menu()%></td>
		<td><img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image1=123"></td>
		<td><img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image2=123"></td>
		<td><img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image3=123"></td>
		<td><img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image4=123"></td>
		<td><img src="<%=request.getContextPath()%>/chef/chefImage.do?chef_no=${chefVO.chef_no}&chef_reci_image5=123"></td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef/chef.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="chef_no" value="${chefVO.chef_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</td>
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef/chef.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="chef_no" value="${chefVO.chef_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
		</td>
	</tr>
</table>

</body>
</html>
