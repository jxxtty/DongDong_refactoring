package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.FavoriteDAO;
import com.dto.FavoriteDTO;
import com.dto.PostDTO;

@Service
public class FavoriteService {
	@Autowired
	FavoriteDAO dao;
	
	public List<PostDTO> favoriteList(String userid) {
		List<PostDTO> list = dao.favoriteList(userid);
		return list;
	}

	public FavoriteDTO getFavorite(FavoriteDTO dto) {
		return dao.getFavorite(dto);
	}

	public int insertFavoite(FavoriteDTO dto) {
		return dao.insertFavoite(dto);
	}

	public int deleteFavoite(FavoriteDTO dto) {
		return dao.deleteFavoite(dto);
	}

	public int favoriteDel(int num) {
		int n = dao.favoriteDel(num);
		return n;
	}

	public int favoriteAllDel(List<String> list) {
		int n = dao.favoriteAllDel(list);
		return n;
	}

	public int getFavoriteCountByPNum(int pNum) {
		return dao.getFavoriteCountByPNum(pNum);
	}
}
