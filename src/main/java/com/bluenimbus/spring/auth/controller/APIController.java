package com.bluenimbus.spring.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluenimbus.spring.auth.exception.ResourceNotFoundException;
import com.bluenimbus.spring.auth.model.SignInAuthResponseDTO;
import com.bluenimbus.spring.auth.model.SignInRequestDTO;
import com.bluenimbus.spring.auth.model.SignUpResponseDTO;
import com.bluenimbus.spring.auth.model.User;
import com.bluenimbus.spring.auth.repository.UserRepository;
import com.bluenimbus.spring.auth.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/api/")
@Api(tags = "/api/")
public class APIController {
	
	@Autowired
	  private UserService userService;
	@Autowired
	  private UserRepository userRepository;
	@Autowired
	  private PasswordEncoder bCryptPasswordEncoder;
	 
	  
	  @PostMapping("/signin")
	  @ApiOperation(value = "${APIController.signin}",response = SignInAuthResponseDTO.class)
	  @ApiResponses(value = {//
	      @ApiResponse(code = 400, message = "Something went wrong"), //
	      @ApiResponse(code = 422, message = "Invalid username/password supplied")})
	  public SignInAuthResponseDTO login(@RequestBody SignInRequestDTO adminRequest) {
	    return userService.authenticate(adminRequest.getUserName(), adminRequest.getPassword(),false);
	  }
	  @PostMapping("/signup")
	  @ApiOperation(value = "${APIController.signup}",response = SignUpResponseDTO.class )
	  @ApiResponses(value = {//
	      @ApiResponse(code = 400, message = "Something went wrong"), //
	      @ApiResponse(code = 422, message = "Username is already in use"), //
	      @ApiResponse(code = 500, message = "Not sure what happend")})
	  public String signup(@RequestBody User userRequest) {
	    return userService.signup(userRequest);
	  }
	  /**
	   * Gets users by id.
	   *
	   * @param userId the user id
	   * @return the users by id
	   * @throws ResourceNotFoundException the resource not found exception
	   */
	  @GetMapping("/v1/users/{id}")
	  @PreAuthorize("hasRole('ROLE_ADMIN')")
	  @ApiOperation(value = "${APIController.getUser}",response = User.class, authorizations = { @Authorization(value="authHeader") } )
	  public ResponseEntity<User> getUsersById(@PathVariable(value = "id") Long userId)
	      throws ResourceNotFoundException {
	    User user =
	        userRepository
	            .findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
	    return ResponseEntity.ok().body(user);
	  }

}
