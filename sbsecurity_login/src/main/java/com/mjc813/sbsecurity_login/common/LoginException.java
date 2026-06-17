package com.mjc813.sbsecurity_login.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LoginException extends Exception {
	private String message;
}
