package com.mjc813.cookies.models.recipe;

import com.mjc813.cookies.models.common.IdName;
import com.mjc813.cookies.models.cookie.CookieEntity;
import com.mjc813.cookies.models.ingredient.IngredientEntity;
import com.mjc813.cookies.models.ingredient.IngredientInterface;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "recipe")
@NamedEntityGraph(name = "RecipeEntity.fetchCookieIngredient", attributeNodes = {
		@NamedAttributeNode(value = "cookie"),
		@NamedAttributeNode(value = "ingredient", subgraph = "RecipeIngredient.fetchCategory")
},
		subgraphs = {
				@NamedSubgraph(name = "RecipeIngredient.fetchCategory", attributeNodes = {
						@NamedAttributeNode(value = "category")})
		}
)
public class RecipeEntity implements RecipeInterface {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Transient
	private Long cookieId;
	@JoinColumn(name = "cookie_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private CookieEntity cookie;

	@Transient
	private Long ingredientId;
	@JoinColumn(name = "ingredient_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private IngredientEntity ingredient;

	@Column(name = "unit", length=5, nullable = false)
	private RecipeUnit unit;

	@Column(name = "weight", nullable = false, precision=8, scale=2)
	private BigDecimal weight;

	@Override
	public Long getCookieId() {
		if ( this.cookie == null ) {
			this.cookie = new CookieEntity();
		}
		if ( this.cookie.getId() != null) {
			this.cookieId = this.cookie.getId();
		}
		return this.cookie.getId();
	}

	@Override
	public void setCookieId(Long cookieId) {
		// Long 외래키값 과 객체.기본키 값을 항상 같도록 해야 한다.
		if ( this.cookie == null ) {
			this.cookie = new CookieEntity();
		}
		this.cookie.setId(cookieId);
		this.cookieId = cookieId;
	}

	@Override
	public void setCookie(IdName cookie) {
		// Long 외래키값 과 객체.기본키 값을 항상 같도록 해야 한다.
		if ( cookie == null ) {
			return;
		}
		if ( this.cookie == null ) {
			this.cookie = new CookieEntity();
		}
		this.cookie.copyMembers(cookie, true);
		this.cookieId = cookie.getId();
	}

	@Override
	public Long getIngredientId() {
		if ( this.ingredient == null ) {
			this.ingredient = new IngredientEntity();
		}
		if ( this.ingredient.getId() != null) {
			this.ingredientId = this.ingredient.getId();
		}
		return this.ingredient.getId();
	}

	@Override
	public void setIngredientId(Long ingredientId) {
		// Long 외래키값 과 객체.기본키 값을 항상 같도록 해야 한다.
		if ( this.ingredient == null ) {
			this.ingredient = new IngredientEntity();
		}
		this.ingredient.setId(ingredientId);
		this.ingredientId = ingredientId;
	}

	@Override
	public void setIngredient(IngredientInterface ingredient) {
		// Long 외래키값 과 객체.기본키 값을 항상 같도록 해야 한다.
		if ( ingredient == null ) {
			return;
		}
		if ( this.ingredient == null ) {
			this.ingredient = new IngredientEntity();
		}
		this.ingredient.copyMembers(ingredient, true);
		this.ingredientId = ingredient.getId();
	}
}
