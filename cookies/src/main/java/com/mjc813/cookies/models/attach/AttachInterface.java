package com.mjc813.cookies.models.attach;

import com.mjc813.cookies.models.common.IdName;

public interface AttachInterface {
	Long getId();
	void setId(Long id);

	String getFileName();
	void setFileName(String fileName);

	Integer getSize();
	void setSize(Integer size);

	String getExt();
	void setExt(String ext);

	String getStoreName();
	void setStoreName(String storeName);

	String getPath();
	void setPath(String path);

	Long getCookieId();
	void setCookieId(Long cookieId);

	IdName getCookie();
	void setCookie(IdName cookie);

	default AttachInterface copyMembers(AttachInterface src, boolean forced) {
		if ( src == null ) {
			return this;
		}
		if ( forced || src.getId() != null ) {
			this.setId(src.getId());
		}
		if ( forced || src.getFileName() != null ) {
			this.setFileName(src.getFileName());
		}
		if ( forced || src.getSize() != null ) {
			this.setSize(src.getSize());
		}
		if ( forced || src.getExt() != null ) {
			this.setExt(src.getExt());
		}
		if ( forced || src.getPath() != null ) {
			this.setPath(src.getPath());
		}
		if ( forced || src.getStoreName() != null ) {
			this.setStoreName(src.getStoreName());
		}
		if ( forced || src.getCookieId() != null ) {
			this.setCookieId(src.getCookieId());
			this.getCookie().copyMembers(src.getCookie(), forced);
		}
		return this;
	}
}
