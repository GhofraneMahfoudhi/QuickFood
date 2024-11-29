package com.example.QuickFood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages ="com.example.QuickFood.model" )
public class QuickFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickFoodApplication.class, args);
	}

}
