package com.chef_order_list.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.chef.model.*;
import com.chef_order_list.model.*;
import com.util.MailService;

public class Chef_order_listServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
if ("updateByChef".equals(action)) { // 來自update_chef_order_list_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			MailService mailSvc = new MailService();
			String usermail = req.getParameter("mem_email");
			String subject = "私廚訂單通知";
			String mailcontext = null;
		
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
							.getRequestDispatcher("/front-end/chef_order_list/UpdateChefOrderList.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Chef_order_listService chef_order_listSvc = new Chef_order_listService();
				chef_order_listVO = chef_order_listSvc.updateChef_order_list(chef_ord_no, chef_ord_cost, chef_act_date, chef_ord_place, chef_ord_cnt, chef_ord_con);
				
				String mem_name = req.getParameter("mem_name");
				mailcontext = mem_name + " 您好，您所下的私廚訂單已由私廚方完成金額的填寫，請您至會員個人頁面中的私廚訂單查詢中確認，感謝您的配合。";
				mailSvc.sendMail(usermail, subject, mailcontext);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("chef_order_listVO", chef_order_listVO); // 資料庫update成功後,正確的的chef_order_listVO物件,存入req
				String url = "/front-end/chef_order_list/ChefOrderListOfChef2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneChef_order_list.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef_order_list/UpdateChefOrderList.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getChefNo_For_addChefOrd".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("chef_no");

				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入私廚訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chef/chefInfo.jsp");
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
							.getRequestDispatcher("/front-end/chef_order_list/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ChefService chefSvc = new ChefService();
				ChefVO chefVO = chefSvc.getOneChef(chef_no);
				if (chefVO == null) {
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
				req.setAttribute("chefVO", chefVO); // 資料庫取出的chefVO物件,存入req
				String url = "/front-end/chef_order_list/setChefOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneChef_order_list.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef_order_list/setChefOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
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
					url = "/front-end/chef_order_list/ChefOrderInfoOfChef2.jsp";
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
					url = "/front-end/chef_order_list/endChefOrder.jsp";
				else if("getOne_For_ChefCheck".equals(action))
					url = "/front-end/chef_order_list/UpdateChefOrderList.jsp";
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
		
//		if ("getOne_For_Update".equals(action)) { // 來自listAllChef_order_list.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.接收請求參數****************************************/
//				String chef_ord_no = new String(req.getParameter("chef_ord_no"));
//				
//				/***************************2.開始查詢資料****************************************/
//				Chef_order_listService chef_order_listSvc = new Chef_order_listService();
//				Chef_order_listVO chef_order_listVO = chef_order_listSvc.getOneChef_order_list(chef_ord_no);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("chef_order_listVO", chef_order_listVO);         // 資料庫取出的chef_order_listVO物件,存入req
//				String url = "/front-end/chef_order_list/chefOrderInfoOfChef.jsp";
//				
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_chef_order_list_input.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/chef_order_list/listAllChef_order_list.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		
		
		if ("accept_ord".equals(action)) { // 來自update_chef_order_list_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);		
//			try {
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
				
				//寄送mail
				String to = req.getParameter("mem_email");
				String subject = req.getParameter("mem_name")+"已同意訂單並付款";
				String messageText = req.getParameter("mem_name")+"已同意了您的訂單並支付了款項，請注意約定的時間及內容，祝您交易順利";
				
				MailService mailService = new MailService();
			    mailService.sendMail(to, subject, messageText);
				
				req.setAttribute("chef_order_listVO", chef_order_listVO); // 資料庫update成功後,正確的的chef_order_listVO物件,存入req
				String url = "/front-end/chef_order_list/chefOrderListOfMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneChef_order_list.jsp
				successView.forward(req, res);				

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/chef_order_list/update_chef_order_list_input.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		if ("reject_ord".equals(action)) { // 來自update_chef_order_list_input.jsp的請求
			
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
				
				//寄送mail
				String to = req.getParameter("mem_email");
				String subject = req.getParameter("mem_name")+"已回絕訂單";
				String messageText = req.getParameter("reject_reason");
				
				MailService mailService = new MailService();
			    mailService.sendMail(to, subject, messageText);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef_order_list/update_chef_order_list_input.jsp");
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
				
				//寄mail
				String to = req.getParameter("mem_email");
				String subject = req.getParameter("chef_name")+"已回復訂單";
				String messageText = req.getParameter("reject_reason");
				
				MailService mailService = new MailService();
			    mailService.sendMail(to, subject, messageText);

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
//				String mem_no = req.getParameter("mem_no").trim();
String mem_no = "M00000001";
				String chef_no = req.getParameter("chef_no").trim();
			
				Double chef_ord_cost = null;
				try {
					chef_ord_cost = new Double(req.getParameter("chef_ord_cost").trim());
				} catch (NumberFormatException e) {
					chef_ord_cost = 0.0;
					errorMsgs.add("訂單金額請填數字.");
				}
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			    String act_date = req.getParameter("act_date").trim();
			    String act_time = req.getParameter("act_time").trim();
			    String dateTime = act_date+" "+act_time;
				Timestamp chef_act_date = null;
				try {
					java.util.Date du = sdf.parse(dateTime);
					long len = du.getTime();
					chef_act_date = new Timestamp(len);
				//	chef_act_date = new Timestamp(sdf.parse(dateTime).getTime());
				} catch (Exception e) {
					chef_act_date=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String chef_ord_place = req.getParameter("chef_ord_place").trim();
				String chef_ord_cnt = req.getParameter("chef_ord_cnt").trim();

												

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
				String url = "/front-end/chef_order_list/chefOrderListOfMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllChef_order_list.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef_order_list/setChefOrder.jsp");
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
