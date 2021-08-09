package com.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.Alarm;

@Repository
public class AlarmDAO {
	
	@Autowired
	SqlSessionTemplate template;
	
	public int newAlarm(Alarm alarm) { // 새로운 알림이 생기면 DB에 저장한다.
		template.insert("AlarmMapper.newAlarm", alarm);
		return alarm.getaNum(); // insert되고나서 aNum값을 가져온다.
	}

	public List<Alarm> getMyAlarm(String receiver) { // 알림함에 들어갔을때 로그인된 회원의 알림만 가져온다.
		return template.selectList("AlarmMapper.getMyAlarm", receiver);
	}

	public Alarm getAlarm(int aNum) {
		return template.selectOne("AlarmMapper.getAlarm", aNum);
	}

	public int deleteAlarmOne(int aNum) {
		return template.delete("AlarmMapper.deleteAlarmOne", aNum);
	}


	public int readAlarmOne(int aNum) { // 알림을 읽었을때 해당 알림의 isRead값을 1로변경한다.
		return template.update("AlarmMapper.readAlarmOne", aNum);
	}

	public List<Alarm> myAlarmListFive(String userid) {
		return template.selectList("AlarmMapper.myAlarmListFive", userid, new RowBounds(0, 5));
	}

	public int deleteAlarmAll(List<String> list) {
		return template.delete("AlarmMapper.deleteAlarmAll", list);
	}
	
	public int readAlarmAll(List<String> list) {
		return template.update("AlarmMapper.readAlarmAll", list);
	}
}
