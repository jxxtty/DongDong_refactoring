package com.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MemberDTO;
import com.mail.MailAuth;
import com.service.MemberService;
import com.service.SanctionService;

@Controller
public class LoginController {

	@Autowired
	MemberService service;
	@Autowired
	SanctionService saService;
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		// return "../"; //.xml에 설정 main.jsp
		return "redirect:/"; // .xml에 설정 main.jsp ../ 을 이용하여 /loginCheck 의 상위 주소로 이동
		// 하여 주소를 사용함
	}

	@RequestMapping(value = "/login")
	public String login(@RequestParam HashMap<String, String> map, Model model, HttpSession session) {
		MemberDTO dto = service.login(map);
		String userid = map.get("userid");
		//System.out.println(map);
		if (dto != null) {
			if(service.isAdmin(userid)){
				session.setAttribute("login", dto);
				session.setAttribute("admin", true);
				return "redirect:/admin";
			}
			int lockStatusN= service.selectLockStatus(userid);
			if(saService.isSanctioned(userid)) {
				model.addAttribute("mesg", "정지된 계정입니다. ("+saService.getSanctionReasonByUserid(userid)+")"); //줄바꿈
				model.addAttribute("mesg1", saService.getEndDateByUserid(userid)+"까지"); //줄바꿈
				return "loginForm";
			} else if(lockStatusN >= 1) {
				Date loginDate= service.selectLoginDate(userid);
				int lockCount = service.selectLockCount(userid);
				int time = lockCount*3;
				Calendar cal = Calendar.getInstance();
				cal.setTime(loginDate);
				cal.add(Calendar.MINUTE, time);
				//model.addAttribute("mesg", "일시적으로 제한된 계정입니다.("+time+")분"+ cal.getTime()); //줄바꿈
				model.addAttribute("mesg", "일시적으로 제한된 계정입니다. ("+time+")분"); //줄바꿈
				model.addAttribute("mesg1", cal.getTime()+" 까지"); //줄바꿈
				return "loginForm";
			}else {
				service.updateClearLoginFailCount(userid);
				service.updateClearLockCount(userid);
				service.loginDate(userid);
				session.setAttribute("login", dto);
				return "redirect:/";
			}

		} else {
			service.plusLoginFailCount(userid);
			service.updateLockStatus(userid);
			int failCount = service.selectFailCount(userid);
			model.addAttribute("mesg", "아이디 또는 비밀번호가 잘못되었습니다.("+failCount+"회 실패)");//줄바꿈이랑, 몇번남았는지 뜨게끔 수정
			model.addAttribute("mesg1","5회 이상 틀릴 시 계정이 일시 정지 됩니다.");
			return "loginForm";
		}

	}

	@RequestMapping(value = "/idSearch")
	public String idSerach(String username, String phone, String email, Model model) {
		MemberDTO dto = new MemberDTO();
		String email1 = email.split("@")[0];
		String email2 = email.split("@")[1];

		dto.setUsername(username);
		dto.setPhone(phone);
		dto.setEmail1(email1);
		dto.setEmail2(email2);
		String userid = service.idSearch(dto);

		if (userid == null) {
			model.addAttribute("mesg", "등록되지 않은  회원 정보");
			return "redirect:/idSearch";
		} else {
			// 이메일 전송
			String host = "smtp.naver.com"; // 호스트 네이버 메일
			String subject = ""; // 메일제목
			String from = "****@naver.com"; // 보내는 사람 메일 주소 //이부분 채워주셔야 합니다.
			String fromName = "DongDong"; // 송신자명
			String to = email; // 받는 사람 메일 주소
			String content = "";
			String mesg = "";

			if (userid != null) {
				content = "귀하의 아이디:" + userid;// 메일 내용
				subject = "동동에서 보내는 아이디 확인 메일입니다.";
				mesg = "아이디가 발송되었습니다.";
			}

			sendMail(model, host, subject, from, fromName, to, content, mesg);
		}

		return "redirect:/";
	}
	
	@RequestMapping(value = "/pwSearch")
	public String pwSerach(String userid, String username, String phone, String email, Model model) {
		MemberDTO dto = new MemberDTO();
		String email1 = email.split("@")[0];
		String email2 = email.split("@")[1];

		dto.setUsername(username);
		dto.setUserid(userid);
		dto.setPhone(phone);
		dto.setEmail1(email1);
		dto.setEmail2(email2);
		
		String passwd= service.pwSearch(dto);

		if (userid == null) {
			model.addAttribute("mesg", "등록되지 않은  회원 정보");
			return "redirect:/idSearch";
		} else {
			// 이메일 전송
			String host = "smtp.naver.com"; // 호스트 네이버 메일
			String subject = ""; // 메일제목
			String from = "****@naver.com"; // 보내는 사람 메일 주소 //이부분 채워주셔야 합니다.
			String fromName = "DongDong"; // 송신자명
			String to = email; // 받는 사람 메일 주소
			String content = "";
			String mesg = "";

			if (passwd != null) {
				content = "귀하의 비밀번호 : " + passwd;
				subject = "동동에서 보내는 비밀번호 확인 메일입니다.";
				mesg = "비밀번호가 발송되었습니다.";
			}

			sendMail(model, host, subject, from, fromName, to, content, mesg);
		}
		
		return "redirect:/";
	}

	private void sendMail(Model model, String host, String subject, String from, String fromName, String to,
			String content, String mesg) {
		try {
			// 프로퍼티 값 인스턴스 생성과 기본세션(SMTP 서버 호스트 지정)
			Properties props = new Properties();
			// 네이버 SMTP 사용시
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", host);

			props.put("mail.smtp.port", "465"); // 보내는 메일 포트 설정
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");

			MailAuth auth = new MailAuth(); // 인증받을 자료 입력
			Session mailSession = Session.getDefaultInstance(props, auth); // 인증받기

			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName, "UTF-8", "B"))); // 보내는 사람 설정
			InternetAddress[] address = { new InternetAddress(to) };

			msg.setRecipients(Message.RecipientType.TO, address); // 받는 사람설정

			msg.setSubject(subject); // 제목설정
			msg.setSentDate(new java.util.Date()); // 보내는 날짜 설정
			msg.setContent(content, "text/html; charset=UTF-8"); // 내용 설정(MIME 지정-HTML 형식)

			Transport.send(msg); // 메일 보내기

			model.addAttribute("mesg", mesg);

		} catch (MessagingException ex) {
			System.out.println("mail send error : " + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
			e.printStackTrace();
		}
	}

	
}
