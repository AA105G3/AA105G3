<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef.model.*"%>

<%
	String mem_no = (String) session.getAttribute("mem_no");
	ChefVO chefVO=(ChefVO)request.getAttribute("chefVO");
%>
<!DOCTYPE html>
<html lang="">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>私廚申請頁面</title>
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
    
    body{
		background: #efede8;
		padding-top: 50px;
		position : relative;
		height : 100%;
	}
    #mainpage{
    	margin-top: 20px;
    	margin-bottom: 50px;
    }
	#mainpage .become-chef-input{
		border: 1px solid #000;
		margin-top: 10px;
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
	.chef-input-wrapper{
		padding:0 30px 0 30px;
		background: #f2f2f2;
	}
	table{
		margin-left: auto;
		margin-right: auto;
	}
	td{
		padding:10px;
	}
	label{
		font-size: 20px;
		padding-left: 0px !important;
	}
	td.chef_image-wrapper{
		padding:10px 0px 10px 20px;
		text-align:center;
	}
	td.chef_image-wrapper img{
		width: 200px;
		height: 200px;
		/*border: 3px solid #fff;*/
	}
	#chef_image{
		margin: 0px;
	}
	.form-group{
		margin:0px;
	}
	#chef_bnk_ac,#chef_bnk{
		/* font-size: 20px; */
	}
	textarea{
		resize: none;
	}
	.chef_lic-wrapper{
		position: relative;
	}
	.chef_lic-wrapper img{
		width: 590px;
		height: 250px;
	}
	.chef_lic-wrapper input{
		display: none;
		margin: 0px;
	}
	.menu-img-top{
		padding: 0px 1px 0px 1px;

	}
	.menu-img-top img{
		height: 250px;
		width: 300px;
	}
	.menu-img-bottom{
		padding:3px 1px;
	}
	.menu-img-bottom img{
		height: 160px;
		width: 200px;
	}
	.menu-img-top input,.menu-img-bottom input,#chef_image{
		display: none;
	}
	.chef_image-wrapper .clearImg{
		position: absolute;
		z-index: 1;
		top:90px;
		right:  100px;
		display: inline;
		color: red;
		font-size: 16px;
		border: 1px solid red; 
		padding: 1px 3px;
		border-radius: 25px;
		background: #fff;
	} 
	.chef_lic-wrapper .clearImg{
		position: absolute;
		z-index: 1;
		top:50px;
		right:  13px;
		display: inline;
		color: red;
		font-size: 16px;
		border: 1px solid red; 
		padding: 1px 3px;
		border-radius: 25px;
		background: #fff;
	}
	.menu-img-top .clearImg{
		position: absolute;
		z-index: 1;
		top:5px;
		right:  13px;
		display: inline;
		color: red;
		font-size: 16px;
		border: 1px solid red; 
		padding: 1px 3px;
		border-radius: 25px;
		background: #fff;
	}
	.menu-img-bottom .clearImg{
		position: absolute;
		z-index: 1;
		top:5px;
		right:  8px;
		display: inline;
		color: red;
		font-size: 16px;
		border: 1px solid red; 
		padding: 1px 3px;
		border-radius: 25px;
		background: #fff;
	}
	td.chef-video-wraper{
		padding: 1px;
		position: relative;
	}
	td.chef-video-wraper video{
		height: 200px;
		width: 300px;
		border:1px solid;
	}
	.clearVideo{
		position: absolute;
		z-index: 1;
		top:5px;
		right:  8px;
		display: inline;
		color: red;
		font-size: 16px;
		border: 1px solid red; 
		padding: 1px 3px;
		border-radius: 25px;
		background: #fff;
	}
	.chef-button-wrapper-left{
		padding: 20px 30px;
	}
	.chef-button-wrapper-right{
		padding: 20px 0px;
	}
	#mainpage h4{
		color: red;

	}
	#mainpage span{
		color:red;
	}
    </style>
</head>

<body>
    
    <header>
    	<c:import url="/front-end/frontNavbar.jsp"></c:import>
    </header>
    
<section id="top-img">
        <div class="col-xs-12 col-sm-12">
            <div class="top-img"></div>
            <!-- <img src="/AA105G3/images/title.jpg" height="500px" width="100%"> -->
        </div>
    </section>
    
    
    
    
	<div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-12 text-center">
                <h2>填寫私廚基本資訊</h2>
            </div>
        </div>
    </div>
