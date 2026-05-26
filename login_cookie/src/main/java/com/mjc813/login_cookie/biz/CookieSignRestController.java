package com.mjc813.login_cookie.biz;

import com.mjc813.login_cookie.common.ComResponseDto;
import com.mjc813.login_cookie.common.LoginException;
import com.mjc813.login_cookie.common.ResponseCode;
import com.mjc813.login_cookie.models.auth.SignInDto;
import com.mjc813.login_cookie.models.auth.SignUpDto;
import com.mjc813.login_cookie.models.auth.ValidEmailDto;
import com.mjc813.login_cookie.models.member.IMember;
import com.mjc813.login_cookie.models.member.MemberDto;
import com.mjc813.login_cookie.models.member.MemberService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class CookieSignRestController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private MailService mailService;
	@Autowired
	private AuthService authService;

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
		, HttpServletResponse response) throws LoginException {
		Boolean isSign = this.authService.signMember(signInDto);
		if ( isSign ) {
			// 정상적으로 로그인(사인인) 되면 쿠키를 클라이언트로 응답한다.
			// 이 클라이언트 해당 쿠키를 가지고 다음에 계속 요청한다.
			Cookie signCookie = new Cookie("MJC_LOGIN", signInDto.getSignId());
			signCookie.setPath("/");
			signCookie.setHttpOnly(true);
			signCookie.setMaxAge(3600);
			response.addCookie(signCookie);
			return ResponseEntity.status(200).body(
					ComResponseDto.make(ResponseCode.SUCCESS, isSign)
			);
		} else {
			return ResponseEntity.status(500).body(
					ComResponseDto.make(ResponseCode.AUTHENTICATION_ERROR, isSign)
			);
		}
	}
}
