package com.mzaletsin.selfstudy.imdbaggregator.infrastructure.impl;

import static com.mzaletsin.selfstudy.imdbaggregator.infrastructure.entity.User.fromDomain;
import static com.mzaletsin.selfstudy.imdbaggregator.infrastructure.entity.User.toDomain;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.User;
import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.repository.UserRepository;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.exception.UserNotFoundException;
import com.mzaletsin.selfstudy.imdbaggregator.usecase.port.out.UserDataAccess;

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
