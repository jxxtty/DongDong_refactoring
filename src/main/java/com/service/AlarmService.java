package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AlarmDAO;
import com.dto.Alarm;


@Service
public class AlarmService {
	
	@Autowired
	AlarmDAO dao;

	public int newAlarm(Alarm alarm) {
		int n = dao.newAlarm(alarm);
		return n;
	}

	public List<Alarm> getMyAlarm(String receiver) {
		return dao.getMyAlarm(receiver);
	}

	public Alarm getAlarm(int aNum) {
		return dao.getAlarm(aNum);
	}

	public int deleteAlarmOne(int aNum) {
		return dao.deleteAlarmOne(aNum);
	}

	public int readAlarmOne(int aNum) {
		return dao.readAlarmOne(aNum);
	}

	public List<Alarm> myAlarmListFive(String userid) {
		return dao.myAlarmListFive(userid);
	}

	public int deleteAlarmAll(List<String> list) {
		return dao.deleteAlarmAll(list);
	}
	
	public int readAlarmAll(List<String> list) {
		return dao.readAlarmAll(list);
	}
}
