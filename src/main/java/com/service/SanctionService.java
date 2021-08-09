package com.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.SanctionDAO;
import com.dto.SanctionDTO;


@Service
public class SanctionService {
	@Autowired
	SanctionDAO saDAO;

	public int insertSanction(SanctionDTO saDTO) {
		return saDAO.insertSanction(saDTO);
	}

	public List<SanctionDTO> sanctionList(String userid) {
		return saDAO.sanctionList(userid);
	}
	
	public String getEndDateByUserid(String userid) {
		return saDAO.getEndDateByUserid(userid);
	}
	
	public String getSanctionReasonByUserid(String userid) {
		int saType = saDAO.getSanctionReasonByUserid(userid);
		String mesg = null;
		if(saType==1) {
			mesg = "불법사이트 홍보/계정 해킹 시도";
		} else if(saType==2){
			mesg = "불법 거래/범죄 행위";
		}else if(saType==3){
			mesg = "폭력적인 언어, 욕설, 비속어 사용";
		} else if(saType==4){
			mesg = "부적절한 사진/게시글 게시";
		} else if(saType==5){
			mesg = "반복적 게시글/댓글 등록 행위";
		}
		return mesg;
	}
	
	
	public boolean isSanctioned(String userid) {
		return saDAO.isSanctioned(userid);
	}
}
