package com.websocket.chat;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
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

@ServerEndpoint("/ChatEndpoint/{mem_no}/{frd_no}")
public class ChatEndpoint {
	private static HashMap<Session, String> users = new HashMap<>();
	private static HashMap<String, String> userName = new HashMap<>();
	private static HashMap<String, Room> rooms = new HashMap<>();
	private static HashMap<String, String> chefName = new HashMap<>();

	@OnOpen
	public void onOpen(Session session, @PathParam("mem_no") String userId, @PathParam("frd_no") String frd_no)
			throws IOException, EncodeException {

		users.put(session, userId);
		setNameToMap(userId);
		/* 創建room */
		String roomId1 = userId + frd_no;
		String roomId2 = frd_no + userId;
		/* 確認room是否存在 */
		Boolean roomIsExist1 = rooms.containsKey(roomId1.toString());
		Boolean roomIsExist2 = rooms.containsKey(roomId2.toString());

		if (roomIsExist1 == false & roomIsExist2 == false) {
			/* 不存在建新的，並存入user的sessionId以待之後比對用 */
			Room room = new Room(roomId1);
			room.setMeber1(session.getId());
			rooms.put(roomId1, room);
			System.out.println("!!!!!!!!!!!!!!!!!!!roomIsExist1 == false & roomIsExist2 == false");
			/* 取得user在網站註冊的 NickName */
		} else {
			/* 若room已存在,找到該room並存入sessionId */
			if (roomIsExist1) {
				(rooms.get(roomId1)).setMeber2(session.getId());

			} else if (roomIsExist2) {
				(rooms.get(roomId2)).setMeber2(session.getId());
			}
		}System.out.println("room else XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXsession.getId()"+session.getId());
	}

	@OnMessage
	public void onMessage(Session session, String message) throws IOException {

		try {
			JSONObject jsonObjectIn = new JSONObject(message);
			Message msg = new Message();

			msg.setFrom(jsonObjectIn.getString("from"));
			msg.setTo(jsonObjectIn.getString("to"));
			msg.setContent(jsonObjectIn.getString("content"));
			sendMessageToOneUser(msg);
			System.out.println("聊天室測試print:" + " from: " + jsonObjectIn.getString("from") + " |to: "
					+ jsonObjectIn.getString("to") + " |content: " + jsonObjectIn.getString("content"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@OnClose
	public void onClose(Session Session, CloseReason reason) {
		users.remove(Session);
		for(String roomId:rooms.keySet()){
			if((rooms.get(roomId).getMeber1()).equals(Session.getId())){
				rooms.remove(roomId);
			};
			if((rooms.get(roomId).getMeber2()).equals(Session.getId())){
				rooms.remove(roomId);
			};
		}
		System.out.println(Session.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		// e.printStackTrace();
	}

	private static void setNameToMap(String userId) {

		// 存入nickName，若是廚師則再存入真實姓名

		MemberService memberSvc = new MemberService();
		ChefService chefSvc = new ChefService();
		if (userName.containsKey(userId) == false) {
			String mem_name = (memberSvc.getOneMember(userId)).getMem_name();
			userName.put(userId, mem_name);
		}

		if (chefName.containsKey(userId) == false) {
			ChefVO chefVO = chefSvc.getOneChefByMem_no(userId);
			chefName.put(userId, chefVO.getChef_name());
		}
	}

	private static String getMemberName(String userid) throws IOException {

		if (users.containsValue(userid)) {
			if (chefName.containsKey(userid)) {
				return userName.get(userid) + "(" + chefName.get(userid) + ")";
			}
			return userName.get(userid);
		}

		return null;
	}

	private static void sendMessageToOneUser(Message message) throws IOException {
		/* 確認線上真的有這個user */
		if (users.containsValue(message.getTo())) {
			/* 組合可能的roomId */
			String roomId1 = message.getFrom() + message.getTo();
			String roomId2 = message.getTo() + message.getFrom();

			/* 先將訊息存入JSONObject */
			JSONObject msg = new JSONObject();
			try {
				msg.put("memberName", getMemberName(message.getFrom()));
				msg.put("from", message.getFrom());
				msg.put("content", message.getContent());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/* 確定該roomId是否存在,再比對該發訊息者是哪個room的成員 */
			if (rooms.containsKey(roomId1)) {
				/* 透過roomId取得該room實體 */
				Room room = rooms.get(roomId1);
				for (Session session : users.keySet()) {
					/* 取得收訊息者的session並發出訊息 */
					if ((session.getId()).equals(room.getMeber2())) {
						session.getAsyncRemote().sendText(msg.toString());
					}
				}

			} else if (rooms.containsKey(roomId2)) {
				Room room = rooms.get(roomId2);
				for (Session session : users.keySet()) {
					if ((session.getId()).equals(room.getMeber1())) {
						session.getAsyncRemote().sendText(msg.toString());
					}
				}
			}

		}

	}
}
