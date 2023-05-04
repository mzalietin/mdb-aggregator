package com.mzaletsin.selfstudy.imdbaggregator.domain.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Collection;

public class Movie {
    private final String id;
    private final String name;
    private final LocalDate releaseDate;
    private final BigDecimal rating;

    Movie(String id, String name, LocalDate releaseDate, BigDecimal rating) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public Movie applyReviews(Integer currentReviewsCount, Collection<MovieReview> newReviews) {
        if (newReviews.isEmpty()) {
            return this;
        } else {
            BigDecimal currentVolume = rating.multiply(BigDecimal.valueOf(currentReviewsCount));

            int newReviewsVolume = newReviews.stream()
                .mapToInt(MovieReview::getRating)
                .sum();

            BigDecimal newVolume = currentVolume.add(BigDecimal.valueOf(newReviewsVolume));
            int newReviewsCount = currentReviewsCount + newReviews.size();
            BigDecimal newRating = newVolume.setScale(2, RoundingMode.HALF_UP)
                .divide(BigDecimal.valueOf(newReviewsCount));

            return new Movie(id, name, releaseDate, newRating);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public BigDecimal getRating() {
        return rating;
    }
}
