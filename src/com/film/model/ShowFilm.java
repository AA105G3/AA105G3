package com.film.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.film.model.*;


@WebServlet("/film/ShowFilm.do")
public class ShowFilm extends HttpServlet {

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("audio/mpeg");
		OutputStream out = res.getOutputStream();
		
		try
		{
			String recipe_no = req.getParameter("recipe_no");
			FilmService filmSvc = new FilmService();
			FilmVO filmVO = filmSvc.getOneMember(recipe_no);
			
			byte[] buffer = filmVO.getFilm_file();
			out.write(buffer);
		} catch (Exception e)
		{
			
		}
		
	}


}
