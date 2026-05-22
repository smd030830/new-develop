package com.mjc813.cookies.models.ingredient;

import com.mjc813.cookies.models.common.IdName;

public interface IngredientInterface extends IdName {
	Long getCategoryId();
	void setCategoryId(Long categoryId);

	IdName getCategory();
	void setCategory(IdName category);

	default IngredientInterface copyMembers(IngredientInterface src, boolean forced) {
		if ( src == null ) {
			return this;
		}
		IdName.super.copyMembers(src, forced);
		if ( forced || src.getCategoryId() != null ) {
			this.setCategoryId(src.getCategoryId());
			this.getCategory().copyMembers(src.getCategory(), forced);
		}
		return this;
	}
}
