package com.recipe_message.model;


import java.util.List;
import com.recipe_message.model.Recipe_messageDAO;
import com.recipe_message.model.Recipe_messageVO;


public class Recipe_messageService {

	Recipe_messageDAO_interface dao;

	public Recipe_messageService() {

		dao = new Recipe_messageDAO();
	}

	public Recipe_messageVO addRecipe_message(String mem_no, String recipe_no, String msg_cont, String recipe_msg_ref) {

		Recipe_messageVO recipe_messageVO = new Recipe_messageVO();

		recipe_messageVO.setMem_no(mem_no);
		recipe_messageVO.setRecipe_no(recipe_no);
		recipe_messageVO.setMsg_cont(msg_cont);
		recipe_messageVO.setRecipe_msg_ref(recipe_msg_ref);

		dao.insert(recipe_messageVO);

		return recipe_messageVO;
	}

	public Recipe_messageVO updateRecipe_message(String msg_cont, String recipe_msg_ref) {
		Recipe_messageVO recipe_messageVO = new Recipe_messageVO();

		recipe_messageVO.setMsg_cont(msg_cont);
		recipe_messageVO.setRecipe_msg_ref(recipe_msg_ref);

		dao.insert(recipe_messageVO);

		return recipe_messageVO;
	}

	public void deleteRecipe_message(String recipe_msg_no) {
		dao.delete(recipe_msg_no);
	}

	public Recipe_messageVO getOneRecipe_message(String recipe_msg_no) {

		return dao.findByPrimaryKey(recipe_msg_no);
	}
	
	public List<Recipe_messageVO> getAll(){
		return dao.getAll();
	}	

}
