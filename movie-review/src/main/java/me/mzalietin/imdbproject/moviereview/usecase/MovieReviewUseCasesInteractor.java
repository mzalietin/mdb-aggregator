package me.mzalietin.imdbproject.moviereview.usecase;

//import me.mzalietin.imdbproject.movie.usecase.ports.MovieUseCases;
import me.mzalietin.imdbproject.moviereview.domain.MovieReviews;
import me.mzalietin.imdbproject.moviereview.usecase.ports.MovieReviewDataAccess;
import me.mzalietin.imdbproject.moviereview.usecase.ports.MovieReviewUseCases;

public class MovieReviewUseCasesInteractor implements MovieReviewUseCases {
    private final MovieReviewDataAccess movieReviewDataAccess;
//    private final MovieUseCases movieUseCases;

    public MovieReviewUseCasesInteractor(
//        final MovieUseCases movieUseCases,
        final MovieReviewDataAccess movieReviewDataAccess)
    {
//        this.movieUseCases = movieUseCases;
        this.movieReviewDataAccess = movieReviewDataAccess;
    }

    //TODO refactor
    @Override
    public void save(final MovieReviews reviews) {
//        if (!reviews.isEmpty()) {
//
//            List<String> movieIds = reviews.getUniqueMovieIds();
//
//            for (String movieId : movieIds) {
//                List<MovieReview> movieReviews = reviews.getReviewsByMovie(movieId);
//
//                Movie movie = movieDataAccess.getById(movieId);
//                Integer reviewsCount = movieReviewDataAccess.countByMovieId(movieId);
//
//                Movie updatedMovie = movie.applyReviews(reviewsCount, movieReviews);
//
//                movieDataAccess.save(updatedMovie);
//            }
//
//            movieReviewDataAccess.save(reviews);
//        }
    }
}
