package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PostDAO;
import com.dto.PageDTO;
import com.dto.PostDTO;

@Service
public class PostService {
	@Autowired
	PostDAO pDAO;

	public List<PostDTO> postListAll() {
		List<PostDTO> list = pDAO.postListAll();
		return list;
    }

    public List<PostDTO> postListByAddr(String addr){
    	List<PostDTO> list = pDAO.postListByAddr(addr);
        return list;
    }
    
    public PostDTO getPostByPNum(int pNum) {
    	return pDAO.getPostByPNum(pNum);
    }

	public int deletePostByPNum(int pNum) {
		return pDAO.deletePostByPNum(pNum);
	}

	public int newPost(PostDTO post) {
		int result = pDAO.newPost(post);
		return result;
	}

	public int updatePost(PostDTO dto) {
		return pDAO.updatePost(dto);
	}

	public int updatePHit(PostDTO dto) {
		return pDAO.updatePHit(dto);
	}

	public PageDTO searchByKeyword(int curPage, HashMap<String, String> map) {
		PageDTO pDTO = pDAO.searchByKeyword(map,curPage);
        return pDTO;
	}
	
	public List<PostDTO> mypostList(String userid) {
		List<PostDTO> list = pDAO.mypostList(userid);
		return list;
	}//end mypostList

	public int postAllDel(List<String> list) {
		int n = pDAO.postAllDel(list);
		return n;
	}

	public PageDTO searchByCategory(int curPage,boolean login, HashMap<String, String> map) {
        PageDTO dto = pDAO.searchByCategory(curPage,login,map);
        return dto;
	}

	public PageDTO selectAllPostPage(int curPage, boolean login, String addr){
		PageDTO pDTO = pDAO.selectAllPostPage(curPage,login,addr);
		return pDTO;
	}
	
	public int pullPost(PostDTO pDto) {
        int pullResult = pDAO.pullPost(pDto);
        return pullResult;
	}
}// end class

