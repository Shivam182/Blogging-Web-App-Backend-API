package com.api.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.blog.exceptions.ApiException;
import com.api.blog.payloads.JwtAuthRequest;
import com.api.blog.payloads.JwtAuthResponse;
import com.api.blog.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@Autowired
	private UserDetailsService userDetailService;
	
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{
		
		
		this.authenticate(request.getUsername(),request.getPassword());
		
		UserDetails userDetails = this.userDetailService.loadUserByUsername(request.getUsername());
		
		String token = this.jwtTokenHelper.generateToken(userDetails);
		
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		
		
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}
	
	
	private void authenticate(String username, String password) throws Exception {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		
		try {
			this.authenticationManager.authenticate(authenticationToken);
		}catch(BadCredentialsException e) {
			System.out.println("invalid user credentials");
			throw new ApiException("Invalid user credentials");
			
		}
		
		
	}
}
