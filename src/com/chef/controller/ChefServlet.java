package com.chef.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.chef.model.*;
import com.member.model.*;
import com.util.MailService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10000 * 1024 * 1024, maxRequestSize = 1000 * 10000 * 1024 * 1024)
public class ChefServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)||"getOne_For_Back".equals(action)) { // 來自select_page.jsp的請求

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
							.getRequestDispatcher("/front-end/chef/select_page.jsp");
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
							.getRequestDispatcher("/front-end/chef/select_page.jsp");
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
							.getRequestDispatcher("/front-end/chef/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("chefVO", chefVO); // 資料庫取出的chefVO物件,存入req			
//				String url = "/front-end/chef/chefInfo.jsp";
				String url = null;
				if("getOne_For_Display".equals(action))
					url = "/front-end/chef/chefInfo.jsp";
				else if("getOne_For_Back".equals(action))
					url = "/back-end/chef/ChefInfoPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_chef_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getName_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("chef_name");			
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入私廚姓名");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chef/chefList.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String chef_name = null;
				try {
					chef_name = new String(str);
				} catch (Exception e) {
					errorMsgs.add("私廚編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chef/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}				
				/***************************2.開始查詢資料*****************************************/
				ChefService chefSvc = new ChefService();				
				ChefVO chefVO = chefSvc.getChef_name(chef_name);
				if (chefVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chef/chefList.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("chefVO", chefVO); // 資料庫取出的chefVO物件,存入req
				String url = "/front-end/chef/chefInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneChef.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef/chefList.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("update_chk_cond".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String chef_no = req.getParameter("chef_no");
				String chef_chk_cond = req.getParameter("chef_chk_cond");
				System.out.println(chef_chk_cond);
				
				
				/***************************2.開始查詢資料*****************************************/
				ChefService chefSvc = new ChefService();
				ChefVO chefVO = chefSvc.getOneChef(chef_no);
				if (chefVO == null) {
					errorMsgs.add("查無資料");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chef/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				chefVO.setChef_chk_cond(chef_chk_cond);
				
				chefSvc.updateChef(chef_no, chefVO.getChef_bnk(), chefVO.getChef_bnk_ac(), chefVO.getChef_skill(), chefVO.getChef_lic(), chefVO.getChef_image(), chefVO.getChef_movie1(), chefVO.getChef_movie2(), chefVO.getChef_id(), chefVO.getChef_name(), chefVO.getChef_area(), chefVO.getChef_intr(), chefVO.getChef_menu(), chefVO.getChef_reci_image1(), chefVO.getChef_reci_image2(), chefVO.getChef_reci_image3(), chefVO.getChef_reci_image4(), chefVO.getChef_reci_image5(),chef_chk_cond);
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(chefVO.getMem_no());
				
				
				String to = memberVO.getMem_email();
				String subject = "私廚審核通知";
				String messageText = chefVO.getChef_name()+"您好， 恭喜您私廚申請審核已通過。";
				
				MailService mailService = new MailService();
			    mailService.sendMail(to, subject, messageText);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("chefVO", chefVO); // 資料庫取出的chefVO物件,存入req
				String url = "/back-end/chef/ChefCheckListPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneChef.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action) || "getOne_For_Check".equals(action)) { // 來自listAllChef.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			String requestURL = req.getParameter("requestURL");
			
			try {
				/***************************1.接收請求參數****************************************/
				String chef_no = new String(req.getParameter("chef_no"));
				
				/***************************2.開始查詢資料****************************************/
				ChefService chefSvc = new ChefService();
				ChefVO chefVO = chefSvc.getOneChef(chef_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("chefVO", chefVO);         // 資料庫取出的chefVO物件,存入req					
				String url = null;
				if("getOne_For_Update".equals(action))
					url = "/front-end/chef/changeChefInfo.jsp";
				else if("getOne_For_Check".equals(action))
					url = "/back-end/chef/ChefCheckPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_chef_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef/listAllChef.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_chef_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String chef_no = new String(req.getParameter("chef_no").trim());
				
				String chef_bnk = req.getParameter("chef_bnk").trim();
				if(chef_bnk == ""){
					errorMsgs.add("請輸入私廚匯款帳戶銀行代碼.");
				}
				
				String chef_bnk_ac = req.getParameter("chef_bnk_ac").trim();
				if(chef_bnk_ac == ""){
					errorMsgs.add("請輸入私廚匯款帳號.");
				}
				
				String chef_skill = req.getParameter("chef_skill").trim();
				if(chef_skill == ""){
					errorMsgs.add("請輸入私廚專長.");
				}
				
				ChefService chefSvc = new ChefService();
				
				Part lic_part=req.getPart("chef_lic");
				byte[] chef_lic=null;
				if(getFileNameFromPart(lic_part) != null && lic_part.getContentType() != null){
					InputStream lic_in= lic_part.getInputStream();
					chef_lic=new byte[lic_in.available()];
					lic_in.read(chef_lic);
					lic_in.close();
				}
				else{
					chef_lic = chefSvc.getOneChef(chef_no).getChef_lic();
				}
				
				Part c_img_part=req.getPart("chef_image");
				byte[] chef_image=null;
				if(getFileNameFromPart2(c_img_part) != null && c_img_part.getContentType() != null){
					InputStream c_img_in= c_img_part.getInputStream();
					chef_image=new byte[c_img_in.available()];
					c_img_in.read(chef_image);
					c_img_in.close();
				}
				else{
					chef_image = chefSvc.getOneChef(chef_no).getChef_image();
				}				
				
				Part mov1_part=req.getPart("chef_movie1");
				byte[] chef_movie1=null;
				if(getFileNameFromPart3(mov1_part) != null && mov1_part.getContentType() != null){
					
					InputStream mov1_in= mov1_part.getInputStream();
					chef_movie1=new byte[mov1_in.available()];
					mov1_in.read(chef_movie1);
					mov1_in.close();
				}
				else{
					chef_movie1 = chefSvc.getOneChef(chef_no).getChef_movie1();
				}
				
				Part mov2_part=req.getPart("chef_movie2");
				byte[] chef_movie2=null;
				if(getFileNameFromPart4(mov2_part) != null && mov2_part.getContentType() != null){
					InputStream mov2_in= mov2_part.getInputStream();
					chef_movie2=new byte[mov2_in.available()];
					mov2_in.read(chef_movie2);
					mov2_in.close();
				}
				else{
					chef_movie2 = chefSvc.getOneChef(chef_no).getChef_movie2();
				}
				
				
				String chef_id = req.getParameter("chef_id").trim();
				if(chef_id == ""){
					errorMsgs.add("請輸入身分證字號.");
				}
				
				String chef_name = req.getParameter("chef_name").trim();
				if(chef_name == ""){
					errorMsgs.add("請輸入姓名.");
				}
				
				String chef_area = req.getParameter("chef_area").trim();
				if(chef_area == ""){
					errorMsgs.add("請輸入活動地區.");
				}
				
				String chef_intr = req.getParameter("chef_intr").trim();
				if(chef_intr == ""){
					errorMsgs.add("請輸入簡介.");
				}
				
				String chef_menu = req.getParameter("chef_menu").trim();
				if(chef_menu == ""){
					errorMsgs.add("請輸入參考菜單.");
				}
				
				Part img1_part=req.getPart("chef_reci_image1");
				byte[] chef_reci_image1=null;
				if(getFileNameFromPart5(img1_part) != null && img1_part.getContentType() != null){
					InputStream img1_in= img1_part.getInputStream();
					chef_reci_image1=new byte[img1_in.available()];
					img1_in.read(chef_reci_image1);
					img1_in.close();
				}
				else{
					chef_reci_image1 = chefSvc.getOneChef(chef_no).getChef_reci_image1();
				}
				
				Part img2_part=req.getPart("chef_reci_image2");
				byte[] chef_reci_image2=null;
				if(getFileNameFromPart6(img2_part) != null && img2_part.getContentType() != null){
					InputStream img2_in= img2_part.getInputStream();
					chef_reci_image2=new byte[img2_in.available()];
					img2_in.read(chef_reci_image2);
					img2_in.close();
				}
				else{
					chef_reci_image2 = chefSvc.getOneChef(chef_no).getChef_reci_image2();
				}
				
				Part img3_part=req.getPart("chef_reci_image3");
				byte[] chef_reci_image3=null;
				if(getFileNameFromPart7(img3_part) != null && img3_part.getContentType() != null){
					InputStream img3_in= img3_part.getInputStream();
					chef_reci_image3=new byte[img3_in.available()];
					img3_in.read(chef_reci_image3);
					img3_in.close();
				}
				else{
					chef_reci_image3 = chefSvc.getOneChef(chef_no).getChef_reci_image3();
				}
				
				Part img4_part=req.getPart("chef_reci_image4");
				byte[] chef_reci_image4=null;
				if(getFileNameFromPart8(img4_part) != null && img4_part.getContentType() != null){
					InputStream img4_in= img4_part.getInputStream();
					chef_reci_image4=new byte[img4_in.available()];
					img4_in.read(chef_reci_image4);
					img4_in.close();
				}
				else{
					chef_reci_image4 = chefSvc.getOneChef(chef_no).getChef_reci_image4();
				}
				
				Part img5_part=req.getPart("chef_reci_image5");
				byte[] chef_reci_image5=null;
				if(getFileNameFromPart9(img5_part) != null && img5_part.getContentType() != null){
					InputStream img5_in= img5_part.getInputStream();
					chef_reci_image5=new byte[img5_in.available()];
					img5_in.read(chef_reci_image5);
					img5_in.close();
				}
				else{
					chef_reci_image5 = chefSvc.getOneChef(chef_no).getChef_reci_image5();
				}				

				ChefVO chefVO = chefSvc.getOneChef(chef_no);
				chefVO.setChef_bnk(chef_bnk);
				chefVO.setChef_bnk_ac(chef_bnk_ac);
				chefVO.setChef_skill(chef_skill);
				chefVO.setChef_lic(chef_lic);
				chefVO.setChef_image(chef_image);
				chefVO.setChef_movie1(chef_movie1);
				chefVO.setChef_movie2(chef_movie2);
				chefVO.setChef_id(chef_id);
				chefVO.setChef_name(chef_name);
				chefVO.setChef_area(chef_area);
				chefVO.setChef_intr(chef_intr);
				chefVO.setChef_menu(chef_menu);
				chefVO.setChef_reci_image1(chef_reci_image1);
				chefVO.setChef_reci_image2(chef_reci_image2);
				chefVO.setChef_reci_image3(chef_reci_image3);
				chefVO.setChef_reci_image4(chef_reci_image4);
				chefVO.setChef_reci_image5(chef_reci_image5);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("chefVO", chefVO); // 含有輸入格式錯誤的chefVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chef/update_chef_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				//ChefService chefSvc = new ChefService();
				chefVO = chefSvc.updateChef(chef_no, chef_bnk, chef_bnk_ac, chef_skill, chef_lic, chef_image, chef_movie1, chef_movie2, chef_id, chef_name, chef_area, chef_intr, chef_menu, chef_reci_image1, chef_reci_image2, chef_reci_image3, chef_reci_image4, chef_reci_image5,chefVO.getChef_chk_cond());
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("chefVO", chefVO); // 資料庫update成功後,正確的的chefVO物件,存入req
				String url = "/front-end/chef/chefInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneChef.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef/update_chef_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addChef.jsp的請求  
			
			Map<String,String> errorMsgs = new HashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				System.out.println("xxx");
				String mem_no = req.getParameter("mem_no").trim();
				
				String chef_bnk = req.getParameter("chef_bnk").trim();
				String chef_bnk_ac = req.getParameter("chef_bnk_ac").trim();
				if(chef_bnk.equals("") || chef_bnk_ac.equals("")){
					errorMsgs.put("bankError","請輸入銀行匯款帳戶");
				}
				
				
				String chef_skill = req.getParameter("chef_skill").trim();
				if(chef_skill.equals("")){
					errorMsgs.put("skillError","請填寫專長");
				}
				
				Part lic_part=req.getPart("chef_lic");
				byte[] chef_lic=null;
				if(getFileNameFromPart(lic_part) != null && lic_part.getContentType() != null){
					InputStream lic_in= lic_part.getInputStream();
					chef_lic=new byte[lic_in.available()];
					lic_in.read(chef_lic);
					lic_in.close();
				}
				else{
					errorMsgs.put("licError","請上傳證照");
				}
				
				Part c_img_part=req.getPart("chef_image");
				byte[] chef_image=null;
				if(getFileNameFromPart2(c_img_part) != null && c_img_part.getContentType() != null){
					String type = (c_img_part.getContentType()).substring(0,1);
					if(type.equals("i")){
						InputStream c_img_in= c_img_part.getInputStream();
						chef_image=new byte[c_img_in.available()];
						c_img_in.read(chef_image);
						c_img_in.close();
					}else{
						errorMsgs.put("chef_imgError","請上傳圖片格式");
					}
				}
				else{
					errorMsgs.put("chef_imgError","請上傳真實相片");
				}
				
				Part mov1_part=req.getPart("chef_movie1");
				byte[] chef_movie1=null;
				if(getFileNameFromPart3(mov1_part) != null && mov1_part.getContentType() != null){
					String type = (mov1_part.getContentType()).substring(0,1);
					if(type.equals("v")){
						InputStream mov1_in= mov1_part.getInputStream();
						chef_movie1=new byte[mov1_in.available()];
						mov1_in.read(chef_movie1);
						mov1_in.close();
					}else{
						errorMsgs.put("chef_movieError","請上傳影片");
					}
				}
				else{
					errorMsgs.put("chef_movieError","必須上傳兩部影片");
				}
				
				Part mov2_part=req.getPart("chef_movie2");
				byte[] chef_movie2=null;
				if(getFileNameFromPart4(mov2_part) != null && mov2_part.getContentType() != null){
					String type = (mov2_part.getContentType()).substring(0,1);
					if(type.equals("v")){
					InputStream mov2_in= mov2_part.getInputStream();
					chef_movie2=new byte[mov2_in.available()];
					mov2_in.read(chef_movie2);
					mov2_in.close();
					}else{
						errorMsgs.put("chef_movieError","請上傳影片");
					}
				}
				else{
					errorMsgs.put("chef_movieError","必須上傳兩部影片");
				}
				
				String chef_id = req.getParameter("chef_id").trim();
				if(chef_id.equals("") ){
					errorMsgs.put("idError","請輸入身分證字號");
				}else if(chef_id.substring(0, 1).matches("[a-zA-Z]")){
					errorMsgs.put("idError","格式錯誤");
				}else if(chef_id.length()<10)
				for(int i =1;i<10;i++){
					String num = chef_id.substring(i, i+1);
					try{
						int number = Integer.parseInt(num); 
					}catch(NumberFormatException ne){
						errorMsgs.put("idError","格式錯誤");
					}
				}
				
				String chef_name = req.getParameter("chef_name").trim();
				if(chef_name.equals("")){
					errorMsgs.put("nameError","請輸入姓名");
				}
				
				String chef_area = req.getParameter("chef_area").trim();
				
				
				String chef_intr = req.getParameter("chef_intr").trim();
				
				
				String chef_menu = req.getParameter("chef_menu").trim();
				
	
				Part img1_part=req.getPart("chef_reci_image1");
				byte[] chef_reci_image1=null;
				if(getFileNameFromPart5(img1_part) != null && img1_part.getContentType() != null){
					String type = (img1_part.getContentType()).substring(0,1);
					if(type.equals("i")){
					InputStream img1_in= img1_part.getInputStream();
					chef_reci_image1=new byte[img1_in.available()];
					img1_in.read(chef_reci_image1);
					img1_in.close();
					}else{
						errorMsgs.put("rec_imgError","請上傳圖片格式");
					}
				}
				else{
					errorMsgs.put("rec_imgError","至少上傳一張圖片");
				}
				
				Part img2_part=req.getPart("chef_reci_image2");
				byte[] chef_reci_image2=null;
				if(getFileNameFromPart6(img2_part) != null && img2_part.getContentType() != null){
					String type = (img1_part.getContentType()).substring(0,1);
					if(type.equals("i")){
					InputStream img2_in= img2_part.getInputStream();
					chef_reci_image2=new byte[img2_in.available()];
					img2_in.read(chef_reci_image2);
					img2_in.close();
					}else{
						errorMsgs.put("rec_imgError","請上傳圖片格式");
					}
				}
				else{
					chef_reci_image2 = null;
				}
				
				Part img3_part=req.getPart("chef_reci_image3");
				byte[] chef_reci_image3=null;
				if(getFileNameFromPart7(img3_part) != null && img3_part.getContentType() != null){
					String type = (img3_part.getContentType()).substring(0,1);
					if(type.equals("i")){
					InputStream img3_in= img3_part.getInputStream();
					chef_reci_image3=new byte[img3_in.available()];
					img3_in.read(chef_reci_image3);
					img3_in.close();
					}else{
						errorMsgs.put("rec_imgError","請上傳圖片格式");
					}
				}
				else{
					chef_reci_image3= null;
				}
				
				Part img4_part=req.getPart("chef_reci_image4");
				byte[] chef_reci_image4=null;
				if(getFileNameFromPart8(img4_part) != null && img4_part.getContentType() != null){
					String type = (img4_part.getContentType()).substring(0,1);
					if(type.equals("i")){
					InputStream img4_in= img4_part.getInputStream();
					chef_reci_image4=new byte[img4_in.available()];
					img4_in.read(chef_reci_image4);
					img4_in.close();
					}else{
						errorMsgs.put("rec_imgError","請上傳圖片格式");
					}
				}
				else{
					chef_reci_image4 = null;
				}
				
				Part img5_part=req.getPart("chef_reci_image5");
				byte[] chef_reci_image5=null;
				if(getFileNameFromPart9(img5_part) != null && img5_part.getContentType() != null){
					String type = (img5_part.getContentType()).substring(0,1);
					if(type.equals("i")){
					InputStream img5_in= img5_part.getInputStream();
					chef_reci_image5=new byte[img5_in.available()];
					img5_in.read(chef_reci_image5);
					img5_in.close();
					}else{
						errorMsgs.put("rec_imgError","請上傳圖片格式");
					}
				}
				else{
					chef_reci_image5 = null;
				}
				

				ChefVO chefVO = new ChefVO();
				chefVO.setMem_no(mem_no);
				chefVO.setChef_bnk(chef_bnk);
				chefVO.setChef_bnk_ac(chef_bnk_ac);
				chefVO.setChef_skill(chef_skill);
				chefVO.setChef_lic(chef_lic);
				chefVO.setChef_image(chef_image);
				chefVO.setChef_movie1(chef_movie1);
				chefVO.setChef_movie2(chef_movie2);
				chefVO.setChef_id(chef_id);
				chefVO.setChef_name(chef_name);
				chefVO.setChef_area(chef_area);
				chefVO.setChef_intr(chef_intr);
				chefVO.setChef_menu(chef_menu);
				chefVO.setChef_reci_image1(chef_reci_image1);
				chefVO.setChef_reci_image2(chef_reci_image2);
				chefVO.setChef_reci_image3(chef_reci_image3);
				chefVO.setChef_reci_image4(chef_reci_image4);
				chefVO.setChef_reci_image5(chef_reci_image5);

					System.out.println("xxxxxxxxxxxxxxxxxxx");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
//					System.out.println(errorMsgs.size()+"AAAAAAAAAAA");
					req.setAttribute("chefVO", chefVO); // 含有輸入格式錯誤的chefVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chef/becomeChef.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ChefService chefSvc = new ChefService();
				chefVO = chefSvc.addChef(mem_no, chef_bnk, chef_bnk_ac, chef_skill, chef_lic, chef_image, chef_movie1, chef_movie2, chef_id, chef_name, chef_area, chef_intr, chef_menu,chef_reci_image1, chef_reci_image2, chef_reci_image3, chef_reci_image4, chef_reci_image5);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("chefVO", chefVO);
				String url = "/front-end/chef/chefInfoAfAdd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllChef.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("error",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef/becomeChef.jsp");
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
				String chef_no = new String(req.getParameter("chef_no"));
				
				/***************************2.開始刪除資料***************************************/
				ChefService chefSvc = new ChefService();
				ChefVO chefVO = chefSvc.getOneChef(chef_no); 
				chefSvc.deleteChef(chef_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/chef/ChefCheckListPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/*寄送e-mail*/
				
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(chefVO.getMem_no());
				
				
				String to = memberVO.getMem_email();
				String subject = "私廚審核通知";
				String messageText = chefVO.getChef_name()+"您好， 很抱歉，您私廚申請審核未通過。";
				
				MailService mailService = new MailService();
			    mailService.sendMail(to, subject, messageText);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef/listAllChef.jsp");
				failureView.forward(req, res);
			}
		}
	}

	public String getFileNameFromPart(Part lic_part){
		String header = lic_part.getHeader("content-disposition");
		String fileName = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if(fileName.length() == 0){
			return null;
		}
		return fileName;
	}
	public String getFileNameFromPart2(Part c_img_part){
		String header = c_img_part.getHeader("content-disposition");
		String fileName = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if(fileName.length() == 0){
			return null;
		}
		return fileName;
	}
	public String getFileNameFromPart3(Part mov1_part){
		String header = mov1_part.getHeader("content-disposition");
		String fileName = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if(fileName.length() == 0){
			return null;
		}
		return fileName;
	}
	public String getFileNameFromPart4(Part mov2_part){
		String header = mov2_part.getHeader("content-disposition");
		String fileName = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if(fileName.length() == 0){
			return null;
		}
		return fileName;
	}
	public String getFileNameFromPart5(Part img1_part){
		String header = img1_part.getHeader("content-disposition");
		String fileName = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if(fileName.length() == 0){
			return null;
		}
		return fileName;
	}
	public String getFileNameFromPart6(Part img2_part){
		String header = img2_part.getHeader("content-disposition");
		String fileName = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if(fileName.length() == 0){
			return null;
		}
		return fileName;
	}
	public String getFileNameFromPart7(Part img3_part){
		String header = img3_part.getHeader("content-disposition");
		String fileName = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if(fileName.length() == 0){
			return null;
		}
		return fileName;
	}
	public String getFileNameFromPart8(Part img4_part){
		String header = img4_part.getHeader("content-disposition");
		String fileName = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if(fileName.length() == 0){
			return null;
		}
		return fileName;
	}
	public String getFileNameFromPart9(Part img5_part){
		String header = img5_part.getHeader("content-disposition");
		String fileName = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if(fileName.length() == 0){
			return null;
		}
		return fileName;
	}
}
