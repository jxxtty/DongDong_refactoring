package com.dto;

public class ImageDTO {
	
	private int iNum;
	private int pNum;
	private String pImage2;
	private String pImage3;
	private String pImage4;
	private String pImage5;
	
	public ImageDTO() {
		super();
	}

	public ImageDTO(int iNum, int pNum, String pImage2, String pImage3, String pImage4, String pImage5) {
		super();
		this.iNum = iNum;
		this.pNum = pNum;
		this.pImage2 = pImage2;
		this.pImage3 = pImage3;
		this.pImage4 = pImage4;
		this.pImage5 = pImage5;
	}

	public int getiNum() {
		return iNum;
	}

	public void setiNum(int iNum) {
		this.iNum = iNum;
	}

	public int getpNum() {
		return pNum;
	}

	public void setpNum(int pNum) {
		this.pNum = pNum;
	}

	public String getpImage2() {
		return pImage2;
	}

	public void setpImage2(String pImage2) {
		this.pImage2 = pImage2;
	}

	public String getpImage3() {
		return pImage3;
	}

	public void setpImage3(String pImage3) {
		this.pImage3 = pImage3;
	}

	public String getpImage4() {
		return pImage4;
	}

	public void setpImage4(String pImage4) {
		this.pImage4 = pImage4;
	}

	public String getpImage5() {
		return pImage5;
	}

	public void setpImage5(String pImage5) {
		this.pImage5 = pImage5;
	}

	@Override
	public String toString() {
		return "ImageDTO [iNum=" + iNum + ", pNum=" + pNum + ", pImage2=" + pImage2 + ", pImage3=" + pImage3
				+ ", pImage4=" + pImage4 + ", pImage5=" + pImage5 + "]";
	}
	
	// 편의 메서드
	public void setpImages(int length, String[] fileNames) {
		this.pImage2 = fileNames[1];
		if(length >= 3) this.pImage3 = fileNames[2];
		if(length >= 4) this.pImage4 = fileNames[3];
		if(length >= 5) this.pImage5 = fileNames[4];
	}
	
	
}
