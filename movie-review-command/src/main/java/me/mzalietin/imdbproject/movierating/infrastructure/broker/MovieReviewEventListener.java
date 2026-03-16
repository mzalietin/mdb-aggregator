package me.mzalietin.imdbproject.movierating.infrastructure.broker;

import static java.util.stream.Collectors.groupingByConcurrent;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import me.mzalietin.imdbproject.movierating.application.MovieUseCases;
import me.mzalietin.imdbproject.movierating.domain.model.MovieReviewKey;
import me.mzalietin.imdbproject.movierating.domain.model.events.MovieRatingImpactEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class MovieReviewEventListener {

    private static final Logger logger = LoggerFactory.getLogger(MovieReviewEventListener.class);

    @Autowired
    ExecutorService kafkaProcessingPool;

    @Autowired
    MovieUseCases movieUseCases;

    @KafkaListener(
        id = "movie-rating-group",
        topics = "movie-review",
        batch = "true",
        clientIdPrefix = "MovieReviewRatingConsumer",
        containerFactory = "moviereviewKafkaListenerContainerFactory"
    )
    public void listen(List<ConsumerRecord<MovieReviewKey, MovieRatingImpactEvent>> records, Acknowledgment ack) {
        logger.info("Received Movie Review events batch size = {}", records.size());

        Map<String, List<MovieRatingImpactEvent>> eventsByMovie = records.stream()
            .collect(groupingByConcurrent(r -> r.key().movieId(), mapping(ConsumerRecord::value, toList())));

        List<CompletableFuture<Void>> futures = eventsByMovie.entrySet().stream()
            .map(entry -> CompletableFuture.runAsync(
                () -> movieUseCases.updateRating(entry.getKey(), entry.getValue()),
                kafkaProcessingPool))
            .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        ack.acknowledge();
    }
}
