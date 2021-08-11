package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ImageDAO;
import com.dto.ImageDTO;

@Service
public class ImageService {
	@Autowired
	ImageDAO imageDAO;

	public void newImages(ImageDTO iDto) {
		imageDAO.newImages(iDto);
	}
}
