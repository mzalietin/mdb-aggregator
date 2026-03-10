package me.mzalietin.imdbproject.moviereview.infrastructure.broker;

import java.util.List;
import me.mzalietin.imdbproject.moviereview.domain.service.spi.MovieReviewDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MovieReviewEventListener {

    @Autowired
    MovieReviewDataAccess movieReviewDataAccess;

    @KafkaListener(
        id = "movie-review-group",
        topics = "movie-review",
        batch = "true",
        clientIdPrefix = "MovieReviewEventListener"
    )
    public void listen(List<String> in) {
        in.forEach(System.out::println);
    }
}
