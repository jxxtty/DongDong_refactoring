package com.dto;

public class KakaoProfileDTO {
	private String id;
	private KakaoAccount kakao_account;

	public class KakaoAccount {
		private Profile profile;
		private String email;
		private boolean has_email;

		public class Profile {
			private String nickname;
			private String profile_image_url;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public KakaoAccount getKakao_account() {
		return kakao_account;
	}

	public void setKakao_account(KakaoAccount kakao_account) {
		this.kakao_account = kakao_account;
	}

	

	
}
