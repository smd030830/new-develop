package com.mjc813.cookies.models.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service    // 스프링부트가 이 클래스를 자동으로 Autowired, 객체로 생성할 수 있다.
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryDto insert(CategoryDto insertDto) {
		CategoryEntity categoryEntity = (CategoryEntity)new CategoryEntity().copyMembers(insertDto, true);
		categoryEntity.setId(null);
		CategoryEntity save = this.categoryRepository.save(categoryEntity);
		CategoryDto result = (CategoryDto)new CategoryDto().copyMembers(save, true);
		return result;
	}

	public CategoryDto update(CategoryDto updateDto) {
		CategoryEntity find = this.categoryRepository.findById(updateDto.getId()).orElseThrow();
		CategoryEntity updateEntity = (CategoryEntity)new CategoryEntity().copyMembers(find, true);
		updateEntity.copyMembers(updateDto, false); // false 이유는 수정할 멤버변수값만 복사한다.
		CategoryEntity save = this.categoryRepository.save(updateEntity);
		CategoryDto result = (CategoryDto)new CategoryDto().copyMembers(save, true);
		return result;
	}

	public CategoryDto findById(Long id) {
		CategoryEntity find = this.categoryRepository.findById(id).orElseThrow();
		CategoryDto result = (CategoryDto)new CategoryDto().copyMembers(find, true);
		return result;
	}

	public CategoryDto deleteById(Long id) {
		CategoryDto find = this.findById(id);
		this.categoryRepository.deleteById(id);
		return find;
	}

	public Slice<CategoryDto> findByNameContains(String name, Pageable pageable) {
		Slice<CategoryEntity> slc = this.categoryRepository.findByNameContains(name, pageable);
		List<CategoryDto> list = slc.getContent().stream()
				.map( t -> (CategoryDto)new CategoryDto().copyMembers(t, true))
				.toList();
		Slice<CategoryDto> result = new SliceImpl<>(list, slc.getPageable(), slc.hasNext());
		return result;
	}
}
