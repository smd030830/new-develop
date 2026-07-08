package com.mjc813.jwtsecurity_login.models.music;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjc813.jwtsecurity_login.common.xss.XssError;
import com.mjc813.jwtsecurity_login.common.xss.XssEscape;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MusicRequestDto implements IMusic {
	private Long id;

	@Size(min = 4, max = 100)
	private String title;

	@Pattern(regexp = "^[a-zA-Z0-9_]{6,20}$", message = "6~20 size only characters(Aplabhet|Number|_")
	private String artist;

	@Pattern(regexp = "^[0-9]{2}:[0-9]{2}$", message = "00:00~99:99")
	private String playtime;

	@XssError
//	@XssEscape
	@NotEmpty(message = "you must write description")
	private String description;

	@JsonIgnore
	private String createId;
	@JsonIgnore
	private LocalDateTime createDt;
	@JsonIgnore
	private String updateId;
	@JsonIgnore
	private LocalDateTime updateDt;
	@JsonIgnore
	private String deleteId;
	@JsonIgnore
	private LocalDateTime deleteDt;
}
