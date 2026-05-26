package com.mjc813.login_cookie.models.member;

import java.time.LocalDateTime;

public interface IMember {
	Long getId();
	void setId(Long id);

	String getSignId();
	void setSignId(String signId);

	String getPassword();
	void setPassword(String password);

	String getEmail();
	void setEmail(String email);

	String getRole();
	void setRole(String role);

	Boolean getIsValidEmail();
	void setIsValidEmail(Boolean isValidEmail);

	String getValidText();
	void setValidText(String validText);

	LocalDateTime getCreateDt();
	void setCreateDt(LocalDateTime createDt);

	LocalDateTime getUpdateDt();
	void setUpdateDt(LocalDateTime updateDt);

	LocalDateTime getDeleteDt();
	void setDeleteDt(LocalDateTime deleteDt);

	default IMember clone(IMember source, boolean bForced) {
		if ( source == null ) {
			return this;
		}
		if ( bForced || source.getId() != null ) {
			this.setId(source.getId());
		}
		if ( bForced || source.getSignId() != null ) {
			this.setSignId(source.getSignId());
		}
		if ( bForced || source.getPassword() != null ) {
			this.setPassword(source.getPassword());
		}
		if ( bForced || source.getEmail() != null ) {
			this.setEmail(source.getEmail());
		}
		if ( bForced || source.getRole() != null ) {
			this.setRole(source.getRole());
		}
		if ( bForced || source.getIsValidEmail() != null ) {
			this.setIsValidEmail(source.getIsValidEmail());
		}
		if ( bForced || source.getValidText() != null ) {
			this.setValidText(source.getValidText());
		}
		if ( bForced || source.getCreateDt() != null ) {
			this.setCreateDt(source.getCreateDt());
		}
		if ( bForced || source.getUpdateDt() != null ) {
			this.setUpdateDt(source.getUpdateDt());
		}
		if ( bForced || source.getDeleteDt() != null ) {
			this.setDeleteDt(source.getDeleteDt());
		}
		return this;
	}
}
