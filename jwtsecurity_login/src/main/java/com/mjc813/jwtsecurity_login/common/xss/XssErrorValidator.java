package com.mjc813.jwtsecurity_login.common.xss;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class XssErrorValidator implements ConstraintValidator<XssError, String> {
	private static final Pattern XSS_PATTERN =
			Pattern.compile("<[^>]+>|javascript:|on\\w+\\s*=", Pattern.CASE_INSENSITIVE);

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) return true;
		return !XSS_PATTERN.matcher(value).find();
	}
}
