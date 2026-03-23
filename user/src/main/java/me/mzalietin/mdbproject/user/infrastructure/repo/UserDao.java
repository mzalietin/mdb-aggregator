package me.mzalietin.mdbproject.user.infrastructure.repo;

import me.mzalietin.mdbproject.user.domain.service.spi.UserDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao implements UserDataAccess {

    @Autowired
    UserRepository repository;

    @Override
    public void deleteUser(final String username) {
        repository.deleteById(username);
    }
}
