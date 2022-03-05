package com.tanmoy.SpringSecurityJWT.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtils implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final long JWT_TOKEN_VALIDITY = 1 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;

	/**
	 * Retrieve username from jwt token
	 * 
	 * @param token
	 * @return username
	 */
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	/**
	 * Retrieve expiration date from jwt token
	 * 
	 * @param token
	 * @return expiration date
	 */
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	/**
	 * For retrieving any information from token we will need the secret key
	 * 
	 * @param token
	 * @return A JWT Claims set
	 */
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	/**
	 * Check if the token has expired
	 * 
	 * @param token
	 * @return true if token expired else false
	 */
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	/**
	 * Generate token for user
	 * 
	 * @param userDetails
	 * @return token
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	/**
	 * While creating the token - 1. Define claims of the token, like Issuer,
	 * Expiration, Subject, and the ID 2. Sign the JWT using the HS512 algorithm and
	 * secret key. 3. According to JWS Compact
	 * Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	 * compaction of the JWT to a URL-safe string
	 * 
	 * @param claims
	 * @param subject
	 * @return token
	 */
	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	/**
	 * Validate token
	 * 
	 * @param token
	 * @param userDetails
	 * @return true if token valid else false
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
