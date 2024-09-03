package com.Instagram.Instagram.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Instagram.Instagram.Utilities.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public JwtFilter jwtFilter() {
		return new JwtFilter();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/api/auth/**").permitAll().anyRequest().authenticated()
				.and().addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().antMatchers("/swagger-ui/**", "/v3/api-docs/**");
	}
	 @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}