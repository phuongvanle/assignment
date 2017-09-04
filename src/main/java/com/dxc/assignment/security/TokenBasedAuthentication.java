package com.dxc.assignment.security;


import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class TokenBasedAuthentication extends AbstractAuthenticationToken {
	
	private String token;
	
	private final UserDetails principle;
	

	public TokenBasedAuthentication(UserDetails principle) {
		super(principle.getAuthorities());
		this.principle = principle;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	public String getToken() {
		return token;
	}

	public UserDetails getPrinciple() {
		return principle;
	}

	@Override
	public Object getCredentials() {
		return token;
	}

	@Override
	public Object getPrincipal() {
		return principle;
	}
	
	@Override
	public boolean isAuthenticated() {
		return true;
	}

}
