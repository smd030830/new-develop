package com.mjc813.jwtsecurity_login.models.redismember;

import com.mjc813.jwtsecurity_login.models.member.MemberDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RedisMemberDto extends MemberDto implements IRedisMember {
	private String accessToken;
	private String refreshToken;
}
