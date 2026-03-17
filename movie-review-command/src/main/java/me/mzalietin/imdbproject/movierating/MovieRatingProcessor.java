package me.mzalietin.imdbproject.movierating;

import static org.apache.kafka.common.serialization.Serdes.String;

import java.math.BigDecimal;
import java.math.RoundingMode;
import me.mzalietin.imdbproject.movierating.events.in.MovieRatingImpact;
import me.mzalietin.imdbproject.movierating.events.in.MovieReviewKey;
import me.mzalietin.imdbproject.movierating.events.out.MovieRatingUpdated;
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
    Serde<MovieRatingImpact> valueSerde;

    @Value("${kafka.output-topic}")
    String outputTopic;

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        streamsBuilder
            .stream("movie-review", Consumed.with(keySerde, valueSerde))
            .peek((key, value) -> logger.info("Received event key={}, value={}", key, value))
            .mapValues(value -> new RatingData(value.absoluteRatingImpact(), value.reviewsCountImpact()))
            .groupBy((key, value) -> key.movieId(), Grouped.with(String(), new JacksonJsonSerde<>(RatingData.class)))
            .reduce((aggValue, newValue) -> {
                aggValue.absoluteRating += newValue.absoluteRating;
                aggValue.reviewsCount += newValue.reviewsCount;
                return aggValue;
            })
            .mapValues(v -> new MovieRatingUpdated(computeAverageRating(v), v.reviewsCount))
            .toStream()
            .peek((key, value) -> logger.info("Pushing event key={}, value={}", key, value))
            .to(outputTopic, Produced.with(String(), new JacksonJsonSerde<>(MovieRatingUpdated.class)));
    }

    static BigDecimal computeAverageRating(RatingData ratingData) {
        try {
            return new BigDecimal(ratingData.absoluteRating).divide(new BigDecimal(ratingData.reviewsCount), 2, RoundingMode.HALF_UP);
        } catch (ArithmeticException e) {
            logger.error("computeAverageRating failed", e);
            return BigDecimal.ZERO;
        }
    }
}
