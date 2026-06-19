package com.mjc813.jwtsecurity_login.common;

import lombok.Getter;

@Getter
public enum ResponseCode {
	SUCCESS(10000),
	INSERT_ERROR(41000),
	UPDATE_ERROR(42000),
	DELETE_ERROR(43000),
	SELECT_ERROR(44000),
	SERVER_ERROR(49000),
	DATA_NOT_FOUND_ERROR(51000),
	AUTHENTICATION_ERROR(52000), // 로그인 인증 에러
	AUTHORIZATION_ERROR(53000); // 자원을 사용하기 위한 권한 인가 에러

	private Integer code;

	ResponseCode(Integer code) {
		this.code = code;
	}
}
