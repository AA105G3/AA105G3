package com.member.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.member.model.*;
import com.product_order.model.MailService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class MemberServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("contact".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			MailService mailSvc = new MailService();
			String usermail = "aa105g3foodtime@gmail.com";
			String subject = null;
			String mailcontext = null;
			String mail = null;
			String name = null;
			String subject2 = null;
			
			try {
				mail = req.getParameter("email").trim();
				name = req.getParameter("name").trim();
				subject2 = req.getParameter("subject").trim();
				
				subject = "聯絡客服通知：" + subject2;
				mailcontext = "來自	" + name + "(" + mail + ")" + "	的聯絡\n內容如下：\n\n" + req.getParameter("message");
				
				mailSvc.sendMail(usermail, subject, mailcontext);
				
				String url = "/front-end/ContactUsSuccess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				/*RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/update_member_input.jsp");*/
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/ContactUs.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("forgetPW".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			MailService mailSvc = new MailService();
			String usermail = req.getParameter("mem_email");

			String subject = "分享食光會員帳號忘記密碼通知";
			String mailcontext = null;
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_pw = "";
				String mem_email = req.getParameter("mem_email").trim();
				
				MemberVO memberVO = new MemberVO();
				memberVO.setMem_pw(mem_pw);
				memberVO.setMem_email(mem_email);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					/*RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/update_member_input.jsp");*/
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Login/Flogin.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				int index;
				
				String[] newPwArr = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j",
						"k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I",
						"J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
				
				for(int i=0 ;i<6 ;i++){
					index =(int) Math.floor((Math.random() * 62));
					mem_pw+=newPwArr[index];
				}

				MemberVO memberVO2 = new MemberVO();
				memberVO2.setMem_pw(mem_pw);
				memberVO2.setMem_email(mem_email);
				
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.updateMemPw(memberVO2);
				
				mailcontext = "親愛的會員您好，請使用以下的密碼登入您的帳號\n\n" + mem_pw + "\n\n並於登入後至您的會員個人頁面修改密碼，感謝您的配合！";
				mailSvc.sendMail(usermail, subject, mailcontext);

				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // 資料庫update成功後,正確的的memberVO物件,存入req
				String url = "/front-end/member/MemberForgetPasswordSuccess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				/*RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/update_member_input.jsp");*/
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Login/Flogin.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("validation".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_own = req.getParameter("mem_own").trim();
				String mem_email = req.getParameter("mem_email").trim();

				MemberVO memberVO = new MemberVO();
				memberVO.setMem_own(mem_own);
				memberVO.setMem_email(mem_email);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					/*RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/update_member_input.jsp");*/
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Login/Flogin.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.updateMemOwn("0",mem_email);

				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // 資料庫update成功後,正確的的memberVO物件,存入req
				String url = "/Login/Flogin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				/*RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/update_member_input.jsp");*/
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Login/Flogin.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if ("signOut".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				MemberService memberSvc = new MemberService();
						
				HttpSession session = req.getSession();
				String mem_no= (String)session.getAttribute("mem_no");
				MemberVO memberVO1 =memberSvc.getOneMember(mem_no);
				MemberVO memberVO = memberSvc.updateMember(mem_no, memberVO1.getMem_name(), memberVO1.getMem_ac(),
						memberVO1.getMem_pw(), memberVO1.getMem_image(), memberVO1.getMem_sex(), memberVO1.getMem_phone()
						, memberVO1.getMem_email(), memberVO1.getMem_adrs(), memberVO1.getMem_own(), memberVO1.getMem_history(), "0");
				session.removeAttribute("mem_no");
				session.removeAttribute("mem_ac");
				session.removeAttribute("mem_name");
				
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/index.jsp");
				failureView.forward(req, res);
				
				
			} catch (Exception e) {

			}
		}
		
		if ("getOne_For_Display".equals(action) || "getOne_For_List".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("mem_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String mem_no = null;
				try {
					mem_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mem_no);
				if (memberVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的memberVO物件,存入req
				
				String url = null;
				if ("getOne_For_Display".equals(action))
					url = "/front-end/member/listOneMember.jsp";
				else if ("getOne_For_List".equals(action))
					url = "/front-end/product_order/AddProductOrder.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllMember.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String mem_no = new String(req.getParameter("mem_no"));
				
				/***************************2.開始查詢資料****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mem_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memberVO", memberVO);         // 資料庫取出的memberVO物件,存入req
				/*String url = "/front-end/member/update_member_input.jsp";*/
				String url = "/front-end/member/UpdateMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_member_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_no = req.getParameter("mem_no").trim();
				if(mem_no == ""){
					errorMsgs.add("請輸入會員編號.");
				}
				
				String mem_name = req.getParameter("mem_name").trim();
				if(mem_name == ""){
					errorMsgs.add("請輸入會員暱稱.");
				}
				
				String mem_ac = req.getParameter("mem_ac").trim();
				if(mem_ac == ""){
					errorMsgs.add("請輸入會員帳號.");
				}
				
				String mem_pw = req.getParameter("mem_pw").trim();
				if(mem_pw == ""){
					errorMsgs.add("請輸入會員密碼.");
				}
				
				MemberService memberSvc = new MemberService();
				
				Part part = req.getPart("mem_image");
				byte[] mem_image = null;
				if(getFileNameFromPart(part) != null && part.getContentType() != null){
					InputStream in = part.getInputStream();
					mem_image = new byte[in.available()];
					in.read(mem_image);
					in.close();
				}
				else{
					mem_image = memberSvc.getOneMember(mem_no).getMem_image();
				}
				
				String mem_sex = null;
				try {
					mem_sex = new String(req.getParameter("mem_sex").trim());
				} catch (NullPointerException e) {
					mem_sex = "";
					errorMsgs.add("請選擇性別.");
				}
				
				String mem_phone = req.getParameter("mem_phone").trim();
				if(mem_phone == ""){
					errorMsgs.add("請輸入手機.");
				}
				
				String mem_email = req.getParameter("mem_email").trim();
				if(mem_email == ""){
					errorMsgs.add("請輸入電子郵件.");
				}
				
				String mem_adrs = req.getParameter("mem_adrs").trim();
				if(mem_adrs == ""){
					errorMsgs.add("請輸入地址.");
				}
				
				String mem_own = req.getParameter("mem_own").trim();
				String mem_history = req.getParameter("mem_history").trim();
				String mem_online = req.getParameter("mem_online").trim();

				MemberVO memberVO = new MemberVO();
				memberVO.setMem_no(mem_no);
				memberVO.setMem_name(mem_name);
				memberVO.setMem_ac(mem_ac);
				memberVO.setMem_pw(mem_pw);
				memberVO.setMem_image(mem_image);
				memberVO.setMem_sex(mem_sex);
				memberVO.setMem_phone(mem_phone);
				memberVO.setMem_email(mem_email);
				memberVO.setMem_adrs(mem_adrs);
				memberVO.setMem_own(mem_own);
				memberVO.setMem_history(mem_history);
				memberVO.setMem_online(mem_online);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					/*RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/update_member_input.jsp");*/
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/UpdateMember.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				//MemberService memberSvc = new MemberService();
				memberVO = memberSvc.updateMember(mem_no, mem_name, mem_ac, mem_pw, mem_image, mem_sex, mem_phone, mem_email, 
						mem_adrs, mem_own, mem_history, mem_online);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // 資料庫update成功後,正確的的memberVO物件,存入req
				String url = "/front-end/member/memberInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				/*RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/update_member_input.jsp");*/
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/UpdateMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // 來自addMember.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			MailService mailSvc = new MailService();
			String usermail = req.getParameter("mem_email");
			String subject = "分享食光會員帳號驗證通知";
			String mailcontext = null;

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_name = req.getParameter("mem_name").trim();
				if(mem_name == ""){
					errorMsgs.add("請輸入會員暱稱.");
				}
				
				String mem_ac = req.getParameter("mem_ac").trim();
				if(mem_ac == ""){
					errorMsgs.add("請輸入會員帳號.");
				}
				
				String mem_pw = req.getParameter("mem_pw").trim();
				if(mem_pw == ""){
					errorMsgs.add("請輸入會員密碼.");
				}
				if(!mem_pw.matches("^[A-Za-z0-9]{6,20}$")){
					errorMsgs.add("密碼格式不正確.");
				}
				
				Part part = req.getPart("mem_image");
				byte[] mem_image = null;
				if(getFileNameFromPart(part) != null && part.getContentType() != null){
					InputStream in = part.getInputStream();
					mem_image = new byte[in.available()];
					in.read(mem_image);
					in.close();
				}
				else{
					InputStream in = getServletContext().getResourceAsStream("/front-end/member/images/No-image-found.png");
					mem_image = new byte[in.available()];
					in.read(mem_image);
					in.close();
				}
				
				String mem_sex = null;
				try {
					mem_sex = new String(req.getParameter("mem_sex").trim());
				} catch (NullPointerException e) {
					mem_sex = "";
					errorMsgs.add("請選擇性別.");
				}
				
				String mem_phone = req.getParameter("mem_phone").trim();
				if(mem_phone == ""){
					errorMsgs.add("請輸入手機.");
				}
				if(!mem_phone.matches("^\\d{10}$")){
					errorMsgs.add("手機格式不正確.");
				}
				
				String mem_email = req.getParameter("mem_email").trim();
				if(mem_email == ""){
					errorMsgs.add("請輸入電子郵件.");
				}
				if(!mem_email.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")){
					errorMsgs.add("電子郵件格式不正確.");
				}
				
				String mem_adrs = req.getParameter("mem_adrs").trim();
				if(mem_adrs == ""){
					errorMsgs.add("請輸入地址.");
				}
				
				String mem_own = req.getParameter("mem_own").trim();
				String mem_history = req.getParameter("mem_history").trim();
				String mem_online = req.getParameter("mem_online").trim();

				MemberVO memberVO = new MemberVO();				
				memberVO.setMem_name(mem_name);
				memberVO.setMem_ac(mem_ac);
				memberVO.setMem_pw(mem_pw);
				memberVO.setMem_sex(mem_sex);
				memberVO.setMem_phone(mem_phone);
				memberVO.setMem_email(mem_email);
				memberVO.setMem_adrs(mem_adrs);
				memberVO.setMem_own(mem_own);
				memberVO.setMem_history(mem_history);
				memberVO.setMem_online(mem_online);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/MemberSignUp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MemberService memberSvc = new MemberService();
				
				MemberVO memberVO1 = memberSvc.getOneByMem_ac(mem_ac);
				if (memberVO1 != null) {
					errorMsgs.add("帳號重複");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/MemberSignUp.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				memberVO = memberSvc.addMember(mem_name, mem_ac, mem_pw, mem_image, mem_sex, mem_phone, mem_email, 
						mem_adrs, mem_own, mem_history, mem_online);
				
				HttpSession session = req.getSession();
				String sessionID = session.getId();
				StringBuffer getRequestURL = req.getRequestURL();
				
				String loginURL = getRequestURL + "?action=validation" 
				+ "&mem_own=0"
				+ "&mem_email=" + mem_email 
				+ "&sessionID=" + sessionID;
				
				mailcontext = mem_name + " 您好，感謝您在分享食光中的註冊，請點擊下列驗證碼完成帳號驗證\n" + loginURL;
				mailSvc.sendMail(usermail, subject, mailcontext);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/member/MemberSignUpSuccess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMember.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/MemberSignUp.jsp");
				failureView.forward(req, res);
			}
		}
			
		if ("delete".equals(action)) { // 來自listAllMember.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String mem_no = new String(req.getParameter("mem_no"));
				
				/***************************2.開始刪除資料***************************************/
				MemberService memberSvc = new MemberService();
				memberSvc.deleteMember(mem_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getMemberInfo".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String mem_no = req.getParameter("mem_no");
				
				/***************************2.開始查詢資料*****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mem_no);
				memberVO.setMem_email((memberVO.getMem_email()).toLowerCase());
				
				if (memberVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的memberVO物件,存入req
				
				String url = null;
				
					url = "/front-end/member/memberInfo.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
	}
	
	public String getFileNameFromPart(Part part){
		String header = part.getHeader("content-disposition");
		String fileName = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if(fileName.length() == 0){
			return null;
		}
		return fileName;
	}

}
