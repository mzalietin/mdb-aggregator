package me.mzalietin.mdbproject.moviereview;

import java.util.HashMap;
import java.util.Map;
import me.mzalietin.mdbproject.moviereview.domain.model.ResourceAlreadyExistsException;
import me.mzalietin.mdbproject.moviereview.infrastructure.broker.event.in.UserEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
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
import org.springframework.kafka.annotation.KafkaListenerConfigurer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.ExponentialBackOffWithMaxRetries;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@ComponentScan
@EnableAutoConfiguration
@EnableKafka
@Configuration
@PropertySource("classpath:movie-review-context.properties")
public class MovieReviewContextConfig implements KafkaListenerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(MovieReviewContextConfig.class);

    @Autowired
    private LocalValidatorFactoryBean validator;

    @Value("${movie.review.context.kafka.dlt}")
    String movieReviewContextKafkaDlt;

    @Value("${kafka.host}")
    String kafkaHost;

    @Autowired
    KafkaTemplate<?, ?> kafkaTemplate;

    @Override
    public void configureKafkaListeners(final KafkaListenerEndpointRegistrar registrar) {
        registrar.setValidator(this.validator);
    }

    @Bean
    public ConsumerFactory<String, UserEvent> userKafkaConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHost);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserEvent> userListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, UserEvent>();
        factory.setConsumerFactory(userKafkaConsumerFactory());
        return factory;
    }

    @Bean
    public DefaultErrorHandler handler() {
        ExponentialBackOffWithMaxRetries bo = new ExponentialBackOffWithMaxRetries(3);
        bo.setInitialInterval(100);
        bo.setMultiplier(2.0);
        bo.setMaxInterval(1000);
        DefaultErrorHandler handler = new DefaultErrorHandler(recoverer(), bo);
        handler.setCommitRecovered(true);
        handler.addNotRetryableExceptions(ResourceAlreadyExistsException.class);
        handler.addNotRetryableExceptions(MethodArgumentNotValidException.class);
        return handler;
    }

    @Bean
    public DeadLetterPublishingRecoverer recoverer() {
        return new DeadLetterPublishingRecoverer(kafkaTemplate,
            (record, ex) -> {
                logger.error("Record topic {}, offset {} failed with exception", record.topic(), record.offset(), ex);
                return new TopicPartition(movieReviewContextKafkaDlt, -1);
            });
    }

    // -------- NON PROD CONFIG --------

    @Bean
    public NewTopic deadLetterTopic() {
        return TopicBuilder.name(movieReviewContextKafkaDlt)
            .partitions(1)
            .replicas(1)
            .compact()
            .build();
    }
}
