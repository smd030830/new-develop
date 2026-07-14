package com.mjc813.jwtsecurity_login.conf;

import com.mjc813.jwtsecurity_login.jwt.JwtIllegalException;
import com.mjc813.jwtsecurity_login.jwt.JwtUtils;
import com.mjc813.jwtsecurity_login.models.redismember.RedisMemberDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class CWCAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtils jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request
			, HttpServletResponse response
			, FilterChain filterChain) throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		try {
			String jwtAccessToken = this.jwtUtils.resolveJwtTokenFromBearerToken(authHeader);
			if ( jwtAccessToken != null ) {
				String signId = this.jwtUtils.getSignId(jwtAccessToken);
				RedisMemberDto findDto = this.jwtUtils.findRedis(signId);
//					MemberDto findDto = this.memberService.findBySignId(signId);   // DB 에서 조회하지 않고 redis  에서 조회
				if ( findDto != null ) {
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
							findDto, null, findDto.getAuthorities()
					);
					SecurityContextHolder.getContext().setAuthentication(auth);
				} else {  // 사인아웃 했다면
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
					SecurityContextHolder.clearContext();
					filterChain.doFilter(request, response);
					return;
				}
			}
		} catch (ExpiredJwtException e ) {
			log.error(e.getMessage());
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			SecurityContextHolder.clearContext();
			filterChain.doFilter(request, response);
			return;
		} catch (JwtIllegalException | JwtException e) {
			log.error(e.getMessage());
			SecurityContextHolder.clearContext();
		}
		filterChain.doFilter(request, response);
	}
}
