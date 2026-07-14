package com.mjc813.jwtsecurity_login.models.redismember;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash(value = "member")
public class RedisMemberEntity implements IRedisMember {
	private Long id;

	@Id
	private String signId;

	private String password;
	private String email;
	private String role;
	private Boolean isValidEmail;
	@Transient
	private String validText;
	@Transient
	private LocalDateTime createDt;
	@Transient
	private LocalDateTime updateDt;
	@Transient
	private LocalDateTime deleteDt;

	private String accessToken;
	private String refreshToken;
}
