package com.recipe_m_type.model;

import java.util.*;

import com.recipe_s_type.model.Recipe_s_typeVO;

public interface Recipe_m_typeDAO_interface {
	public void insert(Recipe_m_typeVO recipe_m_typeVO);
	public void update(Recipe_m_typeVO recipe_m_typeVO);
	public void delete(String recipe_m_type_no);
	public Recipe_m_typeVO findByPrimaryKey(String recipe_m_type_no);
	public List<Recipe_m_typeVO> getAll();
	
	 //查詢大類別裡的中類別
    public Set<Recipe_s_typeVO> getS_typesByM_Type_No(String parent_type);

}
