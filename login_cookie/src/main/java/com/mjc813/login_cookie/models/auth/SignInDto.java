package com.mjc813.login_cookie.models.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjc813.login_cookie.models.member.IMember;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInDto implements IMember {
	@JsonIgnore
	private Long id;

	private String signId;
	private String password;

	@JsonIgnore
	private String email;
	@JsonIgnore
	private String role;
	@JsonIgnore
	private Boolean isValidEmail;
	@JsonIgnore
	private String validText;
	@JsonIgnore
	private LocalDateTime createDt;
	@JsonIgnore
	private LocalDateTime updateDt;
	@JsonIgnore
	private LocalDateTime deleteDt;
}
