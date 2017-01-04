package com.recipe_message.model;
import java.sql.Timestamp;



public class Recipe_messageVO implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String  	recipe_msg_no;	//���Яd���s��,PK
	private String  	mem_no;			//�|���s��
	private String  	recipe_no;		//���нs��
	private String 	msg_cont;		//�d�����e
	private String  	recipe_msg_ref;	//�������Яd���s��
	private Timestamp 	msg_time;		//���Яd���ɶ�
	
		
	public String getRecipe_msg_no() {
		return recipe_msg_no;
	}
	public void setRecipe_msg_no(String recipe_msg_no) {
		this.recipe_msg_no = recipe_msg_no;
	}
	
	
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	
	
	public String getRecipe_no() {
		return recipe_no;
	}
	public void setRecipe_no(String recipe_no) {
		this.recipe_no = recipe_no;
	}
	
	
	public String getMsg_cont() {
		return msg_cont;
	}
	public void setMsg_cont(String msg_cont) {
		this.msg_cont = msg_cont;
	}
	
	
	public String getRecipe_msg_ref() {
		return recipe_msg_ref;
	}
	public void setRecipe_msg_ref(String recipe_msg_ref) {
		this.recipe_msg_ref = recipe_msg_ref;
	}
	public Timestamp getMsg_time() {
		return msg_time;
	}
	public void setMsg_time(Timestamp msg_time) {
		this.msg_time = msg_time;
	}
	
	
	
	
	
	
	
}
