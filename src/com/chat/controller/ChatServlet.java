package com.chat.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.chat.model.*;

public class ChatServlet extends HttpServlet {
	
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
				String str = req.getParameter("chat_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訊息編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chat/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String chat_no = null;
				try {
					chat_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("訊息編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chat/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ChatService chatSvc = new ChatService();
				ChatVO chatVO = chatSvc.getOneChat(chat_no);
				if (chatVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chat/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("chatVO", chatVO); // 資料庫取出的chatVO物件,存入req
				String url = "/front-end/chat/listOneChat.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneChat.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chat/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // 來自addChat.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String send_no = req.getParameter("send_no").trim();
				if(send_no == ""){
					errorMsgs.add("請輸入發送人編號.");
				}
				
				String get_no = req.getParameter("get_no").trim();
				if(get_no == ""){
					errorMsgs.add("請輸入接收人編號.");
				}
				
				String chat_text = req.getParameter("chat_text").trim();
				if(chat_text == ""){
					errorMsgs.add("請輸入訊息內容.");
				}

				ChatVO chatVO = new ChatVO();
				chatVO.setSend_no(send_no);
				chatVO.setGet_no(get_no);
				chatVO.setChat_text(chat_text);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("chatVO", chatVO); // 含有輸入格式錯誤的chatVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chat/addChat.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ChatService chatSvc = new ChatService();
				chatVO = chatSvc.addChat(send_no, get_no, chat_text);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/chat/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listOneChat.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chat/addChat.jsp");
				failureView.forward(req, res);
			}
		}
			
		if ("delete".equals(action)) { // 來自listOneChat.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String chat_no = new String(req.getParameter("chat_no"));
				
				/***************************2.開始刪除資料***************************************/
				ChatService chatSvc = new ChatService();
				chatSvc.deleteChat(chat_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/chat/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chat/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
	}

}
