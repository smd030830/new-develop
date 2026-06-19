package com.mjc813.jwtsecurity_login.biz;

import com.mjc813.jwtsecurity_login.common.ComResponseDto;
import com.mjc813.jwtsecurity_login.common.ResponseCode;
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

	@PostMapping("/signup")
	public ResponseEntity<ComResponseDto<IMember>> signUp(@RequestBody SignUpDto signUpDto) {
		MemberDto memberDto = (MemberDto)new MemberDto().clone(signUpDto, true);
		MemberDto inserted = this.memberService.insert(memberDto, false);
		return ResponseEntity.status(201).body(
				ComResponseDto.make(ResponseCode.SUCCESS, inserted)
		);
	}

	@PostMapping("/signin")
	public ResponseEntity<ComResponseDto<Boolean>> signin(@RequestBody SignInDto signInDto
		, HttpSession session) {
		Authentication auth = this.authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(signInDto.getSignId(), signInDto.getPassword())
		);
		SecurityContextHolder.getContext().setAuthentication(auth);
		session.setAttribute("MJC_LOGIN", signInDto.getSignId());
		session.setMaxInactiveInterval(1200);
		return ResponseEntity.status(200).body(
				ComResponseDto.make(ResponseCode.SUCCESS, true)
		);
	}

	@GetMapping("/signout")
	public ResponseEntity<ComResponseDto<Boolean>> signout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.status(200).body(
				ComResponseDto.make(ResponseCode.SUCCESS, true)
		);
	}
}
