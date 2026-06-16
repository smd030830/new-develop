package com.mjc813.session_login.models.member;

import com.mjc813.session_login.common.ComResponseDto;
import com.mjc813.session_login.common.ResponseCode;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/member")
public class MemberRestController {
	@Autowired
	private MemberService memberService;

	@PostMapping("")
	public ResponseEntity<ComResponseDto<MemberDto>> insert(@RequestBody MemberDto memberDto) {
		MemberDto result = this.memberService.insert(memberDto, true);
		return ResponseEntity.status(201).body(
				ComResponseDto.make(ResponseCode.SUCCESS, result)
		);
	}

	@GetMapping("")
	public ResponseEntity<ComResponseDto<List<MemberDto>>> findAll(HttpSession session) {
		String userRole = (String) session.getAttribute("ROLE");

		if (userRole == null || !userRole.equals("ADMIN")) {
			return ResponseEntity.status(403)
					.body(ComResponseDto.make(ResponseCode.AUTHORIZATION_ERROR, null));
		}

		List<MemberDto> result = this.memberService.findAll();
		return ResponseEntity.status(200)
				.body(ComResponseDto.make(ResponseCode.SUCCESS, result));
	}
}
