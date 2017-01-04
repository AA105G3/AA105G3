package com.chef_schedule.controller;

import java.io.*;
import java.util.*;


import javax.servlet.*;
import javax.servlet.http.*;

import com.chef_schedule.model.*;

public class Chef_scheduleServlet extends HttpServlet {

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
				String str = req.getParameter("chef_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�p�p�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chef_schedule/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String chef_no = null;
				try {
					chef_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�p�p�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chef_schedule/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Chef_scheduleService chef_scheduleSvc = new Chef_scheduleService();
				Chef_scheduleVO chef_scheduleVO = chef_scheduleSvc.getOneChef_schedule(chef_no);
				if (chef_scheduleVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chef_schedule/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("chef_scheduleVO", chef_scheduleVO); // ��Ʈw���X��chef_scheduleVO����,�s�Jreq
				String url = "/front-end/chef_schedule/listOneChef_schedule.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneChef_schedule.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef_schedule/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllChef_schedule.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String chef_no = new String(req.getParameter("chef_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				Chef_scheduleService chef_scheduleSvc = new Chef_scheduleService();
				Chef_scheduleVO chef_scheduleVO = chef_scheduleSvc.getOneChef_schedule(chef_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("chef_scheduleVO", chef_scheduleVO);         // ��Ʈw���X��chef_scheduleVO����,�s�Jreq
				String url = "/front-end/chef_schedule/update_chef_schedule_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_chef_schedule_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef_schedule/listAllChef_schedule.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_chef_schedule_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String chef_no = new String(req.getParameter("chef_no").trim());
				String chef_scd_mae = req.getParameter("chef_scd_mae").trim();
				
				java.sql.Date chef_scd_date = null;
				try {
					chef_scd_date = java.sql.Date.valueOf(req.getParameter("chef_scd_date").trim());
				} catch (IllegalArgumentException e) {
					chef_scd_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
								
				String chef_scd_cnt = req.getParameter("chef_scd_cnt").trim();				
				
				


				Chef_scheduleVO chef_scheduleVO = new Chef_scheduleVO();
				chef_scheduleVO.setChef_no(chef_no);			
				chef_scheduleVO.setChef_scd_mae(chef_scd_mae);
				chef_scheduleVO.setChef_scd_date(chef_scd_date);
				chef_scheduleVO.setChef_scd_cnt(chef_scd_cnt);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("chef_scheduleVO", chef_scheduleVO); // �t����J�榡���~��chef_scheduleVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chef_schedule/update_chef_schedule_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				Chef_scheduleService chef_scheduleSvc = new Chef_scheduleService();
				chef_scheduleVO = chef_scheduleSvc.updateChef_schedule(chef_no, chef_scd_mae, chef_scd_date, chef_scd_cnt);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("chef_scheduleVO", chef_scheduleVO); // ��Ʈwupdate���\��,���T����chef_scheduleVO����,�s�Jreq
				String url = "/front-end/chef_schedule/listOneChef_schedule.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneChef_schedule.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef_schedule/update_chef_schedule_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addChef_schedule.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				
				String chef_no = req.getParameter("chef_no").trim();
				String chef_scd_mae = req.getParameter("chef_scd_mae").trim();
				String chef_scd_cnt = req.getParameter("chef_scd_cnt").trim();
				
				java.sql.Date chef_scd_date = null;
				try {
					chef_scd_date = java.sql.Date.valueOf(req.getParameter("chef_scd_date").trim());
				} catch (IllegalArgumentException e) {
					chef_scd_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
//				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//			    String hireDate = req.getParameter("emp_hiredate").trim();
//			    
//			    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//			    java.util.Date date = sdf.parse(hireDate);
//			    hireDate = sdf2.format(date);
//				java.sql.Date emp_hiredate = null;
//				try {
//					emp_hiredate = java.sql.Date.valueOf(hireDate);
//				} catch (IllegalArgumentException e) {
//					emp_hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("�п�J���!");
//				}


				Chef_scheduleVO chef_scheduleVO = new Chef_scheduleVO();
				chef_scheduleVO.setChef_no(chef_no);
				chef_scheduleVO.setChef_scd_mae(chef_scd_mae);
				chef_scheduleVO.setChef_scd_cnt(chef_scd_cnt);
				chef_scheduleVO.setChef_scd_date(chef_scd_date);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("chef_scheduleVO", chef_scheduleVO); // �t����J�榡���~��chef_scheduleVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/chef_schedule/addChef_schedule.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				Chef_scheduleService chef_scheduleSvc = new Chef_scheduleService();
				chef_scheduleVO = chef_scheduleSvc.addChef_schedule(chef_no, chef_scd_date, chef_scd_mae, chef_scd_cnt);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front-end/chef_schedule/listAllChef_schedule.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllChef_schedule.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef_schedule/addChef_schedule.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllChef_schedule.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String chef_no = new String(req.getParameter("chef_no"));
				
				
				java.sql.Date chef_scd_date = null;
				try {
					chef_scd_date = java.sql.Date.valueOf(req.getParameter("chef_scd_date").trim());
				} catch (IllegalArgumentException e) {
					chef_scd_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				String chef_scd_mae = new String(req.getParameter("chef_scd_mae"));				
				
				/***************************2.�}�l�R�����***************************************/
				Chef_scheduleService chef_scheduleSvc = new Chef_scheduleService();
				chef_scheduleSvc.deleteChef_schedule(chef_no,chef_scd_date,chef_scd_mae);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front-end/chef_schedule/listAllChef_schedule.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/chef_schedule/listAllChef_schedule.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
