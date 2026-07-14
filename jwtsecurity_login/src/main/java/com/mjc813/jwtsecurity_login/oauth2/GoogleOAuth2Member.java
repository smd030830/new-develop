package com.mjc813.jwtsecurity_login.oauth2;

import com.mjc813.jwtsecurity_login.models.member.MemberDto;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class GoogleOAuth2Member implements IOAuth2Member {
	private final Map<String, Object> attributes;

	@Override
	public String getOauth2Id() {
		return this.attributes.get("sub").toString();
	}

	@Override
	public String getEmail() {
		return this.attributes.get("email").toString();
	}

	@Override
	public String getName() {
		return this.attributes.get("name").toString();
	}

	@Override
	public String getPictureUrl() {
		return this.attributes.get("picture").toString();
	}
}
