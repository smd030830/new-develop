package com.mjc813.login_cookie.biz;

import com.mjc813.login_cookie.models.member.MemberDto;
import com.mjc813.login_cookie.models.member.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class MenuController {
	@Autowired
	private MemberService memberService;

	@GetMapping("/")
	public String index(HttpServletRequest request, Model model) {
		Cookie cookie = this.getCookie(request, "MJC_LOGIN");
		if ( cookie != null ) {
			String signId = cookie.getValue();
			MemberDto signedMember = this.memberService.findBySignId(signId);
			model.addAttribute("signedMember", signedMember);
		}
		return "home";
	}

	private Cookie getCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		Cookie result = null;
		try {
			result = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(cookieName)).findFirst().orElse(null);
		} catch (Exception ex) {
			result = null;
		}
		return result;
	}

	@GetMapping("/signup")
	public String signupPage() {
		return "info/signup";
	}

	@GetMapping("/checkemail")
	public String checkEmail(@RequestParam("id") String signId, Model model) {
		model.addAttribute("signId", signId);
		return "info/checkemail";
	}

	@GetMapping("/signin")
	public String signinPage() {
		return "info/signin";
	}
}
