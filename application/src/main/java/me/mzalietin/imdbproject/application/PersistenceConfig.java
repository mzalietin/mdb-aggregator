package me.mzalietin.imdbproject.application;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
    basePackages = "me.mzalietin.imdbproject.*.gateway.dataprovider"
)
@EntityScan("me.mzalietin.imdbproject.*.gateway.dataprovider")
public class PersistenceConfig {
}
