package com.mzaletsin.selfstudy.imdbaggregator.usecase.impl;

import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.MOVIE_ID;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.testMovieReviews;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures;
import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.Movie;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieReviewDataAccess;
import jakarta.validation.Validator;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
        var movie = DomainTestFixtures.initialTestMovie();
        var reviews = testMovieReviews();

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
