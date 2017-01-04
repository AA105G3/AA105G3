<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM Chef_order_list: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM Chef_order_list: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM Chef_order_list: Home</p>

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
  <li><a href='listAllChef_order_list.jsp'>List</a> all Chef_order_lists. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef_order_list/chef_order_list.do" >
        <b>輸入私廚訂單編號 (如COL000001):</b>
        <input type="text" name="chef_ord_no">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="chef_order_listSvc" scope="page" class="com.chef_order_list.model.Chef_order_listService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef_order_list/chef_order_list.do" >
       <b>選擇私廚訂單編號:</b>
       <select size="1" name="chef_ord_no">
         <c:forEach var="chef_order_listVO" items="${chef_order_listSvc.all}" > 
          <option value="${chef_order_listVO.chef_ord_no}">${chef_order_listVO.chef_ord_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
</ul>


<h3>私廚訂單管理</h3>

<ul>
  <li><a href='addChef_order_list.jsp'>Add</a> a new Chef_order_list.</li>
</ul>

</body>

</html>
