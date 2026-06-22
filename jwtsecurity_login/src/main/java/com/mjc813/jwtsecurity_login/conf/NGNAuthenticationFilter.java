package com.mjc813.jwtsecurity_login.conf;

import com.mjc813.jwtsecurity_login.common.ResponseCode;
import com.mjc813.jwtsecurity_login.jwt.JwtExpireException;
import com.mjc813.jwtsecurity_login.jwt.JwtIllegalException;
import com.mjc813.jwtsecurity_login.jwt.JwtUtils;
import com.mjc813.jwtsecurity_login.models.member.MemberDto;
import com.mjc813.jwtsecurity_login.models.member.MemberService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class NGNAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private MemberService memberService;
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
                MemberDto find = this.memberService.findBySignId(signId);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        find, null, find.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (ExpiredJwtException e ) {
            log.error(e.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            SecurityContextHolder.clearContext();
            return;
        } catch (JwtIllegalException | JwtException e) {
            log.error(e.getMessage());
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }
}
