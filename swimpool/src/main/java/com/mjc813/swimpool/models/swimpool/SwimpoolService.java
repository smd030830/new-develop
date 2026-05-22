package com.mjc813.swimpool.models.swimpool;

import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SwimpoolService {
//	@Autowired
	private final SwimpoolRepository swimpoolRepository;

	public SwimpoolDto insert(SwimpoolDto insertDto) {
		log.debug("insetDto = {}", insertDto);
		SwimpoolEntity newObject = (SwimpoolEntity) new SwimpoolEntity().copy(insertDto, true);
		SwimpoolEntity madeObject = this.swimpoolRepository.save(newObject);
		this.swimpoolRepository.flush();
		SwimpoolDto result = (SwimpoolDto) new SwimpoolDto().copy(madeObject, true);
		log.debug("result = {}", result);
		return result;
	}

	public SwimpoolDto findById(Long id) {
		log.debug("findById = {}", id);
		SwimpoolEntity find = this.swimpoolRepository.findById(id).orElseThrow();
		SwimpoolDto result = (SwimpoolDto) new SwimpoolDto().copy(find, true);
		log.debug("result = {}", result);
		return result;
	}

	public SwimpoolDto update(SwimpoolDto updateDto) {
		log.debug("update = {}", updateDto);
		SwimpoolDto find = this.findById(updateDto.getId());
		find.copy(updateDto, false);
		SwimpoolEntity updateEntity = (SwimpoolEntity) new SwimpoolEntity().copy(find, true);
		SwimpoolEntity save = this.swimpoolRepository.save(updateEntity);
		SwimpoolDto result = (SwimpoolDto) new SwimpoolDto().copy(save, true);
		log.debug("result = {}", result);
		return result;
	}

	public SwimpoolDto deleteById(Long id) {
		log.debug("delete = {}", id);
		SwimpoolDto find = this.findById(id);
		this.swimpoolRepository.deleteById(id);
		log.debug("result = {}", find);
		return find;
	}

	// findAll 을 하면 모든 데이터를 조회하므로 행수가 많은 테이블은 사용하지 마세요.
	// 행수가 적은 테이블은 사용가능하다. 실제 예를 들면 한국의 경기도,강원도,경상북도,전라남도,제주도...
	public List<SwimpoolDto> findAll() {
		List<SwimpoolEntity> list = this.swimpoolRepository.findAll();
		List<SwimpoolDto> result = list.stream().map(
			node -> (SwimpoolDto) new SwimpoolDto().copy(node, true)
		).toList();
		return result;
	}

	public Page<SwimpoolDto> findAllByNameContaining(String name, Pageable pageable) {
		Page<SwimpoolEntity> selectData = this.swimpoolRepository.findAllByNameContaining(name, pageable);
		List<SwimpoolDto> list = selectData.stream().map(
				node -> (SwimpoolDto) new SwimpoolDto().copy(node, true)
		).toList();
		Page<SwimpoolDto> result = new PageImpl<>(list, selectData.getPageable(), selectData.getTotalElements());
		return result;
	}
}
