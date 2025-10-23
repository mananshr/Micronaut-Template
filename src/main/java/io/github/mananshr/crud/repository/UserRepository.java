package io.github.mananshr.crud.repository;

import io.github.mananshr.crud.model.User;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;

@MongoRepository
public interface UserRepository extends CrudRepository<User, String> {
    User findByEmail(String email);
    User findByAuth0Id(String auth0Id);
}
