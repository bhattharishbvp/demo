package com.example.controller;

import java.util.Calendar;

import javax.ws.rs.ext.ParamConverter;

public class MyDatePathParmConverter implements ParamConverter<MyDate> {

	@Override
	public MyDate fromString(String value) {
		
		return new MyDate(value);
	}

	@Override
	public String toString(MyDate value) {
		// TODO Auto-generated method stub
		return null;
	}

}
