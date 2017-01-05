package com.frd_list.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.frd_list.model.*;
import com.member.model.MemberService;
import com.member.model.MemberVO;


@WebServlet("/frd_list/frd_listJsonRes.do")
public class Frd_listJsonRes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		
		
		if("addFriend".equals(action)){
			
			HttpSession session = request.getSession();
//			String mem_no = (String)session.getAttribute("mem_no");
			String mem_no = "M00000005";
			String friend_no = request.getParameter("friend_no");
			Frd_listService frd_listSvc = new Frd_listService();
			
			JSONObject obj = new JSONObject();
			
			Frd_listVO member = frd_listSvc.addFrd_list(mem_no, friend_no, "1");
			Frd_listVO friend = frd_listSvc.addFrd_list(friend_no,mem_no , "0");
			
			MemberService memberSvc = new MemberService();
			
			MemberVO friendInfo = memberSvc.getOneMember(friend_no); 
			
			try
			{
				obj.put("friendName", friendInfo.getMem_name());
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
