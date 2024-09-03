package com.Instagram.Instagram.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Instagram.Instagram.Entity.User;
import com.Instagram.Instagram.Repository.UserRepository;
import com.Instagram.Instagram.Utilities.JwtUtil;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public String signIn(String username, String password) {
		User user = userRepository.findByUsername(username);
		if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
			return jwtUtil.generateToken(username);
		}
		return null;
	}

	public String signUp(String username, String email, String password) {
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(bCryptPasswordEncoder.encode(password));
		userRepository.save(user);
		return "Account Created Sucessfully";
	}
}
