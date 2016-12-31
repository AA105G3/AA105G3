<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.recipe.model.*"%>
<%@ page import="com.recipe_cont.model.*"%>
<% session.setAttribute("mem_no", "M00000005"); %>

<jsp:useBean id="recipeVO" scope="request" class="com.recipe.model.RecipeVO"/>
<jsp:useBean id="ingredients" scope="request" class="java.util.ArrayList"/>
<jsp:useBean id="quantity" scope="request" class="java.util.ArrayList"/>
<jsp:useBean id="list" scope="request" class="java.util.ArrayList"/>
<jsp:useBean id="recipe_cont_set" scope="request" type="java.util.LinkedHashSet" />


<!DOCTYPE html>
<html lang="">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>食譜修改</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	<style type="text/css">
		.recipe-title h3{
			margin-bottom: 5px;
		}
		.sec-recipe-name{
			margin-top: 5px;
			margin-bottom: 10px;
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
			height: 121px;
			width: 171px;
		}
		h3 span{
			font-size:16px;
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
							<img src="<%=request.getContextPath()%>/recipe/showRecipe_pic.do?recipe_no=${recipeVO.recipe_no}" id="image0">
								<input type="file" name="recipe_pic" id="upload0" onchange="showImage(0)">
							</div>
							
							<div class="recipe-intro">
								<h3>簡介</h3>
								<textarea name="recipe_intro" rows="6" cols="40" placeholder="請輸入食譜簡介" style="resize: none">${recipeVO.recipe_intro}</textarea>
							</div>
						</div>
						<div class="col-xs-12 col-sm-6">
							<h3 id="foodMaterTitle">食材</h3>
							
						<c:forEach var="ingredient" items="${ingredients}" varStatus="s">
							<div class="foodMater-wrapper">
								<div class="foodMater">
									<input type="text" class="ingredients" name="ingredients" placeholder="食材" value="${ingredient}">
									<input type="text" class="quantity" name="quantity" placeholder="份量" value="${quantity[s.index]}">
									<a class="btn"><i class="glyphicon glyphicon-trash ingredients-trash"></i></a>
								</div>
							</div>
						</c:forEach>	
						
							<div class="moreFoodMater btn" id="moreFoodMater">
								增加食材
							</div>
						</div>
						<div class="col-xs-12 col-sm-12 step-area">
							<div class="step-title">
								<h3>步驟<span>(請重新上傳圖片)</span></h3>
							</div>
						
						<c:forEach var="recipe_contVO" items="${recipe_cont_set}">
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
										<a class="btn"><i class="glyphicon glyphicon-plus step-plus"></i></a>
										<a class="btn"><i class="glyphicon glyphicon-trash step-trash"></i></a>
									</div>
									<div class="step-cont-wrapper">
										<textarea name="step_cont" rows="4" cols="55" placeholder="請輸入食譜步驟內容" style="resize: none">${recipe_contVO.step_cont}</textarea>	
									</div>
								</div>
							</div>
						</c:forEach>
						
						</div>
					</div>
						
				
			</div>
			<div class="col-xs-12 col-sm-1">
				<div>
					<input type="hidden" name="recipe_no" value=${param.recipe_no}>
					<button class="btn btn-primary createRecipe-btn" type="submit" name="action" value="update">完成修改</button>
				</div>
			</div>				

			</form>
			</div>
		</div>
	</div>
	
	
								

	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript">


	// 增加食材輸入區塊
		$("#moreFoodMater").click(function(){
			var idx=$(".foodMater").length;
			
			$(".foodMater-wrapper").last().append('<div class="foodMater"><input type="text" class="ingredients" name="ingredients" placeholder="食材" value=""> <input type="text" class="quantity" name="quantity" placeholder="份量" value=""> <a class="btn"><i class="glyphicon glyphicon-trash ingredients-trash" ></i></a></div>')

			$(".ingredients-trash").click(function(){
			$(this).parent().parent().empty().remove();	
			})
		})

		
<<<<<<< HEAD
	</tr>
	<tr>
		<td>
		<input type="file" name="recipe_pic" id = "upLoadFile" onchange="showImage()"></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="recipe_no" value=${recipeVO.recipe_no}>
<input type="submit" value="送出修改"></FORM>
<img id="image" src="<%=request.getContextPath()%>/recipe/showRecipe_pic.do?recipe_no=${recipeVO.recipe_no}" style="width:300px;"/>
=======
	// 食材區初始垃圾桶event	
		$(".ingredients-trash").click(function(){
			$(this).parent().parent().empty().remove();	
		})

	// 步驟區初始+號event

		
		function appendRecipeCont(){
			var step = $(".step-plus").length+1;
			var html = '<div class="row step-wrapper">'+
						'<div class="col-xs-12 col-sm-4 step-left">'+
						 '<img src="<%=request.getContextPath()%>/images/recipe_cont/stepNoPic.PNG" id="image'+step+'">'+
						 '<input type="file" name="step_pic" id="upload'+step+'" onchange="showImage('+step+')">'+
						'</div>'+
						'<div class="col-xs-12 col-sm-8 step-right">'+
						'<div class="col-xs-12 col-sm-6">'+'<h2>'+step+'</h2>'+' <input type="hidden" name="step" value="'+step+'"></div>'+
						'<div class="col-xs-12 col-sm-6 step-trash-wrapper">'+
						 '<a class="btn"><i class="glyphicon glyphicon-plus step-plus"></i></a>'+
						 '<a class="btn"><i class="glyphicon glyphicon-trash step-trash"></i></a>'+
						'</div>'+
						'<div class="step-cont-wrapper">'+
						'<textarea name="step_cont" rows="4" cols="55" placeholder="請輸入食譜步驟內容" style="resize: none"></textarea>'+
						'</div>'+'</div>'+'</div>';

			$(this).parent().parent().parent().parent().parent().append(html);
		}
	//步驟區垃圾桶

	$('body').on('click', '.step-trash',dropStep);

		function dropStep(){

			$(this).parent().parent().parent().parent().empty().remove();
			var stepArray = $("h2")
			for(var i = 0;i<=stepArray.length;i++){
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
>>>>>>> refs/heads/liquid-develop2

	</script>
</body>
</html>