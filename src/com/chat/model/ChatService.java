package com.chat.model;

import java.util.List;

import com.chat.model.ChatVO;
import com.chat.model.ChatDAO;

public class ChatService {
	ChatDAO_interface dao;

	public ChatService() {

		dao = new ChatDAO();

	}

	public ChatVO addChat(String send_no, String get_no, String chat_text) {

		ChatVO chatVO = new ChatVO();

		chatVO.setSend_no(send_no);
		chatVO.setGet_no(get_no);
		chatVO.setChat_text(chat_text);
		dao.insert(chatVO);

		return chatVO;
	}

	public ChatVO updateChat(String send_no, String get_no, String chat_text, String chat_no) {
		ChatVO chatVO = new ChatVO();

		chatVO.setSend_no(send_no);
		chatVO.setGet_no(get_no);
		chatVO.setChat_text(chat_text);
		chatVO.setChat_no(chat_no);
		dao.update(chatVO);

		return chatVO;
	}

	public void deleteChat(String chat_no) {
		dao.delete(chat_no);

	}

	public ChatVO getOneChat(String chat_no){
		
		return dao.findByPrimaryKey(chat_no);
	}
	
	public List<ChatVO> getAll(){
		return dao.getAll();
	}
	
}
