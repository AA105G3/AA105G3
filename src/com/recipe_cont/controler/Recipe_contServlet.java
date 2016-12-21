package com.recipe_cont.controler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.recipe.model.RecipeService;
import com.recipe.model.RecipeVO;
import com.recipe_cont.model.Recipe_contService;
import com.recipe_cont.model.Recipe_contVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/recipe_cont/recipe_cont.do")

public class Recipe_contServlet extends HttpServlet {
	
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
							.getRequestDispatcher("/front-end/recipe_cont/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				Recipe_contService recipe_contSvc = new Recipe_contService();
				Set<Recipe_contVO> set = recipe_contSvc.getRecipe_cont(recipe_no);
				
				if (set == null) {
					errorMsgs.add("查無資料");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/recipe_cont/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("recipe_cont_set", set); 
				String url = "/front-end/recipe_cont/recipe_cont_byRecipe_no.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/recipe_cont/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String recipe_no = req.getParameter("recipe_no").trim();
				String[] step = req.getParameterValues("step");
				String[] step_cont = req.getParameterValues("step_cont");
				
				List<byte[]> step_pics = new ArrayList<byte[]>();
				
				Collection<Part> parts =  req.getParts();

				/*判斷上傳的檔案是否是空的，就算是空的一樣要加入list中，原因在為了存入資料庫的步驟圖片是正確的，DAO部分圖片上傳的地方也要做
				  空值判斷，否則會出現NullPointer*/
				for (Part part : parts) {
						if (getFileNameFromPart(part) != null && part.getContentType() != null){
							InputStream in = part.getInputStream();
							byte[] step_pic = new byte[in.available()];
							in.read(step_pic);
							in.close();
							step_pics.add(step_pic);
							
						}else if(getFileNameFromPart(part) == null){
							InputStream in = part.getInputStream();
							byte[] step_pic = null;
							step_pics.add(step_pic);
						}
					} 
				
				List<String> step_conts = new ArrayList<String>();
				
				for(int i =0;i<step_cont.length;i++){
					step_conts.add(step_cont[i]);
				}
				
				List<Recipe_contVO> newRecipe_conts = new ArrayList<Recipe_contVO>();
				
				Recipe_contVO recipe_contVO = new Recipe_contVO();
				for(int i =0;i<step.length;i++){
					recipe_contVO.setRecipe_no(recipe_no);
					recipe_contVO.setStep(new Integer(step[i]));
					
						recipe_contVO.setStep_pic(step_pics.get(i));
					
					recipe_contVO.setStep_cont(step_conts.get(i));
					newRecipe_conts.add(recipe_contVO);
				}
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("newRecipe_conts", newRecipe_conts); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/recipe_cont/addRecipe_cont.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Recipe_contService recipe_contSvc = new Recipe_contService();
				
				for(int i =0 ; i<newRecipe_conts.size();i++){
						recipe_contVO=recipe_contSvc.addRecipe_cont(recipe_no, new Integer(step[i]),
								step_pics.get(i), step_conts.get(i));
				
					
				}
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "recipe_cont.do?action=getOne_For_Display";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/recipe_cont/addRecipe_cont.jsp");
//				failureView.forward(req, res);
//			}
		}
		
	}
	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}
