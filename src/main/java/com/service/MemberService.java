package com.service;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MemberDAO;
import com.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	MemberDAO dao;
	
	public MemberDTO mypage(String userid) {
		MemberDTO dto = dao.mypage(userid);
		return dto;
	}//end mypage
	
	
	public int memberAdd(MemberDTO dto) {
		 int n = dao.memberAdd(dto);
		 return n;
	}//end memberAdd
	
	public MemberDTO login(HashMap<String, String> map) {
		MemberDTO dto = dao.login(map);
		return dto;
	}//end login
	
	public String idSearch(MemberDTO dto) {
		String userid = dao.idSearch(dto);
		return userid;
	}//end idSearch


	public int idCheck(String userid) {
		int count = dao.idCheck(userid);
		return count;	
	}


	public int nickNameCheck(String nickName) {
		int count = dao.nickNameCheck(nickName);
		return count;	
	}
	public String pwSearch(MemberDTO dto) {
		String passwd = dao.pwSearch(dto);
		return passwd;
	}//end pwSerach


	public int memberUpdate(MemberDTO dto2) {
		int num = dao.memberUpdate(dto2);
		return num;
	}


	public int nickCheck(String nickName) {
		int n = dao.nickCheck(nickName);
		return n;
	}//mypage 닉네임중복검사


	public int nickUpdate(MemberDTO dto2) {
		int n = dao.nickUpdate(dto2);
		return n;
	}//mypage에서 닉네임 중복검사후 변경


	public void withdrawal(String userid) {
		dao.withdrawal(userid);
	}//end withdrawal


	public int addrAuth1(MemberDTO dto2) {
		int n = dao.addrAuth1(dto2);
		return n;
	}//동 같은지 체크
	
	public int addrAuth2(MemberDTO dto2) {
		int n = dao.addrAuth2(dto2);
		return n;
	}//동 달라서 현재위치로 addr 수정


	public MemberDTO getMemberByUserid(String senderId) {
		return dao.getMemberByUserid(senderId);
	} // userid로 MemberDTO 반환
	
	public int selectLockStatus(String userid) {
		return dao.selectLockStatus(userid);
	}


	public void updateClearLoginFailCount(String userid) {
		dao.updateClearLoginFailCount(userid);
	}


	public void updateClearLockCount(String userid) {
		dao.updateClearLockCount(userid);
	}


	public void plusLoginFailCount(String userid) {
		dao.plusLoginFailCount(userid);
	}


	public void updateLockStatus(String userid) {
		dao.updateLockStatus(userid);
	}


	public Date selectLoginDate(String userid) {
		return dao.selectLoginDate(userid);
	}


	public int selectLockCount(String userid) {
		return dao.selectLockCount(userid);
	}


	public int selectFailCount(String userid) {
		return dao.selectFailCount(userid);
	}

	public void loginDate(String userid) {
		dao.loginDate(userid);
	}
	
	public boolean isAdmin(String userid) {
		return dao.isAdmin(userid);
	}
}
