package com.tsystems.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tsystems.model.Person;
import com.tsystems.repository.AuthenticationRepository;

@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	AuthenticationRepository authenticationRepository;

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		authenticationRepository.findUserByEmail(email);
		Person userInfo = authenticationRepository.findUserByEmail(email);
		GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getType().toString());
		UserDetails userDetails = (UserDetails) new User(userInfo.getEmail(), userInfo.getPassword(),
				Arrays.asList(authority));
		return userDetails;
	}

}
