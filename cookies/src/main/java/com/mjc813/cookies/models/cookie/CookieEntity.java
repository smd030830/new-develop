package com.mjc813.cookies.models.cookie;

import com.mjc813.cookies.models.common.IdName;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "cookie")
public class CookieEntity implements IdName {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 30, nullable = false, unique = true)
	private String name;
}
