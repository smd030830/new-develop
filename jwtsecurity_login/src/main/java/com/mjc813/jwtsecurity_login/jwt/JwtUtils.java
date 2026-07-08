package com.mjc813.jwtsecurity_login.jwt;

import com.mjc813.jwtsecurity_login.models.auth.AuthTokenDto;
import com.mjc813.jwtsecurity_login.models.member.IMember;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {
//	@Value("${myapp.jwt.secret:thisismyjwtsecretkey!123456abcdef}")
	private String secret = "thisismyjwtsecretkey!123456abcdef";
//	@Value("${myapp.jwt.expireAccessToken}")
	private Long expireAccessToken = 1800000L; // 30분
	private Long expireRefreshToken = 604800000L; // 7일

	private final SecretKey secretKey;

	public JwtUtils() {
		this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String generateAccessToken(String value) {
		return this.generateToken(value, this.expireAccessToken);
	}

	public String generateRefreshToken(String value) {
		return this.generateToken(value, this.expireRefreshToken);
	}

	public String generateToken(String value, Long milliSeconds) {
		String str = Jwts.builder()
				.subject(value)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + milliSeconds))
				.signWith(this.secretKey)
				.compact();
		return str;
	}

	public String generateToken(IMember member, Long milliSeconds) {
		String str = Jwts.builder()
				.subject(member.getSignId())
				.claim("role", member.getRole())    // subject 외에 부가정보는 claim 에 추가할수 있다.
				.claim("email", member.getEmail())  // jwt 에 부가정보 중 개인정보를 넣으면 위험하다.
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + milliSeconds))
				.signWith(this.secretKey)
				.compact();
		return str;
	}

	public Claims parseToken(String token) {
		try {
			Claims cl = Jwts.parser()
					.verifyWith(this.secretKey)
					.build()
					.parseSignedClaims(token)
					.getPayload();
			return cl;
		} catch (ExpiredJwtException | IllegalArgumentException e ) {
			throw e;
		} catch (JwtException e ) {
			throw e;
		}
	}

	public String getRole(String token) throws JwtExpireException {
		Claims cl = this.parseToken(token);
		return cl.get("role", String.class);
	}

	public String getEmail(String token) throws JwtExpireException {
//		Claims cl = this.parseToken(token);
//		return cl.get("email", String.class);
		return this.getValueFromClaims(token, "email");
	}

	public String getValueFromClaims(String token, String key) throws JwtExpireException {
		Claims cl = this.parseToken(token);
		return cl.get(key, String.class);
	}

	public String getSignId(String token) throws JwtExpireException {
		Claims cl = this.parseToken(token);
		return cl.getSubject();
	}

	public Boolean validateToken(String token) throws JwtExpireException {
		this.parseToken(token);
		return true;
	}

	public String resolveJwtTokenFromBearerToken(String bearerToken) {
		if ( bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}
	public void saveRedis(String signId, AuthTokenDto authTokenDto){

	}
}
