package com.mjc813.jwtsecurity_login.models.redismember;

import com.mjc813.jwtsecurity_login.models.member.IMember;

public interface IRedisMember extends IMember {
	String getAccessToken();
	void setAccessToken(String accessToken);

	String getRefreshToken();
	void setRefreshToken(String refreshToken);

	default IRedisMember clone(IRedisMember source, boolean bForced) {
		if ( source == null ) {
			return this;
		}
		IMember.super.clone(source, bForced);
		if ( bForced || source.getAccessToken() != null ) {
			this.setAccessToken(source.getAccessToken());
		}
		if ( bForced || source.getRefreshToken() != null ) {
			this.setRefreshToken(source.getRefreshToken());
		}
		return this;
	}
}
