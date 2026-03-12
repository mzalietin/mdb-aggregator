package me.mzalietin.imdbproject.movierating.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.stream.IntStream;

public record Movie(String id, String name, LocalDate releaseDate, BigDecimal rating, Integer reviewsCount) {

    public Movie updateRating(int[] newRatings) {
        if (newRatings.length == 0) {
            throw new IllegalArgumentException("Ratings array cannot be empty");
        } else {
            BigDecimal currentVolume = rating.multiply(BigDecimal.valueOf(reviewsCount));

            int newReviewsVolume = IntStream.of(newRatings).sum();

            BigDecimal newVolume = currentVolume.add(BigDecimal.valueOf(newReviewsVolume));
            int newReviewsCount = reviewsCount + newRatings.length;
            BigDecimal newRating = newVolume.setScale(2, RoundingMode.HALF_UP)
                .divide(BigDecimal.valueOf(newReviewsCount));

            return new Movie(id, name, releaseDate, newRating, newReviewsCount);
        }
    }
}
