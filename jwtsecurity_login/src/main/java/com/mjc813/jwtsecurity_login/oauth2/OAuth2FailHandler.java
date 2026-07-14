package com.mjc813.jwtsecurity_login.oauth2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class OAuth2FailHandler extends SimpleUrlAuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request
			, HttpServletResponse response
			, AuthenticationException exception) throws IOException, ServletException {
		log.error("OAuth2 failed : {}", exception.getMessage());
		getRedirectStrategy().sendRedirect(request, response, "/error?msg=" + exception.getMessage());
	}
}
