package com.mjc813.cookies.models.category;

import com.mjc813.cookies.models.common.ApiResponse;
import com.mjc813.cookies.models.common.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @RestController 는 RestFul API 서버를 만들어주는 컨트롤러 애노테이션이다.
 * RestFul API : http/https 로 JSON 데이터 통신을 하는 국제 표준방식
 * 클라이언트가 요청 : URI 주소, Method, 요청데이터 {http header(COOKIE, SESSIONID, CONTENT-TYPE,..), http body(JSON데이터, GET방식데이터, 첨부파일데이터)}
 * 요청에 대해서 서버가 응답 : 서버는 클라이언트의 요청을 처리하고 http header, http body(JSON) 으로 응답할 수 있다.
 */
@Slf4j  // 로그를 기록하는 애노테이션
@RestController // 클래스를 Rest 컨트롤러 기능으로 만든다.
@RequestMapping("/api/v1/category") // URI 주소 중에서 공통 앞부분
public class CategoryRestController {
	@Autowired // 스프링부트가 자동으로 new 인스터스객체로 생성해 준다. @Service, @Component, @Repository, ... 인 클래스만 가능
	private CategoryService categoryService;

	@GetMapping("/{fruit}/{color}/{size}")
	public String test(@PathVariable String fruit, @PathVariable String color, @PathVariable String size) {
		log.info("test fruit={}, color={}, size={}", fruit, color, size);
		return fruit + ", " + color + ", " + size;
	}

	@PostMapping // URI 주소 뒷 부분을 서술 할 수 있다.
	public ResponseEntity<ApiResponse<CategoryDto>> insert(@RequestBody CategoryDto insertDto) {
		CategoryDto result = this.categoryService.insert(insertDto);
		return ResponseEntity.status(201).body(
//				ApiResponse.<CategoryDto>builder()
//						.responseCode(ResponseCode.insert_ok)
//						.message("ok")
//						.responseData(result)
//						.build()
				ApiResponse.make(ResponseCode.insert_ok, "ok", result)
		);
	}

	@PatchMapping
	public ResponseEntity<ApiResponse<CategoryDto>> update(@RequestBody CategoryDto insertDto) {
		CategoryDto result = this.categoryService.update(insertDto);
		return ResponseEntity.status(200).body(
				ApiResponse.make(ResponseCode.update_ok, "ok", result)
		);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CategoryDto>> findById(@PathVariable Long id) {
		CategoryDto result = this.categoryService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(
				ApiResponse.make(ResponseCode.select_ok, "ok", result)
		);
	}

	@DeleteMapping("/{id}") // URI 주소 뒷 부분을 서술한다. @PathVariable 변수 이름으로 {변수} 하면 주소를 값으로 받는다.
	public ResponseEntity<ApiResponse<CategoryDto>> deleteById(@PathVariable Long id) {
		CategoryDto result = this.categoryService.deleteById(id);
		return ResponseEntity.status(200).body(
				ApiResponse.make(ResponseCode.delete_ok, "ok", result)
		);
	}

	@GetMapping("/searchname")
	public ResponseEntity<ApiResponse<Slice<CategoryDto>>> searchByName(@RequestParam String name
			, @PageableDefault(size=10, page=0, sort="id", direction= Sort.Direction.DESC) Pageable pageable) {
		Slice<CategoryDto> result = this.categoryService.findByNameContains(name, pageable);
		return ResponseEntity.status(200).body(
				ApiResponse.make(ResponseCode.select_ok, "ok", result)
		);
	}
}
