package me.mzalietin.mdbproject.user.infrastructure.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, String>, CrudRepository<UserEntity, String> {
}
