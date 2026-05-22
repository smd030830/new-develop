package com.mjc813.swimpool.models.teacher;

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

@RestController
@RequestMapping("/rest/tc")
public class TeacherRestController {
	@Autowired
	private TeacherService teacherService;

	@PostMapping
	public ResponseEntity<CommonResponseDto<TeacherDto>> insert(@RequestBody TeacherDto insertDto) {
		TeacherDto result = this.teacherService.insert(insertDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(
			CommonResponseDto.make(CommonResponseCode.C0000, result)
		);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CommonResponseDto<TeacherDto>> findById(@PathVariable Long id) {
		TeacherDto result = this.teacherService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(
				CommonResponseDto.make(CommonResponseCode.C0000, result)
		);
	}

	@PatchMapping
	public ResponseEntity<CommonResponseDto<TeacherDto>> update(@RequestBody TeacherDto updateDto) {
		TeacherDto result = this.teacherService.update(updateDto);
		return ResponseEntity.status(HttpStatus.OK).body(
				CommonResponseDto.make(CommonResponseCode.C0000, result)
		);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CommonResponseDto<TeacherDto>> deleteById(@PathVariable Long id) {
		TeacherDto result = this.teacherService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body(
				CommonResponseDto.make(CommonResponseCode.C0000, result)
		);
	}

	@GetMapping("/swimpool/{id}")
	public ResponseEntity<CommonResponseDto<Slice<TeacherDto>>> findBySwimpool(
			@PathVariable(name="id") Long swimpoolId
			, @PageableDefault(page=0, size=5, sort="name", direction= Sort.Direction.ASC) Pageable pageable) {
		Slice<TeacherDto> result = this.teacherService.findBySwimpool(swimpoolId, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(
				CommonResponseDto.make(CommonResponseCode.C0000, result)
		);
	}

	@GetMapping("/teacher")
	public ResponseEntity<CommonResponseDto<Slice<TeacherDto>>> findByNameContaining(
			@RequestParam(name="name") String name
			, @PageableDefault(page=0, size=5, sort="name", direction= Sort.Direction.ASC) Pageable pageable) {
		Slice<TeacherDto> result = this.teacherService.findByNameContaining(name, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(
				CommonResponseDto.make(CommonResponseCode.C0000, result)
		);
	}
}
