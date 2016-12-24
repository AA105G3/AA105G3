package com.frd_list.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.frd_list.model.Frd_listVO;
import com.frd_list.model.Frd_listService;
import com.frd_list.model.Frd_listService;
import com.google.gson.stream.JsonReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import util.SendResponse;

@SuppressWarnings("serial")
@WebServlet("/Frd_listServletAndroid")
public class Frd_listServletAndroid extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Frd_listService frd_listSvc = new Frd_listService();
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
		System.out.println("action = " + action);
		StringBuffer outStr = new StringBuffer();		

		if ("getOne_For_Display".equals(action)) {
		}

		if ("getAllByMem_no_For_Display".equals(action)) {
			String mem_noJson = jsonObject.get("mem_no").getAsString();
			JsonReader reader = new JsonReader(new StringReader(mem_noJson));
			reader.setLenient(true);
			String mem_no = gson.fromJson(reader, String.class);System.out.println("Frd_listServletAndroid(63 line) mem_no:" + mem_no);
			List<Frd_listVO> frd_listVOList = frd_listSvc.getAllByMem_noFrd_list(mem_no); 
			
			outStr.append(gson.toJson(frd_listVOList));
			SendResponse.writeText(res, outStr.toString());	
		}

		if ("insert".equals(action)) {

		}

		if ("delete".equals(action)) {

		}

		if ("deleteOne".equals(action)) {
		}

		if ("getOne_For_Update".equals(action)) {

		}

		if ("update".equals(action)) {
		}
	}
}
