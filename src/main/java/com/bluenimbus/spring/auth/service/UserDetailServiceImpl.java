package com.bluenimbus.spring.auth.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bluenimbus.spring.auth.model.User;
import com.bluenimbus.spring.auth.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

  /**
   * Instantiates a new User detail service.
   *
   * @param userRepository the user repository
   */
  public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userDetails = userRepository.findByUsername(username);
        if(userDetails == null){
            throw new UsernameNotFoundException(username);
        }
        
        return org.springframework.security.core.userdetails.User//
                .withUsername(userDetails.getUsername())//
                .password(userDetails.getPassword())//
                .authorities("ROLE_ADMIN")//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }
}
