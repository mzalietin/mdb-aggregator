package me.mzalietin.imdbproject.movie.domain.service.spi;

import java.math.BigDecimal;
import me.mzalietin.imdbproject.movie.domain.model.ResourceNotFoundException;

public interface MovieDataAccess {

    void updateRatingInfo(String movieId, BigDecimal rating, Integer reviewsCount) throws ResourceNotFoundException;
}
