package com.chef.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.InputStream;

import com.chef.model.*;

@WebServlet("/chef/chefImage.do")
public class chef_image extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("image/gif");
		OutputStream out = res.getOutputStream();
		
		try
		{
			String chef_no = req.getParameter("chef_no");
			ChefService chefSvc = new ChefService();
			ChefVO chefVO = chefSvc.getOneChef(chef_no);
			byte[] buffer=null;
			if(req.getParameter("chef_lic")!=null);{
				buffer =chefVO.getChef_lic();
			}
			if(req.getParameter("chef_image")!=null){
				buffer =chefVO.getChef_image();
			}
			if(req.getParameter("chef_movie1")!=null){
				buffer =chefVO.getChef_movie1();
			}
			if(req.getParameter("chef_movie2")!=null){
				buffer =chefVO.getChef_movie2();
			}
			if(req.getParameter("chef_reci_image1")!=null){
				buffer =chefVO.getChef_reci_image1();
			}
			if(req.getParameter("chef_reci_image2")!=null){
				buffer =chefVO.getChef_reci_image2();
			}
			if(req.getParameter("chef_reci_image3")!=null){
				buffer =chefVO.getChef_reci_image3();
			}
			if(req.getParameter("chef_reci_image4")!=null){
				buffer =chefVO.getChef_reci_image4();
			}
			if(req.getParameter("chef_reci_image5")!=null){
				buffer =chefVO.getChef_reci_image5();
			}
			
			out.write(buffer);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
//			InputStream in = getServletContext().getResourceAsStream("images/nopic.gif");
//			byte[] buffer = new byte[in.available()];
//			in.read(buffer);
//			out.write(buffer);
//			in.close();
		}
		
		
	}

}
