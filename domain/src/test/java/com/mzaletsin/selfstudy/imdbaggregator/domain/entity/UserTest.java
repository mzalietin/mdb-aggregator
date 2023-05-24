package com.mzaletsin.selfstudy.imdbaggregator.domain.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.FIRST_NAME;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.LAST_NAME;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.USERNAME;
import static com.mzaletsin.selfstudy.imdbaggregator.domain.entity.DomainTestFixtures.USER_AGE;

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
