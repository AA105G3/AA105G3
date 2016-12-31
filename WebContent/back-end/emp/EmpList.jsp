<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>


<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />

<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>員工資料管理</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/css/bootstrap-select.min.css">
		<script></script>
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<style type="text/css">
			#top_header{
 	background: #f5f5dc; 
}
#hyper_link{
	background: #f5f5dc;
}
#table_title{
	font-size: 25px;
	padding:5px 0px;
}
#menu_zone{
	background: #f5deb3;
}
#accordion2{
	margin-top: 15px;
}
#accordion2 div{
	background: #fee4cd;
}
#logo{
	width: 50px;
	height: 50px;
}
#head_icon{
	width: 25px;
	height: 25px;
	margin-top: 13px;
}

thead{
	background: #e2fede;
}

#the_footer{
	clear:both;
	text-align:center;
	background: #2b1100;
	clear: both;
	position:fixed ;
	bottom: 0px;
	width: 100%;
}
.lightcolor{
	color: #fff;
}
.pagination{
	margin-left: 300px;
}
#search{
	width: 350px;
	text-align: center;
}
#insert{
	/* width: 300px; */
}

.emp-list-info-title{
	padding:0px !important;
}
.emp-info-title{
	padding:0px;
}
.emp-info-head{
	margin:0px;
}
.emp-info-head td{
	padding: 5px 5px !important;
	border:1px solid #ddd;
}
.emp_no,.emp_name,.emp_account,.emp_email,.emp_phone{
	width: 256px;
}
.emp_no_info{
	width: 254px;
}
.emp_account_info,.emp_email_info,.emp_name_info{
	width: 256px;
}
.emp-info-a:hover{
	text-decoration: none;
}
.emp-info-a:link{
	text-decoration: none;
}
.emp-info-wrapper{
	margin:0px;
}
.emp-info-inner{
	font-size: 16px;
	padding-left: 5px;
}
.emp_auth-update,.emp-info-update{
	font-size: 15px;
	width: 112px;
}
.emp_auth-update-wrapper{
	margin-bottom: 5px;
}
tr{
	border:0px;
}
.modal{
	margin-top: 100px;
}
.modal-title{
	text-align: center;
}
#emp_address{
	width:350px;
}
#emp_status{
	height: 32px;
}
.modal-footer{
	text-align:center;
}

