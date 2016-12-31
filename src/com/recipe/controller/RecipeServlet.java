﻿package com.recipe.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.recipe.model.*;
import com.recipe_cont.model.*;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class RecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
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
							.getRequestDispatcher("/front-end/recipe/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				RecipeService recipeSvc = new RecipeService();
				RecipeVO recipeVO = recipeSvc.getOneRecipe(recipe_no);
				
				if (recipeVO == null) {
					errorMsgs.add("食譜查無資料");
				}
				
				Recipe_contService recipe_contSvc = new Recipe_contService();
				Set<Recipe_contVO> set = recipe_contSvc.getRecipe_cont(recipe_no);
				if (set == null) {
					errorMsgs.add("食譜內容步驟查無資料");
				}
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/recipe/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				recipeSvc.updateRecipeViews(recipe_no, recipeVO.getRecipe_total_views()+1, recipeVO.getRecipe_week_views()+1);
				recipeVO = recipeSvc.getOneRecipe(recipe_no);
				
				
				
				String str = recipeVO.getFood_mater();
				String[] tokens = str.split("-|\\+");
				
				List<String> ingredients = new ArrayList<String>();
				List<String> quantity = new ArrayList<String>();
				
				for(int i =0;i<tokens.length-1;i+=2){
					ingredients.add(tokens[i]);
				}
				for(int i =1;i<tokens.length;i+=2){
					quantity.add(tokens[i]);
				}
				
				
				
				
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("recipeVO", recipeVO); 
				req.setAttribute("ingredients", ingredients);
				req.setAttribute("quantity", quantity);
				req.setAttribute("recipe_cont_set", set); 
				String url = "/front-end/recipe/RecipeInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/recipe/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String recipe_no = req.getParameter("recipe_no");
				
				/***************************2.開始查詢資料****************************************/
				RecipeService recipeSvc = new RecipeService();
				RecipeVO recipeVO = recipeSvc.getOneRecipe(recipe_no);
				
				String str = recipeVO.getFood_mater();
				String[] tokens = str.split("-|\\+");
				
				List<String> ingredients = new ArrayList<String>();
				List<String> quantity = new ArrayList<String>();
				
				for(int i =0;i<tokens.length-1;i+=2){
					ingredients.add(tokens[i]);
				}
				for(int i =1;i<tokens.length;i+=2){
					quantity.add(tokens[i]);
				}
				
				Recipe_contService recipe_contSvc = new Recipe_contService();
				Set<Recipe_contVO> set = recipe_contSvc.getRecipe_cont(recipe_no);
				
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("recipeVO", recipeVO);    
				req.setAttribute("ingredients", ingredients);
				req.setAttribute("quantity", quantity);
				req.setAttribute("recipe_cont_set", set);
				String url = "/front-end/recipe/update_recipe_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/recipe/listAllRecipe.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("search".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String searchCondition = req.getParameter("searchCondition");
				String searchInput =req.getParameter("searchInput");
				
				
				if(searchInput.length()==0 || searchInput.isEmpty()){
					errorMsgs.add("搜尋條件請勿空白");
				}
				
				if (!errorMsgs.isEmpty()) {
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/recipe/RecipeHome.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始查詢資料****************************************/
				RecipeService recipeSvc = new RecipeService();
				
				List<RecipeVO> list =null;
				if(searchCondition.equals("recipe_name")){
					list = recipeSvc.serachByRecipe_name(searchInput);
				}
				
				if(searchCondition.equals("food_mater")){
					list = recipeSvc.serachByFood_Mater(searchInput);
				}
				
				
				
				
				
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("list", list);
				req.setAttribute("title", searchInput);
				String url = "/front-end/recipe/RecipeSearch.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/recipe/listAllRecipe.jsp");
				failureView.forward(req, res);
			}
		}
		
			
			
		
		
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String recipe_no = req.getParameter("recipe_no").trim();
				String recipe_name= req.getParameter("recipe_name").trim();
				String recipe_intro= req.getParameter("recipe_intro");

				String[] ingredientsStr =req.getParameterValues("ingredients");
				String[] quantityStr =req.getParameterValues("quantity");
				
				
				
				
				RecipeService recipeSvc = new RecipeService();
			
				Part part = req.getPart("recipe_pic");
				byte[] recipe_pic = null;
				
				if (getFileNameFromPart(part) != null && part.getContentType() != null) {	
					InputStream in = part.getInputStream();
					recipe_pic = new byte[in.available()];
					in.read(recipe_pic);
					in.close();
				} else {
					recipe_pic = recipeSvc.getOneRecipe(recipe_no).getRecipe_pic();
				}	
				
				if(recipe_name==null || recipe_name.isEmpty()){
					errorMsgs.add("食譜名稱不能為空白");
				}
				if(recipe_intro==null || recipe_intro.isEmpty()){
					errorMsgs.add("食譜簡介不能為空白");
				}
				for(int i =0;i<ingredientsStr.length;i++){
					if(ingredientsStr[i].isEmpty() ){
						errorMsgs.add("食材不能為空白");
						break;
					}
				}
				for(int i =0;i<ingredientsStr.length;i++){
					if(quantityStr[i].isEmpty()){
						errorMsgs.add("食材數量不能為空白");
						break;
					}
				}
				
				
				
				
				StringBuffer str = new StringBuffer();
				List ingredients = new ArrayList();
				List quantity = new ArrayList();
				
				try
				{
					str.append(ingredientsStr[0]+"-"+quantityStr[0]);
					ingredients.add(ingredientsStr[0]);
					quantity.add(quantityStr[0]);
					for(int i =1;i<ingredientsStr.length;i++){
						str.append("+"+ingredientsStr[i]+"-"+quantityStr[i]); 
						ingredients.add(ingredientsStr[i]);
						quantity.add(quantityStr[i]);
					}
				} catch (IndexOutOfBoundsException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String food_mater = new String(str);
				
				RecipeVO recipeVO = new RecipeVO();
				recipeVO.setRecipe_no(recipe_no);	
				recipeVO.setRecipe_name(recipe_name);
				recipeVO.setRecipe_intro(recipe_intro);
				recipeVO.setFood_mater(food_mater);
				recipeVO.setRecipe_pic(recipe_pic);
				
				
				String[] step = req.getParameterValues("step");
				String[] step_cont = req.getParameterValues("step_cont");
				
				List<byte[]> step_pics = new ArrayList<byte[]>();
				
				Collection<Part> parts =  req.getParts();

				
				int picIdx = 0; //用getParts所取得的圖片包含食譜的圖片，依照順序上傳,第一張便是食譜圖片,必須剔除掉。
				
				/*判斷上傳的檔案是否是空的，就算是空的一樣要加入list中，原因在為了存入資料庫的步驟圖片是正確的，DAO部分圖片上傳的地方也要做
				  空值判斷，否則會出現NullPointer*/
				for (Part recipe_contPic : parts) {
					if(picIdx>4){
						if (getFileNameFromPart(recipe_contPic) != null && recipe_contPic.getContentType() != null){
							InputStream in = recipe_contPic.getInputStream();
							byte[] step_pic = new byte[in.available()];
							in.read(step_pic);
							in.close();
							step_pics.add(step_pic);
							
						}else if(getFileNameFromPart(recipe_contPic) == null){
							InputStream in = part.getInputStream();
							byte[] step_pic = null;
							step_pics.add(step_pic);
						}
					}
					picIdx++;
				} 
				
				
				List<String> step_conts = new ArrayList<String>();
				
				for(int i =0;i<step_cont.length;i++){
					step_conts.add(step_cont[i]);
				}
				
				Set<Recipe_contVO> recipe_cont_set = new LinkedHashSet<Recipe_contVO>();
				
				for(int i =0;i<step.length;i++){
					Recipe_contVO recipe_contVO = new Recipe_contVO();
					recipe_contVO.setRecipe_no(recipe_no);
					recipe_contVO.setStep(new Integer(step[i]));
					recipe_contVO.setStep_pic(step_pics.get(i));
					recipe_contVO.setStep_cont(step_conts.get(i));
					recipe_cont_set.add(recipe_contVO);
				}
				
				
				
				
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("recipeVO", recipeVO);
					req.setAttribute("ingredients", ingredients); 
					req.setAttribute("quantity", quantity); 
					req.setAttribute("recipe_cont_set", recipe_cont_set); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/recipe/update_recipe_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/

				
				recipeVO = recipeSvc.updateRecipe(recipe_no, recipe_name, recipe_intro, food_mater,recipe_pic);

//				String edit = req.getParameter("edit");
				
//				if(edit.equals("儲存")){
//					recipeVO = recipeSvc.updateRecipe(recipe_no, recipe_name, recipe_intro, food_mater,recipe_pic,"編輯中");
//					
//				}
				
//				if(edit.equals("送出修改")){
				recipeVO = recipeSvc.updateRecipe(recipe_no, recipe_name, recipe_intro, food_mater,recipe_pic,"已發布");
//				}
					
				Recipe_contService recipe_contSvc = new Recipe_contService();
				
//				Set<Recipe_contVO> db_recipe_cont_set =recipe_contSvc.getRecipe_cont(recipe_no);	
				Recipe_contVO recipe_contVO = new Recipe_contVO();
				
				recipe_contSvc.deleteRecipe_cont(recipe_no);
				for(int i =0 ; i<recipe_cont_set.size();i++){
					recipe_contVO=recipe_contSvc.addRecipe_cont(recipe_no, new Integer(step[i]),
						step_pics.get(i), step_conts.get(i));
				}
					
				recipe_cont_set.clear();
				recipe_cont_set = recipe_contSvc.getRecipe_cont(recipe_no);

				recipeVO = recipeSvc.getOneRecipe(recipe_no);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				String str2 = recipeVO.getFood_mater();
				String[] tokens = str2.split("-|\\+");
				
				 ingredients.clear();
				 quantity.clear();;
				
				for(int i =0;i<tokens.length-1;i+=2){
					ingredients.add(tokens[i]);
				}
				for(int i =1;i<tokens.length;i+=2){
					quantity.add(tokens[i]);
				}
				
				req.setAttribute("recipeVO", recipeVO); 
				req.setAttribute("ingredients", ingredients); 
				req.setAttribute("quantity", quantity); 
				req.setAttribute("recipe_cont_set", recipe_cont_set); 
				String url = "/front-end/recipe/RecipeInfo.jsp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				
				
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/recipe/update_recipe_input.jsp");
				failureView.forward(req, res);
			}
		}
		

		
		if ("insert_old".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_no = req.getParameter("mem_no").trim();
				String recipe_name = req.getParameter("recipe_name").trim();
				String recipe_intro = req.getParameter("recipe_intro").trim();
				
				Part part = req.getPart("recipe_pic");
				byte[] recipe_pic = null;
				if (getFileNameFromPart(part) != null && part.getContentType() != null) {	
					InputStream in = part.getInputStream();
					recipe_pic = new byte[in.available()];
					in.read(recipe_pic);
					in.close();
				} else {
					errorMsgs.add("請上傳食譜圖片");
				}	
				
				String[] ingredientsStr =req.getParameterValues("ingredients");
				String[] quantityStr =req.getParameterValues("quantity");
				
				if(recipe_name==null || recipe_name.isEmpty()){
					errorMsgs.add("食譜名稱不能為空白");
				}
				
				for(int i =0;i<ingredientsStr.length;i++){
					if(ingredientsStr[i].isEmpty() ){
						errorMsgs.add("食材不能為空白");
						break;
					}
				}
				for(int i =0;i<ingredientsStr.length;i++){
					if(quantityStr[i].isEmpty()){
						errorMsgs.add("食材數量不能為空白");
						break;
					}
				}
				
				
				
				
				StringBuffer str = new StringBuffer();
				List ingredients = new ArrayList();
				List quantity = new ArrayList();
				
				try
				{
					str.append(ingredientsStr[0]+"-"+quantityStr[0]);
					ingredients.add(ingredientsStr[0]);
					quantity.add(quantityStr[0]);
					for(int i =1;i<ingredientsStr.length;i++){
						str.append("+"+ingredientsStr[i]+"-"+quantityStr[i]); 
						ingredients.add(ingredientsStr[i]);
						quantity.add(quantityStr[i]);
					}
				} catch (IndexOutOfBoundsException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String food_mater = new String(str);
				
				RecipeVO recipeVO = new RecipeVO();
				recipeVO.setMem_no(mem_no);
				recipeVO.setRecipe_name(recipe_name);
				recipeVO.setRecipe_intro(recipe_intro);
				recipeVO.setRecipe_pic(recipe_pic);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("recipeVO", recipeVO); 
					req.setAttribute("ingredients", ingredients); 
					req.setAttribute("quantity", quantity); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/recipe/addRecipe.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				RecipeService recipeSvc = new RecipeService();
				recipeVO = recipeSvc.addRecipe(mem_no,recipe_name,recipe_intro,food_mater,recipe_pic);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/recipe/listAllRecipe.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/recipe/addRecipe.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				HttpSession session =req.getSession(); //取得session 
				String mem_no = (String)session.getAttribute("mem_no");
				String recipe_name = req.getParameter("recipe_name").trim();
				String recipe_intro = req.getParameter("recipe_intro").trim();
				
				Part part = req.getPart("recipe_pic");
				byte[] recipe_pic = null;
				if (getFileNameFromPart(part) != null && part.getContentType() != null) {	
					InputStream in = part.getInputStream();
					recipe_pic = new byte[in.available()];
					in.read(recipe_pic);
					in.close();
				} else {
					errorMsgs.add("請上傳食譜圖片");
				}	
				
				//取得食材及分量,並做簡單的輸入錯誤處理
				String[] ingredientsStr =req.getParameterValues("ingredients");
				String[] quantityStr =req.getParameterValues("quantity");
				
				if(recipe_name==null || recipe_name.isEmpty()){
					errorMsgs.add("食譜名稱不能為空白");
				}
<<<<<<< HEAD
				if(recipe_intro==null || recipe_intro.isEmpty()){
					errorMsgs.add("食譜簡介不能為空白");
				}
=======
				
				if(ingredientsStr==null || ingredientsStr.length==0){
					errorMsgs.add("食材為必填選項");
				}
				
>>>>>>> refs/heads/liquid-develop2
				for(int i =0;i<ingredientsStr.length;i++){
					if(ingredientsStr[i].isEmpty() ){
						errorMsgs.add("食材不能為空白");
						break;
					}
				}
				for(int i =0;i<ingredientsStr.length;i++){
					if(quantityStr[i].isEmpty()){
						errorMsgs.add("食材數量不能為空白");
						break;
					}
				}
				
				
				
				//串接食材字串
				StringBuffer str = new StringBuffer();
				List ingredients = new ArrayList();
				List quantity = new ArrayList();
				
				try
				{
					str.append(ingredientsStr[0]+"-"+quantityStr[0]);
					ingredients.add(ingredientsStr[0]);
					quantity.add(quantityStr[0]);
					for(int i =1;i<ingredientsStr.length;i++){
						str.append("+"+ingredientsStr[i]+"-"+quantityStr[i]); 
						ingredients.add(ingredientsStr[i]);
						quantity.add(quantityStr[i]);
					}
				} catch (IndexOutOfBoundsException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String food_mater = new String(str);
				
				//取得食譜內容
				String[] step = req.getParameterValues("step");
				String[] step_cont = req.getParameterValues("step_cont");
				
				//準備好放複數食譜圖片的list
				List<byte[]> step_pics = new ArrayList<byte[]>();
				
				Collection<Part> parts =  req.getParts();

	
				
				
				int picIdx = 0; //用getParts所取得的圖片包含食譜的圖片，依照順序上傳,第一張便是食譜圖片,必須剔除掉。
				
				/*判斷上傳的檔案是否是空的，就算是空的一樣要加入list中，原因在為了存入資料庫的步驟圖片是正確的，DAO部分圖片上傳的地方也要做
				  空值判斷，否則會出現NullPointer*/
				
				for (Part recipe_contPic : parts) {
						if(picIdx>4){
							if (getFileNameFromPart(recipe_contPic) != null && recipe_contPic.getContentType() != null){
								InputStream in = recipe_contPic.getInputStream();
								byte[] step_pic = new byte[in.available()];
								in.read(step_pic);
								in.close();
								step_pics.add(step_pic);
								
							}else if(getFileNameFromPart(recipe_contPic) == null){
								InputStream in = part.getInputStream();
								byte[] step_pic = null;
								step_pics.add(step_pic);
							}
						}
						picIdx++;
					} 
				
				for(int k =0;k<step_cont.length;k++){
					if(step_cont[k].isEmpty() && step_pics.get(k)==null){
						errorMsgs.add("步驟圖片與步驟內容不能為同時空白");
						break;
					}
				}
				
				//接收的食譜內容為複數，放入list中。
				List<String> step_conts = new ArrayList<String>();
				
				for(int i =0;i<step_cont.length;i++){
					step_conts.add(step_cont[i]);
				}
				
				List<Recipe_contVO> contList = new ArrayList<Recipe_contVO>();
				
				for(int i =0;i<step.length;i++){
					Recipe_contVO recipe_contVO = new Recipe_contVO();
					recipe_contVO.setStep(new Integer(step[i]));
					recipe_contVO.setStep_pic(step_pics.get(i));
					recipe_contVO.setStep_cont(step_conts.get(i));
					contList.add(recipe_contVO);
				}
				
				for(int i =0;i<contList.size();i++){
					System.out.println(contList.get(i).getStep());
				}
				
				//將資料存入VO
				RecipeVO recipeVO = new RecipeVO();
				recipeVO.setMem_no(mem_no);
				recipeVO.setRecipe_name(recipe_name);
				recipeVO.setRecipe_intro(recipe_intro);
				recipeVO.setRecipe_pic(recipe_pic);
				
				
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("recipeVO", recipeVO); 
					req.setAttribute("ingredients", ingredients); 
					req.setAttribute("quantity", quantity); 
					req.setAttribute("list", contList); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/recipe/addRecipe.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				RecipeService recipeSvc = new RecipeService();
				recipeVO = recipeSvc.addRecipeWith_Recipe_conts(mem_no, recipe_name, recipe_intro, food_mater, recipe_pic,"已發布", contList);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				List<RecipeVO> list = recipeSvc.findByMem_no(recipeVO.getMem_no());
				String recipe_no = "";
				for(RecipeVO aRecipe:list){
					if(aRecipe.getRecipe_name().equals(recipe_name)){
						recipe_no = aRecipe.getRecipe_no();
					}
				}
				
				
				String url = "recipe.do?action=getOne_For_Display&recipe_no="+recipe_no;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/recipe/addRecipe.jsp");
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
				RecipeService recipeSvc = new RecipeService();
				recipeSvc.deleteRecipe(recipe_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/recipe/listAllRecipe.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/recipe/listAllRecipe.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_UpdateViews".equals(action) || "getOne_For_UpdateLike".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String recipe_no = req.getParameter("recipe_no");
				
				/***************************2.開始查詢資料****************************************/
				RecipeService recipeSvc = new RecipeService();
				RecipeVO recipeVO = recipeSvc.getOneRecipe(recipe_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("recipeVO", recipeVO);     
				
				
				if (action.equals("getOne_For_UpdateViews"))
				{
					String url = "/front-end/recipe/update_recipeViews.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}else if(action.equals("getOne_For_UpdateLike")){
					String url = "/front-end/recipe/update_recipeLike.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/recipe/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("updateViews".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String recipe_no = req.getParameter("recipe_no").trim();
				
				Integer recipe_viewsPlus = null;
				try
				{
					recipe_viewsPlus = new Integer(req.getParameter("recipe_viewsPlus").trim());
					
				} catch (NumberFormatException e)
				{
					// TODO Auto-generated catch block
					errorMsgs.add("人氣增加數請填數字.");
					recipe_viewsPlus=0;
				}
				
				RecipeService recipeSvc = new RecipeService();
				RecipeVO recipeVO = recipeSvc.getOneRecipe(recipe_no);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("recipeVO", recipeVO);
					req.setAttribute("recipe_viewsPlus", recipe_viewsPlus);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/recipe/update_recipeViews.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Integer recipe_total_views = recipeVO.getRecipe_total_views()+recipe_viewsPlus;
				
				Integer recipe_week_views = recipeVO.getRecipe_week_views()+recipe_viewsPlus;
				
				recipeVO = recipeSvc.updateRecipeViews(recipe_no, recipe_total_views, recipe_week_views);
				
				recipeVO = recipeSvc.getOneRecipe(recipe_no);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("recipeVO", recipeVO); 
				String url = "/front-end/recipe/listOneRecipe.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/recipe/update_recipeViews.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("updateLike".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String recipe_no = req.getParameter("recipe_no").trim();
				
				Integer recipe_likePlus = null;
				try
				{
					recipe_likePlus = new Integer(req.getParameter("recipe_likePlus").trim());
					
				} catch (NumberFormatException e)
				{
					// TODO Auto-generated catch block
					errorMsgs.add("Like增加數請填數字.");
					recipe_likePlus=0;
				}
				
				RecipeService recipeSvc = new RecipeService();
				RecipeVO recipeVO = recipeSvc.getOneRecipe(recipe_no);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("recipeVO", recipeVO);
					req.setAttribute("recipe_likePlus", recipe_likePlus);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/recipe/update_recipeLike.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Integer recipe_like = recipeVO.getRecipe_like()+recipe_likePlus;
				
				
				recipeVO = recipeSvc.updateRecipeLike(recipe_no, recipe_like);
				
				recipeVO = recipeSvc.getOneRecipe(recipe_no);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("recipeVO", recipeVO); 
				String url = "/front-end/recipe/listOneRecipe.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/recipe/update_recipeLike.jsp");
				failureView.forward(req, res);
			}
		}

		if ("recipe_week_viewsZero".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				RecipeService recipeSvc = new RecipeService();
				List<RecipeVO> list = (List<RecipeVO>) recipeSvc.getAll().get(0);
				
				/***************************2.開始修改資料*****************************************/
				
				for(RecipeVO recipeVO:list){
					recipeSvc.WeekViewsToZero(recipeVO.getRecipe_no());
				}
				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				String url = "/front-end/recipe/listAllRecipe.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("人氣歸零失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/recipe/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	}
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
//		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
