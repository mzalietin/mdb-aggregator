package com.mzaletsin.selfstudy.imdbaggregator.usecase;

import com.mzaletsin.selfstudy.imdbaggregator.domain.entity.User;

public interface FindUser {

    User find(String username);
}
