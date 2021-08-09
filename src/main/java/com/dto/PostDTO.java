package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("PostDTO")
public class PostDTO {
	private int pNum;
	private String userid;
	private String addr;
	private String pCategory;
	private String pTitle;
	private String pContent;
	private int pPrice;
	private String pImage;
	private int pHit;
	private String pDate;
	private String pDateDiff;
	private String pStatus;
	private String pPull;
	
	public PostDTO() {
		super();
	}

	public PostDTO(int pNum, String userid, String addr, String pCategory, String pTitle, String pContent, int pPrice,
			String pImage, int pHit, String pDate, String pDateDiff, String pStatus, String pPull) {
		super();
		this.pNum = pNum;
		this.userid = userid;
		this.addr = addr;
		this.pCategory = pCategory;
		this.pTitle = pTitle;
		this.pContent = pContent;
		this.pPrice = pPrice;
		this.pImage = pImage;
		this.pHit = pHit;
		this.pDate = pDate;
		this.pDateDiff = pDateDiff;
		this.pStatus = pStatus;
		this.pPull = pPull;
	}

	public int getpNum() {
		return pNum;
	}

	public void setpNum(int pNum) {
		this.pNum = pNum;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getpCategory() {
		return pCategory;
	}

	public void setpCategory(String pCategory) {
		this.pCategory = pCategory;
	}

	public String getpTitle() {
		return pTitle;
	}

	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}

	public String getpContent() {
		return pContent;
	}

	public void setpContent(String pContent) {
		this.pContent = pContent;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public String getpImage() {
		return pImage;
	}

	public void setpImage(String pImage) {
		this.pImage = pImage;
	}

	public int getpHit() {
		return pHit;
	}

	public void setpHit(int pHit) {
		this.pHit = pHit;
	}

	public String getpDate() {
		return pDate;
	}

	public void setpDate(String pDate) {
		this.pDate = pDate;
	}

	
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public String getpDateDiff() {
		return pDateDiff;
	}

	public void setpDateDiff(String pDateDiff) {
		this.pDateDiff = pDateDiff;
	}

	public String getpStatus() {
		return pStatus;
	}

	public void setpStatus(String pStatus) {
		this.pStatus = pStatus;
	}

	public String getpPull() {
		return pPull;
	}

	public void setpPull(String pPull) {
		this.pPull = pPull;
	}

	@Override
	public String toString() {
		return "PostDTO [pNum=" + pNum + ", userid=" + userid + ", addr=" + addr + ", pCategory=" + pCategory
				+ ", pTitle=" + pTitle + ", pContent=" + pContent + ", pPrice=" + pPrice + ", pImage=" + pImage
				+ ", pHit=" + pHit + ", pDate=" + pDate + ", pDateDiff=" + pDateDiff + ", pStatus=" + pStatus
				+ ", pPull=" + pPull + "]";
	}

}
