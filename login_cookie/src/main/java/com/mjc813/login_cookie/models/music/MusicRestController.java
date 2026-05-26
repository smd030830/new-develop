package com.mjc813.login_cookie.models.music;

import com.mjc813.login_cookie.common.ComResponseDto;
import com.mjc813.login_cookie.common.ResponseCode;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<ComResponseDto<MusicDto>> insert(@RequestBody MusicDto insertDto) {
		MusicDto result = this.musicService.insert(insertDto);
		return ResponseEntity.status(201).body(
			ComResponseDto.make(ResponseCode.SUCCESS, result)
		);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ComResponseDto<MusicDto>> insert(@PathVariable Long id) {
		MusicDto result = this.musicService.findById(id);
		return ResponseEntity.status(200).body(
				ComResponseDto.make(ResponseCode.SUCCESS, result)
		);
	}

	@GetMapping("/all")
	public ResponseEntity<ComResponseDto<List<MusicDto>>> findAll(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		try {
			Optional<Cookie> cookie = Arrays.stream(cookies).filter(x -> x.getName().equals("MJC_LOGIN")).findFirst();
			if (cookie.isPresent()) {
				// 로그인 되어 있음
				List<MusicDto> result = this.musicService.findAll();
				return ResponseEntity.status(200).body(
						ComResponseDto.make(ResponseCode.SUCCESS, result)
				);
			} else {
				// 로그인 안되어 있음
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
