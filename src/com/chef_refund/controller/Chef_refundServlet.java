package com.chef_refund.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.chef_refund.model.*;

public class Chef_refundServlet extends HttpServlet {

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
				String str = req.getParameter("chef_rfd_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入退款編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/chef_refund/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String chef_rfd_no = null;
				try {
					chef_rfd_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("退款編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/chef_refund/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Chef_refundService chef_refundSvc = new Chef_refundService();
				Chef_refundVO chef_refundVO = chef_refundSvc.getOneChef_refund(chef_rfd_no);
				if (chef_refundVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/chef_refund/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("chef_refundVO", chef_refundVO); // 資料庫取出的chef_refundVO物件,存入req
				String url = "/back-end/chef_refund/listOneChef_refund.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneChef_refund.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/chef_refund/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllChef_refund.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String chef_rfd_no = new String(req.getParameter("chef_rfd_no"));
				
				/***************************2.開始查詢資料****************************************/
				Chef_refundService chef_refundSvc = new Chef_refundService();
				Chef_refundVO chef_refundVO = chef_refundSvc.getOneChef_refund(chef_rfd_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("chef_refundVO", chef_refundVO);         // 資料庫取出的chef_refundVO物件,存入req
				String url = "/back-end/chef_refund/update_chef_refund_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_chef_refund_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/chef_refund/listAllChef_refund.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_chef_refund_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String chef_rfd_no = new String(req.getParameter("chef_rfd_no").trim());
				String chef_ord_no = req.getParameter("chef_ord_no").trim();
				String emp_no = req.getParameter("emp_no").trim();				
				
				Double chef_rfd_per = null;
				try {
					chef_rfd_per = new Double(req.getParameter("chef_rfd_per").trim());
				} catch (NumberFormatException e) {
					chef_rfd_per = 0.0;
					errorMsgs.add("退款百分比請填數字.");
				}

				String mem_bnk = req.getParameter("mem_bnk").trim();
				String mem_bnk_ac = req.getParameter("mem_bnk_ac").trim();
				String chef_ord_chk_cond = req.getParameter("chef_ord_chk_cond").trim();
				String chef_ord_red_cnt = req.getParameter("chef_ord_red_cnt").trim();

				Chef_refundVO chef_refundVO = new Chef_refundVO();
				chef_refundVO.setChef_rfd_no(chef_rfd_no);
				chef_refundVO.setChef_ord_no(chef_ord_no);
				chef_refundVO.setEmp_no(emp_no);
				chef_refundVO.setChef_rfd_per(chef_rfd_per);
				chef_refundVO.setMem_bnk(mem_bnk);
				chef_refundVO.setMem_bnk_ac(mem_bnk_ac);
				chef_refundVO.setChef_ord_chk_cond(chef_ord_chk_cond);
				chef_refundVO.setChef_ord_red_cnt(chef_ord_red_cnt);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("chef_refundVO", chef_refundVO); // 含有輸入格式錯誤的chef_refundVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/chef_refund/update_chef_refund_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Chef_refundService chef_refundSvc = new Chef_refundService();
				chef_refundVO = chef_refundSvc.updateChef_refund(chef_rfd_no, chef_ord_no, emp_no, chef_rfd_per, mem_bnk, mem_bnk_ac, chef_ord_chk_cond, chef_ord_red_cnt);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("chef_refundVO", chef_refundVO); // 資料庫update成功後,正確的的chef_refundVO物件,存入req
				String url = "/back-end/chef_refund/listOneChef_refund.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneChef_refund.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/chef_refund/update_chef_refund_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update_back".equals(action)) { // 來自listAllChef_refund.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String chef_rfd_no = new String(req.getParameter("chef_rfd_no"));
				
				/***************************2.開始查詢資料****************************************/
				Chef_refundService chef_refundSvc = new Chef_refundService();
				Chef_refundVO chef_refundVO = chef_refundSvc.getOneChef_refund(chef_rfd_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("chef_refundVO", chef_refundVO);         // 資料庫取出的chef_refundVO物件,存入req
				String url = "/back-end/chef_refund/update_chef_refund_check.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_chef_refund_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/chef_refund/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update_back".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String chef_rfd_no = new String(req.getParameter("chef_rfd_no").trim());			
				
				Double chef_rfd_per = null;
				try {
					chef_rfd_per = new Double(req.getParameter("chef_rfd_per").trim());
				} catch (NumberFormatException e) {
					chef_rfd_per = 0.0;
					errorMsgs.add("退款百分比請填數字.");
				}

				String chef_ord_chk_cond = req.getParameter("chef_ord_chk_cond").trim();
				String chef_ord_red_cnt = req.getParameter("chef_ord_red_cnt").trim();

				Chef_refundVO chef_refundVO = new Chef_refundVO();
				chef_refundVO.setChef_rfd_no(chef_rfd_no);
				chef_refundVO.setChef_rfd_per(chef_rfd_per);
				chef_refundVO.setChef_ord_chk_cond(chef_ord_chk_cond);
				chef_refundVO.setChef_ord_red_cnt(chef_ord_red_cnt);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("chef_refundVO", chef_refundVO); // 含有輸入格式錯誤的chef_refundVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/chef_refund/update_chef_refund_check.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Chef_refundService chef_refundSvc = new Chef_refundService();
				chef_refundVO = chef_refundSvc.update_backChef_refund(chef_rfd_no, chef_rfd_per, chef_ord_chk_cond, chef_ord_red_cnt);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("chef_refundVO", chef_refundVO); // 資料庫update成功後,正確的的chef_refundVO物件,存入req
				String url = "/back-end/chef_refund/listOneChef_refund.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneChef_refund.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/chef_refund/update_chef_refund_check.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addChef_refund.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String chef_ord_no = req.getParameter("chef_ord_no").trim();
				String emp_no = req.getParameter("emp_no").trim();
				
				
				Double chef_rfd_per = null;
				try {
					chef_rfd_per = new Double(req.getParameter("chef_rfd_per").trim());
				} catch (NumberFormatException e) {
					chef_rfd_per = 0.0;
					errorMsgs.add("退款百分比請填數字.");
				}
				
				String mem_bnk = req.getParameter("mem_bnk").trim();
				String mem_bnk_ac = req.getParameter("mem_bnk_ac").trim();
				String chef_ord_chk_cond = req.getParameter("chef_ord_chk_cond").trim();
				String chef_ord_red_cnt = req.getParameter("chef_ord_red_cnt").trim();

				Chef_refundVO chef_refundVO = new Chef_refundVO();				
				chef_refundVO.setChef_ord_no(chef_ord_no);
				chef_refundVO.setEmp_no(emp_no);
				chef_refundVO.setChef_rfd_per(chef_rfd_per);
				chef_refundVO.setMem_bnk(mem_bnk);
				chef_refundVO.setMem_bnk_ac(mem_bnk_ac);
				chef_refundVO.setChef_ord_chk_cond(chef_ord_chk_cond);
				chef_refundVO.setChef_ord_red_cnt(chef_ord_red_cnt);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("chef_refundVO", chef_refundVO); // 含有輸入格式錯誤的chef_refundVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/chef_refund/addChef_refund.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Chef_refundService chef_refundSvc = new Chef_refundService();
				chef_refundVO = chef_refundSvc.addChef_refund(chef_ord_no, emp_no, chef_rfd_per, mem_bnk, mem_bnk_ac, chef_ord_chk_cond, chef_ord_red_cnt);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/chef_refund/listAllChef_refund.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllChef_refund.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/chef_refund/addChef_refund.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllChef_refund.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String chef_rfd_no = new String(req.getParameter("chef_rfd_no"));
				
				/***************************2.開始刪除資料***************************************/
				Chef_refundService chef_refundSvc = new Chef_refundService();
				chef_refundSvc.deleteChef_refund(chef_rfd_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/chef_refund/listAllChef_refund.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/chef_refund/listAllChef_refund.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
