package com.recipe.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.recipe_cont.model.Recipe_contVO;


public class RecipeService
{
	private RecipeDAO_interface dao;
		
	public RecipeService() {
		dao = new RecipeDAO();
	}
	public RecipeVO addRecipe(String mem_no,String recipe_name,String recipe_intro,String food_mater,byte[] recipe_pic) {

		RecipeVO recipeVO = new RecipeVO();

		recipeVO.setMem_no(mem_no);
		recipeVO.setRecipe_name(recipe_name);
		recipeVO.setRecipe_intro(recipe_intro);
		recipeVO.setFood_mater(food_mater);
		recipeVO.setRecipe_pic(recipe_pic);
		dao.insert(recipeVO);

		return recipeVO;
	}
	
	public RecipeVO addRecipeWith_Recipe_conts(String mem_no,String recipe_name,String recipe_intro,String food_mater,byte[] recipe_pic,String recipe_edit,List<Recipe_contVO> list) {


		RecipeVO recipeVO = new RecipeVO();

		recipeVO.setMem_no(mem_no);
		recipeVO.setRecipe_name(recipe_name);
		recipeVO.setRecipe_intro(recipe_intro);
		recipeVO.setFood_mater(food_mater);
		recipeVO.setRecipe_pic(recipe_pic);
		recipeVO.setRecipe_edit(recipe_edit);
		
		dao.insertWithRecipe_conts(recipeVO, list);
		
		return recipeVO;
	}
	
	

	public RecipeVO updateRecipe(String recipe_no,String recipe_name,String recipe_intro,String food_mater,byte[] recipe_pic,String recipe_edit) {

		RecipeVO recipeVO = new RecipeVO();

		recipeVO.setRecipe_no(recipe_no);
		recipeVO.setRecipe_name(recipe_name);
		recipeVO.setRecipe_intro(recipe_intro);
		recipeVO.setFood_mater(food_mater);
		recipeVO.setRecipe_pic(recipe_pic);
		recipeVO.setRecipe_edit(recipe_edit);
		dao.update(recipeVO);

		return recipeVO;
	}
	
	public RecipeVO updateClassify(String recipe_no,String recipe_classify) {

		RecipeVO recipeVO = new RecipeVO();

		recipeVO.setRecipe_no(recipe_no);
		recipeVO.setRecipe_classify(recipe_classify);
		
		dao.updateClassify(recipeVO);

		return recipeVO;
	}
	
	

	public RecipeVO updateRecipeViews(String recipe_no ,Integer recipe_total_views,Integer recipe_week_views) {

		RecipeVO recipeVO = new RecipeVO();

		recipeVO.setRecipe_no(recipe_no);
		recipeVO.setRecipe_total_views(recipe_total_views);
		recipeVO.setRecipe_week_views(recipe_week_views);
		dao.updateViews(recipeVO);

		return recipeVO;
	}
	
	public RecipeVO updateRecipeLike(String recipe_no ,Integer recipe_like) {

		RecipeVO recipeVO = new RecipeVO();

		recipeVO.setRecipe_no(recipe_no);
		recipeVO.setRecipe_like(recipe_like);
		dao.updateLike(recipeVO);

		return recipeVO;
	}
	
	public void deleteRecipe(String recipe_no) {
		dao.delete(recipe_no);
	}

	public RecipeVO getOneRecipe(String recipe_no) {
		RecipeVO recipeVO = dao.findByPrimaryKey(recipe_no);
		if(recipeVO.getRecipe_edit().equals("已刪除")){
			recipeVO=null;
		}
		return recipeVO;
	}
	public List<RecipeVO> getNotClassified() {
		
		List<RecipeVO> list = dao.getAll();
		List<RecipeVO> list2 = new ArrayList<RecipeVO>();
		
		for(RecipeVO aRecipe : list){
			if(aRecipe.getRecipe_classify().equals("未分類")){
				list2.add(aRecipe);
			}
		}
		return list2;
	}
	
	public List<RecipeVO> getClassified() {
		
		List<RecipeVO> list = dao.getAll();
		List<RecipeVO> list2 = new ArrayList<RecipeVO>();
		
		for(RecipeVO aRecipe : list){
			if(aRecipe.getRecipe_classify().equals("已分類")){
				list2.add(aRecipe);
			}
		}
		return list2;
	}
	
	public List<RecipeVO> getAll() {
		
		List<RecipeVO> list = dao.getAll();
		List<RecipeVO> list2 = new ArrayList<RecipeVO>();
		
		for(RecipeVO aRecipe : list){
			if(aRecipe.getRecipe_edit().equals("編輯中")||aRecipe.getRecipe_edit().equals("已發布")){
				list2.add(aRecipe);
			}
		}
		return list2;
	}
	
	public void WeekViewsToZero(String recipe_no){
		
		dao.changeWeekViewsZero(recipe_no);
	}
	
	public List<RecipeVO> findByMem_no(String mem_no) {
		
		List<RecipeVO> list = dao.findByMem_no(mem_no);
		List<RecipeVO> list2 = new ArrayList<RecipeVO>();
		
		for(RecipeVO aRecipe : list){
			if(aRecipe.getRecipe_edit().equals("編輯中")||aRecipe.getRecipe_edit().equals("已發布")){
				list2.add(aRecipe);
			}
		}
		return list2;
	}
	
