package com.mjc813.cookies.models.category;

import com.mjc813.cookies.models.common.IdName;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "category")
public class CategoryEntity implements IdName {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length=15, nullable = false, unique = true)
	private String name;
}
