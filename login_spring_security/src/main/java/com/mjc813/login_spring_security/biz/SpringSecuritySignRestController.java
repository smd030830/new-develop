package com.mjc813.login_spring_security.biz;

import com.mjc813.login_spring_security.common.ComResponseDto;
import com.mjc813.login_spring_security.common.LoginException;
import com.mjc813.login_spring_security.common.ResponseCode;
import com.mjc813.login_spring_security.models.auth.SignInDto;
import com.mjc813.login_spring_security.models.auth.SignUpDto;
import com.mjc813.login_spring_security.models.auth.ValidEmailDto;
import com.mjc813.login_spring_security.models.member.IMember;
import com.mjc813.login_spring_security.models.member.MemberDto;
import com.mjc813.login_spring_security.models.member.MemberService;
import jakarta.mail.MessagingException;
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
public class SpringSecuritySignRestController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private MailService mailService;
	@Autowired
	private AuthService authService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/signup")
	public ResponseEntity<ComResponseDto<IMember>> signUp(@RequestBody SignUpDto signUpDto) {
		MemberDto memberDto = (MemberDto)new MemberDto().clone(signUpDto, true);
		MemberDto inserted = this.memberService.insert(memberDto, false);
		try {
			this.mailService.sendHtmlEmail(inserted);
		} catch (MessagingException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(500).body(
					ComResponseDto.make(ResponseCode.SERVER_ERROR, inserted)
			);
		}
		return ResponseEntity.status(201).body(
				ComResponseDto.make(ResponseCode.SUCCESS, signUpDto)
		);
	}

	@GetMapping("/emailtest/{id}")
	public ResponseEntity<ComResponseDto<MemberDto>> emailTest(@PathVariable String id) {
		MemberDto find = this.memberService.findById(id);
		try {
			this.mailService.sendHtmlEmail(find);
		} catch (MessagingException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(500).body(
					ComResponseDto.make(ResponseCode.SERVER_ERROR, find)
			);
		}
		return ResponseEntity.status(200).body(
				ComResponseDto.make(ResponseCode.SUCCESS, find)
		);
	}

	@GetMapping("/checkvalidemail")
	public ResponseEntity<ComResponseDto<Boolean>> checkvalidemailGet(
			@RequestParam("signId") String signId,
			@RequestParam("validText") String validText) {
		Boolean isValid = this.checkValid(signId, validText);
		if ( isValid ) {
			return ResponseEntity.status(200).body(
					ComResponseDto.make(ResponseCode.SUCCESS, isValid)
			);
		} else {
			return ResponseEntity.status(500).body(
					ComResponseDto.make(ResponseCode.AUTHENTICATION_ERROR, isValid)
			);
		}
	}

	@PostMapping("/checkvalidemail")
	public ResponseEntity<ComResponseDto<Boolean>> checkvalidemailPost(
			@RequestBody ValidEmailDto validEmailDto) {
		Boolean isValid = this.authService.checkValidEmail(validEmailDto);
		if ( isValid ) {
			return ResponseEntity.status(200).body(
					ComResponseDto.make(ResponseCode.SUCCESS, isValid)
			);
		} else {
			return ResponseEntity.status(500).body(
					ComResponseDto.make(ResponseCode.AUTHENTICATION_ERROR, isValid)
			);
		}
	}

	private Boolean checkValid(String signId, String validText) {
		ValidEmailDto validEmailDto = ValidEmailDto.builder().signId(signId).validText(validText).build();
		Boolean isValid = this.authService.checkValidEmail(validEmailDto);
		return isValid;
	}

	@PostMapping("/signin")
	public ResponseEntity<ComResponseDto<Boolean>> signin(@RequestBody SignInDto signInDto
			, HttpSession httpSession
	) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signInDto.getSignId(), signInDto.getPassword())
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// 정상적으로 로그인(사인인) 되면 세션을 만들고 세션ID 를 쿠키로 응답합니다.
		// 클라이언트는 세션ID 해당 쿠키를 가지고 다음에 계속 요청한다.
		httpSession.setAttribute("MJC_LOGIN", signInDto.getSignId());
		httpSession.setMaxInactiveInterval(3600);
		return ResponseEntity.status(200).body(
				ComResponseDto.make(ResponseCode.SUCCESS, true)
		);
	}

	@GetMapping("/signout")
	public ResponseEntity<ComResponseDto<Boolean>> signout(HttpSession httpSession) {
		httpSession.invalidate();
		return ResponseEntity.status(200).body(
				ComResponseDto.make(ResponseCode.SUCCESS, true)
		);
	}
}
