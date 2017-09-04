package com.dxc.assignment.security;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenHelper {
	@Value("${app.name}")
	private String appname;
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expires_in}")
	private long expires_In;
	@Value("${jwt.header}")
	private String auth_Header;
	@Value("${jwt.cookie}")
	private String auth_Cookie;
	@Autowired
	UserDetailsService userDetailsService;

	private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

	public String getUsernameFromToken(String token) {
		return getClaimsFromToken(token, Claims::getSubject);
	}
	
	public Boolean canTokenBeRefreshed(String token) {
		final Date expirationDate = getClaimsFromToken(token, Claims::getExpiration);
		return expirationDate.compareTo(generateCurrentDate()) > 0;
	}
	
	public String refreshToken(String token) {
		final Claims claims = getAllClaimsFromToken(token);
		claims.setIssuedAt(generateCurrentDate());
		return generateToken(claims);
	}

	private Date generateCurrentDate() {
		return new Date(getCurrentTimeMillis());
	}

	private <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
		Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	public String generateToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate())
				.signWith(signatureAlgorithm, secret).compact();
	}
	
	public String generateToken(String username) {
		return Jwts.builder().setIssuer(appname).setSubject(username).setIssuedAt(generateCurrentDate())
				.setExpiration(generateExpirationDate()).signWith(signatureAlgorithm, secret).compact();
	}

	private Date generateExpirationDate() {
		return new Date(getCurrentTimeMillis() + this.expires_In * 1000);
	}

	private long getCurrentTimeMillis() {
		return DateTime.now().getMillis();
	}
	
	public String getToken(HttpServletRequest request) {
		// Getting the token from Cookie Store
		Cookie authCookie = getCookieValueByName(request, auth_Cookie);
		if (authCookie != null) {
			return authCookie.getValue();
		}
		
		/**
		 * Getting the token from Authentication header e.g Bearer your_token
		 */
		String authHeader = request.getHeader(auth_Header);
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}
		return null;
	}
	
	/**
     * Find a specific HTTP cookie in a request.
     *
     * @param request
     *            The HTTP request object.
     * @param name
     *            The cookie name to look for.
     * @return The cookie, or <code>null</code> if not found.
     */

	private Cookie getCookieValueByName(HttpServletRequest request, String name) {
		if (request.getCookies() == null) {
			return null;
		}
		for (int i = 0; i < request.getCookies().length; i++) {
			if (request.getCookies()[i].getName().equals(name)) {
				return request.getCookies()[i];
			}
		}
		return null;
	}

}
