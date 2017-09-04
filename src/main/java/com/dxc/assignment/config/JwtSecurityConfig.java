package com.dxc.assignment.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.dxc.assignment.security.AuthenticationFailureHandler;
import com.dxc.assignment.security.AuthenticationSuccessHandler;
import com.dxc.assignment.security.JwtAuthenticationEntryPoint;
import com.dxc.assignment.security.JwtLogoutSuccessHandler;
import com.dxc.assignment.security.TokenAuthenticationFilter;
import com.dxc.assignment.security.TokenHelper;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;
	
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtLogoutSuccessHandler logoutSuccess;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
	private TokenHelper tokenHelper;

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().exceptionHandling().authenticationEntryPoint(entryPoint).and().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/", "/*.html","/favicon.ico","/**/*.html", "/**/*.css", "/**/*.js", "/charts/*")
		.permitAll().antMatchers("/auth/**").permitAll().anyRequest().authenticated().and()
		.addFilterBefore(new TokenAuthenticationFilter(tokenHelper, userDetailsService), BasicAuthenticationFilter.class)
		.formLogin().loginPage("/auth/login")
		.successHandler(authenticationSuccessHandler)
		.failureHandler(authenticationFailureHandler).and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
		.logoutSuccessHandler(logoutSuccess)
		.deleteCookies(TOKEN_COOKIE);
		
		 // disable csrf for the login request
		http.csrf().ignoringAntMatchers("/auth/login")
		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/*.html","/favicon.ico","/**/*.html","/**/*.css","/**/*.js","/charts/**");
	}

}
