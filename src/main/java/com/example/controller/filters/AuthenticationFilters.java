package com.example.controller.filters;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

/**
 * If two filters have same priority then order is not guaranteed
 * 
 * @author harbhatt
 *
 */
@Priority(-1)
@Provider
public class AuthenticationFilters implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		List<String> authKeys = requestContext.getHeaders().get("Authorization");
		
		if(authKeys!=null && authKeys.size()>0) {
			String key = authKeys.get(0);
			
			if(key.toUpperCase().contains("BASIC")) {
				String cred = Base64.decodeAsString(key.replaceFirst("Basic ", ""));
				
				StringTokenizer tokens = new StringTokenizer(cred, ":");
				
				String userName = tokens.hasMoreTokens() ? tokens.nextToken() : "";
				String password = tokens.hasMoreTokens() ? tokens.nextToken() : "";
				
				if(userName.equalsIgnoreCase("username") && password.equalsIgnoreCase("password")) {
					return;
				}
			}
			
		} 
		
		Response response = Response.status(Response.Status.UNAUTHORIZED)
				.entity("User is not authorized").build();
		requestContext.abortWith(response);
	}

}
