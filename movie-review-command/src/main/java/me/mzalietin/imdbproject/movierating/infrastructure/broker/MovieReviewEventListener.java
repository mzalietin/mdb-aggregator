package me.mzalietin.imdbproject.movierating.infrastructure.broker;

import java.util.List;
import java.util.concurrent.ExecutorService;
import me.mzalietin.imdbproject.movierating.domain.model.MovieReviewKey;
import me.mzalietin.imdbproject.movierating.domain.model.events.RatingImpactEvent;
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

    @KafkaListener(
        id = "movie-rating-group",
        topics = "movie-review",
        batch = "true",
        clientIdPrefix = "MovieReviewRatingConsumer",
        containerFactory = "moviereviewKafkaListenerContainerFactory"
    )
    public void listen(List<ConsumerRecord<MovieReviewKey, RatingImpactEvent>> records, Acknowledgment ack) {

        logger.info("Received Movie Review events batch size = {}", records.size());

        records.forEach(record -> {
            logger.info(record.value().toString());
        });

//        final Map<String, List<ConsumerRecord<MovieReviewKey, RatingImpactEvent>>> byMovie =
//            records.stream().collect(Collectors.groupingBy(r -> r.key().movieId()));

        ack.acknowledge();
    }
}
