package com.chat.model;

import java.util.List;



public interface ChatDAO_interface {

	public void insert(ChatVO chatVO);
    public void update(ChatVO chatVO);
    public void delete(String chat_no);
    public ChatVO findByPrimaryKey(String chat_no);
    public List<ChatVO> getAll();
	
	
	
}
