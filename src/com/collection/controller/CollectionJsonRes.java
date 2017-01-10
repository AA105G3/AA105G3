package com.collection.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.recipe.model.RecipeService;
import com.chef.model.ChefService;
import com.collection.model.*;

/**
 * Servlet implementation class CollectionJsonRes
 */
@WebServlet("/collection/collectionJsonRes.do")
public class CollectionJsonRes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String class_no;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if("addCollection".equals(action)){
			
			HttpSession session = request.getSession();
			String mem_no = (String)session.getAttribute("mem_no");
//			String mem_no = "M00000005";
			String all_no = request.getParameter("all_no");
			CollectionService collectionSvc = new CollectionService();
			
			String class_no = all_no.substring(0,1);
			
			
			
			CollectionVO collectionVO = collectionSvc.addCollection(mem_no, all_no, class_no);
			String msg = "";
			if(class_no.equals("M")){
				MemberService memberSvc = new MemberService();
				msg = "作者 "+memberSvc.getOneMember(all_no).getMem_name()+" 已被加入追隨!";
			}
			if(class_no.equals("R")){
				RecipeService recipeSvc = new RecipeService();
				msg = recipeSvc.getOneRecipe(all_no).getRecipe_name()+" 已被加入收藏!";
			}
			if(class_no.equals("C")){
				ChefService chefSvc = new ChefService();
				msg = "廚師 "+ chefSvc.getOneChef(all_no).getChef_name()+" 已被加入追隨!";
			}

			JSONObject obj = new JSONObject();
			
			try
			{
				obj.put("msg", msg);
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
		
		if("delete".equals(action)){
			
			HttpSession session = request.getSession();
			String mem_no = (String)session.getAttribute("mem_no");
			String all_no = request.getParameter("all_no");
			CollectionService collectionSvc = new CollectionService();

			String class_no = all_no.substring(0,1);
			
			
			
			
			if(class_no.equals("M")){
				List<CollectionVO> list = collectionSvc.getMyAuthorCollection(mem_no);
				for(CollectionVO collectionVO:list){
					if(collectionVO.getAll_no().equals(all_no)){
						collectionSvc.deleteCollection(collectionVO.getColl_no());
					}
				}
			}
			if(class_no.equals("R")){
				List<CollectionVO> list = collectionSvc.getMyRecipeCollection(mem_no);
				for(CollectionVO collectionVO:list){
					if(collectionVO.getAll_no().equals(all_no)){
						collectionSvc.deleteCollection(collectionVO.getColl_no());
					}
				}
			}
			if(class_no.equals("C")){
				List<CollectionVO> list = collectionSvc.getMyChefCollection(mem_no);
				for(CollectionVO collectionVO:list){
					if(collectionVO.getAll_no().equals(all_no)){
						collectionSvc.deleteCollection(collectionVO.getColl_no());
					}
				}
			}
			
			
			
			JSONObject obj = new JSONObject();
			
			try
			{
				obj.put("msg", "OK");
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
