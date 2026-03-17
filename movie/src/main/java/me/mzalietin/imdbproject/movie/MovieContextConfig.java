package me.mzalietin.imdbproject.movie;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;

@ComponentScan
@EnableAutoConfiguration
@EnableKafka
@Configuration
@PropertySource("classpath:movie-context.properties")
public class MovieContextConfig {

}
