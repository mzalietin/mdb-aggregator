package me.mzalietin.imdbproject.user.core.usecase.ports;

import me.mzalietin.imdbproject.user.core.domain.User;

public interface UserDataAccess {

    void save(User user);

    User getByUsername(String username);

    void delete(String username);
}
