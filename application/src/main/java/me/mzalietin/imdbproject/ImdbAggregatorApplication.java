package me.mzalietin.imdbproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ImdbAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImdbAggregatorApplication.class, args);
	}
}
