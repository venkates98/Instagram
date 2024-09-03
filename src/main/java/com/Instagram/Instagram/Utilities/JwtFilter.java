package com.Instagram.Instagram.Utilities;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			final String token = authorizationHeader.substring(7);
			final String username = jwtUtil.extractUsername(token);

			if (username != null && jwtUtil.validateToken(token, username)) {
				// Set authentication in SecurityContext
				// Add user details or principal if necessary
			}
		}
		filterChain.doFilter(request, response);
	}

}
