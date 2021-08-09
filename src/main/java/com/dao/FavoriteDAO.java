package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.FavoriteDTO;
import com.dto.PostDTO;

@Repository
public class FavoriteDAO {
	@Autowired
	SqlSessionTemplate template;
	
	public List<PostDTO> favoriteList(String userid) {
		List<PostDTO> list = template.selectList("FavoriteMapper.favoriteList", userid);
		return list;
	}

	public FavoriteDTO getFavorite(FavoriteDTO dto) {
		FavoriteDTO returnDTO = template.selectOne("FavoriteMapper.getFavorite", dto);
		return returnDTO;
	}

	public int insertFavoite(FavoriteDTO dto) {
		return template.insert("FavoriteMapper.insertFavoite",dto);
	}

	public int deleteFavoite(FavoriteDTO dto) {
		return template.delete("FavoriteMapper.deleteFavoite",dto);
	}

	public int favoriteDel(int num) {
		int n = template.delete("FavoriteMapper.favoriteDel", num);
		return n;
	}

	public int favoriteAllDel(List<String> list) {
		int n = template.delete("FavoriteMapper.favoriteAllDel", list);
		return n;
	}

	public int getFavoriteCountByPNum(int pNum) {
		int n = template.selectOne("FavoriteMapper.getFavoriteCountByPNum", pNum);
		return n;
	}
}
