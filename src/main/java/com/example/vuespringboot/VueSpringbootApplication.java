package com.example.vuespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class VueSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(VueSpringbootApplication.class, args);
	}

}
