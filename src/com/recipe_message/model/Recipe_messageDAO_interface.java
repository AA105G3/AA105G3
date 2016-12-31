package com.recipe_message.model;

import java.util.List;

public interface Recipe_messageDAO_interface {
	
	public void insert(Recipe_messageVO recipe_messageVO);
    public void update(Recipe_messageVO recipe_messageVO);
    public void delete(String recipe_msg_no);
    public Recipe_messageVO findByPrimaryKey(String recipe_msg_no);
    public List<Recipe_messageVO> getAll();
	
}

