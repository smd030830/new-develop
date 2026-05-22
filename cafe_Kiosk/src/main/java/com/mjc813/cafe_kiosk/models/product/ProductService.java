package com.mjc813.cafe_kiosk.models.product;

import com.mjc813.cafe_kiosk.models.category.CategoryDto;
import com.mjc813.cafe_kiosk.models.category.CategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.swing.text.PlainDocument;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;

	public ProductDto insert(ProductDto newDto) {
		ProductEntity newEntity = (ProductEntity) new ProductEntity().copyMembers(newDto, true);
		newEntity.setId(null);

		ProductEntity save = this.productRepository.save(newEntity);

		ProductDto result = (ProductDto) new ProductDto().copyMembers(save, true);
		return result;
	}

	public ProductDto update(ProductDto updateDto) {
		ProductEntity findEntity = this.productRepository.findById(updateDto.getId()).orElseThrow();
		ProductEntity data = (ProductEntity) new ProductEntity().copyMembers(findEntity, true);
		data.copyMembers(updateDto, false);

		ProductEntity save = this.productRepository.save(data);

		ProductDto result = (ProductDto) new ProductDto().copyMembers(save, true);
		return result;
	}

	public ProductDto deleteById(Integer id) {
		ProductDto find = this.findById(id);
		this.productRepository.deleteById(id);
		return find;
	}

	public ProductDto findById(Integer id) {
		ProductEntity findEntity = this.productRepository.findById(id).orElseThrow();
		ProductDto result = (ProductDto) new ProductDto().copyMembers(findEntity, true);
		return result;
	}

	public Slice<ProductDto> findByNameContains(String name, Pageable pageable) {
		Slice<ProductEntity> find = this.productRepository.findByNameContains(name, pageable);
		// Slice<ProductEntity> => Slice<ProductDto> 변환해서 리턴하는게 비고적 데이터가 안전해서 좋다.
		List<ProductDto> list = find.getContent().stream()
				.map(item -> {
					ProductDto convert = (ProductDto) new ProductDto().copyMembers(item, true);
					return convert;
				}).toList();
		Slice<ProductDto> result = new SliceImpl<>(list, find.getPageable(), find.hasNext());
		return result;
	}

	List<ProductDto> findByPriceGreaterThan(Integer price) {
		List<ProductEntity> list = this.productRepository.findByPriceGreaterThanOrderByIdDesc(price);
		List<ProductDto> result = list.stream()
				.map( item -> {
					ProductDto convert = (ProductDto) new ProductDto().copyMembers(item, true);
					return convert;
				}).toList();
		return result;
	}

	Page<ProductDto> findByCategoryEntity(CategoryDto category, Pageable pageable) {
		CategoryEntity categoryEntity = (CategoryEntity) new CategoryEntity().copyMembers(category, true);
		Page<ProductEntity> find = this.productRepository.findByCategoryEquals(categoryEntity, pageable);
		List<ProductDto> list = find.getContent().stream()
				.map( item -> {
					ProductDto convert = (ProductDto) new ProductDto().copyMembers(item, true);
					return convert;
				}).toList();
		Page<ProductDto> result = new PageImpl<>(list, find.getPageable(), find.getTotalElements());
		return result;
	}
}