	public List<RecipeVO> topViewsRecipe() {
		List<RecipeVO> list = dao.getAllOrderByViews();
		List<RecipeVO> list2 = new ArrayList<RecipeVO>();
		for(RecipeVO aRecipe:list){
			
			if(aRecipe.getRecipe_intro()!=null&&aRecipe.getRecipe_intro().length()>64){
				String introSbsr= aRecipe.getRecipe_intro().substring(0,65)+"...";
				aRecipe.setRecipe_intro(introSbsr);
			}
			
			String str = aRecipe.getFood_mater();
			String[] tokens = str.split("-|\\+");
			
			StringBuffer ingredients = new StringBuffer();
			
			ingredients.append(tokens[0]);
			for(int i =2;i<tokens.length-1;i+=2){
				ingredients.append("、"+tokens[i]);
			}
			
			String food_maters = null;
			if(ingredients.length()>38){
				food_maters = ingredients.substring(0,39)+"..."; 
				
			}else{
				food_maters = new String(ingredients);
			}
			
			aRecipe.setFood_mater(new String(food_maters));
			
			if(aRecipe.getRecipe_edit().equals("編輯中")||aRecipe.getRecipe_edit().equals("已發布")){
				list2.add(aRecipe);
			}
		}
		return list2;
		
	}
	public List<RecipeVO> serachClassified(String recipe_name){
		List<RecipeVO> list= dao.serachByRecipe_name(recipe_name);
		List<RecipeVO> list2 = new ArrayList<RecipeVO>();
		for(RecipeVO aRecipe:list){
			if(aRecipe.getRecipe_classify().equals("已分類")){
				list2.add(aRecipe);
			}
		}
		return list2;
	}
	
	public List<RecipeVO> serachByRecipe_name(String recipe_name){
		
		List<RecipeVO> list= dao.serachByRecipe_name(recipe_name);
		List<RecipeVO> list2 = new ArrayList<RecipeVO>();
		for(RecipeVO aRecipe:list){
			
			if(aRecipe.getRecipe_intro()!=null&&aRecipe.getRecipe_intro().length()>64){
				String introSbsr= aRecipe.getRecipe_intro().substring(0,65)+"...";
				aRecipe.setRecipe_intro(introSbsr);
			}
			
			String str = aRecipe.getFood_mater();
			String[] tokens = str.split("-|\\+");
			
			StringBuffer ingredients = new StringBuffer();
			
			ingredients.append(tokens[0]);
			for(int i =2;i<tokens.length-1;i+=2){
				ingredients.append("、"+tokens[i]);
			}
			
			String food_maters = null;
			if(ingredients.length()>38){
				food_maters = ingredients.substring(0,39)+"..."; 
				
			}else{
				food_maters = new String(ingredients);
			}
			
			aRecipe.setFood_mater(new String(food_maters));
			
			if(aRecipe.getRecipe_edit().equals("編輯中")||aRecipe.getRecipe_edit().equals("已發布")){
				list2.add(aRecipe);
			}
			
		}
		
		return list2;
	}
	public List<RecipeVO> serachByFood_Mater(String food_mater){
		
		List<RecipeVO> list= dao.serachByFood_Mater(food_mater);
		List<RecipeVO> list2 = new ArrayList<RecipeVO>();
		for(RecipeVO aRecipe:list){
			
			if(aRecipe.getRecipe_intro()!=null&&aRecipe.getRecipe_intro().length()>64){
				String introSbsr= aRecipe.getRecipe_intro().substring(0,65)+"...";
				aRecipe.setRecipe_intro(introSbsr);
			}
			
			String str = aRecipe.getFood_mater();
			String[] tokens = str.split("-|\\+");
			
			StringBuffer ingredients = new StringBuffer();
			
			ingredients.append(tokens[0]);
			for(int i =2;i<tokens.length-1;i+=2){
				ingredients.append("、"+tokens[i]);
			}
			
			String food_maters = null;
			if(ingredients.length()>38){
				food_maters = ingredients.substring(0,39)+"..."; 
				
			}else{
				food_maters = new String(ingredients);
			}
			
			aRecipe.setFood_mater(new String(food_maters));
			
			if(aRecipe.getRecipe_edit().equals("編輯中")||aRecipe.getRecipe_edit().equals("已發布")){
				list2.add(aRecipe);
			}
			
		}
		return list2;
	}
	public List<RecipeVO> getNewest(){
		List<RecipeVO> list = dao.getAll();
		List<RecipeVO> list2 = new ArrayList<RecipeVO>();
		for(RecipeVO aRecipe:list){
			
			if(aRecipe.getRecipe_intro()!=null&&aRecipe.getRecipe_intro().length()>64){
				String introSbsr= aRecipe.getRecipe_intro().substring(0,65)+"...";
				aRecipe.setRecipe_intro(introSbsr);
			}
			
			String str = aRecipe.getFood_mater();
			String[] tokens = str.split("-|\\+");
			
			StringBuffer ingredients = new StringBuffer();
			
			ingredients.append(tokens[0]);
			for(int i =2;i<tokens.length-1;i+=2){
				ingredients.append("、"+tokens[i]);
			}
			
			String food_maters = null;
			if(ingredients.length()>38){
				food_maters = ingredients.substring(0,39)+"..."; 
				
			}else{
				food_maters = new String(ingredients);
			}
			
			aRecipe.setFood_mater(new String(food_maters));
			
			if(aRecipe.getRecipe_edit().equals("編輯中")||aRecipe.getRecipe_edit().equals("已發布")){
				list2.add(aRecipe);
			}
		}
		return list2;
	}
	
	//for android by cyh
	public byte[] getImage(String recipe_no) {  
		return dao.getImage(recipe_no);
	}
}
