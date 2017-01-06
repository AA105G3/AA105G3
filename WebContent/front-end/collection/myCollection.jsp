<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.collection.model.*"%>

<% 		
	session.setAttribute("mem_no","M00000001");
	String mem_no =(String) session.getAttribute("mem_no");
	CollectionService collectionSvc = new CollectionService();
	List list = collectionSvc.getMyRecipeCollection(mem_no);
	List list2 = collectionSvc.getMyAuthorCollection(mem_no);
	List list3 = collectionSvc.getMyChefCollection(mem_no);
	pageContext.setAttribute("list",list);
	pageContext.setAttribute("list2",list2);
	pageContext.setAttribute("list3",list3);
	pageContext.setAttribute("collectionSvc",collectionSvc);
	
%>

<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="recipeSvc" scope="page" class="com.recipe.model.RecipeService" />
<jsp:useBean id="chefSvc" scope="page" class="com.chef.model.ChefService" />



<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>我的收藏</title>
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
			.collection-author td{
				padding-top:5px;	
				padding-bottom: 5px;
			}
			.collection-author img{
				width: 80px;
				height: 80px;
			}
			.collection-author tr{
				border-bottom: 1px solid #e2e0db;
				margin-bottom: 10px;


			}
			.author-info{
				padding-left: 10px;
				width: 120px;
			}
			.author-info h3{
				margin: 10px 0px 5px 0px;
			}
			.author-info p{
				margin-bottom: 3px;
			}
			td.collection-newestRecipe{
				vertical-align: middle;
				/*padding-left: 5px;*/
				width: 75px;
				text-align: center;
				text-decoration: underline;
				border-left: 1px solid #e2e0db;
			}
			.collection-recipe-view{
				width: 150px;
				
			}
			.collection-newestRecipe h4{
				margin: 0px;
			}
			td.cancle-form{
				width: 306px;
			}
			.collection-author-recipe-info{
				padding-left: 5px;
			}
			.collection-author-recipe-info h4{
				margin: 0px 0px 3px 0px;
				font-weight: bold;
			}
			.collection-author-recipe-info p{
				margin:0px;
			}
			.collection-chef tr{
				/*margin-top: 5px;*/
				border-bottom: 1px solid #e2e0db;
			}
			.chef-name{
				width: 120px;
				vertical-align: center;
				padding-left: 10px;
			}
			.chef-name h3{
				margin: 0px;
			}
			.collection-chef-info{
				border-left: 1px solid #e2e0db;
				padding:5px 0px 5px 5px;
			}
			.collection-chef-info p{
				word-break:break-all;
				border-bottom: 1px solid #e2e0db;
			}
			.act-area{
				margin:0px;
			}
			.act-area span{
				color:steelblue;
				font-weight: bold;
			}
			.chef-skill span{
				color:steelblue;
				font-weight: bold;
			}
			.chef-skill{
				border-bottom: 1px solid #e2e0db;
				margin-bottom: 3px;
			}
			.tab1 a,.tab2 a,.tab3 a{
				color:#000;
			}
			.tab1.active a,.tab2.active a,.tab3.active a{
				color:steelblue !important;
				font-weight:bold ;
			}
		</style>

	</head>
	
	
	<body>
			
		

		 <div class="navbar navbar-default navbar-fixed-top navbar-inverse mu-main-navbar" >
	      <div class="container">
	        <div class="navbar-header">
	          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>
	          <a href="#home" class="foodtime"><img alt="FoodTime" src="img/Logo.png">分享食光</a>
	        </div>
	        <div class="collapse navbar-collapse" id="navbar-ex-collapse">
	          <ul class="nav navbar-nav navbar-right mu-main-nav">
	            <li >
	              <a href="#home">首頁</a>
	            </li>
	            <li>
	              <a href="#mu-recipe">食譜</a>
	            </li>
	            <li>
	              <a href="#mu-video">影音</a>
	            </li>
	            <li>
	              <a href="#mu-chef">私廚</a>
	            </li>
	            <li>
	              <a href="#mu-stream">實況</a>
	            </li>
	            <li>
	              <a href="#mu-market">市集</a>
	            </li>
	            <li>
	              <a href="#mu-contact">聯絡我們</a>
	            </li>
	            <li>
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">UserID<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#">編輯個人資訊</a></li>
						<li><a href="#">個人頁面</a></li>
						<li><a href="#">我的最愛</a></li>
						<li><a href="#">登出</a></li>
					</ul>
				</li>
				<li>
					<a href="#">註冊</a>
				</li>
	          </ul>
	        </div>
	      </div>
	    </div>


	    <div class="container">
	    	<div class="row">
					<div class="container" id="page-content">
					<header class="header-style">
						<div class="row">
							<div class="col-xs-12 col-sm-6 search-style">
								<div class="recipe-search">
										<form class="form-inline" action="<%=request.getContextPath()%>/recipe/recipe.do" method="POST">
										    <select class="form-control recipe-select" name="searchCondition">
										        <option value="recipe_name">找食譜名</option>
										        <option value="food_mater">找食材</option>
										    </select>
										   <div class="input-group recipe-search-form">
										    <input type="text" class="form-control recipe-text" placeholder="Search Recipe" name="searchInput">
										        <span class="input-group-btn">
										            <button class="btn btn-default recipe-btn" type="submit" name="action" value="search"><i class="glyphicon glyphicon-search"></i></button>
										        </span>
										    </div>
										</form>
									</div>
							</div>
							
							<div class="col-xs-12 col-sm-4 recipe-header-right search-style">
								
									<a href="addRecipe.jsp">
									<button class="btn btn-default write-recipe">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									寫食譜</button></a>
								
							</div>
							
						</div>
					</header>
						<div class="col-xs-12 col-sm-8">
			   				
				   				<div role="tabpanel">
									<nav class="nav navbar-default">
								        <div class="container-fluid">
								            <ul class="nav navbar-nav">
								                <li><a href="#home" >食譜</a></li>
								                <li><a href="#menu1" id="href-style">收藏</a></li>
								                <li><a href="#menu2">好友</a></li>
								                <li><a href="#menu3">商品訂單
								                	<i class="glyphicon glyphicon-new-window"></i></a></li>
								                <li><a href="#menu3">私廚訂單
								                <i class="glyphicon glyphicon-new-window"></i></a></li>
								            </ul>
								        </div>
								    </nav>
								    
								    <div class="col-xs-12 col-sm-12 content-style">
										<div role="tabpanel">
									    <!-- 標籤面板：標籤區 -->
									    	
									    	<div role="tabpanel">
									    	    <!-- 標籤面板：標籤區 -->
									    	    <ul class="nav nav-tabs" role="tablist">
									    	        <li role="presentation" class="active tab1">
									    	            <a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">食譜</a>
									    	        </li>
									    	        <li role="presentation" class="tab2">
									    	            <a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">作者</a>
									    	        </li>
									    	        <li role="presentation" class="tab3">
									    	            <a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">私廚</a>
									    	        </li>
									    	    </ul>
									    	
									    	    <!-- 標籤面板：內容區 -->
									    	    <div class="tab-content">
									    	        <div role="tabpanel" class="tab-pane active" id="tab1">
										    		  <c:forEach var="aCollection" items="${list}" >	
										    		  	
														<div class="recipe-wrapper row">
															<div class="col-xs-12 col-sm-4 recipe-img-wrapper">
																<img src="https://api.fnkr.net/testimg/200x180/00CED1/FFF/?text=img+placeholder">
															</div>
															<div class="col-xs-12 col-sm-8 recipe-content">
															<a>
																<h3>${recipeSvc.getCollectRecipe(aCollection.all_no).recipe_name}</h3>
																</a>
																<c:set var="authorNO" value="${recipeSvc.getCollectRecipe(aCollection.all_no).mem_no}" />
																<p>by <a href="#">${memberSvc.getOneMember(authorNO).mem_name}</a></p>
																<p class="recipe-intro">${recipeSvc.getCollectRecipe(aCollection.all_no).recipe_intro}</p>
																<p class="recipe-food-mater">食材：${recipeSvc.getCollectRecipe(aCollection.all_no).food_mater}</p>
																<table >
																	<tr>
																		<td class="collection-recipe-view">
																			<i class="glyphicon glyphicon-eye-open">${recipeSvc.getCollectRecipe(aCollection.all_no).recipe_total_views}</i>
																			<i class="glyphicon glyphicon-heart">${recipeSvc.getCollectRecipe(aCollection.all_no).recipe_like + collectionSvc.getCollectionSize(aCollection.all_no)}</i>		
																		</td>
																		<td class="cancle-form">
																			<form action="" class="text-right" method="post">
																			<input type="submit" class="btn btn-danger btn-xs" value="取消追隨">
																			<input type="hidden" name="action" value="delete">
																			<input type="hidden" name="coll_no" value="${aCollection.coll_no}">
																			</form>											
																		</td>
																	</tr>
																</table>
															</div>
														</div>
														
													</c:forEach>


									    	        </div>
									    	        <div role="tabpanel" class="tab-pane" id="tab2">
															<table class="collection-author">
															<c:forEach var="aCollection" items="${list2}" >
																<tr>
																	<td class="author-img">
																		<img class="img-circle" src="https://api.fnkr.net/testimg/80x80/00CED1/FFF/?text=img+placeholder">
																	</td>
																	<td class="author-info">
																		<h3>${memberSvc.getOneMember(aCollection.all_no).mem_name}</h3>
																		<p>食譜數：${recipeSvc.findByMem_no(aCollection.all_no).size()}</p>
																		<p>追隨數：${collectionSvc.getCollectionSize(aCollection.all_no)}</p>
																	</td>
																	<td class="collection-newestRecipe">
																		<h4>The Newest</h4>
																	</td>
																	<td class="collection-recipe-img">
																		<img src="https://api.fnkr.net/testimg/100x80/00CED1/FFF/?text=img+placeholder">
																	</td>
																	<td class="collection-author-recipe-info">
																	<c:set var="newestRecipe" value="${recipeSvc.findByMem_no(aCollection.all_no).get(0)}" />
																		<h4>${newestRecipe.recipe_name}</h4>
																		<p>${newestRecipe.recipe_intro}
																		</p>
																		<form action="" class="text-right" method="post">
																			<input type="submit" class="btn btn-danger btn-xs" value="取消追隨">
																			<input type="hidden" name="action" value="delete">
																			<input type="hidden" name="coll_no" value="${aCollection.coll_no}">
																			<input type="hidden" name="tabID" value="tab2">
																		</form>
																	</td>
																</tr>
															</c:forEach>
															</table>
															
													</div>

									    	        <div role="tabpanel" class="tab-pane" id="tab3">	
									    	        	<table class="collection-chef">
																<tr >
																	<td class="chef-img">
																		<img class="img-circle" src="https://api.fnkr.net/testimg/80x80/00CED1/FFF/?text=img+placeholder">
																	</td>
																	<td class="chef-name">
																		<h3>史提芬周</h3>
																		
																	</td>
																	<td class="collection-chef-info">
																		<p class="chef-skill"><span>私廚專長：</span>湯是我非常喜愛的冬日暖湯，裡面滿滿蔬菜熬出清甜，花時間燉煮的牛尾風味濃郁、肉質軟嫩，輕拉就馬上與骨頭分開，主要營養之一的膠...
																		</p>
																		<p class="act-area"><span>活動地區：</span>湯是我非常喜愛的冬日暖湯，裡面滿滿蔬菜熬出清甜，花時間燉煮的牛尾風味濃郁、肉質軟嫩，輕拉就馬上與骨頭分開，主要營養之一的膠...
																		</p>
																		<form action="" class="text-right" method="post">
																			<input type="submit" class="btn btn-danger btn-xs" value="取消追隨">
																			<input type="hidden" name="action" value="delete">
																			<input type="hidden" name="chef_no" value="">
																			<input type="hidden" name="tabID" value="tab3">
																		</form>
																	</td>
																</tr>
														</table>
									    	        </div>
									    	    </div>
									    	</div>
										</div>
									</div>		    
								</div>	

			    		</div>
			    		<div class="col-xs-12 col-sm-3 col-sm-push-1 text-center member-style">
					    	<img src="https://api.fnkr.net/testimg/150x150/00CED1/FFF/?text=img+placeholder">
	    					<h3>mem_name</h3>
					
							<div class="col-xs-12 col-sm-6 count-style">
						    	<div>食譜數：0</div>
						    </div>
						    <div class="col-xs-12 col-sm-6 count-style">
						    	<div>追隨數：0</div>
						    </div>
						    <div class="col-xs-12 col-sm-12 text-left">
						    	<div class="mem-email">email:xxxx@gmail.com</div>
						    </div>
						    <div class="col-xs-12 col-sm-6">
						    	<a class="btn btn-primary">加入追隨</a>
						    </div>
						    <div class="col-xs-12 col-sm-6">
						    	<button id="addFriend" class="btn btn-primary" value="M00000001">加入好友</button>
						    </div>
					
					    	
					    </div>
					</div>
				
	    	</div>
	  	</div>



	<footer>
		Copyright &copy; 2016 Java Team 3 
	</footer>
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
