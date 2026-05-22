package com.mjc813.swimpool.models.swimpool;

import com.mjc813.swimpool.common.CommonResponseCode;
import com.mjc813.swimpool.common.CommonResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/sp")
public class SwimpoolRestController {
	@Autowired
	private SwimpoolService swimpoolService;

	@PostMapping
	public ResponseEntity<CommonResponseDto<SwimpoolDto>> insert(@RequestBody SwimpoolDto insertDto) {
		SwimpoolDto result = this.swimpoolService.insert(insertDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(
				CommonResponseDto.make(CommonResponseCode.C0000, result)
		);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CommonResponseDto<SwimpoolDto>> findById(@PathVariable Long id) {
		SwimpoolDto result = this.swimpoolService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(
				CommonResponseDto.make(CommonResponseCode.C0000, result)
		);
	}

	@PatchMapping
	public ResponseEntity<CommonResponseDto<SwimpoolDto>> update(@RequestBody SwimpoolDto updateDto) {
		SwimpoolDto result = this.swimpoolService.update(updateDto);
		return ResponseEntity.status(HttpStatus.OK).body(
				CommonResponseDto.make(CommonResponseCode.C0000, result)
		);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CommonResponseDto<SwimpoolDto>> delete(@PathVariable Long id) {
		SwimpoolDto result = this.swimpoolService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body(
				CommonResponseDto.make(CommonResponseCode.C0000, result)
		);
	}

	@GetMapping("/all")
	public ResponseEntity<CommonResponseDto<List<SwimpoolDto>>> findAll() {
		List<SwimpoolDto> result = this.swimpoolService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(
				CommonResponseDto.make(CommonResponseCode.C0000, result)
		);
	}

	@GetMapping("/name")
	public ResponseEntity<CommonResponseDto<Slice<SwimpoolDto>>> findAllByNameContaining(
			@RequestParam String name, @PageableDefault(size=10, page=0, sort="name", direction= Sort.Direction.ASC) Pageable pageable
	) {
		Slice<SwimpoolDto> result = this.swimpoolService.findAllByNameContaining(name, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(
				CommonResponseDto.make(CommonResponseCode.C0000, result)
		);
	}
}
