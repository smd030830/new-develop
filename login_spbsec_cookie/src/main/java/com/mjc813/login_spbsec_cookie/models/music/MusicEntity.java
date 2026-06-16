package com.mjc813.login_spbsec_cookie.models.music;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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


	@Column(name = "create_id", nullable = false, length = 20)
	private String createId;

	@Column(name = "create_dt", nullable = false)
	private LocalDateTime createDt;

	@Column(name = "update_id", nullable = true, length = 20)
	private String updateId;

	@Column(name = "update_dt", nullable = true)
	private LocalDateTime updateDt;

	@Column(name = "delete_id", nullable = true, length = 20)
	private String deleteId;

	@Column(name = "delete_dt", nullable = true)
	private LocalDateTime deleteDt;
}
