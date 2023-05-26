package com.mzaletsin.selfstudy.imdbaggregator.usecase.impl;

import com.mzaletsin.selfstudy.imdbaggregator.domain.port.UserDataAccess;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.DeleteUser;

final class DeleteUserUC implements DeleteUser {
    private final UserDataAccess userDataAccess;

    DeleteUserUC(UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    @Override
    public void delete(String username) {
        userDataAccess.delete(username);
    }
}
