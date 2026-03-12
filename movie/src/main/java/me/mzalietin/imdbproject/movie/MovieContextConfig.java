package me.mzalietin.imdbproject.movie;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@EnableAutoConfiguration
@Configuration
@PropertySource("classpath:movie-context.properties")
public class MovieContextConfig {

}
