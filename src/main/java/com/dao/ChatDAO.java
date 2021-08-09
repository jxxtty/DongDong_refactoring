package com.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.ChatRoom;

@Repository
public class ChatDAO {
	
	@Autowired
	SqlSessionTemplate template;
	
	public int checkFile(String chatId) {
		return template.selectOne("OrderSheetMapper.checkChatRoom", chatId);
	}

	public void createRoom(HashMap<String, String> map) {
		System.out.println(map);
		template.insert("OrderSheetMapper.createChatRoom", map);
	}

	public List<ChatRoom> getChatList(String userid) {
		return template.selectList("OrderSheetMapper.getChatList", userid);
	}
	
	

}
