package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.TransactionDAO;
import com.dto.PostDTO;

@Service
public class TransactionService {
	@Autowired
	TransactionDAO dao;
	
	public List<PostDTO> purchaseList(String userid) {
		List<PostDTO> plist = dao.purchaseList(userid);
		return plist;
	}

	public List<PostDTO> saleList(String userid) {
		List<PostDTO> slist = dao.saleList(userid);
		return slist;
	}
	
	public int saleCount(String userid) {
		return  dao.saleCount(userid);
	}
}
