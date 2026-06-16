package com.mjc813.login_spring_security.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mjc813Exception extends Exception {
	private ResponseCode code;
	private String message;
}
