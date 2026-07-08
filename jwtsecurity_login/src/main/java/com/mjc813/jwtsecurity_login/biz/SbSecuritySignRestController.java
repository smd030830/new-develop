package com.mjc813.jwtsecurity_login.biz;

import com.mjc813.jwtsecurity_login.common.ComResponseDto;
import com.mjc813.jwtsecurity_login.common.ResponseCode;
import com.mjc813.jwtsecurity_login.jwt.JwtExpireException;
import com.mjc813.jwtsecurity_login.jwt.JwtUtils;
import com.mjc813.jwtsecurity_login.models.auth.AuthTokenDto;
import com.mjc813.jwtsecurity_login.models.auth.RefreshAuthTokenDto;
import com.mjc813.jwtsecurity_login.models.auth.SignInDto;
import com.mjc813.jwtsecurity_login.models.auth.SignUpDto;
import com.mjc813.jwtsecurity_login.models.member.IMember;
import com.mjc813.jwtsecurity_login.models.member.MemberDto;
import com.mjc813.jwtsecurity_login.models.member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class SbSecuritySignRestController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private AuthService authService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/signup")
	public ResponseEntity<ComResponseDto<IMember>> signUp(@RequestBody SignUpDto signUpDto) {
		MemberDto memberDto = (MemberDto)new MemberDto().clone(signUpDto, true);
		MemberDto inserted = this.memberService.insert(memberDto, false);
		return ResponseEntity.status(201).body(
				ComResponseDto.make(ResponseCode.SUCCESS, inserted)
		);
	}

	@PostMapping("/signin")
	public ResponseEntity<ComResponseDto<AuthTokenDto>> signin(@RequestBody SignInDto signInDto
		, HttpSession session) {
		Authentication auth = this.authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(signInDto.getSignId(), signInDto.getPassword())
		);
		SecurityContextHolder.getContext().setAuthentication(auth);
//		session.setAttribute("MJC_LOGIN", signInDto.getSignId());
//		session.setMaxInactiveInterval(1200);

		String accessToken = this.jwtUtils.generateAccessToken(signInDto.getSignId());
		String refreshToken = this.jwtUtils.generateRefreshToken(signInDto.getSignId());

//		MemberDto signMember = this.memberService.findBySignId(signInDto.getSignId());
//		String accessToken = jwtUtils.generateToken(signMember);

		AuthTokenDto authTokenDto = new AuthTokenDto(accessToken, refreshToken);
		return ResponseEntity.status(200).body(
				ComResponseDto.make(ResponseCode.SUCCESS, authTokenDto)
		);
	}

	@GetMapping("/signout")
	public ResponseEntity<ComResponseDto<Boolean>> signout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.status(200).body(
				ComResponseDto.make(ResponseCode.SUCCESS, true)
		);
	}

	@PostMapping("/refresh")
	public ResponseEntity<ComResponseDto<AuthTokenDto>> refresh(@RequestBody RefreshAuthTokenDto authToken) {
		String signId = authToken.getSignId();
		String accessToken = authToken.getAccessToken();
		try {
			this.jwtUtils.validateToken(accessToken);
		} catch (JwtExpireException e) {
			// 이 토큰은 시간 종료되었으므로 재발급 가능하다.
			String newAccessToken = this.jwtUtils.generateAccessToken(signId);
			String newRefreshToken = this.jwtUtils.generateRefreshToken(signId);
			AuthTokenDto authTokenDto = new AuthTokenDto(newAccessToken, newRefreshToken);
			return ResponseEntity.status(200).body(
					ComResponseDto.make(ResponseCode.SUCCESS, authTokenDto)
			);
		}
		return ResponseEntity.status(500).body(
				ComResponseDto.make(ResponseCode.TOKEN_NOT_EXPIRED_ERROR, null)
		);
	}
}
