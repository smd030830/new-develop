package com.mjc813.cafe_kios.models.product;

import com.mjc813.cafe_kios.models.category.CategoryDto;
import com.mjc813.cafe_kios.models.category.ICategory;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto implements IProduct {
	private Integer id;
	private String name;
	private Integer price;
	private CategoryDto category = new CategoryDto();
	private String picture;

	public void setCategory(ICategory category) {
		if ( category == null ) {
			return;
		}
		if ( this.category == null ) {
			// Dto 와 Entity 형 변환시에 Integer <=> 객체
			this.category = new CategoryDto();
		}
		this.category.copyMembers(category);
	}
}
