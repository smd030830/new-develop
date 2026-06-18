package com.mjc813.sbsecurity_login.models.member;

import com.mjc813.sbsecurity_login.common.ComResponseDto;
import com.mjc813.sbsecurity_login.common.Mjc813Exception;
import com.mjc813.sbsecurity_login.common.ResponseCode;
import com.mjc813.sbsecurity_login.models.music.MusicDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/member")
public class MemberRestController {
	@Autowired
	private MemberService memberService;

	@PostMapping("")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public ResponseEntity<ComResponseDto<MemberDto>> insert(@RequestBody MemberDto memberDto) {
		MemberDto result = this.memberService.insert(memberDto, true);
		return ResponseEntity.status(201).body(
				ComResponseDto.make(ResponseCode.SUCCESS, result)
		);
	}
	@PatchMapping("")
	@PreAuthorize("hasAnyAuthority('ADMIN') or @memberService.isCreateId(#updateDto.id, authentication.name)")
	public ResponseEntity<ComResponseDto<MusicDto>> update(@RequestBody MusicDto updateDto) throws Mjc813Exception {
		MusicDto result = this.memberService.update(updateDto);
		return ResponseEntity.status(HttpStatus.OK).body(
				ComResponseDto.make(ResponseCode.SUCCESS, result)
		);
	}

	@GetMapping("")
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
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
	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ComResponseDto<List<MemberDto>>> findAll() throws Mjc813Exception {
		List<MusicDto> result = this.memberService.findAll();
		return ResponseEntity.status(200).body(
				ComResponseDto.make(ResponseCode.SUCCESS, result)
		);
	}
}
