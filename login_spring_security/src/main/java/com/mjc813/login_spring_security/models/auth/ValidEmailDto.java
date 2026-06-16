package com.mjc813.login_spring_security.models.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjc813.login_spring_security.models.member.IMember;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidEmailDto implements IMember {
	@JsonIgnore
	private Long id;

	private String signId;

	@JsonIgnore
	private String password;
	@JsonIgnore
	private String email;
	@JsonIgnore
	private String role;
	@JsonIgnore
	private Boolean isValidEmail;

	private String validText;
	@JsonIgnore
	private LocalDateTime createDt;
	@JsonIgnore
	private LocalDateTime updateDt;
	@JsonIgnore
	private LocalDateTime deleteDt;
}
