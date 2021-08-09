package com.dao;

import java.util.Date;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.MemberDTO;
@Repository
public class MemberDAO {
	@Autowired
	SqlSessionTemplate template;
	
	public MemberDTO mypage(String userid) {
		MemberDTO dto = template.selectOne("MemberMapper.mypage", userid);
		return dto;
	}// end myPage

	public MemberDTO login(HashMap<String, String> map) {
		MemberDTO dto = template.selectOne("MemberMapper.login", map);
		return dto;
	} //end login

	public int memberAdd(MemberDTO dto) {
		   int n = template.insert("MemberMapper.memberAdd", dto);
		   return n;
	}//end memberAdd
	
	public String idSearch(MemberDTO dto) {
		String userid = template.selectOne("MemberMapper.idSearch",dto);
		return userid;
	}//end idSearch

	public int idCheck(String userid) {
		int count = template.selectOne("MemberMapper.idCheck", userid);
		return count;
	}//end idCheck

	public int nickNameCheck(String nickName) {
		int count = template.selectOne("MemberMapper.nickNameCheck", nickName);
		return count;
	}//end nickNameCheck

	public String pwSearch(MemberDTO dto) {
		String passwd = template.selectOne("MemberMapper.pwSearch",dto);
		return passwd;
	}//end pwSearch

	public int memberUpdate(MemberDTO dto2) {
		int num = template.update("MemberMapper.memberUpdate", dto2);
		return num;
	}//end memberUpdate

	public int nickCheck(String nickName) {
		int n = template.selectOne("MemberMapper.nickCheck", nickName);
		return n;
	}//end nickCHeck

	public int nickUpdate(MemberDTO dto2) {
		int n = template.update("MemberMapper.nickUpdate", dto2);
		return n;
	}//end nickUpdate

	public void withdrawal(String userid) {
		template.delete("MemberMapper.withdrawal", userid);
	}

	public int addrAuth1(MemberDTO dto2) {
		int n = template.selectOne("MemberMapper.addrAuth1", dto2);
		return n;
	}
	
	public int addrAuth2(MemberDTO dto2) {
		int n = template.update("MemberMapper.addrAuth2", dto2);
		return n;
	}

	public MemberDTO getMemberByUserid(String senderId) {
		return template.selectOne("MemberMapper.getMemberByUserid", senderId);
	}
	
	public int selectLockStatus(String userid) {
		return template.selectOne("MemberMapper.selectLockStatus", userid);
	}

	public void updateClearLoginFailCount(String userid) {
		template.update("MemberMapper.updateClearLoginFailCount", userid);
	}

	public void updateClearLockCount(String userid) {
		template.update("MemberMapper.updateClearLockCount", userid);
	}

	public void plusLoginFailCount(String userid) {
		template.update("MemberMapper.plusLoginFailCount", userid);
	}

	public void updateLockStatus(String userid) {
		template.update("MemberMapper.updateLockStatus", userid);
	}

	public Date selectLoginDate(String userid) {
		return template.selectOne("MemberMapper.selectLoginDate", userid);
	}

	public int selectLockCount(String userid) {
		return template.selectOne("MemberMapper.selectLockCount", userid);
	}

	public int selectFailCount(String userid) {
		return template.selectOne("MemberMapper.selectFailCount", userid);
	}

	public void loginDate(String userid) {
		template.update("MemberMapper.loginDate", userid);
	}

	public boolean isAdmin(String userid) {
		int adminNum = template.selectOne("MemberMapper.isAdmin", userid);
		if(adminNum==1) {
			return true;
		}
		return false;
		
	}
}//end MemberDAO

