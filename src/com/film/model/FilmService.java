package com.film.model;

import java.util.List;

public class FilmService {

	private FilmDAO_interface dao;

	public FilmService() {
		dao = new FilmDAO();
	}
	
	public FilmVO addFilm(String recipe_no, byte[] film_file){
		
		FilmVO filmVO = new FilmVO();
		
		filmVO.setRecipe_no(recipe_no);
		filmVO.setFilm_file(film_file);
		dao.insert(filmVO);
		
		return filmVO;
	}	
	
	public FilmVO updateFilm(String recipe_no, byte[] film_file){
		
		FilmVO filmVO = new FilmVO();
		
		filmVO.setRecipe_no(recipe_no);
		filmVO.setFilm_file(film_file);
		dao.update(filmVO);
		
		return filmVO;
	}

	public FilmVO getOneMember(String recipe_no) {
		return dao.findByPrimaryKey(recipe_no);
	}

	public List<FilmVO> getAll() {
		return dao.getAll();
	}
	
}
