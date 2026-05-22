package com.mjc813.petapp.pet;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PetRequestDto {
	private Integer rowCount;
	private String searchName;
}
