package com.collection.controller;

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

import com.collection.model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.collection.model.CollectionService;
import com.collection.model.CollectionVO;

import util.SendResponse;

@SuppressWarnings("serial")
@WebServlet("/CollectionServletAndroid")
public class CollectionServletAndroid extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		CollectionService collectionSvc = new CollectionService();
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
		System.out.println("Collection action = " + action);
		StringBuffer outStr = new StringBuffer();

		if ("getOne_For_Display".equals(action)) {

		}

		if ("getAllByMem_no_For_Display".equals(action)) {
			String mem_noJson = jsonObject.get("mem_no").getAsString();
			JsonReader reader = new JsonReader(new StringReader(mem_noJson));
			reader.setLenient(true);
			String mem_no = gson.fromJson(reader, String.class);
			System.out.println("CollectionServletAndroid(63 line) mem_no:" + mem_no);
			List<CollectionVO> collectionVOList = collectionSvc.getAllByMem_noCollection(mem_no);

			outStr.append(gson.toJson(collectionVOList));
			SendResponse.writeText(res, outStr.toString());
			return;
		}

		if ("insert".equals(action)) {
			String mem_no = jsonObject.get("mem_no").getAsString();
			String all_no = jsonObject.get("all_no").getAsString();
			String class_no = String.valueOf(all_no.charAt(0));
			// System.out.println("insert to collection mem_no=" + mem_no + "
			// all_no= " + all_no + " class_no= " + class_no );
			// try {
			CollectionVO collectionVO = collectionSvc.findByMem_noAndAll_no(mem_no, all_no);
			// } catch (Exception e) {
			// collectionSvc.addCollection(mem_no, all_no, class_no);
			// }
			// System.out.println("all no " + collectionVO.getAll_no() +
			// "coll_no" + collectionVO.getColl_no()
			// + collectionVO.getMem_no());
			if (collectionVO == null) {
				collectionVO = collectionSvc.addCollection(mem_no, all_no, class_no);
			} else {
				collectionVO = null;
			}

			// System.out.println("collectionVO= " + collectionVO.getAll_no());

			outStr.append(gson.toJson(collectionVO));
			SendResponse.writeText(res, outStr.toString());
		}

		if ("delete".equals(action)) {
			String mem_no = jsonObject.get("mem_no").getAsString();
			String coll_no = jsonObject.get("coll_no").getAsString();
			collectionSvc.deleteCollection(coll_no);

			List<CollectionVO> collectionVOList = collectionSvc.getAllByMem_noCollection(mem_no);

			outStr.append(gson.toJson(collectionVOList));
			SendResponse.writeText(res, outStr.toString());
			return;
		}

		if ("deleteByMem_noAndAll_no".equals(action)) {
			String mem_no = jsonObject.get("mem_no").getAsString();
			String all_no = jsonObject.get("all_no").getAsString();
			collectionSvc.deleteCollection(collectionSvc.findByMem_noAndAll_no(mem_no, all_no).getColl_no());

			// collectionSvc.deleteCollection(coll_no);

			List<CollectionVO> collectionVOList = collectionSvc.getAllByMem_noCollection(mem_no);

			outStr.append(gson.toJson(collectionVOList));
			SendResponse.writeText(res, outStr.toString());
			return;
		}

		if ("findByMem_noAndAll_no".equals(action)) {
			String mem_no = jsonObject.get("mem_no").getAsString();
			String all_no = jsonObject.get("all_no").getAsString();
			boolean result = false;
			if (collectionSvc.findByMem_noAndAll_no(mem_no, all_no) == null) {
				result = false;
			} else {
				result = true;
			}

			outStr.append(gson.toJson(result));
			SendResponse.writeText(res, outStr.toString());
			return;
		}

		if ("getOne_For_Update".equals(action)) {

		}

		if ("update".equals(action)) {

		}
	}

}
