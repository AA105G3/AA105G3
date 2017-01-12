<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.adv.model.*"%>
<%
AdvVO advVO = (AdvVO) request.getAttribute("advVO");
%>

<html>
<head>
<title>廣告資料新增 - addAdv.jsp</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/AA105G3/css/backpageCSS.css">

<style>

#mainTable{
	width : 600px;
	height : 400px;
	margin : 0px auto;
	/* background-color : #f5deb3; */
}
th, td {
	height : 50px;
	max-width : 200px;
	padding-left : 60px;
}
#center{
	padding-left : 0px;
	text-align : center;
}
#productTextarea{
	resize : none;
	width : 250px;
	height : 150px;
}
#img{
	height : 300px;
	width : auto;
}
.btn-style{
	padding-top : 50px;
}
.table-style{
	padding-top : 50px;
}
</style>

</head>
<script language="JavaScript" src="<%=request.getContextPath()%>/back-end/adv/js/adv_image.js"></script>
<div id="popupcalendar" class="text"></div>

<body>





<c:import url="/back-end/TopNavBar.jsp"></c:import>



<c:import url="/back-end/LeftNavBar.jsp"></c:import>





<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-sm-push-1 table-style">





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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adv/adv.do" name="form1" enctype="multipart/form-data">
<table border='1' bordercolor='#CCCCFF' id="mainTable" cellspacing="0">

	<tr>
		<td colspan="2" id="center" align="center" valign="center"><h2>新增廣告</h2></td>
	</tr>

	<%-- <tr>
		<td>
			員工編號：
			<input type="TEXT" name="emp_no" size="45" value="<%= (advVO==null)? "1001" : advVO.getEmp_no()%>" />
		</td>
	</tr> --%>
	
	<tr>
		<td>
			廣告名稱：
			<input type="TEXT" name="adv_name" size="45" value="<%= (advVO==null)? "新的廣告" : advVO.getAdv_name()%>" />
		</td>
	</tr>
	
	<tr>
		<td>圖片名稱：
			<input type="TEXT" name="adv_image_name" size="45" 
			value="<%= (advVO==null)? "新圖名稱" : advVO.getAdv_image_name()%>" />
		</td>
	</tr>

	<tr>
		<td>
			廣告連結：
			<input type="TEXT" name="adv_url" size="45" value="<%= (advVO==null)? "www.foodtime.com.tw" : advVO.getAdv_url()%>" />
		</td>
	</tr>
	
	<tr>
		<td>廣告圖片：
			<input accept="image/*" type="FILE" name="adv_image" id="adv_image" />
			<div id="center"><img id="img" src="<%=request.getContextPath()%>/back-end/adv/images/No-image-found.png"></div>
		</td>
	</tr>

</table>

<br>

<div class="text-center btn-style">
	<a class="btn btn-default" href="/AA105G3/back-end/adv/AdvManagement.jsp">取消新增</a>
	
	
	<input type="hidden" name="action" value="insert">
	<input type="hidden" name="emp_no" value="1001">
	<input class="btn btn-primary" type="submit" value="確認新增">
</div>

</FORM>





		</div>
	</div>
</div>







		
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>





</body>

</html>