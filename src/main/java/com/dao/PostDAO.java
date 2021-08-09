package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.PageDTO;
import com.dto.PostDTO;

@Repository
public class PostDAO {

	@Autowired
	SqlSessionTemplate template;

	public List<PostDTO> postListByAddr(String addr) {
		List<PostDTO> list = template.selectList("PostMapper.postListByAddr", addr);
		return list;
	}

	public PostDTO getPostByPNum(int pNum) {
		PostDTO dto = template.selectOne("PostMapper.getPostByPNum", pNum);
		return dto;
	}

	public List<PostDTO> recentList(String pDate) {
		List<PostDTO> list = template.selectList("PostMapper.recentList", pDate);
		return list;
	}

	public List<PostDTO> postListAll() {
		List<PostDTO> list = template.selectList("PostMapper.postListAll");
		return list;
	}

	public int deletePostByPNum(int pNum) {
		return template.delete("PostMapper.deletePostByPNum", pNum);
	}

	public int newPost(PostDTO post) {
		int n = template.insert("PostMapper.newPost", post);
		return n;
	}

	public int updatePost(PostDTO dto) {
		return template.update("PostMapper.updatePost", dto);
	}

	public int updatePHit(PostDTO dto) {
		return template.update("PostMapper.updatePHit", dto);
	}

	public PageDTO searchByKeyword(HashMap<String, String> map, int curPage) {

		PageDTO pDTO = new PageDTO();// List, curPage, totalCount, perPage
		int perPage = 16;// db에서 몇개를 읽어올지
		int offset = (curPage - 1) * perPage;// db레코드 select 시작번호
		int blockPerPage = 5; // 페이지당 표시할 페이지 갯수
		int prevPageBlock = ((curPage - 1) / blockPerPage * blockPerPage) + 1; // 이전 페이지 블록
		int nextPageBlock = ((curPage - 1) / blockPerPage * blockPerPage) + blockPerPage; // 다음 페이지 블록
		List<PostDTO> list = template.selectList("PostMapper.searchByKeyword", map, new RowBounds(offset, perPage));
		pDTO.setBlockPerPage(blockPerPage);
		pDTO.setNextPageBlock(nextPageBlock);
		pDTO.setPrevPageBlock(prevPageBlock);
		pDTO.setPerPage(perPage);// 한페이지당 페이지 개수
		pDTO.setCurPage(curPage);// 현재페이지
		pDTO.setOffset(offset);// 시작페이지
		pDTO.setList(list);// 0~15 16개
		pDTO.setTotalCount(totalCountKeyword(map));// 전체 레코드 갯수

		// PDTO에 모든 데이터 저장완료
		return pDTO;

	}

	public List<PostDTO> mypostList(String userid) {
		List<PostDTO> list = template.selectList("PostMapper.mypostList", userid);
		return list;
	}

	public int postAllDel(List<String> list) {
		int n = template.delete("PostMapper.postAllDel", list);
		return n;
	}

	public PageDTO searchByCategory(int curPage, boolean login, HashMap<String, String> map) {
		PageDTO pDTO = new PageDTO();// List, curPage, totalCount, perPage
		int perPage = 16;// db에서 몇개를 읽어올지
		int offset = (curPage - 1) * perPage;// db레코드 select 시작번호
		int blockPerPage = 5; // 페이지당 표시할 페이지 갯수
		int prevPageBlock = ((curPage - 1) / blockPerPage * blockPerPage) + 1; // 이전 페이지 블록
		int nextPageBlock = ((curPage - 1) / blockPerPage * blockPerPage) + blockPerPage; // 다음 페이지 블록
		List<PostDTO> list = template.selectList("PostMapper.searchByCategory", map, new RowBounds(offset, perPage));
		if (login) {
			list = template.selectList("PostMapper.searchByCategory", map, new RowBounds(offset, perPage));
		} else {
			list = template.selectList("PostMapper.searchByCategory", map, new RowBounds(offset, perPage));
		}
		pDTO.setBlockPerPage(blockPerPage);
		pDTO.setNextPageBlock(nextPageBlock);
		pDTO.setPrevPageBlock(prevPageBlock);
		pDTO.setPerPage(perPage);// 한페이지당 페이지 개수
		pDTO.setCurPage(curPage);// 현재페이지
		pDTO.setOffset(offset);// 시작페이지
		pDTO.setList(list);// 0~15 16개
		pDTO.setTotalCount(totalCountCategory(map));// 전체 레코드 갯수
		// PDTO에 모든 데이터 저장완료

		return pDTO;
	}

	public int totalCount(String addr) {
		return template.selectOne("totalCount", addr);
	}

	public int totalCountKeyword(HashMap<String, String> map) {
		return template.selectOne("totalCountKeyword", map);
	}

	public int totalCountCategory(HashMap<String, String> map) {
		return template.selectOne("totalCountCategory", map);
	}

	public PageDTO selectAllPostPage(int curPage, boolean login, String addr) {

		PageDTO pDTO = new PageDTO();// List, curPage, totalCount, perPage
		int blockPerPage = 5; // 페이지당 표시할 페이지 갯수
		int prevPageBlock = ((curPage - 1) / blockPerPage * blockPerPage) + 1; // 이전 페이지 블록
		int nextPageBlock = ((curPage - 1) / blockPerPage * blockPerPage) + blockPerPage; // 다음 페이지 블록
		int perPage = 16;// db에서 몇개를 읽어올지
		int offset = (curPage - 1) * perPage;// db레코드 select 시작번호
		List<PostDTO> list = template.selectList("postListAll", null, new RowBounds(offset, perPage));
		// 최초 offset=0, perPage=16
		// 데이터를 주고 받는 것 PageDTO
		if (login) {
			list = template.selectList("postListByAddr", addr, new RowBounds(offset, perPage));
		} else {
			list = template.selectList("postListAll", null, new RowBounds(offset, perPage));
		}
		pDTO.setBlockPerPage(blockPerPage);// 페이지당 표시할 페이지 갯수
		pDTO.setPrevPageBlock(prevPageBlock); // 한페이지에 표시할 블럭 시작번호
		pDTO.setNextPageBlock(nextPageBlock); // 한페이지에 표시할 블럭 끝번호
		pDTO.setPerPage(perPage);// 한페이지당 페이지 개수
		pDTO.setCurPage(curPage);// 현재페이지
		pDTO.setList(list);// 0~15 16개
		pDTO.setTotalCount(totalCount(addr));// 전체 레코드 갯수

		return pDTO;

	}

	public int pullPost(PostDTO pDto) {
		return template.update("PostMapper.pullPost", pDto);
	}

	public int poststatus(int pNum) {
		int n = template.update("PostMapper.poststatus", pNum);
		return n;
	}

}
