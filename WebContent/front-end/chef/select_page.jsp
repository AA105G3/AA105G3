<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM Chef: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM Chef: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM Chef: Home</p>

<h3>資料查詢:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<ul>
  <li><a href='listAllChef.jsp'>List</a> all Chefs. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="chef.do" >
        <b>輸入私廚編號 (如C00000001):</b>
        <input type="text" name="chef_no">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="chefSvc" scope="page" class="com.chef.model.ChefService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef/chef.do" >
       <b>選擇私廚編號:</b>
       <select size="1" name="chef_no">
         <c:forEach var="chefVO" items="${chefSvc.all}" > 
          <option value="${chefVO.chef_no}">${chefVO.chef_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef/chef.do" >
       <b>選擇私廚姓名:</b>
       <select size="1" name="chef_no">
         <c:forEach var="chefVO" items="${chefSvc.all}" > 
          <option value="${chefVO.chef_no}">${chefVO.chef_name}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>


<h3>私廚管理</h3>

<ul>
  <li><a href='addChef.jsp'>Add</a> a new Chef.</li>
</ul>

</body>

</html>
