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


@ServerEndpoint("/ChatEndpoint/{mem_no}/{frd_no}")
public class ChatEndpoint
{
	private static HashMap<Session,String> users = new HashMap<>();
	
	@OnOpen
    public void onOpen(Session session, @PathParam("mem_no") String userId,@PathParam("frd_no") String frd_no) throws IOException, EncodeException {
		System.out.println(session);
        users.put(session, userId);
        System.out.println(userId);
        
    }
	
	@OnMessage
    public void onMessage(Session session, String message) throws IOException {
		
		try
		{
			JSONObject jsonObjectIn = new JSONObject(message);
			Message msg = new Message();
			msg.setFrom(users.get(session.getId()));
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
	
	private static void sendMessageToOneUser(Message message) throws IOException {
		if(users.containsValue(message.getTo())){
			for (Session session : users.keySet()) {
				synchronized(session) {
					if (users.get(session).equals(message.getTo())) {
						session.getAsyncRemote().sendObject(message);
					}
				}
			}
		}
    }
}
