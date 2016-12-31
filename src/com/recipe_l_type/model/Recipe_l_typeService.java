package com.recipe_l_type.model;

import java.util.List;


public class Recipe_l_typeService {

	private Recipe_l_typeDAO_interface dao;
	
	public Recipe_l_typeService(){
		dao = new Recipe_l_typeDAO();
		
	}
	
	public  Recipe_l_typeVO addRecipe_l_type(String l_type_name){
		
		Recipe_l_typeVO recipe_l_typeVO = new Recipe_l_typeVO();
		
		recipe_l_typeVO.setL_type_name(l_type_name);		
		dao.insert(recipe_l_typeVO);
		
		return recipe_l_typeVO;
	}
	
	public Recipe_l_typeVO updateRecipe_l_type(String recipe_l_type_no, String l_type_name ){
		Recipe_l_typeVO recipe_l_typeVO = new Recipe_l_typeVO();
		
		recipe_l_typeVO.setRecipe_l_type_no(recipe_l_type_no);
		recipe_l_typeVO.setL_type_name(l_type_name);		
		dao.update(recipe_l_typeVO);
		
		return recipe_l_typeVO;
	}	
	
	public void deleteRecipe_l_type(String recipe_l_type_no ){
		dao.delete(recipe_l_type_no);
		
	}
	
	public Recipe_l_typeVO getOneRecipe_l_type(String recipe_l_type_no ){
		
		return dao.findByPrimaryKey(recipe_l_type_no);
	}
	
	public List<Recipe_l_typeVO> getAll(){
		return dao.getAll();
	}	
}
