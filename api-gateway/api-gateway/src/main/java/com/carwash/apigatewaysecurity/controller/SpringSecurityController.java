package com.carwash.apigatewaysecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.carwash.apigatewaysecurity.models.AuthenticationRequest;
import com.carwash.apigatewaysecurity.models.AuthenticationResponse;
import com.carwash.apigatewaysecurity.service.MyUserDetailsService;
import com.carwash.apigatewaysecurity.util.JwtUtil;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SpringSecurityController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;
	@Autowired
	RestTemplate restTemplate;


	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());

		final String jwt = jwtTokenUtil.generateToken(userDetails);
		List<GrantedAuthority> role=userDetails.getAuthorities().stream().collect(Collectors.toList());

		String userName=userDetails.getUsername();
		return ResponseEntity.ok(new AuthenticationResponse(jwt,role.get(0).toString(),userName));

	}
}