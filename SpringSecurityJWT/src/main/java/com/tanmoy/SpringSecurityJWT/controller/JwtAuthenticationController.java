package com.tanmoy.SpringSecurityJWT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tanmoy.SpringSecurityJWT.model.JwtRequest;
import com.tanmoy.SpringSecurityJWT.model.JwtResponse;
import com.tanmoy.SpringSecurityJWT.security.JwtTokenUtils;
import com.tanmoy.SpringSecurityJWT.service.UserDetailsServiceImplements;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
	
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtils tokenUtils;
	
	@Autowired
	private UserDetailsServiceImplements userDetailsService;
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest request) throws Exception {
		authenticate(request.getUsername(), request.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(request.getUsername());

		final String token = tokenUtils.generateToken(userDetails);
		
		System.out.println("Generated Token: " + token);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	

}
