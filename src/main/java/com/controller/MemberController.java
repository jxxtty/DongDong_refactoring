package com.controller;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.MemberDTO;
import com.mail.MailAuth;
import com.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService mService;
	private Logger complaintLogger = LoggerFactory.getLogger("statistics");
	
	//랜덤값 생성 
	//public class GenerateCertPassword{
	    private int pwdLength = 8;
	    private final char[] passwordTable =  { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
	                                            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
	                                            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
	                                            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
	                                            'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '^', '&', '*',
	                                            '(', ')', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

	    public String excuteGenerate() {
	        Random random = new Random(System.currentTimeMillis());
	        int tablelength = passwordTable.length;
	        StringBuffer buf = new StringBuffer();
	        
	        for(int i = 0; i < pwdLength; i++) {//pwdLength=8 자리 
	            buf.append(passwordTable[random.nextInt(tablelength)]);
	        }
	        
	        return buf.toString();
	    }

	    public int getPwdLength() {
	        return pwdLength;
	    }

	    public void setPwdLength(int pwdLength) {
	        this.pwdLength = pwdLength;
	    };
	//};
	//랜덤값 생성하기 
	
	//이메일 인증하기 
	@RequestMapping(value="/emailAuthC")
	public  String emailAuth (String email1, String email2,Model model ){
		String Authcode= excuteGenerate();
		
		String mailTo =(email1 + "@" + email2);
		//System.out.println(mailTo);
		String host = "smtp.naver.com"; // 호스트 네이버 메일
		String subject = "동동 이메일 인증"; // 메일제목
		String from = "qkrtmdtn3520@naver.com"; // 보내는 사람 메일 주소 //이부분 채워주셔야 합니다.
		String fromName = "DongDong"; // 송신자명
		String to = mailTo; // 받는 사람 메일 주소
		String content =  " 인증 번호 : '"+Authcode +"' 입니다. ";
		String mesg = "";
		
		sendMail(model, host, subject, from, fromName, to, content, mesg);

		model.addAttribute("email1", email1);
		model.addAttribute("email2", email2);
		model.addAttribute("Authcode", Authcode);//인증값 넘겨줌
		
	return "CheckmailAuth";
		
	}//이메일 인증 (이메일 받아서 넘기기-> sendemailAuth)
	
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
	
	@RequestMapping(value = "/nicknameDuplicateCheck", produces = "text/plain;charset=UTF-8") // 한글처리
	public @ResponseBody String nicknameDuplicateCheck(@RequestParam("nickName") String nickName) {
		// 비동기 방식 요청에 대한 mesg를 문자열로 전송
		MemberDTO dto = mService.mypage(nickName);
		String mesg = "닉네임 사용가능";
		if (dto != null) {// db에 같은 id가 존재
			mesg = "닉네임 중복";
		}
		return mesg; // view페이지가 아닌 mesg문자열 전송
	}

	// 아이디 중복검사
	@RequestMapping(value = "/idDuplicateCheck", produces = "text/plain;charset=UTF-8") // 한글처리
	public @ResponseBody String idDuplicatedCheck(@RequestParam("id") String userid) {
		// 비동기 방식 요청에 대한 mesg를 문자열로 전송
		MemberDTO dto = mService.mypage(userid);
		String mesg = "아이디 사용가능";
		if (dto != null) {// db에 같은 id가 존재
			mesg = "아이디 중복";
		}
		return mesg; // view페이지가 아닌 mesg문자열 전송
	}

	/*
	 * //회원가입 예비용 
	 * 
	 * @RequestMapping(value = "/memberAdd") // 회원가입 기능 public String
	 * memberAdd(MemberDTO m,Model model) { mService.memberAdd(m); // m 값이 1인경우 회원가입
	 * 가능하면 좋은데 model.addAttribute("success", "회원가입성공"); return "main"; }
	 */

	//회원가입 
	@RequestMapping(value = "/memberAdd") // 회원가입 기능
	public String memberAdd(MemberDTO m,String email1,String email2,String nickName,HttpSession session,Model model) {
		//회원가입시 가입 이메일 보내기 
		  String mailTo =(email1 + "@" + email2);
		  String host = "smtp.naver.com"; // 호스트 네이버 메일
		  String subject = "동동 회원님 가입을  환영합니다 !! "; // 메일제목
		  String from ="qkrtmdtn3520@naver.com"; // 보내는 사람 메일 주소 //이부분 채워주셔야 합니다. 
		  String fromName ="Dong Dong"; // 송신자명 
		  String to = mailTo; // 받는 사람 메일 주소 
		  String content =nickName+"님 동동의 가족이 된 것을 환영합니다 ~! "; 
		  String mesg = "";
		  
		  sendMail(model, host, subject, from, fromName, to, content, mesg);
		 
		mService.memberAdd(m);
		complaintLogger.info("MemberController AccountCreated- "+m);
		//model.addAttribute("success", "회원가입성공");
		session.setAttribute("success",  "회원가입성공");
		System.out.println(m);
		return "redirect:/";
	}
}