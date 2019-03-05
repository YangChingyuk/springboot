package com.yqx.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello Spring Boot!";
	}
	
	@RequestMapping("/hi")
	public String hi() {
		return "Hi Spring Boot!";
	}
}
