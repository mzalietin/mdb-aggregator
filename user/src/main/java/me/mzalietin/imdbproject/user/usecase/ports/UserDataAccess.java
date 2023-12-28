package me.mzalietin.imdbproject.user.usecase.ports;

import me.mzalietin.imdbproject.user.domain.User;

public interface UserDataAccess {

    void save(User user);

    User getByUsername(String username);

    void delete(String username);
}
