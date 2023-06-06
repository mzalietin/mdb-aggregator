package com.mzaletsin.selfstudy.imdbaggregator.usecase.impl;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.User;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.port.in.UserUseCases;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.port.out.UserDataAccess;
import jakarta.validation.Validator;

public final class UserUseCasesInteractor extends ValidatingInteractor implements UserUseCases {
    private final UserDataAccess userDataAccess;

    public UserUseCasesInteractor(final Validator validator, final UserDataAccess userDataAccess) {
        super(validator);
        this.userDataAccess = userDataAccess;
    }

    @Override
    public void create(final User user) {
        validate(user);
        userDataAccess.save(user);
    }

    @Override
    public User find(final String username) {
        return userDataAccess.getByUsername(username);
    }

    @Override
    public void delete(final String username) {
        userDataAccess.delete(username);
    }
}
