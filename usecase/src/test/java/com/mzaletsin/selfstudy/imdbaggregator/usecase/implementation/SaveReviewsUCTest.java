package com.mzaletsin.selfstudy.imdbaggregator.usecase.implementation;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures;
import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.Movie;
import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.MovieReview;
import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.MovieReviews;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieReviewDataAccess;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.MOVIE_ID;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.USERNAME;

@ExtendWith(MockitoExtension.class)
class SaveReviewsUCTest {
    @Mock
    MovieDataAccess movieDataAccess;

    @Mock
    MovieReviewDataAccess movieReviewDataAccess;

    @Mock
    Validator validator;

    @InjectMocks
    SaveReviewsUC usecase;

    @Test
    void givenUseCase_whenSaveReviews_thenRecalculatesMovieRatingAndSavesReviews() {
        var movie = DomainTestFixtures.defaultMovie();
        var reviews = new MovieReviews(List.of(
            new MovieReview("user_0", MOVIE_ID, 6, "comment_0"),
            new MovieReview("user_1", MOVIE_ID, 6, "comment_1"),
            new MovieReview("user_2", MOVIE_ID, 7, "comment_2"),
            new MovieReview("user_3", MOVIE_ID, 7, "comment_3"),
            new MovieReview("user_4", MOVIE_ID, 8, "comment_4")));

        Mockito.when(movieDataAccess.getById(MOVIE_ID)).thenReturn(movie);

        usecase.save(reviews);

        var movieArgumentCaptor = ArgumentCaptor.forClass(Movie.class);
        Mockito.verify(validator).validate(reviews);
        Mockito.verify(movieDataAccess).save(movieArgumentCaptor.capture());
        Mockito.verify(movieReviewDataAccess).countByMovieId(MOVIE_ID);
        Mockito.verify(movieReviewDataAccess).save(reviews);

        var savedMovie = movieArgumentCaptor.getValue();
        Assertions.assertEquals(BigDecimal.valueOf(680, 2), savedMovie.getRating());
    }
}
