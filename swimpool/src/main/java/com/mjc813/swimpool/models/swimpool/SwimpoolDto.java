package com.mjc813.swimpool.models.swimpool;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SwimpoolDto implements ISwimpool {
	private Long id;
	private String name;
	private String tel;
	private String addr1;
	private String addr2;
	private String lanes;
	private String size;
}
