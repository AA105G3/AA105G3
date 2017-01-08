package com.recipe_l_type.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe_l_type.model.Recipe_l_typeVO;
import com.recipe_m_type.model.*;

import util.SendResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.recipe.model.RecipeService;
import com.recipe.model.RecipeVO;
import com.recipe_l_type.model.Recipe_l_typeService;

@WebServlet("/recipe_l_type/recipe_l_type_android.do")
public class Recipe_l_typeServletAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Recipe_l_typeService recipe_l_typeSvc = new Recipe_l_typeService();
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
		System.out.println("Recipe L Type action = " + action);
		StringBuffer outStr = new StringBuffer();
		
		if ("getAll".equals(action)) {
			List<Recipe_l_typeVO> recipe_l_typelist = recipe_l_typeSvc.getAll();

			SendResponse.writeText(res, gson.toJson(recipe_l_typelist));
			return;
		}
		
		if("getM_typesByL_Type_No".equals(action)){
			String recipe_l_type_no = jsonObject.get("recipe_l_type_no").getAsString();
			Set<Recipe_m_typeVO> recipe_m_typeSet = recipe_l_typeSvc.getM_typesByL_Type_No(recipe_l_type_no);
		
			List<Recipe_m_typeVO> recipe_m_typeList = new ArrayList<Recipe_m_typeVO>();
			recipe_m_typeList.addAll(recipe_m_typeSet);
			
			SendResponse.writeText(res, gson.toJson(recipe_m_typeList));
			return;
		}

		if ("getOne_For_Display".equals(action)) {

		}

		if ("getOne_For_Update".equals(action)) {

		}

		if ("update".equals(action)) {

		}

		if ("insert".equals(action)) {

		}

		if ("delete".equals(action)) {

		}
	}
}
