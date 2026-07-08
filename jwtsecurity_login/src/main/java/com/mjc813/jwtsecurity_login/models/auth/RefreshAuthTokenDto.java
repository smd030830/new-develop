package com.mjc813.jwtsecurity_login.models.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class RefreshAuthTokenDto extends AuthTokenDto {
	private String signId;

	public RefreshAuthTokenDto(String signId, String accessToken, String refreshToken) {
		super(accessToken, refreshToken);
		this.signId = signId;
	}
}
