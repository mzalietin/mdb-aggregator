package com.mzaletsin.selfstudy.imdbaggregator.infrastructure.repository;

import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.FIRST_NAME;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.LAST_NAME;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.USERNAME;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.USER_AGE;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.defaultUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.PersistenceConfig;
import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = PersistenceConfig.class)
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    UserRepository repository;

    @Test
    void should_find_no_users_if_repository_is_empty() {
        var users = repository.findAll();

        assertTrue(users.isEmpty());
    }

    @Test
    void should_store_a_user() {
        var user = User.fromDomain(defaultUser());

        var savedUser = repository.saveAndFlush(user);

        assertEquals(USERNAME, savedUser.getUsername());
        assertEquals(FIRST_NAME, savedUser.getFirstName());
        assertEquals(LAST_NAME, savedUser.getLastName());
        assertEquals(USER_AGE, savedUser.getAge());
    }

    @Test
    void should_find_user_by_id() {
        var user = User.fromDomain(defaultUser());
        entityManager.persist(user);

        var foundUser = repository.findById(USERNAME);

        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
    }

    @Test
    void should_delete_user_by_id() {
        var user = User.fromDomain(defaultUser());
        entityManager.persist(user);

        repository.deleteById(USERNAME);

        var users = repository.findAll();
        assertTrue(users.isEmpty());
    }
}
