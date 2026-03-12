package me.mzalietin.imdbproject.moviereview;

import java.util.HashMap;
import java.util.Map;
import me.mzalietin.imdbproject.moviereview.domain.model.MovieReviewKey;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListenerConfigurer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@ComponentScan
@EnableAutoConfiguration
@EnableKafka
@Configuration
@PropertySource("classpath:movie-review-crud.properties")
public class MovieReviewCrudConfig implements KafkaListenerConfigurer {

    @Autowired
    private LocalValidatorFactoryBean validator;

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<MovieReviewKey, Object>> moviereviewKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<MovieReviewKey, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(1);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    @Bean
    public ConsumerFactory<MovieReviewKey, Object> consumerFactory() {
        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JacksonJsonDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JacksonJsonDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALIDATOR_CLASS, LocalValidatorFactoryBean.class);
        props.put(JacksonJsonDeserializer.KEY_DEFAULT_TYPE, MovieReviewKey.class);
        props.put(JacksonJsonDeserializer.VALUE_DEFAULT_TYPE, Object.class);
        props.put(JacksonJsonDeserializer.TRUSTED_PACKAGES, "me.mzalietin.imdbproject.moviereview.infrastructure.broker.events");
        props.put(JacksonJsonDeserializer.TYPE_MAPPINGS,
            "review_created:me.mzalietin.imdbproject.moviereview.infrastructure.broker.events.MovieReviewCreated,"
            + " review_updated:me.mzalietin.imdbproject.moviereview.infrastructure.broker.events.MovieReviewUpdated,"
            + " review_deleted:me.mzalietin.imdbproject.moviereview.infrastructure.broker.events.MovieReviewDeleted");

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Override
    public void configureKafkaListeners(final KafkaListenerEndpointRegistrar registrar) {
        registrar.setValidator(this.validator);
    }
}
