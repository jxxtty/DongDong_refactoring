package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("ChatMessage")
public class ChatMessage {
	
	private String userId;
	private String message;
	private String sendTime;
	private String chatId;
	public ChatMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChatMessage(String userId, String message, String sendTime, String chatId) {
		super();
		this.userId = userId;
		this.message = message;
		this.sendTime = sendTime;
		this.chatId = chatId;
	}
	@Override
	public String toString() {
		return "ChatMessage [userId=" + userId + ", message=" + message + ", sendTime=" + sendTime + ", chatId="
				+ chatId + "]";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getChatId() {
		return chatId;
	}
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}
	
	
}
