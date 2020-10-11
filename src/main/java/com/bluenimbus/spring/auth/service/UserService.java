package com.bluenimbus.spring.auth.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.bluenimbus.spring.auth.exception.CustomException;
import com.bluenimbus.spring.auth.model.SignInAuthResponseDTO;
import com.bluenimbus.spring.auth.model.User;
import com.bluenimbus.spring.auth.repository.UserRepository;
import com.bluenimbus.spring.auth.security.AuthJwtTokenProvider;









@Service
public class UserService {
	  

	  @Autowired
	  private AuthJwtTokenProvider jwtTokenProvider;

	  @Autowired
	  private AuthenticationManager authenticationManager;
	  
	  @Autowired
	  private PasswordEncoder passwordEncoder;
	  
	  @Autowired
	  private UserRepository userRepository;
	  
	  private SignInAuthResponseDTO authResponse;
	  
	 

	  
	  public SignInAuthResponseDTO authenticate(String username, String password, boolean isEmail) {
		    try {
		     authResponse=new SignInAuthResponseDTO();
		      System.out.println("username=" + username);
		      System.out.println("password=" + password);
		      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		      System.out.println("Trying to get token " + password);
		      authResponse.setStatus(true);
		      authResponse.setToken(jwtTokenProvider.createToken(username));
		      authResponse.setHttpStatus("200");
		      return authResponse;
		    } catch (AuthenticationException e) {
		      e.printStackTrace();
		      throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		    }
		  }
	  public String signup(User user) {
		    if (!userRepository.existsByUsername(user.getUsername())) {
		      user.setPassword(passwordEncoder.encode(user.getPassword()));
		      userRepository.save(user);
		      return jwtTokenProvider.createToken(user.getUsername());
		    } else {
		      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		    }
		  }
	  public String refresh(String username) {
	    return jwtTokenProvider.createToken(username);
	  }

}