<div class="container" id="mainpage">
        <div class="row">
			<div class="col-xs-12 col-sm-8 col-sm-offset-2 chef-input-wrapper" style="border:1px solid;">

			<form id="become-chef" METHOD="post" ACTION="<%=request.getContextPath()%>/chef/chef.do" name="form1" enctype="multipart/form-data">
				<h4>*為必填欄位</h4><button type="button" id="magic"></button>
			
				<table>
					<tr>
						<td>
							<div class="form-group ">
		                        <label for="chef_name"  class="control-label"><span>*</span>真實姓名：</label><span>${errorMsgs.get("nameError")}</span>
		                        <input type="text" name="chef_name" id="chef_name" value="${chefVO.chef_name}" class="form-control">
		                    </div>
						</td>
						<td rowspan="3" class="chef_image-wrapper">
						<div>
						<label><span>*</span>請上傳您的真實照片</label><span>${errorMsgs.get("chef_imgError")}</span>	
						</div>
						<label for="chef_image" id="123">
							<img src="/AA105G3/images/recipe_cont/stepNoPic.PNG" class="img-circle">
						</label>
						<i type="button" class="glyphicon glyphicon-remove clearImg btn"></i>
						<input type="File" class="imgUpload" name="chef_image" id="chef_image" size="45" value="" />
						</td>
					</tr>
					<tr>
						<td>
							<div class="form-group">
		                        <label for="chef_id" class=" control-label"><span>*</span>身分證字號：</label><span>${errorMsgs.get("idError")}</span>
		                       
		                        <input type="text" name="chef_id" id="chef_id" value="${chefVO.chef_id}" class="form-control" maxlength="10" minlength="10">
		                        
		                    </div>
						</td>
					</tr>
					
					<tr>
						<td>
							<div class="form-group ">
		                        <label for="chef_bnk" class="control-label"><span>*</span>匯款帳戶：</label><span>${errorMsgs.get("bankError")}</span>
		                        <div>
		                        <input type="text" name="chef_bnk" id="chef_bnk" value="${chefVO.chef_bnk}" class="form-inline" maxlength="3" style="width: 50px;">-
		                        <input type="text" name="chef_bnk_ac" id="chef_bnk_ac" value="${chefVO.chef_bnk_ac}" class="form-inline" maxlength="14" style="width: 220px;">
		                        </div>
		                    </div>
						</td>
					</tr>
					<tr>
						<td colspan="2" >
							<div class="form-group">
		                        <label for="chef_area" class=" control-label">願意服務的地區：<span>建議填寫</span></label>
		                       	<textarea name="chef_area" id="chef_area" value="" class="form-control" rows="3">${chefVO.chef_area}</textarea>
		                    </div>
						</td>
					</tr>
					<tr>
						<td colspan="2" >
							<div class="form-group">
		                        <label for="chef_intr" class=" control-label">自我簡介：</label>
		                       	<textarea name="chef_intr" id="chef_intr" value="" class="form-control" rows="3">${chefVO.chef_intr}</textarea>
		                    </div>
						</td>
					</tr>
					<tr>
						<td colspan="2" >
							<div class="form-group">
		                        <label for="chef_skill" class=" control-label"><span>*</span>擅長的料理風格：</label><span>${errorMsgs.get("skillError")}</span>
		                       	<textarea name="chef_skill" id="chef_skill" value="" class="form-control" rows="3"></textarea>
		                    </div>
						</td>
					</tr>
					<tr>
						<td colspan="2" >
							<div class="form-group">
		                        <label for="chef_menu" class=" control-label">請填寫您的參考菜單：<span>(請附上價格)</span></label>
		                       	<textarea name="chef_menu" id="chef_menu" value="" class="form-control" rows="3">${chefVO.chef_menu}</textarea>
		                    </div>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="chef_lic-wrapper">
							<div class="text-center"><label><span>*</span>廚藝相關證照(橫向):</label><span>${errorMsgs.get("licError")}</span></div>
							<label for="chef_lic">
								<img src="/AA105G3/images/recipe_cont/stepNoPic.PNG">
							</label>
							<i type="button" class="glyphicon glyphicon-remove clearImg btn"></i>
							<input type="File" class="imgUpload" id="chef_lic" name="chef_lic" size="45" value="" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="text-center"><label>菜單參考圖片:<span>(至少上傳左上的圖片)</span></label><span>${errorMsgs.get("rec_imgError")}</span></div>
							<div class="row">
								<div class="col-xs-12 col-sm-6 menu-img-top">
								<label for="chef_reci_image1">
									<img src="/AA105G3/images/recipe_cont/stepNoPic.PNG">
								</label>
								<i type="button" class="glyphicon glyphicon-remove clearImg btn"></i>
									<input type="File" class="imgUpload" id="chef_reci_image1" name="chef_reci_image1" size="45" value="" />
								</div>
								<div class="col-xs-12 col-sm-6 menu-img-top">
								<label for="chef_reci_image2">
									<img src="/AA105G3/images/recipe_cont/stepNoPic.PNG">
								</label>
								<i type="button" class="glyphicon glyphicon-remove clearImg btn"></i>
									<input type="File" class="imgUpload" id="chef_reci_image2" name="chef_reci_image2" size="45" value="" />
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12 col-sm-4 menu-img-bottom">
								<label for="chef_reci_image3">
									<img src="/AA105G3/images/recipe_cont/stepNoPic.PNG">	
								</label>
								<i type="button" class="glyphicon glyphicon-remove clearImg btn"></i>
								 <input type="File" class="imgUpload" id="chef_reci_image3" name="chef_reci_image3" size="45" value="" />
								</div>
								<div class="col-xs-12 col-sm-4 menu-img-bottom">
								<label for="chef_reci_image4">
									<img src="/AA105G3/images/recipe_cont/stepNoPic.PNG">
								</label>
								<i type="button" class="glyphicon glyphicon-remove clearImg btn"></i>
								<input type="File" class="imgUpload" id="chef_reci_image4" name="chef_reci_image4" size="45" value="" />
								</div>
								<div class="col-xs-12 col-sm-4 menu-img-bottom">
								<label for="chef_reci_image5">
									<img src="/AA105G3/images/recipe_cont/stepNoPic.PNG">
								</label>
								<i type="button" class="glyphicon glyphicon-remove clearImg btn"></i>
								<input type="File" class="imgUpload" id="chef_reci_image5" name="chef_reci_image5" size="45" value="" />
								</div>

							</div>
							
						</td>
					</tr>
					<tr>
						<td colspan="2" class="text-center"><label>請上傳您實際料理時的影片<span>(必須上傳2部影片)</span></label><span>${errorMsgs.get("chef_movieError")}</span></td>
					</tr>
					<tr>
						<td class="chef-video-wraper">
							<video id="videoPlayer1" controls>
								<source id="currentVID1" src="" type="">
							</video>
							<i type="button" id="clearVideo1" class="glyphicon glyphicon-remove clearVideo btn"></i>
							<input id="videoUpload1" type="file" name="chef_movie1">
						</td>
						<td class="chef-video-wraper">
							<video id="videoPlayer2" controls>
								<source id="currentVID2" src="" type="">
							</video>
							<i type="button" id="clearVideo2" class="glyphicon glyphicon-remove clearVideo btn"></i>
							<input id="videoUpload2" type="file" name="chef_movie2">

						</td>
					</tr>
					<tr>
						<td class="chef-button-wrapper-left text-right">
							<button type="button" id="become-chef-cancel" class="btn btn-danger btn-lg">取消</button>
						</td>
						<td class="chef-button-wrapper-right text-left">
							<input type="hidden" name="mem_no" value="${mem_no}">      
                    		<input type="hidden" name="action" value="insert"> 
                    		<input type="submit" class="btn btn-primary btn-lg" value="送出申請"> 
						</td>
					</tr>
				</table>
				</form>
			</div>            
    </div>
