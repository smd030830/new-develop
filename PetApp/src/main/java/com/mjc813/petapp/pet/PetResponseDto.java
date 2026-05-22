package com.mjc813.petapp.pet;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PetResponseDto {
	private Integer code;
	private String message;
	private Object data;
}
