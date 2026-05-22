package com.mjc813.petapp.pet.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PetDto implements IPet {
	private Integer id;
	private String name;
	private String species;
	private String breed;
	private String gender;
	private LocalDate birth;
	private String imgFile;
}
