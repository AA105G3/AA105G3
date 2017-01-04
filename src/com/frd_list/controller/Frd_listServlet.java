package com.frd_list.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.frd_list.model.Frd_listService;
import com.frd_list.model.Frd_listVO;

public class Frd_listServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action(Frd_listServlet):" + action);

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String str = req.getParameter("mem_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入我的最愛編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/frd_list/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String mem_no = null;
				try {
					mem_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/frd_list/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Frd_listService frd_listSvc = new Frd_listService();
				Frd_listVO frd_listVO = frd_listSvc.getOneFrd_list(mem_no.toUpperCase());
				if (frd_listVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/frd_list/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("frd_listVO", frd_listVO); // 資料庫取出的frd_listVO物件,存入req
				String url = "/front-end/frd_list/listOneFrd_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneFrd_list.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/frd_list/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getAllByMem_no_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String str = req.getParameter("mem_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/frd_list/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String mem_no = null;
				try {
					mem_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/frd_list/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Frd_listService frd_listSvc = new Frd_listService();
				List frd_listVO = frd_listSvc.getAllByMem_noFrd_list(mem_no.toUpperCase());
				if (frd_listVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/frd_list/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("frd_listVO", frd_listVO); // 資料庫取出的frd_listVO物件,存入req
				String url = "/front-end/frd_list/listAllByMem_noFrd_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listAllByMem_noFrd_list.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/frd_list/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addFrd_list.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String mem_no = req.getParameter("mem_no").trim();
				String friend_no = req.getParameter("friend_no").trim();
				String friend_chk = req.getParameter("friend_chk").trim();

				Frd_listVO frd_listVO = new Frd_listVO();
				frd_listVO.setFriend_no(friend_no);
				frd_listVO.setMem_no(mem_no);
				frd_listVO.setFriend_chk(friend_chk);
				System.out.println("1= "+friend_chk.isEmpty());
				System.out.println("1 friend_chk= "+friend_chk);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {System.out.println("181= ");
					req.setAttribute("frd_listVO", frd_listVO); // 含有輸入格式錯誤的frd_listVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/frd_list/addFrd_list.jsp");
					failureView.forward(req, res);
					return;
				}

				// 自己加自己
				if ((mem_no.toUpperCase()).equals(friend_no.toUpperCase())) {
					errorMsgs.add("不可以自己加自己");
					req.setAttribute("frd_listVO", frd_listVO); // 含有輸入格式錯誤的frd_listVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/frd_list/addFrd_list.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Frd_listService frd_listSvc = new Frd_listService();
				System.out.println("198= ");
				//兩人沒有關係，所以getFriend_chk().isEmpty()是真，則用1, 0模式
				System.out.println("2= " +frd_listSvc.findByTwoPrimaryKey(mem_no.toUpperCase(), friend_no.toUpperCase())==null);
				
				if (frd_listSvc.findByTwoPrimaryKey(mem_no.toUpperCase(), friend_no.toUpperCase())==null) {
					frd_listVO = frd_listSvc.addFrd_list(mem_no.toUpperCase(), friend_no.toUpperCase(), "1");
					frd_listSvc.addFrd_list(friend_no.toUpperCase(), mem_no.toUpperCase(), "0");
				}else if(frd_listSvc.findByTwoPrimaryKey(mem_no.toUpperCase(), friend_no.toUpperCase()).getFriend_chk().equals("0")){
					frd_listSvc.updateFrd_list(mem_no.toUpperCase(), friend_no.toUpperCase(), "2");//新增一個申請中的人，兩人則變成好友
					frd_listSvc.updateFrd_list(friend_no.toUpperCase(), mem_no.toUpperCase(), "2");
				}else if(frd_listSvc.findByTwoPrimaryKey(mem_no.toUpperCase(), friend_no.toUpperCase()).getFriend_chk().equals("1")){
					errorMsgs.add("你已提出交友邀請");
				}else if(frd_listSvc.findByTwoPrimaryKey(mem_no.toUpperCase(), friend_no.toUpperCase()).getFriend_chk().equals("2")){
					errorMsgs.add("你們已是好友");
				}

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/front-end/frd_list/listAllFrd_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/frd_list/addFrd_list.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String mem_no = new String(req.getParameter("mem_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				Frd_listService frd_listSvc = new Frd_listService();
				frd_listSvc.deleteFrd_list(mem_no.toUpperCase());

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/front-end/frd_list/listAllFrd_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/frd_list/listAllFrd_list.jsp");
				failureView.forward(req, res);
			}
		}

		if ("deleteOne".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String mem_no = new String(req.getParameter("mem_no"));
				String friend_no = new String(req.getParameter("friend_no"));

				Frd_listVO frd_listVO = new Frd_listVO();
				frd_listVO.setMem_no(mem_no.toUpperCase());
				frd_listVO.setFriend_no(friend_no.toUpperCase());

				/*************************** 2.開始刪除資料 ***************************************/
				Frd_listService frd_listSvc = new Frd_listService();
				Frd_listVO frd_listVOtmp = new Frd_listVO();
				
				frd_listVOtmp.setMem_no(frd_listVO.getFriend_no());
				frd_listVOtmp.setFriend_no(frd_listVO.getMem_no());
				
				frd_listSvc.deleteOneFrd_list(frd_listVO);
				frd_listSvc.deleteOneFrd_list(frd_listVOtmp);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/front-end/frd_list/listAllFrd_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/frd_list/listAllFrd_list.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String mem_no = new String(req.getParameter("mem_no"));
				String friend_no = new String(req.getParameter("friend_no"));

				Frd_listVO frd_listVO = new Frd_listVO();
				frd_listVO.setFriend_no(friend_no);
				frd_listVO.setMem_no(mem_no);

				// 自己加自己
				if ((mem_no.toUpperCase()).equals(friend_no.toUpperCase())) {
					req.setAttribute("frd_listVO", frd_listVO); // 含有輸入格式錯誤的frd_listVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/frd_list/update_frd_list_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始查詢資料 ****************************************/
				Frd_listService frd_listSvc = new Frd_listService();

				// dao.findByTwoPrimaryKey(mem_no, friend_no);
				frd_listVO = frd_listSvc.findByTwoPrimaryKey(mem_no.toUpperCase(), friend_no.toUpperCase());

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("frd_listVO", frd_listVO); // 資料庫取出的frd_listVO物件,存入req
				String url = "/front-end/frd_list/update_frd_list_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_frd_list_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/frd_list/listAllFrd_list.jsp");
				failureView.forward(req, res);
			}
		}
		
		//拒絕=刪除加好友
		if ("updateDeny".equals(action)) { // 來自update_frd_list_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String mem_no = new String(req.getParameter("mem_no").trim());
				String friend_no = req.getParameter("friend_no").trim();
				String friend_chk = req.getParameter("friend_chk").trim();

				Frd_listVO frd_listVO = new Frd_listVO();
				frd_listVO.setMem_no(mem_no);
				frd_listVO.setFriend_no(friend_no);
				frd_listVO.setFriend_chk(friend_chk);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("frd_listVO", frd_listVO); // 含有輸入格式錯誤的frd_listVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/frd_list/update_frd_list_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				// 自己加自己
				if ((mem_no.toUpperCase()).equals(friend_no.toUpperCase())) {
					req.setAttribute("frd_listVO", frd_listVO); // 含有輸入格式錯誤的frd_listVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/frd_list/update_frd_list_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				Frd_listService frd_listSvc = new Frd_listService();
				Frd_listVO frd_listVOtmp = new Frd_listVO();
				
				frd_listVOtmp.setMem_no(frd_listVO.getFriend_no());
				frd_listVOtmp.setFriend_no(frd_listVO.getMem_no());
				
				frd_listSvc.deleteOneFrd_list(frd_listVO);
				frd_listSvc.deleteOneFrd_list(frd_listVOtmp);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("frd_listVO", frd_listVO); // 資料庫update成功後,正確的的frd_listVO物件,存入req
				String url = "/front-end/frd_list/listOneFrd_list.jsp";
				// String url = "/front-end/frd_list/listAllFrd_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/frd_list/update_frd_list_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		//同意加好友
		if ("updateAgree".equals(action)) { // 來自update_frd_list_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String mem_no = new String(req.getParameter("mem_no").trim());
				String friend_no = req.getParameter("friend_no").trim();
				String friend_chk = req.getParameter("friend_chk").trim();

				Frd_listVO frd_listVO = new Frd_listVO();
				frd_listVO.setMem_no(mem_no);
				frd_listVO.setFriend_no(friend_no);
				frd_listVO.setFriend_chk(friend_chk);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("frd_listVO", frd_listVO); // 含有輸入格式錯誤的frd_listVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/frd_list/update_frd_list_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				// 自己加自己
				if ((mem_no.toUpperCase()).equals(friend_no.toUpperCase())) {
					req.setAttribute("frd_listVO", frd_listVO); // 含有輸入格式錯誤的frd_listVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/frd_list/update_frd_list_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				Frd_listService frd_listSvc = new Frd_listService();
				frd_listVO = frd_listSvc.updateFrd_list(mem_no.toUpperCase(), friend_no.toUpperCase(), "2");
				frd_listVO = frd_listSvc.updateFrd_list(friend_no.toUpperCase(), mem_no.toUpperCase(), "2");

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("frd_listVO", frd_listVO); // 資料庫update成功後,正確的的frd_listVO物件,存入req
				String url = "/front-end/frd_list/listOneFrd_list.jsp";
				// String url = "/front-end/frd_list/listAllFrd_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/frd_list/update_frd_list_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_frd_list_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String mem_no = new String(req.getParameter("mem_no").trim());
				String friend_no = req.getParameter("friend_no").trim();
				String friend_chk = req.getParameter("friend_chk").trim();

				Frd_listVO frd_listVO = new Frd_listVO();
				frd_listVO.setMem_no(mem_no);
				frd_listVO.setFriend_no(friend_no);
				frd_listVO.setFriend_chk(friend_chk);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("frd_listVO", frd_listVO); // 含有輸入格式錯誤的frd_listVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/frd_list/update_frd_list_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				// 自己加自己
				if ((mem_no.toUpperCase()).equals(friend_no.toUpperCase())) {
					req.setAttribute("frd_listVO", frd_listVO); // 含有輸入格式錯誤的frd_listVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/frd_list/update_frd_list_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				Frd_listService frd_listSvc = new Frd_listService();
				frd_listVO = frd_listSvc.updateFrd_list(mem_no.toUpperCase(), friend_no.toUpperCase(), friend_chk);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("frd_listVO", frd_listVO); // 資料庫update成功後,正確的的frd_listVO物件,存入req
				String url = "/front-end/frd_list/listOneFrd_list.jsp";
				// String url = "/front-end/frd_list/listAllFrd_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/frd_list/update_frd_list_input.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
