package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ImageDAO;
import com.dto.ImageDTO;

@Service
public class ImageService {
	@Autowired
	ImageDAO imageDAO;

	public int newImages(ImageDTO iDto) {
		return imageDAO.newImages(iDto);
	}
	
	public ImageDTO getImageByINum(int iNum) {
		return imageDAO.getImageByINum(iNum);
	}

	public void deleteByINum(int iNum) {
		imageDAO.deleteByINum(iNum);
	}
}
