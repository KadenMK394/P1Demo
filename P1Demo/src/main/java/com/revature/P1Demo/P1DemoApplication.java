package com.revature.P1Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.revature.models") //This tells Spring to look in the models package for DB entities
@ComponentScan("com.revature") //This tells Spring to look in com.revature for Beans (stereotype annotations)
@EnableJpaRepositories("com.revature.DAOs") //This tells Spring to look in the DAOs package for JPA Repositories
public class P1DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(P1DemoApplication.class, args);

		System.out.println("Welcome to the Team and Player Management System!");
	}

}
