package com.recipe_m_type.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.recipe_l_type.model.Recipe_l_typeService;
import com.recipe_l_type.model.Recipe_l_typeVO;
import com.recipe_m_type.model.Recipe_m_typeService;
import com.recipe_m_type.model.Recipe_m_typeVO;
import com.recipe_s_type.model.*;

import util.SendResponse;

@WebServlet("/recipe_m_type/recipe_m_type_android.do")
public class Recipe_m_typeServletAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Recipe_m_typeService recipe_m_typeSvc = new Recipe_m_typeService();
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
		System.out.println("Recipe M Type action = " + action);
		StringBuffer outStr = new StringBuffer();

		if ("getAllByRecipe_l_type_no".equals(action)) {
			List<Recipe_m_typeVO> recipe_m_typelist = recipe_m_typeSvc.getAll();

			SendResponse.writeText(res, gson.toJson(recipe_m_typelist));
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

		if ("delete".equals(action)) { // 來自listAllRecipe_m_type.jsp
		}
	}
}
