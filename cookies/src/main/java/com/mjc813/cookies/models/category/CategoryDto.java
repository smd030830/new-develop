package com.mjc813.cookies.models.category;

import com.mjc813.cookies.models.common.IdName;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto implements IdName {
	private Long id;
	private String name;
}
