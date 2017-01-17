<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_order.model.*"%>
<%@ page import="com.chef.model.*"%>
<%@ page import="com.product_order_list.model.*"%>
<%@ page import="java.util.*"%>

<%
	Product_orderService product_orderSvc = new Product_orderService();
	List<Product_orderVO> list = product_orderSvc.getAll();
	
	Product_order_listService product_order_listSvc = new Product_order_listService();
	
	List<Product_orderVO> displayList = new ArrayList<Product_orderVO>();
	
	boolean flag = false;
	
	for(Product_orderVO aOrder:list){
		
		List<Product_order_listVO> list2 =  product_order_listSvc.getProduct_order_list_By_One_PK(aOrder.getProd_ord_no());
		List<String> deliStatus = new ArrayList<String>();
		
		for(Product_order_listVO aOrderList:list2){
			String status = aOrderList.getDeli_status();
			deliStatus.add(status);
		}
		
		if(deliStatus.contains("0") || deliStatus.contains("4")){
			flag = true;
		}
		
		if(flag == true){
			displayList.add(aOrder);
		}
		
		flag = false;
		
	}
	ChefService chefSvc = new ChefService();
	List<ChefVO> list3 = chefSvc.getAll();
	List<ChefVO> unCheckChef = new ArrayList<ChefVO>();
	for(ChefVO aChef: list3){
		if((aChef.getChef_chk_cond()).equals("0")){
			unCheckChef.add(aChef);
		}
		
	}
	
	int unCompleteSize = displayList.size();
	int unCheckChefSize = unCheckChef.size();
%>



<jsp:useBean id="recipeSvc" scope="page" class="com.recipe.model.RecipeService" />


<html>
<head>
<title>後端首頁</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="/AA105G3/css/backpageCSS.css">

<style>

.btn-xlarge{
	position: relative;
	margin: 50px 30px;
	/*width: 100%;*/
	height: 100x;
	padding: 10px 10px;
	
	color: white;
	background: #62b1d0;
	border-radius: 15px;
	text-align: left;
}
.btn-xlarge i{
	font-size: 1000%;
}
.btn-xlarge span{
	font-size: 32px;
}
.btn-xlarge:hover{
	color: white;
}
.btn-xlarge td{
	color: white;
}
.span-style{
	display: inline;
	padding-left: 20px;
	font-size: 48px;
}
.text-style{
	font-size: 32px;
}
#table_title{
     		font-size:30px;
     	}
     	th{
     		text-align:center;
     		font-size:16px;
     	}
     	tbody td{
     		font-size:16px;
     		vertical-align: middle !important;
     	}

</style>

</head>

<body>

<c:import url="/back-end/TopNavBar.jsp"></c:import>



<c:import url="/back-end/LeftNavBar.jsp"></c:import>





<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12">





			<a href="<%=request.getContextPath()%>/back-end/recipe_type_info/RecipeNotClassified.jsp">
				<div class="col-xs-12 col-sm-3 btn btn-xlarge" >
					<table>
						<tr>
							<td>
								<i class="glyphicon glyphicon-book" ></i>
							</td>
							
							<td>
								<p class="span-style">${recipeSvc.getNotClassified().size()}</p>
							</td>
						</tr>
					</table>
					<div class="text-style">待處理未分類食譜</div>
				</div>
			</a>
			
			<a href="<%=request.getContextPath()%>/product_order/product_order.do?action=getPartForDisplay">
				<div class="col-xs-12 col-sm-3 btn btn-xlarge" >
					<table>
						<tr>
							<td>
								<i class="glyphicon glyphicon-list-alt" ></i>
							</td>
							
							<td>
								<p class="span-style"><%= unCompleteSize %></p>
							</td>
						</tr>
					</table>
					<div class="text-style">待處理未完成訂單</div>
				</div>
			</a>
			
			<a href="<%=request.getContextPath()%>/back-end/chef/ChefCheckListPage.jsp">
				<div class="col-xs-12 col-sm-3 btn btn-xlarge" >
					<table>
						<tr>
							<td>
								<i class="glyphicon glyphicon-user" ></i>
							</td>
							
							<td>
								<p class="span-style"><%= unCheckChefSize %></p>
							</td>
						</tr>
					</table>
					<div class="text-style">待處理私廚審核</div>
				</div>
			</a>




		</div>
	</div>
</div>





<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>