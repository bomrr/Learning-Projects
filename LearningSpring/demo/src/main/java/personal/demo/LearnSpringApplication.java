package personal.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * This class is the main class that runs the Spring Boot application.
 * When you want to run the program, run this class.
 */

// @SpringBootApplication is a meta-annotation that pulls in component scanning, autoconfiguration, and property support.
@SpringBootApplication
public class LearnSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringApplication.class, args);
	}

}