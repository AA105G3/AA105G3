package com.film.model;

import java.util.List;


public interface FilmDAO_interface {
	public void insert(FilmVO filmVO);
	public void update(FilmVO filmVO);
	public FilmVO findByPrimaryKey(String recipe_no);
	public List<FilmVO> getAll();
	public void insert2 (FilmVO filmVO , java.sql.Connection con);
}
