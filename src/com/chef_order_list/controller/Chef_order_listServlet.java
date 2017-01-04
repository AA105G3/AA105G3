package com.chef_order_list.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.chef_order_list.model.*;

public class Chef_order_listServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)|| "getOne_For_Chef".equals(action)|| "getOne_For_Back".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("chef_ord_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入私廚訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chef_order_list/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String chef_ord_no = null;
				try {
					chef_ord_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("私廚訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chef_order_list/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Chef_order_listService chef_order_listSvc = new Chef_order_listService();
				Chef_order_listVO chef_order_listVO = chef_order_listSvc.getOneChef_order_list(chef_ord_no);
				if (chef_order_listVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chef_order_list/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("chef_order_listVO", chef_order_listVO); // 資料庫取出的chef_order_listVO物件,存入req
				String url = null;
				if("getOne_For_Display".equals(action))
					url = "/front-end/chef_order_list/chefOrderInfoOfMem.jsp";
				else if("getOne_For_Chef".equals(action))
					url = "/front-end/chef_order_list/chefOrderInfoOfChef.jsp";
				else if("getOne_For_Back".equals(action))
					url = "/back-end/chef_order_list/chefOrderInfoOfBack.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneChef_order_list.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef_order_list/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action) || "getOne_For_ChefCheck".equals(action)|| "getOne_For_BackCheck".equals(action)) { // 來自listAllChef_order_list.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String chef_ord_no = new String(req.getParameter("chef_ord_no"));
				
				/***************************2.開始查詢資料****************************************/
				Chef_order_listService chef_order_listSvc = new Chef_order_listService();
				Chef_order_listVO chef_order_listVO = chef_order_listSvc.getOneChef_order_list(chef_ord_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("chef_order_listVO", chef_order_listVO);         // 資料庫取出的chef_order_listVO物件,存入req
				String url = null;
				if("getOne_For_Update".equals(action))
					url = "/front-end/chef_order_list/chefOrderInfoOfMem.jsp";
				else if("getOne_For_ChefCheck".equals(action))
					url = "/front-end/chef_order_list/chefOrderInfoOfChef.jsp";
				else if("getOne_For_BackCheck".equals(action))
					url = "/back-end/chef_order_list/chefOrderInfoOfBack.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_chef_order_list_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef_order_list/listAllChef_order_list.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_chef_order_list_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String chef_ord_no = new String(req.getParameter("chef_ord_no").trim());
				
				Double chef_ord_cost = null;
				try {
					chef_ord_cost = new Double(req.getParameter("chef_ord_cost").trim());
				} catch (NumberFormatException e) {
					chef_ord_cost = 0.0;
					errorMsgs.add("訂單金額請填數字.");
				}
				
				Timestamp chef_act_date = null;
				try {
					
					
					
					chef_act_date = Timestamp.valueOf(req.getParameter("chef_act_date").trim());
				} catch (IllegalArgumentException e) {
					chef_act_date=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String chef_ord_place = req.getParameter("chef_ord_place").trim();
				String chef_ord_cnt = req.getParameter("chef_ord_cnt").trim();
				String chef_ord_con = req.getParameter("chef_ord_con").trim();
				
				Chef_order_listVO chef_order_listVO = new Chef_order_listVO();
				chef_order_listVO.setChef_ord_no(chef_ord_no);
				chef_order_listVO.setChef_ord_cost(chef_ord_cost);
				chef_order_listVO.setChef_act_date(chef_act_date);
				chef_order_listVO.setChef_ord_place(chef_ord_place);
				chef_order_listVO.setChef_ord_cnt(chef_ord_cnt);
				chef_order_listVO.setChef_ord_con(chef_ord_con);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("chef_order_listVO", chef_order_listVO); // 含有輸入格式錯誤的chef_order_listVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chef_order_list/update_chef_order_list_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Chef_order_listService chef_order_listSvc = new Chef_order_listService();
				chef_order_listVO = chef_order_listSvc.updateChef_order_list(chef_ord_no, chef_ord_cost, chef_act_date, chef_ord_place, chef_ord_cnt, chef_ord_con);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("chef_order_listVO", chef_order_listVO); // 資料庫update成功後,正確的的chef_order_listVO物件,存入req
				String url = "/front-end/chef_order_list/listOneChef_order_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneChef_order_list.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef_order_list/update_chef_order_list_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addChef_order_list.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_no = req.getParameter("mem_no").trim();
				String chef_no = req.getParameter("chef_no").trim();
				
				Double chef_ord_cost = null;
				try {
					chef_ord_cost = new Double(req.getParameter("chef_ord_cost").trim());
				} catch (NumberFormatException e) {
					chef_ord_cost = 0.0;
					errorMsgs.add("訂單金額請填數字.");
				}
				
				Timestamp chef_act_date = null;
				try {
					chef_act_date = Timestamp.valueOf(req.getParameter("chef_act_date").trim());
				} catch (IllegalArgumentException e) {
					chef_act_date=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String chef_ord_place = req.getParameter("chef_ord_place").trim();
				String chef_ord_cnt = req.getParameter("chef_ord_cnt").trim();
				
				
//				Timestamp chef_ord_date = null;
//				try {
//					chef_ord_date = Timestamp.valueOf(req.getParameter("chef_ord_date").trim());
//				} catch (IllegalArgumentException e) {
//					chef_ord_date=new Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				
				

				Chef_order_listVO chef_order_listVO = new Chef_order_listVO();
				chef_order_listVO.setMem_no(mem_no);
				chef_order_listVO.setChef_no(chef_no);
				chef_order_listVO.setChef_ord_cost(chef_ord_cost);
				chef_order_listVO.setChef_act_date(chef_act_date);
				chef_order_listVO.setChef_ord_place(chef_ord_place);
				chef_order_listVO.setChef_ord_cnt(chef_ord_cnt);
				
//				chef_order_listVO.setChef_ord_date(chef_ord_date);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("chef_order_listVO", chef_order_listVO); // 含有輸入格式錯誤的chef_order_listVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chef_order_list/addChef_order_list.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Chef_order_listService chef_order_listSvc = new Chef_order_listService();
				chef_order_listVO = chef_order_listSvc.addChef_order_list(mem_no, chef_no, chef_ord_cost, chef_act_date, chef_ord_place, chef_ord_cnt);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/chef_order_list/listAllChef_order_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllChef_order_list.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef_order_list/addChef_order_list.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllChef_order_list.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String chef_ord_no = new String(req.getParameter("chef_ord_no"));
				
				/***************************2.開始刪除資料***************************************/
				Chef_order_listService chef_order_listSvc = new Chef_order_listService();
				chef_order_listSvc.deleteChef_order_list(chef_ord_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/chef_order_list/listAllChef_order_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef_order_list/listAllChef_order_list.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
