<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.frd_list.model.*"%>

<% 		
	String mem_no =(String) session.getAttribute("mem_no");
	Frd_listService frd_listSvc = new Frd_listService();
	List list = frd_listSvc.getMyFriends(mem_no);
	pageContext.setAttribute("list",list);
	
%>

<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />

<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<style type="text/css">
	#mwt_mwt_slider_scroll
	{
		top: 60px;
		left:-230px;
		width:230px;	
		position:fixed;
		z-index:9999;
	}
	
	#mwt_slider_content{
		background:#e0e1e2;
		text-align:left;
		
		overflow-y: scroll;
	
	}
	
	#mwt_slider_content button{
		
		font-size: 22px; 
		display: block;
		text-align: left;
		color:#fff;
		background: #3c5a98;
		
	
	}
	
	#mwt_slider_content  i{
		
		padding: 3px;
		border-radius: 5px;
		color: #fff;
	}
	
	#frd_tab {
		position:absolute;
		top:20px;
		right:-24px;
		width:24px;
		background:#3c5a98;
		color:#ffffff;
		font-family:Arial, Helvetica, sans-serif;	
		text-align:center;
		padding:9px 0;
	
	
		-moz-border-radius-topright:10px;
		-moz-border-radius-bottomright:10px;
		-webkit-border-top-right-radius:10px;
		-webkit-border-bottom-right-radius:10px;	
	}
	#frd_tab span {
		display:block;
		height:12px;
		padding:1px 0;
		line-height:12px;
		text-transform:uppercase;
		font-size:12px;
	}
	
	.scroll-frd-name{
		float: left;
		width:165px;
	}
	
	.scroll-frd-invite{
		float: left;
	}
</style>

	</head>
	<body>
		<div id="mwt_mwt_slider_scroll">
		 <div id="frd_tab">
		    <span>上</span>
		    <span>線</span>
		    <span>的</span>
		    <span>好</span>
		    <span>友</span>
		</div>
		<div id="mwt_slider_content">
		${frd_listSvc.getMyFriends(sessionScope.mem_no)}
		<c:forEach var="aFriend" items="${list}">
		<c:if test="${memberSvc.getOneMember(aFriend.friend_no).mem_online ==1}">
			<div>
				<button class="list-frd-chat" value="${aFriend.friend_no}">
					<div class="scroll-frd-name">${memberSvc.getOneMember(aFriend.friend_no).mem_name}</div>
					<div class="scroll-frd-invite"><i class="glyphicon glyphicon-comment"></i></div>
				</button>
			</div>
		</c:if>
		</c:forEach>
		</div>
		</div>
		
		
		<script type='text/javascript'>
		$(function(){
			var w = $("#mwt_slider_content").width();
			$('#mwt_slider_content').css('height', ($(window).height() - 300) + 'px' ); //將區塊自動撐滿畫面高度
			
			$("#frd_tab").mouseover(function(){ //滑鼠滑入時
				if ($("#mwt_mwt_slider_scroll").css('left') == '-'+w+'px')
				{
					$("#mwt_mwt_slider_scroll").animate({ left:'0px' }, 600 ,'swing');
				}
			});
			
			
			$("#mwt_slider_content").mouseleave(function(){　//滑鼠離開後
				$("#mwt_mwt_slider_scroll").animate( { left:'-'+w+'px' }, 600 ,'swing');	
			});	
		});

		</script>
	</body>
</html>