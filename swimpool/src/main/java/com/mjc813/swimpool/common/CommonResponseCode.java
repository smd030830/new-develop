package com.mjc813.swimpool.common;

public enum CommonResponseCode {
	C0000("success"),
	F0001("request failed"),
	F0002("server process error");

	private final String message;

	CommonResponseCode(String msg) {
		this.message = msg;
	}

	public String getMessage() {
		return this.message;
	}
}
