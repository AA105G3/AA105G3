package com.recipe_l_type.model;

import java.util.List;
import java.util.Set;

import com.recipe_m_type.model.Recipe_m_typeVO;



public interface Recipe_l_typeDAO_interface {

	public void insert(Recipe_l_typeVO recipe_l_typeVO);
    public void update(Recipe_l_typeVO recipe_l_typeVO);
    public void delete(String recipe_l_type_no);
    public Recipe_l_typeVO findByPrimaryKey(String recipe_l_type_no);
    public List<Recipe_l_typeVO> getAll();
    //查詢大類別裡的中類別
    public Set<Recipe_m_typeVO> getM_typesByL_Type_No(String parent_type);
	
}


