package com.cart.shopping.security;

import com.cart.shopping.exception.AuthMethodNotSupportedException;
import com.cart.shopping.exception.ErrorResponse;
import com.cart.shopping.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	public static final String ISSUER_INFO = "https://www.autentia.com/";
	public static final String SUPER_SECRET_KEY = "1234";
	public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day

	private ObjectMapper mapper;

	private AuthenticationManager authenticationManager;
	
	private Long tokenExpirationInSeconds;
	
	private String tokenSecret;

	public AuthenticationFilter(String authenticationUrl, Long tokenExpirationInSeconds, String tokenSecret, AuthenticationManager authenticationManager) {
		super(authenticationUrl);
		this.mapper = new ObjectMapper();
		this.authenticationManager = authenticationManager;
		this.tokenExpirationInSeconds = tokenExpirationInSeconds;
		this.tokenSecret = tokenSecret;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		if (!HttpMethod.POST.name().equals(request.getMethod())) {
			if (logger.isDebugEnabled()) {
				logger.debug("Authentication method not supported. Request method: " + request.getMethod());
			}
			throw new AuthMethodNotSupportedException(Constants.MESSAGE_AUTH_METHOD_NOT_ALLOWED);
		}

		AppUserDetails credenciales = null;

		try {
			credenciales = new ObjectMapper().readValue(request.getInputStream(), AppUserDetails.class);
		} catch (Exception e) {
			throw new AuthMethodNotSupportedException("Usuario y password son requeridos.");
		}
		
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credenciales.getUsername(),
				credenciales.getPassword(), new ArrayList<>()));

	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException, ServletException {

		SecurityContextHolder.clearContext();

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		if (e instanceof BadCredentialsException) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			mapper.writeValue(response.getWriter(),
					ErrorResponse.of("Usuario/contraseña inválidos! Verificar que el usuario y su contraseña existen.", HttpStatus.UNAUTHORIZED));
		}else if (e instanceof AuthMethodNotSupportedException) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			mapper.writeValue(response.getWriter(), ErrorResponse.of(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
		}else {
			mapper.writeValue(response.getWriter(), ErrorResponse.of("Authentication failed", HttpStatus.INTERNAL_SERVER_ERROR));
		}

		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		final Date expirationToken = new Date(System.currentTimeMillis() + tokenExpirationInSeconds);
		//add ROLE_ prefix
		final String authorities = auth.getAuthorities().stream()
			      .map(profile -> "ROLE_"+profile.getAuthority())
			      .collect(Collectors.joining(","));

		String token = Jwts.builder().setIssuedAt(new Date()).setIssuer(ISSUER_INFO)
				.setSubject(((AppUserDetails) auth.getPrincipal()).getUsername()).claim("roles", authorities)
				.setExpiration(expirationToken)
				.signWith(SignatureAlgorithm.HS512, tokenSecret).compact();
		
		
		Map<String, Object> tokenMap = new HashMap<String, Object>();
        tokenMap.put("token", token);
        tokenMap.put("vence_token", expirationToken);        

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), tokenMap);
        
	}

}
