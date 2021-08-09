package com.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.Alarm;
import com.dto.FavoriteDTO;
import com.dto.MemberDTO;
import com.dto.MyOrderSheetDTO;
import com.dto.PostDTO;
import com.service.AlarmService;
import com.service.FavoriteService;
import com.service.MemberService;
import com.service.OrderSheetService;
import com.service.PostService;
import com.service.TransactionService;

import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;

@Controller
public class MypageController {
	@Autowired
	MemberService mService;
	@Autowired
	FavoriteService fService;
	@Autowired
	PostService pService;
	@Autowired
	OrderSheetService oService;
	@Autowired
	TransactionService tService;
	@Autowired
	AlarmService aService;
	
	@Resource(name="uploadPath")
	String uploadPath;
	
	private Logger complaintLogger = LoggerFactory.getLogger("statistics");
	
	@RequestMapping(value = "/loginCheck/mypage")
	public String myPage(HttpSession session) {
		//System.out.println("myPage호출====");
		MemberDTO dto =(MemberDTO)session.getAttribute("login");
		String userid = dto.getUserid(); //세션에서 id 얻기
		dto= mService.mypage(userid);
		session.setAttribute("login", dto);
		return "redirect:../mypage"; // 주소에 해당하는 페이지를 servlet-context에 등록사용
		//return "mypage";// 주소에 해당하는 페이지를 servlet-context에 등록사용
	}//mypage
	
