<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="chef_order_listVO" scope="request" class="com.chef_order_list.model.Chef_order_listVO" />
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="chefSvc" scope="page" class="com.chef.model.ChefService" />
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>填寫私廚訂單金額</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/AA105G3/css/frontpageCSS.css">
    <!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<style type="text/css" media="screen">
    html, body{
		background : white;
		height : 100%;
	}
   
    
    .front-style {
        background: #f5deb3;
        width: 175px;
    }
    
   
    #theFooter{

		position : absolute;
		bottom : 0px;
		width : 100%;
		
		background: #222222;
		color:#fff ;
		font-size: 26px;
		font-family: Reklame;
		text-align: center;
	}
	.title-style{
		padding-top : 20px;
		padding-bottom : 50px;
	}
	.button-style{
		padding-top : 10px;
	}
	body{
		background: #efede8;
		padding-top: 50px;
		position : relative;
		height : 100%;
	}
	span{
		color:red;
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
	form{
		display:inline;
	}

</style>

</head>
<link rel="stylesheet" type="text/css" href="/AA105G3/css/calendar.css">
<script language="JavaScript" src="/AA105G3/js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body >




<!--START SCROLL TOP BUTTON -->
<header>    
    <c:import url="/front-end/frontNavbar.jsp"></c:import>
</header>
<%-- 錯誤表列 --%>


	<div class="container">
        <div class="row">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 text-center title-style">
                        <h2>私廚訂單金額修改：</h2>
                    </div>
                </div>
            </div>
            
            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef_order_list/chef_order_list.do" name="form1">
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
						<th><span>*交易金額：(必填)<span></span></th>
						<td>
						<input type="TEXT" name="chef_ord_cost" size="45" value="" placeholder="請填寫此訂單金額"/>
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
	                
	                
	                
	                <div class="col-xs-12 col-sm-12 text-center button-style">
	                	<button class="btn btn-danger goBack " type="button">取消</button>
		                <c:if test="${chef_order_listVO.chef_ord_con != '2'}">
			                
						     <input type="hidden" name="action" value="updateByChef">
							 <input type="hidden" name="chef_ord_no" value="<%=chef_order_listVO.getChef_ord_no()%>">
							 <input type="hidden" name="mem_no" value="<%=chef_order_listVO.getMem_no()%>">
						 	 <input type="hidden" name="chef_no" value="<%=chef_order_listVO.getChef_no()%>">
					   		 <input type="hidden" name="chef_act_date" value="<%=chef_order_listVO.getChef_act_date()%>">
							 <input type="hidden" name="chef_ord_place" value="<%=chef_order_listVO.getChef_ord_place()%>">
							 <input type="hidden" name="chef_ord_cnt" value="<%=chef_order_listVO.getChef_ord_cnt()%>">
							 <input type="hidden" name="chef_ord_con" value="1">
							 <input type="hidden" name="chef_ord_date" value="<%=chef_order_listVO.getChef_ord_date()%>">
							 <input type="submit" value="送出修改" class="btn btn-primary">
		                </c:if>
		                
	                </div>
	            </div>
            </FORM>
        </div>
    </div>

<c:import url="/front-end/chat/inviteChat.jsp" ></c:import>
<footer id="theFooter">
		Copyright &copy; 2016 Java Team 3 
</footer>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script>
    	$('.goBack').click(function(){
			window.history.go(-1);
		})
    </script>
</body>
</html>
