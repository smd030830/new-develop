package com.mjc813.login_cookie.models.music;

import com.mjc813.login_cookie.common.ComResponseDto;
import com.mjc813.login_cookie.common.ResponseCode;
import com.mjc813.login_cookie.models.member.IMember;
import com.mjc813.login_cookie.models.member.MemberDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/music")
public class MusicRestController {
	@Autowired
	private MusicService musicService;

	@PostMapping("")
	public ResponseEntity<ComResponseDto<MusicDto>> insert(Model model, @RequestBody MusicDto insertDto) {
		IMember signedMember = (IMember)model.getAttribute("signedMember");
		// Model 클래스에 "signedMember" 키에 해당하는 MemberDto 가 존재하는지 찾는다.
		if ( !this.IsUserOrAdmin(model) ) {
			// 존재하지 않으면 인가 에러를 출력한다.
			return ResponseEntity.status(500).body(
				ComResponseDto.make(ResponseCode.AUTHORIZATION_ERROR, null)
			);
		}
		insertDto.setCreateId(signedMember.getSignId());
		MusicDto result = this.musicService.insert(insertDto);
		return ResponseEntity.status(201).body(
			ComResponseDto.make(ResponseCode.SUCCESS, result)
		);
	}

	// role 이 USER 인지 체크
	private Boolean IsUser(Model model) {
		IMember signedMember = (IMember)model.getAttribute("signedMember");
		if ( signedMember == null) {
			return false;
		}
		if ( !signedMember.getRole().equals("USER") ) {
			return false;
		}
		return true;
	}

	private Boolean IsUserOrAdmin(Model model) {
		IMember signedMember = (IMember)model.getAttribute("signedMember");
		if ( signedMember == null) {
			return false;
		}
		if ( signedMember.getRole().equals("GUEST") ) {
			return false;
		}
		return true;

	}

	private Boolean IsAdmin(Model model) {
		IMember signedMember = (IMember)model.getAttribute("signedMember");
		if ( signedMember == null) {
			return false;
		}
		if ( !signedMember.getRole().equals("ADMIN") ) {
			return false;
		}
		return true;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ComResponseDto<MusicDto>> findById(@PathVariable Long id) {
		MusicDto result = this.musicService.findById(id);
		return ResponseEntity.status(200).body(
				ComResponseDto.make(ResponseCode.SUCCESS, result)
		);
	}

	@GetMapping("/all")
	public ResponseEntity<ComResponseDto<List<MusicDto>>> findAll(
//			HttpServletRequest request
			@CookieValue(name="MJC_LOGIN", required = true) String signId
	) {
//		Cookie[] cookies = request.getCookies();
		try {
//			Optional<Cookie> cookie = Arrays.stream(cookies).filter(x -> x.getName().equals("MJC_LOGIN")).findFirst();
			if (signId != null) {
				// 쿠키 MJC_LOGIN 키에 값이 있다
				List<MusicDto> result = this.musicService.findAll();
				return ResponseEntity.status(200).body(
						ComResponseDto.make(ResponseCode.SUCCESS, result)
				);
			} else {
				// 쿠키 MJC_LOGIN 키에 값이 없다
				return ResponseEntity.status(500).body(
						ComResponseDto.make(ResponseCode.AUTHORIZATION_ERROR, null)
				);
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(
					ComResponseDto.make(ResponseCode.AUTHORIZATION_ERROR, null)
			);
		}
	}
}
