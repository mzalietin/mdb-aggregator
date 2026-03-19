package me.mzalietin.mdbproject.movierating;

import static org.apache.kafka.streams.StreamsConfig.APPLICATION_ID_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.EXACTLY_ONCE_V2;
import static org.apache.kafka.streams.StreamsConfig.PROCESSING_GUARANTEE_CONFIG;
import static org.springframework.kafka.streams.RecoveringDeserializationExceptionHandler.KSTREAM_DESERIALIZATION_RECOVERER;

import java.util.HashMap;
import java.util.Map;
import me.mzalietin.mdbproject.movierating.event.in.MovieRatingEvent;
import me.mzalietin.mdbproject.movierating.event.in.MovieReviewKey;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.listener.ConsumerRecordRecoverer;
import org.springframework.kafka.streams.RecoveringDeserializationExceptionHandler;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@EnableKafka
@EnableKafkaStreams
@Configuration
@ComponentScan
@PropertySource("classpath:movie-rating-aggregator.properties")
public class MovieRatingAggregatorConfig {

    @Value("${kafka.host}")
    String kafkaHost;

    @Value("${kafka.app-id}")
    String appId;

    @Value("${kafka.output-topic}")
    String outputTopic;

    private static final Logger logger = LoggerFactory.getLogger(MovieRatingAggregatorConfig.class);

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    KafkaStreamsConfiguration kStreamsConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(APPLICATION_ID_CONFIG, appId);
        props.put(BOOTSTRAP_SERVERS_CONFIG, kafkaHost);
        props.put(DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG, RecoveringDeserializationExceptionHandler.class);
        props.put(KSTREAM_DESERIALIZATION_RECOVERER, loggingRecoverer());
        props.put(PROCESSING_GUARANTEE_CONFIG, EXACTLY_ONCE_V2);
        return new KafkaStreamsConfiguration(props);
    }

    @Bean
    public ConsumerRecordRecoverer loggingRecoverer() {
        return (record, ex) -> logger.error("deserialization failed at record offset {}", record.offset(), ex);
    }

    @Bean
    public Serde<MovieReviewKey> keySerde() {
        var deserializer = new ValidatingDeserializer<>(new JacksonJsonDeserializer<MovieReviewKey>(), validator());
        deserializer.configure(Map.of(JacksonJsonDeserializer.KEY_DEFAULT_TYPE, MovieReviewKey.class), true);
        return Serdes.serdeFrom(new JacksonJsonSerializer<>(), deserializer);
    }

    @Bean
    public Serde<MovieRatingEvent> valueSerde() {
        var deserializer = new ValidatingDeserializer<>(new JacksonJsonDeserializer<MovieRatingEvent>(), validator());
        Map<String, Object> props = new HashMap<>();
        props.put(JacksonJsonDeserializer.TRUSTED_PACKAGES, "me.mzalietin.mdbproject.movierating.event.in");
        props.put(JacksonJsonDeserializer.TYPE_MAPPINGS,
            "review_created:me.mzalietin.mdbproject.movierating.event.in.MovieReviewCreated,"
                + " review_updated:me.mzalietin.mdbproject.movierating.event.in.MovieReviewUpdated,"
                + " review_deleted:me.mzalietin.mdbproject.movierating.event.in.MovieReviewDeleted");
        props.put(JacksonJsonDeserializer.USE_TYPE_INFO_HEADERS, true);
        deserializer.configure(props, false);
        return Serdes.serdeFrom(new JacksonJsonSerializer<>(), deserializer);
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    // -------- NON PROD CONFIG --------

    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHost);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic ratingOutputTopic() {
        return TopicBuilder.name(outputTopic)
            .partitions(1)
            .replicas(1)
            .compact()
            .build();
    }
}
