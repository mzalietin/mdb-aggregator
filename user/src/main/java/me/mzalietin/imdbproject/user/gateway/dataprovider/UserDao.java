package me.mzalietin.imdbproject.user.gateway.dataprovider;

import static me.mzalietin.imdbproject.user.gateway.dataprovider.UserEntity.fromDomain;
import static me.mzalietin.imdbproject.user.gateway.dataprovider.UserEntity.toDomain;

import me.mzalietin.imdbproject.user.core.domain.User;
import me.mzalietin.imdbproject.user.core.domain.UserNotFoundException;
import me.mzalietin.imdbproject.user.core.usecase.ports.UserDataAccess;

public class UserDao implements UserDataAccess {
    private final UserRepository repo;

    public UserDao(final UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(User user) {
        var persistenceUser = fromDomain(user);
        repo.saveAndFlush(persistenceUser);
    }

    @Override
    public User getByUsername(String username) {
        var persistenceUser = repo.findById(username)
            .orElseThrow(() -> new UserNotFoundException(username));
        return toDomain(persistenceUser);
    }

    @Override
    public void delete(String username) {
        repo.deleteById(username);
    }
}
