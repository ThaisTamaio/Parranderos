package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import java.util.Collection;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


@Bean
public CommandLineRunner demo(UserRepository repository) {
  return (args) -> {
	// save a few customers
	Collection<User> c_users = repository.findAllActiveUsers();

	for(User u: c_users)
	{
		System.out.println(u.toString());
	}
	
  };

}
}