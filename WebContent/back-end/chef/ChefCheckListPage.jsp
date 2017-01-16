<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chef.model.*"%>

<%
    ChefService chefSvc = new ChefService();
    List<ChefVO> All = chefSvc.getAll();
    List<ChefVO> list = new ArrayList<ChefVO>();
    for(ChefVO aChef:All){
    	if((aChef.getChef_chk_cond()).equals("0")){
    		list.add(aChef);
    	}
    }
    
    pageContext.setAttribute("list",list);
    
    
    
%>
<!DOCTYPE html>
<html lang="">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/AA105G3/css/BackChefPage_main.css">
    <script src=></script>
    <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
     <style>
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
    <nav>
    	<c:import url="/back-end/TopNavBar.jsp"></c:import>
    </nav>
    
    	<c:import url="/back-end/LeftNavBar.jsp"></c:import>
    
    <div class="col-xs-12 col-sm-10">
       <div class="container">
				<div class="row">
		
        
        <table class="table table-hover table-striped table-bordered table-condensed">
            <caption id="table_title">私廚資格審核清單</caption>
            <thead>
                <tr>
                    <th>私廚編號</th>
                    <th>會員編號</th>
                    <th>真實姓名</th>
                    <th>私廚審核情況</th>
                    <th>操作</th>
                </tr>
            </thead>
            <%@ include file="page1.file" %>
                <c:forEach var="chefVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                    <tbody>
                        <tr align='center' valign='middle'>
                            <td>${chefVO.chef_no}</td>
                            <td>${chefVO.mem_no}</td>
                            <td>${chefVO.chef_name}</td>
                            <td>
                            <c:if test="${chefVO.chef_chk_cond==0}">
                            	待審核
                            </c:if>
                            </td>
                            <td>                                
            					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef/chef.do">
			     					<input type="submit" class="btn btn-primary" value="進入審核">
			     					<input type="hidden" name="chef_no" value="${chefVO.chef_no}">
<%-- 			     				<input type="hidden" name="chef_chk_cond" value="${chefVO.chef_chk_cond}"> --%>
			     					<input type="hidden" name="action"	value="getOne_For_Check"></FORM>
<%--                            <div class="btn btn-danger btn-xs" href="<%=request.getContextPath()%>/back-end/chef/ChefCheckPage.jsp">進入審核</div> --%>        
                            </td>
                        </tr>
                    </tbody>
                </c:forEach>
        </table>
        <%@ include file="page2.file" %>
       </div> 
       </div>
    </div>

    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>

</html>
