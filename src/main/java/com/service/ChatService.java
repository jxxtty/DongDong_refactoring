package com.service;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ChatDAO;
import com.dto.ChatMessage;
import com.dto.ChatRoom;

@Service
public class ChatService {
	@Autowired
	ChatDAO dao;

	public boolean checkFile(String chatId) {
		boolean flag = false;
		int n = dao.checkFile(chatId);
		if (n == 0)
			flag = true;

		return flag;
	}

	public void appendMessage(ChatMessage chatMessage) {
		String chatId = chatMessage.getChatId();
		String userId = chatMessage.getUserId();
		String message = chatMessage.getMessage();
		String sendTime = chatMessage.getSendTime();
		String content = userId + " : " + message + "," + sendTime;

		// 채팅 유무 확인
		if (checkFile(chatId)) { // 생성된 채팅이 있을 경우 message 추가 및 히스토리 가져오기
                //쓰기
            try {
            	File f = new File("C:/chatHistory/", chatId +".txt");
            	//BufferedWriter bw = new BufferedWriter(new FileWriter("C:/chatHistory/" + chatId + ".txt", false)); //true시 append or overwirte
            	BufferedWriter bw = new BufferedWriter(new FileWriter(f, true)); //true시 append or overwirte
				bw.write(content);
                bw.newLine();
                bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
               
            

		} else { // 생성 된 채팅이 없는 경우 file 생성 후 message 추가
			File file = new File("C:/chatHistory/" + chatId + ".txt");

			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
				bw.write(content);
                bw.newLine();
                bw.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void creatRoom(HashMap<String, String> map) {
		dao.createRoom(map);
		
	}
	
	public List<ChatRoom> getChatList(String userid) {
		
		return dao.getChatList(userid);
	}

}
