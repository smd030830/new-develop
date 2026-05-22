package com.mjc813.cafe_kios.models.product;

import com.mjc813.cafe_kios.models.category.ICategory;

public interface IProductA {
	Integer getId();
	void setId(Integer id);

	String getName();
	void setName(String name);

	Integer getPrice();
	void setPrice(Integer price);

	Integer getCategoryId();
	void setCategoryId(Integer categoryId);

	ICategory getCategoryObj();
	void setCategoryObj(ICategory categoryObj);

	String getPicture();
	void setPicture(String picture);

	default IProductA copyMembers(IProductA src) {
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
			if ( src.getCategoryId() != null ) {
				this.setCategoryId(src.getCategoryId());
			}
			if ( src.getCategoryObj() != null) {
				this.getCategoryObj().copyMembers(src.getCategoryObj());
			}
			if ( src.getPicture() != null ) {
				this.setPicture(src.getPicture());
			}
		}
		return this;
	}
}
