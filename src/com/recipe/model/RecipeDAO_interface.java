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
    public List<RecipeVO> getAllOrderByViews();
    public void updateViews(RecipeVO recipeVO);
    public void updateLike(RecipeVO recipeVO);
    public void changeWeekViewsZero(String recipe_no);
    public List<RecipeVO> findByMem_no(String mem_no);
    public List<RecipeVO> serachByRecipe_name(String recipe_name);
    public List<RecipeVO> serachByFood_Mater(String food_mater);
    
    
  //同時新增食譜與食譜步驟內容 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)
    public void insertWithRecipe_conts(RecipeVO recipeVO , List<Recipe_contVO> list);
    
}