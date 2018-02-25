package com.example.controller;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class JerseyTest {

	@PathParam("day")
	private String dayPathParam; 
	
	@PathParam("customDay")
	private MyDate customDayPathParam;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/messageBodyWriter/{day}")
	public MyDate get() {
		
		return new MyDate(dayPathParam);
		//return "this is get " + dayPathParam;
	}
	
	/**
	 * PathParamConverter and MessageBodyWriter
	 * and custom Media type
	 * @return
	 */
	@GET
	@Produces("text/customDate")
	@Path("/pathParamConverter/{customDay}")
	public MyDate getCustom() {
		return customDayPathParam;
		//return "this is get " + dayPathParam;
	}
}


class MyDate {
	
	private Date date;

	public MyDate(String date) {
		Calendar calendar = Calendar.getInstance();
		if(date.equalsIgnoreCase("tomorrow")) {
			calendar.add(Calendar.DATE, 1);
		} else if(date.equalsIgnoreCase("yesterday")) {
			calendar.add(Calendar.DATE, -1);
		}
		
		this.date = calendar.getTime();
	}

	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		return this.date.toString();
	}
}