package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("ChatRoom")
public class ChatRoom {
	private String chatId;
	private String bUserid;
	private String sUSerid;
	private String chatHistory;
	
	public ChatRoom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChatRoom(String chatId, String bUserid, String sUSerid, String chatHistory) {
		super();
		this.chatId = chatId;
		this.bUserid = bUserid;
		this.sUSerid = sUSerid;
		this.chatHistory = chatHistory;
	}

	@Override
	public String toString() {
		return "ChatRoom [chatId=" + chatId + ", bUserid=" + bUserid + ", sUSerid=" + sUSerid + ", chatHistory="
				+ chatHistory + "]";
	}

	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	public String getbUserid() {
		return bUserid;
	}

	public void setbUserid(String bUserid) {
		this.bUserid = bUserid;
	}

	public String getsUSerid() {
		return sUSerid;
	}

	public void setsUSerid(String sUSerid) {
		this.sUSerid = sUSerid;
	}

	public String getChatHistory() {
		return chatHistory;
	}

	public void setChatHistory(String chatHistory) {
		this.chatHistory = chatHistory;
	}
	

	
}
