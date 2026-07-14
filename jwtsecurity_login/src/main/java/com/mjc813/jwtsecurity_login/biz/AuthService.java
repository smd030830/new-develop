package com.mjc813.jwtsecurity_login.biz;

import com.mjc813.jwtsecurity_login.common.ComResponseDto;
import com.mjc813.jwtsecurity_login.common.LoginException;
import com.mjc813.jwtsecurity_login.common.Mjc813Exception;
import com.mjc813.jwtsecurity_login.common.ResponseCode;
import com.mjc813.jwtsecurity_login.jwt.JwtExpireException;
import com.mjc813.jwtsecurity_login.jwt.JwtUtils;
import com.mjc813.jwtsecurity_login.models.auth.AuthTokenDto;
import com.mjc813.jwtsecurity_login.models.auth.RefreshAuthTokenDto;
import com.mjc813.jwtsecurity_login.models.auth.SignInDto;
import com.mjc813.jwtsecurity_login.models.member.*;
import com.mjc813.jwtsecurity_login.models.redismember.RedisMemberDto;
import com.mjc813.jwtsecurity_login.models.role.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService extends MemberService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtUtils jwtUtils;

	public AuthTokenDto signIn(String signId) {
		String accessToken = this.jwtUtils.generateAccessToken(signId);
		String refreshToken = this.jwtUtils.generateRefreshToken(signId);

		MemberDto signMember = super.findBySignId(signId);

		AuthTokenDto authTokenDto = new AuthTokenDto(accessToken, refreshToken);

		// 정상적으로 signin 하면 사용자 정보를 redis 저장한다.
		this.jwtUtils.saveRedis(signMember, authTokenDto);

		return authTokenDto;
	}

	public ResponseEntity<ComResponseDto<AuthTokenDto>> refreshToken(RefreshAuthTokenDto tokenDto) {
		String signId = tokenDto.getSignId();
		RedisMemberDto findDto = this.jwtUtils.findRedis(signId);
		if ( findDto == null ) {
			// 사인아웃 했던 유저는 refresh 토큰을 받아가면 안된다.
			return ResponseEntity.status(500).body(
					ComResponseDto.make(ResponseCode.AUTHENTICATION_ERROR, null)
			);
		}
		String accessToken = tokenDto.getAccessToken();
		try {
			this.jwtUtils.validateToken(accessToken);
		} catch (JwtExpireException e) {
			// 이 토큰은 시간 종료되었으므로 재발급 가능하다.
			String newAccessToken = this.jwtUtils.generateAccessToken(signId);
			String newRefreshToken = this.jwtUtils.generateRefreshToken(signId);
			AuthTokenDto authTokenDto = new AuthTokenDto(newAccessToken, newRefreshToken);
			// 정상적으로 리프레시코튼이 발급 되면 사용자 정보를 redis 수정 한다.
			this.jwtUtils.updateRedis(findDto, authTokenDto);
			return ResponseEntity.status(200).body(
					ComResponseDto.make(ResponseCode.SUCCESS, authTokenDto)
			);
		}
		return ResponseEntity.status(500).body(
				ComResponseDto.make(ResponseCode.TOKEN_NOT_EXPIRED_ERROR, null)
		);
	}

	public void signOut(String signId) {
		this.jwtUtils.removeRedis(signId);
	}

	public MemberDto findBySignId(String signId) {
		return super.findBySignId(signId);
	}
}
