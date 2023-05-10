package com.mzaletsin.selfstudy.imdbaggregator.usecase.implementation;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.User;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.UserDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.CreateUser;

final class CreateUserUC implements CreateUser {
    private final UserDataAccess userDataAccess;

    CreateUserUC(UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    @Override
    public Integer create(User user) {
        return userDataAccess.save(user);
    }
}
