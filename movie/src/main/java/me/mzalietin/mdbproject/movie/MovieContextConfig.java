package me.mzalietin.mdbproject.movie;

import jakarta.persistence.EntityManagerFactory;
import me.mzalietin.mdbproject.movie.domain.model.ResourceNotFoundException;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.ExponentialBackOffWithMaxRetries;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@ComponentScan
@EnableAutoConfiguration
@EnableKafka
@Configuration
@PropertySource("classpath:movie-context.properties")
public class MovieContextConfig {

    private static final Logger logger = LoggerFactory.getLogger(MovieContextConfig.class);

    @Autowired
    KafkaTemplate<?, ?> kafkaTemplate;

    @Value("${movie.context.kafka.dlt}")
    String movieContextKafkaDlt;

    @Value("${movie.context.kafka.out.events-topic}")
    String eventsTopic;

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public DefaultErrorHandler handler() {
        ExponentialBackOffWithMaxRetries bo = new ExponentialBackOffWithMaxRetries(3);
        bo.setInitialInterval(100);
        bo.setMultiplier(2.0);
        bo.setMaxInterval(1000);
        DefaultErrorHandler handler = new DefaultErrorHandler(recoverer(), bo);
        handler.setCommitRecovered(true);
        handler.addNotRetryableExceptions(ResourceNotFoundException.class);
        return handler;
    }

    @Bean
    public DeadLetterPublishingRecoverer recoverer() {
        return new DeadLetterPublishingRecoverer(kafkaTemplate,
            (record, ex) -> {
                logger.error("Record topic {}, offset {} failed with exception", record.topic(), record.offset(), ex);
                return new TopicPartition(movieContextKafkaDlt, -1);
            });
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

    @Bean
    public NewTopic deadLetterTopic() {
        return TopicBuilder.name(movieContextKafkaDlt)
            .partitions(1)
            .replicas(1)
            .compact()
            .build();
    }
}
