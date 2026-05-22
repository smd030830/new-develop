package com.mjc813.cookies.models.cooking;

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
@RequestMapping("/api/v1/cooking")
public class CookingRestController {
	@Autowired
	private CookingService cookingService;

	@PostMapping
	public ResponseEntity<ApiResponse<CookingDto>> insert(@RequestBody CookingDto insertDto) {
		CookingDto result = this.cookingService.insert(insertDto);
		return ResponseEntity.status(201).body(
				ApiResponse.make(ResponseCode.insert_ok, "ok", result)
		);
	}

	@PatchMapping
	public ResponseEntity<ApiResponse<CookingDto>> update(@RequestBody CookingDto updateDto) {
		CookingDto result = this.cookingService.update(updateDto);
		return ResponseEntity.status(200).body(
				ApiResponse.make(ResponseCode.update_ok, "ok", result)
		);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<CookingDto>> deleteById(@PathVariable Long id) {
		CookingDto result = this.cookingService.deleteById(id);
		return ResponseEntity.status(200).body(
				ApiResponse.make(ResponseCode.delete_ok, "ok", result)
		);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CookingDto>> findById(@PathVariable Long id) {
		CookingDto result = this.cookingService.findById(id);
		return ResponseEntity.status(200).body(
				ApiResponse.make(ResponseCode.select_ok, "ok", result)
		);
	}

	@GetMapping("/searchdescription")
	public ResponseEntity<ApiResponse<Slice<CookingDto>>> findAllByDescriptionContaining(@RequestParam String description
			, @PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable pageable) {
		Slice<CookingDto> result = this.cookingService.findAllByDescriptionContaining(description, pageable);
		return ResponseEntity.status(200).body(
				ApiResponse.make(ResponseCode.select_ok, "ok", result)
		);
	}

	@GetMapping("/cookie/{cookieId}")
	public ResponseEntity<ApiResponse<Slice<CookingDto>>> findAllByCookieEquals(@PathVariable Long cookieId
			, @PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable pageable) {
		Slice<CookingDto> result = this.cookingService.findAllByCookieEquals(cookieId, pageable);
		return ResponseEntity.status(200).body(
				ApiResponse.make(ResponseCode.select_ok, "ok", result)
		);
	}
}
