package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("OrderSheetDTO")
public class OrderSheetDTO {
	private int oNum;
	private int pNum;
	private String sUserid;
	private String bUserid;
	private String oAddr;
	private int oPrice;
	private String oMessage;
	private String oDate;
	public OrderSheetDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderSheetDTO(int oNum, int pNum, String sUserid, String bUserid, String oAddr, int oPrice, String oMessage,
			String oDate) {
		super();
		this.oNum = oNum;
		this.pNum = pNum;
		this.sUserid = sUserid;
		this.bUserid = bUserid;
		this.oAddr = oAddr;
		this.oPrice = oPrice;
		this.oMessage = oMessage;
		this.oDate = oDate;
	}
	public int getoNum() {
		return oNum;
	}
	public void setoNum(int oNum) {
		this.oNum = oNum;
	}
	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	public String getsUserid() {
		return sUserid;
	}
	public void setsUserid(String sUserid) {
		this.sUserid = sUserid;
	}
	public String getbUserid() {
		return bUserid;
	}
	public void setbUserid(String bUserid) {
		this.bUserid = bUserid;
	}
	public String getoAddr() {
		return oAddr;
	}
	public void setoAddr(String oAddr) {
		this.oAddr = oAddr;
	}
	public int getoPrice() {
		return oPrice;
	}
	public void setoPrice(int oPrice) {
		this.oPrice = oPrice;
	}
	public String getoMessage() {
		return oMessage;
	}
	public void setoMessage(String oMessage) {
		this.oMessage = oMessage;
	}
	public String getoDate() {
		return oDate;
	}
	public void setoDate(String oDate) {
		this.oDate = oDate;
	}
	@Override
	public String toString() {
		return "OrdersheetDTO [oNum=" + oNum + ", pNum=" + pNum + ", sUserid=" + sUserid + ", bUserid=" + bUserid
				+ ", oAddr=" + oAddr + ", oPrice=" + oPrice + ", oMessage=" + oMessage + ", oDate=" + oDate + "]";
	}
	
}
