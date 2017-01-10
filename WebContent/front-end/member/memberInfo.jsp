
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.frd_list.model.*"%>

<% 		

	
%>

<jsp:useBean id="frd_listSvc" scope="page" class="com.frd_list.model.Frd_listService" />
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="recipeSvc" scope="page" class="com.recipe.model.RecipeService" />
<jsp:useBean id="collectionSvc" scope="page" class="com.collection.model.CollectionService" />
<jsp:useBean id="memberVO" scope="request" class="com.member.model.MemberVO" />


<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>個人首頁</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel ="stylesheet" href="css/frontpageCSS.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/sweetalert2/6.2.9/sweetalert2.min.css">
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

		<style type="text/css" media="screen">
			.header-style{
				padding-top: 80px;
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
			
			body{
				background: #efede8;
				padding-top: 50px;
				height : 100%;
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
			/*.recipe-item-wrapper h3{
				margin:0px 0px 5px 0px;	
			}
			.recipe-item-wrapper-right h3{
				margin:0px 0px 5px 0px;
			}*/
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
			.recipe-wrapper{
				padding:15px 0px 5px 0px;
				margin-bottom: 10px;
				border-bottom: 1px solid #d3d0c9;

			}
			.recipe-wrapper h3{
				margin:5px 0px;
			}
			.recipe-content p a{
				/*font-size: 16px;*/
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
		</style>

	</head>
	
	
	<body>

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
								            	<c:if test="${memberVO.mem_no == sessionScope.mem_no}">
								                	<li><a  href="<%=request.getContextPath()%>/member/member.do?action=getMemberInfo&mem_no=${sessionScope.mem_no}" id="href-style" >食譜</a></li>
								                </c:if>
								                <c:if test="${memberVO.mem_no != sessionScope.mem_no}">
								                	<li><a href="<%=request.getContextPath()%>/member/member.do?action=getMemberInfo&mem_no=${memberVO.mem_no}" id="href-style" >食譜</a></li>
								                </c:if>
								                <c:if test="${memberVO.mem_no == sessionScope.mem_no}">
								                <li><a href="<%=request.getContextPath()%>/front-end/collection/myCollection.jsp">收藏</a></li>
								                <li><a href="<%=request.getContextPath()%>/front-end/frd_list/memberFriend.jsp">好友</a></li>
								                <li><a data-toggle="tab" href="#menu3">商品訂單
								                	<i class="glyphicon glyphicon-new-window"></i></a></li>
								                <li><a href="#menu3">私廚訂單
								                	<i class="glyphicon glyphicon-new-window"></i></a></li>
								                </c:if>
								            </ul>
								        </div>
								    </nav>
								    
								    <div class="col-xs-12 col-sm-12 content-style">
										<div role="tabpanel">
									    <!-- 標籤面板：標籤區 -->
									    <c:forEach var="recipeVO" items="${recipeSvc.findByMem_no(memberVO.mem_no)}" >
									    	<div class="row recipe-wrapper">
												<div class="col-xs-12 col-sm-4 recipe-img-wrapper">
												<a href="<%=request.getContextPath()%>/recipe/recipe.do?action=getOne_For_Display&recipe_no=${recipeVO.recipe_no}">
													<img src="<%=request.getContextPath()%>/recipe/showRecipe_pic.do?recipe_no=${recipeVO.recipe_no}">
												</a>
												</div>
												<div class="col-xs-12 col-sm-8 recipe-content">
														<a href="<%=request.getContextPath()%>/recipe/recipe.do?action=getOne_For_Display&recipe_no=${recipeVO.recipe_no}">
														<h3>${recipeVO.recipe_name }</h3>
														</a>
														<p>by <a href="<%=request.getContextPath()%>/member/member.do?action=getMemberInfo&mem_no=${recipeVO.mem_no}">${memberVO.mem_name}</a></p>
														<p class="recipe-intro">${recipeVO.recipe_intro}</p>
														<p class="recipe-food-mater">食材：${recipeVO.food_mater}</p>
														<p>
															<i class="glyphicon glyphicon-eye-open">${recipeVO.recipe_total_views}</i>
															<i class="glyphicon glyphicon-heart">${recipeVO.recipe_like}</i>
														</p>												
												</div>
											</div>
										</c:forEach>
											
										</div>
									</div>		    
								</div>	

			    		</div>
			    		<div class="col-xs-12 col-sm-3 col-sm-push-1 text-center member-style">
					    	<img id="memImg" src="<%=request.getContextPath()%>/MemberDBGifReader.do?name=${memberVO.mem_no}">
	    					<h3>${memberSvc.getOneMember(memberVO.mem_no).mem_name}</h3>
					
							<div class="col-xs-12 col-sm-6 count-style">
						    	<div>食譜數：${recipeSvc.findByMem_no(memberVO.mem_no).size()}</div>
						    </div>
						    <div class="col-xs-12 col-sm-6 count-style">
						    	<div>追隨數：${collectionSvc.getCollectionSize(sessionScope.mem_no)>0?collectionSvc.getCollectionSize(sessionScope.mem_no):0}</div>
						    </div>
						    <div class="col-xs-12 col-sm-12 text-left">
						    	<div class="mem-email">${memberVO.mem_email}</div>
						    </div>
						     <c:if test="${memberVO.mem_no != sessionScope.mem_no}">
						     
						     <c:set var="authorFlag" value="false" />
						     <c:set var="coll_no" value="0" />
						     <c:forEach var="aCollection" items="${collectionSvc.getMyAuthorCollection(sessionScope.mem_no)}" >
						     	<c:if test="${aCollection.all_no == memberVO.mem_no}">
						    		<c:set var="authorFlag" value="true" />
						    		<c:set var="coll_no" value="${aCollection.coll_no}" />
						    	</c:if>
						     </c:forEach>
							    <c:if test="${!authorFlag}">
								    <div class="col-xs-12 col-sm-6">
								    	<button id ="addCollection" class="btn btn-primary" value="${memberVO.mem_no}">加入追隨</button>
								    </div>
							    </c:if>
							    <c:if test="${authorFlag}">
								    <div class="col-xs-12 col-sm-6">
								    	<button id="cancelCollection" class="btn btn-default" value="${memberVO.mem_no}">取消追隨</button>
								    </div>
							    </c:if>
						    
						    <c:set var="flag" value="false" />
						    <c:forEach var="aFriend" items="${frd_listSvc.getFriendsByMem_no(sessionScope.mem_no)}" >
						    	<c:if test="${aFriend.friend_no == memberVO.mem_no}">
						    		<c:set var="flag" value="true" />
						    	</c:if>
						    </c:forEach>
							      <c:if test="${!flag}">
							    	<div class="col-xs-12 col-sm-6">
							    		<button id="addFriend" class="btn btn-primary" value="${memberVO.mem_no}">加入好友</button>
							   		 </div>
							     </c:if>
							     <c:if test="${flag}">
							    	<div class="col-xs-12 col-sm-6">
							    		<button class="btn btn-default" disabled="disabled" value="${memberVO.mem_no}">已加入好友</button>
							   		 </div>
							     </c:if>
							     <input type="hidden" id="addNo" value="${memberVO.mem_no}">
							     <input type="hidden" id="cancelNo" value="${coll_no}">
							</c:if>
					    	
					    </div>
					</div>
				
	    	</div>
	  	</div>

	<c:import url="/front-end/frontFooter.jsp" ></c:import>


		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.2.9/sweetalert2.min.js"></script>
		<script type="text/javascript">

			//init
			$().ready(function(){

				//判斷是哪個tab
				var tabId ="" ; 
				if(tabId!=""){
					$("#tab1").removeClass('active');
					$("#"+tabId).addClass('active');
					$(".tab1").removeClass('active');
					$("."+tabId).addClass('active');
				}


				//好友區塊
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
				
				//收藏區塊
				//得到收藏的兩個號碼 一個刪除用 一個加入用
				
				var addCollection = function(){
					var all_no = $(this).val();
					 $.ajax({
						 type:"POST",
						 url:"/AA105G3/collection/collectionJsonRes.do",
						 data:{"action":"addCollection","all_no":all_no},
						 dataType:"json",
						 success:function (data){

							 swal({
							   title: data.msg,
							    type:'success'
							  })
							 $('#addCollection').text('取消追隨')
							 $('#addCollection').attr("id","cancelCollection")
							 $('#cancelCollection').removeClass('btn-primary');
							 $('#cancelCollection').addClass('btn-default');
							 $('#cancelCollection').unbind( "click",addCollection);
							 $('#cancelCollection').click(cancelCollection);
					     },
			             error:function(){alert('not found')}
			         }) 
				}
				//註冊方法
				$("#addCollection").on("click",addCollection);
				
				var cancelCollection = function(){
					var all_no = $(this).val();
					 $.ajax({
						 type:"POST",
						 url:"/AA105G3/collection/collectionJsonRes.do",
						 data:{"action":"delete","all_no":all_no},
						 dataType:"json",
						 success:function (data){

							 swal({
							   title:'已取消追隨',
							    type:'success'
							  })
							 $('#cancelCollection').text('加入追隨')
							 $('#cancelCollection').attr("id","addCollection")
							 $('#addCollection').removeClass('btn-default');
							 $('#addCollection').addClass('btn-primary');
							 $('#addCollection').unbind( "click",cancelCollection);
							 $('#addCollection').click(addCollection);
					     },
			             error:function(){alert('not found')}
			         }) 
				}
				//註冊方法
				$("#cancelCollection").on("click",cancelCollection);
				
				/* $('body').on('click',"#addCollection",addCollection);
				$('body').on('click',"#cancelCollection",cancelCollection); */
			})
			
			
			
		</script>
	</body>

</html>
