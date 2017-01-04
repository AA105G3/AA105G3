package com.recipe_message.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe_message.model.Recipe_messageVO;
import com.recipe_message.model.Recipe_messageService;

public class Recipe_messageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String str = req.getParameter("recipe_msg_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入食譜留言編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/recipe_message/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String recipe_msg_no = null;
				try {
					recipe_msg_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("食譜留言編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/recipe_message/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Recipe_messageService recipe_messageSvc = new Recipe_messageService();
				Recipe_messageVO recipe_messageVO = recipe_messageSvc.getOneRecipe_message(recipe_msg_no);
				if (recipe_messageVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/recipe_message/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("recipe_messageVO", recipe_messageVO); // 資料庫取出的recipe_messageVO物件,存入req
				String url = "/recipe_message/listOneRecipe_message.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneRecipe_message.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/recipe_message/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllRecipe_message.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String recipe_msg_no = new String(req.getParameter("recipe_msg_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				Recipe_messageService recipe_messageSvc = new Recipe_messageService();
				Recipe_messageVO recipe_messageVO = recipe_messageSvc.getOneRecipe_message(recipe_msg_no);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("recipe_messageVO", recipe_messageVO); // 資料庫取出的recipe_messageVO物件,存入req
				String url = "/recipe_message/update_recipe_message_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_recipe_message_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/recipe_message/listAllRecipe_message.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_recipe_m_type_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/

				String msg_cont = req.getParameter("msg_cont").trim();
				String recipe_msg_ref = req.getParameter("recipe_msg_ref").trim();

				Recipe_messageVO recipe_messageVO = new Recipe_messageVO();
				recipe_messageVO.setMsg_cont(msg_cont);
				recipe_messageVO.setRecipe_msg_ref(recipe_msg_ref);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("recipe_messageVO", recipe_messageVO); // 含有輸入格式錯誤的recipe_messageVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/recipe_message/update_recipe_message_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Recipe_messageService recipe_messageSvc = new Recipe_messageService();
				recipe_messageVO = recipe_messageSvc.updateRecipe_message(msg_cont, recipe_msg_ref);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("recipe_messageVO", recipe_messageVO); // 資料庫update成功後,正確的的recipe_messageVO物件,存入req
				String url = "/recipe_message/listOneRecipe_message.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneRecipe_message.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/recipe_message/update_recipe_message_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addRecipe_l_type.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String mem_no = req.getParameter("mem_no").trim();
				String recipe_no = req.getParameter("recipe_no").trim();
				String msg_cont = req.getParameter("msg_cont").trim();
				String recipe_msg_ref = req.getParameter("recipe_msg_ref").trim();

				Recipe_messageVO recipe_messageVO = new Recipe_messageVO();
				recipe_messageVO.setMem_no(mem_no);
				recipe_messageVO.setRecipe_no(recipe_no);
				recipe_messageVO.setMsg_cont(msg_cont);
				recipe_messageVO.setRecipe_msg_ref(recipe_msg_ref);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("recipe_messageVO", recipe_messageVO); // 含有輸入格式錯誤的recipe_m_typeVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/recipe_message/addRecipe_message.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Recipe_messageService recipe_messageSvc = new Recipe_messageService();
				recipe_messageVO = recipe_messageSvc.addRecipe_message(mem_no, recipe_no, msg_cont, recipe_msg_ref);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/recipe_message/listAllRecipe_message.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllRecipe_message.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/recipe_message/addRecipe_message.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllRecipe_message.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String recipe_msg_no = new String(req.getParameter("recipe_msg_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				Recipe_messageService recipe_messageSvc = new Recipe_messageService();
				recipe_messageSvc.deleteRecipe_message(recipe_msg_no);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/recipe_message/listAllRecipe_message.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/recipe_message/listAllRecipe_message.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
