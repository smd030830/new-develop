package com.mjc813.session_login.biz;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SessionTestController {

	@GetMapping("/session/make")
	public String make(HttpSession session
			, @RequestParam("name") String name
			, @RequestParam("value") String value, Model model)
	{
		session.setAttribute(name, value);
		session.setMaxInactiveInterval(3600);
		model.addAttribute("name", name);
		model.addAttribute("value", session.getAttribute(name).toString());
		return "info/info";
	}

	//확실하지않으니까 패스
//	@GetMapping("/session/get")
//	public String get(HttpServletRequest request, Model model) {
//		Cookie[] cks = request.getCookies();
//		if (cks != null && cks.length > 0 ) {
//			model.addAttribute("cookie_time", cks[0].getMaxAge());
//			model.addAttribute("cookie_name", cks[0].getName());
//			model.addAttribute("cookie_value", cks[0].getValue());
//		}
//		return "info/info";
//	}

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
