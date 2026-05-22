package com.mjc813.swimpool.models.teacher;

import com.mjc813.swimpool.models.swimpool.SwimpoolEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
	@Autowired
	private TeacherRepository teacherRepository;

	public TeacherDto insert(TeacherDto insertDto) {
		TeacherEntity insertEntity = (TeacherEntity)new TeacherEntity().copy(insertDto, true);
		TeacherEntity save = this.teacherRepository.save(insertEntity);
		TeacherDto result = (TeacherDto)new TeacherDto().copy(save, true);
		return result;
	}

	public TeacherDto findById(Long id) {
		TeacherEntity find = this.teacherRepository.findById(id).orElseThrow();
		TeacherDto result = (TeacherDto)new TeacherDto().copy(find, true);
		return result;
	}

	public TeacherDto update(TeacherDto updateDto) {
		TeacherDto find = this.findById(updateDto.getId());     // 데이터를 찾는다.
		TeacherEntity updateEntity = (TeacherEntity)new TeacherEntity().copy(find, true); // Entity 변환
		updateEntity.copy(updateDto, false);    // 화면에서 수정되지 않는 필드는 기존의 DB 값을 그대로 놔둔다.
		TeacherEntity save = this.teacherRepository.save(updateEntity);
		TeacherDto result = (TeacherDto)new TeacherDto().copy(save, true);
		return result;
	}

	public TeacherDto deleteById(Long id) {
		TeacherEntity find = this.teacherRepository.findById(id).orElseThrow();
		TeacherDto result = (TeacherDto)new TeacherDto().copy(find, true);
		this.teacherRepository.deleteById(id);
		return result;
	}

	public Slice<TeacherDto> findBySwimpool(Long swimpoolId, Pageable pageable) {
		SwimpoolEntity swimpool = SwimpoolEntity.builder().id(swimpoolId).build();
		Slice<TeacherEntity> find = this.teacherRepository.findBySwimpool(swimpool, pageable);
		return this.getSliceTeacherDto(find);
	}

	private Slice<TeacherDto> getSliceTeacherDto(Slice<TeacherEntity> find) {
		List<TeacherDto> list = find.getContent().stream().map(
				item -> (TeacherDto)new TeacherDto().copy(item, true)
		).toList();
		Slice<TeacherDto> result = new SliceImpl<>(list, find.getPageable(), find.hasNext());
		return result;
	}

	public Slice<TeacherDto> findByNameContaining(String name, Pageable pageable) {
		Slice<TeacherEntity> find = this.teacherRepository.findByNameContaining(name, pageable);
		return this.getSliceTeacherDto(find);
	}
}
