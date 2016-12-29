package com.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


@WebServlet("/Emp/EmpJsonRes.do")
public class EmpJsonRes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if("getEmpInfo".equals(request.getParameter("action"))){
			String emp_no = request.getParameter("emp_no");
			EmpService empSvc = new EmpService();
			EmpVO empVO = empSvc.getOneEmp(emp_no); 
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            String hiredate = sdf.format(empVO.getEmp_hiredate());;
			
				JSONObject obj = new JSONObject();
				try
				{
					obj.put("emp_name",empVO.getEmp_name());
					obj.put("emp_id",empVO.getEmp_id());
					obj.put("emp_email",empVO.getEmp_email());
					obj.put("emp_phone",empVO.getEmp_phone());
					obj.put("emp_hiredate",hiredate);
					obj.put("emp_address",empVO.getEmp_address());
					obj.put("emp_job",empVO.getEmp_job());
				} catch (JSONException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
			out.flush();
			out.close();
		}
	}
	
}
