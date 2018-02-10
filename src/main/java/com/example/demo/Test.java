package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Test {

	public static void main(String[] args) {
		String cookie = "ramesh,hema,harish,shobhna,jeeva";
		
		Optional<String> cookieStr = Optional.ofNullable(cookie); 
		
		List<String> cookies = new ArrayList<>();
		
		if(cookieStr.isPresent()) {
			Arrays.stream(cookieStr.get().split(",")).forEach(cookies::add);
		}
		
		cookies.stream().forEach(System.out::println);
	}
}
