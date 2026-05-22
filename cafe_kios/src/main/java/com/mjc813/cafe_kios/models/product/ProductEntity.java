package com.mjc813.cafe_kios.models.product;

import com.mjc813.cafe_kios.models.category.CategoryEntity;
import com.mjc813.cafe_kios.models.category.ICategory;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "product")
public class ProductEntity implements IProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length=50, nullable=false)
	private String name;

	@Column(nullable = false)
	private Integer price;

	@JoinColumn(name="category_id", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private CategoryEntity category = new CategoryEntity();  // 참조테이블의 외래키는 마스터테이블의 Entity 클래스

	@Column(length=500, nullable = true)
	private String picture;

	@Override
	public void setCategory(ICategory category) {
		if ( category == null ) {
			return;
		}
		if ( this.category == null ) {
			// Dto 와 Entity 형 변환시에 Integer <=> 객체
			this.category = new CategoryEntity();
		}
		this.category.copyMembers(category);
	}
}
