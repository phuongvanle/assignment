package com.dxc.assignment.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.joda.time.DateTimeUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import io.jsonwebtoken.ExpiredJwtException;

public class TokenHelperTest {
	
	private static final String  TEST_USERNAME = "testUser";
	
	@InjectMocks
	private TokenHelper tokenHelper;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		ReflectionTestUtils.setField(tokenHelper, "expires_In", 10L);
		ReflectionTestUtils.setField(tokenHelper, "secret", "mySecret");
		DateTimeUtils.setCurrentMillisSystem();
	}
	
	@Test
	public void testGenerateTokenGeneratesDifferentTokensForDifferentCreationDates() {
		final String token  = createToken();
		DateTimeUtils.setCurrentMillisFixed(200000L);
		final String laterToken = createToken();
		
		assertThat(token).isNotEqualTo(laterToken);
	}
	private String createToken() {
		return tokenHelper.generateToken(new UserDetailsDummy(TEST_USERNAME).getUsername());
	}
	
	@Test
	public void getUsernameFromToken() {
		final String token = createToken();
		assertThat(tokenHelper.getUsernameFromToken(token)).isEqualTo(TEST_USERNAME);
	}
	
	
	@Test(expected = ExpiredJwtException.class)
	public void expiredTokenCannotBeRefreshed() {
		DateTimeUtils.setCurrentMillisFixed(1L);
		String token = createToken();
		DateTimeUtils.setCurrentMillisSystem();
		assertThat(tokenHelper.canTokenBeRefreshed(token)).isFalse();
	}
	
	@Test
	public void notExpiredCanBeRefreshed() {
		String token = createToken();
		assertThat(tokenHelper.canTokenBeRefreshed(token)).isTrue();
	}
	
	

}
