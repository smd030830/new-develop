package com.mjc813.cookies.models.recipe;

import com.mjc813.cookies.models.cookie.CookieEntity;
import com.mjc813.cookies.models.ingredient.IngredientEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
	@EntityGraph(attributePaths = {"cookie", "ingredient", "ingredient.category"})
	Optional<RecipeEntity> findJoinAllById(Long id);

	@EntityGraph(attributePaths = {"cookie", "ingredient", "ingredient.category"})
	Slice<RecipeEntity> findAllByCookieEquals(CookieEntity cookie, Pageable pageable);

	@EntityGraph(value = "RecipeEntity.fetchCookieIngredient")
	Slice<RecipeEntity> findByIngredient(IngredientEntity ingredient, Pageable pageable);
	/*
SELECT
	r.id
	, r.cookie_id
	, c.name as cookie_name
	, i.category_id
	, ca.name AS category_name
	, r.ingredient_id
	, i.name AS ingredient_name
	, r.unit
	, r.weight
FROM recipe AS r
INNER JOIN cookie AS c ON r.cookie_id = c.id
INNER JOIN ingredient AS i ON r.ingredient_id = i.id
INNER JOIN category as ca ON i.category_id = ca.id
	 */
}
