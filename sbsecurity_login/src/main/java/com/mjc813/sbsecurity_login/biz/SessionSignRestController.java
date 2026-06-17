package com.mjc813.sbsecurity_login.biz;

import com.mjc813.sbsecurity_login.common.ComResponseDto;
import com.mjc813.sbsecurity_login.common.LoginException;
import com.mjc813.sbsecurity_login.common.ResponseCode;
import com.mjc813.sbsecurity_login.models.auth.SignInDto;
import com.mjc813.sbsecurity_login.models.auth.SignUpDto;
import com.mjc813.sbsecurity_login.models.auth.ValidEmailDto;
import com.mjc813.sbsecurity_login.models.member.IMember;
import com.mjc813.sbsecurity_login.models.member.MemberDto;
import com.mjc813.sbsecurity_login.models.member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class SessionSignRestController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<ComResponseDto<IMember>> signUp(@RequestBody SignUpDto signUpDto) {
		MemberDto memberDto = (MemberDto) new MemberDto().clone(signUpDto, true);
		MemberDto inserted = this.memberService.insert(memberDto, false);

		return ResponseEntity.status(201).body(
				ComResponseDto.make(ResponseCode.SUCCESS, signUpDto)
		);
	}

	@GetMapping("/emailtest/{id}")
	public ResponseEntity<ComResponseDto<MemberDto>> emailTest(@PathVariable String id) {
		MemberDto find = this.memberService.findById(id);
		return ResponseEntity.status(200).body(
				ComResponseDto.make(ResponseCode.SUCCESS, find)
		);
	}

	@GetMapping("/checkvalidemail")
	public ResponseEntity<ComResponseDto<Boolean>> checkvalidemailGet(
			@RequestParam("signId") String signId,
			@RequestParam("validText") String validText) {
		Boolean isValid = this.checkValid(signId, validText);
		if (isValid) {
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
		if (isValid) {
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
			, HttpSession session) throws LoginException {
		Boolean isSign = this.authService.signMember(signInDto);
		if (isSign) {
			// 정상적으로 로그인(사인인) 되면 쿠키를 클라이언트로 응답한다.
			// 이 클라이언트 해당 쿠키를 가지고 다음에 계속 요청한다.
			session.setAttribute("MJC_LOGIN", signInDto.getSignId());
			session.setMaxInactiveInterval(3600);
			return ResponseEntity.status(200).body(
					ComResponseDto.make(ResponseCode.SUCCESS, isSign)
			);
		} else {
			return ResponseEntity.status(500).body(
					ComResponseDto.make(ResponseCode.AUTHENTICATION_ERROR, isSign)
			);
		}
	}

	@GetMapping("/signout")
	public ResponseEntity<ComResponseDto<Boolean>> signout(HttpSession httpSession) {
		httpSession.invalidate();
		return ResponseEntity.status(200).body(
				ComResponseDto.make(ResponseCode.SUCCESS, true)
		);
	}
}