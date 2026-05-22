package com.mjc813.cafe_kiosk.models.product;

import com.mjc813.cafe_kiosk.common.ApiResponse;
import com.mjc813.cafe_kiosk.common.CafeResponse;
import com.mjc813.cafe_kiosk.models.category.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prd")
public class ProductRestController {

	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<ApiResponse<ProductDto>> insert(@RequestBody ProductDto newDto) {
		ProductDto result = this.productService.insert(newDto);
		return ResponseEntity.status(201).body(
				ApiResponse.make(CafeResponse.insert_success, "ok", result)
		);
	}

	@PatchMapping
	public ResponseEntity<ApiResponse<ProductDto>> update(@RequestBody ProductDto updateDto) {
		ProductDto result = this.productService.update(updateDto);
		return ResponseEntity.status(200).body(
				ApiResponse.make(CafeResponse.update_success, "ok", result)
		);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<ProductDto>> delete(@PathVariable Integer id) {
		ProductDto result = this.productService.deleteById(id);
		return ResponseEntity.status(200).body(
				ApiResponse.make(CafeResponse.delete_success, "ok", result)
		);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ProductDto>> findById(@PathVariable Integer id) {
		ProductDto result = this.productService.findById(id);
		return ResponseEntity.status(200).body(
				ApiResponse.make(CafeResponse.select_success, "ok", result)
		);
	}

	@GetMapping("/search")
	public ResponseEntity<ApiResponse<Slice<ProductDto>>> findByNameContains(@RequestParam String name
			, @PageableDefault(size=5, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
		Slice<ProductDto> result = this.productService.findByNameContains(name, pageable);
		return ResponseEntity.status(200).body(
				ApiResponse.make(CafeResponse.select_success, "ok", result)
		);
	}

	@GetMapping("/pricegt")
	public ResponseEntity<ApiResponse<List<ProductDto>>> findByPriceGreaterThan(@RequestParam Integer price) {
		List<ProductDto> result = this.productService.findByPriceGreaterThan(price);
		return ResponseEntity.status(200).body(
				ApiResponse.make(CafeResponse.select_success, "ok", result)
		);
	}

	@GetMapping("/cat")
	public ResponseEntity<ApiResponse<Page<ProductDto>>> findByCategoryEntity(@RequestBody CategoryDto categoryDto
			, @PageableDefault(page=0, size=3, sort="id", direction = Sort.Direction.DESC) Pageable pageable
	) {
		Page<ProductDto> result = this.productService.findByCategoryEntity(categoryDto, pageable);
		return ResponseEntity.status(200).body(
				ApiResponse.make(CafeResponse.select_success, "ok", result)
		);
	}
}
