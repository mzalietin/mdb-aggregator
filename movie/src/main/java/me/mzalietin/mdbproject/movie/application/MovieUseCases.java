package me.mzalietin.mdbproject.movie.application;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface MovieUseCases {

    Long create(String name, LocalDate releaseDate);

    void updateRating(Long id, BigDecimal newRating, Integer newReviewsCount);
}
