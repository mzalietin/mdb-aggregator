package com.mzaletsin.selfstudy.imdbaggregator.usecase.implementation;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.User;
import com.mzaletsin.selfstudy.imdbaggregator.domain.port.UserDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.FindUser;

final class FindUserUC implements FindUser {
    private final UserDataAccess userDataAccess;

    FindUserUC(UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    @Override
    public User find(String username) {
        return userDataAccess.getByUsername(username);
    }
}
