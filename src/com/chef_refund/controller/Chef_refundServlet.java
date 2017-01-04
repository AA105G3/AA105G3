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
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("chef_rfd_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�h�ڽs��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/chef_refund/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String chef_rfd_no = null;
				try {
					chef_rfd_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�h�ڽs���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/chef_refund/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Chef_refundService chef_refundSvc = new Chef_refundService();
				Chef_refundVO chef_refundVO = chef_refundSvc.getOneChef_refund(chef_rfd_no);
				if (chef_refundVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/chef_refund/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("chef_refundVO", chef_refundVO); // ��Ʈw���X��chef_refundVO����,�s�Jreq
				String url = "/back-end/chef_refund/listOneChef_refund.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneChef_refund.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/chef_refund/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllChef_refund.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String chef_rfd_no = new String(req.getParameter("chef_rfd_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				Chef_refundService chef_refundSvc = new Chef_refundService();
				Chef_refundVO chef_refundVO = chef_refundSvc.getOneChef_refund(chef_rfd_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("chef_refundVO", chef_refundVO);         // ��Ʈw���X��chef_refundVO����,�s�Jreq
				String url = "/back-end/chef_refund/update_chef_refund_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_chef_refund_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/chef_refund/listAllChef_refund.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // �Ӧ�update_chef_refund_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String chef_rfd_no = new String(req.getParameter("chef_rfd_no").trim());
				String chef_ord_no = req.getParameter("chef_ord_no").trim();
				String emp_no = req.getParameter("emp_no").trim();				
				
				Double chef_rfd_per = null;
				try {
					chef_rfd_per = new Double(req.getParameter("chef_rfd_per").trim());
				} catch (NumberFormatException e) {
					chef_rfd_per = 0.0;
					errorMsgs.add("�h�ڦʤ���ж�Ʀr.");
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
					req.setAttribute("chef_refundVO", chef_refundVO); // �t����J�榡���~��chef_refundVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/chef_refund/update_chef_refund_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				Chef_refundService chef_refundSvc = new Chef_refundService();
				chef_refundVO = chef_refundSvc.updateChef_refund(chef_rfd_no, chef_ord_no, emp_no, chef_rfd_per, mem_bnk, mem_bnk_ac, chef_ord_chk_cond, chef_ord_red_cnt);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("chef_refundVO", chef_refundVO); // ��Ʈwupdate���\��,���T����chef_refundVO����,�s�Jreq
				String url = "/back-end/chef_refund/listOneChef_refund.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneChef_refund.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/chef_refund/update_chef_refund_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update_back".equals(action)) { // �Ӧ�listAllChef_refund.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String chef_rfd_no = new String(req.getParameter("chef_rfd_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				Chef_refundService chef_refundSvc = new Chef_refundService();
				Chef_refundVO chef_refundVO = chef_refundSvc.getOneChef_refund(chef_rfd_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("chef_refundVO", chef_refundVO);         // ��Ʈw���X��chef_refundVO����,�s�Jreq
				String url = "/back-end/chef_refund/update_chef_refund_check.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_chef_refund_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
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
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String chef_rfd_no = new String(req.getParameter("chef_rfd_no").trim());			
				
				Double chef_rfd_per = null;
				try {
					chef_rfd_per = new Double(req.getParameter("chef_rfd_per").trim());
				} catch (NumberFormatException e) {
					chef_rfd_per = 0.0;
					errorMsgs.add("�h�ڦʤ���ж�Ʀr.");
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
					req.setAttribute("chef_refundVO", chef_refundVO); // �t����J�榡���~��chef_refundVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/chef_refund/update_chef_refund_check.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				Chef_refundService chef_refundSvc = new Chef_refundService();
				chef_refundVO = chef_refundSvc.update_backChef_refund(chef_rfd_no, chef_rfd_per, chef_ord_chk_cond, chef_ord_red_cnt);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("chef_refundVO", chef_refundVO); // ��Ʈwupdate���\��,���T����chef_refundVO����,�s�Jreq
				String url = "/back-end/chef_refund/listOneChef_refund.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneChef_refund.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/chef_refund/update_chef_refund_check.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addChef_refund.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String chef_ord_no = req.getParameter("chef_ord_no").trim();
				String emp_no = req.getParameter("emp_no").trim();
				
				
				Double chef_rfd_per = null;
				try {
					chef_rfd_per = new Double(req.getParameter("chef_rfd_per").trim());
				} catch (NumberFormatException e) {
					chef_rfd_per = 0.0;
					errorMsgs.add("�h�ڦʤ���ж�Ʀr.");
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
					req.setAttribute("chef_refundVO", chef_refundVO); // �t����J�榡���~��chef_refundVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/chef_refund/addChef_refund.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				Chef_refundService chef_refundSvc = new Chef_refundService();
				chef_refundVO = chef_refundSvc.addChef_refund(chef_ord_no, emp_no, chef_rfd_per, mem_bnk, mem_bnk_ac, chef_ord_chk_cond, chef_ord_red_cnt);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back-end/chef_refund/listAllChef_refund.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllChef_refund.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/chef_refund/addChef_refund.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllChef_refund.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String chef_rfd_no = new String(req.getParameter("chef_rfd_no"));
				
				/***************************2.�}�l�R�����***************************************/
				Chef_refundService chef_refundSvc = new Chef_refundService();
				chef_refundSvc.deleteChef_refund(chef_rfd_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back-end/chef_refund/listAllChef_refund.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/chef_refund/listAllChef_refund.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
