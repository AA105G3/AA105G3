package com.recipe_m_type.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.recipe_l_type.model.Recipe_l_typeService;
import com.recipe_l_type.model.Recipe_l_typeVO;
import com.recipe_m_type.model.*;
import com.recipe_m_type.model.Recipe_m_typeVO;
import com.recipe_s_type.model.Recipe_s_typeVO;

/**
 * Servlet implementation class Recipe_m_typeJsonRes
 */
@WebServlet("/recipe_m_type/recipe_m_typeJsonRes.do")
public class Recipe_m_typeJsonRes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if("getTypeInfo".equals(request.getParameter("action"))){
			
			String recipe_m_type_no = request.getParameter("recipe_m_type_no");
			Recipe_m_typeService recipe_m_typeSvc = new Recipe_m_typeService();
			
			Recipe_m_typeVO recipe_m_typeVO = recipe_m_typeSvc.getOneRecipe_m_type(recipe_m_type_no);
			Set<Recipe_s_typeVO> sTypes =  recipe_m_typeSvc.getS_typesByM_Type_No(recipe_m_type_no);
			
			JSONArray sTypeWrapper = new JSONArray();
			JSONArray lTypeWrapper = new JSONArray();
			JSONArray wrapper = new JSONArray();
			Recipe_l_typeService recipe_l_typeSvc = new Recipe_l_typeService();
			List<Recipe_l_typeVO> lTypes = recipe_l_typeSvc.getAll();
			
				JSONObject obj = new JSONObject();
				try
				{
					obj.put("m_type_name",recipe_m_typeVO.getM_type_name());
					obj.put("recipe_m_type_no",recipe_m_typeVO.getRecipe_m_type_no());
					obj.put("parent_type",recipe_m_typeVO.getParent_type());
					wrapper.put(obj);
					
				} catch (JSONException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(Recipe_l_typeVO recipe_l_typeVO:lTypes){
					JSONObject obj2 = new JSONObject();
					try
					{
						obj2.put("recipe_l_type_no",recipe_l_typeVO.getRecipe_l_type_no());
						obj2.put("l_type_name",recipe_l_typeVO.getL_type_name());
						lTypeWrapper.put(obj2);
					} catch (JSONException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for(Recipe_s_typeVO recipe_s_typeVO:sTypes){
					JSONObject obj3 = new JSONObject();
					try
					{
						obj3.put("recipe_s_type_no",recipe_s_typeVO.getRecipe_s_type_no());
						obj3.put("s_type_name",recipe_s_typeVO.getS_type_name());
						sTypeWrapper.put(obj3);
					} catch (JSONException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				wrapper.put(lTypeWrapper);
				wrapper.put(sTypeWrapper);
				
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(wrapper.toString());
			out.flush();
			out.close();
		}
	}

}
