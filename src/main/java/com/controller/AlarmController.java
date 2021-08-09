package com.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.Alarm;
import com.dto.MemberDTO;
import com.service.AlarmService;

@Controller
public class AlarmController {

	@Autowired
	SimpMessagingTemplate simpMessageTemplate;
	@Autowired
	AlarmService aService;
	
	@MessageMapping("/reply/{userid}")
	public void sendAlarm(Alarm alarm) throws IOException {
		int aNumPK = aService.newAlarm(alarm); // alarm DB에 데이터 쌓고, 방금insert한 레코드의 PK값을 받아온다.
		alarm.setaNum(aNumPK);
		String title = alarm.getDetail();
		if(title.length() > 7) {
			alarm.setDetail(title.substring(0,7)+"...");
		}
		String urlSubscribe = "/subscribe/alarm/" + alarm.getReceiver(); // receiver = 글작성자
		simpMessageTemplate.convertAndSend(urlSubscribe, alarm);
	}

	
	@RequestMapping(value = "/myAlarmList", produces = "text/plain;charset=UTF-8") // 한글처리
	public @ResponseBody String myAlarmList(@RequestParam("id") String userid) { // top.jsp에서 알림클릭 시 안읽은 알림 중 최근5개만 긁어온다.
		List<Alarm> list = aService.myAlarmListFive(userid);
		JSONObject obj = new JSONObject();
        try {
            JSONArray jArray = new JSONArray();//배열이 필요할때
            for (int i = 0; i < list.size(); i++){
                JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
                sObject.put("sender", list.get(i).getSender());
                sObject.put("info", list.get(i).getInfo());
                sObject.put("type", list.get(i).getType()); 
                if(list.get(i).getDetail().length() > 7) {// 글제목이 길 경우
                	sObject.put("detail", list.get(i).getDetail().substring(0,7)+"...");
                } else {
                	sObject.put("detail", list.get(i).getDetail());
                } 
                sObject.put("date", list.get(i).getaDate());
                jArray.put(sObject);
            }
            
            obj.put("alarm", jArray);//배열을 넣음
        } catch (JSONException e) {
            e.printStackTrace();
        }

		return obj.toString(); 
	}

	@RequestMapping(value = "/loginCheck/alarmDeleteOne", method = RequestMethod.GET)
	public String alarmDeleteOne(@RequestParam("aNum") String aNum, HttpSession session) {

		MemberDTO mDto = (MemberDTO) session.getAttribute("login");
		String userid = mDto.getUserid();
		Alarm aDto = aService.getAlarm(Integer.parseInt(aNum));

		if (userid.equals(aDto.getReceiver())) { // 로그인되어있는 회원과 해당알림을 받은 회원이 동일할때
			int n = aService.deleteAlarmOne(Integer.parseInt(aNum));
		}

		return "redirect:../loginCheck/myAlarm"; // 내알림함으로 이동
	}

	@RequestMapping(value = "/loginCheck/alarmReadOne", method = RequestMethod.GET)
	public String alarmReadOne(@RequestParam("aNum") String aNum, HttpSession session) {

		MemberDTO mDto = (MemberDTO) session.getAttribute("login");
		String userid = mDto.getUserid();
		Alarm aDto = aService.getAlarm(Integer.parseInt(aNum));

		if (userid.equals(aDto.getReceiver())) { // 로그인되어있는 회원과 해당알림을 받은 회원이 동일할때
			int n = aService.readAlarmOne(Integer.parseInt(aNum));
		}

		return "redirect:../loginCheck/myAlarm"; // 내알림함으로 이동
	}
	
	
	@RequestMapping(value = "/loginCheck/deleteAlarmAll")
	public String deleteAlarmAll(@RequestParam("data") String num) {
		String [] check = num.split(",");
		List<String> list = Arrays.asList(check);
		aService.deleteAlarmAll(list);
		return "redirect:../loginCheck/myAlarm"; // 내알림함으로 이동
	}

	@RequestMapping(value = "/loginCheck/readAlarmAll")
	public String readAlarmAll(@RequestParam("data") String num) {
		String [] check = num.split(",");
		List<String> list = Arrays.asList(check);
		aService.readAlarmAll(list);
		return "redirect:../loginCheck/myAlarm"; // 내알림함으로 이동
	}
}
