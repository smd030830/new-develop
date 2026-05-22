package com.mjc813.cookies.models.attach;

import com.mjc813.cookies.models.common.IdName;
import com.mjc813.cookies.models.cookie.CookieEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "attach")
public class AttachEntity implements AttachInterface {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="file_name", length=200, nullable=false)
	private String fileName;

	@Column(name="size", nullable=false)
	private Integer size;

	@Column(name="ext", length=10, nullable=false)
	private String ext;

	@Column(name="store_name", length=500, nullable=false)
	private String storeName;

	@Column(name="path", length=100, nullable=false)
	private String path;

	@Transient
	private Long cookieId;

	@JoinColumn(name="cookie_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private CookieEntity cookie;

	@Override
	public Long getCookieId() {
		if ( this.cookie == null ) {
			this.cookie = new CookieEntity();
		}
		if ( this.cookie.getId() != null) {
			this.cookieId = this.cookie.getId();
		}
		return this.cookie.getId();
	}

	@Override
	public void setCookieId(Long cookieId) {
		// Long cookieId 랑 cookie.getId() 랑 값이 항상 같도록 해야 한다.
		if ( this.cookie == null ) {
			this.cookie = new CookieEntity();
		}
		this.cookie.setId(cookieId);
		this.cookieId = cookieId;
	}

	@Override
	public void setCookie(IdName cookie) {
		// Long cookieId 랑 cookie.getId() 랑 값이 항상 같도록 해야 한다.
		if ( cookie == null ) {
			return;
		}
		if ( this.cookie == null ) {
			this.cookie = new CookieEntity();
		}
		this.cookie.copyMembers(cookie, true);
		this.cookieId = cookie.getId();
	}
}
