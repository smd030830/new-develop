package com.mjc813.jwtsecurity_login.oauth2;

import com.mjc813.jwtsecurity_login.biz.AuthService;
import com.mjc813.jwtsecurity_login.models.auth.AuthTokenDto;
import com.mjc813.jwtsecurity_login.models.member.IMember;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Slf4j
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	@Autowired
	private AuthService authService;
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request
			, HttpServletResponse response
			, Authentication authentication) throws IOException, ServletException {
		IMember member = (IMember)authentication.getPrincipal();
		AuthTokenDto authTokenDto = this.authService.signIn(member.getSignId());
		String result = this.objectMapper.writeValueAsString(authTokenDto);

		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		response.getWriter().write(result);
	}
}
