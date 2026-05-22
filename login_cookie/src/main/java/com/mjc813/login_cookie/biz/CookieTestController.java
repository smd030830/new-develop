package com.mjc813.login_cookie.biz;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CookieTestController {

	@GetMapping("/cookie/make")
	public String make(HttpServletResponse response
			, @RequestParam("name") String name
			, @RequestParam("value") String value, Model model)
	{
		Cookie ck = new Cookie(name, value);
		ck.setPath("/cookie");
		ck.setHttpOnly(true);
		ck.setMaxAge(120);
		response.addCookie(ck);
		model.addAttribute("cookie_time", ck.getMaxAge());
		model.addAttribute("cookie_name", ck.getName());
		model.addAttribute("cookie_value", ck.getValue());
		return "info/info";
	}

	@GetMapping("/cookie/get")
	public String get(HttpServletRequest request, Model model) {
		Cookie[] cks = request.getCookies();
		if (cks != null && cks.length > 0 ) {
			model.addAttribute("cookie_time", cks[0].getMaxAge());
			model.addAttribute("cookie_name", cks[0].getName());
			model.addAttribute("cookie_value", cks[0].getValue());
		}
		return "info/info";
	}

	@GetMapping("/cookie/delete")
	public String delete(HttpServletResponse response
			, @RequestParam("name") String name, Model model)
	{
		Cookie ck = new Cookie(name, "");
		ck.setPath("/cookie");
		ck.setHttpOnly(true);
		ck.setMaxAge(-1);
		response.addCookie(ck);
		model.addAttribute("cookie_time", "");
		model.addAttribute("cookie_name", "");
		model.addAttribute("cookie_value", "");
		return "info/info";
	}
}
