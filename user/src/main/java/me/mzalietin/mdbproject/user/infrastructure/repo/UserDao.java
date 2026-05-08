package me.mzalietin.mdbproject.user.infrastructure.repo;

import me.mzalietin.mdbproject.user.domain.model.ResourceAlreadyExistsException;
import me.mzalietin.mdbproject.user.domain.model.ResourceNotFoundException;
import me.mzalietin.mdbproject.user.domain.model.User;
import me.mzalietin.mdbproject.user.domain.service.spi.UserDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDao implements UserDataAccess {

    @Autowired
    JdbcAggregateTemplate template;

    @Override
    public void createUser(final User user) {
        try {
            template.insert(new UserEntity(user));
        } catch (DuplicateKeyException e) {
            throw new ResourceAlreadyExistsException("Username = '" + user.username() + "'", e);
        }
    }

    @Override
    public void deleteUser(final String username) {
        if (template.existsById(username, UserEntity.class)) {
            template.deleteById(username, UserEntity.class);
        } else {
            throw new ResourceNotFoundException("Username = '" + username + "' not found");
        }
    }
}
