package me.mzalietin.mdbproject.movierating;

import static org.apache.kafka.common.serialization.Serdes.String;

import java.math.BigDecimal;
import java.math.RoundingMode;
import me.mzalietin.mdbproject.movierating.event.in.MovieRatingEvent;
import me.mzalietin.mdbproject.movierating.event.in.MovieReviewKey;
import me.mzalietin.mdbproject.movierating.event.out.MovieRatingCalculated;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JacksonJsonSerde;
import org.springframework.stereotype.Component;

@Component
public class MovieRatingProcessor {

    private static final Logger logger = LoggerFactory.getLogger(MovieRatingProcessor.class);

    @Autowired
    Serde<MovieReviewKey> keySerde;

    @Autowired
    Serde<MovieRatingEvent> valueSerde;

    @Value("${movie-rating-aggregator.kafka.input-topic}")
    String inputTopic;

    @Value("${movie-rating-aggregator.kafka.output-topic}")
    String outputTopic;

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        streamsBuilder
            .stream(inputTopic, Consumed.with(keySerde, valueSerde))
            .peek((key, value) -> logger.info("Received event key={}, value={}", key, value))
            .mapValues(value -> new RatingData(value.absoluteRatingImpact(), value.reviewsCountImpact()))
            .groupBy((key, value) -> key.movieId(), Grouped.with(String(), new JacksonJsonSerde<>(RatingData.class)))
            .reduce((aggValue, newValue) -> {
                aggValue.absoluteRating += newValue.absoluteRating;
                aggValue.reviewsCount += newValue.reviewsCount;
                return aggValue;
            })
            .mapValues(v -> new MovieRatingCalculated(computeAverageRating(v), v.reviewsCount))
            .toStream()
            .peek((key, value) -> logger.info("Pushing event key={}, value={}", key, value))
            .to(outputTopic, Produced.with(String(), new JacksonJsonSerde<>(MovieRatingCalculated.class).noTypeInfo()));
    }

    static BigDecimal computeAverageRating(RatingData ratingData) {
        try {
            return new BigDecimal(ratingData.absoluteRating).divide(new BigDecimal(ratingData.reviewsCount), 2, RoundingMode.HALF_UP);
        } catch (ArithmeticException e) {
            logger.warn("computeAverageRating - rating will be defaulted to 0.00 - {}", e.getMessage());
            return BigDecimal.ZERO;
        }
    }
}
