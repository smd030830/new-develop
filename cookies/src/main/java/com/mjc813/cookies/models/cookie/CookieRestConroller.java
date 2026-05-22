package com.mjc813.cookies.models.cookie;

import com.mjc813.cookies.models.common.ApiResponse;
import com.mjc813.cookies.models.common.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cookie")
public class CookieRestConroller {
	@Autowired
	private CookieService cookieService;

	@PostMapping
	public ResponseEntity<ApiResponse<CookieDto>> insert(@RequestBody CookieDto insertDto) {
		CookieDto result = this.cookieService.insert(insertDto);
		return ResponseEntity.status(201).body(
//				ApiResponse.<CookieDto>builder()
//						.responseCode(ResponseCode.insert_ok)
//						.message("ok")
//						.responseData(result)
//						.build()
				ApiResponse.make(ResponseCode.insert_ok, "ok", result)
		);
	}

	@PatchMapping
	public ResponseEntity<ApiResponse<CookieDto>> update(@RequestBody CookieDto insertDto) {
		CookieDto result = this.cookieService.update(insertDto);
		return ResponseEntity.status(200).body(
				ApiResponse.make(ResponseCode.update_ok, "ok", result)
		);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CookieDto>> findById(@PathVariable Long id) {
		CookieDto result = this.cookieService.findById(id);
		return ResponseEntity.status(200).body(
				ApiResponse.make(ResponseCode.select_ok, "ok", result)
		);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<CookieDto>> deleteById(@PathVariable Long id) {
		CookieDto result = this.cookieService.deleteById(id);
		return ResponseEntity.status(200).body(
				ApiResponse.make(ResponseCode.delete_ok, "ok", result)
		);
	}

	@GetMapping("/searchname")
	public ResponseEntity<ApiResponse<Slice<CookieDto>>> searchByName(@RequestParam String name
			, @PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable pageable) {
		Slice<CookieDto> result = this.cookieService.findByNameContains(name, pageable);
		return ResponseEntity.status(200).body(
				ApiResponse.make(ResponseCode.select_ok, "ok", result)
		);
	}
}
