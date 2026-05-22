package com.mjc813.cafe_kios.models.product;

import com.mjc813.cafe_kios.models.category.CategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository repository;
	private final ProductARepository repositoryA;

	public ProductDto insert(ProductDto newDto) {
		ProductEntity newEntity = new ProductEntity();
//		newEntity2.setCategory(new CategoryEntity());
		newEntity.copyMembers(newDto);
		newEntity.setId(null);
		this.repository.save(newEntity);
		ProductDto result = new ProductDto();
//		result.setCategory(new CategoryEntity());
		result.copyMembers(newEntity);
		return result;
	}

	public ProductDto update(ProductDto updateDto) {
		ProductEntity emptyEntity = new ProductEntity();
		ProductEntity findEntity = this.repository.findById(updateDto.getId()).orElseThrow();
		findEntity.copyMembers(updateDto);
		this.repository.save(findEntity);
		ProductDto result = new ProductDto();
		result.copyMembers(findEntity);
		return result;
	}

	public ProductADto insertA(ProductADto newDto) {
		ProductAEntity newEntity = new ProductAEntity();
		CategoryEntity category = CategoryEntity.builder().id(newDto.getCategoryId()).build();
		newEntity.copyMembers(newDto);
		newEntity.setId(null);
		newEntity.setCategoryObj(category);
		this.repositoryA.save(newEntity);
		ProductADto result = new ProductADto();
		result.copyMembers(newEntity);
		return result;
	}
}
