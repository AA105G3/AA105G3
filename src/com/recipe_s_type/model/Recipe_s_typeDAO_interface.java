package com.recipe_s_type.model;

import java.util.List;

public interface Recipe_s_typeDAO_interface {
    public void insert(Recipe_s_typeVO recipe_s_typeVO);
    public void update(Recipe_s_typeVO recipe_s_typeVO);
    public void delete(String mem_no);
 //   public void deleteOne(Recipe_s_typeVO recipe_s_typeVO);
    public Recipe_s_typeVO findByPrimaryKey(String recipe_s_type_no);
    public List<Recipe_s_typeVO> getAll();
	
}
