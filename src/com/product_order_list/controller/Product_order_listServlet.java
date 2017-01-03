package com.product_order_list.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.product.model.ProductVO;
import com.product_order_list.model.*;

public class Product_order_listServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		HttpSession session = req.getSession();
		Vector<Product_order_listVO> buylist = (Vector<Product_order_listVO>) session.getAttribute("shoppingcart");
		

		// 刪除購物車中的商品
		if (action.equals("DELETE_PRODUCT")) {
			String del = req.getParameter("del");
			for(int index = 0; index < buylist.size(); index++){
				if(buylist.elementAt(index).getProd_no().toString().equals(del)){
					buylist.removeElementAt(index);
				}
			}
			
			int total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				Product_order_listVO product_order_listVO = buylist.get(i);
				int unit_price = product_order_listVO.getUnit_price();
				int prod_quantity = product_order_listVO.getProd_quantity();
				total += (unit_price * prod_quantity);
			}
				
			int total2 = 0;
			for (int i = 0; i < buylist.size(); i++) {
				Product_order_listVO product_order_listVO = buylist.get(i);
				int unit_price = product_order_listVO.getUnit_price();
				int prod_quantity = product_order_listVO.getProd_quantity();
				total2 += (prod_quantity);
			}

			String amount = String.valueOf(total);
			String quantity = String.valueOf(total2);
			
			session.setAttribute("amount", amount);
			session.setAttribute("quantity", quantity);

			String url = "/front-end/web_page/Cart.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
			
		// 新增商品至購物車中
		if (action.equals("ADD_PRODUCT")) {
			boolean match = false;

			// 取得後來新增的商品
			Product_order_listVO aproduct_order_list = getProduct(req);
			
			// 新增第一本商品至購物車時
			if (buylist == null) {
				buylist = new Vector<Product_order_listVO>();
				buylist.add(aproduct_order_list);
			} else {
				for (int i = 0; i < buylist.size(); i++) {
					Product_order_listVO product_order_list = buylist.get(i);

					// 假若新增的商品和購物車的商品一樣時
					if (product_order_list.getProd_no().equals(aproduct_order_list.getProd_no())) {
						product_order_list.setProd_quantity(product_order_list.getProd_quantity()
								+ aproduct_order_list.getProd_quantity());
						buylist.setElementAt(product_order_list, i);
						match = true;
					} // end of if name matches
				} // end of for

				// 假若新增的商品和購物車的商品不一樣時
				if (!match)
					buylist.add(aproduct_order_list);
			}
				
			int total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				Product_order_listVO product_order_listVO = buylist.get(i);
				int unit_price = product_order_listVO.getUnit_price();
				int prod_quantity = product_order_listVO.getProd_quantity();
				total += (unit_price * prod_quantity);
			}
				
			int total2 = 0;
			for (int i = 0; i < buylist.size(); i++) {
				Product_order_listVO product_order_listVO = buylist.get(i);
				int unit_price = product_order_listVO.getUnit_price();
				int prod_quantity = product_order_listVO.getProd_quantity();
				total2 += (prod_quantity);
			}

			String amount = String.valueOf(total);
			String quantity = String.valueOf(total2);

			session.setAttribute("amount", amount);
			session.setAttribute("quantity", quantity);
				
			session.setAttribute("shoppingcart", buylist);
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/web_page/Market.jsp");
			failureView.forward(req, res);
		}
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str1 = req.getParameter("prod_ord_no");
				String str2 = req.getParameter("prod_no");
				
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_order_list/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String prod_ord_no = null;
				try {
					prod_ord_no = new String(str1);
				} catch (Exception e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_order_list/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				if (str2 == null || (str2.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_order_list/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String prod_no = null;
				try {
					prod_no = new String(str2);
				} catch (Exception e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_order_list/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Product_order_listService product_order_listSvc = new Product_order_listService();
				Product_order_listVO product_order_listVO = product_order_listSvc.getOneProduct_order_list(prod_ord_no, prod_no);
				if (product_order_listVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_order_list/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("product_order_listVO", product_order_listVO); // 資料庫取出的product_order_listVO物件,存入req
				String url = "/back-end/product_order_list/listOneProduct_order_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProduct_order_list.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_order_list/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllProduct_order_list.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String prod_ord_no = new String(req.getParameter("prod_ord_no"));
				String prod_no = new String(req.getParameter("prod_no"));
				
				/***************************2.開始查詢資料****************************************/
				Product_order_listService product_order_listSvc = new Product_order_listService();
				Product_order_listVO product_order_listVO = product_order_listSvc.getOneProduct_order_list(prod_ord_no, prod_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("product_order_listVO", product_order_listVO);         // 資料庫取出的product_order_listVO物件,存入req
				/*String url = "/back-end/product_order_list/update_product_order_list_input.jsp";*/
				String url = "/back-end/web_page/UpdateList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_product_order_list_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_order_list/listAllProduct_order_list.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Display_By_One_PK".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("prod_ord_no");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_order_list/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String prod_ord_no = null;
				try {
					prod_ord_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_order_list/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Product_order_listService product_order_listSvc = new Product_order_listService();
				Product_order_listVO product_order_listVO = product_order_listSvc.getOneProduct_order_list_By_One_PK(prod_ord_no);
				if (product_order_listVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_order_list/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("product_order_listVO", product_order_listVO); // 資料庫取出的product_order_listVO物件,存入req
				String url = "/back-end/product_order_list/listOneProduct_order_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProduct_order_list.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_order_list/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getPart_For_Display_By_One_PK".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("prod_ord_no");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					/*RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_order_list/select_page.jsp");*/
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/web_page/ListManagement.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String prod_ord_no = null;
				try {
					prod_ord_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					/*RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_order_list/select_page.jsp");*/
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/web_page/ListManagement.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Product_order_listService product_order_listSvc = new Product_order_listService();
				List<Product_order_listVO> product_order_listVO = product_order_listSvc.getProduct_order_list_By_One_PK(str);

				if (product_order_listVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					/*RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_order_list/select_page.jsp");*/
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/web_page/ListManagement.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("product_order_listVO", product_order_listVO); // 資料庫取出的product_order_listVO物件,存入req
				/*String url = "/back-end/product_order_list/listPartProduct_order_list.jsp";*/
				String url = "/back-end/web_page/DisplayProductOrderList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProduct_order_list.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_order_list/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update_By_One_PK".equals(action)) { // 來自listAllProduct_order_list.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String prod_ord_no = new String(req.getParameter("prod_ord_no"));
				
				/***************************2.開始查詢資料****************************************/
				Product_order_listService product_order_listSvc = new Product_order_listService();
				Product_order_listVO product_order_listVO = product_order_listSvc.getOneProduct_order_list_By_One_PK(prod_ord_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("product_order_listVO", product_order_listVO);         // 資料庫取出的product_order_listVO物件,存入req
				String url = "/back-end/product_order_list/update_product_order_list_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_product_order_list_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_order_list/listAllProduct_order_list.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_product_order_list_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String prod_ord_no = req.getParameter("prod_ord_no").trim();
				if(prod_ord_no == ""){
					errorMsgs.add("請輸入訂單編號.");
				}
				
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

				Product_order_listVO product_order_listVO = new Product_order_listVO();
				product_order_listVO.setProd_ord_no(prod_ord_no);
				product_order_listVO.setProd_no(prod_no);
				product_order_listVO.setUnit_price(unit_price);
				product_order_listVO.setProd_quantity(prod_quantity);
				product_order_listVO.setDeli_status(deli_status);
				product_order_listVO.setDeli_time(deli_time);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_order_listVO", product_order_listVO); // 含有輸入格式錯誤的product_order_listVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_order_list/update_product_order_list_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Product_order_listService product_order_listSvc = new Product_order_listService();
				product_order_listVO = product_order_listSvc.updateProduct_order_list(prod_ord_no, prod_no, unit_price, prod_quantity, deli_status, deli_time);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("product_order_listVO", product_order_listVO); // 資料庫update成功後,正確的的product_order_listVO物件,存入req
				/*String url = "/back-end/product_order_list/listOneProduct_order_list.jsp";*/
				String url = "/back-end/web_page/ListManagement.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneProduct_order_list.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_order_list/update_product_order_list_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // 來自addProduct_order_list.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/			
				String prod_ord_no = req.getParameter("prod_ord_no").trim();
				if(prod_ord_no == ""){
					errorMsgs.add("請輸入訂單編號.");
				}
				
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

				Product_order_listVO product_order_listVO = new Product_order_listVO();
				product_order_listVO.setProd_ord_no(prod_ord_no);
				product_order_listVO.setProd_no(prod_no);
				product_order_listVO.setUnit_price(unit_price);
				product_order_listVO.setProd_quantity(prod_quantity);
				product_order_listVO.setDeli_status(deli_status);
				product_order_listVO.setDeli_time(deli_time);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_order_listVO", product_order_listVO); // 含有輸入格式錯誤的product_order_listVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/product_order_list/addProduct_order_list.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Product_order_listService product_order_listSvc = new Product_order_listService();
				product_order_listVO = product_order_listSvc.addProduct_order_list(prod_ord_no, prod_no, unit_price, prod_quantity, deli_status, deli_time);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/product_order_list/listAllProduct_order_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProduct_order_list.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product_order_list/addProduct_order_list.jsp");
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
	
	private Product_order_listVO getProduct(HttpServletRequest req) {

		String prod_no = req.getParameter("prod_no");
		String unit_price = req.getParameter("unit_price");
		String prod_quantity = req.getParameter("prod_quantity");

		Product_order_listVO product_order_listVO = new Product_order_listVO();

		product_order_listVO.setProd_no(prod_no);
		product_order_listVO.setUnit_price((new Integer(unit_price)).intValue());
		product_order_listVO.setProd_quantity((new Integer(prod_quantity)).intValue());
		return product_order_listVO;
	}

}
