package com.mjc813.cafe_kios.models.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repository;

	public CategoryDto insert(CategoryDto newData) {
		CategoryEntity newEntity = new CategoryEntity();
		newEntity.copyMembers(newData);
		newEntity.setId(null);
		this.repository.save(newEntity);
		CategoryDto result = new CategoryDto();
		result.copyMembers(newEntity);
		return result;
	}
}
