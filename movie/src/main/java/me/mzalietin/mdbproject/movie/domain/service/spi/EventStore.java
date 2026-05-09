package me.mzalietin.mdbproject.movie.domain.service.spi;

import java.math.BigDecimal;
import me.mzalietin.mdbproject.movie.domain.model.Movie;

public interface EventStore {

    void sendCreated(Movie movie);

    void sendRatingUpdated(Long movieId, BigDecimal newRating, Integer newReviewsCount);
}
