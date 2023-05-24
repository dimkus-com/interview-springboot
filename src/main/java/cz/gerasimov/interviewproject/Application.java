package cz.gerasimov.interviewproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		Thread.currentThread().setName("Application");
		SpringApplication.run(Application.class, args);
	}

}
