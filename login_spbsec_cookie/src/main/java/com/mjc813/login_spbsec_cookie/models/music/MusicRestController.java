package com.mjc813.login_spbsec_cookie.models.music;

import com.mjc813.login_spbsec_cookie.common.ComResponseDto;
import com.mjc813.login_spbsec_cookie.common.LoginException;
import com.mjc813.login_spbsec_cookie.common.Mjc813Exception;
import com.mjc813.login_spbsec_cookie.common.ResponseCode;
import com.mjc813.login_spbsec_cookie.models.member.IMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/music")
public class MusicRestController {
	@Autowired
	private MusicService musicService;

	@PostMapping("")
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public ResponseEntity<ComResponseDto<MusicDto>> insert(@RequestBody MusicDto insertDto) throws LoginException {
		MusicDto result = this.musicService.insert(insertDto);
		return ResponseEntity.status(201).body(
			ComResponseDto.make(ResponseCode.SUCCESS, result)
		);
	}

	@PatchMapping("")
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public ResponseEntity<ComResponseDto<MusicDto>> update(@RequestBody MusicDto updateDto) throws Mjc813Exception {
		MusicDto result = this.musicService.update(updateDto);
		return ResponseEntity.status(HttpStatus.OK).body(
				ComResponseDto.make(ResponseCode.SUCCESS, result)
		);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public ResponseEntity<ComResponseDto<MusicDto>> delete(@PathVariable Long id
			, @AuthenticationPrincipal IMember signedMember
	) throws Mjc813Exception {
		System.out.println(signedMember);
		MusicDto result = this.musicService.deleteById(id);
		return ResponseEntity.status(200).body(
				ComResponseDto.make(ResponseCode.SUCCESS, result)
		);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ComResponseDto<MusicDto>> findById(@PathVariable Long id) throws Mjc813Exception {
		MusicDto result = this.musicService.findById(id);
		return ResponseEntity.status(200).body(
				ComResponseDto.make(ResponseCode.SUCCESS, result)
		);
	}

	@GetMapping("/all")
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public ResponseEntity<ComResponseDto<List<MusicDto>>> findAll() throws Mjc813Exception {
		List<MusicDto> result = this.musicService.findAll();
		return ResponseEntity.status(200).body(
				ComResponseDto.make(ResponseCode.SUCCESS, result)
		);
	}
}
