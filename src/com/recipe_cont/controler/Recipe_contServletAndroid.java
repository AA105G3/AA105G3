package com.recipe_cont.controler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.recipe.model.RecipeService;
import com.recipe.model.RecipeVO;
import com.recipe_cont.model.Recipe_contService;
import com.recipe_cont.model.Recipe_contVO;
import com.recipe_cont.model.*;

import util.ImageUtil;
import util.SendResponse;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/recipe_cont/recipe_cont_android.do")

public class Recipe_contServletAndroid extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Recipe_contService recipe_contSvc = new Recipe_contService();
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
		System.out.println("Recipe_cont action = " + action);
		StringBuffer outStr = new StringBuffer();
		
		if ("getOne_For_Display".equals(action) || "getOne_For_Update".equals(action)) { // 來自select_page.jsp的請求

		}
		
		if("showRecipe_cont_pic".equals(action)){
			
			OutputStream os = res.getOutputStream();
			String recipe_no = jsonObject.get("recipe_no").getAsString();
			int step = jsonObject.get("step").getAsInt();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			
			Recipe_contVO recipe_contVO = recipe_contSvc.getOneRecipe_cont(recipe_no, step);
			
			byte[] step_pic = recipe_contVO.getStep_pic();
			System.out.println("recipe_no " + recipe_no + " step_pic " + step_pic+ " imageSize " + imageSize);

			if (step_pic != null) {

				step_pic = ImageUtil.shrink(step_pic, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(step_pic.length);
			} else {
				InputStream in = getServletContext().getResourceAsStream("/noImages/noimage.jpg");
				step_pic = new byte[in.available()];
				in.read(step_pic);
				in.close();
			}
			os.write(step_pic);
			return;
			
			
//			String recipe_noJson = jsonObject.get("recipe_no").getAsString();
//			String stepJson = jsonObject.get("step").getAsString();
//			
//			JsonReader reader = new JsonReader(new StringReader(recipe_noJson));
//			reader.setLenient(true);
//			String recipe_no = gson.fromJson(reader, String.class);
//			
//			JsonReader reader2 = new JsonReader(new StringReader(stepJson));
//			reader2.setLenient(true);
//			String stepString = gson.fromJson(reader2, String.class);
//			
//			Integer step = Integer.parseInt(stepString);
//			
////			Recipe_contService recipe_contSvc = new Recipe_contService();
//			Recipe_contVO recipe_contVO = recipe_contSvc.getOneRecipe_cont(recipe_no, step);
//			byte[] buffer = recipe_contVO.getStep_pic();
//			
//			outStr.append(gson.toJson(frd_listVOList));
//			
//			
////			outStr.append(gson.toJson(recipeVOList));
//			SendResponse.writeText(res, outStr.toString());
//			
//			return;
			
//			String recipe_no = req.getParameter("recipe_no");
//			Integer step = new Integer(req.getParameter("step"));
//			Recipe_contService recipe_contSvc = new Recipe_contService();
//			Recipe_contVO recipe_contVO = recipe_contSvc.getOneRecipe_cont(recipe_no, step);
//			byte[] buffer = recipe_contVO.getStep_pic();
//			out.write(buffer);
		}
		
		if ("update".equals(action)) {
			
		}
		
		if ("insert".equals(action)) {
		}
		

		
		if ("deleteImage".equals(action)) { 

		}

	}
	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}
