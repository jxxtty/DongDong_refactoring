package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("MemberDTO")
public class MemberDTO {
	private String userid;
	private String passwd;
	private String username;
	private String nickName;
	private String addr;
	private String phone;
	private String email1;
	private String email2;
	private String userimage;
	public MemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberDTO(String userid, String passwd, String username, String nickName, String addr, String phone,
			String email1, String email2, String userimage) {
		super();
		this.userid = userid;
		this.passwd = passwd;
		this.username = username;
		this.nickName = nickName;
		this.addr = addr;
		this.phone = phone;
		this.email1 = email1;
		this.email2 = email2;
		this.userimage = userimage;
	}
	@Override
	public String toString() {
		return "MemberDTO [userid=" + userid + ", passwd=" + passwd + ", username=" + username + ", nickName="
				+ nickName + ", addr=" + addr + ", phone=" + phone + ", email1=" + email1 + ", email2=" + email2
				+ ", userimage=" + userimage + "]";
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getUserimage() {
		return userimage;
	}
	public void setUserimage(String userimage) {
		this.userimage = userimage;
	}
	
	
	
	
}
