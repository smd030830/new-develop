package com.mjc813.login_session.models.music;

import java.time.LocalDateTime;

public interface IMusic {
	Long getId();
	void setId(Long id);

	String getTitle();
	void setTitle(String title);

	String getArtist();
	void setArtist(String artist);

	String getPlaytime();
	void setPlaytime(String playtime);

	String getCreateId();
	void setCreateId(String createId);

	LocalDateTime getCreateDt();
	void setCreateDt(LocalDateTime createDt);

	String getUpdateId();
	void setUpdateId(String updateId);

	LocalDateTime getUpdateDt();
	void setUpdateDt(LocalDateTime updateDt);

	String getDeleteId();
	void setDeleteId(String deleteId);

	LocalDateTime getDeleteDt();
	void setDeleteDt(LocalDateTime deleteDt);

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
		if ( forced || source.getCreateId() != null ) {
			this.setCreateId(source.getCreateId());
		}
		if ( forced || source.getCreateDt() != null ) {
			this.setCreateDt(source.getCreateDt());
		}
		if ( forced || source.getUpdateId() != null ) {
			this.setUpdateId(source.getUpdateId());
		}
		if ( forced || source.getUpdateDt() != null ) {
			this.setUpdateDt(source.getUpdateDt());
		}
		if ( forced || source.getDeleteId() != null ) {
			this.setDeleteId(source.getDeleteId());
		}
		if ( forced || source.getDeleteDt() != null ) {
			this.setDeleteDt(source.getDeleteDt());
		}
		return this;
	}
}
