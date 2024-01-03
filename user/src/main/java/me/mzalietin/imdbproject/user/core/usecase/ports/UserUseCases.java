package me.mzalietin.imdbproject.user.core.usecase.ports;

import me.mzalietin.imdbproject.user.core.domain.User;
import me.mzalietin.imdbproject.user.core.domain.UserNotFoundException;

public interface UserUseCases {

    void create(User user);

    User find(String username) throws UserNotFoundException;

    void delete(String username);
}
