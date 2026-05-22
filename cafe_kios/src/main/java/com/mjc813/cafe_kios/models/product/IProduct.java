package com.mjc813.cafe_kios.models.product;

import com.mjc813.cafe_kios.models.category.ICategory;

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

	default IProduct copyMembers(IProduct src) {
		if ( src != null ) {
			if ( src.getId() != null ) {
				this.setId(src.getId());
			}
			if ( src.getName() != null ) {
				this.setName(src.getName());
			}
			if ( src.getPrice() != null ) {
				this.setPrice(src.getPrice());
			}
			if ( src.getCategory() != null) {
				this.getCategory().copyMembers(src.getCategory());
			}
			if ( src.getPicture() != null ) {
				this.setPicture(src.getPicture());
			}
		}
		return this;
	}
}
