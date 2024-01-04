package me.mzalietin.imdbproject.movie.core.usecase;

import java.util.Collection;
import java.util.List;
import me.mzalietin.imdbproject.movie.core.domain.Movie;
import me.mzalietin.imdbproject.movie.core.domain.Ratings;
import me.mzalietin.imdbproject.movie.core.usecase.ports.MovieDataAccess;
import me.mzalietin.imdbproject.movie.core.usecase.ports.MovieUseCases;

public final class MovieUseCasesInteractor implements MovieUseCases {
    private final MovieDataAccess movieDataAccess;

    public MovieUseCasesInteractor(final MovieDataAccess movieDataAccess) {
        this.movieDataAccess = movieDataAccess;
    }

    @Override
    public String create(final Movie movie) {
        return movieDataAccess.save(movie);
    }

    @Override
    public Double findRating(final String movieName) {
        var movie = movieDataAccess.getByName(movieName);
        return movie.rating().doubleValue();
    }

    @Override
    public Collection<Movie> getTopRated(final Integer count) {
        return movieDataAccess.getTopRated(count);
    }

    @Override
    public Collection<Movie> getTopRatedByUser(final Integer count, final String username) {
        return movieDataAccess.getTopRated(count, username);
    }

    @Override
    public void updateMovies(final Ratings ratings) {
        if (!ratings.isEmpty()) {
            List<String> movieIds = ratings.getUniqueMovieIds();

            for (String movieId : movieIds) {
                int[] movieReviews = ratings.getRatingsByMovie(movieId);
                Movie movie = movieDataAccess.getById(movieId);
                Movie updatedMovie = movie.updateRating(movieReviews);
                movieDataAccess.save(updatedMovie);
            }
        }
    }
}
