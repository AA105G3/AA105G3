<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.recipe.model.*"%>
<%@ page import="com.recipe_cont.model.*"%>

<jsp:useBean id="recipeVO" scope="request" class="com.recipe.model.RecipeVO"/>
<jsp:useBean id="ingredients" scope="request" class="java.util.ArrayList"/>
<jsp:useBean id="quantity" scope="request" class="java.util.ArrayList"/>
<jsp:useBean id="list" scope="request" class="java.util.ArrayList"/>


<!DOCTYPE html>
<html lang="">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>新增食譜</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	<style type="text/css">
	
		body{
			background: #efede8;
			padding-top: 90px;
		}
		.recipe-title h3{
			margin-bottom: 5px;
		}
		.sec-recipe-name{
			margin-top: 5px;
			margin-bottom: 10px;
			font-size:20px;
			height:40px;
		}
		#image0{
			width: 300px;
			height: 200px;
		}
		.recipe-img{
			width: 300px;
			height: 200px;
		}

		.update-recipe-wrapper{
			margin-top: 20px;
		}
		.quantity{
			width: 60px;
		}
		.quantity,.ingredients{
			height: 35px;
			margin-right: 10px;
		}
		.foodMater{
			padding: 10px 0px;
			vertical-align: middle;
			border-bottom: 1px solid #cfccbe;
			margin:0px;
		}
		#foodMaterTitle{
			margin:5px 0px 0px 0px;
		}
		.glyphicon-trash,.glyphicon-stepTrash{
			/*font-size: 20px;*/
			color: red;
			padding: 6px;
			border: 1px solid red;
			border-radius: 15px;
		}
		a.btn{
			padding:0px;
		}
		.step-plus{
			color: #55b5f0;
			padding: 6px;
			border: 1px solid #55b5f0;
			border-radius: 20px;
			text-align: center;
		}
	
		.moreFoodMater{
			text-align: center;
			border:1px solid steelblue;
			padding: 5px 110px;
			font-size: 18px;
			margin-top: 10px;
			color:steelblue;
		}
		.step-area{
			margin-top: 30px;
		}
		.step-title{
			border-bottom: 1px solid #cfccbe;
		}
		.step-wrapper{
			padding-top: 20px;
			height: 200px;
			border-bottom: 1px solid #cfccbe;
			margin: 0px 5px;

		}
		.recipe-intro{
			margin-top: 40px;
		}
		.step-area h2{
			margin: 0px;
		}
		.step-left{
		}
		.step-right{
			padding:10px 0px;
		}
		
		.step-trash-wrapper{
			text-align: right;
			padding-top: 5px;
			height: 40px;

		}
		.step-cont-wrapper{
			padding-left: 10px;
			margin-top:  10px;
		}
		.step-wrapper img{
			border:2px solid #fff;
			height: 121px;
			width: 171px;
			margin-bottom:3px;
		}
		.recipe-select{
			margin-left:170px;
		}
		.write-recipe{
			margin-right:190px;
		}
		.createRecipe-btn{
			margin-top:40px;
			padding:5px 30px;
		}
		.cancelRecipe-btn{
			margin-top:10px;
			padding:5px 30px;
		}
		.col-sm-offset-2.col-sm-7{
			border:1px solid gray;
			background:#fff;
		}
		.video-area{
			border-top:1px solid #cfccbe;
			border-bottom:1px solid #cfccbe;
			margin:20px 2% 0px 2%;
			width:96%;
			padding:15px 5px;
			
		}
		.video-area h3{
			margin:0px 0px 10px 0px;
		}
	</style>
