package com.mzaletsin.selfstudy.imdbaggregator.usecase;

import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.MovieReviewDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.UserDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.impl.FindUserUC;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.impl.SaveReviewsUC;
import jakarta.validation.Validator;

public final class UseCaseFactory {
    private final UserDataAccess userDataAccess;
    private final MovieDataAccess movieDataAccess;
    private final MovieReviewDataAccess movieReviewDataAccess;
    private final Validator validator;

    public UseCaseFactory(
        final UserDataAccess userDataAccess,
        final MovieDataAccess movieDataAccess,
        final MovieReviewDataAccess movieReviewDataAccess,
        final Validator validator)
    {
        this.userDataAccess = userDataAccess;
        this.movieDataAccess = movieDataAccess;
        this.movieReviewDataAccess = movieReviewDataAccess;
        this.validator = validator;
    }

    public SaveReviews getSaveReviewsCase() {
        return new SaveReviewsUC(validator, movieDataAccess, movieReviewDataAccess);
    }

    public FindUser getFindUserCase() {
        return new FindUserUC(userDataAccess);
    }
}
