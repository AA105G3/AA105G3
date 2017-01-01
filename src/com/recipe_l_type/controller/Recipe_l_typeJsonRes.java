package com.recipe_l_type.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.recipe_l_type.model.*;
import com.recipe_m_type.model.Recipe_m_typeVO;

import net.sf.json.JSONArray;



@WebServlet("/Recipe_l_type/Recipe_l_typeJsonRes.do")
public class Recipe_l_typeJsonRes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if("xxx".equals(request.getParameter("action"))){
			
			JSONArray array = new JSONArray();
			Recipe_l_typeService recipe_l_typeSvc = new Recipe_l_typeService();
			List<Recipe_l_typeVO> list = recipe_l_typeSvc.getAll(); 
			System.out.println("xxx");
			
			
				for(Recipe_l_typeVO aLType:list){
					JSONObject obj = new JSONObject();
					Set<Recipe_m_typeVO> mTypes = recipe_l_typeSvc.getM_typesByL_Type_No(aLType.getRecipe_l_type_no());
					StringBuffer str = new StringBuffer();
					for(Recipe_m_typeVO aMType : mTypes){
						str.append(aMType.getM_type_name()+"、");
						str.deleteCharAt(str.lastIndexOf("、"));
					}
					try
					{
						obj.put("recipe_l_type_no", aLType.getRecipe_l_type_no());
						obj.put("l_type_name", aLType.getL_type_name());
						obj.put("mTypes", str);
						array.add(obj);
						
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
