package practice.oauth.util;

import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TokenProvider {
	private static final int expireTime = 30;

	@Value("${jwt.secret}")
	private String secret;
	private Key key;

	@PostConstruct
	protected void init() {
		this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
	}

	public String createToken(final String email, final String role) {
		final Claims claims = Jwts.claims().setSubject(email);
		claims.put("role", role);
		final LocalDateTime current = LocalDateTime.now();
		return Jwts.builder()
				   .setClaims(claims)
				   .setIssuedAt(Timestamp.valueOf(current))
				   .setExpiration(Timestamp.valueOf(current.plusMinutes(expireTime)))
				   .signWith(key, SignatureAlgorithm.HS512)
				   .compact();
	}

	public boolean validateToken(final String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			log.info("잘못된 JWT 서명입니다.");
		} catch (ExpiredJwtException e) {
			log.info("만료된 JWT 토큰입니다.");
		} catch (UnsupportedJwtException e) {
			log.info("지원되지 않는 JWT 토큰입니다.");
		} catch (IllegalArgumentException e) {
			log.info("JWT 토큰이 잘못되었습니다.");
		}

		return false;
	}

}