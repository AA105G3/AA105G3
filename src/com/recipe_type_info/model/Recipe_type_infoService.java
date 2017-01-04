package com.recipe_type_info.model;

import java.util.List;
import java.util.Set;



public class Recipe_type_infoService
{
	private Recipe_type_infoDAO_interface dao;

	public Recipe_type_infoService() {
		dao = new Recipe_type_infoDAO();
	}
	
	public Recipe_type_infoVO addRecipe_type_info(String recipe_no,String recipe_type_no,String type_range) {

		Recipe_type_infoVO recipe_type_infoVO = new Recipe_type_infoVO();
		
		recipe_type_infoVO.setRecipe_no(recipe_no);
		recipe_type_infoVO.setRecipe_type_no(recipe_type_no);
		recipe_type_infoVO.setType_range(type_range);
		dao.insert(recipe_type_infoVO);

		return recipe_type_infoVO;
	}
	
	public List<Recipe_type_infoVO> getAll() {
		return dao.getAll();
	}
	
	public List<Recipe_type_infoVO> getRecipe_type_infoByRecipe_no(String recipe_no) {
		return dao.findByPrimaryKey(recipe_no);
	}
	
	public void deleteRecipe_type_info(String recipe_no) {
		dao.delete(recipe_no);
	}
	
	public void deleteOneType_info(String recipe_no,String recipe_type_no){
		dao.deleteOneType(recipe_no, recipe_type_no);
		
	}
	public Set<Recipe_type_infoVO> findByType_no(String recipe_type_no){
		
		return dao.findByType_no(recipe_type_no);
	}
	
}
