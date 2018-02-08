package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value="/")
public class HomeController {

	@RequestMapping(value={"/a"})
	public String home(final String a) {
		return "a";
	}
}
