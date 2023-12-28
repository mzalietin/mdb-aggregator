package me.mzalietin.imdbproject.user.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static me.mzalietin.imdbproject.user.domain.DomainTestFixtures.*;

class UserTest {

    @Test
    void givenUser_whenGetValue_thenReturnsValue() {
        var user = new User(USERNAME, FIRST_NAME, LAST_NAME, USER_AGE);

        Assertions.assertEquals(USERNAME, user.getUsername());
        Assertions.assertEquals(FIRST_NAME, user.getFirstName());
        Assertions.assertEquals(LAST_NAME, user.getLastName());
        Assertions.assertEquals(USER_AGE, user.getAge());
    }
}
