<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chef_refund.model.*"%>
<%
Chef_refundVO chef_refundVO = (Chef_refundVO) request.getAttribute("chef_refundVO");
%>



<html>
<head>
<title>退款資料新增 - addChef_refund.jsp</title></head>
<link rel="stylesheet" type="text/css" href="/AA105G3/css/calendar.css">
<script language="JavaScript" src="/AA105G3/js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>退款資料新增 - addChef_refund.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/back-end/chef_refund/select_page.jsp"><img src="/AA105G3/images/chef/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>退款資料:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chef_refund/chef_refund.do" name="form1">
<table border="0">

	<jsp:useBean id="chef_order_listSvc" scope="page" class="com.chef_order_list.model.Chef_order_listService" />
	<tr>
		<td>訂單編號:<font color=red><b>*</b></font></td>
		<td>
			${param.chef_ord_no}
		</td>
	</tr>
	<tr>
		<td>訂單執行狀態:<font color=red><b>*</b></font></td>
		<td>
			${chef_order_listSvc.getOneChef_order_list(param.chef_ord_no).chef_ord_con}
		</td>
	</tr>
	
<%-- 	<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>處理退款員工:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="emp_no"> -->
<%-- 			<c:forEach var="empVO" items="${empSvc.all}"> --%>
<%-- 				<option value="${empVO.emp_no}" ${(chef_refundVO.emp_no==empVO.emp_no)? 'selected':'' } >${empVO.emp_name} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

	<tr>
		<td>員工編號:</td>
		<td><input type="TEXT" name="emp_no" size="45"
			value="<%= (chef_refundVO==null)? "1001" : chef_refundVO.getEmp_no()%>" /></td>
	</tr>
	<tr>
		<td>退款百分比:</td>
		<td><input type="TEXT" name="chef_rfd_per" size="45"
			value="<%= (chef_refundVO==null)? "1" : chef_refundVO.getChef_rfd_per()%>" /></td>
	</tr>
	<tr>
		<td>會員匯款帳號銀行代碼:</td>
		<td><input type="TEXT" name="mem_bnk" size="45"
			value="<%= (chef_refundVO==null)? "700" : chef_refundVO.getMem_bnk()%>" /></td>
	</tr>
	<tr>
		<td>會員匯款帳號:</td>
		<td><input type="TEXT" name="mem_bnk_ac" size="45"
			value="<%= (chef_refundVO==null)? "10256381159730" : chef_refundVO.getMem_bnk_ac()%>" /></td>
	</tr>
	<tr>
		<td>退款審核狀態:</td>
		<td><input type="TEXT" name="chef_ord_chk_cond" size="45"
			value="<%= (chef_refundVO==null)? "2" : chef_refundVO.getChef_ord_chk_cond()%>" /></td>
	</tr>
	<tr>
		<td>退款內容:</td>
		<td><input type="TEXT" name="chef_ord_red_cnt" size="45"
			value="<%= (chef_refundVO==null)? "因颱風天取消" : chef_refundVO.getChef_ord_red_cnt()%>" /></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="hidden" name="chef_ord_no" value=${param.chef_ord_no}>

<input type="submit" value="送出新增"></FORM>
</body>

</html>
