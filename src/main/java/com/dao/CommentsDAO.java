package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.CommentsDTO;

@Repository
public class CommentsDAO {
	@Autowired
	SqlSessionTemplate template;
	
	public int insertComments(CommentsDTO dto) {
		return template.insert("CommentsMapper.insertComments",dto);
	}

	public List<CommentsDTO> getCommentsByPNum(int pNum) {
		List<CommentsDTO> list = template.selectList("CommentsMapper.getCommentsByPNum", pNum);
		return list;
	}

	public CommentsDTO getCommentByCNum(int cNum) {
		CommentsDTO dto = template.selectOne("CommentsMapper.getCommentByCNum", cNum);
		return dto;
	}

	public int deleteCommentByCNum(int cNum) {
		return template.delete("CommentsMapper.deleteCommentByCNum",cNum);
	}

	public int updateComment(CommentsDTO dto) {
		return template.update("CommentsMapper.updateComment",dto);
	}
}
