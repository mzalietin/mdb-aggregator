package com.mzaletsin.selfstudy.imdbaggregator.infrastructure;

import com.mzaletsin.selfstudy.imdbaggregator.models.entity.User;

public interface UserDao {

    Integer save(User user);

    User getByUsername(String username);

    void delete(String username);
}
