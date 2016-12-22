<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Chat select</title></head>
<body>

<table border='1' cellpadding='3' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h2>分享食光</h2><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>測試訊息紀錄 (Chat) 搜尋</p>

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
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chat/chat.do" >
        <b>輸入訊息編號 (如CTN1):</b>
        <input type="text" name="chat_no">
        <input type="submit" value="送出"><font color=blue>(資料格式驗證  by Controller ).</font> 
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="chatSvc" scope="page" class="com.chat.model.ChatService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chat/chat.do" >
       <b>選擇會員編號:</b>
       <select size="1" name="chat_no">
         <c:forEach var="chatVO" items="${chatSvc.all}" > 
          <option value="${chatVO.chat_no}">${chatVO.send_no}
         </c:forEach>
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
</ul>

<h3>訊息紀錄管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/front-end/chat/addChat.jsp'>Add</a> a new Chat.</li>
</ul>

</body>
</html>