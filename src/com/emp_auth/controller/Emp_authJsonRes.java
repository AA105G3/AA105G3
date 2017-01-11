package com.emp_auth.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.emp_auth.model.Emp_authService;
import com.emp_auth.model.Emp_authVO;



@WebServlet("/emp_auth/emp_authJsonRes.do")
public class Emp_authJsonRes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if("getEmpAuths".equals(request.getParameter("action"))){
			String emp_no = request.getParameter("emp_no");
			Emp_authService empSvc = new Emp_authService();
			Set<Emp_authVO> emp_auths = empSvc.getAuthsByEmp_no(emp_no); 
			JSONArray array = new JSONArray();
			
			for(Emp_authVO aAuth:emp_auths){
				JSONObject obj = new JSONObject();
				try
				{
					obj.put("auth", aAuth.getAuth_no());
					array.put(obj);
				} catch (JSONException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
				
				
				
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();
		}
	}

}
