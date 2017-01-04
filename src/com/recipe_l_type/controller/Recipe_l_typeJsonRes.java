package com.recipe_l_type.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.recipe_l_type.model.*;
import com.recipe_m_type.model.Recipe_m_typeVO;

import org.json.JSONArray;



@WebServlet("/recipe_l_type/recipe_l_typeJsonRes.do")
public class Recipe_l_typeJsonRes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if("getTypeInfo".equals(request.getParameter("action"))){
			String recipe_l_type_no = request.getParameter("recipe_l_type_no");
			Recipe_l_typeService recipe_l_typeSvc = new Recipe_l_typeService();
			
			Recipe_l_typeVO recipe_l_typeVO = recipe_l_typeSvc.getOneRecipe_l_type(recipe_l_type_no);
			Set<Recipe_m_typeVO> mTypes = recipe_l_typeSvc.getM_typesByL_Type_No(recipe_l_type_no); 
			JSONArray array = new JSONArray();
			
			
				JSONObject obj = new JSONObject();
				try
				{
					obj.put("l_type_name",recipe_l_typeVO.getL_type_name());
					obj.put("recipe_l_type_no",recipe_l_typeVO.getRecipe_l_type_no());
					array.put(obj);
					
				} catch (JSONException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(Recipe_m_typeVO recipe_m_typeVO:mTypes){
					JSONObject obj2 = new JSONObject();
					try
					{
						obj2.put("recipe_m_type_no",recipe_m_typeVO.getRecipe_m_type_no());
						obj2.put("m_type_name",recipe_m_typeVO.getM_type_name());
						array.put(obj2);
					} catch (JSONException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();
		}
		
		
		
		if("getAll".equals(request.getParameter("action"))){
			
			JSONArray array = new JSONArray();
			Recipe_l_typeService recipe_l_typeSvc = new Recipe_l_typeService();
			List<Recipe_l_typeVO> list = recipe_l_typeSvc.getAll(); 
			
			
				for(Recipe_l_typeVO aLType:list){
					JSONObject obj = new JSONObject();
					Set<Recipe_m_typeVO> mTypes = recipe_l_typeSvc.getM_typesByL_Type_No(aLType.getRecipe_l_type_no());
					StringBuffer str = new StringBuffer();
					for(Recipe_m_typeVO aMType : mTypes){
						str.append(aMType.getM_type_name()+"、");
					}
					if(str.length()>1){
						str.deleteCharAt(str.length()-1);
					}
					
					try
					{
						obj.put("recipe_l_type_no", aLType.getRecipe_l_type_no());
						obj.put("l_type_name", aLType.getL_type_name());
						obj.put("mTypes", str);
						array.put(obj);
						
					} catch (JSONException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();
		}
		
		
	}

}
