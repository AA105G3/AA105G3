package com.recipe_type_info.controler;

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

import com.emp.model.EmpService;
import com.recipe.model.*;
import com.recipe_m_type.model.Recipe_m_typeService;
import com.recipe_m_type.model.Recipe_m_typeVO;
import com.recipe_s_type.model.Recipe_s_typeService;
import com.recipe_s_type.model.Recipe_s_typeVO;
import com.recipe_type_info.model.*;


@WebServlet("/recipe_type_info/recipe_type_info_android.do")

public class Recipe_type_infoServletAndroid extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { 
		}
		
		if ("findByTypeNo".equals(action)) { 
		}
		
		if ("insert".equals(action)) {   
		}
		
		if ("delete".equals(action)) { 
		}

		if ("deleteOne".equals(action)) { 
		}
	}

}
