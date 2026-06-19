package com.mjc813.jwtsecurity_login.biz;

import com.mjc813.jwtsecurity_login.models.member.IMember;
import com.mjc813.jwtsecurity_login.models.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
	@Autowired
	private MemberService memberService;

	@GetMapping("/")
	public String index(Model model) {
		Object obj = model.getAttribute("signedMember");
		if ( obj instanceof IMember signedMember) {
			model.addAttribute("signedMember", signedMember);
		}
		return "home";
	}

	@GetMapping("/signup")
	public String signupPage() {
		return "info/signup";
	}

	@GetMapping("/signin")
	public String signinPage() {
		return "info/signin";
	}
}
