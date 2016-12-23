package com.recipe.model;

import java.util.List;

import com.recipe_cont.model.Recipe_contVO;


public interface RecipeDAO_interface
{
	public void insert(RecipeVO recipeVO);
    public void update(RecipeVO recipeVO);
    public void delete(String recipe_no);
    public RecipeVO findByPrimaryKey(String recipe_no);
    public List<RecipeVO> getAll();
    public void updateViews(RecipeVO recipeVO);
    public void updateLike(RecipeVO recipeVO);
    public void changeWeekViewsZero(String recipe_no);
    
  //同時新增食譜與食譜步驟內容 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)
    public void insertWithEmps(RecipeVO recipeVO , List<Recipe_contVO> list);
    
}