	@RequestMapping(value = "/loginCheck/MemberUpdate", produces = "text/plain;charset=UTF-8") 
	public String memberUpdate(String userid, String username, String resultNick,
			String addr, String phone, String email1, String email2, HttpSession session,MultipartFile file) throws IOException {
		MemberDTO mdto =(MemberDTO)session.getAttribute("login"); // 로그인 정보
		String passwd = mdto.getPasswd(); // mypage.jsp에서 넘겨주는 값이 없어서 dto에서 뽑아왔어요!(어차피 수정되는부분이아니라 null들어가도 상관없긴함)
		MemberDTO updateDto = new MemberDTO();
		updateDto.setUserid(userid);
		updateDto.setUsername(username);
		updateDto.setPasswd(passwd);
		updateDto.setNickName(resultNick);
		updateDto.setAddr(addr);
		updateDto.setPhone(phone);
		updateDto.setEmail1(email1);
		updateDto.setEmail2(email2);
		
		String fileName = file.getOriginalFilename();
		
		if(fileName.length() != 0) { // 사진파일 변경된게 옴
			fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
			UUID uuid = UUID.randomUUID();
			fileName = uuid.toString()+"_"+fileName;
			String uploadPathProfile = uploadPath+"/profile/";
			File target = new File(uploadPathProfile, fileName);
			updateDto.setUserimage(fileName);
			// 경로생성
			if(! new File(uploadPath).exists()) {
				new File(uploadPath).mkdirs();
			}
			// 파일복사
			try {
				FileCopyUtils.copy(file.getBytes(), target);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else { // 사진파일 변경된거 없음(기존 정보에 저장되어있는 이미지를 넣어준다)
			updateDto.setUserimage(mdto.getUserimage());
		}
		
	
		
		mService.memberUpdate(updateDto);
		session.setAttribute("mesg", "회원정보가 수정되었습니다."); 
		return "redirect:../loginCheck/mypage";
	}//memberUpdate
	
	
	@RequestMapping(value = "/loginCheck/confirmNickC", produces = "text/plain;charset=UTF-8")
	public String confirmNick(@RequestParam("nickName") String nickName, Model m) {
		int n = mService.nickCheck(nickName);
		m.addAttribute("nickCheck", n);
		m.addAttribute("nickName", nickName);
		return "confirmNick";
	}//닉네임체크  중복확인
	
	@RequestMapping(value = "/loginCheck/nickUpdate", produces = "text/plain;charset=UTF-8")
	public String nickUpdate(@RequestParam("nickName") String nickName, HttpSession session, Model m) {
		MemberDTO dto =(MemberDTO)session.getAttribute("login");
		MemberDTO dto2 = new MemberDTO();
		dto2.setUserid(dto.getUserid());
		dto2.setNickName(nickName);
		dto2.setAddr(dto.getAddr());
		dto2.setEmail1(dto.getEmail1());
		dto2.setEmail2(dto.getEmail2());
		dto2.setPasswd(dto.getPasswd());
		dto2.setPhone(dto.getPhone());
		dto2.setUserimage(dto.getUserimage());
		dto2.setUsername(dto.getUsername());
		mService.nickUpdate(dto2);
		m.addAttribute("nickDTO", dto2.getNickName());
		return "okNick";
	}//닉네임 업데이트
	
	@RequestMapping(value = "/loginCheck/addrCheckC", produces = "text/plain;charset=UTF-8")
	public String addrCheck(@RequestParam("dong") String addr, HttpSession session, Model m) {
		MemberDTO dto =(MemberDTO)session.getAttribute("login");
		String userid = dto.getUserid();
		String userName = dto.getUsername();
		MemberDTO dto2 = new MemberDTO();
		dto2.setUsername(userName);
		dto2.setUserid(userid);
		dto2.setAddr(addr);
		int n = mService.addrAuth1(dto2);//db에저장된 주소랑 같은지 확인
		if(n==1) {
			m.addAttribute("auth1", addr);
		}else {
			 n = mService.addrAuth2(dto2);//현재위치로 주소 변경
			 m.addAttribute("auth2", addr);
		}
		return "addrauth";
	}//동네 인증
	
	@RequestMapping(value = "/loginCheck/Withdrawal")
	public String withdrawal(HttpSession session, Model m) {
		MemberDTO dto =(MemberDTO)session.getAttribute("login");
		String userid = dto.getUserid();
		mService.withdrawal(userid);
		session.setAttribute("withdrawal", "정상적으로 회원탈퇴되었습니다."); 
		return "redirect:../";
	}//회원탈퇴
	
	@RequestMapping(value = "loginCheck/FavoriteList", produces = "text/plain;charset=UTF-8")
	public String favoritList(HttpSession session,RedirectAttributes attr, Model m) {
		MemberDTO dto =(MemberDTO)session.getAttribute("login");
		String userid = dto.getUserid();
		List<PostDTO> list = fService.favoriteList(userid);
		for(int i = 0 ; i < list.size() ; i++) {
			String[] image = list.get(i).getpImage().split(" ");
			list.get(i).setpImage(image[0]);
		}
		attr.addFlashAttribute("favoriteList", list);
		return "redirect:../favoriteList";
		//m.addAttribute("favoriteList", list);
		//return "favoriteList";
	}//관심목록 리스트
	
	@RequestMapping(value = "loginCheck/favoriteDel")
	public String favoriteDel(@RequestParam("num") int num, HttpSession session) {
		MemberDTO dto =(MemberDTO)session.getAttribute("login");
		fService.favoriteDel(num);
		return "redirect:/loginCheck/FavoriteList";
	}//관심목록 개별삭제
	
	@RequestMapping(value = "loginCheck/MyPostList", produces = "text/plain;charset=UTF-8")
	public String mypostList(HttpSession session, RedirectAttributes attr,Model m) {
		MemberDTO dto =(MemberDTO)session.getAttribute("login");
		String userid = dto.getUserid();
		List<PostDTO> list = pService.mypostList(userid);
		for(int i = 0 ; i < list.size() ; i++) {
			String[] image = list.get(i).getpImage().split(" ");
			list.get(i).setpImage(image[0]);
		}
		attr.addFlashAttribute("mypostList", list);
		return "redirect:../mypostList";
	}//내 게시물 보기
	
	@RequestMapping(value = "loginCheck/MyOrdersheetList", produces = "text/plain;charset=UTF-8")
	public String MyOrdersheetList(HttpSession session, Model m,RedirectAttributes attr) {
		MemberDTO dto =(MemberDTO)session.getAttribute("login");
		String userid = dto.getUserid();
		List<MyOrderSheetDTO> list = oService.myordersheetList(userid);
		for(int i = 0 ; i < list.size() ; i++) {
			String[] image = list.get(i).getpImage().split(" ");
			list.get(i).setpImage(image[0]);
		}
		attr.addFlashAttribute("ordersheetList", list);
		return "redirect:../myordersheetList";
	}// 주문서 발신함
	
	@RequestMapping(value = "/loginCheck/myOrderDel")
	public String myOrderDel(@RequestParam("oNum") int num) {
		oService.orderDel(num);
		return "redirect:/loginCheck/MyOrdersheetList";
	}// 발신함 개별삭제
	
	@RequestMapping(value = "/loginCheck/myOrderDelAll")
	public String myOrderDelAll(@RequestParam("data") String data) {
		String [] x = data.split(",");
		List<String> list = Arrays.asList(x);
		oService.orderAllDel(list);
		return "redirect:/loginCheck/MyOrdersheetList";
	}// 발신함 체크삭제
	
	@RequestMapping(value = "loginCheck/OrdersheetList", produces = "text/plain;charset=UTF-8")
	public String OrdersheetList(HttpSession session, Model m,RedirectAttributes attr) {
		MemberDTO dto =(MemberDTO)session.getAttribute("login");
		String userid = dto.getUserid();
		List<MyOrderSheetDTO> list = oService.ordersheetList(userid);
		for(int i = 0 ; i < list.size() ; i++) {
			String[] image = list.get(i).getpImage().split(" ");
			list.get(i).setpImage(image[0]);
		}
		attr.addFlashAttribute("ordersheetList", list);
		return "redirect:../ordersheetList";
	}// 주문서 수신함
	
	@RequestMapping(value = "/loginCheck/OrderDel")
	public String OrderDel(@RequestParam("oNum") int num) {
		oService.orderDel(num);
		return "redirect:/loginCheck/OrdersheetList";
	}// 수신함 개별삭제
	
	@RequestMapping(value = "/loginCheck/OrderDelAll")
	public String OrderDelAll(@RequestParam("data") String data) {
		String [] x = data.split(",");
		List<String> list = Arrays.asList(x);
		oService.orderAllDel(list);
		return "redirect:/loginCheck/OrdersheetList";
	}// 수신함 체크삭제
	
	@RequestMapping(value = "/loginCheck/myPopupMessage", produces = "text/plain;charset=UTF-8")
	public String myPopupMessage(@RequestParam("oNum") String oNum, Model m) {
		List<MyOrderSheetDTO> list = oService.message(oNum);
		System.out.println(list);
		int saleCount = tService.saleCount(list.get(0).getbUserid());
		m.addAttribute("saleCount", saleCount);
		m.addAttribute("message", list);
		return "mypopupmessage";
	}//myPopupMessage 
	
	@RequestMapping(value = "/loginCheck/PopupMessage", produces = "text/plain;charset=UTF-8")
	public String PopupMessage(@RequestParam("oNum") String oNum, Model m) {
		List<MyOrderSheetDTO> list = oService.message(oNum);
		int saleCount = tService.saleCount(list.get(0).getbUserid());
		m.addAttribute("saleCount", saleCount);
		m.addAttribute("message", list);
		return "popupmessage";
	}//PopupMessage
	
	@RequestMapping(value = "/loginCheck/myPopupOrderDel")
	public String myPopupOrderDel(@RequestParam("oNum") int num, @RequestParam("popup") Integer popup) {
		oService.orderDel(num);
		if(popup == null) {
			return "loginCheck/MyOrdersheetList";
		}else {
			return "popupclose";
		}
	}// 메세지창에서 삭제(발신) 
	
	@RequestMapping(value = "/loginCheck/PopupOrderDel")
	public String PopupOrderDel(@RequestParam("oNum") int num, @RequestParam("popup") Integer popup) {
		oService.orderDel(num);
		if (popup == null) {
			return "loginCheck/OrdersheetList";
		} else {
			return "popupclose";
		}
	}// 메세지창에서 삭제(수신)
	
	@RequestMapping(value = "/loginCheck/Sale")
	public String sale(HttpSession session, @RequestParam("bUserid") String bUserid, 
			@RequestParam("sUserid") String sUserid, @RequestParam("pNum") int pNum,
			RedirectAttributes attr) {
		MemberDTO dto =(MemberDTO)session.getAttribute("login");
		String userid = dto.getUserid();
		int n = oService.sale(bUserid, sUserid, pNum);
		complaintLogger.info("MypageController PurchaseComplete - bUserid: "+sUserid+", sUserid: "+sUserid+", pNum: "+pNum);
		attr.addFlashAttribute("sale", n);
		
		PostDTO pDTO = pService.getPostByPNum(pNum);
		
		SimpleDateFormat timeFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Calendar time = Calendar.getInstance();
		
		SlackApi webhook = new SlackApi("https://hooks.slack.com/services/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"); // 본인의 슬랙 URL
		webhook.call(new SlackMessage("#동동", "admin", "transaction : userid = " + userid + ", item = " + pDTO.getpTitle() +", " + timeFormat.format(time.getTime())));
		
		return "redirect:../salecomplete";
	}
	
	@RequestMapping(value = "loginCheck/BuyList", produces = "text/plain;charset=UTF-8")
	public String buyList(HttpSession session, RedirectAttributes attr,Model m) {
		MemberDTO dto =(MemberDTO)session.getAttribute("login");
		String userid = dto.getUserid();
		List<PostDTO> plist = tService.purchaseList(userid);
		for(int i = 0 ; i < plist.size() ; i++) {
			String[] image = plist.get(i).getpImage().split(" ");
			plist.get(i).setpImage(image[0]);
		}
		attr.addFlashAttribute("purchaseList", plist);
		return "redirect:../BuyList";
		//m.addAttribute("purchaseList", plist);
		//return "BuyList";
		
	}//구매내역 
	
	@RequestMapping(value = "loginCheck/SaleList", produces = "text/plain;charset=UTF-8")
	public String saleList(HttpSession session, RedirectAttributes attr,Model m) {
		MemberDTO dto =(MemberDTO)session.getAttribute("login");
		String userid = dto.getUserid();
		List<PostDTO> slist = tService.saleList(userid);
		for(int i = 0 ; i < slist.size() ; i++) {
			String[] image = slist.get(i).getpImage().split(" ");
			slist.get(i).setpImage(image[0]);
		}
		attr.addFlashAttribute("saleList", slist);
		return "redirect:../SaleList";
		//m.addAttribute("saleList", slist);
		//return "SaleList";
	}//판매내역  
	
	@RequestMapping(value = "/loginCheck/favorateSwitch")
	public @ResponseBody boolean favorateSwitch(HttpSession session, @RequestParam Map<String, String> map) {
		MemberDTO mDTO = (MemberDTO)session.getAttribute("login"); // 로그인 정보 조회
		String pNum = map.get("pNum");
		boolean favorite = (map.get("favorite").equals("true")?true:false); // 현재 상태 저장 T: 관심O / F: 관심X
		boolean returnValue;
		
		FavoriteDTO fDTO = new FavoriteDTO();
		fDTO.setpNum(Integer.parseInt(pNum));
		fDTO.setUserId(mDTO.getUserid());
			
		// 현재 상태에 따라 if_else
		if(!favorite) { // 관심 X 일때 -> 관심목록에 추가하기
			int insertResult = fService.insertFavoite(fDTO); // 관심목록에 저장
			if(insertResult==1) { // 성공여부 확인
					
				returnValue = true; // 관심목록 저장이 완료된 것을 페이지에 표시하기 위해 비동기 전달
			} else {
				session.setAttribute("mesg", "관심목록 저장에 실패하였습니다.");
				returnValue = false;
			}
		}else { // 관심 O 일때 -> 관심목록에서 삭제하기
			int deleteResult = fService.deleteFavoite(fDTO); // 관심목록에서 삭제
			if(deleteResult==1) { // 성공여부 확인
				returnValue = false; // 관심목록 삭제가 완료된 것을 페이지에 표시하기 위해 비동기 전달
			} else {
				session.setAttribute("mesg", "관심목록 삭제에 실패하였습니다.");
				returnValue = true;
			}
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/userprofile")
	public ModelAndView userProfile(HttpSession session, @RequestParam("userid") String userid) {
		MemberDTO dto = mService.mypage(userid);
		int saleCount = tService.saleCount(userid);
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("nickName",dto.getNickName());
		mav.addObject("userImage",dto.getUserimage());
		mav.addObject("userid",userid);
		mav.addObject("saleCount",saleCount);
		mav.setViewName("userprofile");

		return mav;
	}
	
	
	@RequestMapping(value = "/loginCheck/myAlarm", produces = "text/plain;charset=UTF-8")
	public String myAlarm(HttpSession session, RedirectAttributes attr) {
		MemberDTO dto =(MemberDTO)session.getAttribute("login");
		String receiver = dto.getUserid(); // 해당 회원이 받은 알림
		List<Alarm> list = aService.getMyAlarm(receiver);
		attr.addFlashAttribute("myAlarmList", list);
		
		Map<String,String> typeMap = new HashMap<>();
		typeMap.put("c", "댓글");
		typeMap.put("o", "주문서");
		typeMap.put("rc", "대댓글");
		attr.addFlashAttribute("typeMap", typeMap);
		
		return "redirect:../myAlarm";
	}//내 알림 보기
	
}//mService