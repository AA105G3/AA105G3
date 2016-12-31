package com.recipe_m_type.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe_m_type.model.Recipe_m_typeService;
import com.recipe_m_type.model.Recipe_m_typeVO;


public class Recipe_m_typeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("recipe_m_type_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入食譜中類別編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/recipe_m_type/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String  recipe_m_type_no = null;
				try {
					recipe_m_type_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("食譜中類別編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/recipe_m_type/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Recipe_m_typeService recipe_m_typeSvc = new Recipe_m_typeService();
				Recipe_m_typeVO recipe_m_typeVO = recipe_m_typeSvc.getOneRecipe_m_type(recipe_m_type_no);
				if (recipe_m_typeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/recipe_m_type/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("recipe_m_typeVO", recipe_m_typeVO); // 資料庫取出的recipe_m_typeVO物件,存入req
				String url = "/recipe_m_type/listOneRecipe_m_type.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneRecipe_m_type.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/recipe_m_type/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllRecipe_l_type.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String recipe_m_type_no = new String(req.getParameter("recipe_m_type_no"));
				
				/***************************2.開始查詢資料****************************************/
				Recipe_m_typeService recipe_m_typeSvc = new Recipe_m_typeService();
				Recipe_m_typeVO recipe_m_typeVO = recipe_m_typeSvc.getOneRecipe_m_type(recipe_m_type_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("recipe_m_typeVO", recipe_m_typeVO);         // 資料庫取出的recipe_m_typeVO物件,存入req
				String url = "/recipe_m_type/update_recipe_m_type_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_recipe_m_type_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/recipe_m_type/listAllRecipe_m_type.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_recipe_m_type_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String recipe_m_type_no = new String(req.getParameter("recipe_m_type_no").trim());
				String m_type_name = req.getParameter("m_type_name").trim();
				String parent_type = req.getParameter("parent_type").trim();
								
				Recipe_m_typeVO recipe_m_typeVO = new Recipe_m_typeVO();
				recipe_m_typeVO.setRecipe_m_type_no(recipe_m_type_no);
				recipe_m_typeVO.setM_type_name(m_type_name);
				recipe_m_typeVO.setParent_type(parent_type);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("recipe_m_typeVO", recipe_m_typeVO); // 含有輸入格式錯誤的recipe_m_typeVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/recipe_m_type/update_recipe_m_type_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Recipe_m_typeService recipe_m_typeSvc = new Recipe_m_typeService();
				recipe_m_typeVO = recipe_m_typeSvc.updateRecipe_m_type(recipe_m_type_no, m_type_name, parent_type);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("recipe_m_typeVO", recipe_m_typeVO); // 資料庫update成功後,正確的的recipe_m_typeVO物件,存入req
				String url = "/recipe_m_type/listOneRecipe_m_type.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneRecipe_m_type.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/recipe_m_type/update_recipe_m_type_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addRecipe_l_type.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String m_type_name = req.getParameter("m_type_name").trim();
				
				Recipe_m_typeVO recipe_m_typeVO = new Recipe_m_typeVO();
				recipe_m_typeVO.setM_type_name(m_type_name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("recipe_m_typeVO", recipe_m_typeVO); // 含有輸入格式錯誤的recipe_m_typeVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/recipe_m_type/addRecipe_m_type.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Recipe_m_typeService recipe_m_typeSvc = new Recipe_m_typeService();
				recipe_m_typeVO = recipe_m_typeSvc.addRecipe_m_type( m_type_name);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/recipe_m_type/listAllRecipe_m_type.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllRecipe_m_type.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/recipe_m_type/addRecipe_m_type.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllRecipe_m_type.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String recipe_m_type_no = new String(req.getParameter("recipe_m_type_no"));
				
				/***************************2.開始刪除資料***************************************/
				Recipe_m_typeService recipe_m_typeSvc = new Recipe_m_typeService();
				recipe_m_typeSvc.deleteRecipe_m_type(recipe_m_type_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/recipe_m_type/listAllRecipe_m_type.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/recipe_m_type/listAllRecipe_m_type.jsp");
				failureView.forward(req, res);
			}
		}
	}
}	
	
