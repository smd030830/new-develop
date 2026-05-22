package com.mjc813.cafe_kiosk.models.category;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "category") // @Entity 는 JPA 에서 CRUD 를 할 수 있는 객체, 테이블
public class CategoryEntity implements ICategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", length = 50, nullable = false)
	private String name;
}
