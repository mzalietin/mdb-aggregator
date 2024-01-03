package me.mzalietin.imdbproject.user.core.usecase;

import me.mzalietin.imdbproject.user.core.usecase.ports.UserDataAccess;
import me.mzalietin.imdbproject.user.core.domain.User;
import me.mzalietin.imdbproject.user.core.usecase.ports.UserUseCases;

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
