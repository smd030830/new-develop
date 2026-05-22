package com.mjc813.cafe_kiosk.models.category;

public interface ICategory {
	Integer getId();
	void setId(Integer id);

	String getName();
	void setName(String name);

	default ICategory copyMembers(ICategory source, Boolean forced) {
		if (source == null) {
			return this;
		}
		if ( forced || source.getId() != null ) {
			this.setId(source.getId());
		}
		if ( forced || source.getName() != null ) {
			this.setName(source.getName());
		}
		return this;
	}
}
