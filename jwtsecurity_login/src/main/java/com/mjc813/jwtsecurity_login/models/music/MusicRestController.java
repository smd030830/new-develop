package com.mjc813.jwtsecurity_login.models.music;

import com.mjc813.jwtsecurity_login.common.ComResponseDto;
import com.mjc813.jwtsecurity_login.common.LoginException;
import com.mjc813.jwtsecurity_login.common.Mjc813Exception;
import com.mjc813.jwtsecurity_login.common.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("hasAnyAuthority('ADMIN') or @musicService.isCreateId(#updateDto.id, authentication.name)")
	public ResponseEntity<ComResponseDto<MusicDto>> update(@RequestBody MusicDto updateDto) throws Mjc813Exception {
		MusicDto result = this.musicService.update(updateDto);
		return ResponseEntity.status(HttpStatus.OK).body(
				ComResponseDto.make(ResponseCode.SUCCESS, result)
		);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN') or @musicService.isCreateId(#id, authentication.name)")
	public ResponseEntity<ComResponseDto<MusicDto>> deleteById(@PathVariable Long id) throws Mjc813Exception {
		MusicDto result = this.musicService.deleteById(id);
		return ResponseEntity.status(200).body(
				ComResponseDto.make(ResponseCode.SUCCESS, result)
		);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	public ResponseEntity<ComResponseDto<MusicDto>> findById(@PathVariable Long id) throws Mjc813Exception {
		MusicDto result = this.musicService.findById(id);
		return ResponseEntity.status(200).body(
				ComResponseDto.make(ResponseCode.SUCCESS, result)
		);
	}

	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ComResponseDto<List<MusicDto>>> findAll() throws Mjc813Exception {
		List<MusicDto> result = this.musicService.findAll();
		return ResponseEntity.status(200).body(
				ComResponseDto.make(ResponseCode.SUCCESS, result)
		);
	}
}
