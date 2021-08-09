package com.dto;

public class Alarm {
	private int aNum;
	private String sender; // 댓글 작성한 회원
	private String receiver; // 글 작성한 회원
	private String type; // 댓글
	private String info; // 글번호
	private String detail; // 글제목
	private int isRead;
	private String aDate; //알림 넣은 날짜
	
	public Alarm() {
		super();
	}

	

	public Alarm(String sender, String receiver, String type, String info, String detail, int isRead) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.type = type;
		this.info = info;
		this.detail = detail;
		this.isRead = isRead;
	}



	public int getaNum() {
		return aNum;
	}

	public void setaNum(int aNum) {
		this.aNum = aNum;
	}

	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getaDate() {
		return aDate;
	}

	public void setaDate(String aDate) {
		this.aDate = aDate;
	}

	@Override
	public String toString() {
		return "Alarm [aNum=" + aNum + ", sender=" + sender + ", receiver=" + receiver + ", type=" + type + ", info="
				+ info + ", detail=" + detail + ", isRead=" + isRead + ", aDate=" + aDate + "]";
	}
	
	

}
