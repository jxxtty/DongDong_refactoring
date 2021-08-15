package com.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.ImageDTO;

@Repository
public class ImageDAO {
	@Autowired
	SqlSessionTemplate template;

	public int newImages(ImageDTO iDto) {
		template.insert("ImageMapper.newImages", iDto);
		return iDto.getiNum();
	}

	public ImageDTO getImageByINum(int iNum) {
		return template.selectOne("ImageMapper.getImageByINum", iNum);
	}
	
	
}
