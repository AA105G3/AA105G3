<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chef_order_list.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%
	Chef_order_listVO chef_order_listVO = (Chef_order_listVO) request.getAttribute("chef_order_listVO"); //Chef_order_listServlet.java (Concroller), 存入req的chef_order_listVO物件 (包括幫忙取出的chef_order_listVO, 也包括輸入資料錯誤時的chef_order_listVO物件)
%>

<%
    Chef_order_listService chef_order_listSvc = new Chef_order_listService();
    List<Chef_order_listVO> list = chef_order_listSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<%
    MemberService memberSvc = new MemberService();
    List<MemberVO> mem_list = memberSvc.getAll();
    pageContext.setAttribute("mem_list",mem_list);
%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>私廚訂單資料修改 - update_chef_order_list_input.jsp</title>

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
    .first-col {
        padding-top: 25px;
        padding-bottom: 50px;
    }
    
    .first-col img {
        width: 350px;
        height: 250px;
    }
    
    .next-col {
        padding-bottom: 50px;
    }
    
    .next-col img {
        width: 350px;
        height: 250px;
    }
    
    .front-style {
        background: #f5deb3;
        width: 175px;
    }
    
    .btn-style {
        background: #f5deb3;
        width: 175px;
        text-align: center;
        padding-top: 18px;
        padding-bottom: 18px;
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
		padding-top : 115px;
		padding-bottom : 50px;
	}
	.button-style{
		padding-top : 50px;
	}
	body{
		background: #efede8;
		padding-top: 50px;
		position : relative;
		height : 100%;
	}
	#bookedmsg{
		
	}
</style>

</head>
<link rel="stylesheet" type="text/css" href="/AA105G3/css/calendar.css">
<script language="JavaScript" src="/AA105G3/js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

	<header>
		<c:import url="/front-end/frontNavbar.jsp"></c:import>
	</header>



	<div class="col-xs-12 col-sm-10 col-sm-offset-1 text-center" id="bookedmsg">
		<b>
		對不起，
		request.getChef_order_listVo.
		時段已被預訂，請先聯繫私廚議定時間再下訂
		</b>
	</div>


<c:import url="/front-end/chat/inviteChat.jsp" ></c:import>
<footer id="theFooter">
	Copyright &copy; 2016 Java Team 3
</footer>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>
