package me.mzalietin.imdbproject.moviereview;

import java.util.HashMap;
import java.util.Map;
import me.mzalietin.imdbproject.moviereview.domain.model.MovieReviewKey;
import me.mzalietin.imdbproject.moviereview.infrastructure.broker.events.MovieReviewCreatedEvent;
import me.mzalietin.imdbproject.moviereview.infrastructure.broker.events.MovieReviewDeletedEvent;
import me.mzalietin.imdbproject.moviereview.infrastructure.broker.events.MovieReviewUpdatedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJacksonJsonMessageConverter;
import org.springframework.kafka.support.mapping.DefaultJacksonJavaTypeMapper;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;

@ComponentScan
@EnableAutoConfiguration
@EnableKafka
@Configuration
@PropertySource("classpath:movie-review-context.properties")
public class MovieReviewContextConfig {

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<MovieReviewKey, Object>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<MovieReviewKey, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(1);
        factory.getContainerProperties().setPollTimeout(3000);
        factory.setRecordMessageConverter(multiTypeConverter());
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
        props.put(JacksonJsonDeserializer.KEY_DEFAULT_TYPE, Object.class);
        props.put(JacksonJsonDeserializer.VALUE_DEFAULT_TYPE, Object.class);

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public RecordMessageConverter multiTypeConverter() {
        StringJacksonJsonMessageConverter converter = new StringJacksonJsonMessageConverter();
        DefaultJacksonJavaTypeMapper typeMapper = new DefaultJacksonJavaTypeMapper();
        typeMapper.setTypePrecedence(DefaultJacksonJavaTypeMapper.TypePrecedence.TYPE_ID);
        typeMapper.addTrustedPackages("me.mzalietin.imdbproject.moviereview.infrastructure.broker.events");
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put("review_created", MovieReviewCreatedEvent.class);
        mappings.put("review_updated", MovieReviewUpdatedEvent.class);
        mappings.put("review_deleted", MovieReviewDeletedEvent.class);
        typeMapper.setIdClassMapping(mappings);
        converter.setTypeMapper(typeMapper);
        return converter;
    }
}
