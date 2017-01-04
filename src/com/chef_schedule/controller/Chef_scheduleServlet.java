package com.chef_schedule.controller;

import java.io.*;
import java.util.*;


import javax.servlet.*;
import javax.servlet.http.*;

import com.chef_schedule.model.*;

public class Chef_scheduleServlet extends HttpServlet {

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
				String str = req.getParameter("chef_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入私廚編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chef_schedule/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String chef_no = null;
				try {
					chef_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("私廚編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chef_schedule/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Chef_scheduleService chef_scheduleSvc = new Chef_scheduleService();
				Chef_scheduleVO chef_scheduleVO = chef_scheduleSvc.getOneChef_schedule(chef_no);
				if (chef_scheduleVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chef_schedule/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("chef_scheduleVO", chef_scheduleVO); // 資料庫取出的chef_scheduleVO物件,存入req
				String url = "/front-end/chef_schedule/listOneChef_schedule.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneChef_schedule.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef_schedule/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllChef_schedule.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String chef_no = new String(req.getParameter("chef_no"));
				
				/***************************2.開始查詢資料****************************************/
				Chef_scheduleService chef_scheduleSvc = new Chef_scheduleService();
				Chef_scheduleVO chef_scheduleVO = chef_scheduleSvc.getOneChef_schedule(chef_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("chef_scheduleVO", chef_scheduleVO);         // 資料庫取出的chef_scheduleVO物件,存入req
				String url = "/front-end/chef_schedule/update_chef_schedule_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_chef_schedule_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef_schedule/listAllChef_schedule.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_chef_schedule_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String chef_no = new String(req.getParameter("chef_no").trim());
				String chef_scd_mae = req.getParameter("chef_scd_mae").trim();
				
				java.sql.Date chef_scd_date = null;
				try {
					chef_scd_date = java.sql.Date.valueOf(req.getParameter("chef_scd_date").trim());
				} catch (IllegalArgumentException e) {
					chef_scd_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
								
				String chef_scd_cnt = req.getParameter("chef_scd_cnt").trim();				
				
				


				Chef_scheduleVO chef_scheduleVO = new Chef_scheduleVO();
				chef_scheduleVO.setChef_no(chef_no);			
				chef_scheduleVO.setChef_scd_mae(chef_scd_mae);
				chef_scheduleVO.setChef_scd_date(chef_scd_date);
				chef_scheduleVO.setChef_scd_cnt(chef_scd_cnt);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("chef_scheduleVO", chef_scheduleVO); // 含有輸入格式錯誤的chef_scheduleVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chef_schedule/update_chef_schedule_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Chef_scheduleService chef_scheduleSvc = new Chef_scheduleService();
				chef_scheduleVO = chef_scheduleSvc.updateChef_schedule(chef_no, chef_scd_mae, chef_scd_date, chef_scd_cnt);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("chef_scheduleVO", chef_scheduleVO); // 資料庫update成功後,正確的的chef_scheduleVO物件,存入req
				String url = "/front-end/chef_schedule/listOneChef_schedule.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneChef_schedule.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef_schedule/update_chef_schedule_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addChef_schedule.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String chef_no = req.getParameter("chef_no").trim();
				String chef_scd_mae = req.getParameter("chef_scd_mae").trim();
				String chef_scd_cnt = req.getParameter("chef_scd_cnt").trim();
				
				java.sql.Date chef_scd_date = null;
				try {
					chef_scd_date = java.sql.Date.valueOf(req.getParameter("chef_scd_date").trim());
				} catch (IllegalArgumentException e) {
					chef_scd_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
//				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//			    String hireDate = req.getParameter("emp_hiredate").trim();
//			    
//			    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//			    java.util.Date date = sdf.parse(hireDate);
//			    hireDate = sdf2.format(date);
//				java.sql.Date emp_hiredate = null;
//				try {
//					emp_hiredate = java.sql.Date.valueOf(hireDate);
//				} catch (IllegalArgumentException e) {
//					emp_hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}


				Chef_scheduleVO chef_scheduleVO = new Chef_scheduleVO();
				chef_scheduleVO.setChef_no(chef_no);
				chef_scheduleVO.setChef_scd_mae(chef_scd_mae);
				chef_scheduleVO.setChef_scd_cnt(chef_scd_cnt);
				chef_scheduleVO.setChef_scd_date(chef_scd_date);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("chef_scheduleVO", chef_scheduleVO); // 含有輸入格式錯誤的chef_scheduleVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chef_schedule/addChef_schedule.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Chef_scheduleService chef_scheduleSvc = new Chef_scheduleService();
				chef_scheduleVO = chef_scheduleSvc.addChef_schedule(chef_no, chef_scd_date, chef_scd_mae, chef_scd_cnt);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/chef_schedule/listAllChef_schedule.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllChef_schedule.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef_schedule/addChef_schedule.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllChef_schedule.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String chef_no = new String(req.getParameter("chef_no"));
				
				
				java.sql.Date chef_scd_date = null;
				try {
					chef_scd_date = java.sql.Date.valueOf(req.getParameter("chef_scd_date").trim());
				} catch (IllegalArgumentException e) {
					chef_scd_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				String chef_scd_mae = new String(req.getParameter("chef_scd_mae"));				
				
				/***************************2.開始刪除資料***************************************/
				Chef_scheduleService chef_scheduleSvc = new Chef_scheduleService();
				chef_scheduleSvc.deleteChef_schedule(chef_no,chef_scd_date,chef_scd_mae);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/chef_schedule/listAllChef_schedule.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef_schedule/listAllChef_schedule.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
