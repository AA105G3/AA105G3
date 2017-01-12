package com.back.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.*;
import com.emp_auth.model.*;


@WebServlet("/BLoginHandler")
public class BLoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected boolean allowUser(String emp_account, String emp_password,HttpSession session) {
		EmpService empSvc = new EmpService();
		//搜尋帳號
		EmpVO empVO = empSvc.getOneEmpByAccount(emp_account);
		if (empVO==null){
			return false;
		}
		//有帳號取帳號
		String account = empVO.getEmp_account();
		
		//取出密碼
		String password = empVO.getEmp_password();
		
		String emp_no =  empVO.getEmp_no();
		
		//比對帳號及密碼
		if(account.equals(emp_account) && password.equals(emp_password)){
			setIntoSession(emp_account,emp_password,emp_no,session);
			return true;
		}
		else{
			return false;
		}
	}
	protected void setIntoSession(String emp_account, String emp_password,String emp_no,HttpSession session){
		
		Emp_authService emp_authSvc = new Emp_authService();
		List<String> emp_auths = emp_authSvc.getAuthsStringByEmp_no(emp_no);
		
		
		session.setAttribute("emp_no", emp_no);
		session.setAttribute("emp_account", emp_account);
		session.setAttribute("emp_password", emp_password);
		session.setAttribute("emp_auths", emp_auths);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		HttpSession session = req.getSession();
		
		
		
		// 【取得使用者 帳號(account) 密碼(password)】
		String emp_account = req.getParameter("emp_account").trim();
		String emp_password = req.getParameter("emp_password").trim();
		
		
		// 【檢查該帳號 , 密碼是否有效】
		if (!allowUser(emp_account, emp_password,session)) {          //【帳號 , 密碼無效時】
			List<String> errorMsg = new LinkedList<String>();
			errorMsg.add("帳號或密碼錯誤");
			
			req.setAttribute("errorMsg", errorMsg);
			
			String url = "/Login/Blogin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		else {                                       //【帳號 , 密碼有效時, 才做以下工作】

			try {                                                        
				String location = (String) session.getAttribute("location");
				if (location != null) {
					System.out.println(location);
					session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					res.sendRedirect(location);   
					return;
				}
			}catch (Exception ignored) {}

			res.sendRedirect(req.getContextPath()+"/back-end/index.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)
		}
	}

}
