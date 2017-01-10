package com.film.model;

public class FilmVO implements java.io.Serializable {

	private String recipe_no;
	private byte[] film_file;
	
	public String getRecipe_no() {
		return recipe_no;
	}
	public void setRecipe_no(String recipe_no) {
		this.recipe_no = recipe_no;
	}
	public byte[] getFilm_file() {
		return film_file;
	}
	public void setFilm_file(byte[] film_file) {
		this.film_file = film_file;
	}

}
