package com.recipe_s_type.controller;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.recipe_m_type.model.Recipe_m_typeService;
import com.recipe_s_type.model.Recipe_s_typeService;
import com.recipe_s_type.model.Recipe_s_typeVO;

import util.SendResponse;

@WebServlet("/recipe_s_type/recipe_s_type_android.do")
public class Recipe_s_typeServletAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Recipe_s_typeService recipe_s_typeSvc = new Recipe_s_typeService();
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
		System.out.println("Recipe S Type action = " + action);
		StringBuffer outStr = new StringBuffer();
		
		
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
