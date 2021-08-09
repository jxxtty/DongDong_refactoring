package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.OrderSheetDAO;
import com.dao.PostDAO;
import com.dto.MyOrderSheetDTO;
import com.dto.OrderSheetDTO;
import com.dto.PurchaseDTO;
import com.dto.SaleDTO;

@Service
public class OrderSheetService {
	@Autowired
	OrderSheetDAO oDAO;
	@Autowired
	PostDAO pDAO;
	public void OrderSheetAdd(OrderSheetDTO dto) {
		
		oDAO.OrderSheetAdd(dto);
	}

	public List<MyOrderSheetDTO> ordersheetList(String userid) {
		List<MyOrderSheetDTO> list = oDAO.ordersheetList(userid);
		return list;
	}//ordersheetList
	
	public int orderDel(int num) {
		int n = oDAO.orderDel(num);
		return n;
	}
	
	public int orderAllDel(List<String> list) {
		int n = oDAO.orderAllDel(list);
		return n;
	}

	public List<MyOrderSheetDTO> message(String oNum) {
		List<MyOrderSheetDTO> list = oDAO.message(oNum);
		return list;
	}

	//TX 처리? 내가 코딩한게 아니라 모름
	public int sale(String bUserid, String sUserid, int pNum) {
		PurchaseDTO dto1 = new PurchaseDTO();
		SaleDTO dto2 = new SaleDTO();
		int n = 0;

		dto1.setUserid(bUserid);
		dto1.setpNum(pNum);
		dto2.setUserid(sUserid);
		dto2.setpNum(pNum);
		n = oDAO.purchase(dto1);
		n = oDAO.sale(dto2);
		n = oDAO.ordercomplete(pNum);
		n = pDAO.poststatus(pNum);
		
		return n;
	}//end sale

	public List<MyOrderSheetDTO> myordersheetList(String userid) {
		List<MyOrderSheetDTO> list = oDAO.myordersheetList(userid);
		return list;
	}	
}
