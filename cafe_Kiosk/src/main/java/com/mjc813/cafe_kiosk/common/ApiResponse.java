package com.mjc813.cafe_kiosk.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ApiResponse<T> {
	private CafeResponse code;
	private String message;
	private T resultData;

	public static <T> ApiResponse<T> make(CafeResponse code, String message, T resultData) {
		return ApiResponse.<T>builder()
				.code(code)
				.message(message)
				.resultData(resultData)
				.build();
	}
}
