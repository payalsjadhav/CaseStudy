package com.carwash.apigatewaysecurity.models;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

public class AuthenticationResponse implements Serializable {

	private final String jwt;
	private String role;
	private String userName;

	public AuthenticationResponse(String jwt, String role , String userName) {
		this.jwt = jwt;
		this.role=role;
		this.userName=userName;
	}

	public String getJwt() {
		return jwt;
	}

	public String getRole() {
		return role;
	}

	public String getUserName() {
		return userName;
	}
}
