package com.websocket.chat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

import com.chef.model.ChefService;
import com.chef.model.ChefVO;
import com.member.model.MemberService;

import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;


@ServerEndpoint("/InviteEndpoint/{mem_no}")
public class InviteEndpoint {
	private static HashMap<String,Session> users = new HashMap<>();
	private static HashMap<String,String> userName = new HashMap<>();
	private static HashMap<String,String> chefName = new HashMap<>();
	
   
	@OnOpen
    public void onOpen(Session session, @PathParam("mem_no") String userId) throws IOException, EncodeException {
        MemberService memberSvc = new MemberService();
        ChefService chefSvc = new ChefService();
        users.put(userId, session);
        	if(userName.containsKey(userId)==false){
            	String mem_name = (memberSvc.getOneMember(userId)).getMem_name();
            	userName.put(userId, mem_name);
            }
        
        	if(chefName.containsKey(userId)==false){
        		ChefVO chefVO = chefSvc.getOneChefByMem_no(userId);
            	chefName.put(userId, chefVO.getChef_name());
            }
    }
	
	@OnMessage
    public void onMessage(Session session, String message) throws IOException {
		
		try
		{
			System.out.println(session.getId());
			JSONObject jsonObjectIn = new JSONObject(message);
			Message msg = new Message();
			msg.setFrom(getFromNo(session));
			msg.setMemberName(getMemberName(session));
			msg.setTo(jsonObjectIn.getString("to"));
			msg.setContent(jsonObjectIn.getString("content"));
			
			sendMessageToOneUser(msg);
		} catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	@OnClose
	public void onClose(Session Session, CloseReason reason) {
		
		for(String userId:users.keySet()){
			if(Session.equals(users.get(userId))){
				users.remove(userId);
			}
		}
		
		System.out.println(Session.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}
	@OnError
	public void onError(Session userSession, Throwable e){
//		e.printStackTrace();
	}
	
	private static String getMemberName(Session session){
		
		if(users.containsValue(session)){
			for(String userId : users.keySet()){
				if(session.equals(users.get(userId))){
					
					if(chefName.containsKey(userId)){
						
						return (userName.get(userId) +"("+chefName.get(userId)+")");
					}
					return userName.get(userId);
				}
			}
		}
		return null;
	}
	private static String getFromNo(Session session){
		
		if(users.containsValue(session)){
			for(String userId : users.keySet()){
				if(session.equals(users.get(userId))){
					return userId;
				}
			}
		}
		return null;
	}
	
	
	private static void sendMessageToOneUser(Message message) throws IOException {
		
		if(users.containsKey(message.getTo())){
			for (String to : users.keySet()) {
				if (to.equals(message.getTo())) {
					JSONObject msg = new JSONObject(); 
					try
					{
						System.out.println(to);
						msg.put("from", message.getFrom());
						msg.put("content", message.getContent());
						msg.put("memberName", message.getMemberName());
					} catch (JSONException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Session session = users.get(to);
					session.getAsyncRemote().sendText(msg.toString());
				}
			}
		}
    }
	
}
