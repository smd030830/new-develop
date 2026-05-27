package com.mjc813.login_cookie.biz;

import com.mjc813.login_cookie.models.member.MemberDto;
import com.mjc813.login_cookie.models.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CookieControllerAdvice {
	@Autowired
	private MemberService memberService;

	@ModelAttribute
	public void addSignedUserFromCookie(Model model
		, @CookieValue(name = "MJC_LOGIN", required = false) String signId ) {
		// 해당 메소드는 클라이언트의 요청을 가로채서 쿠키의 키가 "MJC_LOGIN" 인 값을 signId 변수에 담는다.
		if ( signId != null ) {
			// signId 가 존재한다면 member 테이블을 조회 한다.
			MemberDto signedMember = this.memberService.findBySignId(signId);
			model.addAttribute("signedMember", signedMember);
			// 조회한 사용자 데이터를 model 에 추가한다. 추가할때 키 를 "signedMember" 를 줬다. 값은 MemberDto 이다.
		}
	}
}
