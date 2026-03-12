package me.mzalietin.imdbproject.movierating.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public record Ratings(Collection<MovieRating> ratings) {

    public List<String> getUniqueMovieIds() {
        return ratings.stream()
            .map(MovieRating::movieId)
            .distinct()
            .toList();
    }

    public int[] getRatingsByMovie(String movieId) {
        return ratings.stream()
            .filter(r -> r.movieId().equals(movieId))
            .mapToInt(MovieRating::rating)
            .toArray();
    }

    public boolean isEmpty() {
        return ratings.isEmpty();
    }

    @Override
    public Collection<MovieRating> ratings() {
        return new ArrayList<>(ratings);
    }
}
