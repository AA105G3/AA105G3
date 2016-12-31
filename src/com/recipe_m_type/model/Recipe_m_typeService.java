package com.recipe_m_type.model;

import java.util.List;


public class Recipe_m_typeService {

	Recipe_m_typeDAO_interface dao;

	public Recipe_m_typeService() {

		dao = new Recipe_m_typeDAO();
	}

	public Recipe_m_typeVO addRecipe_m_type(String m_type_name) {

		Recipe_m_typeVO recipe_m_typeVO = new Recipe_m_typeVO();

		recipe_m_typeVO.setM_type_name(m_type_name);
		dao.insert(recipe_m_typeVO);

		return recipe_m_typeVO;
	}
	
	public Recipe_m_typeVO updateRecipe_m_type(String recipe_m_type_no, String m_type_name, String  parent_type ){
		Recipe_m_typeVO recipe_m_typeVO = new Recipe_m_typeVO();
		
		recipe_m_typeVO.setRecipe_m_type_no(recipe_m_type_no);
		recipe_m_typeVO.setM_type_name(m_type_name);
		recipe_m_typeVO.setParent_type(parent_type);
		dao.update(recipe_m_typeVO);
		
		return recipe_m_typeVO;
	}	
	
	public void deleteRecipe_m_type(String recipe_m_type_no ){
		dao.delete(recipe_m_type_no);
	}
	
	public Recipe_m_typeVO getOneRecipe_m_type(String recipe_m_type_no ){
		
		return dao.findByPrimaryKey(recipe_m_type_no);
	}
	
	public List<Recipe_m_typeVO> getAll(){
		return dao.getAll();
	}		

}
