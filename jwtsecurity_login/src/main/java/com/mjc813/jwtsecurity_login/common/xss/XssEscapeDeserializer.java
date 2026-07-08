//package com.mjc813.jwtsecurity_login.common.xss;
//
//import io.jsonwebtoken.io.IOException;
//import org.springframework.boot.jackson.JacksonComponent;
//import org.springframework.web.util.HtmlUtils;
//import tools.jackson.core.JsonParser;
//import tools.jackson.databind.DeserializationContext;
//import tools.jackson.databind.deser.std.StdDeserializer;
//
///**
// * 이 방법은 스프링부트4만 가능하며 모든 String 멤버변수에 적용하므로 속도가 느리다.
// */
//@JacksonComponent
//public class XssEscapeDeserializer extends StdDeserializer<String> {
//	public XssEscapeDeserializer() {
//		super(String.class);
//	}
//
//	@Override
//	public String deserialize(JsonParser p, DeserializationContext ctxt)
//			throws IOException {
//		String value = p.getValueAsString();
//		if (value == null) {
//			return null;
//		}
//		return HtmlUtils.htmlEscape(value); // 자동 이스케이프
//	}
//}
