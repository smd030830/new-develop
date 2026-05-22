package com.mjc813.cookies.models.cookie;

import com.mjc813.cookies.models.common.IdName;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CookieDto implements IdName {
	private Long id;
	private String name;
}
