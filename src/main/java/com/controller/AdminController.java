package com.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dto.CommentsDTO;
import com.dto.ComplaintDTO;
import com.dto.MemberDTO;
import com.dto.PostDTO;
import com.dto.SanctionDTO;
import com.service.CommentsService;
import com.service.ComplaintService;
import com.service.MemberService;
import com.service.PostService;
import com.service.SanctionService;
import com.service.StatisticService;

@Controller
public class AdminController {
	@Autowired
	ComplaintService coService;
	@Autowired
	MemberService mService;
	@Autowired
	PostService pService;
	@Autowired
	CommentsService cService;
	@Autowired
	SanctionService saService;
	@Autowired
	StatisticService stService;
	
	@RequestMapping(value = "/admin/logout")
	public String adminLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value = "/admin")
	public ModelAndView adminPage(Model model) {
		ModelAndView mav = new ModelAndView();
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH");
		String currentTime = sdf.format(cal.getTime());
		Map<String, List> purchaseChartData = stService.getPurchaseChartData(currentTime, "H", 7);
		Map<String, List> postChartData = stService.getPostWriteChartData(currentTime, "H", 7);
		Map<String, List> accountChartData = stService.getAccountCreateChartData(currentTime, "H", 7);
		Map<String, List> complaintChartData = stService.getComplaintChartData(currentTime, "H", 7);
		mav.addObject("mainChartLabel",purchaseChartData.get("purchaseChartLabel"));
		mav.addObject("purchaseChartData",purchaseChartData.get("purchaseChartData"));
		mav.addObject("postChartData",postChartData.get("postWriteChartData"));
		mav.addObject("accountChartData",accountChartData.get("accountCreateChartData"));
		mav.addObject("complaintChartData",complaintChartData.get("complaintChartData"));

		Map<String, List> txChartData = stService.getTXChartData(currentTime, "H", 7);
		mav.addObject("txChartLabel",txChartData.get("txChartLabel"));
		mav.addObject("txChartData",txChartData.get("txChartData"));
		
		Map<String, List> dailyPostWriteMap = stService.getPostWriteChartData(currentTime, "D", 1);
		List<Integer> temp = dailyPostWriteMap.get("postWriteChartData");
		int dailyPostWrite = temp.get(0);
		
		Map<String, List> dailyPurchaseMap = stService.getPurchaseChartData(currentTime, "D", 1);
		temp = dailyPurchaseMap.get("purchaseChartData");
		int dailyPurchase = temp.get(0);
		
		Map<String, List> dailyAccountMap = stService.getAccountCreateChartData(currentTime, "D", 1);
		temp = dailyAccountMap.get("accountCreateChartData");
		int dailyAccount = temp.get(0);
		
		Map<String, List> dailyComplaintMap = stService.getComplaintChartData(currentTime, "D", 1);
		temp = dailyComplaintMap.get("complaintChartData");
		int dailyComplaint = temp.get(0);

		mav.addObject("dailyPostWrite",dailyPostWrite);
		mav.addObject("dailyPurchase",dailyPurchase);
		mav.addObject("dailyAccount",dailyAccount);
		mav.addObject("dailyComplaint",dailyComplaint);
		
		dailyPostWriteMap = stService.getPostWriteChartData(currentTime, "H", 1);
		temp = dailyPostWriteMap.get("postWriteChartData");
		dailyPostWrite = temp.get(0);
		
		dailyPurchaseMap = stService.getPurchaseChartData(currentTime, "H", 1);
		temp = dailyPurchaseMap.get("purchaseChartData");
		dailyPurchase = temp.get(0);
		
		dailyAccountMap = stService.getAccountCreateChartData(currentTime, "H", 1);
		temp = dailyAccountMap.get("accountCreateChartData");
		dailyAccount = temp.get(0);
		
		dailyComplaintMap = stService.getComplaintChartData(currentTime, "H", 1);
		temp = dailyComplaintMap.get("complaintChartData");
		dailyComplaint = temp.get(0);
		 
		List<Integer> txPieChartData = new ArrayList<Integer>();
		txPieChartData.add(dailyPostWrite);
		txPieChartData.add(dailyPurchase);
		txPieChartData.add(dailyAccount);
		txPieChartData.add(dailyComplaint);
		mav.addObject("txPieChartData",txPieChartData);
		
		mav.setViewName("admin/adminMain");
		return mav;
	}
	
	@RequestMapping(value = "/admin/txChartChange")
	public @ResponseBody Map<String, List> txChartChange(HttpSession session, @RequestParam Map<String, String> map) {
		String start = map.get("startDate");
		String end = map.get("endDate");
		String selectType = map.get("selectType");
		Date temp = null;
		SimpleDateFormat inputDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat changeDataFormat = new SimpleDateFormat("yyyy-MM-dd_HH");
		Calendar startCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		Calendar curCal = Calendar.getInstance();
		long longLength = 0;
		int length = 0;
		try {
			temp = inputDateFormat.parse(start);
			startCal.setTime(temp);
			temp = inputDateFormat.parse(end);
			endCal.setTime(temp);
			String currentTime = changeDataFormat.format(curCal.getTime());
			
			if(selectType.equals("H")) {
				end = changeDataFormat.format(endCal.getTime());
				if(end.substring(0,10).equals(currentTime.substring(0,10))) {
					longLength = (curCal.getTimeInMillis()-startCal.getTimeInMillis())/60/60/1000;
					length = (int)longLength+1;
					end = changeDataFormat.format(curCal.getTime());
				} else{
					longLength = (endCal.getTimeInMillis()-startCal.getTimeInMillis())/60/60/1000;
					length = (int)longLength+24;
					end = end.substring(0,10).concat("_23");
				}
			} else if(selectType.equals("D")) {
				longLength = (endCal.getTimeInMillis()-startCal.getTimeInMillis())/60/60/24/1000;
				length = (int)longLength+1;
				end = changeDataFormat.format(endCal.getTime());
				end = end.substring(0,10);
			} else if(selectType.equals("M")) {
				length = endCal.get(Calendar.MONTH)-startCal.get(Calendar.MONTH)+1;
				end = changeDataFormat.format(endCal.getTime());
				end = end.substring(0,7);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		Map<String, List> txChartData = stService.getTXChartData(end, selectType, length);
		
		return txChartData; 
	}
	
	@RequestMapping(value = "/admin/complaintMember")
	public ModelAndView complaintMemberPage() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", coService.memberComplaintList());
		mav.setViewName("admin/complaintMember");
		return mav;
	}
	
	@RequestMapping(value = "/admin/complaintPost")
	public ModelAndView complaintPostPage() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", coService.postComplaintList());
		mav.setViewName("admin/complaintPost");
		return mav;
	}
	
	@RequestMapping(value = "/admin/complaintComment")
	public ModelAndView complaintCommentPage() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", coService.commentComplaintList());
		mav.setViewName("admin/complaintComment");
		return mav;
	}
	@Transactional
	@RequestMapping(value = "/admin/complaintEnd")
	public ModelAndView complaintEnd(HttpSession session,  @RequestParam Map<String, String> map) {
		int coNum = Integer.parseInt(map.get("coNum"));
		int saType = Integer.parseInt(map.get("saType"));
		int saDate = Integer.parseInt(map.get("saDate"));
		
		
		ComplaintDTO coDTO = coService.getComplaint(coNum);
		SanctionDTO saDTO = new SanctionDTO();
		saDTO.setCoNum(coNum);
		saDTO.setSaType(saType);
		saDTO.setSaDate(saDate);
		
		ModelAndView mav = new ModelAndView();
		
		switch (coDTO.getCoType()) {
		case 1:
			saDTO.setUserid(coDTO.getCoTarget());
			mav.setViewName("redirect:/admin/complaintMember");
			break;
		case 2:
			saDTO.setUserid(coDTO.getTargetUserid());
			mav.setViewName("redirect:/admin/complaintPost");
			break;
		case 3:
			saDTO.setUserid(coDTO.getTargetUserid());
			mav.setViewName("redirect:/admin/complaintComment");
			break;
		default:
			mav.setViewName("redirect:/admin");
			break;
		}

		if(saService.isSanctioned(saDTO.getUserid())==true) {
			saDTO.setEndDate(saService.getEndDateByUserid(saDTO.getUserid()));
		}
		if(saDate==0) {
			int complaintResult = coService.complaintEnd(coNum);
			return mav;
		}
		int complaintResult = coService.complaintEnd(coNum);
		int sanctionResult = saService.insertSanction(saDTO);
		
		
		return mav;
	}
	
	
	@RequestMapping(value = "/admin/complaintHandlingDetail")
	public ModelAndView complaintHandlingDetail(HttpSession session, @RequestParam("coNum") int coNum) {
		ModelAndView mav = new ModelAndView();
		MemberDTO mDTO = null;
		PostDTO pDTO = null;
		CommentsDTO cDTO = null;
		ComplaintDTO coDTO = coService.getComplaint(coNum);
		
		switch (coDTO.getCoType()) {
		case 1:
			mDTO = mService.mypage(coDTO.getTargetUserid());
			if(mDTO==null) {
				mav.addObject("isDeleted", true);
			} else {
				mav.addObject("isDeleted", false);
			}
			mDTO = new MemberDTO();
			
			mDTO.setUserid(coDTO.getTargetUserid());
			mDTO.setUserimage(coDTO.getTargetImage());
			mDTO.setNickName(coDTO.getTargetUserid());
			mDTO.setUserid(coDTO.getTargetUserid());
			mav.setViewName("admin/complaintMemberDetail");
			break;
		case 2:
			pDTO = pService.getPostByPNum(Integer.parseInt(coDTO.getCoTarget()));
			if(pDTO==null) {
				mav.addObject("isDeleted", true);
			} else {
				mav.addObject("isDeleted", false);
			}
			pDTO = new PostDTO();
			pDTO.setUserid(coDTO.getTargetUserid());
			pDTO.setpTitle(coDTO.getTargetTitle());
			pDTO.setpImage(coDTO.getTargetImage());
			pDTO.setpContent(coDTO.getTargetContent());
			mav.addObject("pDTO", pDTO);
			// 다중파일 대비 이미지파싱
    		String[] originalImages = pDTO.getpImage().split(" ");
    		mav.addObject("imageDetail", originalImages);
    		
    		// 다중파일인 경우 postDetail에서 추가부분이 있어 구분자로 넣어줌
    		if(originalImages.length == 1) { 
    			mav.addObject("isMultiFile","N");
    		} else {
    			mav.addObject("isMultiFile","Y");
    		}
			mav.setViewName("admin/complaintPostDetail");
			break;
		case 3:
			cDTO = cService.getCommentByCNum(Integer.parseInt(coDTO.getCoTarget()));
			if(cDTO==null) {
				mav.addObject("isDeleted", true);
			} else {
				mav.addObject("isDeleted", false);
			}
			cDTO = new CommentsDTO();
			cDTO.setUserid(coDTO.getTargetUserid());
			cDTO.setUserimage(coDTO.getTargetImage());
			cDTO.setcContent(coDTO.getTargetContent());
			cDTO.setNickName(coDTO.getTargetTitle());
			mav.addObject("cDTO", cDTO);
			mav.setViewName("admin/complaintCommentDetail");
			break;
		default:
			mav.setViewName("admin");
			break;
		}
		mDTO = mService.mypage(coDTO.getTargetUserid());
		mav.addObject("mDTO", mDTO);
		mav.addObject("coDTO", coDTO);
		mav.addObject("sanctionList", saService.sanctionList((String)mDTO.getUserid()));
		mav.addObject("isSanctioned", saService.isSanctioned((String)mDTO.getUserid()));
		mav.addObject("endSanctionDate", saService.getEndDateByUserid((String)mDTO.getUserid()));
		mav.addObject("isAlreadyCompleted", coService.isAlreadyCompleted(coDTO)==0?false:true);
		return mav;
	}
	
	@RequestMapping(value = "/admin/targetDelete")
	public @ResponseBody void targetDelete(HttpSession session, @RequestParam Map<String, String> map) {
		ComplaintDTO coDTO = coService.getComplaint(Integer.parseInt(map.get("target")));
		if(coDTO.getCoType()==1) {
			
		} else if(coDTO.getCoType()==2) {
			pService.deletePostByPNum(Integer.parseInt(coDTO.getCoTarget()));
		} else if(coDTO.getCoType()==3) {
			cService.deleteCommentByCNum(Integer.parseInt(coDTO.getCoTarget()));
		}
	}
}
