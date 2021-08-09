package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dto.OrderSheetDTO;
import com.service.OrderSheetService;
@Controller
public class OrderController {
	
	@Autowired
	OrderSheetService service;
	
	@RequestMapping(value = "/AddOrderSheet", method =RequestMethod.GET)
	public ModelAndView AddOrderSheet(int pNum, String sUserid, String bUserid
						, String oAddr, int oPrice, String oMessage) {
		
		OrderSheetDTO dto = new OrderSheetDTO();
		dto.setpNum(pNum);
		dto.setsUserid(sUserid);
		dto.setbUserid(bUserid);
		dto.setoAddr(oAddr);
		dto.setoPrice(oPrice);
		dto.setoMessage(oMessage);
		dto.setoDate("");
		
		service.OrderSheetAdd(dto);
		
		ModelAndView mav = new ModelAndView("redirect:/orderEndSheet");
		
		return mav;
	}
	
}
