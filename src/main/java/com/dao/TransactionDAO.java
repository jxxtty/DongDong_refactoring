package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.PostDTO;

@Repository
public class TransactionDAO {
	@Autowired
	SqlSessionTemplate template;
	
	public List<PostDTO> purchaseList(String userid) {
		List<PostDTO> plist = template.selectList("TransactionMapper.purchaseList", userid);
		return plist;
	}

	public List<PostDTO> saleList(String userid) {
		List<PostDTO> slist = template.selectList("TransactionMapper.saleList", userid);
		return slist;
	}

	public int saleCount(String userid) {
		return template.selectOne("TransactionMapper.saleCount", userid);
	}

}
