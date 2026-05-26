package com.mjc813.login_cookie.models.music;

import lombok.*;

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
}
