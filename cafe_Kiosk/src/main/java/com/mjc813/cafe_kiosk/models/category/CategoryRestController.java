package com.mjc813.cafe_kiosk.models.category;

import com.mjc813.cafe_kiosk.common.ApiResponse;
import com.mjc813.cafe_kiosk.common.CafeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cat") // 화면 -> 컨트롤러 -> 서비스 -> Repository
public class CategoryRestController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping
	public ResponseEntity<ApiResponse<CategoryDto>> insert(@RequestBody CategoryDto data) {
		CategoryDto result = this.categoryService.insert(data);
		return ResponseEntity.status(201).body(
				ApiResponse.make(CafeResponse.insert_success, "ok", result)
		);
	}

	@PatchMapping
	public ResponseEntity<ApiResponse<CategoryDto>> update(@RequestBody CategoryDto data) {
		CategoryDto result = this.categoryService.update(data);
		return ResponseEntity.status(201).body(
				ApiResponse.make(CafeResponse.update_success, "ok", result)
		);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<CategoryDto>> deleteById(@PathVariable Integer id) {
		CategoryDto result = this.categoryService.deleteById(id);
		return ResponseEntity.status(201).body(
				ApiResponse.make(CafeResponse.delete_success, "ok", result)
		);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CategoryDto>> findById(@PathVariable Integer id) {
		CategoryDto result = this.categoryService.findById(id);
		return ResponseEntity.status(201).body(
				ApiResponse.make(CafeResponse.select_success, "ok", result)
		);
	}

	@GetMapping("/search")
	public ResponseEntity<ApiResponse<Slice<CategoryDto>>> findSearch(@RequestParam String name, Pageable pageable) {
		Slice<CategoryDto> result = this.categoryService.findByNameContains(name, pageable);
		return ResponseEntity.status(201).body(
				ApiResponse.make(CafeResponse.select_success, "ok", result)
		);
	}
}
