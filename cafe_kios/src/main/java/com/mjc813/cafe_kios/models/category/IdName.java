package com.mjc813.cafe_kios.models.category;

public interface IdName {
	Integer getId();
	void setId(Integer id);

	String getName();
	void setName(String name);

	default IdName copyMembers(IdName src) {
		if ( src != null ) {
			if ( src.getId() != null ) {
				this.setId(src.getId());
			}
			if ( src.getName() != null ) {
				this.setName(src.getName());
			}
		}
		return this;
	}
}
