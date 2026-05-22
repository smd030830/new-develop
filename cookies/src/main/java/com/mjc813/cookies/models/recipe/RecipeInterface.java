package com.mjc813.cookies.models.recipe;

import com.mjc813.cookies.models.common.IdName;
import com.mjc813.cookies.models.ingredient.IngredientInterface;

import java.math.BigDecimal;

public interface RecipeInterface {
	Long getId();
	void setId(Long id);

	Long getCookieId();
	void setCookieId(Long cookiesId);

	IdName getCookie();
	void setCookie(IdName cookie);

	Long getIngredientId();
	void setIngredientId(Long ingredientId);

	IngredientInterface getIngredient();
	void setIngredient(IngredientInterface ingredient);

	RecipeUnit getUnit();
	void setUnit(RecipeUnit unit);

	BigDecimal getWeight();
	void setWeight(BigDecimal weight);

	default RecipeInterface copyMembers(RecipeInterface src, boolean forced) {
		if ( src == null ) {
			return this;
		}
		if ( forced || src.getId() != null ) {
			this.setId(src.getId());
		}
		if ( forced || src.getCookieId() != null ) {
			this.setCookieId(src.getCookieId());
			this.getCookie().copyMembers(src.getCookie(), forced);
		}
		if ( forced || src.getIngredientId() != null ) {
			this.setIngredientId(src.getIngredientId());
			this.getIngredient().copyMembers(src.getIngredient(), forced);
		}
		if ( forced || src.getUnit() != null ) {
			this.setUnit(src.getUnit());
		}
		if ( forced || src.getWeight() != null ) {
			this.setWeight(src.getWeight());
		}
		return this;
	}
}
