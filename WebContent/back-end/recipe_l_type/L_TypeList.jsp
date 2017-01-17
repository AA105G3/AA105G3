<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.recipe_l_type.model.*"%>

<jsp:useBean id="recipe_l_typeSvc" scope="page" class="com.recipe_l_type.model.Recipe_l_typeService" />
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>食譜類別管理</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
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
			;
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
		
		thead{
			background: #e2fede;
		}
		/*tabs panel*/
		.type-title{
			margin-bottom: 20px;
		}
		.head-wrapper{
			padding: 0px;
		}
		
		.aType-Wrapper{
			padding: 0px;
		}
		
		.addType{
			margin:30px 3px 5px 0px;
			font-size: 15px; 
			padding: 5px 25px;
		}
		
		.type_no_head{
			width: 150px;
			font-size: 17px;
		}
		.type_name_head{
			width: 160px;
			text-align: center;
			font-size: 17px;
		}
		.attachment_head{
			width: 62%;
			font-size: 17px;
		}
		.type_no,.type_name,.attachment,.edit_head{
			font-size: 17px;
		}
		.type_name,.edit_head,.edit{
			text-align: center;
		}
		.glyphicon-pencil.btn ,.glyphicon-trash.btn{
			padding: 0px 5px;
			font-size: 17px;
			border:1px solid;
			border-radius: 20px;
			font-weight: bold;
		}
		.glyphicon-pencil.btn:hover,.glyphicon-trash.btn:hover{
			color: red;
		}
		
		
		#type-info-input{
			margin-top: 70px;
		}
		#type-info-input .modal-header{
			text-align: center;
		}
		#type-info-input label,#type-info-input input[type="text"]{
			font-size: 17px;
		}
		
		#deleteAttachment{
			height: 34px;
			font-size: 16px;
		}
		#deleteAttachment option{
			font-size: 16px;
		}
		#addLType{
			margin-top: 50px;
		}


		</style>
	</head>

	<body>
		 <c:import url="/back-end/TopNavBar.jsp" ></c:import>
		 <c:import url="/back-end/LeftNavBar.jsp" ></c:import>

			
			<div class="col-xs-12 col-sm-10 col-md-10"">
			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12">
						<div class="col-xs-12 col-sm-12 head-wrapper">
							<div class="col-xs-12 col-sm-6">
								<h2 class="type-title">食譜類別管理-第一層類別</h2>
							</div>
							 <div class="col-xs-12 col-sm-6 text-right aType-Wrapper">
							 	<a href="<%=request.getContextPath()%>/back-end/recipe_m_type/M_TypeList.jsp" class="btn btn-default addType" >第二層類別管理</a>
	                    		<button class="btn btn-primary addType" data-toggle="modal" data-target="#addLType">新增類別</button>
	                    	</div>
						</div>
			                 
	                
	                	<table class="table table-striped table-bordered">
	                    	<thead class="thead-inverse">
	                    		<tr>
	                    			<th class="type_no_head">第一層類別編號</th>
	                    			<th class="type_name_head">類別名稱</th>
	                    			<th class="attachment_head">附屬類別(次類別)</th>
	                    			<th class="edit_head">編輯</th>
	                    		</tr>
	                    	</thead>
	                		<tbody>
	                		<c:forEach var="recipe_l_typeVO" items="${recipe_l_typeSvc.all}" >
	                			<tr>
	                				<td class="type_no">${recipe_l_typeVO.recipe_l_type_no }</td>
	                				<td class="type_name">${recipe_l_typeVO.l_type_name }</td>
	                				<td class="attachment">
	                				<c:forEach var="recipe_m_typeVO" items="${recipe_l_typeSvc.getM_typesByL_Type_No(recipe_l_typeVO.recipe_l_type_no)}" varStatus="s">
	                					<%-- ${recipe_l_typeSvc.getM_typesByL_Type_No(recipe_l_typeVO.recipe_l_type_no).size() !=s.count ? recipe_m_typeVO.m_type_name+"、" : recipe_m_typeVO.m_type_name} --%>
	                					 ${recipe_m_typeVO.m_type_name}
	                					 <c:if test="${recipe_l_typeSvc.getM_typesByL_Type_No(recipe_l_typeVO.recipe_l_type_no).size() != s.count}">
	                					 	、
	                					 </c:if>
	                				</c:forEach>
	                				</td>
	                				<td class="edit">
	                				<i class="glyphicon glyphicon-pencil btn"  onclick="getLTypeInfo('${recipe_l_typeVO.recipe_l_type_no }')"></i>
	                				<!-- <i class="glyphicon glyphicon-trash btn" onclick="deleteLType('RL0001')"></i> -->
	                				</td>
	                			</tr>
	                		</c:forEach>
	                		</tbody>
	                	</table>
	                </div>
			    </div>
		  </div>
	 </div>
      			
				

				
			
		</div>

		<div class="modal fade" id="addLType" role="dialog">
		    <div class="modal-dialog">
		    
		      <!-- Modal content-->
		      <div class="modal-content">
		      <form class="form-horizontal" action="/AA105G3/recipe_l_type/recipe_l_type.do" method="post" >
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h3 class="modal-title">新增標題類別</h3>
		        </div>
		        <div class="modal-body">

		          	<div class="form-group form-inline">
							<label  for="l_type_name" class="col-sm-4 control-label">類別名稱 : </label>
						<div class="col-sm-8 text-left">
							<input type="text" name="l_type_name" id="newl_type_name" class="form-control" placeholder="請輸入新增的類別">
						</div>
					</div>
		        </div>
		        <div class="modal-footer">
		          <input type="submit" class="btn btn-default" name="submitButton" value="提交">
		          <input type="hidden" name="action" value="insert">
		          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        </div>
		        </form>
		      </div>
		    </div>
		  </div>


		 <div class="modal fade" id="type-info-input" role="dialog">
		    <div class="modal-dialog">
		    
		      <!-- Modal content-->
		      <div class="modal-content">
		      <form class="form-horizontal" action="/AA105G3/recipe_l_type/recipe_l_type.do" method="post" >
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h3 class="modal-title">修改類別資訊</h3>
		        </div>
		        <div class="modal-body">

		          	<div class="form-group form-inline">
							<label  for="l_type_name" class="col-sm-4 control-label">類別名稱 : </label>
						<div class="col-sm-8 text-left">
							<input type="text" name="l_type_name" id="l_type_name" class="form-control" placeholder="請輸入新增的類別">
						</div>
					</div>

		          	<div class="form-group form-inline">
							<label  for="addAttachment" class="col-sm-4 control-label">新增的附屬類別 : </label>
						<div class="col-sm-8 text-left">
							<input type="text" name="addAttachment" id="addAttachment" class="form-control" placeholder="請輸入新增的類別">
						</div>
					</div>
					<!-- <div class="form-group form-inline">
							<label  for="deleteAttachment" class="col-sm-4 control-label">刪除的附屬類別 : </label>
							<div class="col-sm-8 text-left">
							<select name="deleteAttachment" id="deleteAttachment" class="">
								<option selected>請選擇</option>
								
							</select>
							</div>
						</div> -->
		          
		        </div>
		        <div class="modal-footer">
		          <input type="submit" class="btn btn-default" name="submitButton" value="提交">
		          <input type="hidden" name="action" value="update">
		          <input type="hidden" name="recipe_l_type_no" id="recipe_l_type_no" value="">
		          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        </div>
		        </form>
		      </div>
		    </div>
		  </div>



		
	

		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.1/js/bootstrap-select.min.js"></script>

		<script type="text/javascript">
		

			function getLTypeInfo(e){
				// $("#emp-info-input").modal('show');
				var recipe_l_type_no = e;
				$.ajax({
						 type:"GET",
						 url:"/AA105G3/recipe_l_type/recipe_l_typeJsonRes.do",
						 data:{"action":"getTypeInfo","recipe_l_type_no":recipe_l_type_no},
						 dataType:"json",
						 success:function (data){
							 $("#l_type_name").val(data[0].l_type_name);
							// selectCreate(data,'deleteAttachment');
							$("#type-info-input").modal('show');
							$("#recipe_l_type_no").val(recipe_l_type_no);
					     },
			             error:function(){alert('not found')}
			         })
			}

			function selectCreate(oJson,targetId){
				$("#deleteAttachment").empty();
				var i=0;
				$("#deleteAttachment").append("<option value=''>請選擇</option>");
				$.each(oJson,function(){
					if(i>0){
						$("#deleteAttachment").append($("<option></option>").attr("value", oJson[i].recipe_m_type_no).text(oJson[i].m_type_name));	
						
						// .append("<option value='"+oJson[i].recipe_m_type_no+"'>"+oJson[i].m_type_name+"</option>");
					}
					i++;
				});
				
				
			}
			
		</script>
	</body>
</html>