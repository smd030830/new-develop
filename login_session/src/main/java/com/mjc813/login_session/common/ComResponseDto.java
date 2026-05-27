package com.mjc813.login_session.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComResponseDto<T> {
	private Integer code;
	private String message;
	private T data;

	public static <T> ComResponseDto<T> make(ResponseCode resCode, T data) {
		return new ComResponseDto<T>(resCode.getCode(), resCode.name(), data);
	}
}
