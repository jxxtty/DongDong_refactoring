package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CommentsDAO;
import com.dto.CommentsDTO;

@Service
public class CommentsService {
	@Autowired
	CommentsDAO dao;
	
	public int insertComments(CommentsDTO dto) {
		return dao.insertComments(dto);
	}

	public List<CommentsDTO> getCommentsByPNum(int pNum) {
		return dao.getCommentsByPNum(pNum);
	}
	
	public CommentsDTO getCommentByCNum(int cNum) {
		return dao.getCommentByCNum(cNum);
	}

	public int deleteCommentByCNum(int cNum) {
		return dao.deleteCommentByCNum(cNum);
	}

	public int updateComment(CommentsDTO dto) {
		return dao.updateComment(dto);
	}
}
