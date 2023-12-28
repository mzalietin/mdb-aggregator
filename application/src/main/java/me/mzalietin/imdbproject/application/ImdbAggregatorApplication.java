package me.mzalietin.imdbproject.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"me.mzalietin.imdbproject"})
public class ImdbAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImdbAggregatorApplication.class, args);
	}
}
