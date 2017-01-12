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
<title>所有廣告資料</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/AA105G3/css/backpageCSS.css">

<style>
.table-style{
	padding-top: 75px;
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
</style>

</head>
<body>





<c:import url="/back-end/TopNavBar.jsp"></c:import>



<c:import url="/back-end/LeftNavBar.jsp"></c:import>





<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-sm-push-2 table-style">





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





<div class="search-style">
	<div class="col-xs-12 col-sm-2">
		<a class="btn btn-primary" href="/AA105G3/back-end/adv/AddAdv.jsp">新增廣告</a>
	</div>
	




	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adv/adv.do">
	
		<div class="col-xs-12 col-sm-4 input-group">
			<div class="input-group-addon">
				輸入廣告編號：
			</div>
			<input type="text" name="adv_no" class="form-control">
			<input type="hidden" name="action" value="getOne_For_Display">
			<div class="input-group-btn">
				<button class="btn btn-primary">查詢資料</button>
			</div>
		</div>
	
	</FORM>
</div>





<table border='1' bordercolor='#CCCCFF' width='1100'>
	<tr class="th-style">
		<th>廣告圖片</th>
		<th>廣告編號</th>
		<th>員工編號</th>
		<th>廣告名稱</th>
		<th>圖片名稱</th>
		<th>廣告連結</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>

	<%@ include file="pages/page1.file" %> 
	<c:forEach var="advVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(advVO.adv_no==param.adv_no) ? 'bgcolor=#f5deb3':''}>
			<td><img src="/AA105G3/AdvDBGifReader.do?name=${advVO.adv_no}" width='100'></td>
			<td>${advVO.adv_no}</td>
			<td>${advVO.emp_no}</td>
			<td>${advVO.adv_name}</td>
			<td>${advVO.adv_image_name}</td>
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
	</c:forEach>
</table>

<div class="col-xs-12 col-sm-4 col-sm-push-4 page-style">
	<%@ include file="pages/page2.file" %>
</div>



		</div>
	</div>
</div>




		
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>





</body>
</html>