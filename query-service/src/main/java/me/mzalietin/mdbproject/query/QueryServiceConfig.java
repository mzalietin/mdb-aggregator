package me.mzalietin.mdbproject.query;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
@EnableAutoConfiguration
@ComponentScan
@PropertySource("classpath:query-service.properties")
public class QueryServiceConfig {


}
