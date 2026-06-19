package com.mjc813.jwtsecurity_login.models.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	@GetMapping("/member")
	public String getHomePage() {
		return "member/index";
	}
}
