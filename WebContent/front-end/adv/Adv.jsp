<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adv.model.*"%>

<%
	AdvService advSvc = new AdvService();
	List<AdvVO> list = advSvc.getAll();
	pageContext.setAttribute("list",list);
	AdvVO advVO1 = null;
	AdvVO advVO2 = null;
	
	
	for(int i = 0; i < 2; i++){
		int count = (int)(Math.random()*list.size());
		if(i == 0){
			advVO1 = list.get(count);	
			list.remove(count);
		}
		if(i == 1){
		advVO2 = list.get(count);			
		}
	}
	
	pageContext.setAttribute("advVO1", advVO1);
	pageContext.setAttribute("advVO2", advVO2);
%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>ADV</title>

<style type="text/css" media="screen">
	.adv{
		position: absolute;
		position: fixed;
		top: 57%;
		left: 90%;
		width: 250px;
		list-style: none;
		margin: 0;
		padding: 0;
		font-size: 16px;
	}
	.adv li{
		padding-bottom: 15px;
	}
	
	/* div.bigDiv {
		height: 5000px;
	} */
	
	.adv img{
		width : 175px;
		height : 175px; 
	}
	.adv p{
		top:50px;
		position: relative;
		opacity: 1;
		color: #fff;
		z-index: 1;
	}
	.adv p::before{
		content: ' ';
		display: block;
	    position: absolute;
		z-index: -1;
		background: #000;
		width: 100%;
	    height: 100%;
		opacity: 0.6;
	}
</style>

</head>
<body>

<!-- <div class="bigDiv">我是一個很長的區塊</div> -->

<div id="sidebar-wrapper">
	<ul class="adv">
		<li>
			<a href="http://${advVO1.adv_url}" style="text-decoration:none;">
				<div style="background-image:url('/AA105G3/AdvDBGifReader.do?name=${advVO1.adv_no}'); height:175px; width:175px; background-size: 175px 175px; text-align:center;">
					<font size="5"><p>${advVO1.adv_name}</p></font>
				</div>
			</a>
		</li>
		<li>
			<a href="http://${advVO2.adv_url}" style="text-decoration:none;">
				<div style="background-image:url('/AA105G3/AdvDBGifReader.do?name=${advVO2.adv_no}'); height:175px; width:175px; background-size: 175px 175px; text-align:center;">
					<font size="5"><p>${advVO2.adv_name}</p></font>
				</div>
			</a>
		</li>
	</ul>
</div>

</body>
</html>