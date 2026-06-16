package com.mjc813.login_session.models.music;

import com.mjc813.login_session.common.ComResponseDto;
import com.mjc813.login_session.common.Mjc813Exception;
import com.mjc813.login_session.common.ResponseCode;
import com.mjc813.login_session.models.member.IMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.mjc813.login_session.common.LoginException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/music")
public class MusicRestController {
	@Autowired
	private MusicService musicService;

	@PostMapping("")
	public ResponseEntity<ComResponseDto<MusicDto>> insert(Model model, @RequestBody MusicDto insertDto) throws LoginException {
		// 이 곳 Controller 에서 isUserOrAdmin 을 사용하는 것보다 Service 안에서 사용하는게 좋다. update, delete 참고하세요
		IMember signedMember = this.isUserOrAdmin(model);
		if ( signedMember == null ) {
			// 존재하지 않으면 인가 에러를 출력한다.
			return ResponseEntity.status(500).body(
				ComResponseDto.make(ResponseCode.AUTHORIZATION_ERROR, null)
			);
		}
		MusicDto result = this.musicService.insert(signedMember, insertDto);
		return ResponseEntity.status(201).body(
			ComResponseDto.make(ResponseCode.SUCCESS, result)
		);
	}

	// role 이 USER 또는 ADMIN 인지 체크해서 아니면 null 을 리턴
	private IMember isUserOrAdmin(Model model) {
		IMember signedMember = (IMember)model.getAttribute("signedMember");
		if ( signedMember != null && signedMember.getRole().equals("GUEST") ) {
			return null;
		}
		return signedMember;
	}

	@PatchMapping("")
	public ResponseEntity<ComResponseDto<MusicDto>> update(Model model
			, @RequestBody MusicDto updateDto) throws Mjc813Exception {
		MusicDto result = this.musicService.update(model, updateDto);
		return ResponseEntity.status(HttpStatus.OK).body(
				ComResponseDto.make(ResponseCode.SUCCESS, result)
		);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ComResponseDto<MusicDto>> deleteById(Model model
			, @PathVariable Long id) throws Mjc813Exception {
		MusicDto result = this.musicService.deleteById(model, id);
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
	public ResponseEntity<ComResponseDto<List<MusicDto>>> findAll(Model model) throws Mjc813Exception {
		List<MusicDto> result = this.musicService.findAll(model);
		return ResponseEntity.status(200).body(
				ComResponseDto.make(ResponseCode.SUCCESS, result)
		);
	}
}
