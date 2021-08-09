package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("MyOrderSheetDTO")
public class MyOrderSheetDTO {
	private int oNum;
	private int pNum;
	private String sUserid;
	private String bUserid;
	private String oAddr;
	private int oPrice;
	private String oMessage;
	private String oDate;
	private String pTitle;
	private String pImage;
	private String nickName;
	private String userImage;
	public MyOrderSheetDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyOrderSheetDTO(int oNum, int pNum, String sUserid, String bUserid, String oAddr, int oPrice,
			String oMessage, String oDate, String pTitle, String pImage, String nickName, String userImage) {
		super();
		this.oNum = oNum;
		this.pNum = pNum;
		this.sUserid = sUserid;
		this.bUserid = bUserid;
		this.oAddr = oAddr;
		this.oPrice = oPrice;
		this.oMessage = oMessage;
		this.oDate = oDate;
		this.pTitle = pTitle;
		this.pImage = pImage;
		this.nickName = nickName;
		this.userImage = userImage;
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
	public String getpTitle() {
		return pTitle;
	}
	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}
	public String getpImage() {
		return pImage;
	}
	public void setpImage(String pImage) {
		this.pImage = pImage;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	@Override
	public String toString() {
		return "MyOrderSheetDTO [oNum=" + oNum + ", pNum=" + pNum + ", sUserid=" + sUserid + ", bUserid=" + bUserid
				+ ", oAddr=" + oAddr + ", oPrice=" + oPrice + ", oMessage=" + oMessage + ", oDate=" + oDate
				+ ", pTitle=" + pTitle + ", pImage=" + pImage + ", nickName=" + nickName + ", userImage=" + userImage
				+ "]";
	}
	
}
