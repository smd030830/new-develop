package com.mjc813.login_session.biz;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SessionTestController {

	@GetMapping("/session/make")
	public String make(HttpServletResponse response
            , HttpSession session
			, @RequestParam("name") String name
			, @RequestParam("value") String value, Model model)
	{
		session.setAttribute("MJC_LOGIN", value);
		session.setMaxInactiveInterval(120);
		model.addAttribute("session_name", "MJC_LOGIN");
		model.addAttribute("session_value", value);
		return "info/info";
	}

	@GetMapping("/session/get")
	public String get(HttpServletRequest request
			, HttpSession session
			, Model model) {
			model.addAttribute("session_name", "MJC_LOGIN");
			model.addAttribute("session_value", session.getAttribute("MJC_LOGIN"));
		return "info/info";
	}

	@GetMapping("/session/delete")
	public String delete(HttpServletResponse response
			, HttpSession session
			, @RequestParam("name") String name, Model model)
	{
		session.invalidate();
		model.addAttribute("session_name", "");
		model.addAttribute("session_value", "");
		return "info/info";
	}
}
