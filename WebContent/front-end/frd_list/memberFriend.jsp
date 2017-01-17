
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.frd_list.model.*"%>

<% 		
	String mem_no =(String) session.getAttribute("mem_no");
	Frd_listService frd_listSvc = new Frd_listService();
	List list = frd_listSvc.getMyFriends(mem_no);
	List list2 = frd_listSvc.getInviteFriend(mem_no);
	List list3 = frd_listSvc.getInvited(mem_no);
	pageContext.setAttribute("list",list);
	pageContext.setAttribute("list2",list2);
	pageContext.setAttribute("list3",list3);
	
%>

<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="recipeSvc" scope="page" class="com.recipe.model.RecipeService" />
<jsp:useBean id="ChefSvc" scope="page" class="com.chef.model.ChefService" />


<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>我的好友</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel ="stylesheet" href="css/frontpageCSS.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/sweetalert2/6.2.9/sweetalert2.min.css">
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

		<style type="text/css">
			.header-style{
				padding-top: 40px;
				margin-bottom:50px;
			}
			.search-style{
				padding-bottom: 50px;
			}
			.member-style{
				padding-top: 20px;
				padding-bottom: 20px;
				background: white;
			}
			.count-style{
				padding-top: 10px;
				padding-bottom: 15px;
			}
			.content-style{
				background: white;
				margin-top: 25px;
			}
			#href-style{
				color: red;
				border-bottom: 3px red solid;
			}
			
			html,body{
				background: #efede8;
				
				height : 100%;
				
			}
			.page{
				min-height:100%;
				   position: relative;
				   padding-top:50px;
				   padding-bottom:50px;
			}
			.recipe-search-wrapper{
				margin-bottom:20px;
			}
			.recipe-search{

				text-align: right;
			}

			.recipe-select{
				height: 50px;
			}
			.recipe-text{
				height: 50px;
				width:375px !important;
			}
			
			.recipe-btn{
				height: 50px;
				color:#D9230F;
				width: 50px;
			}
			.recipe-header-right{
				text-align: right;
				padding-right: 0px;
			}
			.write-recipe{
				height: 50px;
				font-weight: bold;
			}
					
			
			.page-top-title-wrapper{
				background: #fff;
				margin-bottom: 5px;
				border:1px solid #d3d0c9;
			}
			.recipe-row-wrapper{
				margin-bottom: 10px;
			}
			.recipe-row-wrapper img{
				width:230px;
				height:200px;
			}
			.recipe-item-wrapper{
				background: #fff;
				padding: 15px;
				border:1px solid #d3d0c9;
				height:230px;
			}
			.recipe-item-wrapper-right{
				padding: 15px 15px 15px 15px;
				background: #fff;
				border:1px solid #d3d0c9;
				height:230px;
			}
			.recipe-item-wrapper h3{
				margin:0px 0px 5px 0px;	
			}
			.recipe-item-wrapper-right h3{
				margin:0px 0px 5px 0px;
			}
			.recipe-item-left{
				padding:0px;
			}
			.recipe-item-right{
				padding:0px 0px 0px 10px;
			}
			.recipe-intro{
				color:#706864;
				height:60px;
				word-break:break-all;
			}
			.recipe-food-mater{
				color:#b6b0a5;
				word-break:break-all;
				height:40px;
			}
			#page-content{
				min-height:700px;
			}
			footer{
				position : absolute;
			    bottom : 0px;
				width : 100%;
			}
			.friend-img{
				width:121px !important;
			}
			.friend-img2{
				
			}
			.friend-img img,.friend-img2 img{
				height: 120px;
				width: 120px;
			}
			.friend-img2{
				padding-left: 10px;
			}
			.friend-name{
				width:104px;
				padding:5px;
				vertical-align: middle;
				word-break:break-all;
				font-size: 20px;
			}
			.fri-status{
				text-align: right;
				width:120px;
				font-size: 22px;
			}
			.friend{
				margin-top: 10px;
			}
			.friend td{
				padding-top: 10px;
				padding-bottom: 10px;
			}
			.frd-online{
				width:12px;
				height:12px;
				background:#41ec00;
				float:right ;
				margin-top:10px;
			}
			.frd-offline{
				width:12px;
				height:12px;
				background:gray;
				float:right ;
				margin-top:10px;
			}
			.list-frd-delete{
				/*margin-right: 12px;*/
			}
			.list-frd-cancel{
				margin-right: 5px;
			}
			.frd-form{
				padding:0px !important;
				margin:0px !important;
				display:inline !important;
			}
			.mem-email{
				padding-left: 17px;
				margin-bottom: 10px;
				
			}
			.recipe-img-wrapper img{
				width: 230px;
				height: 200px;
			}
			#memImg{
				width:150px;
				height:150px;
			}
			.tab1 a,.tab2 a,.tab3 a{
				color:#000;
			}
			.tab1.active a,.tab2.active a,.tab3.active a{
				color:steelblue !important;
				font-weight:bold ;
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
			.mem_chef_name{
				font-size:16px;
			}
			.goChef{
				padding:5px 0px;
			}
		</style>

	</head>
	
	
	<body>
<div class="page">
	      	<c:import url="/front-end/frontNavbar.jsp" ></c:import>	

					<header class="header-style">
						<c:import url="/front-end/recipe/RecipeSearchBar.jsp" ></c:import>
					</header>
					<div class="container">

	    	<div class="row">
						<div class="col-xs-12 col-sm-8">
			   				
			   				
				   				<div role="tabpanel">
									<nav class="nav navbar-default">
								        <div class="container-fluid">
								            <ul class="nav navbar-nav">
								                <li><a  href="<%=request.getContextPath()%>/member/member.do?action=getMemberInfo&mem_no=${sessionScope.mem_no}" >食譜</a></li>
								                <li><a  href="<%=request.getContextPath()%>/front-end/collection/myCollection.jsp">收藏</a></li>
								                <li><a  href="<%=request.getContextPath()%>/front-end/frd_list/memberFriend.jsp" id="href-style">好友</a></li>
								                <li><a href="<%=request.getContextPath()%>/front-end/product_order/ListProductOrder.jsp">商品訂單
								                	<i class="glyphicon glyphicon-new-window"></i></a></li>
								                <li><a href="/AA105G3/front-end/chef_order_list/chefOrderListOfMem.jsp">我的私廚訂單
								                	<i class="glyphicon glyphicon-new-window"></i></a></li>
								                <c:if test="${ChefSvc.getOneChefByMem_no(sessionScope.mem_no)!=null}">
								                <li><a href="/AA105G3/front-end/chef_order_list/ChefOrderListOfChef2.jsp">他人預定訂單
								                	<i class="glyphicon glyphicon-new-window"></i></a></li>
								                </c:if>
								            </ul>
								        </div>
								    </nav>
								    
								    <div class="col-xs-12 col-sm-12 content-style">
										<div role="tabpanel">
									    <!-- 標籤面板：標籤區 -->
									    <ul class="nav nav-tabs" role="tablist">
									        <li role="presentation" class="active tab1" >
									            <a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">全部好友</a>
									        </li>
									        <li role="presentation" class="tab2">
									            <a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">邀請中
									            <c:if test="${list2.size()>0}">
									            (${list2.size()})
									            </c:if>
									            </a>
									        </li>
									        <li role="presentation" class="tab3">
									            <a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">被邀請
									            <c:if test="${list3.size()>0}">
									            (${list3.size()})
									            </c:if>
									            </a>
									        </li>
									    </ul>
									
									    <!-- 標籤面板：內容區 -->
									    <div class="tab-content">
									        <div role="tabpanel" class="tab-pane active" id="tab1">
									        			<table class="friend">
									        			<c:forEach var="aFriend" items="${list}" varStatus="s" step="2">
									        				<tr>
									        					<td class="friend-img">
									        					<a href="<%=request.getContextPath()%>/member/member.do?action=getMemberInfo&mem_no=${aFriend.friend_no}">
									        						<img src="<%=request.getContextPath()%>/MemberDBGifReader.do?name=${aFriend.friend_no}">
									        					</a>
									        					</td>
									        					<td class="friend-name">
									        					<a href="<%=request.getContextPath()%>/member/member.do?action=getMemberInfo&mem_no=${aFriend.friend_no}">
									        						${memberSvc.getOneMember(aFriend.friend_no).mem_name}
									        					</a>
									        					</td>
									        					<td class="fri-status">
									        					<c:if test="${memberSvc.getOneMember(aFriend.friend_no).mem_online ==1}">
									        						<div class="frd-online"></div>上線中
									        						<div style="clear:both;"></div>
								        							<button class="btn btn-info list-frd-chat" value="${aFriend.friend_no}">聊天</button>
								        							<form action="<%=request.getContextPath()%>/frd_list/frd_list.do" method="post" class="frd-form">
								        								<input type="submit" class="btn btn-danger list-frd-delete" value="刪除">
								        								<input type="hidden" name="friend_no" value="${aFriend.friend_no}">
								        								<input type="hidden" name="action" value="delete">
								        							 </form>
								        						</c:if>
								        						<c:if test="${memberSvc.getOneMember(aFriend.friend_no).mem_online ==0}">
								        							<div class="frd-offline"></div> 離線
									        						<div style="clear:both;"></div>
									        						<form action="<%=request.getContextPath()%>/frd_list/frd_list.do" method="post" class="frd-form">
								        								<input type="submit" class="btn btn-danger list-frd-delete" value="刪除">
								        								<input type="hidden" name="friend_no" value="${aFriend.friend_no}">
								        								<input type="hidden" name="action" value="delete">
								        							 </form>
								        						</c:if>
									        					</td>
									        					<c:if test="${s.index+1 < list.size()}">
									        					<td class="friend-img2">
									        					<a href="<%=request.getContextPath()%>/member/member.do?action=getMemberInfo&mem_no=${list.get(s.index+1).friend_no}">
									        						<img src="<%=request.getContextPath()%>/MemberDBGifReader.do?name=${list.get(s.index+1).friend_no}">
									        					</a>
									        					</td>
									        					<td class="friend-name">
									        					<a href="<%=request.getContextPath()%>/member/member.do?action=getMemberInfo&mem_no=${list.get(s.index+1).friend_no}">
									        						${memberSvc.getOneMember(list.get(s.index+1).friend_no).mem_name}
									        					</a>
									        					</td>
									        					<td class="fri-status">
									        						<c:if test="${memberSvc.getOneMember(list.get(s.index+1).friend_no).mem_online ==1}">
									        						<div class="frd-online"></div>上線中
									        						<div style="clear:both;"></div>
								        							<button class="btn btn-info list-frd-chat" value="${list.get(s.index+1).friend_no}">聊天</button>
								        							<form action="<%=request.getContextPath()%>/frd_list/frd_list.do" method="post" class="frd-form">
								        								<input type="submit" class="btn btn-danger list-frd-delete" value="刪除">
								        								<input type="hidden" name="friend_no" value="${list.get(s.index+1).friend_no}">
								        								<input type="hidden" name="action" value="delete">
								        							 </form>
								        						</c:if>
								        						<c:if test="${memberSvc.getOneMember(list.get(s.index+1).friend_no).mem_online ==0}">
								        							<div class="frd-offline"></div> 離線
									        						<div style="clear:both;"></div>
									        						<form action="<%=request.getContextPath()%>/frd_list/frd_list.do" method="post" class="frd-form">
								        								<input type="submit" class="btn btn-danger list-frd-delete" value="刪除">
								        								<input type="hidden" name="friend_no" value="${list.get(s.index+1).friend_no}">
								        								<input type="hidden" name="action" value="delete">
								        							 </form>
								        						</c:if>
									        					</td>
									        					</c:if>
									        				</tr>
														</c:forEach>
									        			</table>
									        </div>
									        <div role="tabpanel" class="tab-pane" id="tab2">
									        	<table class="friend">
									        	<c:forEach var="aFriend" items="${list2}" varStatus="s" step="2">
								        				<tr>
								        					<td class="friend-img">
								        					<a href="<%=request.getContextPath()%>/member/member.do?action=getMemberInfo&mem_no=${aFriend.friend_no}">
								        						<img src="<%=request.getContextPath()%>/MemberDBGifReader.do?name=${aFriend.friend_no}">
								        					</a>
								        					</td>
								        					<td class="friend-name">
								        					<a href="<%=request.getContextPath()%>/member/member.do?action=getMemberInfo&mem_no=${aFriend.friend_no}">
								        						${memberSvc.getOneMember(aFriend.friend_no).mem_name}
								        					</a>
								        					</td>
								        					<td class="fri-status">
								        						<div>邀請中</div>
								        						<form action="<%=request.getContextPath()%>/frd_list/frd_list.do" method="post" class="frd-form">
								        								<input type="submit" class="btn btn-danger list-frd-cancel" value="取消">
								        								<input type="hidden" name="friend_no" value="${aFriend.friend_no}">
								        								<input type="hidden" name="action" value="delete">
								        								<input type="hidden" name="tabID" value="tab2">
								        							 </form>
								        					</td>
								        					<c:if test="${s.index+1 < list2.size()}">
								        					<td class="friend-img2">
								        					<a href="<%=request.getContextPath()%>/member/member.do?action=getMemberInfo&mem_no=${list2.get(s.index+1).friend_no}">
								        						<img src="<%=request.getContextPath()%>/MemberDBGifReader.do?name=${list2.get(s.index+1).friend_no}">
								        					</a>
								        					</td>
								        					<td class="friend-name">
								        					<a href="<%=request.getContextPath()%>/member/member.do?action=getMemberInfo&mem_no=${list2.get(s.index+1).friend_no}">
								        						${memberSvc.getOneMember(list2.get(s.index+1).friend_no).mem_name}
								        					</a>
								        					</td>
								        					<td class="fri-status">
								        						<div>邀請中</div>
								        						<form action="<%=request.getContextPath()%>/frd_list/frd_list.do" method="post" class="frd-form">
								        								<input type="submit" class="btn btn-danger list-frd-cancel" value="取消">
								        								<input type="hidden" name="friend_no" value="${list2.get(s.index+1).friend_no}">
								        								<input type="hidden" name="action" value="delete">
								        								<input type="hidden" name="tabID" value="tab2">
								        							 </form>
								        					</td>
								        					</c:if>
								        				</tr>
								        			</c:forEach>
								        			</table>
									        </div>
									        <div role="tabpanel" class="tab-pane" id="tab3">
									        	<table class="friend">
									        	<c:forEach var="aFriend" items="${list3}" varStatus="s" step="2">
								        				<tr>
								        					<td class="friend-img">
								        					<a href="<%=request.getContextPath()%>/member/member.do?action=getMemberInfo&mem_no=${aFriend.friend_no}">
								        						<img src="<%=request.getContextPath()%>/MemberDBGifReader.do?name=${aFriend.friend_no}">
								        					</a>
								        					</td>
								        					<td class="friend-name">
								        					<a href="<%=request.getContextPath()%>/member/member.do?action=getMemberInfo&mem_no=${aFriend.friend_no}">
								        						${memberSvc.getOneMember(aFriend.friend_no).mem_name}
								        					</a>
								        					</td>
								        					<td class="fri-status">
									        					<form action="<%=request.getContextPath()%>/frd_list/frd_list.do" method="post" class="frd-form">
								        							<input type="submit" class="btn btn-primary  list-frd-accept" value="接受">
								        							<input type="hidden" name="friend_no" value="${aFriend.friend_no}">
									        						<input type="hidden" name="action" value="insert">
								        						</form>
							        							<form action="<%=request.getContextPath()%>/frd_list/frd_list.do" method="post" class="frd-form">
							        								<input type="submit" class="btn btn-danger list-frd-cancel" value="取消">
							        								<input type="hidden" name="friend_no" value="${aFriend.friend_no}">
							        								<input type="hidden" name="action" value="delete">
							        								<input type="hidden" name="tabID" value="tab3">
							        							 </form>	
								        					</td>
								        					<c:if test="${s.index+1 < list3.size()}">
								        					<td class="friend-img2">
								        					<a href="<%=request.getContextPath()%>/member/member.do?action=getMemberInfo&mem_no=${list3.get(s.index+1).friend_no}">
								        						<img src="<%=request.getContextPath()%>/MemberDBGifReader.do?name=${list3.get(s.index+1).friend_no}">
								        					</a>
								        					</td>
								        					<td class="friend-name">
								        					<a href="<%=request.getContextPath()%>/member/member.do?action=getMemberInfo&mem_no=${list3.get(s.index+1).friend_no}">
								        						${memberSvc.getOneMember(list3.get(s.index+1).friend_no).mem_name}
								        					</a>
								        					</td>
								        					<td class="fri-status">
								        						<form action="<%=request.getContextPath()%>/frd_list/frd_list.do" method="post" class="frd-form">
								        							<input type="submit" class="btn btn-primary  list-frd-accept" value="接受">
								        							<input type="hidden" name="friend_no" value="${list3.get(s.index+1).friend_no}">
									        						<input type="hidden" name="action" value="insert">
							        							</form>
								        						<form action="<%=request.getContextPath()%>/frd_list/frd_list.do" method="post" class="frd-form">
							        								<input type="submit" class="btn btn-danger list-frd-cancel" value="取消">
							        								<input type="hidden" name="friend_no" value="${list3.get(s.index+1).friend_no}">
							        								<input type="hidden" name="action" value="delete">
							        								<input type="hidden" name="tabID" value="tab3">
							        							 </form>	
								        					</td>
								        					</c:if>
								        				</tr>
								        				</c:forEach>
								        			</table>
									        </div>
									    </div>
									</div>
									</div>		    
								</div>	

			    		</div>
			    		<div class="col-xs-12 col-sm-3 col-sm-push-1 text-center member-style">
					    	<img id="memImg" src="<%=request.getContextPath()%>/MemberDBGifReader.do?name=${sessionScope.mem_no}">
	    					<h3>${memberSvc.getOneMember(sessionScope.mem_no).mem_name}</h3>
	    					<c:set var="aChef" value="${ChefSvc.getOneChefByMem_no(sessionScope.mem_no)}" />
							<c:if test="${aChef.chef_name !=null}">
							<div class="mem_chef_name">(${ChefSvc.getOneChefByMem_no(sessionScope.mem_no).chef_name})</div>
							</c:if>
							<div class="col-xs-12 col-sm-6 count-style">
						    	<div>食譜數：${recipeSvc.findByMem_no(sessionScope.mem_no).size()}</div>
						    </div>
						    <div class="col-xs-12 col-sm-6 count-style">
						    	<div>追隨數：${collectionSvc.getCollectionSize(sessionScope.mem_no)>0?collectionSvc.getCollectionSize(sessionScope.mem_no):0}</div>
						    </div>
						   
						    <div class="col-xs-12 col-sm-12 text-left">
						    	<div class="mem-email">${memberSvc.getOneMember(sessionScope.mem_no).mem_email.toLowerCase()}</div>
						    	<div class="text-center goChef">
									<a href="<%=request.getContextPath()%>/chef/chef.do?action=getOne_For_Display&chef_no=${aChef.chef_no}" class="btn btn-info btn-xs">
									前往私廚頁面
									</a>
								</div>
						    </div>
					    	
					    </div>
					</div>
				
	    	</div>


	<footer id="theFooter">
		Copyright &copy; 2017 Java Team 3 
	</footer>
	  	</div>
<c:import url="/front-end/chat/inviteChat.jsp" ></c:import>
	
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		
	<c:if test="${sessionScope.mem_ac !=null}">
		<c:import url="/front-end/frd_list/friendBar.jsp" ></c:import>
	</c:if>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.2.9/sweetalert2.min.js"></script>
		<script type="text/javascript">

			//init
			$().ready(function(){

				//判斷是哪個tab
				var tabId ="${tabID}" ; 
				if(tabId!=""){
					$("#tab1").removeClass('active');
					$("#"+tabId).addClass('active');
					$(".tab1").removeClass('active');
					$("."+tabId).addClass('active');
				}
				


				var addFriend = $("#addFriend").click(function(){
					var friend_no = $(this).val();
					$.ajax({
						 type:"POST",
						 url:"/AA105G3/frd_list/frd_listJsonRes.do",
						 data:{"action":"addFriend","friend_no":friend_no},
						 dataType:"json",
						 success:function (data){

							 swal({
							   title: '已向'+ data.friendName +'送出好友申請!',
							    type:'success'
							  })
							 $('#addFriend').text('已加入好友')
							 $('#addFriend').attr("disabled", true);
							 $('#addFriend').removeClass('btn-primary');
							 $('#addFriend').addClass('btn-default');
					     },
			             error:function(){alert('not found')}
			         })
				})
			})
		</script>
	</body>

</html>
