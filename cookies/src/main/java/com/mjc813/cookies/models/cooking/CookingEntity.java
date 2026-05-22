package com.mjc813.cookies.models.cooking;

import com.mjc813.cookies.models.cookie.CookieEntity;
import com.mjc813.cookies.models.common.IdName;
import com.mjc813.cookies.models.cookie.CookieDto;
import com.mjc813.cookies.models.cookie.CookieEntity;
import com.mjc813.cookies.models.ingredient.IngredientInterface;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "cooking")
//@NamedEntityGraph(name="CookingEntity.fetchCookie", attributeNodes = {
//		@NamedAttributeNode(value="cookie")
//})
public class CookingEntity implements CookingInterface {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Transient
	private Long cookieId;

	@JoinColumn(name = "cookie_id", nullable = false)
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
