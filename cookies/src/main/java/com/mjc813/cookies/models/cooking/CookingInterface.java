package com.mjc813.cookies.models.cooking;

import com.mjc813.cookies.models.common.IdName;

public interface CookingInterface {
	Long getId();
	void setId(Long id);

	String getDescription();
	void setDescription(String description);
	
	Long getCookieId();
	void setCookieId(Long cookieId);

	IdName getCookie();
	void setCookie(IdName cookie);

	default CookingInterface copyMembers(CookingInterface src, boolean forced) {
		if ( src == null ) {
			return this;
		}
		if ( forced || src.getId() != null ) {
			this.setId(src.getId());
		}
		if ( forced || src.getDescription() != null ) {
			this.setDescription(src.getDescription());
		}
		if ( forced || src.getCookieId() != null ) {
			this.setCookieId(src.getCookieId());
			this.getCookie().copyMembers(src.getCookie(), forced);
		}
		return this;
	}
}
