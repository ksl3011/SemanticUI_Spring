package com.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class SemanticUiApplication {

	public static void main(String[] args) {

		SpringApplication.run(SemanticUiApplication.class, args);
	}

}
