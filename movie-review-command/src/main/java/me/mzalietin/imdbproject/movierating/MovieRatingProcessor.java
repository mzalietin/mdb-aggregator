package me.mzalietin.imdbproject.movierating;

import me.mzalietin.imdbproject.movierating.events.MovieRatingImpactEvent;
import me.mzalietin.imdbproject.movierating.events.MovieReviewKey;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieRatingProcessor {

    private static final Logger logger = LoggerFactory.getLogger(MovieRatingProcessor.class);

    @Autowired
    Serde<MovieReviewKey> keySerde;

    @Autowired
    Serde<MovieRatingImpactEvent> valueSerde;

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<MovieReviewKey, MovieRatingImpactEvent> messageStream = streamsBuilder
            .stream("movie-review", Consumed.with(keySerde, valueSerde))
            .peek((key, value) -> logger.info("Received event key={}, value={}", key, value));

        //        messageStream
        //            .mapValues((ValueMapper<String, String>) String::toLowerCase)
        //            .flatMapValues(value -> Arrays.asList(value.split("\\W+")))
        //            .groupBy((key, word) -> word, Grouped.with(STRING_SERDE, STRING_SERDE))
        //            .count()
        //            .toStream()
        //            .to("output-topic");
    }
}
