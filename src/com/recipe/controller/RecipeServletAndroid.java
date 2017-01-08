package com.recipe.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.collection.model.CollectionVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.recipe.model.*;
import com.recipe_cont.model.*;
import com.recipe_type_info.model.Recipe_type_infoService;
import com.recipe_type_info.model.Recipe_type_infoVO;

import util.ImageUtil;
import util.SendResponse;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

@SuppressWarnings("serial")
@WebServlet("/RecipeServletAndroid")
public class RecipeServletAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		RecipeService recipeSvc = new RecipeService();
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
		System.out.println("Recipe action = " + action);
		StringBuffer outStr = new StringBuffer();

		if ("getAll".equals(action)) {
			List<RecipeVO> recipelist = recipeSvc.getAll();

			List<RecipeVO> list2 = new ArrayList<RecipeVO>();
			for (RecipeVO aRecipe : recipelist) {
				if (aRecipe.getRecipe_edit().equals("已發布")) {
					aRecipe.setRecipe_pic(null);
					list2.add(aRecipe);
//					System.out.println("aRecipe.getRecipe_no()"+aRecipe.getRecipe_no() +"aRecipe.getRecipe_edit():"+aRecipe.getRecipe_edit());
				}
			}

			SendResponse.writeText(res, gson.toJson(list2));
			return;
		}

		if ("getAllByMem_no_For_Display".equals(action)) {
			String mem_noJson = jsonObject.get("mem_no").getAsString();
			JsonReader reader = new JsonReader(new StringReader(mem_noJson));
			reader.setLenient(true);
			String mem_no = gson.fromJson(reader, String.class);
			List<RecipeVO> recipeVOList = recipeSvc.findByMem_no(mem_no);

			List<RecipeVO> list2 = new ArrayList<RecipeVO>();
			for (RecipeVO aRecipe : recipeVOList) {
				aRecipe.setRecipe_pic(null);
				list2.add(aRecipe);
			}

			outStr.append(gson.toJson(list2));
			// outStr.append(gson.toJson(recipeVOList));
			SendResponse.writeText(res, outStr.toString());

			return;
		}
		
		if ("getAllByRecipe_name".equals(action)) {
			String recipe_name = jsonObject.get("recipe_name").getAsString();
			List<RecipeVO> recipeVOList = recipeSvc.serachByRecipe_name(recipe_name);

			List<RecipeVO> list2 = new ArrayList<RecipeVO>();
			for (RecipeVO aRecipe : recipeVOList) {
				if (aRecipe.getRecipe_edit().equals("已發布")) {
					aRecipe.setRecipe_pic(null);
					list2.add(aRecipe);
					System.out.println("aRecipe.getRecipe_no()"+aRecipe.getRecipe_no() +"aRecipe.getRecipe_name():"+aRecipe.getRecipe_name());
				}
			}

			SendResponse.writeText(res, gson.toJson(list2));

			return;
		}
		if("getAllByRecipe_type_no".equals(action)){
			String recipe_type_no = jsonObject.get("recipe_type_no").getAsString();
			Recipe_type_infoService recipe_type_infoSvc = new Recipe_type_infoService();
			Set<Recipe_type_infoVO> recipe_type_infoVOSet = recipe_type_infoSvc.findByType_no(recipe_type_no);
			List<Recipe_type_infoVO> recipe_type_infoVOList = new ArrayList();
			recipe_type_infoVOList.addAll(recipe_type_infoVOSet);
			List<RecipeVO> recipeVOList = new ArrayList();
			
			for(Recipe_type_infoVO aRecipe_type_info: recipe_type_infoVOList){
				recipeVOList.add(recipeSvc.getOneRecipe(aRecipe_type_info.getRecipe_no()));
			}
			SendResponse.writeText(res, gson.toJson(recipeVOList));
			
		}
		
		
		if ("getOneByRecipe_no_For_Display".equals(action)) { // 來自select_page.jsp的請求

			String recipe_no = jsonObject.get("recipe_no").getAsString();
			// JsonReader reader = new JsonReader(new
			// StringReader(recipe_noJson));
			// reader.setLenient(true);

			// String recipe_no = gson.fromJson(reader, String.class);
			RecipeVO recipeVO = recipeSvc.getOneRecipe(recipe_no);

			recipeVO.setRecipe_pic(null);

			outStr.append(gson.toJson(recipeVO));
			SendResponse.writeText(res, outStr.toString());

			return;
		}

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			String recipe_noJson = jsonObject.get("recipe_no").getAsString();
			JsonReader reader = new JsonReader(new StringReader(recipe_noJson));
			reader.setLenient(true);

			String recipe_no = gson.fromJson(reader, String.class);
			Recipe_contService recipe_contSvc = new Recipe_contService();
			// recipeSvc.updateRecipeViews(recipe_no,
			// recipeVO.getRecipe_total_views()+1,
			// recipeVO.getRecipe_week_views()+1);
			// recipeVO = recipeSvc.getOneRecipe(recipe_no);
			Set<Recipe_contVO> set = recipe_contSvc.getRecipe_cont(recipe_no);

			Set<Recipe_contVO> set2 = new LinkedHashSet<Recipe_contVO>();
			for (Recipe_contVO aRecipeCond : set) {
				aRecipeCond.setStep_pic(null);
				set2.add(aRecipeCond);
			}

			outStr.append(gson.toJson(set2));
			// outStr.append(gson.toJson(recipeVOList));
			SendResponse.writeText(res, outStr.toString());

			return;
		}

		if ("getOne_For_Update".equals(action)) {
		}

		if ("search".equals(action)) {
		}

		if ("update".equals(action)) {
		}

		if ("insert_old".equals(action)) {
		}

		if ("insert".equals(action)) {
		}

		if ("delete".equals(action)) {
		}

		if ("getOne_For_UpdateViews".equals(action) || "getOne_For_UpdateLike".equals(action)) {
		}

		if ("updateViews".equals(action)) {
		}

		if ("updateLike".equals(action)) {
		}

		if ("recipe_week_viewsZero".equals(action)) {
		}

		if ("getImage".equals(action)) {
			OutputStream os = res.getOutputStream();
			String recipe_no = jsonObject.get("recipe_no").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] recipe_pic = recipeSvc.getImage(recipe_no);
			System.out.println("recipe_no " + recipe_no + " recipe_pic " + recipe_pic + " imageSize " + imageSize);

			if (recipe_pic != null) {

				recipe_pic = ImageUtil.shrink(recipe_pic, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(recipe_pic.length);
			} else {
				InputStream in = getServletContext().getResourceAsStream("/noImages/noimage.jpg");
				recipe_pic = new byte[in.available()];
				in.read(recipe_pic);
				in.close();
			}
			os.write(recipe_pic);
			return;
		}
	}

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		// System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		// System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
