<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM Chef_refund: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM Chef_refund: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM Chef_refund: Home</p>

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
  <li>所有退款<a href='listAllChef_refund.jsp'>清單</a></li> <br><br>
  
  <li>單項查詢</li>
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef_refund/chef_refund.do" >
        <b>輸入退款編號 (如CR0000001):</b>
        <input type="text" name="chef_rfd_no">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
<!--   <li> -->
<%--     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef_refund/chef_refund.do" > --%>
<!--         <b>輸入訂單編號 (如COL000001):</b> -->
<!--         <input type="text" name="chef_ord_no"> -->
<!--         <input type="submit" value="送出"> -->
<!--         <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--     </FORM> -->
<!--   </li> -->

  <jsp:useBean id="chef_refundSvc" scope="page" class="com.chef_refund.model.Chef_refundService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef_refund/chef_refund.do" >
       <b>選擇退款編號:</b>
       <select size="1" name="chef_rfd_no">
         <c:forEach var="chef_refundVO" items="${chef_refundSvc.all}" > 
          <option value="${chef_refundVO.chef_rfd_no}">${chef_refundVO.chef_rfd_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef_refund/chef_refund.do" >
       <b>選擇訂單編號:</b>
       <select size="1" name="chef_rfd_no">
         <c:forEach var="chef_refundVO" items="${chef_refundSvc.all}" > 
          <option value="${chef_refundVO.chef_rfd_no}">${chef_refundVO.chef_ord_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
<!--value填入servlet的doPost內的對應名稱(在action前) -->
       <input type="hidden" name="action" value="getOne_For_Update_back">
    </FORM>
  </li>
  
</ul>


<h3>退款管理</h3>

<ul>
  <li><a href='addChef_refund.jsp?chef_ord_no=COL000005'>Add</a> a new Chef_refund.</li>
</ul>

</body>

</html>
