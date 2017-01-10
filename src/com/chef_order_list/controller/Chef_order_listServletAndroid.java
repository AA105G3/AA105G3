package com.chef_order_list.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.chef_order_list.model.*;
import com.collection.model.CollectionService;
import com.collection.model.CollectionVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import util.SendResponse;

@SuppressWarnings("serial")
@WebServlet("/Chef_order_listServletAndroid")
public class Chef_order_listServletAndroid extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Chef_order_listService chef_order_listSvc = new Chef_order_listService();
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
		System.out.println("Chef_order_list action = " + action);
		StringBuffer outStr = new StringBuffer();

		if ("getOne_For_Display".equals(action) || "getOne_For_Chef".equals(action)
				|| "getOne_For_Back".equals(action)) {

		}

		if ("getOne_For_Update".equals(action) || "getOne_For_ChefCheck".equals(action)
				|| "getOne_For_BackCheck".equals(action)) {

		}

		if ("update".equals(action)) {

		}

		if ("insert".equals(action)) {
//			String mem_no, String chef_no, Double chef_ord_cost, ;
//			Timestamp chef_act_date,String chef_ord_place, String chef_ord_cnt;
			
			String mem_no = jsonObject.get("mem_no").getAsString();
			String chef_no = jsonObject.get("chef_no").getAsString();
			Double chef_ord_cost = jsonObject.get("chef_ord_cost").getAsDouble();
			String chef_act_date = jsonObject.get("chef_act_date").getAsString();
			String chef_ord_place = jsonObject.get("chef_ord_place").getAsString();
			String chef_ord_cnt = jsonObject.get("chef_ord_cnt").getAsString();
			
			System.out.println("chef_act_date:" + chef_act_date);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //("yyyy-MM-dd HH:mm:ss")
			String tt = chef_act_date + " 03:04:05";
			System.out.println("tt:" + tt);
//			String time = df.format(chef_act_date);
			Timestamp chef_act_date_timestamp = Timestamp.valueOf(tt);
			
			
			Chef_order_listVO chef_order_listVO = chef_order_listSvc.addChef_order_list(mem_no, chef_no, chef_ord_cost, 
					chef_act_date_timestamp, chef_ord_place, chef_ord_cnt);
			
//			if (chef_order_listVO == null) {
//				chef_order_listVO = chef_order_listSvc.addCollection(mem_no, all_no, class_no);
//			} else {
//				collectionVO = null;
//			}

			// System.out.println("collectionVO= " + collectionVO.getAll_no());

			outStr.append(gson.toJson(chef_order_listVO));
			SendResponse.writeText(res, outStr.toString());

		}

		if ("delete".equals(action)) {

		}
	}
}
