package com.recipe_type_info.controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpService;
import com.recipe.model.*;
import com.recipe_m_type.model.Recipe_m_typeService;
import com.recipe_m_type.model.Recipe_m_typeVO;
import com.recipe_s_type.model.Recipe_s_typeService;
import com.recipe_s_type.model.Recipe_s_typeVO;
import com.recipe_type_info.model.*;


@WebServlet("/recipe_type_info/recipe_type_info.do")

public class Recipe_type_infoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String recipe_no = req.getParameter("recipe_no");
				if (recipe_no == null || (recipe_no.trim()).length() == 0) {
					errorMsgs.add("請輸入食譜編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/recipe_type_info/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				/***************************2.開始查詢資料*****************************************/
				Recipe_type_infoService recipe_type_infoSvc = new Recipe_type_infoService();
				List one_recipe_type_info = recipe_type_infoSvc.getRecipe_type_infoByRecipe_no(recipe_no);
				if (one_recipe_type_info.isEmpty()) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/recipe_type_info/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", one_recipe_type_info); 
				String url = "/back-end/recipe_type_info/listOfType_byRecipe_no.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/recipe_type_info/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("findByTypeNo".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String recipe_type_no = req.getParameter("recipe_type_no");
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/recipe_type_info/categories.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				/***************************2.開始查詢資料*****************************************/
				String range = recipe_type_no.substring(1, 2);
				String typeName = "";
				if(range.equals("M")){
					Recipe_m_typeService recipe_m_typeService =new Recipe_m_typeService();
					Recipe_m_typeVO aMtype = recipe_m_typeService.getOneRecipe_m_type(recipe_type_no);
					typeName = aMtype.getM_type_name();
				}
				if(range.equals("S")){
					Recipe_s_typeService recipe_s_typeService =new Recipe_s_typeService();
					Recipe_s_typeVO aStype = recipe_s_typeService.getOneRecipe_s_type(recipe_type_no);
					typeName = aStype.getS_type_name();
				}
				
				
				Recipe_type_infoService recipe_type_infoSvc = new Recipe_type_infoService();
				Set<Recipe_type_infoVO> recipesByType = recipe_type_infoSvc.findByType_no(recipe_type_no);
				RecipeService recipeSvc = new RecipeService();
				List<RecipeVO> list = new ArrayList();
				for(Recipe_type_infoVO aTypeInfo:recipesByType){
					RecipeVO recipeVO = recipeSvc.getOneRecipe(aTypeInfo.getRecipe_no());
					
					if(recipeVO.getRecipe_intro()!=null && recipeVO.getRecipe_intro().length()>64){
						String introSbsr= recipeVO.getRecipe_intro().substring(0,65)+"...";
						recipeVO.setRecipe_intro(introSbsr);
					}
					
					String str = recipeVO.getFood_mater();
					String[] tokens = str.split("-|\\+");
					
					StringBuffer ingredients = new StringBuffer();
					
					ingredients.append(tokens[0]);
					for(int i =2;i<tokens.length-1;i+=2){
						ingredients.append("、"+tokens[i]);
					}
					
					String food_maters = null;
					if(ingredients.length()>38){
						food_maters = ingredients.substring(0,39)+"..."; 
						
					}else{
						food_maters = new String(ingredients);
					}
					
					recipeVO.setFood_mater(new String(food_maters));
					
					list.add(recipeVO);
				}
				
				if (recipesByType.isEmpty()) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); 
				req.setAttribute("typeName", typeName); 
				String url = "/front-end/recipe_type_info/categories.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/recipe_type_info/categories.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // 來自addAuth.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

				String recipe_no = req.getParameter("recipe_no").trim();
				String recipe_type_no = req.getParameter("recipe_type_no").trim();
				String type_range =req.getParameter("type_range").trim();
				
				Recipe_type_infoVO recipe_type_infoVO = new Recipe_type_infoVO();
				recipe_type_infoVO.setRecipe_no(recipe_no);
				recipe_type_infoVO.setRecipe_type_no(recipe_type_no);
				recipe_type_infoVO.setType_range(type_range);
				
				
						
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("recipe_type_infoVO", recipe_type_infoVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/recipe_type_info/addRecipe_type_info.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Recipe_type_infoService recipe_type_infoSvc = new Recipe_type_infoService();
				recipe_type_infoVO = recipe_type_infoSvc.addRecipe_type_info(recipe_no, recipe_type_no, type_range);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "recipe_type_info.do?action=getOne_For_Display";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllAuth.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/recipe_type_info/listAllRecipe_type_info.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String recipe_no = req.getParameter("recipe_no").trim();
				
				
				/***************************2.開始刪除資料***************************************/
				Recipe_type_infoService recipe_type_infoSvc = new Recipe_type_infoService();
				recipe_type_infoSvc.deleteRecipe_type_info(recipe_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/recipe_type_info/listAllRecipe_type_info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/recipe_type_info/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		
		if ("deleteOne".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String recipe_no = req.getParameter("recipe_no").trim();
				String recipe_type_no =req.getParameter("recipe_type_no").trim();
				
				
				/***************************2.開始刪除資料***************************************/
				Recipe_type_infoService recipe_type_infoSvc = new Recipe_type_infoService();
				recipe_type_infoSvc.deleteOneType_info(recipe_no, recipe_type_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "recipe_type_info.do?action=getOne_For_Display";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/recipe_type_info/listOfType_byRecipe_no.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
