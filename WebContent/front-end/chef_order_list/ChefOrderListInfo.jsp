<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="chef_order_listVO" scope="request" class="com.chef_order_list.model.Chef_order_listVO" />
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="chefSvc" scope="page" class="com.chef.model.ChefService" />
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>訂單明細</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<style type="text/css">
		body{
			background: #efede8;
			padding-top: 50px;
			position : relative;
			height : 100%;
		}
		html{
			height : 100%;
		}	
		
		table.chefOrderListInfo{
			margin-left: auto;
			margin-right: auto;
			background: #f2f2f2;
			border: 1px solid #d3d4d5;
			margin-bottom: 20px; 
		}
		.chefOrderListInfo th{
			font-size: 24px;
			padding: 5px;
			border-bottom:  1px solid #d3d4d5;
		}
		.chefOrderListInfo td{
			width: 80%;
			padding: 5px 10px;
			font-size: 24px;
			border-bottom:  1px solid #d3d4d5;
		}
		td.ord-content{
			height: 300px;
			vertical-align: top;
		}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12 text-center order-list-title">
					<h2>私廚訂單明細</h2>
				</div>
			</div>
			<div class="col-xs-12 col-sm-12 ">
				<table class="chefOrderListInfo">
					<tr>
						<th>下訂會員：</td>
						<td>${memberSvc.getOneMember(chef_order_listVO.mem_no).mem_name}</td>
					</tr>
					<tr>
						<th>執行私廚：</td>
						<td>${chefSvc.getOneChef(chef_order_listVO.chef_no).chef_name}</td>
					</tr>
					<tr>
						<th>執行時間：</td>
						<td>
						<fmt:formatDate value="${chef_order_listVO.chef_act_date}" var="formattedDate" 
               				 type="date" pattern="yyyy/MM/dd" />
							${formattedDate} &nbsp 
						<fmt:formatDate value="${chef_order_listVO.chef_act_date}" var="formattedTime" 
               				 type="date" pattern="HH:mm" />
               				 <c:set var="time" value="${formattedTime}" />
               				<c:if test="${time == '10:00'}"> 
							${time}~14:00 
							</c:if>
							 <c:set var="time2" value="${formattedTime}" />
							<c:if test="${time2 == '16:00'}"> 
							${time2}~20:00 
							</c:if>
						</td>
					</tr>
					<tr>
						<th>執行地點：</td>
						<td>${chef_order_listVO.chef_ord_place}</td>
					</tr>
					<tr>
						<th>交易金額：</th>
						<td>
						<fmt:parseNumber var="dollar" integerOnly="true" type="number" value="${chef_order_listVO.chef_ord_cost}" />
						<c:set var="money" value="${dollar}" />
							<c:if test="${money!='0'}">
							${money}
							</c:if>
							<c:if test="${money=='0'}">
							待私廚填寫
							</c:if>
						</td>
					</tr>
					<tr>
						<th colspan="2" class="text-center ">訂單內容說明：</th>
					</tr>
					<tr>
						<td colspan="2" class="ord-content">
							${chef_order_listVO.chef_ord_cnt}
						</td>
					</tr>
					<tr>
						<th>下訂時間：</th>
						<td>
							<fmt:formatDate value="${chef_order_listVO.chef_ord_date}" var="formattedDate2" 
               				 type="date" pattern="yyyy/MM/dd HH:mm" />
							${formattedDate2} 
						</td>
					</tr>

				</table>
				<div class="text-center"><button class="btn btn-success btn-lg goBack">返回上一頁</button></div>
			</div>
		</div>
		
		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$('.goBack').click(function() {
				window.history.go(-1);
			});
		</script>
	</body>
</html>