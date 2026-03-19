package me.mzalietin.mdbproject.user.infrastructure.repo;

import static me.mzalietin.mdbproject.user.infrastructure.repo.UserEntity.fromDomain;
import static me.mzalietin.mdbproject.user.infrastructure.repo.UserEntity.toDomain;

import me.mzalietin.mdbproject.user.domain.model.User;
import me.mzalietin.mdbproject.user.domain.model.UserNotFoundException;
import me.mzalietin.mdbproject.user.domain.service.spi.UserDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao implements UserDataAccess {
    private final UserRepository repo;

    @Autowired
    public UserDao(final UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public void save(User user) {
        var persistenceUser = fromDomain(user);
        repo.save(persistenceUser);
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
