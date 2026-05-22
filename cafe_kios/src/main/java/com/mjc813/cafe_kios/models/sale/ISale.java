package com.mjc813.cafe_kios.models.sale;

import java.time.LocalDateTime;

public interface ISale {
	Integer getId();
	void setId(Integer id);

//	Integer getProductId();
//	void setProductId(Integer productId);

	Integer getQty();
	void setQty(Integer qty);

	Integer getPrice();
	void setPrice(Integer price);

	LocalDateTime getSaleTime();
	void setSaleTime(LocalDateTime saleTime);

	default ISale copyMembers(ISale src) {
		if ( src != null ) {
			if ( src.getId() != null ) {
				setId(src.getId());
			}
//			if ( src.getProductId() != null ) {
//				setProductId(src.getProductId());
//			}
			if ( src.getQty() != null ) {
				setQty(src.getQty());
			}
			if ( src.getPrice() != null ) {
				setPrice(src.getPrice());
			}
			if ( src.getSaleTime() != null ) {
				setSaleTime(src.getSaleTime());
			}
		}
		return this;
	}
}
