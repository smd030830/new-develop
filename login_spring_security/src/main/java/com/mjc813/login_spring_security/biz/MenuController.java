package com.mjc813.login_spring_security.biz;

import com.mjc813.login_spring_security.models.member.MemberDto;
import com.mjc813.login_spring_security.models.member.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MenuController {
	@Autowired
	private MemberService memberService;

	@GetMapping("/")
	public String index(HttpSession session, Model model) {
		Object oSignId = session.getAttribute("MJC_LOGIN");
		if ( oSignId != null ) {
			MemberDto signedMember = this.memberService.findBySignId(oSignId.toString());
			model.addAttribute("signedMember", signedMember);
		}
		return "home";
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
