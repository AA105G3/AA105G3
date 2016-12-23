package com.recipe_cont.model;

import java.util.List;
import java.util.Set;


public interface Recipe_contDAO_interface
{
	public void insert(Recipe_contVO recipe_contVO);
    public void update(Recipe_contVO recipe_contVO);
    public void delete(String recipe_no);
    public void deleteOneStep(String recipe_no,Integer step);
    public Recipe_contVO getOneCont(String recipe_no,Integer step);
    public Set<Recipe_contVO> findByPrimaryKey(String recipe_no);
    public List<Recipe_contVO> getAll();
    
  //同時新增食譜與食譜步驟內容 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)
    public void insert2 (Recipe_contVO recipe_contVO , java.sql.Connection con);
}
