package com.mjc813.jwtsecurity_login.models.music;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicDto implements IMusic {
	private Long id;
	private String title;
	private String artist;
	private String playtime;

	private String createId;
	private LocalDateTime createDt;
	private String updateId;
	private LocalDateTime updateDt;
	private String deleteId;
	private LocalDateTime deleteDt;
}
