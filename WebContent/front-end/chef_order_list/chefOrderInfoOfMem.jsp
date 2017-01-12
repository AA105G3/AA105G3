<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef_order_list.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.chef.model.*"%>
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
<%
    ChefService chefSvc = new ChefService();
    List<ChefVO> chef_list = chefSvc.getAll();
    pageContext.setAttribute("chef_list",chef_list);
%>
<!DOCTYPE html>
<html lang="">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>frontpage-chef</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/AA105G3/css/frontpageCSS.css">
    <!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
    <style type="text/css" media="screen">
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
    
    #top-img {
        background-image: url(/AA105G3/images/chef/chef_title.jpg);
        height: 500px;
        /* background-size: cover; */
        background-position: center;
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
	body{
		background: #efede8;
		padding-top: 50px;
		position : relative;
		height : 100%;
	}
	html{
		height : 100%;
	}
    </style>
</head>

<body>
    <header>
    	<c:import url="/front-end/frontNavbar.jsp"></c:import>
    </header>
<!--     <section id="top-img"> -->
<!--         <div class="col-xs-12 col-sm-12"> -->
<!--             <div class="top-img"></div> -->
<!--             <img src="/AA105G3/images/title.jpg" height="500px" width="100%"> -->
<!--         </div> -->
<!--     </section> -->
<!--     <ol class="breadcrumb"> -->
<!--         <li> -->
<!--             <a href="/AA105G3/front-end/chef_order_list/becomeChef.jsp">成為私廚</a> -->
<!--         </li> -->
<!--     </ol> -->
    <div class="container">
        <div class="row">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 text-center">
                        <h2>下訂的私廚訂單明細</h2>
                    </div>
                </div>
            </div>
            <br>
            <br>
            <div class="col-xs-12 col-sm-10 col-sm-offset-1">
            								
				<div class="row" style="border-bottom: 2px solid #d3d4d5">
                    <div class="col-xs-12 col-sm-4">
                        <h3 style="margin-top:0px"><b>服務私廚</b></h3>
                    </div>
                    <c:forEach var="chefVO" items="${chef_list}">
						<c:if test="${chef_order_listVO.chef_no==chefVO.chef_no}">
                    		<div class="col-xs-12 col-sm-6">
                        		<b style="font-size:12pt">${chefVO.chef_name}</b>                    
                    		</div>                		
                		</c:if>
					</c:forEach>
				</div>
				
                <br>
                <div class="row" style="border-bottom: 2px solid #d3d4d5">
                    <div class="col-xs-12 col-sm-4">
                        <h3 style="margin-top:0px"><b>執行時間</b></h3>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <b style="font-size:12pt">${chef_order_listVO.chef_act_date}</b>                    
                    </div>
                </div>
                <br>
                <div class="row" style="border-bottom: 2px solid #d3d4d5">
                    <div class="col-xs-12 col-sm-4">
                        <h3 style="margin-top:0px"><b>執行地點</b></h3>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <b style="font-size:12pt">${chef_order_listVO.chef_ord_place}</b>                    
                    </div>
                </div>
                <br>
                <div class="row" style="border-bottom: 2px solid #d3d4d5">
                    <div class="col-xs-12 col-sm-4">
                        <h3 style="margin-top:0px"><b>金額</b></h3>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <b style="font-size:12pt">${chef_order_listVO.chef_ord_cost}</b>
                    </div>
                </div>
                <br>
                <div class="row" style="border-bottom: 2px solid #d3d4d5">
                    <div class="col-xs-12 col-sm-4">
                        <h3 style="margin-top:0px"><b>訂單內容說明</b></h3>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <b style="font-size:12pt">${chef_order_listVO.chef_ord_cnt}</b>
                    </div>
                </div>
                <br>
<!--                 <div class="row" style="border-bottom: 2px solid #d3d4d5"> -->
<!--                     <div class="col-xs-12 col-sm-4"> -->
<!--                         <h3 style="margin-top:0px"><b>評價</b></h3> -->
<!--                     </div> -->
<!--                     <div class="col-xs-12 col-sm-6"> -->
<%--                         <b style="font-size:12pt">${chef_order_listVO.chef_appr}</b> --%>
<%--                         <b style="font-size:12pt">${chef_order_listVO.chef_appr_cnt}</b> --%>
<!--                     </div> -->
<!--                 </div> -->
                <div class="row" style="border-bottom: 2px solid #d3d4d5">
                    <div class="col-xs-12 col-sm-4">
                        <h3 style="margin-top:0px"><b>下訂時間</b></h3>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <b style="font-size:12pt">${chef_order_listVO.chef_ord_date}</b>
                    </div>
                </div>
                <br>
                
                <c:if test="${chef_order_listVO.chef_ord_con == '1'}">

				
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef_order_list/chef_order_list.do" name="form1" class="text-center">
					<input type="hidden" name="action" value="getOne_For_Update">
					<input type="hidden" name="chef_ord_no" value="<%=chef_order_listVO.getChef_ord_no()%>">
					<input type="submit" class="btn btn-success" value="同意訂單">
				</FORM>
				<br>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef_order_list/chef_order_list.do" name="form1" class="text-center">
                	<input type="hidden" name="action" value="reject_ord">
					<input type="hidden" name="chef_ord_no" value="<%=chef_order_listVO.getChef_ord_no()%>">
					<input type="hidden" name="mem_no" value="<%=chef_order_listVO.getMem_no()%>">
					<input type="hidden" name="chef_no" value="<%=chef_order_listVO.getChef_no()%>">
					<input type="hidden" name="chef_ord_cost" value="<%=chef_order_listVO.getChef_ord_cost()%>">
					<input type="hidden" name="chef_act_date" value="<%=chef_order_listVO.getChef_act_date()%>">
					<input type="hidden" name="chef_ord_place" value="<%=chef_order_listVO.getChef_ord_place()%>">
					<input type="hidden" name="chef_ord_cnt" value="<%=chef_order_listVO.getChef_ord_cnt()%>">
					<input type="hidden" name="chef_ord_con" value="0">
					<input type="hidden" name="chef_ord_date" value="<%=chef_order_listVO.getChef_ord_date()%>">					
					<input type="submit" class="btn btn-warning" value="不同意訂單">
				</FORM>	
				</c:if>
				<br>
					
<%-- 			  	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef_order_list/chef_order_list.do"> --%>
<!-- 			    	<input type="submit" value="取消訂單"> -->
<%-- 			    	<input type="hidden" name="chef_ord_no" value="${chef_order_listVO.chef_ord_no}"> --%>
<!-- 			    	<input type="hidden" name="action"value="delete"> -->
<!-- 			    </FORM> -->
            </div>
        </div>
    </div>
    <c:import url="/front-end/chat/inviteChat.jsp" ></c:import>
    <footer id="theFooter">
		Copyright &copy; 2016 Java Team 3 
	</footer>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>

</html>
