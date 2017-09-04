package com.dxc.assignment.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.dxc.assignment.model.User;
import com.dxc.assignment.model.UserTokenState;
import com.fasterxml.jackson.databind.ObjectMapper;
@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Value("${jwt.expires_in}")
	private int expires_in;
	@Value("${jwt.cookie}")
	private String token_cookie;
	@Autowired
	TokenHelper tokenHelper;
	@Autowired
	ObjectMapper objectMapper;
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		clearAuthenticationAttributes(request);
		User user = (User) authentication.getPrincipal();
		String jws = tokenHelper.generateToken(user.getUsername());
		
		//Create token auth Cookie
		Cookie authCookie = new Cookie(token_cookie, jws);
		authCookie.setPath("/");
		authCookie.setHttpOnly(true);
		authCookie.setMaxAge(expires_in);
		// add cookie to response
		response.addCookie(authCookie);
		// JWT is also in the response
		UserTokenState userTokenState = new UserTokenState(jws, expires_in);
		String jwtResponse = objectMapper.writeValueAsString(userTokenState);
		response.setContentType("application/json");
		response.getWriter().write(jwtResponse);
	}

}
