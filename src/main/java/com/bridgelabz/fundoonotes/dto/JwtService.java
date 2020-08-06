package com.bridgelabz.fundoonotes.dto;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JwtService {
	private static final String SCERET = "qwertyuiop";

	private final static long TOKEN_VALIDITY = 5 * 60 * 60;

	public static String generateToken(Long id, Token expire) {
		if (expire.equals(Token.WITH_EXPIRE_TIME)) {
			return Jwts.builder().setSubject(String.valueOf(id))
					.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
					.signWith(SignatureAlgorithm.HS512, SCERET).compact();
		} else {
			return Jwts.builder().setSubject(String.valueOf(id)).signWith(SignatureAlgorithm.ES512, SCERET).compact();
		}
	}
	
	
	private static String createJwtToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, SCERET).compact();
	}

	public static Long parse(String token) {
		System.out.println("Parsing");
		Claims claim = Jwts.parser().setSigningKey(SCERET).parseClaimsJws(token).getBody();
		Long id = Long.parseLong(claim.getSubject());
		return id;
	}
	
	
	public static String jwtGenerateToken(String userMail) {
		Map<String, Object> claims = new HashMap<>();
		return createJwtToken(claims, userMail);
	}
	
	public static String parser(String token) {
		boolean isValid = false;
		String email = null;
		if (token != null) {
			Claims claims = Jwts.parser().setSigningKey(SCERET).parseClaimsJws(token).getBody();
			email = claims.getSubject();
			isValid = claims.getExpiration().after(Date.from(Instant.now()));
		}
		if (isValid) {
			return email;
		} else {
			return null;
		}
	}

	public enum Token {
		WITH_EXPIRE_TIME, WITHOUT_EXPIRE_TIME
	}
	
}