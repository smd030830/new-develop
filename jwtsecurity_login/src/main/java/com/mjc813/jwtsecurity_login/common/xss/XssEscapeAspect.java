package com.mjc813.jwtsecurity_login.common.xss;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.util.HtmlUtils;

@Aspect
@Component
public class XssEscapeAspect {

	// @RequestBody, @ModelAttribute 를 가진 컨트롤러 메서드 전체에 적용
	@Around("""
            @within(org.springframework.web.bind.annotation.RestController) ||
            @within(org.springframework.stereotype.Controller)
            """)
	public Object escapeXss(ProceedingJoinPoint joinPoint) throws Throwable {
		for (Object arg : joinPoint.getArgs()) {
			if (arg != null) {
				processFields(arg);  // ← 같은 클래스의 private 메서드 호출
			}
		}
		return joinPoint.proceed();
	}

	// ↓ 같은 클래스 안에 위치
	private void processFields(Object obj) {
		if (obj == null || isSkippable(obj.getClass())) {
			return;
		}

		ReflectionUtils.doWithFields(
				obj.getClass(),
				field -> {
					field.setAccessible(true);
					Object value = field.get(obj);

					if (field.isAnnotationPresent(XssEscape.class)
							&& field.getType() == String.class) {

						if (value != null) {
							field.set(obj, HtmlUtils.htmlEscape((String) value));
						}

					} else if (value != null && !isSkippable(field.getType())) {
						processFields(value);  // 중첩 객체 재귀 호출
					}
				}
		);
	}

	// ↓ 같은 클래스 안에 위치
	private boolean isSkippable(Class<?> clazz) {
		return clazz.isPrimitive()
				|| clazz.isEnum()
				|| clazz.getName().startsWith("java.")
				|| clazz.getName().startsWith("javax.")
				|| clazz.getName().startsWith("jakarta.");
	}
}
