package com.mjc813.cafe_kiosk.models.category;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryDto insert(CategoryDto categoryDto) {
		CategoryEntity newData = new CategoryEntity();
		return saveCategoryDto(categoryDto, newData);
	}

	private @NonNull CategoryDto saveCategoryDto(CategoryDto categoryDto, CategoryEntity newData) {
		newData.copyMembers(categoryDto, true);
//		newData.setName(categoryDto.getName());
//		newData.setId(categoryDto.getId());

		CategoryEntity resEntity = this.categoryRepository.save(newData);

//		CategoryDto result = new CategoryDto();
//		result.copyMembers(resEntity, true);
//		result.setId(resEntity.getId());
//		result.setName(resEntity.getName());
		CategoryDto result = (CategoryDto) new CategoryDto().copyMembers(resEntity, true);
		return result;
	}

	public CategoryDto update(CategoryDto categoryDto) {
		CategoryEntity findData = this.categoryRepository.findById(categoryDto.getId()).orElseThrow();
		return saveCategoryDto(categoryDto, findData);
	}

	public CategoryDto deleteById(Integer id) {
		CategoryDto result = this.findById(id);
		this.categoryRepository.deleteById(id);
		return result;
	}

	public CategoryDto findById(Integer id) {
		CategoryEntity findData = this.categoryRepository.findById(id).orElseThrow();
//		CategoryDto result = new CategoryDto();
//		result.copyMembers(findData, true);
//		result.setId(findData.getId());
//		result.setName(findData.getName());
		CategoryDto result = (CategoryDto) new CategoryDto().copyMembers(findData, true);
		return result;
	}

	public Slice<CategoryDto> findByNameContains(String name, Pageable pageable) {
		Slice<CategoryEntity> slice = this.categoryRepository.findByNameContains(name, pageable);
		List<CategoryEntity> list = slice.getContent();
		List<CategoryDto> resultList = list.stream()
				.map(categoryEntity -> {
					CategoryDto item = (CategoryDto)CategoryDto.builder().build()
							.copyMembers(categoryEntity, true);
//					item.copyMembers(categoryEntity, true);
//					item.setId(categoryEntity.getId());
//					item.setName(categoryEntity.getName());
					return item;
				}).toList();
		Slice<CategoryDto> result = new SliceImpl<>(resultList, pageable, slice.hasNext());
		return result;
	}
}
