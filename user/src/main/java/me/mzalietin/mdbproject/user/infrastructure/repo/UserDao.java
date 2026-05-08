package me.mzalietin.mdbproject.user.infrastructure.repo;

import me.mzalietin.mdbproject.user.domain.model.ResourceAlreadyExistsException;
import me.mzalietin.mdbproject.user.domain.model.ResourceNotFoundException;
import me.mzalietin.mdbproject.user.domain.model.User;
import me.mzalietin.mdbproject.user.domain.service.spi.UserDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.stereotype.Component;

@Component
public class UserDao implements UserDataAccess {

    @Autowired
    JdbcAggregateOperations jdbcOps;

    @Override
    public void createUser(final User user) {
        try {
            jdbcOps.insert(new UserEntity(user));
        } catch (DuplicateKeyException e) {
            throw new ResourceAlreadyExistsException("Username = '" + user.username() + "'", e);
        }
    }

    @Override
    public void deleteUser(final String username) {
        if (jdbcOps.existsById(username, UserEntity.class)) {
            jdbcOps.deleteById(username, UserEntity.class);
        } else {
            throw new ResourceNotFoundException("Username = '" + username + "' not found");
        }
    }
}
