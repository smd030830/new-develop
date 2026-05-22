package com.mjc813.cookies.models.recipe;

import com.mjc813.cookies.models.cookie.CookieEntity;
import com.mjc813.cookies.models.ingredient.IngredientEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
	private final RecipeRepository recipeRepository;

	public RecipeDto insert(RecipeDto insertDto) {
		RecipeEntity insertEntity = (RecipeEntity)new RecipeEntity().copyMembers(insertDto, true);
		insertEntity.setId(null);
		RecipeEntity save = this.recipeRepository.save(insertEntity);
		RecipeDto result = (RecipeDto)new RecipeDto().copyMembers(save, true);
		return result;
	}

	public RecipeDto findById(Long id) {
//		RecipeEntity findEntity = this.recipeRepository.findById(id).orElseThrow();
		RecipeEntity findEntity = this.recipeRepository.findJoinAllById(id).orElseThrow();
		RecipeDto result = (RecipeDto)new RecipeDto().copyMembers(findEntity, true);
		return result;
	}

	public RecipeDto update(RecipeDto updateDto) {
		RecipeEntity findEntity = this.recipeRepository.findById(updateDto.getId()).orElseThrow();
		RecipeEntity updateEntity = (RecipeEntity)new RecipeEntity().copyMembers(findEntity, true);
		updateEntity.copyMembers(updateDto, false);
		RecipeEntity save = this.recipeRepository.save(updateEntity);
		RecipeDto result = (RecipeDto)new RecipeDto().copyMembers(save, true);
		return result;
	}

	public RecipeDto deleteById(Long id) {
		RecipeDto result = this.findById(id);
		this.recipeRepository.deleteById(id);
		return result;
	}

	public Slice<RecipeDto> findAllByCookieEquals(Long cookieId, Pageable pageable) {
		CookieEntity cookie = CookieEntity.builder().id(cookieId).build();
		Slice<RecipeEntity> slice = this.recipeRepository.findAllByCookieEquals(cookie, pageable);
		return this.getSliceRecipeDto(slice);
	}

	public Slice<RecipeDto> findByIngredient(Long ingredientId, Pageable pageable) {
		IngredientEntity ingredient = IngredientEntity.builder().id(ingredientId).build();
		Slice<RecipeEntity> slice = this.recipeRepository.findByIngredient(ingredient, pageable);
		return this.getSliceRecipeDto(slice);
	}

	private Slice<RecipeDto> getSliceRecipeDto(Slice<RecipeEntity> slice) {
		List<RecipeDto> list = slice.getContent().stream()
				.map( item -> (RecipeDto)new RecipeDto().copyMembers(item, true))
				.toList();
		Slice<RecipeDto> result = new SliceImpl<>(list, slice.getPageable(), slice.hasNext());
		return result;
	}
}
