package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("SanctionDTO")
public class SanctionDTO {
	private int saNum;
	private String userid;
	private int coNum;
	private int saType;
	private int saDate;
	private String startDate;
	private String endDate;
	public SanctionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SanctionDTO(int saNum, String userid, int coNum, int saType, int saDate, String startDate, String endDate) {
		super();
		this.saNum = saNum;
		this.userid = userid;
		this.coNum = coNum;
		this.saType = saType;
		this.saDate = saDate;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "SanctionDTO [saNum=" + saNum + ", userid=" + userid + ", coNum=" + coNum + ", saType=" + saType
				+ ", saDate=" + saDate + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	public int getSaNum() {
		return saNum;
	}
	public void setSaNum(int saNum) {
		this.saNum = saNum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getCoNum() {
		return coNum;
	}
	public void setCoNum(int coNum) {
		this.coNum = coNum;
	}
	public int getSaType() {
		return saType;
	}
	public void setSaType(int saType) {
		this.saType = saType;
	}
	public int getSaDate() {
		return saDate;
	}
	public void setSaDate(int saDate) {
		this.saDate = saDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
