package com.mjc813.cookies.models.recipe;

import com.mjc813.cookies.models.common.ApiResponse;
import com.mjc813.cookies.models.common.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/recipe")
@RequiredArgsConstructor
public class RecipeRestController {
	private final RecipeService recipeService;

	@PostMapping
	public ResponseEntity<ApiResponse<RecipeDto>> insert(@RequestBody RecipeDto insertDto) {
		RecipeDto result = this.recipeService.insert(insertDto);
		return ResponseEntity.status(201).body(
				ApiResponse.make(ResponseCode.insert_ok, "ok", result)
		);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<RecipeDto>> findById(@PathVariable Long id) {
		RecipeDto result = this.recipeService.findById(id);
		return ResponseEntity.status(200).body(
				ApiResponse.make(ResponseCode.select_ok, "ok", result)
		);
	}

	@PatchMapping
	public ResponseEntity<ApiResponse<RecipeDto>> update(@RequestBody RecipeDto updateDto) {
		RecipeDto result = this.recipeService.update(updateDto);
		return ResponseEntity.status(201).body(
				ApiResponse.make(ResponseCode.update_ok, "ok", result)
		);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<RecipeDto>> deleteById(@PathVariable Long id) {
		RecipeDto result = this.recipeService.deleteById(id);
		return ResponseEntity.status(200).body(
				ApiResponse.make(ResponseCode.delete_ok, "ok", result)
		);
	}

	@GetMapping("/cookie/{cookieId}")
	public ResponseEntity<ApiResponse<Slice<RecipeDto>>> findAllByCookieEquals(@PathVariable Long cookieId
			, @PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable pageable
	) {
		Slice<RecipeDto> result = this.recipeService.findAllByCookieEquals(cookieId, pageable);
		return ResponseEntity.status(200).body(
				ApiResponse.make(ResponseCode.select_ok, "ok", result)
		);
	}

	@GetMapping("/ingredient/{ingredientId}")
	public ResponseEntity<ApiResponse<Slice<RecipeDto>>> findByIngredient(@PathVariable Long ingredientId
			, @PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable pageable
	) {
		Slice<RecipeDto> result = this.recipeService.findByIngredient(ingredientId, pageable);
		return ResponseEntity.status(200).body(
				ApiResponse.make(ResponseCode.select_ok, "ok", result)
		);
	}
}
