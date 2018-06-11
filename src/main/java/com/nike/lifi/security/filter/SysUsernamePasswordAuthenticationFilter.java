package com.nike.lifi.security.filter;

import java.util.Hashtable;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nike.lifi.constants.LdapConfig;

public class SysUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Resource
	private LdapConfig ldapConfig;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		if (username == null) {
			username = "";
		} else {
			username = username.trim().toLowerCase();
		}

		if (password == null) {
			password = "";
		}

		if ("1".equals(ldapConfig.getEnable()) && !"admin".equalsIgnoreCase(username)) {
			Hashtable<String, String> env = new Hashtable<String, String>();
			DirContext ctx = null;
			env.put(Context.SECURITY_AUTHENTICATION, ldapConfig.getAuth());
			env.put(Context.SECURITY_PRINCIPAL, ldapConfig.getDomain() + "\\" + username);
			env.put(Context.SECURITY_CREDENTIALS, password);
			env.put(Context.INITIAL_CONTEXT_FACTORY, ldapConfig.getClazz()); // LDAP工厂类
			env.put(Context.PROVIDER_URL, ldapConfig.getLdapUrl());

			try {
				logger.debug("Start LDAP validation!");
				ctx = new InitialDirContext(env);
				ctx.close();
			} catch (NamingException e) {
				throw new BadCredentialsException("LDAP validation failed!");
			}

			password = ldapConfig.getDefPass();
		}
		
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

}
