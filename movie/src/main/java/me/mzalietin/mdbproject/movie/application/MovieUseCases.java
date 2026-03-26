package me.mzalietin.mdbproject.movie.application;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface MovieUseCases {

    String create(String name, LocalDate releaseDate);

    void updateRating(String id, BigDecimal newRating, Integer newReviewsCount);
}
