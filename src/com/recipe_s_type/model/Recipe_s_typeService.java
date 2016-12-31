package com.recipe_s_type.model;

import java.util.List;

import com.recipe_s_type.model.Recipe_s_typeDAO;
import com.recipe_s_type.model.Recipe_s_typeVO;

public class Recipe_s_typeService {

	Recipe_s_typeDAO_interface dao;
	
	public Recipe_s_typeService() {

		dao = new Recipe_s_typeDAO();
	}
	
	public Recipe_s_typeVO addRecipe_s_type(String s_type_name) {

		Recipe_s_typeVO recipe_s_typeVO = new Recipe_s_typeVO();

		recipe_s_typeVO.setS_type_name(s_type_name);
		dao.insert(recipe_s_typeVO);

		return recipe_s_typeVO;
	}
	
	public Recipe_s_typeVO updateRecipe_s_type(String recipe_s_type_no, String s_type_name, String  parent_type ){
		Recipe_s_typeVO recipe_s_typeVO = new Recipe_s_typeVO();
		
		recipe_s_typeVO.setRecipe_s_type_no(recipe_s_type_no);
		recipe_s_typeVO.setS_type_name(s_type_name);
		recipe_s_typeVO.setParent_type(parent_type);
		dao.update(recipe_s_typeVO);
		
		return recipe_s_typeVO;
	}	
	
	public void deleteRecipe_s_type(String recipe_s_type_no ){
		dao.delete(recipe_s_type_no);
	}
	
	public Recipe_s_typeVO getOneRecipe_s_type(String recipe_s_type_no ){
		
		return dao.findByPrimaryKey(recipe_s_type_no);
	}
	
	public List<Recipe_s_typeVO> getAll(){
		return dao.getAll();
	}		
	
	
}
