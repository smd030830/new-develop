package com.mjc813.cookies.models.ingredient;

import com.mjc813.cookies.models.category.CategoryEntity;
import com.mjc813.cookies.models.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
	@Autowired
	private  IngredientRepository ingredientRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	public IngredientDto insert(IngredientDto insertDto) {
		IngredientEntity newEntity = (IngredientEntity)new IngredientEntity().copyMembers(insertDto, true);
		newEntity.setId(null);
		IngredientEntity save = this.ingredientRepository.save(newEntity);
		IngredientDto result = (IngredientDto)new IngredientDto().copyMembers(save, true);
		return result;
	}

	public IngredientDto update(IngredientDto updateDto) {
		IngredientEntity find = this.ingredientRepository.findById(updateDto.getId()).orElseThrow();
		IngredientEntity updateEntity = (IngredientEntity)new IngredientEntity().copyMembers(find, true);
		updateEntity.copyMembers(updateDto, false);
		IngredientEntity save = this.ingredientRepository.save(updateEntity);
		IngredientDto result = (IngredientDto)new IngredientDto().copyMembers(save, true);
		return result;
	}

	public IngredientDto findById(Long id) {
		IngredientEntity find = this.ingredientRepository.findById(id).orElseThrow();
		IngredientDto result = (IngredientDto)new IngredientDto().copyMembers(find, true);
		return result;
	}

	public IngredientDto deleteById(Long id) {
		IngredientDto find = this.findById(id);
		this.ingredientRepository.deleteById(id);
		return find;
	}

	public Slice<IngredientDto> findAllByNameContaining(String name, Pageable pageable) {
		Slice<IngredientEntity> slc = this.ingredientRepository.findAllByNameContaining(name, pageable);
		List<IngredientDto> list = slc.getContent().stream()
				.map( x -> (IngredientDto) new IngredientDto().copyMembers(x, true))
				.toList();
		Slice<IngredientDto> result = new SliceImpl<>(list, slc.getPageable(), slc.hasNext());
		return result;
	}

	public Slice<IngredientDto> findAllByCategoryEquals(Long categoryId, Pageable pageable) {
		CategoryEntity category = categoryRepository.findById(categoryId).orElseThrow();
		Slice<IngredientEntity> slc = this.ingredientRepository.findAllByCategoryEquals(category, pageable);
		List<IngredientDto> list = slc.getContent().stream()
				.map( x -> (IngredientDto) new IngredientDto().copyMembers(x, true))
				.toList();
		Slice<IngredientDto> result = new SliceImpl<>(list, slc.getPageable(), slc.hasNext());
		return result;
	}
}
