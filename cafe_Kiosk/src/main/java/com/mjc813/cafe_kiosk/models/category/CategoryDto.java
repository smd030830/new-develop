package com.mjc813.cafe_kiosk.models.category;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto implements ICategory {
	private Integer id;
	private String name;
}
