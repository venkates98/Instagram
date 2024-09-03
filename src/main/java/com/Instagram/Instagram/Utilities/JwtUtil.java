package com.Instagram.Instagram.Utilities;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private String secretKey = "your_secret_key"; // Use a more secure key in production

	public String generateToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}

	public String extractUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateToken(String token, String username) {
		String tokenUsername = extractUsername(token);
		return (tokenUsername.equals(username) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		Date expiration = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration();
		return expiration.before(new Date());
	}
}