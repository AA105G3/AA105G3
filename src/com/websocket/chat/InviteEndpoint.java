package com.websocket.chat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

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
   
	@OnOpen
    public void onOpen(Session session, @PathParam("mem_no") String userId) throws IOException, EncodeException {
        users.put(userId, session);
        System.out.println(userId+"connected");
        
    }
	
	@OnMessage
    public void onMessage(Session session, String message) throws IOException {
		
		try
		{
			JSONObject jsonObjectIn = new JSONObject(message);
			Message msg = new Message();
			
			msg.setFrom(getMemberName(session));
			
			System.out.println(jsonObjectIn.getString("to"));
			System.out.println(jsonObjectIn.getString("content"));
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
		users.remove(Session);
		System.out.println(Session.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}
	
	private static String getMemberName(Session session){
		
		if(users.containsValue(session)){
			for(String memberName : users.keySet()){
				if(users.get(memberName).equals(session)){
					return memberName;
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
						msg.put("from", message.getFrom());
						msg.put("content", message.getContent());
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
