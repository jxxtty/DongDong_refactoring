package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.SanctionDTO;


@Repository
public class SanctionDAO {
	@Autowired
	SqlSessionTemplate template;

	public int insertSanction(SanctionDTO saDTO) {
		return template.insert("SanctionMapper.insertSanction",saDTO);
	}

	public List<SanctionDTO> sanctionList(String userid) {
		return template.selectList("SanctionMapper.sanctionList",userid);
	}
	
	public String getEndDateByUserid(String userid) {
		return template.selectOne("SanctionMapper.getEndDateByUserid",userid);
	}
	
	public boolean isSanctioned(String userid) {
		double result = template.selectOne("SanctionMapper.isSanctioned",userid);
		return (result<0?true:false);
	}

	public int getSanctionReasonByUserid(String userid) {
		return template.selectOne("SanctionMapper.getSanctionReasonByUserid",userid);
	}
}
