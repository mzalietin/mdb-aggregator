package me.mzalietin.mdbproject.user;

import jakarta.persistence.EntityManagerFactory;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@ComponentScan
@EnableAutoConfiguration
@EnableKafka
@Configuration
@PropertySource("classpath:user-context.properties")
public class UserContextConfig {

    @Value("${user.context.kafka.out.events-topic}")
    String eventsTopic;

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    // non Prod config

    @Bean
    public NewTopic eventsTopic() {
        return TopicBuilder.name(eventsTopic)
            .partitions(1)
            .replicas(1)
            .compact()
            .build();
    }
}
