package com.mjc813.cafe_kiosk.models.product;

import com.mjc813.cafe_kiosk.models.category.ICategory;

public interface IProduct {
	Integer getId();
	void setId(Integer id);

	String getName();
	void setName(String name);

	Integer getPrice();
	void setPrice(Integer price);

	ICategory getCategory();
	void setCategory(ICategory category);

	String getPicture();
	void setPicture(String picture);

	default IProduct copyMembers(IProduct source, Boolean forced) {
		if (source == null) {
			return this;
		}
		if ( forced || source.getId() != null ) {
			this.setId(source.getId());
		}
		if ( forced || source.getName() != null ) {
			this.setName(source.getName());
		}
		if ( forced || source.getPrice() != null ) {
			this.setPrice(source.getPrice());
		}
		if ( forced || source.getPicture() != null ) {
			this.setPicture(source.getPicture());
		}
		if ( forced || source.getCategory() != null ) {
			this.getCategory().copyMembers(source.getCategory(), forced);
		}
		return this;
	}
}
