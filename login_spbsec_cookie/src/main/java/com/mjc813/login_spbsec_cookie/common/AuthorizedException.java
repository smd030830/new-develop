package com.mjc813.login_spbsec_cookie.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorizedException extends Exception {
	private String message;
}
