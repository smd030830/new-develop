package com.mjc813.petapp.pet.dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "pet_tbl")
public class PetEntity implements IPet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 50, nullable = false)
	private String name;

	@Column(length = 30, nullable = false)
	private String species;

	@Column(length = 100, nullable = false)
	private String breed;

	@Column(length = 1, nullable = false)
	private String gender;

	@Column
	private LocalDate birth;

	@Column(name="img_file", length = 500, nullable = true)
	private String imgFile;
}
