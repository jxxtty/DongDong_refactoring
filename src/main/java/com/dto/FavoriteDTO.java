package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("FavoriteDTO")
public class FavoriteDTO {
	private int pNum;
	private String userid;

	public FavoriteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FavoriteDTO(int pNum, String userid) {
		super();
		this.pNum = pNum;
		this.userid = userid;
	}
	
	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	public String getUserId() {
		return userid;
	}
	public void setUserId(String userid) {
		this.userid = userid;
	}
	
	@Override
	public String toString() {
		return "FavoriteDTO [pNum=" + pNum + ", userid=" + userid + "]";
	}
}
