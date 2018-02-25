package com.example.controller.filters;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

@Priority(0)
@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

	Logger LOGGER = Logger.getLogger(LoggingFilter.class);
	
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		LOGGER.info("Response rendered for request with URI " + requestContext.getUriInfo().getPath());
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		LOGGER.info("Executing request with URI " + requestContext.getUriInfo().getPath());
	}

}
