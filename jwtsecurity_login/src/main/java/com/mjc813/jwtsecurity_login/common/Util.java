package com.mjc813.jwtsecurity_login.common;

import java.util.Random;

public class Util {
	public static String getRandomAlpha(int length) {
		String sample = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		return getRandomString(sample, length);
	}

	public static String getRandomNumeric(int length) {
		String sample = "0123456789";
		return getRandomString(sample, length);
	}

	public static String getRandomAllString(int length) {
		String sample = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!";
		return getRandomString(sample, length);
	}

	private static String getRandomString(String sample, int length) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(sample.charAt(random.nextInt(sample.length())));
		}
		return sb.toString();
	}
}
