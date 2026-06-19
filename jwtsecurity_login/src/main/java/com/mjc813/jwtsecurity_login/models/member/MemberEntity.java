package com.mjc813.jwtsecurity_login.models.member;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "member")
public class MemberEntity implements IMember {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "sign_id", nullable = false, length = 20, unique = true)
	private String signId;

	@Column(nullable = false, length = 200)
	private String password;

	@Column(nullable = false, length = 200, unique = true)
	private String email;

	@Column(nullable = false, length = 10)
	private String role;

	@Column(name = "is_valid_email", nullable = false)
	private Boolean isValidEmail;

	@Column(name = "valid_text", length=100)
	private String validText;

	@Column(name = "create_dt", nullable = false)
	private LocalDateTime createDt;

	@Column(name = "update_dt")
	private LocalDateTime updateDt;

	@Column(name = "delete_dt")
	private LocalDateTime deleteDt;
}
