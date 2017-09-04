package com.dxc.assignment.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
	
	private final Log logger = LogFactory.getLog(this.getClass());
	
	private TokenHelper tokenHelper;
	
	private UserDetailsService userDetailsService;
	
	

	public TokenAuthenticationFilter(TokenHelper tokenHelper, UserDetailsService userDetailsService) {
		this.tokenHelper = tokenHelper;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String username = null;
		String authToken = tokenHelper.getToken(request);
		if (authToken != null) {
			//get username from token
			try {
				username = tokenHelper.getUsernameFromToken(authToken);
			} catch (Exception e) {
				logger.error("an error occured during getting username from token", e);
			}
			
			if (username != null) {
				//get user
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				//create authentication
				TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
				authentication.setToken(authToken);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			}
		}
		
		chain.doFilter(request, response);
		
	}


}
