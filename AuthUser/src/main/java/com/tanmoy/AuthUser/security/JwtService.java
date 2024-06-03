package com.tanmoy.AuthUser.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtService {

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    private static final long TOKEN_VALIDITY_IN_HOURS = 6;

    public String generateToken(String userName, Authentication authentication) {
        Map<String, Object> claims = new HashMap<>(getRolesAndPrivileges(authentication));
        claims.put("username", userName);
        return createToken(claims, userName);
    }

    private Map<String, Object> getRolesAndPrivileges(Authentication authentication) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roleList = new ArrayList<>();
        List<String> priviligeList = new ArrayList<>();
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if(authority.getAuthority().contains("ROLE_")) {
                roleList.add(authority.getAuthority());
            }
            if(authority.getAuthority().contains("_PRIVILEGE")) {
                priviligeList.add(authority.getAuthority());
            }
        }
        claims.put("roles", roleList);
        claims.put("privileges", priviligeList);
        return claims;
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ((1000 * 60 * 60) * TOKEN_VALIDITY_IN_HOURS)))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
