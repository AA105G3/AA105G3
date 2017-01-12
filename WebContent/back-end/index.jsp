<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>後端首頁</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/AA105G3/css/backpageCSS.css">

<style>

.btn-xlarge{
	position: relative;
	margin: 50px 30px;
	/*width: 100%;*/
	height: 100x;
	padding: 10px 10px;
	
	color: white;
	background: #62b1d0;
	border-radius: 15px;
	text-align: left;
}
.btn-xlarge i{
	font-size: 1000%;
}
.btn-xlarge span{
	font-size: 32px;
}
.btn-xlarge:hover{
	color: white;
}
.btn-xlarge td{
	color: white;
}
.span-style{
	display: inline;
	padding-left: 20px;
	font-size: 48px;
}
.text-style{
	font-size: 32px;
}

</style>

</head>

<body>

<c:import url="/back-end/TopNavBar.jsp"></c:import>



<c:import url="/back-end/LeftNavBar.jsp"></c:import>





<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12">





			<a href="#">
				<div class="col-xs-12 col-sm-3 btn btn-xlarge" >
					<table>
						<tr>
							<td>
								<i class="glyphicon glyphicon-book" ></i>
							</td>
							
							<td>
								<p class="span-style">0</p>
							</td>
						</tr>
					</table>
					<div class="text-style">待處理未分類食譜</div>
				</div>
			</a>
			
			<a href="<%=request.getContextPath()%>/product_order/product_order.do?action=getPartForDisplay">
				<div class="col-xs-12 col-sm-3 btn btn-xlarge" >
					<table>
						<tr>
							<td>
								<i class="glyphicon glyphicon-list-alt" ></i>
							</td>
							
							<td>
								<p class="span-style">0</p>
							</td>
						</tr>
					</table>
					<div class="text-style">待處理未完成訂單</div>
				</div>
			</a>
			
			<a href="#">
				<div class="col-xs-12 col-sm-3 btn btn-xlarge" >
					<table>
						<tr>
							<td>
								<i class="glyphicon glyphicon-user" ></i>
							</td>
							
							<td>
								<p class="span-style">0</p>
							</td>
						</tr>
					</table>
					<div class="text-style">待處理私廚審核</div>
				</div>
			</a>




		</div>
	</div>
</div>





<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>