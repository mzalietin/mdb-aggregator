package me.mzalietin.mdbproject.user.infrastructure.repo;

import java.util.Optional;
import me.mzalietin.mdbproject.user.domain.model.ResourceAlreadyExistsException;
import me.mzalietin.mdbproject.user.domain.model.User;
import me.mzalietin.mdbproject.user.domain.service.spi.UserDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao implements UserDataAccess {

    @Autowired
    UserRepository repository;

    @Override
    public Optional<User> getUser(final String username) {
        return repository.findById(username).map(UserEntity::toModel);
    }

    @Override
    public void createUser(final User user) {
        if (repository.existsById(user.username())){
            throw new ResourceAlreadyExistsException("User Key = " + user.username());
        } else {
            repository.save(new UserEntity(user));
        }
    }

    @Override
    public void deleteUser(final String username) {
        repository.deleteById(username);
    }
}
