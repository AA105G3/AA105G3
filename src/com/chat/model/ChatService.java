package com.chat.model;

import java.util.List;

import com.adv.model.AdvVO;

public class ChatService {
	
	private ChatDAO_interface dao;

	public ChatService() {
		dao = new ChatJNDIDAO();
	}
	
	public ChatVO addChat(String send_no, String get_no, String chat_text){
		
		ChatVO chatVO = new ChatVO();
		

		chatVO.setSend_no(send_no);
		chatVO.setGet_no(get_no);
		chatVO.setChat_text(chat_text);
		dao.insert(chatVO);

		
		return chatVO;
	}
	
	public void deleteChat(String chat_no) {
		dao.delete(chat_no);
	}

	public ChatVO getOneChat(String chat_no) {
		return dao.findByPrimaryKey(chat_no);
	}
	
	public List<ChatVO> getAll() {
		return dao.getAll();
	}


}
