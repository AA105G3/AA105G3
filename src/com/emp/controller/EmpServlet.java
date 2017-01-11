package com.emp.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.util.MailService;

/**
 * Servlet implementation class EmpServlet
 */

public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("emp_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String emp_no = null;
				try {
					emp_no = str;
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_no);
				if (empVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String emp_no = req.getParameter("emp_no").trim();
				
				/***************************2.開始查詢資料****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(emp_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/emp/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String requestURL = req.getParameter("requestURL");
				
				String emp_no = req.getParameter("emp_no").trim();
				String emp_name = req.getParameter("emp_name").trim();
				String emp_id = req.getParameter("emp_id").trim();
				String emp_email = req.getParameter("emp_email").trim();
				String emp_address = req.getParameter("emp_address").trim();
				String emp_phone = req.getParameter("emp_phone").trim();
				String emp_status = req.getParameter("emp_status").trim();
				
				String emp_job = req.getParameter("emp_job").trim();				
				
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			    String hireDate = req.getParameter("emp_hiredate").trim();
			    
			    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			    java.util.Date date = sdf.parse(hireDate);
			    hireDate = sdf2.format(date);
				java.sql.Date emp_hiredate = null;
				try {
					emp_hiredate = java.sql.Date.valueOf(hireDate);
				} catch (IllegalArgumentException e) {
					emp_hiredate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				
				
				EmpVO empVO = new EmpVO();
				empVO.setEmp_no(emp_no);
				empVO.setEmp_name(emp_name);
				empVO.setEmp_id(emp_id);
				empVO.setEmp_email(emp_email);
				empVO.setEmp_address(emp_address);
				empVO.setEmp_phone(emp_phone);
				empVO.setEmp_hiredate(emp_hiredate);
				empVO.setEmp_job(emp_job);
				empVO.setEmp_status(emp_status);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				EmpService empSvc = new EmpService();
				EmpVO dbempVO = empSvc.getOneEmp(emp_no);
				empVO = empSvc.updateEmp(emp_no,emp_name,dbempVO.getEmp_account(),dbempVO.getEmp_password(),emp_id,emp_email,
						emp_address,emp_phone,emp_hiredate,emp_job,emp_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
				
				if(requestURL.equals("/back-end/emp/EmpList.jsp")){
					String url = "/back-end/emp/EmpList.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);
					
				}
				if(requestURL.equals("/back-end/emp/EmpHasLeft.jsp")){
					String url = "/back-end/emp/EmpHasLeft.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);
					
				}

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/EmpList.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

				String emp_name = req.getParameter("emp_name").trim();
				String emp_account = req.getParameter("emp_account").trim();
				String emp_password = getPassWord();
				String emp_id = req.getParameter("emp_id").trim();
				String emp_email = req.getParameter("emp_email").trim();
				String emp_address = req.getParameter("emp_address").trim();
				String emp_phone = req.getParameter("emp_phone").trim();
				
				String emp_job = req.getParameter("emp_job").trim();
				
			    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			    String hireDate = req.getParameter("emp_hiredate").trim();
			    
			    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			    java.util.Date date = sdf.parse(hireDate);
			    hireDate = sdf2.format(date);
				java.sql.Date emp_hiredate = null;
				try {
					emp_hiredate = java.sql.Date.valueOf(hireDate);
				} catch (IllegalArgumentException e) {
					emp_hiredate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				

				EmpVO empVO = new EmpVO();
				empVO.setEmp_name(emp_name);
				empVO.setEmp_account(emp_account);
				empVO.setEmp_password(emp_password);
				empVO.setEmp_id(emp_id);
				empVO.setEmp_email(emp_email);
				empVO.setEmp_address(emp_address);
				empVO.setEmp_phone(emp_phone);
				empVO.setEmp_hiredate(emp_hiredate);
				empVO.setEmp_job(emp_job);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.addEmp(emp_name,emp_account,emp_password,emp_id,emp_email,emp_address
						,emp_phone,emp_hiredate,emp_job);
				/*寄出email*/
				  String to = emp_email;
				  String ch_name = emp_name;
			      String subject = "新進員工"+emp_name+"密碼通知";
			      
			      String passRandom = emp_password;
			      String messageText =  ch_name+" 您好。 " +"\n"+" 請謹記此帳號: " + emp_account + "\n" +
			      " 請謹記此密碼: " + passRandom + "\n" +" (已經啟用)"; 
			       
			      MailService mailService = new MailService();
			      mailService.sendMail(to, subject, messageText);
				
				
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/emp/EmpList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/EmpList.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String emp_no = req.getParameter("emp_no").trim();
				
				/***************************2.開始刪除資料***************************************/
				EmpService empSvc = new EmpService();
				empSvc.deleteEmp(emp_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("signOut".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				HttpSession session = req.getSession();
				session.removeAttribute("emp_no");
				session.removeAttribute("emp_account");
				session.removeAttribute("emp_password");
				session.removeAttribute("emp_auths");
				
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Login/Blogin.jsp");
				failureView.forward(req, res);
				
				
			} catch (Exception e) {

			}
		}
		
		
		
	}
	
	protected String getPassWord() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuffer salt = new StringBuffer();
        Random rnd = new Random();
        while (salt.length() < 10) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
	

}
