package com.mifinity.card.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mifinity.card.model.User;

@Service
public class SessionService {
	private Map<String, String> sessions = new HashMap<>();
	
	public void putSession(String sessionId, String userName) {
		sessions.put(sessionId, userName);
	}
	
	public void removeSession(String sessionId) {
		sessions.remove(sessionId);
	}
	
	public boolean isSessionExists(String sessionId) {
		return sessions.containsKey(sessionId);
	}
}
