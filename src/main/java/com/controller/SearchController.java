package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MemberDTO;
import com.dto.PageDTO;
import com.dto.PostDTO;
import com.service.FavoriteService;
import com.service.PostService;

@Controller
public class SearchController {
	@Autowired
	PostService pService;
	@Autowired
	FavoriteService fService;

	

	@RequestMapping(value="/keywordSearch", method=RequestMethod.GET)
	public String keywordSearch(String keyword, HttpServletRequest request,HttpSession session) {

		MemberDTO mDto = (MemberDTO)session.getAttribute("login");
		String curPage = request.getParameter("curPage");
		String addr = "null";
		List<PostDTO> list2 = new ArrayList<>();
		if(mDto != null) { // 로그인 된 상태
			String userid = mDto.getUserid();
			addr = mDto.getAddr();
			list2 = fService.favoriteList(userid);	
		}
		if(curPage == null) {
			curPage = "1";
		}
		HashMap<String,String> map = new HashMap<>();
		map.put("addr", addr);
		map.put("keyword", keyword);
		
		

		PageDTO pDTO = pService.searchByKeyword(Integer.parseInt(curPage),map);
		int totalPage = pDTO.getTotalCount()/pDTO.getPerPage();
		if(pDTO.getTotalCount()%pDTO.getPerPage() != 0) totalPage++;//나머지 있을 경우 1페이지 증가 
		
		// 다중파일 대비 이미지파싱
		List<PostDTO> list = pDTO.getList();
		for(int i = 0 ; i < list.size() ; i++) {
			String[] image = list.get(i).getpImage().split(" ");
			list.get(i).setpImage(image[0]);
		}
		request.setAttribute("favoriteList", list2);
		request.setAttribute("keyword", keyword);
		request.setAttribute("postList",list);
		request.setAttribute("blockPerPage", pDTO.getBlockPerPage());
		request.setAttribute("offset", pDTO.getOffset());
		request.setAttribute("curPage", curPage);
		request.setAttribute("prevPageBlock", pDTO.getPrevPageBlock());
		request.setAttribute("nextPageBlock", pDTO.getNextPageBlock());
		request.setAttribute("totalCount", pDTO.getTotalCount());
		request.setAttribute("totalPage", totalPage);

		// 키워드도 같이 넘겨야됨!
		return "/main";
	}
	

	

	@RequestMapping(value="/categorySearch")
	public String categorySearch(@RequestParam("category") String category, HttpServletRequest request,HttpSession session, Model m){

		MemberDTO mDto = (MemberDTO)session.getAttribute("login");
		String addr = "null";
		List<PostDTO> list2 = new ArrayList<>();
		if(mDto != null) { // 로그인 된 상태
			String userid = mDto.getUserid();
			addr = mDto.getAddr();
			list2 = fService.favoriteList(userid);	
		}
		String curPage = request.getParameter("curPage");
		if(curPage == null) {
			curPage = "1";
		}
		HashMap<String,String> map = new HashMap<>();
		map.put("addr", addr);
		map.put("category", category);
		
		
		PageDTO pDTO = pService.searchByCategory(Integer.parseInt(curPage),(mDto==null?false:true),map);
		HashMap<String,String> categoryMap = new HashMap<>();
		categoryMap.put("D","디지털, 가전");
		categoryMap.put("H","가구, 인테리어");
		categoryMap.put("BY","유아동");
		categoryMap.put("L","생활, 가공식품");
		categoryMap.put("S","스포츠, 레저");
		categoryMap.put("W","여성의류, 여성잡화");
		categoryMap.put("M","남성의류, 남성잡화");
		categoryMap.put("G","게임, 취미");
		categoryMap.put("BT","뷰티, 미용");
		categoryMap.put("PET","반려동물용품");
		categoryMap.put("BK","도서");
		categoryMap.put("T","티켓");
		categoryMap.put("P","식물");
		categoryMap.put("E","기타");
		
		int totalPage = pDTO.getTotalCount()/pDTO.getPerPage();
		if(pDTO.getTotalCount()%pDTO.getPerPage() != 0) totalPage++;//나머지 있을 경우 1페이지 증가 
		// 넘어온 카테고리로 해당 카테고리 이름 얻어옴
		String categoryName = categoryMap.get(category);
		
		// 다중파일 대비 이미지파싱
		List<PostDTO> list = pDTO.getList();
		for(int i = 0 ; i < list.size() ; i++) {
			String[] image = list.get(i).getpImage().split(" ");
			list.get(i).setpImage(image[0]);
		}
		
		m.addAttribute("favoriteList",list2);
		m.addAttribute("category",category);
		m.addAttribute("categoryMap",categoryMap);
		m.addAttribute("postList",list);
		m.addAttribute("blockPerPage", pDTO.getBlockPerPage());
		m.addAttribute("offset", pDTO.getOffset());
		m.addAttribute("curPage", curPage);
		m.addAttribute("prevPageBlock", pDTO.getPrevPageBlock());
		m.addAttribute("nextPageBlock", pDTO.getNextPageBlock());
		m.addAttribute("totalCount", pDTO.getTotalCount());
		m.addAttribute("totalPage", totalPage);
		m.addAttribute("categoryName", categoryName); //"category이름" 으로 검색된결과 뿌려줘야됨
		return "/main";
	}
}
