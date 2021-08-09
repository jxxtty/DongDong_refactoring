package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("CommentsDTO")
public class CommentsDTO {
	private int pNum;
	private int cNum;
	private String userid;
	private String cContent;
	private String createDate;
	private String updateDate;
	private int parentNum;
	private int cLevel;
	
	// 세부 페이지에 뿌릴때 필요한 유저정보를 저장할 변수
	private String nickName;
	private String userimage;
	
	public CommentsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentsDTO(int pNum, int cNum, String userid, String cContent, String createDate, String updateDate,
			int parentNum, int cLevel, String nickName, String userimage) {
		super();
		this.pNum = pNum;
		this.cNum = cNum;
		this.userid = userid;
		this.cContent = cContent;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.parentNum = parentNum;
		this.cLevel = cLevel;
		this.nickName = nickName;
		this.userimage = userimage;
	}
	
	@Override
	public String toString() {
		return "CommentsDTO [pNum=" + pNum + ", cNum=" + cNum + ", userid=" + userid + ", cContent=" + cContent
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", parentNum=" + parentNum + ", cLevel="
				+ cLevel + ", nickName=" + nickName + ", userimage=" + userimage + "]";
	}
	
	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	public int getcNum() {
		return cNum;
	}
	public void setcNum(int cNum) {
		this.cNum = cNum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getcContent() {
		return cContent;
	}
	public void setcContent(String cContent) {
		this.cContent = cContent;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public int getParentNum() {
		return parentNum;
	}
	public void setParentNum(int parentNum) {
		this.parentNum = parentNum;
	}
	public int getcLevel() {
		return cLevel;
	}
	public void setcLevel(int cLevel) {
		this.cLevel = cLevel;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserimage() {
		return userimage;
	}
	public void setUserimage(String userimage) {
		this.userimage = userimage;
	}
}
