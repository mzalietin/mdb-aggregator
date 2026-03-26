package me.mzalietin.mdbproject.user.application;

import me.mzalietin.mdbproject.user.domain.model.User;

public interface UserUseCases {

    void create(User user);

    void delete(String username);
}
