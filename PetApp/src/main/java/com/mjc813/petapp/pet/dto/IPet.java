package com.mjc813.petapp.pet.dto;

import lombok.Generated;

import java.time.LocalDate;

public interface IPet {

	Integer getId();

	String getName();

	String getSpecies();

	String getBreed();

	String getGender();

	LocalDate getBirth();

	String getImgFile();

	void setId(final Integer id);

	void setName(final String name);

	void setSpecies(final String species);

	void setBreed(final String breed);

	void setGender(final String gender);

	void setBirth(final LocalDate birth);

	void setImgFile(final String imgFile);

	default void copyMemberValue(final IPet src) {
		if( src == null) {
			return;
		}
		if ( src.getId() != null) {
			this.setId(src.getId());
		}
		if ( src.getName() != null) {
			this.setName(src.getName());
		}
		if ( src.getBirth() != null) {
			this.setBirth(src.getBirth());
		}
		if ( src.getBreed() != null) {
			this.setBreed(src.getBreed());
		}
		if ( src.getGender() != null) {
			this.setGender(src.getGender());
		}
		if ( src.getSpecies() != null) {
			this.setSpecies(src.getSpecies());
		}
		if ( src.getImgFile() != null) {
			this.setImgFile(src.getImgFile());
		}
	}
}
