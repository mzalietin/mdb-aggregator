package com.mzaletsin.selfstudy.imdbaggregator.infrastructure.repository;

import com.mzaletsin.selfstudy.imdbaggregator.infrastructure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
