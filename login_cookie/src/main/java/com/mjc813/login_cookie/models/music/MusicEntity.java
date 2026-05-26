package com.mjc813.login_cookie.models.music;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "music")
public class MusicEntity implements IMusic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String title;

	@Column(nullable = false, length = 100)
	private String artist;

	@Column(name = "playtime", nullable = false, length = 5)
	private String playtime;
}
