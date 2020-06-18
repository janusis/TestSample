package com.example.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Test0617Application {

	public static void main(String[] args) {
		SpringApplication.run(Test0617Application.class, args);
	}
	
	@GetMapping
	public String HelloWorld() {
		return "Hello World";
	}

}
