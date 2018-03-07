package com.tablenine.websocket.cmd;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

@ServerEndpoint(value="/broadcasting", configurator= GetHttpSessionForWebSocket.class)
public class WebSocket {
	
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	private static HashMap<String, HashMap<String, Session>> loginWss = new HashMap<>();
	protected static Logger logger = Logger.getLogger(WebSocket.class);
	
	@OnMessage
	public void onMessage(String message, Session session) {
		
		logger.debug("WCD0101 (C) Msg= " + message);
		
		try {
			synchronized (clients) {
				
				for (Session client : clients) {
					if (!client.equals(session)) {
						client.getBasicRemote().sendText(message);
					}
				}
			}
			
		} catch (IOException e) {
			clients.remove(session);
			logger.debug("WCD0102 (C) IOException"+e);
			logger.debug("WCD0103 (C) remove this session");
			
		}
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		
		// Add session to the connected sessions set
		HttpSession hss = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		
		String userId = (String) hss.getAttribute("user_id");
		String sessId = (String) hss.getId();
		
		logger.info("WCI0201 (C) user_id " + userId);
		logger.info("WCI0202 (C) session " + session);
		logger.info("WCI0203 (C) sess_id " + sessId);
		
		clients.add(session);
		HashMap<String, Session> sessionMap;
		
		if (userId != null) {
			if (loginWss.containsKey(userId)) {
				sessionMap = loginWss.get(userId);
			} else {
				sessionMap = new HashMap<>();
			}
			
			sessionMap.put(sessId, session);
			loginWss.put(userId, sessionMap);
		}
		
		logger.info("WCI0204 (C) sessionSize : " + clients.size());
	}

	@OnClose
	public void onClose(Session session) {
		// Remove session from the connected sessions set
		try {
			clients.remove(session);
		} catch (Exception e) {
			logger.debug("WCI0301 (C) Exception : "+e);
		}
	}
	
	@OnError
	public void onError(Session session, Throwable throwable){
		//onError Method
	}
	
	public void sendToAll(){
		//sendToAll Method
	}
	
	public static void send(String msg, String userId){
		
		if (userId == null ) {
			logger.error("WCE0605 (C) Msg= "+ userId +" is null");
			return;
		}
		try {
			if (loginWss.containsKey(userId)) {
				
				for (Session session:loginWss.get(userId).values()) {
					session.getBasicRemote().sendText(msg);
					logger.debug("WCD0601 (C) message " + msg);
					logger.debug("WCD0602 (C) send User " + loginWss.get(userId));
				}
				
			} else {
				logger.info("WCI0603 (C) NOT Session "+ userId);
			}
			
		} catch (IOException e) {
			logger.error("WCE0604 (C) Msg= : "+ e.getMessage());
			logger.error("WCE0604 (C) IOException : "+ e);
		} catch(IllegalStateException e){
			logger.error("WCE0606 (C) Msg= : "+ e.getMessage());
			logger.error("WCE0606 (C) IllegalStateException : "+ e);
		}
	}

	public static Map<String, HashMap<String, Session>> getLoginWss() {
		return loginWss;
	}
}
