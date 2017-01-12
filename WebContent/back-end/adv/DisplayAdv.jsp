<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.adv.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>廣告資料</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/AA105G3/css/backpageCSS.css">

<style>
.table-style{
	padding-top: 150px;
}
th{
	text-align: center;
	height: 50px;
}
.search-style{
	padding-bottom: 30px;
}
.th-style{
	background: #e2fede;
}
.page-style{
	padding-top: 30px;
}
.btn-style{
	padding-top : 50px;
}
</style>

</head>
<body>





<c:import url="/back-end/TopNavBar.jsp"></c:import>



<c:import url="/back-end/LeftNavBar.jsp"></c:import>





<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-sm-push-2 table-style">





<table border='1' bordercolor='#CCCCFF' width='1000'>
	<tr class="th-style">
		<th>廣告編號</th>
		<th>員工編號</th>
		<th>廣告名稱</th>
		<th>圖片名稱</th>
		<th>廣告圖片</th>
		<th>廣告連結</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<tr align='center' valign='middle'>
		<td>${advVO.adv_no}</td>
		<td>${advVO.emp_no}</td>
		<td>${advVO.adv_name}</td>
		<td>${advVO.adv_image_name}</td>
		<td><img src="/AA105G3/AdvDBGifReader.do?name=${advVO.adv_no}" width='100'></td>
		<td>${advVO.adv_url}</td>
		<td>
		  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adv/adv.do">
		     <input type="submit" value="修改" class="btn btn-primary">
		     <input type="hidden" name="adv_no" value="${advVO.adv_no}">
		     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</td>
		<td>
		  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adv/adv.do">
		    <input type="submit" value="刪除" class="btn btn-primary">
		    <input type="hidden" name="adv_no" value="${advVO.adv_no}">
		    <input type="hidden" name="action"value="delete"></FORM>
		</td>
	</tr>
</table>





		</div>
	</div>
</div>





<div class="text-center btn-style">
	<form>
		<a class="btn btn-primary" href="/AA105G3/back-end/adv/AdvManagement.jsp">返回</a>
	</form>
</div>






		
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>





</body>
</html>