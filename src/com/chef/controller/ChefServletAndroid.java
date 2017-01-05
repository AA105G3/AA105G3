package com.chef.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.chef.modelApp.*;
import com.collection.model.CollectionService;
import com.collection.model.CollectionVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import util.SendResponse;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10000 * 1024 * 1024, maxRequestSize = 1000 * 10000
		* 1024 * 1024)

@SuppressWarnings("serial")
@WebServlet("/ChefServletAndroid")
public class ChefServletAndroid extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		ChefService chefSvc = new ChefService();
		BufferedReader br = req.getReader();
		StringBuffer jsonIn = new StringBuffer();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		if (gson.fromJson(jsonIn.toString(), JsonObject.class) == null) {
			return;
		}

		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);

		String action = jsonObject.get("action").getAsString();
		System.out.println("Chef action = " + action);
		StringBuffer outStr = new StringBuffer();

		if ("getOneByMem_no".equals(action)) {
			String mem_no = jsonObject.get("mem_no").getAsString();
			ChefVO chefVO = chefSvc.getOneByMem_no(mem_no);
			
			//照片設為NULL, 避免手機OOM
			chefVO.setChef_lic(null);
			chefVO.setChef_image(null);
			chefVO.setChef_movie1(null);
			chefVO.setChef_movie2(null);
			chefVO.setChef_reci_image1(null);
			chefVO.setChef_reci_image2(null);
			chefVO.setChef_reci_image3(null);
			chefVO.setChef_reci_image4(null);
			chefVO.setChef_reci_image5(null);
			
			outStr.append(gson.toJson(chefVO));
			SendResponse.writeText(res, outStr.toString());
		}

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

		}
		if ("update_chk_cond".equals(action)) {

		}

		if ("getOne_For_Update".equals(action) || "getOne_For_Check".equals(action)) { // 來自listAllChef.jsp的請求

		}

		if ("update".equals(action)) { // 來自update_chef_input.jsp的請求

		}

		if ("insert".equals(action)) { // 來自addChef.jsp的請求

		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

		}
	}

//	public String getFileNameFromPart(Part lic_part) {
//		String header = lic_part.getHeader("content-disposition");
//		String fileName = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		if (fileName.length() == 0) {
//			return null;
//		}
//		return fileName;
//	}
//
//	public String getFileNameFromPart2(Part c_img_part) {
//		String header = c_img_part.getHeader("content-disposition");
//		String fileName = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		if (fileName.length() == 0) {
//			return null;
//		}
//		return fileName;
//	}
//
//	public String getFileNameFromPart3(Part mov1_part) {
//		String header = mov1_part.getHeader("content-disposition");
//		String fileName = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		if (fileName.length() == 0) {
//			return null;
//		}
//		return fileName;
//	}
//
//	public String getFileNameFromPart4(Part mov2_part) {
//		String header = mov2_part.getHeader("content-disposition");
//		String fileName = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		if (fileName.length() == 0) {
//			return null;
//		}
//		return fileName;
//	}
//
//	public String getFileNameFromPart5(Part img1_part) {
//		String header = img1_part.getHeader("content-disposition");
//		String fileName = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		if (fileName.length() == 0) {
//			return null;
//		}
//		return fileName;
//	}
//
//	public String getFileNameFromPart6(Part img2_part) {
//		String header = img2_part.getHeader("content-disposition");
//		String fileName = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		if (fileName.length() == 0) {
//			return null;
//		}
//		return fileName;
//	}
//
//	public String getFileNameFromPart7(Part img3_part) {
//		String header = img3_part.getHeader("content-disposition");
//		String fileName = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		if (fileName.length() == 0) {
//			return null;
//		}
//		return fileName;
//	}
//
//	public String getFileNameFromPart8(Part img4_part) {
//		String header = img4_part.getHeader("content-disposition");
//		String fileName = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		if (fileName.length() == 0) {
//			return null;
//		}
//		return fileName;
//	}
//
//	public String getFileNameFromPart9(Part img5_part) {
//		String header = img5_part.getHeader("content-disposition");
//		String fileName = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		if (fileName.length() == 0) {
//			return null;
//		}
//		return fileName;
//	}
}
