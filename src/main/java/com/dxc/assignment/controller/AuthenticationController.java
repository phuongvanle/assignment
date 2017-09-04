package com.dxc.assignment.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.assignment.model.UserTokenState;
import com.dxc.assignment.security.TokenHelper;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
	
	@Autowired
	TokenHelper tokenHelper;
	
	@Value("${jwt.expires_in}")
	private int expires_in;
	
	@Value("${jwt.cookie}")
	private String token_cookie;
	
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request, HttpServletResponse response) {
		String authToken = tokenHelper.getToken(request);
		if (authToken != null && tokenHelper.canTokenBeRefreshed(authToken)) {
			 // TODO check user password last update
			String refreshedToken = tokenHelper.refreshToken(authToken);
			
			Cookie authCookie = new Cookie(token_cookie, refreshedToken);
			authCookie.setPath("/");
			authCookie.setHttpOnly(true);
			authCookie.setMaxAge(expires_in);
			 // Add cookie to response
			response.addCookie(authCookie);
			
			UserTokenState userTokenState = new UserTokenState(refreshedToken, expires_in);
			return ResponseEntity.ok(userTokenState);
		} else {
			UserTokenState userTokenState = new UserTokenState();
			return ResponseEntity.accepted().body(userTokenState);
		}
	}

}
