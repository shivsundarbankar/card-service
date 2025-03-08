package com.shivpro.cardservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1")
public class CardServiceApplication {

	@GetMapping("/greetings")
	public String greetings(){
		return "Welcome to Kubernetes practice sessions";
	}

	@GetMapping("/users")
	public List<User> users(){
		return Stream.of(new User(1,"jack"),
				new User(2, "mike"),
				new User(3,"suckdeep")).toList();
	}

	public static void main(String[] args) {
		SpringApplication.run(CardServiceApplication.class, args);
	}

}
