package com.mjc813.login_spbsec_cookie.config;

import com.mjc813.login_spbsec_cookie.models.member.IMember;
import com.mjc813.login_spbsec_cookie.models.member.MemberService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
public class FindMemberFilter extends OncePerRequestFilter {
	@Autowired
	private MemberService memberService;

	@Override
	public void doFilterInternal(HttpServletRequest request
			, HttpServletResponse response
			, FilterChain filterChain) throws ServletException, IOException {
		try {
			if ( request.getMethod().equals("OPTIONS") ) {
				filterChain.doFilter(request, response);
				return;
			}
			Cookie cookie = this.getCookie(request, "MJC_LOGIN");
			if ( cookie == null ) {
				filterChain.doFilter(request, response);
				return;
			}
			String signId = (String)cookie.getValue();
			if ( signId != null ) {
				IMember signedMember = this.memberService.findBySignId(signId);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						signedMember, null, signedMember.getAuthorities()
				);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception ex) {
			log.error("user authentication error : " + ex.toString());
		}
		filterChain.doFilter(request, response);
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
}
