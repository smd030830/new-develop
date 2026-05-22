package com.mjc813.cookies.models.recipe;

import com.mjc813.cookies.models.ingredient.IngredientDto;
import com.mjc813.cookies.models.cookie.CookieDto;
import com.mjc813.cookies.models.common.IdName;
import com.mjc813.cookies.models.cookie.CookieDto;
import com.mjc813.cookies.models.ingredient.IngredientDto;
import com.mjc813.cookies.models.ingredient.IngredientInterface;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeDto implements RecipeInterface {
	private Long id;
	private Long cookieId;
	private CookieDto cookie;
	private Long ingredientId;
	private IngredientDto ingredient;
	private RecipeUnit unit;
	private BigDecimal weight;

	@Override
	public Long getCookieId() {
		// Long cookieId 랑 cookie.getId() 랑 값이 항상 같도록 해야 한다.
		if ( this.cookie == null ) {
			this.cookie = new CookieDto();
		}
		this.cookie.setId(this.cookieId);
		return this.cookie.getId();
	}

	@Override
	public void setCookieId(Long cookieId) {
		// Long cookieId 랑 cookie.getId() 랑 값이 항상 같도록 해야 한다.
		if ( this.cookie == null ) {
			this.cookie = new CookieDto();
		}
		this.cookie.setId(cookieId);
		this.cookieId = cookieId;
	}

	@Override
	public void setCookie(IdName cookie) {
		// Long cookieId 랑 cookie.getId() 랑 값이 항상 같도록 해야 한다.
		if ( cookie == null ) {
			return;
		}
		if ( this.cookie == null ) {
			this.cookie = new CookieDto();
		}
		this.cookie.copyMembers(cookie, true);
		this.cookieId = cookie.getId();
	}


	@Override
	public Long getIngredientId() {
		// Long ingredientId 랑 ingredient.getId() 랑 값이 항상 같도록 해야 한다.
		if ( this.ingredient == null ) {
			this.ingredient = new IngredientDto();
		}
		this.ingredient.setId(this.ingredientId);
		return this.ingredient.getId();
	}

	@Override
	public void setIngredientId(Long ingredientId) {
		// Long ingredientId 랑 ingredient.getId() 랑 값이 항상 같도록 해야 한다.
		if ( this.ingredient == null ) {
			this.ingredient = new IngredientDto();
		}
		this.ingredient.setId(ingredientId);
		this.ingredientId = ingredientId;
	}

	@Override
	public void setIngredient(IngredientInterface ingredient) {
		// Long ingredientId 랑 ingredient.getId() 랑 값이 항상 같도록 해야 한다.
		if ( ingredient == null ) {
			return;
		}
		if ( this.ingredient == null ) {
			this.ingredient = new IngredientDto();
		}
		this.ingredient.copyMembers(ingredient, true);
		this.ingredientId = ingredient.getId();
	}
}
