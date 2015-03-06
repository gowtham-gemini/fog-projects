package com.assistanz.fogpanel.auth

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class CustomAuthentication extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1

	CustomAuthentication(principal, credentials) {
		super(principal, credentials)
	}

	CustomAuthentication(principal, credentials, Collection authorities) {
		super(principal, credentials, authorities)
	}
}