</div>
    <c:import url="/front-end/chat/inviteChat.jsp" ></c:import>
    <footer id="theFooter">
		Copyright &copy; 2017 Java Team 3 
	</footer>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script type="text/javascript">

		$('#become-chef-cancel').click(function(){
			window.history.go(-1);
		})

		$('.clearImg').click(function(){
				$(this).siblings('input').val("");
				$(this).siblings('label').children('img').attr('src','/AA105G3/images/recipe_cont/stepNoPic.PNG');
		})
		$('#clearVideo1').click(function(){
				var player = document.getElementById("videoPlayer1");
				var currentVID = document.getElementById("currentVID1");
				currentVID.setAttribute("src", "");
				player.load();
				document.getElementById("videoUpload1").value="";
		})

		$('#clearVideo2').click(function(){
				var player = document.getElementById("videoPlayer2");
				var currentVID = document.getElementById("currentVID2");
				currentVID.setAttribute("src", "");
				player.load();
				document.getElementById("videoUpload2").value="";
		})


		//img
		$('.imgUpload').change(function(){
				readURL(this);
		})


		function readURL(input) {
	        if (input.files && input.files[0]) {
	            var reader = new FileReader();

	            reader.onload = function (e) {
	                $(input).siblings('label').children('img').attr('src', e.target.result);
	            }

	            reader.readAsDataURL(input.files[0]);
	        }
	    }


	    //video1
	    $('#videoUpload1').change(function(){
			  var player = document.getElementById("videoPlayer1");
			  var currentVID = document.getElementById("currentVID1");
			  var selectedLocalVID = document.getElementById("videoUpload1").files[0];
			  currentVID.setAttribute("src", URL.createObjectURL(selectedLocalVID));
			  player.load();
		})

	    $('#videoUpload2').change(function(){
			  var player = document.getElementById("videoPlayer2");
			  var currentVID = document.getElementById("currentVID2");
			  var selectedLocalVID = document.getElementById("videoUpload2").files[0];
			  currentVID.setAttribute("src", URL.createObjectURL(selectedLocalVID));
			  player.load();
		})
		
		$().ready(function(){
				$('#magic').click(function(){
					$('#chef_name').val('伽夫');
					$('#chef_id').val('F123456789');
					$('#chef_bnk').val('202');
					$('#chef_bnk_ac').val('123456789124');
					$('#chef_area').val('桃園市');
					$('#chef_intr').val('我的廚藝，不能用話語解釋。');
					$('#chef_skill').val('各式中式料理，不論是煎炒煮炸都擅長。');
					$('#chef_menu').val('各式年菜  $200<br>\n各式中式小菜 $150。<br>\n價格可議。');
					
				})
				
			})
		
		</script>
</body>

</html>
