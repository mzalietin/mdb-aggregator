package me.mzalietin.mdbproject.user.domain.service;

import me.mzalietin.mdbproject.user.domain.model.User;
import me.mzalietin.mdbproject.user.domain.service.spi.UserDataAccess;
import me.mzalietin.mdbproject.user.domain.service.spi.UserUseCases;

public final class UserUseCasesInteractor implements UserUseCases {
    private final UserDataAccess userDataAccess;

    public UserUseCasesInteractor(final UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    @Override
    public void create(final User user) {
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
