package com.mjc813.cafe_kios.models.category;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDto implements ICategory {
	private Integer id;
	private String name;
}
