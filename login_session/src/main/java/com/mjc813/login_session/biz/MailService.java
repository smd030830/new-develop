package com.mjc813.login_session.biz;

import com.mjc813.login_session.models.member.MemberDto;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class MailService {
	private final String fromEmail = "softagape@gmail.com";

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private Mustache.Compiler mustacheCompiler;
	@Autowired
	private ResourceLoader resourceLoader;

	public void sendHtmlEmail(MemberDto memberDto) throws MessagingException, MessagingException {
		// 1. Mustache 템플릿 파일 읽기 및 컴파일
		String htmlContent = "";
		try {
			Map<String, String> templateModel = new HashMap<>();
			templateModel.put("signId", memberDto.getSignId());
			templateModel.put("email", memberDto.getEmail());
			templateModel.put("validText", memberDto.getValidText());
			Resource resource = resourceLoader.getResource("classpath:/templates/info/validemail.html");
			try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
				Template template = mustacheCompiler.compile(reader);
				// 모델 데이터를 HTML 템플릿에 바인딩
				htmlContent = template.execute(templateModel);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException("이메일 템플릿 컴파일 실패", e);
		}

		// 2. MimeMessage 생성 (HTML 타입 전송용)
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		// 두 번째 인자 'true'는 멀티파트(첨부파일 등) 활성화 여부, 세 번째 인자는 인코딩
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

		helper.setTo(memberDto.getEmail());
		helper.setFrom(fromEmail);
		helper.setSubject("회원가입시 이메일정보로 확인 합니다.");
		// 두 번째 인자 'true'를 설정해야 HTML 태그가 정상 렌더링됩니다.
		helper.setText(htmlContent, true);

		try {
			// 3. 이메일 발송
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
