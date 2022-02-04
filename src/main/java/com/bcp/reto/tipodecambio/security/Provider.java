package com.bcp.reto.tipodecambio.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


@Service
public class Provider implements AuthenticationProvider {
	
	private Logger log = LoggerFactory.getLogger(Provider.class);


	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {


		String userName = authentication.getName();
		String pwd = authentication.getCredentials().toString();

		if(pwd.length() == 0) throw new BadCredentialsException("Por favor ingresar el password");

		if (userName.equals("xavier") && pwd.equals("1234")) {
			log.info("Ingreso correcto: ", userName);
			return new UsernamePasswordAuthenticationToken(userName, pwd, new ArrayList<>());
		} else {
			throw new BadCredentialsException("Las credenciales ingresadas son incorrectas");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}