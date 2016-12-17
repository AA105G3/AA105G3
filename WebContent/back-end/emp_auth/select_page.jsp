<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM Emp_auth: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>Emp_auth: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for Emp_auth: Home</p>

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
  <li><a href='listAllEmp_auth.jsp'>List</a> all Emps_auths. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp_auth/emp_auth.do" >
        <b>輸入員工編號 (如1001):</b>
        <input type="text" name="emp_no">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getAuths_byEmp_no">
    </FORM>
  </li>

  <jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp_auth/emp_auth.do" >
       <b>選擇員工編號:</b>
       <select size="1" name="emp_no">
         <c:forEach var="empVO" items="${empSvc.all}" > 
          <option value="${empVO.emp_no}">${empVO.emp_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getAuths_byEmp_no">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp_auth/emp_auth.do" >
       <b>選擇員工姓名:</b>
       <select size="1" name="emp_no">
         <c:forEach var="empVO" items="${empSvc.all}" > 
          <option value="${empVO.emp_no}">${empVO.emp_name}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getAuths_byEmp_no">
     </FORM>
  </li>
</ul>




</body>

</html>
