package com.mjc813.login_cookie.models.music;

public interface IMusic {
	Long getId();
	void setId(Long id);

	String getTitle();
	void setTitle(String title);

	String getArtist();
	void setArtist(String artist);

	String getPlaytime();
	void setPlaytime(String playtime);

	default IMusic copyMembers(IMusic source, boolean forced) {
		if ( source == null ) {
			return this;
		}
		if ( forced || source.getId() != null ) {
			this.setId(source.getId());
		}
		if ( forced || source.getTitle() != null ) {
			this.setTitle(source.getTitle());
		}
		if ( forced || source.getArtist() != null ) {
			this.setArtist(source.getArtist());
		}
		if ( forced || source.getPlaytime() != null ) {
			this.setPlaytime(source.getPlaytime());
		}
		return this;
	}
}