.add-emp{
	margin-top: 29px;
	font-size: 15px;
}



		</style>
	</head>

	<body>
		<nav class="navbar navbar-default" id="top_header">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
					<span class="sr-only">選單切換</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<img src="images/New_Logo2.0.png" href="#" id="logo">				
			</div>
		
			<!-- 手機隱藏選單區 -->
			<div class="collapse navbar-collapse navbar-ex1-collapse" id="top_header">
				<!-- 左選單 -->
				<ul class="nav navbar-nav">
					<li class="active"><a href="#" id=hyper_link>FOOD TIME分享食光</a></li>				
				</ul>
				<!-- 右選單 -->

				<ul class="nav navbar-nav navbar-right">
					<li><img src="images/頭像icon1.png" href="#" id="head_icon"></li>
					<li><a href="#">傑利鼠您好</a></li>
					<li><a href="#">登出</a></li>
				</ul>
			</div>
			<!-- 手機隱藏選單區結束 -->
		</nav>
		

			<div class="col-xs-12 col-sm-2" id="menu_zone">
				<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
				  	<!-- 區塊1 -->
				  	<div class="panel panel-default">
				    	<div class="panel-heading" role="tab" id="panel1">					      
				    		<h4 class="panel-title">
					      		<i class=" glyphicon glyphicon-user"></i>
					        	<a href="#aaa" data-parent="#accordion2" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="aaa">
					          	會員管理
					        	</a>
					    	</h4>
						</div>
					    <div id="aaa" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel1">
					      	<div class="list-group">
					      		<a href="#" class="list-group-item">會員資料查詢</a>
					      		<a href="#" class="list-group-item">私廚資格審核</a>
					      		<a href="#" class="list-group-item">私廚資料管理</a>
					      	</div>
					    </div>
					</div>
					<!-- 區塊2 -->
					<div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="panel2">
					      	<h4 class="panel-title">
					      		<i class=" glyphicon glyphicon-pawn"></i>
					        	<a href="#bbb" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="bbb">
					          	員工管理
					        	</a>
					      	</h4>
					    </div>
					    <div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2">
					      	<div class="list-group">
					        	<a href="#" class="list-group-item">員工權限管理</a>
					      		<a href="#" class="list-group-item">員工資料管理</a>  
					      	</div>
					    </div>
					</div>
					<!-- 區塊3 -->
					<div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="panel3">
					      	<h4 class="panel-title">
					      		<i class=" glyphicon glyphicon-exclamation-sign"></i>
					        	<a href="#ccc" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ccc">
					          	檢舉管理
					        	</a>
					      	</h4>
					    </div>
					    <div id="ccc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel3">
					      	<div class="list-group">
					        	<a href="#" class="list-group-item">文章檢舉審核</a>
					      		<a href="#" class="list-group-item">食譜檢舉審核</a>
					      		<a href="#" class="list-group-item">實況檢舉審核</a>
					      		<a href="#" class="list-group-item">影片檢舉審核</a>
					      	</div>
					    </div>
					</div>
					<!-- 區塊4 -->
					<div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="panel4">
					      	<h4 class="panel-title">
					      		<i class=" glyphicon glyphicon-book"></i>
					        	<a href="#ddd" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ddd">
					          	食譜管理
					        	</a>
					      	</h4>
					    </div>
					    <div id="ddd" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel4">
					      	<div class="list-group">
					        	<a href="#" class="list-group-item">食譜類別管理</a>
					      		<a href="#" class="list-group-item">未分類食譜管理</a>
					      	</div>
					    </div>
					</div>
					<!-- 區塊5 -->
					<div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="panel5">
					      	<h4 class="panel-title">
					      		<i class=" glyphicon glyphicon-paste"></i>
					        	<a href="#eee" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="eee">
					          	前端網頁管理
					        	</a>
					      	</h4>
					    </div>
					    <div id="eee" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel5">
					      	<div class="list-group">
					        	<a href="#" class="list-group-item">廣告管理</a>
					      		<a href="#" class="list-group-item">公告管理</a>
					      	</div>
					    </div>
					</div>
					<!-- 區塊6 -->
					<div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="panel6">
					      	<h4 class="panel-title">
								<i class=" glyphicon glyphicon-equalizer"></i>
					        	<a href="#fff" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="fff">
					          	市集管理
					        	</a>
					      	</h4>
					    </div>
					    <div id="fff" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel6">
					      	<div class="list-group">
					        	<a href="#" class="list-group-item">商品上下架</a>
					      		<a href="#" class="list-group-item">商品資料管理</a>
					      	</div>
					    </div>
					</div>
					<!-- 區塊7 -->
					<div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="panel7">
					      	<h4 class="panel-title">
					      		<i class=" glyphicon glyphicon-list-alt"></i>
					        	<a href="#ggg" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ggg">
					          	商品訂單管理
					        	</a>
					      	</h4>
					    </div>
					    <div id="ggg" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel7">
					      	<div class="list-group">
					        	<a href="#" class="list-group-item">商品訂單查詢</a>				      
					      	</div>
					    </div>
					</div>
                    <!-- 區塊8 -->
					<div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="panel8">
					      	<h4 class="panel-title">
					      		<i class=" glyphicon glyphicon-cutlery"></i>
					        	<a href="#hhh" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="hhh">
					          	私廚平台管理
					        	</a>
					      	</h4>
					    </div>
					    <div id="hhh" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel8">
					      	<div class="list-group">
					        	<a href="#" class="list-group-item">使用平台金流</a>
					        	<a href="#" class="list-group-item">私廚訂單查詢</a>
					        	<a href="#" class="list-group-item">仲介金流</a>
					        	<a href="#" class="list-group-item">退款審核</a>
					      	</div>
					    </div>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-10">
			<div class="container">
				<div class="row">
					<table class="table table-hover table-striped table-bordered table-condensed">
					<caption id="table_title">
					<div class="col-xs-12 col-sm-6">
						<h2>員工資料管理</h2>
					</div>
					<div class="col-xs-12 col-sm-6 text-right" style="height: 63px;">
						<a href="<%=request.getContextPath()%>/back-end/emp/EmpHasLeft.jsp" class="btn btn-default add-emp" >已離職員工資料</a>
						<a href="<%=request.getContextPath()%>/back-end/emp/EmpRegister.jsp" class="btn btn-primary add-emp" >員工新增</a>
					</div>
					</caption>
					<thead>
						<tr>
							<th class="emp_no">員工編號</th>
   							<th class="emp_name">員工姓名</th>
   							<th class="emp_account">員工帳號</th>
   							<th class="emp_email">員工信箱</th>
   							<th class="emp_job">職稱</th>
   							
   							
						</tr>
					</thead>
					<tbody>
					<c:forEach var="empVO" items="${empSvc.all}" >
					<c:if test="${empVO.emp_status==1}">
						<tr>
							<td class="emp-list-info-title" colspan="6">
								<div class="panel panel-default emp-info-wrapper">
				    				<div class="panel-heading emp-info-title" role="tab" id="panel1">					      
				    				<h4 class="panel-title">
					        			<a href="#E${empVO.emp_no}" data-parent="#accordion2" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="E${empVO.emp_no}" class="emp-info-a">
					        	  		<table class="table emp-info-head">
					        	  			<tr>
					        	  				<td class="emp_no_info">${empVO.emp_no}</td>
					        	  				<td class="emp_name_info">${empVO.emp_name}</td>
					        	  				<td class="emp_account_info">${empVO.emp_account}</td>
					        	  				<td class="emp_email_info">${empVO.emp_email}</td>
					        	  				
					        	  				<td class="emp_job_info">${empVO.emp_job}</td>
					        	  				
					        	  			</tr>
					        	  		</table>
					        		</a>

					    			</h4>
									</div>
								    <div id="E${empVO.emp_no}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel1">
								      	<div>
								      		<div class="list-group-item">
								      			<div class="row">
								      			<div class="col-xs-12 col-sm-10 emp-info-inner">
								      				<label>身分證字號 :</label>&nbsp<span>${empVO.emp_id}</span>&nbsp&nbsp&nbsp&nbsp
								      				<label>連絡電話 :</label>&nbsp<span>${empVO.emp_phone}</span>&nbsp&nbsp&nbsp&nbsp
								      				<label>到職日 :</label>&nbsp<span>${empVO.emp_hiredate}</span><br>
								      				<label>地址 :</label>&nbsp<span>${empVO.emp_address}</span><br>
								      				<label>員工狀態 :</label>&nbsp<span>${empVO.emp_status==0? "已離職":"在職"}</span><br>
								      				<label>擁有權限 :</label>&nbsp<span>員工資料管理、會員管理</span>
								      			</div>
								      			<div class="col-xs-12 col-sm-2 text-right">
								      				<div class="emp_auth-update-wrapper">
								      					<button class="btn btn-primary btn-sm emp-info-update" onclick="updateEmpInfo(${empVO.emp_no})">修改基本資料</button>
								      				</div>
								      				<div>
								      					<button class="btn btn-sm btn-primary emp_auth-update" data-toggle="modal" data-target="#emp-auth-input">修改權限</button>	
								      				</div>
								      			</div>
								      			</div>
								      		</div>
								      	</div>
							   		 </div>
								</div>
							</td>
						</tr>
						</c:if>
						</c:forEach>


						
					</tbody>
				</table>
				</div>
			</div>
				
			
		</div>

		 <div class="modal fade" id="emp-info-input" role="dialog">
		    <div class="modal-dialog">
		    
		      <!-- Modal content-->
		      <div class="modal-content">
		      <form class="form-horizontal" action="<%=request.getContextPath()%>/emp/emp.do" method="post" >
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">修改基本資料</h4>
		        </div>
		        <div class="modal-body">
		          

		          	<div class="form-group form-inline">
							<label  for="emp_name" class="col-sm-3 control-label">姓名 : </label>
						<div class="col-sm-9 text-left">
							<input type="text" name="emp_name" id="emp_name" class="form-control" placeholder="請輸入修改的姓名">
						</div>
					</div>
					<div class="form-group form-inline">
							<label  for="emp_id" class="col-sm-3 control-label">身分證字號 : </label>
						<div class="col-sm-9 text-left">
							<input type="text" name="emp_id" id="emp_id" class="form-control" placeholder="請輸入身分證字號">
						</div>
					</div>
					<div class="form-group form-inline">
							<label  for="emp_email" class="col-sm-3 control-label">E-mail : </label>
							<div class="col-sm-9 text-left">
							<input type="text" name="emp_email" id="emp_email" class="form-control" placeholder="請輸入E-mail">
							</div>
						</div>
					<div class="form-group form-inline">
							<label  for="emp_phone" class="col-sm-3 control-label">連絡電話 : </label>
							<div class="col-sm-9 text-left">
							<input type="text" name="emp_phone" id="emp_phone" class="form-control" placeholder="請輸入連絡電話">
							</div>
						</div>

					<div class="form-group form-inline">
							<label  for="emp_hiredate" class="col-sm-3 control-label"  >到職日 : </label>
							<div class="col-sm-9 text-left">
							<input type="text" name="emp_hiredate" id="emp_hiredate" class="form-control" placeholder="請輸入到職日" readonly>
							</div>
						</div>
						<div class="form-group form-inline">
							<label  for="emp_address" class="col-sm-3 control-label">住址 : </label>
							<div class="col-sm-9 text-left">
							<input type="text" name="emp_address" id="emp_address" class="form-control" placeholder="請輸入住址">
							</div>
						</div>
						<div class="form-group form-inline">
							<label  for="emp_job" class="col-sm-3 control-label">職稱 : </label>
							<div class="col-sm-9 text-left">
							<input type="text" name="emp_job" id="emp_job" class="form-control" placeholder="請輸入職稱">
							</div>
						</div>
						<div class="form-group form-inline">
							<label  for="emp_job" class="col-sm-3 control-label">員工狀態 : </label>
							<div class="col-sm-9 text-left">
							<select name="emp_status" id="emp_status" class="selectpicker show-tick">
								<option value="1">在職</option>
								<option value="0">離職</option>
							</select>
							</div>
						</div>
		          
		        </div>
		        <div class="modal-footer">
		          <input type="submit" class="btn btn-default" name="submitButton" value="提交">
		          <input type="hidden" name="action" value="update">
		          <input type="hidden" id="info_emp_no" name="emp_no" value="">
		          <input type="hidden" id="requestURL" name="requestURL" value="<%=request.getServletPath()%>">
		          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        </div>
		        </form>
		      </div>
		    </div>
		  </div>

		  <div class="modal fade" id="emp-auth-input" role="dialog">
		    <div class="modal-dialog">
		    
		      <!-- Modal content-->
		      <div class="modal-content">
		      <form class="form-horizontal" action="" method="get" >
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">修改權限</h4>
		        </div>
		        <div class="modal-body">
		          <form >
		          	
		          </form>
		        </div>
		        <div class="modal-footer">
		       	  <input type="submit" class="btn btn-default" name="submitButton" value="提交">
		          <input type="hidden" name="action" value="update">
		          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        </div>
		        </form>
		      </div>
		      
		    </div>
		  </div>

		<footer id="the_footer">
			<p class="lightcolor">All Content Copyright &copy; 2016 TomCat Inc</p>
		</footer>
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/bootstrap-select.min.js"></script>

		<script type="text/javascript">

			$(document).ready(function (){
					$('#emp_hiredate').datepicker({
				    todayBtn: "linked",
				    clearBtn: true,
				    
				    orientation: "bottom right"
				    
				});
				})
			

			function updateEmpInfo(e){
				// $("#emp-info-input").modal('show');
				var emp_no = e;
				$("#info_emp_no").val(emp_no);
				$.ajax({
						 type:"GET",
						 url:"/AA105G3/Emp/EmpJsonRes.do",
						 data:creatQueryString(emp_no),
						 dataType:"json",
						 success:function (data){
							 $("#emp_name").val(data.emp_name);
							 $("#emp_id").val(data.emp_id);
							 $("#emp_email").val(data.emp_email);
							 $("#emp_phone").val(data.emp_phone);
							 $("#emp_hiredate").val(data.emp_hiredate);
							 $("#emp_address").val(data.emp_address);
							 $("#emp_job").val(data.emp_job);
							 
							 $("#emp_status").val(data.emp_status).change();
							 $("#emp_status").val(data.emp_status);
							 $("#emp-info-input").modal('show');
							 
					     },
			             error:function(){alert(emp_no)}
			         })
			}
			function creatQueryString(emp_no){
				var queryString={"action":"getEmpInfo","emp_no":emp_no};
				return queryString;
			}
		</script>
	</body>
</html>