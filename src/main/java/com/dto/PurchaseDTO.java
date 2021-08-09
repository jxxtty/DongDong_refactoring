package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("PurchaseDTO")
public class PurchaseDTO {
	private int pNum;
	private String nickName;
	private String pTitle;
	private String pImage;
	private int pPrice;
	private String userid;
	public PurchaseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PurchaseDTO(int pNum, String nickName, String pTitle, String pImage, int pPrice, String userid) {
		super();
		this.pNum = pNum;
		this.nickName = nickName;
		this.pTitle = pTitle;
		this.pImage = pImage;
		this.pPrice = pPrice;
		this.userid = userid;
	}
	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "PurchaseDTO [pNum=" + pNum + ", nickName=" + nickName + ", pTitle=" + pTitle + ", pImage=" + pImage
				+ ", pPrice=" + pPrice + ", userid=" + userid + "]";
	}
	
}
