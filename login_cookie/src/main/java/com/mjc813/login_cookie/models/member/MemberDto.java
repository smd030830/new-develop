package com.mjc813.login_cookie.models.member;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto implements IMember {
	private Long id;
	private String signId;
	private String password;
	private String email;
	private Boolean isValidEmail;
	private LocalDateTime createDt;
	private LocalDateTime updateDt;
	private LocalDateTime deleteDt;
}
