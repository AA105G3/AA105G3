<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Recipe_type_info: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>Recipe_type_info: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for Recipe_type_info: Home</p>

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
  <li><a href='listAllRecipe_type_info.jsp'>List</a> all Emps_auths. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/recipe_type_info/recipe_type_info.do" >
        <b>輸入食譜編號 (如R00000001):</b>
        <input type="text" name="recipe_no">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="recipeSvc" scope="page" class="com.recipe.model.RecipeService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/recipe_type_info/recipe_type_info.do" >
       <b>選擇食譜編號:</b>
       <select size="1" name="recipe_no">
         <c:forEach var="recipeVO" items="${recipeSvc.all}" > 
          <option value="${recipeVO.recipe_no}">${recipeVO.recipe_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/recipe_type_info/recipe_type_info.do" >
       <b>選擇食譜名稱:</b>
       <select size="1" name="recipe_no">
         <c:forEach var="recipeVO" items="${recipeSvc.all}" > 
          <option value="${recipeVO.recipe_no}">${recipeVO.recipe_name}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>



</body>

</html>