</head>
<body>
	
	<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
	<c:import url="/front-end/frontNavbar.jsp" ></c:import>
	<header>
	<c:import url="/front-end/recipe/RecipeSearchBar.jsp" ></c:import>
	</header>
	<div class="container">
		<div class="row update-recipe-wrapper">
			<div class="col-xs-12 col-sm-offset-2 col-sm-7">
			<form class="form" action="<%=request.getContextPath()%>/recipe/recipe.do" method="POST" enctype="multipart/form-data">

					<div class="recipe-title">
					<h3>食譜名稱：</h3>
						<input type="text" class="form-control sec-recipe-name" placeholder="例如:海鮮燉飯  20字以內" 
						name="recipe_name" value="${recipeVO.recipe_name}" maxlength="20">
					</div>
					<div class="row recipe-wrapper">
						<div class="col-xs-12 col-sm-6">
							<div class="recipe-img-wrapper">
							<img src="<%=request.getContextPath()%>/images/nopic.gif" id="image0">
								<input type="file" name="recipe_pic" id="upload0" onchange="showImage(0)">
							</div>
							
							<div class="recipe-intro">
								<h3>簡介</h3>
								<textarea name="recipe_intro" rows="6" cols="40" placeholder="請輸入食譜簡介" style="resize: none">${recipeVO.recipe_intro}</textarea>
							</div>
						</div>
						<div class="col-xs-12 col-sm-6">
							<h3 id="foodMaterTitle">食材</h3>
							
							<c:if test="${quantity.isEmpty()}">
							<div class="foodMater-wrapper">
								<div class="foodMater">
									<input type="text" class="ingredients" name="ingredients" placeholder="食材" value="">
									<input type="text" class="quantity" name="quantity" placeholder="份量" value="">
									<a class="btn"><i class="glyphicon glyphicon-trash ingredients-trash"></i></a>
								</div>
								
							</div>
							<div class="foodMater-wrapper">
								<div class="foodMater">
									<input type="text" class="ingredients" name="ingredients" placeholder="食材" value="">
									<input type="text" class="quantity" name="quantity" placeholder="份量" value="">
									<a class="btn"><i class="glyphicon glyphicon-trash ingredients-trash"></i></a>
								</div>
								
							</div>
							<div class="foodMater-wrapper">
								<div class="foodMater">
									<input type="text" class="ingredients" name="ingredients" placeholder="食材" value="">
									<input type="text" class="quantity" name="quantity" placeholder="份量" value="">
									<a class="btn"><i class="glyphicon glyphicon-trash ingredients-trash"></i></a>
								</div>
								
							</div>
							<div class="foodMater-wrapper">
								<div class="foodMater">
									<input type="text" class="ingredients" name="ingredients" placeholder="食材" value="">
									<input type="text" class="quantity" name="quantity" placeholder="份量" value="">
									<a class="btn"><i class="glyphicon glyphicon-trash ingredients-trash"></i></a>
								</div>
								
							</div>
							<div class="foodMater-wrapper">
								<div class="foodMater">
									<input type="text" class="ingredients" name="ingredients" placeholder="食材" value="">
									<input type="text" class="quantity" name="quantity" placeholder="份量" value="">
									<a class="btn"><i class="glyphicon glyphicon-trash ingredients-trash"></i></a>
								</div>
								
							</div>
						</c:if>
						<!-- 錯誤回來時，食材空格複寫 -->
						<c:if test="${!quantity.isEmpty()}">
						<c:forEach var="ingredient" items="${ingredients}" varStatus="s">
							<div class="foodMater-wrapper">
								<div class="foodMater">
									<input type="text" class="ingredients" name="ingredients" placeholder="食材" value="${ingredient}">
									<input type="text" class="quantity" name="quantity" placeholder="份量" value="${quantity[s.index]}">
									<a class="btn"><i class="glyphicon glyphicon-trash ingredients-trash"></i></a>
								</div>
							</div>
						</c:forEach>	
						</c:if>
						
							<div class="moreFoodMater btn" id="moreFoodMater">
								增加食材
							</div>
						</div>
						
						
						<div class="col-xs-12 col-sm-12 video-area">
						<h3>上傳影片</h3>
							<input type="file" name="film_file">
						</div>
						
						
						
						<div class="col-xs-12 col-sm-12 step-area">
							<div class="step-title">
								<h3>步驟</h3>
							</div>
						<c:if test="${list.isEmpty()}">
							<div id="div_0" class="row step-wrapper">
								<div class="col-xs-12 col-sm-4 step-left">
									<img src="<%=request.getContextPath()%>/images/recipe_cont/stepNoPic.PNG" id="image1">
									<input type="file" name="step_pic" class="upload" id="upload1" onchange="showImage(1)">
								</div>
								<div class="col-xs-12 col-sm-8 step-right">
									<div class="col-xs-12 col-sm-6">
										<h2>1</h2>
										<input type="hidden" name="step" value="1">
									</div>
									<div class="col-xs-12 col-sm-6 step-trash-wrapper">
										<a class="btn"><i id="plus_0" class="glyphicon glyphicon-plus step-plus"></i></a>
										<a class="btn"><i class="glyphicon glyphicon-trash step-trash"></i></a>
									</div>
									<div class="step-cont-wrapper">
										<textarea name="step_cont" rows="4" cols="55" placeholder="請輸入食譜步驟內容" style="resize: none"></textarea>	
									</div>
								</div>
							</div>
						</c:if>
						
						<c:if test="${!list.isEmpty()}">
						<c:forEach var="recipe_contVO" items="${list}">
							<div class="row step-wrapper">
								<div class="col-xs-12 col-sm-4 step-left">
									<img src="<%=request.getContextPath()%>/images/recipe_cont/stepNoPic.PNG" id="image${recipe_contVO.step}">
									<input type="file" name="step_pic" class="upload" id="upload${recipe_contVO.step}" onchange="showImage(${recipe_contVO.step})">
								</div>
								<div class="col-xs-12 col-sm-8 step-right">
									<div class="col-xs-12 col-sm-6">
										<h2>${recipe_contVO.step}</h2>
										<input type="hidden" name="step" value="${recipe_contVO.step}">
									</div>
									<div class="col-xs-12 col-sm-6 step-trash-wrapper">
										<a class="btn"><i id="plus_${recipe_contVO.step}" class="glyphicon glyphicon-plus step-plus"></i></a>
										<a class="btn"><i class="glyphicon glyphicon-trash step-trash"></i></a>
									</div>
									<div class="step-cont-wrapper">
										<textarea name="step_cont" rows="4" cols="55" placeholder="請輸入食譜步驟內容" style="resize: none">${recipe_contVO.step_cont}</textarea>	
									</div>
								</div>
							</div>
						</c:forEach>
						</c:if>
						
						</div>
					</div>
						
				
			</div>
			<div class="col-xs-12 col-sm-1">
				<div>
					<button class="btn btn-primary createRecipe-btn" type="submit" name="action" value="insert">發布</button>
				</div>
				<div>
					<button class="btn btn-danger cancelRecipe-btn" id="cancelRecipe-btn" type="button" >取消</button>
				</div>
			</div>				

			</form>
			</div>
		</div>
	</div>
	
	
								

	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	
	
	$('#cancelRecipe-btn').click(function(){
		window.history.go(-1);
	})
	
	// 增加食材輸入區塊
		$("#moreFoodMater").click(function(){
			var idx=$(".foodMater").length;
			
			$(".foodMater-wrapper").last().append('<div class="foodMater"><input type="text" class="ingredients" name="ingredients" placeholder="食材" value=""> <input type="text" class="quantity" name="quantity" placeholder="份量" value=""> <a class="btn"><i class="glyphicon glyphicon-trash ingredients-trash" ></i></a></div>')

			$(".ingredients-trash").click(function(){
			$(this).parent().parent().empty().remove();	
			})
		})

		
	// 食材區初始垃圾桶event	
		$(".ingredients-trash").click(function(){
			$(this).parent().parent().empty().remove();	
		})

	// 步驟區初始+號event

		
		function appendRecipeCont(){
			var step = $(".step-plus").length+1;
			var html = '<div id="div_'+step+'" class="row step-wrapper">'+
						'<div class="col-xs-12 col-sm-4 step-left">'+
						 '<img src="<%=request.getContextPath()%>/images/recipe_cont/stepNoPic.PNG" id="image'+step+'">'+
						 '<input type="file" name="step_pic" id="upload'+step+'" onchange="showImage('+step+')">'+
						'</div>'+
						'<div class="col-xs-12 col-sm-8 step-right">'+
						'<div class="col-xs-12 col-sm-6">'+'<h2>'+step+'</h2>'+' <input type="hidden" name="step" value="'+step+'"></div>'+
						'<div class="col-xs-12 col-sm-6 step-trash-wrapper">'+
						 '<a class="btn"><i id="plus_'+step+'" class="glyphicon glyphicon-plus step-plus"></i></a>'+
						 '<a class="btn"><i class="glyphicon glyphicon-trash step-trash"></i></a>'+
						'</div>'+
						'<div class="step-cont-wrapper">'+
						'<textarea name="step_cont" rows="4" cols="55" placeholder="請輸入食譜步驟內容" style="resize: none"></textarea>'+
						'</div>'+'</div>'+'</div>';
			
			var indexNo=$(this).attr("id").replace("plus_","");
			$(html).insertAfter("#div_"+indexNo);
			var stepArray = $(".step-wrapper h2")
			
			for(var i = 0;i<stepArray.length;i++){
				stepArray[i].innerHTML=i+1;
			}
		}
	//步驟區垃圾桶
	$('body').click(function(){
		step = $(".step-plus").length+1;
		//image = $('.step-wrapper img');
		stepDiv = $('.step-wrapper');
		plus = $('.step-plus');
		var stepValue = $('.step-wrapper input[name="step"]')
		for(var i = 0;i<step-1;i++){
			stepValue[i].value = i+1;
			stepDiv[i].id = 'div_'+(i+1);
			plus[i].id = 'plus_'+(i+1);
		}
		
	})
	
	$('body').on('click', '.step-trash',dropStep);

		function dropStep(){

			$(this).parent().parent().parent().parent().empty().remove();
			var stepArray = $(".step-wrapper h2")
			
			for(var i = 0;i<stepArray.length;i++){
				stepArray[i].innerHTML=i+1;
			}
			
			
			
		}

		$('body').on('click', '.step-plus',appendRecipeCont);

		function showImage(idx){
			var file = document.getElementById("upload"+idx).files[0];				
			var read = new FileReader();
			read.onload=function(){
					var image = document.getElementById('image'+idx);
						image.src=read.result;
						
					};
			read.readAsDataURL(file);	
		} 

	</script>
</body>
</html>