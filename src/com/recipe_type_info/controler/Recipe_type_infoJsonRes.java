package com.recipe_type_info.controler;

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

import com.recipe_m_type.model.*;
import com.recipe_s_type.model.*;
import com.recipe_type_info.model.*;


@WebServlet("/recipe_type_info/recipe_type_infoJsonRes.do")
public class Recipe_type_infoJsonRes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");
			
			
			if("insert".equals(action)){
				String recipe_no = request.getParameter("recipe_no");
				String recipe_l_type_no = request.getParameter("recipe_l_type_no");
				String recipe_m_type_no = request.getParameter("recipe_m_type_no");
				String recipe_s_type_no = request.getParameter("recipe_s_type_no");
				
				
				
				Recipe_type_infoService recipe_type_infoSvc = new Recipe_type_infoService();
				
				
				JSONObject obj;
				
					obj = new JSONObject();
					if(recipe_m_type_no==""){
						try
						{
							obj.put("ErrorMsg", "次標題不可以是'無'");
						} catch (JSONException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					String typeName="";
					Recipe_type_infoVO recipe_type_infoVO = null;
					List<Recipe_type_infoVO> list = recipe_type_infoSvc.getRecipe_type_infoByRecipe_no(recipe_no);
					
					if(recipe_s_type_no=="" && recipe_m_type_no !=""){
						for(Recipe_type_infoVO aType:list){
							if((aType.getRecipe_type_no()).equals(recipe_m_type_no)){
								System.out.println(aType.getRecipe_type_no());
								try
								{
									obj.put("ErrorMsg", "該類別已重複");
								} catch (JSONException e)
								{
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						
						if(obj.length()<1){
							
							recipe_type_infoVO = recipe_type_infoSvc.addRecipe_type_info(recipe_no, recipe_m_type_no,"1");
							
							Recipe_m_typeService recipe_m_typeSvc = new Recipe_m_typeService();
							typeName = recipe_m_typeSvc.getOneRecipe_m_type(recipe_m_type_no).getM_type_name();
							System.out.println(recipe_type_infoVO.getRecipe_type_no());
							
								try
								{
									obj.put("typeName",typeName);
									obj.put("recipe_type_no", recipe_m_type_no);
									
									
								} catch (JSONException je)
								{
									// TODO Auto-generated catch block
									je.printStackTrace();
								}
							
						}
						
					}
					
					if(recipe_s_type_no!=""){
						
						for(Recipe_type_infoVO aType:list){
							System.out.println(aType.getRecipe_type_no());
							if((aType.getRecipe_type_no()).equals(recipe_s_type_no)){
								System.out.println(aType.getRecipe_type_no());
								try
								{
									obj.put("ErrorMsg", "該類別已重複");
								} catch (JSONException e)
								{
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						
						recipe_type_infoVO = recipe_type_infoSvc.addRecipe_type_info(recipe_no, recipe_s_type_no,"2");
						Recipe_s_typeService recipe_s_typeSvc = new Recipe_s_typeService();
						typeName = recipe_s_typeSvc.getOneRecipe_s_type(recipe_s_type_no).getS_type_name();
						if(obj.length()<1){
							try
							{
								obj.put("typeName",typeName);
								obj.put("recipe_type_no", recipe_s_type_no);
								
							} catch (JSONException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					
					response.setContentType("text/plain");
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
					out.write(obj.toString());
					out.flush();
					out.close();
				 
				
			}
			
			
			if("delete".equals(action)){
				String recipe_no = request.getParameter("recipe_no");
				String recipe_type_no = request.getParameter("recipe_type_no");
				
				Recipe_type_infoService recipe_type_infoSvc = new Recipe_type_infoService();
				
				String typeName;
				
				String range = recipe_type_no.substring(1, 2);
				if(range.equals("M")){
					Recipe_m_typeService recipe_m_typeService =new Recipe_m_typeService();
					Recipe_m_typeVO aMtype = recipe_m_typeService.getOneRecipe_m_type(recipe_type_no);
					typeName = aMtype.getM_type_name();
				}else{
					Recipe_s_typeService recipe_s_typeService =new Recipe_s_typeService();
					Recipe_s_typeVO aStype = recipe_s_typeService.getOneRecipe_s_type(recipe_type_no);
					typeName = aStype.getS_type_name();
				}
				
				recipe_type_infoSvc.deleteOneType_info(recipe_no, recipe_type_no);;
				
				
					JSONObject obj = new JSONObject();
					try
					{
						obj.put("typeName",typeName);
						
						
					} catch (JSONException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					
					
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.write(obj.toString());
				out.flush();
				out.close();
			}
	}

}
