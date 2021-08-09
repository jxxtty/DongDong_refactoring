package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.MyOrderSheetDTO;
import com.dto.OrderSheetDTO;
import com.dto.PurchaseDTO;
import com.dto.SaleDTO;

@Repository
public class OrderSheetDAO {
	@Autowired
	SqlSessionTemplate template;
	
	public void OrderSheetAdd(OrderSheetDTO dto) {
		template.insert("OrderSheetMapper.OrderSheetAdd", dto);
		
	}

	public List<MyOrderSheetDTO> ordersheetList(String userid) {
		List<MyOrderSheetDTO> list = template.selectList("OrderSheetMapper.ordersheetList", userid);
		return list;
	}

	public int orderDel(int num) {
		int n = template.delete("OrderSheetMapper.orderDel", num);
		return n;
	}

	public int orderAllDel(List<String> list) {
		int n = template.delete("OrderSheetMapper.orderAllDel", list);
		return n;
	}

	public List<MyOrderSheetDTO> message(String oNum) {
		List<MyOrderSheetDTO> list = template.selectList("OrderSheetMapper.message", oNum);
		return list;
	}

	public int purchase(PurchaseDTO dto1) {
		  int n = template.insert("OrderSheetMapper.purchase", dto1); 
		  return n; 
	}
	  
	  public int sale(SaleDTO dto2) { 
		  int n = template.insert("OrderSheetMapper.sale", dto2); 
		  return n; 
	}
	  
	  public int ordercomplete(int pNum) { 
		  int n = template.delete("OrderSheetMapper.ordercomplete", pNum); 
		  return n; 
	}

	public List<MyOrderSheetDTO> myordersheetList(String userid) {
		List<MyOrderSheetDTO> list = template.selectList("OrderSheetMapper.myordersheetList", userid);
		return list;
	}

}
