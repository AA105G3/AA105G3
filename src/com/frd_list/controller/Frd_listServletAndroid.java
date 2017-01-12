package com.frd_list.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.member.model.*;
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
		MemberService memberSvc = new MemberService();
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
		System.out.println("Frd_list action = " + action);
		StringBuffer outStr = new StringBuffer();

		if ("getOne_For_Display".equals(action)) {
		}

		if ("getAllByMem_no_For_Display".equals(action)) {
			String mem_noJson = jsonObject.get("mem_no").getAsString();
			JsonReader reader = new JsonReader(new StringReader(mem_noJson));
			reader.setLenient(true);
			String mem_no = gson.fromJson(reader, String.class);
			List<Frd_listVO> frd_listVOList = frd_listSvc.getAllByMem_noFrd_list(mem_no);

			List<HashMap> frd_listMemberVOList = new ArrayList<HashMap>();

			List<HashMap> frd_listMemberVOList_0 = new ArrayList<HashMap>();
			List<HashMap> frd_listMemberVOList_1 = new ArrayList<HashMap>();
			List<HashMap> frd_listMemberVOList_2 = new ArrayList<HashMap>();

			MemberVO memberVO = new MemberVO();
			for (Frd_listVO frd_list : frd_listVOList) {
				HashMap hashmap = new HashMap();
				memberVO = memberSvc.getOneMember(frd_list.getFriend_no(), 1);
				hashmap.put("friend_no", frd_list.getFriend_no());
				hashmap.put("friend_chk", frd_list.getFriend_chk());
				hashmap.put("mem_no", frd_list.getMem_no());
				hashmap.put("mem_name", memberVO.getMem_name());
				hashmap.put("mem_own", memberVO.getMem_own());
				hashmap.put("mem_online", memberVO.getMem_online());

				// 好友排序
				if (frd_list.getFriend_chk().equals("0")) {
					frd_listMemberVOList_0.add(hashmap);
				} else if (frd_list.getFriend_chk().equals("1")) {
					frd_listMemberVOList_1.add(hashmap);
				} else if (frd_list.getFriend_chk().equals("2")) {
					frd_listMemberVOList_2.add(hashmap);
				}

				// System.out.println(hashmap.get("friend_chk"));
			}
			frd_listMemberVOList.addAll(frd_listMemberVOList_2);
			frd_listMemberVOList.addAll(frd_listMemberVOList_0);
			frd_listMemberVOList.addAll(frd_listMemberVOList_1);
			// List<Frd_listVO, MemberVO> list2= new ArrayList<Frd_listVO,
			// MemberVO>;

			outStr.append(gson.toJson(frd_listMemberVOList));
			// outStr.append(gson.toJson(frd_listVOList));
			SendResponse.writeText(res, outStr.toString());
			return;
		}

		if ("insert".equals(action)) {
			String mem_no = jsonObject.get("mem_no").getAsString();
			String friend_no = jsonObject.get("friend_no").getAsString();
			System.out.println("mem_no"+mem_no+" friend_no"+friend_no);
			Frd_listVO frd_listVO = new Frd_listVO();
			boolean result = false;
			if (frd_listSvc.findByTwoPrimaryKey("mem_no", "friend_no") == null) { //未有好友關係，可邀請對方
				frd_listSvc.addFrd_list(mem_no, friend_no, "1");
				frd_listSvc.addFrd_list(friend_no, mem_no, "0");
				System.out.println("111111111111111111111111");

			} else if(frd_listSvc.findByTwoPrimaryKey("mem_no", "friend_no").getFriend_chk().equals("0")){ // 有好友關係，且被邀請中，則變成互為好友
				frd_listSvc.updateFrd_list(mem_no, friend_no, "2");
				frd_listSvc.updateFrd_list(friend_no, mem_no, "2");
				System.out.println("22222222222222222222222222222");

			}else {// 有好友關係，且邀請中或互為好友，則關係不變
				System.out.println("000000000000000000000000000000");

			}
			frd_listVO =frd_listSvc.findByTwoPrimaryKey("mem_no", "friend_no");
			
			outStr.append(gson.toJson(frd_listVO));
			SendResponse.writeText(res, outStr.toString());
			return;

		}

		if ("delete".equals(action)) {

		}

		if ("deleteOne".equals(action)) {

			String frd_listJson = jsonObject.get("frd_listVO").getAsString();
			Frd_listVO frd_listVO = gson.fromJson(frd_listJson, Frd_listVO.class);
			System.out.println("frd_listVO.getMem_no()" + frd_listVO.getMem_no() + "frd_listVO.getMem_no()"
					+ frd_listVO.getFriend_no() + "frd_listVO.getMem_no()" + frd_listVO.getFriend_chk());
			Frd_listVO frd_listVOtmp = new Frd_listVO();

			frd_listVOtmp.setMem_no(frd_listVO.getFriend_no());
			frd_listVOtmp.setFriend_no(frd_listVO.getMem_no());

			frd_listSvc.deleteOneFrd_list(frd_listVO);
			frd_listSvc.deleteOneFrd_list(frd_listVOtmp);

			outStr.append(gson.toJson(frd_listVO));
			SendResponse.writeText(res, outStr.toString());
			return;

		}

		if ("getOne_For_Update".equals(action)) {

		}

		if ("updateAgree".equals(action)) {
			String frd_listJson = jsonObject.get("frd_listVO").getAsString();
			Frd_listVO frd_listVO = gson.fromJson(frd_listJson, Frd_listVO.class);
			System.out.println("frd_listVO.getMem_no():" + frd_listVO.getMem_no());
			System.out.println("frd_listVO.getFriend_no():" + frd_listVO.getFriend_no());
			System.out.println("frd_listVO.getFriend_chk():" + frd_listVO.getFriend_chk());
			frd_listVO = frd_listSvc.updateFrd_list(frd_listVO.getMem_no(), frd_listVO.getFriend_no(), "2");
			frd_listVO = frd_listSvc.updateFrd_list(frd_listVO.getFriend_no(), frd_listVO.getMem_no(), "2");

			SendResponse.writeText(res, outStr.toString());
			return;
		}

		if ("updateDeny".equals(action)) {
			String frd_listJson = jsonObject.get("frd_listVO").getAsString();
			Frd_listVO frd_listVO = gson.fromJson(frd_listJson, Frd_listVO.class);

			Frd_listVO frd_listVOtmp = new Frd_listVO();

			frd_listVOtmp.setMem_no(frd_listVO.getFriend_no());
			frd_listVOtmp.setFriend_no(frd_listVO.getMem_no());

			frd_listSvc.deleteOneFrd_list(frd_listVO);
			frd_listSvc.deleteOneFrd_list(frd_listVOtmp);

			SendResponse.writeText(res, outStr.toString());
			return;
		}
	}
}
