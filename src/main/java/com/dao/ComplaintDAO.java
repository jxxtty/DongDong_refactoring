package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.ComplaintDTO;

@Repository
public class ComplaintDAO {
	@Autowired
	SqlSessionTemplate template;
	
	public int insertComplaint(ComplaintDTO dto) {
		return template.insert("ComplaintMapper.insertComplaint",dto);
	}

	public ComplaintDTO checkDuplication(ComplaintDTO dto) {
		return template.selectOne("ComplaintMapper.checkDuplication",dto);
	}
	
	public List<ComplaintDTO> memberComplaintList() {
		return template.selectList("ComplaintMapper.memberComplaintList");
	}
	
	public List<ComplaintDTO> postComplaintList() {
		return template.selectList("ComplaintMapper.postComplaintList");
	}
	
	public List<ComplaintDTO> commentComplaintList() {
		return template.selectList("ComplaintMapper.commentComplaintList");
	}

	public int complaintEnd(int coNum) {
		return template.update("ComplaintMapper.complaintEnd", coNum);
	}

	public ComplaintDTO getComplaint(int coNum) {
		return template.selectOne("ComplaintMapper.getComplaint",coNum);
	}
	
	public int isAlreadyCompleted(ComplaintDTO dto) {
		return template.selectOne("ComplaintMapper.isAlreadyCompleted",dto);
	}
	
}
