package com.product_order.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.product_order.model.*;
import com.product_order_list.model.Product_order_listService;
import com.product_order_list.model.Product_order_listVO;

public class Product_orderServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getPart_For_Display_By_One_PK".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String prod_ord_no = new String(req.getParameter("prod_ord_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				Product_orderService product_orderSvc = new Product_orderService();
				Set<Product_order_listVO> set = product_orderSvc.getProduct_order_list_By_One_PK(prod_ord_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listPOList_ByProd_ord_no", set);    // 資料庫取出的set物件,存入request

				/*String url = "/front-end/product_order/listPartProduct_order.jsp"; */             // 成功轉交 dept/listAllDept.jsp
				String url = "/front-end/product_order/ListProductOrder.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("prod_ord_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/product_order/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String prod_ord_no = null;
				try {
					prod_ord_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("商品訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/product_order/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Product_orderService product_orderSvc = new Product_orderService();
				Product_orderVO product_orderVO = product_orderSvc.getOneProduct_order(prod_ord_no);
				if (product_orderVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/product_order/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("product_orderVO", product_orderVO); // 資料庫取出的product_orderVO物件,存入req
				String url = "/front-end/product_order/listOneProduct_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProduct_order.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/product_order/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getPart_For_Display_By_Mem_No".equals(action)) { // 來自select_page.jsp的請求

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
							.getRequestDispatcher("/front-end/product_order/select_page.jsp");
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
							.getRequestDispatcher("/front-end/product_order/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Product_orderService product_orderSvc = new Product_orderService();
				List<Product_orderVO> product_orderVO = product_orderSvc.getProduct_order_By_Mem_no(mem_no);
				if (product_orderVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/product_order/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("product_orderVO", product_orderVO); // 資料庫取出的product_orderVO物件,存入req
				/*String url = "/front-end/product_order/listPartProduct_order.jsp";*/
				String url = "/front-end/product_order/ListProductOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProduct_order.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/product_order/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllProduct_order.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String prod_ord_no = new String(req.getParameter("prod_ord_no"));
				
				/***************************2.開始查詢資料****************************************/
				Product_orderService product_orderSvc = new Product_orderService();
				Product_orderVO product_orderVO = product_orderSvc.getOneProduct_order(prod_ord_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("product_orderVO", product_orderVO);         // 資料庫取出的product_orderVO物件,存入req
				/*String url = "/front-end/product_order/update_product_order_input.jsp";*/
				String url = "/front-end/product_order/UpdateProductOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_product_order_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/product_order/listAllProduct_order.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_product_order_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String prod_ord_no = req.getParameter("prod_ord_no").trim();
				if(prod_ord_no == ""){
					errorMsgs.add("請輸入商品訂單編號.");
				}
				
				String mem_no = req.getParameter("mem_no").trim();
				if(mem_no == ""){
					errorMsgs.add("請輸入會員編號.");
				}
				
				java.sql.Timestamp prod_ord_time = java.sql.Timestamp.valueOf(req.getParameter("prod_ord_time").trim());

				String cred_card_no = req.getParameter("cred_card_no").trim();
				if(cred_card_no == ""){
					errorMsgs.add("請輸入信用卡卡號.");
				}
				
				java.sql.Date valid_date = null;
				try {
					valid_date = java.sql.Date.valueOf(req.getParameter("valid_date").trim());
				} catch (IllegalArgumentException e) {
					valid_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入訂單成立時間!");
				}
				
				String valid_no = req.getParameter("valid_no").trim();
				if(valid_no == ""){
					errorMsgs.add("請輸入信用卡驗證碼.");
				}

				String cred_card_type = req.getParameter("cred_card_type").trim();
				if(cred_card_type == ""){
					errorMsgs.add("請輸入信用卡卡別.");
				}

				Integer total_money = null;
				try {
					total_money = new Integer(req.getParameter("total_money").trim());
				} catch (NumberFormatException e) {
					total_money = 0;
					errorMsgs.add("訂單總金額請填數字.");
				}

				String ship_name = req.getParameter("ship_name").trim();
				if(ship_name == ""){
					errorMsgs.add("請輸入收件人姓名.");
				}
				
				String post_code = req.getParameter("post_code").trim();
				if(post_code == ""){
					errorMsgs.add("請輸入郵遞區號.");
				}
				
				String mem_adrs = req.getParameter("mem_adrs").trim();
				if(mem_adrs == ""){
					errorMsgs.add("請輸入寄送地址.");
				}
				
				String cell_phone = req.getParameter("cell_phone").trim();
				if(cell_phone == ""){
					errorMsgs.add("請輸入聯絡手機.");
				}
				
				String tel_phone = req.getParameter("tel_phone").trim();
				if(tel_phone == ""){
					errorMsgs.add("請輸入聯絡市話.");
				}

				Product_orderVO product_orderVO = new Product_orderVO();
				product_orderVO.setProd_ord_no(prod_ord_no);
				product_orderVO.setMem_no(mem_no);
				product_orderVO.setProd_ord_time(prod_ord_time);
				product_orderVO.setCred_card_no(cred_card_no);
				product_orderVO.setValid_date(valid_date);
				product_orderVO.setValid_no(valid_no);
				product_orderVO.setCred_card_type(cred_card_type);
				product_orderVO.setTotal_money(total_money);
				product_orderVO.setShip_name(ship_name);
				product_orderVO.setPost_code(post_code);
				product_orderVO.setMem_adrs(mem_adrs);
				product_orderVO.setCell_phone(cell_phone);
				product_orderVO.setTel_phone(tel_phone);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_orderVO", product_orderVO); // 含有輸入格式錯誤的product_orderVO物件,也存入req
					/*RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/product_order/update_product_order_input.jsp");*/
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/product_order/UpdateProductOrder.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Product_orderService product_orderSvc = new Product_orderService();
				product_orderVO = product_orderSvc.updateProduct_order(prod_ord_no, mem_no, prod_ord_time, cred_card_no, valid_date, 
						valid_no, cred_card_type, total_money, ship_name, post_code, mem_adrs, cell_phone, tel_phone);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("product_orderVO", product_orderVO); // 資料庫update成功後,正確的的product_orderVO物件,存入req
				/*String url = "/front-end/product_order/listPartProduct_order.jsp";*/
				String url = "/front-end/product_order/ListProductOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneProduct_order.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/product_order/update_product_order_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addProduct_order.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_no = req.getParameter("mem_no").trim();
				if(mem_no == ""){
					errorMsgs.add("請輸入會員編號.");
				}
				
				String cred_card_no = req.getParameter("cred_card_no").trim();
				if(cred_card_no == ""){
					errorMsgs.add("請輸入信用卡卡號.");
				}
				
				/*java.sql.Date valid_date = null;
				try {
					valid_date = java.sql.Date.valueOf(req.getParameter("valid_date").trim());
				} catch (IllegalArgumentException e) {
					valid_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入信用卡有效時期!");
				}*/
				
				SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
				String validDate = req.getParameter("valid_date").trim();
				
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date = sdf.parse(validDate);
				validDate = sdf2.format(date);
				java.sql.Date valid_date = null;
				try {
					valid_date = java.sql.Date.valueOf(validDate);
				} catch (IllegalArgumentException e) {
					valid_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入信用卡有效時期!");
				}
				
				
				
				
				String valid_no = req.getParameter("valid_no").trim();
				if(valid_no == ""){
					errorMsgs.add("請輸入信用卡驗證碼.");
				}

				String cred_card_type = req.getParameter("cred_card_type").trim();
				if(cred_card_type == ""){
					errorMsgs.add("請輸入信用卡卡別.");
				}

				Integer total_money = null;
				try {
					total_money = new Integer(req.getParameter("total_money").trim());
				} catch (NumberFormatException e) {
					total_money = 0;
					errorMsgs.add("訂單總金額請填數字.");
				}

				String ship_name = req.getParameter("ship_name").trim();
				if(ship_name == ""){
					errorMsgs.add("請輸入收件人姓名.");
				}
				
				String post_code = req.getParameter("post_code").trim();
				if(post_code == ""){
					errorMsgs.add("請輸入郵遞區號.");
				}
				
				String mem_adrs = req.getParameter("mem_adrs").trim();
				if(mem_adrs == ""){
					errorMsgs.add("請輸入寄送地址.");
				}
				
				String cell_phone = req.getParameter("cell_phone").trim();
				if(cell_phone == ""){
					errorMsgs.add("請輸入聯絡手機.");
				}
				
				String tel_phone = req.getParameter("tel_phone").trim();
				if(tel_phone == ""){
					errorMsgs.add("請輸入聯絡市話.");
				}

				Product_orderVO product_orderVO = new Product_orderVO();
				product_orderVO.setMem_no(mem_no);
				product_orderVO.setCred_card_no(cred_card_no);
				product_orderVO.setValid_date(valid_date);
				product_orderVO.setValid_no(valid_no);
				product_orderVO.setCred_card_type(cred_card_type);
				product_orderVO.setTotal_money(total_money);
				product_orderVO.setShip_name(ship_name);
				product_orderVO.setPost_code(post_code);
				product_orderVO.setMem_adrs(mem_adrs);
				product_orderVO.setCell_phone(cell_phone);
				product_orderVO.setTel_phone(tel_phone);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_orderVO", product_orderVO); // 含有輸入格式錯誤的product_orderVO物件,也存入req
					/*RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/product_order/addProduct_order.jsp");*/
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/product_order/AddProductOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Product_orderService product_orderSvc = new Product_orderService();
				product_orderVO = product_orderSvc.addProduct_order(mem_no, cred_card_no, valid_date, 
						valid_no, cred_card_type, total_money, ship_name, post_code, mem_adrs, cell_phone, tel_phone);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				/*String url = "/front-end/product_order/listAllProduct_order.jsp";*/
				String url = "/front-end/product_order/ListProductOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProduct_order.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/product_order/addProduct_order.jsp");
				failureView.forward(req, res);
			}
		}
        
        if ("insertWithList".equals(action)) { // 來自addProduct_order.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_no = req.getParameter("mem_no").trim();
				if(mem_no == ""){
					errorMsgs.add("請輸入會員編號.");
				}
				
				String cred_card_no = req.getParameter("cred_card_no").trim();
				if(cred_card_no == ""){
					errorMsgs.add("請輸入信用卡卡號.");
				}
				
				/*java.sql.Date valid_date = null;
				try {
					valid_date = java.sql.Date.valueOf(req.getParameter("valid_date").trim());
				} catch (IllegalArgumentException e) {
					valid_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入信用卡有效時期!");
				}*/
				
				SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
				String validDate = req.getParameter("valid_date").trim();
				
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date = sdf.parse(validDate);
				validDate = sdf2.format(date);
				java.sql.Date valid_date = null;
				try {
					valid_date = java.sql.Date.valueOf(validDate);
				} catch (IllegalArgumentException e) {
					valid_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入信用卡有效時期!");
				}
				
				
				
				
				String valid_no = req.getParameter("valid_no").trim();
				if(valid_no == ""){
					errorMsgs.add("請輸入信用卡驗證碼.");
				}

				String cred_card_type = req.getParameter("cred_card_type").trim();
				if(cred_card_type == ""){
					errorMsgs.add("請輸入信用卡卡別.");
				}

				Integer total_money = null;
				try {
					total_money = new Integer(req.getParameter("total_money").trim());
				} catch (NumberFormatException e) {
					total_money = 0;
					errorMsgs.add("訂單總金額請填數字.");
				}

				String ship_name = req.getParameter("ship_name").trim();
				if(ship_name == ""){
					errorMsgs.add("請輸入收件人姓名.");
				}
				
				String post_code = req.getParameter("post_code").trim();
				if(post_code == ""){
					errorMsgs.add("請輸入郵遞區號.");
				}
				
				String mem_adrs = req.getParameter("mem_adrs").trim();
				if(mem_adrs == ""){
					errorMsgs.add("請輸入寄送地址.");
				}
				
				String cell_phone = req.getParameter("cell_phone").trim();
				if(cell_phone == ""){
					errorMsgs.add("請輸入聯絡手機.");
				}
				
				String tel_phone = req.getParameter("tel_phone").trim();
				if(tel_phone == ""){
					errorMsgs.add("請輸入聯絡市話.");
				}

				Product_orderVO product_orderVO = new Product_orderVO();
				product_orderVO.setMem_no(mem_no);
				product_orderVO.setCred_card_no(cred_card_no);
				product_orderVO.setValid_date(valid_date);
				product_orderVO.setValid_no(valid_no);
				product_orderVO.setCred_card_type(cred_card_type);
				product_orderVO.setTotal_money(total_money);
				product_orderVO.setShip_name(ship_name);
				product_orderVO.setPost_code(post_code);
				product_orderVO.setMem_adrs(mem_adrs);
				product_orderVO.setCell_phone(cell_phone);
				product_orderVO.setTel_phone(tel_phone);
				
				//明細
				String prod_no = req.getParameter("prod_no").trim();
				if(prod_no == ""){
					errorMsgs.add("請輸入商品編號.");
				}
				
				Integer unit_price = null;
				try {
					unit_price = new Integer(req.getParameter("unit_price").trim());
				} catch (NumberFormatException e) {
					unit_price = 0;
					errorMsgs.add("單價請填數字.");
				}
				
				Integer prod_quantity = null;
				try {
					prod_quantity = new Integer(req.getParameter("prod_quantity").trim());
				} catch (NumberFormatException e) {
					prod_quantity = 0;
					errorMsgs.add("數量請填數字.");
				}
				
				String deli_status = req.getParameter("deli_status").trim();
				
				java.sql.Date deli_time = null;
				try {
					deli_time = java.sql.Date.valueOf(req.getParameter("deli_time").trim());
				} catch (IllegalArgumentException e) {
					deli_time=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入商品出貨時間!");
				}
				
				
				Vector<Product_order_listVO> buylist = (Vector<Product_order_listVO>) session.getAttribute("shoppingcart");
				
				List<Product_order_listVO> list = new ArrayList<Product_order_listVO>();
				for(int i =0; i < buylist.size(); i++){
					Product_order_listVO product_order_listVO = new Product_order_listVO();
					
					product_order_listVO.setProd_no(buylist.elementAt(i).getProd_no().toString());
					product_order_listVO.setUnit_price(buylist.elementAt(i).getUnit_price());
					product_order_listVO.setProd_quantity(buylist.elementAt(i).getProd_quantity());
					product_order_listVO.setDeli_status(deli_status);
					product_order_listVO.setDeli_time(deli_time);
					
					/*product_order_listVO.setProd_no(prod_no);
					product_order_listVO.setUnit_price(unit_price);
					product_order_listVO.setProd_quantity(prod_quantity);
					product_order_listVO.setDeli_status(deli_status);
					product_order_listVO.setDeli_time(deli_time);*/
					
					list.add(product_order_listVO);
				}
				
				/*HttpSession session = req.getSession();
				List<Product_order_listVO> list = new ArrayList ((Vector<Product_order_listVO>) session.getAttribute("shoppingcart"));

				Product_order_listVO product_order_listVO = new Product_order_listVO();
				product_order_listVO.setProd_no(prod_no);
System.out.println(prod_no);
				product_order_listVO.setUnit_price(unit_price);
				product_order_listVO.setProd_quantity(prod_quantity);
				product_order_listVO.setDeli_status(deli_status);
System.out.println(deli_status);
				product_order_listVO.setDeli_time(deli_time);
				
				list.add(product_order_listVO);*/
				
				/*List<Product_order_listVO> list = new ArrayList<Product_order_listVO>();
				
				Product_order_listVO product_order_listVO = new Product_order_listVO();
				product_order_listVO.setProd_no(prod_no);
				product_order_listVO.setUnit_price(unit_price);
				product_order_listVO.setProd_quantity(prod_quantity);
				product_order_listVO.setDeli_status(deli_status);
				product_order_listVO.setDeli_time(deli_time);
				
				list.add(product_order_listVO);*/
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_orderVO", product_orderVO); // 含有輸入格式錯誤的product_orderVO物件,也存入req
					/*RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/product_order/addProduct_order.jsp");*/
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/product_order/AddProductOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Product_orderService product_orderSvc = new Product_orderService();
				product_orderVO = product_orderSvc.addProduct_orderWithList(mem_no, cred_card_no, valid_date, 
						valid_no, cred_card_type, total_money, ship_name, post_code, mem_adrs, cell_phone, tel_phone, 
						list);
				
				buylist.clear();
				session.setAttribute("amount", null);
				session.setAttribute("quantity", null);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				/*String url = "/front-end/product_order/listAllProduct_order.jsp";*/
				String url = "/front-end/product_order/ListProductOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProduct_order.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/product_order/addProduct_order.jsp");
				failureView.forward(req, res);
			}
		}
        
	}

}
