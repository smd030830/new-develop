package com.mjc813.login_cookie.biz;

import com.mjc813.login_cookie.common.ComResponseDto;
import com.mjc813.login_cookie.common.ResponseCode;
import com.mjc813.login_cookie.models.auth.SignUpDto;
import com.mjc813.login_cookie.models.auth.ValidEmailDto;
import com.mjc813.login_cookie.models.member.IMember;
import com.mjc813.login_cookie.models.member.MemberDto;
import com.mjc813.login_cookie.models.member.MemberService;
import jakarta.mail.MessagingException;
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
	public ResponseEntity<ComResponseDto<Boolean>> checkvalidemail(
			@RequestParam("signId") String signId,
			@RequestParam("validText") String validText) {
		ValidEmailDto validEmailDto = ValidEmailDto.builder().signId(signId).validText(validText).build();
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
}
