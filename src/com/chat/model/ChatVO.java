package com.chat.model;

import java.sql.*;

public class ChatVO implements java.io.Serializable {

	private String 		chat_no;   //訊息編號,PK	
	private String 		send_no;   //發送人編號		
	private String 		get_no;	   //接收人編號			
	private String 		chat_text; //訊息內容		
	private Timestamp   chat_date; //發送時間
	
	
	public String getChat_no() {
		return chat_no;
	}
	public void setChat_no(String chat_no) {
		this.chat_no = chat_no;
	}
	
	
	public String getSend_no() {
		return send_no;
	}
	public void setSend_no(String send_no) {
		this.send_no = send_no;
	}
	
	
	public String getGet_no() {
		return get_no;
	}
	public void setGet_no(String get_no) {
		this.get_no = get_no;
	}
	
	
	public String getChat_text() {
		return chat_text;
	}
	public void setChat_text(String chat_text) {
		this.chat_text = chat_text;
	}
	
	
	public Timestamp getChat_date() {
		return chat_date;
	}
	public void setChat_date(Timestamp chat_date) {
		this.chat_date = chat_date;
	}	

}
