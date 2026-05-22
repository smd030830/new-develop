package com.mjc813.cookies.models.ingredient;

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
@RequestMapping("/api/v1/ingred")
public class IngredientRestController {
	@Autowired
	private IngredientService ingredientService;

	@PostMapping
	public ResponseEntity<ApiResponse<IngredientDto>> insert(@RequestBody IngredientDto insertDto) {
		IngredientDto result = this.ingredientService.insert(insertDto);
		return ResponseEntity.status(201).body(
				ApiResponse.make(ResponseCode.insert_ok, "ok", result)
		);
	}

	@PatchMapping
	public ResponseEntity<ApiResponse<IngredientDto>> update(@RequestBody IngredientDto updateDto) {
		IngredientDto result = this.ingredientService.update(updateDto);
		return ResponseEntity.status(200).body(
				ApiResponse.make(ResponseCode.update_ok, "ok", result)
		);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<IngredientDto>> deleteById(@PathVariable Long id) {
		IngredientDto result = this.ingredientService.deleteById(id);
		return ResponseEntity.status(200).body(
				ApiResponse.make(ResponseCode.delete_ok, "ok", result)
		);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<IngredientDto>> findById(@PathVariable Long id) {
		IngredientDto result = this.ingredientService.findById(id);
		return ResponseEntity.status(200).body(
				ApiResponse.make(ResponseCode.select_ok, "ok", result)
		);
	}

	@GetMapping("/searchname")
	public ResponseEntity<ApiResponse<Slice<IngredientDto>>> findAllByNameContaining(@RequestParam String name
			, @PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable pageable) {
		Slice<IngredientDto> result = this.ingredientService.findAllByNameContaining(name, pageable);
		return ResponseEntity.status(200).body(
				ApiResponse.make(ResponseCode.select_ok, "ok", result)
		);
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<ApiResponse<Slice<IngredientDto>>> findAllByCategoryEquals(@PathVariable Long categoryId
			, @PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable pageable) {
		Slice<IngredientDto> result = this.ingredientService.findAllByCategoryEquals(categoryId, pageable);
		return ResponseEntity.status(200).body(
				ApiResponse.make(ResponseCode.select_ok, "ok", result)
		);
	}
}
