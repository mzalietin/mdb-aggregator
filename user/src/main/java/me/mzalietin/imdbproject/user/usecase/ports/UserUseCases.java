package me.mzalietin.imdbproject.user.usecase.ports;

import me.mzalietin.imdbproject.user.domain.User;
import me.mzalietin.imdbproject.user.domain.UserNotFoundException;

public interface UserUseCases {

    void create(User user);

    User find(String username) throws UserNotFoundException;

    void delete(String username);
}
