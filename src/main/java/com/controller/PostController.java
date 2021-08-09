package com.controller;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.CommentsDTO;
import com.dto.FavoriteDTO;
import com.dto.MemberDTO;
import com.dto.PostDTO;
import com.service.AlarmService;
import com.service.CommentsService;
import com.service.FavoriteService;
import com.service.MemberService;
import com.service.PostService;
import com.service.TransactionService;


@Controller
public class PostController {
	@Autowired
	PostService pService;
	
	@Autowired
	MemberService mService;
	@Autowired
	FavoriteService fService;
	@Autowired
	CommentsService cService;
	@Autowired
	TransactionService tService;
	
	@Resource(name="uploadPath")
	String uploadPath;

	private Logger complaintLogger = LoggerFactory.getLogger("statistics");
	
	// 글쓰기 ---------------------------------------------------------------------------
	@RequestMapping(value="/loginCheck/postWrite", method=RequestMethod.GET)
	public String postWrite() {
		return "redirect:../postWrite"; // 글쓰기화면으로전환
	}
	
	@RequestMapping(value="/loginCheck/postWrite", method=RequestMethod.POST)
	public String postWrite(MultipartHttpServletRequest mtfRequest, HttpSession session) {
		// 입력되어 온 값 받기
		String pCategory = mtfRequest.getParameter("pCategory");
		String pTitle = mtfRequest.getParameter("pTitle");
		String pPrice = mtfRequest.getParameter("pPrice");
		String pContent = mtfRequest.getParameter("pContent");
		List<MultipartFile> fileList = mtfRequest.getFiles("file");
		
		// 입력되어온 내용 pDto에 값 넣기
		PostDTO pDto = new PostDTO();
		MemberDTO mDto = (MemberDTO)session.getAttribute("login");
		pDto.setUserid(mDto.getUserid()); // 아이디
		pDto.setAddr(mDto.getAddr()); // 주소
		pDto.setpCategory(pCategory); // 카테고리
		pDto.setpTitle(pTitle); // 제목
		pContent = pContent.replaceAll("\r\n", "<br>");
		pDto.setpContent(pContent); // 글내용
		pDto.setpPrice(Integer.parseInt(pPrice)); // 가격
		pDto.setpStatus("0"); // default로 들어가는 값
		pDto.setpPull("3"); // default로 들어가는 값
		pDto.setpHit(0); // default로 들어가는 값
		
		String dbSave = ""; // db에 저장될 이미지파일이름의 조합
		for(int i = 0 ; i < fileList.size() ; i++) {
			MultipartFile mf = fileList.get(i);
			String originalFileName = mf.getOriginalFilename(); // 원본파일 이름
			String safeFile = uploadPath + System.currentTimeMillis()+"_" + mDto.getUserid() +"_"+ i +"_"+originalFileName;
			
			String dbSaveFile = System.currentTimeMillis()+"_" + mDto.getUserid() +"_"+ i +"_"+originalFileName;
			if(i == fileList.size()-1) {
				dbSave += dbSaveFile;
			} else {
				dbSave += dbSaveFile+" ";
			}
			
			try {
				mf.transferTo(new File(safeFile));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		pDto.setpImage(dbSave);
		int n = pService.newPost(pDto);
		complaintLogger.info("PostController postWriteSuccess- userid: "+pDto.getUserid());
		return "redirect:../"; // main으로 이동하는 경로
	}
	
	
	// 글수정 -----------------------------------------------------------
	
	@RequestMapping(value="/loginCheck/postUpdate", method=RequestMethod.GET)
	public ModelAndView postUpdate(@RequestParam("pNum") String pNum) {
		ModelAndView mav = new ModelAndView();
		PostDTO pDto = pService.getPostByPNum(Integer.parseInt(pNum));
		// 글내용 줄바꿈 파싱
		String pContent = pDto.getpContent();
		pContent = pContent.replaceAll("<br>", "\r\n");
		pDto.setpContent(pContent);
		// 이미지파일 파일명단위로 끊어서 배열에 넣기
		String pImage = pDto.getpImage();
		String[] images = pImage.split(" ");
		
		mav.addObject("imagesArr", images);
		mav.addObject("postRetrieve", pDto);
		mav.setViewName("postUpdate");
		return mav;
	}
	
	@RequestMapping(value="/loginCheck/postUpdate", method=RequestMethod.POST)
	public String postUpdate(MultipartHttpServletRequest mtfRequest, HttpSession session) {
		
		// 로그인 정보
		MemberDTO mDto = (MemberDTO)session.getAttribute("login");
		
		// 입력되어 온 값 받기
		String pNum = mtfRequest.getParameter("pNum"); // 수정할 글을 불러오는 용
		
		String pCategory = mtfRequest.getParameter("pCategory"); // 수정하는 내용
		String pTitle = mtfRequest.getParameter("pTitle"); // 수정하는 내용
		String pPrice = mtfRequest.getParameter("pPrice"); // 수정하는 내용
		String pContent = mtfRequest.getParameter("pContent"); // 수정하는내용
		List<MultipartFile> fileList = mtfRequest.getFiles("file"); // 수정하는내용 -> 비어있다면 이미지는 수정하지 않는것.
		
		// 수정할 글자체의 정보
		PostDTO pDto = pService.getPostByPNum(Integer.parseInt(pNum));
		
		// 로그인되어있는 회원과 글작성한 회원이 동일한 경우에만 글수정된다.
		if(mDto.getUserid().contentEquals(pDto.getUserid())) {
			PostDTO updateDto = new PostDTO();
			updateDto.setUserid(pDto.getUserid());
			updateDto.setAddr(pDto.getAddr());
			updateDto.setpNum(pDto.getpNum()); // 글번호
			updateDto.setpCategory(pCategory); // 카테고리
			updateDto.setpTitle(pTitle); // 제목
			// 글내용 줄바꿈 파싱
			pContent = pContent.replaceAll("\r\n", "<br>");
			updateDto.setpContent(pContent); // 글내용
			updateDto.setpPrice(Integer.parseInt(pPrice)); // 가격
			updateDto.setpStatus(pDto.getpStatus());
			updateDto.setpPull(pDto.getpPull());
			updateDto.setpHit(pDto.getpHit());
				
			int flag = 0;
			String dbSave = "";// db에 저장될 이미지파일이름의 조합
			for(int i = 0 ; i < fileList.size() ; i++) {
				MultipartFile mf = fileList.get(i);
				String originalFileName = mf.getOriginalFilename(); // 원본파일 이름
				if(originalFileName.length() == 0) {
					flag++;
					break;
				}
				String safeFile = uploadPath + System.currentTimeMillis()+"_" + mDto.getUserid() +"_"+ i +"_"+originalFileName;
				
				String dbSaveFile = System.currentTimeMillis()+"_" + mDto.getUserid() +"_"+ i +"_"+originalFileName;
				if(i == fileList.size()-1) {
					dbSave += dbSaveFile;
				} else {
					dbSave += dbSaveFile+" ";
				}
				try {
					mf.transferTo(new File(safeFile));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(flag == 1) { // 이미지 수정이 없어서 반복문수행안하고 나온경우
				updateDto.setpImage(pDto.getpImage());
			} else {
				// 이미지가 수정된 경우 기존이미지파일들은 삭제해준다.
				String[] originalImages = pDto.getpImage().split(" "); 
				for(String s :originalImages) { 
					String deleteImg = uploadPath+s;
					File file = new File(deleteImg); 
					file.delete(); 
				}
				updateDto.setpImage(dbSave);
			}
			
			// updateDto에 저장된 내용으로 글내용 수정한다.
			int n = pService.updatePost(updateDto);
		
		}
		
		return "redirect:../postDetail?pNum="+pDto.getpNum();
	}
	
	// 글삭제 -----------------------------------------------------------
	@RequestMapping(value="/loginCheck/postDelete", method=RequestMethod.GET)
	public String postDelete(@RequestParam("pNum") String pNum, HttpSession session) {
		
		MemberDTO mDto = (MemberDTO)session.getAttribute("login");
		PostDTO pDto = pService.getPostByPNum(Integer.parseInt(pNum));
		
		if(mDto.getUserid().contentEquals(pDto.getUserid())) { // 로그인 정보와 삭제하려는 글의 id가 동일한 경우에만 삭제된다.
			int deleteResult = pService.deletePostByPNum(Integer.parseInt(pNum));
			
			if(deleteResult==1) { // 게시글 삭제된 경우
				// 삭제되는 게시글의 이미지도 삭제
				String[] originalImages = pDto.getpImage().split(" ");
				for(String s :originalImages) { 
					String deleteImg = uploadPath+s;
					File file = new File(deleteImg); 
					file.delete(); 
				}
	    	}
			
		}
		return "redirect:../"; // main.jsp로 이동
	}
	@RequestMapping(value="/loginCheck/mypostDelete", method=RequestMethod.GET)
	public String mypostDelete(@RequestParam("pNum") String pNum, HttpSession session,RedirectAttributes attr,Model m) {
		
		MemberDTO mDto = (MemberDTO)session.getAttribute("login");
		PostDTO pDto = pService.getPostByPNum(Integer.parseInt(pNum));
		
		if(mDto.getUserid().contentEquals(pDto.getUserid())) { // 로그인 정보와 삭제하려는 글의 id가 동일한 경우에만 삭제된다.
			int deleteResult = pService.deletePostByPNum(Integer.parseInt(pNum));
			
			if(deleteResult==1) { // 게시글 삭제된 경우
				// 삭제되는 게시글의 이미지도 삭제
				String[] originalImages = pDto.getpImage().split(" ");
				for(String s :originalImages) { 
					String deleteImg = uploadPath+s;
					File file = new File(deleteImg); 
					file.delete(); 
				}
	    	}
			
		}
		List<PostDTO> list = pService.mypostList(mDto.getUserid());
		m.addAttribute("mypostList", list);
		return "mypostList";
	}
	
	@RequestMapping(value = "/loginCheck/postDelAll")
	public String myOrderDelAll(@RequestParam("data") String num) {
		String [] x = num.split(",");
		List<String> list = Arrays.asList(x);
		pService.postAllDel(list);
		return "mypostList";
	}// 내 게시물 체크삭제
	
	
	// 끌올 ------------------------------------------------------------
	@RequestMapping(value="/loginCheck/postPull", method=RequestMethod.GET)
	public String postPull(@RequestParam("pNum") String pNum, HttpSession session, Model m){ 
		PostDTO pDto = pService.getPostByPNum(Integer.parseInt(pNum)); // 글번호로 해당 글정보 불러오기
		String pPull = pDto.getpPull();
		
		// 글 작성 시간을 기준으로 끌올가능여부를 계산한다.
		String pDate = pDto.getpDate();
		SimpleDateFormat fDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");// String타입인 pDate를 Date형식으로 바꿔주는 SimpleDateFormat
		Date now = new Date();// 현재시간
		Date write = null;// 글 작성된 시간
		long calDateDay = 0;// 글 작성된 시간과 현재시간의 차이
		try {
			write = fDate.parse(pDate);
			
			long calDate = now.getTime()-write.getTime();
			
			calDateDay = calDate/(24*60*60*1000);
			calDateDay = Math.abs(calDateDay);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		m.addAttribute("pDto", pDto);
		m.addAttribute("calDateDay", Long.toString(calDateDay));
		if(calDateDay < 3 || Integer.parseInt(pPull) == 0) { // 글작성시간과 현재시간의 차이가 3일 이내라면 끌올불가능
			m.addAttribute("pullAvailable", "F");
			
		} else { // 3일 이상이라면 끌올 가능
			m.addAttribute("pullAvailable", "T");
		}
		return "pullPost";
	}
	
	@RequestMapping(value="/loginCheck/postPull", method=RequestMethod.POST)
	public String pullPost(String pNum, HttpSession session) {
		MemberDTO mDto = (MemberDTO)session.getAttribute("login");
		PostDTO pDto = pService.getPostByPNum(Integer.parseInt(pNum));
		if(mDto.getUserid().equals(pDto.getUserid())) {
			String pullTime = pDto.getpPull();
			pullTime = String.valueOf(Integer.parseInt(pullTime) - 1);
			pDto.setpPull(pullTime);
			int pullResult = pService.pullPost(pDto);
			session.setAttribute("mesg", "끌올 완료되었습니다.");
		}
		return "redirect:../loginCheck/MyPostList"; // 내글보기로 이동
	}
	
	// 글 상세보기 ---------------------------------------------------------------------------
	@RequestMapping(value = "/postDetail")
	public ModelAndView postDetail(HttpSession session, @RequestParam("pNum") String pNum) {
		ModelAndView mav = new ModelAndView();
		MemberDTO uDTO = (MemberDTO)session.getAttribute("login");
    	PostDTO pDTO = pService.getPostByPNum(Integer.parseInt(pNum));
    	MemberDTO mDTO = mService.mypage(pDTO.getUserid());
    	List<CommentsDTO> comments = cService.getCommentsByPNum(Integer.parseInt(pNum));

    	//게시글 조회수 증가
    	pDTO.setpHit(pDTO.getpHit()+1);
    	int updateResult = pService.updatePHit(pDTO);
    	// 좋아요 카운트
    	int favoriteCount = fService.getFavoriteCountByPNum(Integer.parseInt(pNum));
    	int saleCount = tService.saleCount(pDTO.getUserid());

    	if(updateResult!=1) { // 조회수 업데이트가 실패했을 경우 
			session.setAttribute("mesg", "게시물 접근 중 오류가 발생하였습니다.");
			mav.setViewName("main");
    	} else { // 조회수 업데이트 성공 후
    		//게시글 정보 전달을 위해 request에 설정
    		mav.addObject("pNum", String.valueOf(pDTO.getpNum()));
    		mav.addObject("pCategory", pDTO.getpCategory());
    		mav.addObject("pHit", String.valueOf(pDTO.getpHit()));
    		mav.addObject("pPrice", String.valueOf(pDTO.getpPrice()));
    		mav.addObject("addr", pDTO.getAddr());
    		mav.addObject("pContent", pDTO.getpContent());
    		mav.addObject("pDate", pDTO.getpDate());
    		mav.addObject("pTitle", pDTO.getpTitle());
    		mav.addObject("pStatus", pDTO.getpStatus());
    		
    		// 다중파일 대비 이미지파싱
    		String[] originalImages = pDTO.getpImage().split(" ");
    		mav.addObject("imageDetail", originalImages);
    		
    		// 다중파일인 경우 postDetail에서 추가부분이 있어 구분자로 넣어줌
    		if(originalImages.length == 1) { 
    			mav.addObject("isMultiFile","N");
    		} else {
    			mav.addObject("isMultiFile","Y");
    		}
    		
    		mav.addObject("pImage", pDTO.getpImage());
    		switch (pDTO.getpCategory()) {
    		case "D" :
    			mav.addObject("category", "디지털, 가전");
    			break;
        	case "H" :
        		mav.addObject("가구, 인테리어");
        		break;
        	case "BY" :
        		mav.addObject("유아동");
        		break;
        	case "L" :
        		mav.addObject("생활, 가공식품");
        		break;
        	case "S" :
        		mav.addObject("스포츠, 레저");
        		break;
        	case "W" :
        		mav.addObject("여성의류, 여성잡화");
        		break;
        	case "M" :
        		mav.addObject("남성의류, 남성잡화");
        		break;
        	case "G" :
        		mav.addObject("게임, 취미");
        		break;
        	case "BT" :
        		mav.addObject("뷰티, 미용");
        		break;
        	case "PET" :
        		mav.addObject("반려동물용품");
        		break;
        	case "BK" :
        		mav.addObject("도서");
        		break;
        	case "T" :
        		mav.addObject("티켓");
        		break;
        	case "P" :
        		mav.addObject("식물");
        		break;
        	case "E" :
        		mav.addObject("기타");
        		break;
    		}
    		
    		DecimalFormat formatter = new DecimalFormat("###,###");
    		mav.addObject("price", formatter.format(pDTO.getpPrice()));
    		
        	//게시글을 작성한 유저 정보 전달을 위해 request에 설정
    		mav.addObject("userid", mDTO.getUserid());
    		mav.addObject("username", mDTO.getUsername());
    		mav.addObject("userImage", mDTO.getUserimage());
    		mav.addObject("nickName", mDTO.getNickName());
        	
        	// 게시글 내용 전달을 위해 설정
    		mav.addObject("comments", comments);
    		mav.addObject("favoriteCount", String.valueOf(favoriteCount));
    		mav.addObject("saleCount", String.valueOf(saleCount));
        	
        	//게시글의 관심 설정 정보 전달을 위해 request에 설정
        	if(uDTO!=null) { // 로그인 정보가 있을 경우
        		FavoriteDTO temp = new FavoriteDTO();
        		temp.setUserId(uDTO.getUserid());
        		temp.setpNum(Integer.parseInt(pNum));
        		FavoriteDTO fDTO = fService.getFavorite(temp); // 관심 정보 조회
        		if(fDTO!=null) { // 관심 O
        			mav.addObject("favorite", true);
        		} else { // 관심 X
        			mav.addObject("favorite", false);
    			}
        	} else { // 로그인 정보가 없을 경우
        		mav.addObject("favorite", false);
        	}
			mav.setViewName("postDetail");
    	}
		return mav;
	}
	
	// 댓글 ---------------------------------------------------------------------------
	@RequestMapping(value = "/loginCheck/commentsWrite")
	public String commentsWrite(HttpSession session, @RequestParam Map<String, String> map) {
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		String pNum = map.get("pNum");
		String parentNum = map.get("parentNum");
		String cContent = map.get("cContent");
		String nextPage = "main";
		
		CommentsDTO cDTO = new CommentsDTO();
			
		if(parentNum!=null) {
			CommentsDTO parentDTO = cService.getCommentByCNum(Integer.parseInt(parentNum));
			cDTO.setParentNum(Integer.parseInt(parentNum));
			cDTO.setcLevel(parentDTO.getcLevel()+1);
		} else {
			cDTO.setParentNum(0);
			cDTO.setcLevel(1);
		}
		cDTO.setpNum(Integer.parseInt(pNum));
		cDTO.setcContent(cContent.replaceAll("\r\n", "<br>"));
		cDTO.setUserid(dto.getUserid());
			
		int insertResult = cService.insertComments(cDTO);
			
		if(insertResult!=1) { // 게시글 업데이트가 실패했을 경우 
			session.setAttribute("mesg", "댓글 작성 중 오류가 발생하였습니다.");
	    } else {
	    	nextPage="postDetail?pNum="+pNum;
	    }
		return "redirect:../"+nextPage;
	}
	
	@RequestMapping(value = "/loginCheck/commentsUpdate")
	public String commentsUpdate(HttpSession session, @RequestParam Map<String, String> map) {
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		String pNum = map.get("pNum");
		String cNum = map.get("cNum");
		String cContent = map.get("cContent");
		String nextPage = "postDetail?pNum="+pNum;
		if(dto==null) { // 로그인 정보가 없는 경우
			session.setAttribute("mesg", "로그인 정보가 없습니다.");
			nextPage = "main";
		} else { // 로그인 정보가 있는 경우
			CommentsDTO cDTO = cService.getCommentByCNum(Integer.parseInt(cNum));
			cDTO.setcContent(cContent.replaceAll("\r\n", "<br>"));
			
			if(dto.getUserid().equals(cDTO.getUserid())){ // 수정할 댓글과 로그인 유저 정보가 일치하는 경우
				int updateResult = cService.updateComment(cDTO);
				
				if(updateResult!=1) { // 댓글 수정이 실패했을 경우 
					session.setAttribute("mesg", "댓글 수정 중 오류가 발생하였습니다.");
					nextPage="postDetail?pNum="+pNum;
				}
			} else {
				session.setAttribute("mesg", "자신이 쓴 댓글만 수정이 가능합니다.");
			}
		}
		return "redirect:../"+nextPage;
	}
	
	@RequestMapping(value = "/loginCheck/commentsDelete")
	public String commentsDelete(HttpSession session, @RequestParam Map<String, String> map) {
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		String pNum = map.get("pNum");
		String cNum = map.get("cNum");
		String nextPage = "postDetail?pNum="+pNum;

		int deleteResult = cService.deleteCommentByCNum(Integer.parseInt(cNum));
				
		if(deleteResult==0) { // 댓글 삭제가 실패했을 경우 
			session.setAttribute("mesg", "댓글 삭제 중 오류가 발생하였습니다.");
			nextPage="postDetail?pNum="+pNum;
		}
		return "redirect:../"+nextPage;
	}
	
	
	
}
