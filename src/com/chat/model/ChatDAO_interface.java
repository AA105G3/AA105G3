package com.chat.model;

import java.util.List;



public interface ChatDAO_interface {

	public int insert(ChatVO chatVO);
    public int update(ChatVO chatVO);
    public int delete(String chat_no);
    public ChatVO findByPrimaryKey(String chat_no);
    public List<ChatVO> getAll();
	
	
	
}
