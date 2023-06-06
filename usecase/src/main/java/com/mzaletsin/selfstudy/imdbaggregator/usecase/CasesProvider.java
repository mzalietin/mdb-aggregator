package com.mzaletsin.selfstudy.imdbaggregator.usecase;

import com.mzaletsin.selfstudy.imdbaggregator.usecase.impl.MovieReviewUseCasesInteractor;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.impl.MovieUseCasesInteractor;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.impl.UserUseCasesInteractor;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.port.in.MovieReviewUseCases;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.port.in.MovieUseCases;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.port.in.UserUseCases;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.port.out.MovieDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.port.out.MovieReviewDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.port.out.UserDataAccess;
import jakarta.validation.Validator;

public final class CasesProvider {
    private final MovieUseCases movieUseCases;
    private final UserUseCases userUseCases;
    private final MovieReviewUseCases movieReviewUseCases;

    public CasesProvider(final MovieDataAccess movieDataAccess,
                    final UserDataAccess userDataAccess,
                    final MovieReviewDataAccess movieReviewDataAccess,
                    final Validator validator) {
        this.movieUseCases = new MovieUseCasesInteractor(validator, movieDataAccess);
        this.userUseCases = new UserUseCasesInteractor(validator, userDataAccess);
        this.movieReviewUseCases = new MovieReviewUseCasesInteractor(validator, movieDataAccess, movieReviewDataAccess);
    }

    public MovieUseCases getMovieUseCases() {
        return movieUseCases;
    }

    public UserUseCases getUserUseCases() {
        return userUseCases;
    }

    public MovieReviewUseCases getMovieReviewUseCases() {
        return movieReviewUseCases;
    }
}
