package com.mjc813.cookies.models.ingredient;

import com.mjc813.cookies.models.category.CategoryEntity;
import com.mjc813.cookies.models.common.IdName;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "ingredient")
@NamedEntityGraph(name="IngredientEntity.fetchCategory", attributeNodes = {
		@NamedAttributeNode(value="category")
})
public class IngredientEntity implements IngredientInterface {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 30, nullable = false, unique = true)
	private String name;

	@Transient // JPA 에 테이블을 어떤 컬럼에도 연결 시키지 않는다.
	private Long categoryId;

	@JoinColumn(name = "category_id", nullable = false) // 외래키가 저장되는 컬럼이름, 마스터테이블 Entity
	@ManyToOne(fetch = FetchType.EAGER)  // FetchType.LAZY = SELECT 여러문장을 실행, EAGER = JOIN 을 실행
	private CategoryEntity category;

	@Override
	public Long getCategoryId() {
		if ( this.category == null ) {
			this.category = new CategoryEntity();
		}
		if ( this.category.getId() != null) {
			this.categoryId = this.category.getId();
		}
		return this.category.getId();
	}

	@Override
	public void setCategoryId(Long categoryId) {
		// Long 외래키값 과 객체.기본키 값을 항상 같도록 해야 한다.
		if ( this.category == null ) {
			this.category = new CategoryEntity();
		}
		this.category.setId(categoryId);
		this.categoryId = categoryId;
	}

	@Override
	public void setCategory(IdName category) {
		// Long 외래키값 과 객체.기본키 값을 항상 같도록 해야 한다.
		if ( category == null ) {
			return;
		}
		if ( this.category == null ) {
			this.category = new CategoryEntity();
		}
		this.category.copyMembers(category, true);
		this.categoryId = category.getId();
	}
}
