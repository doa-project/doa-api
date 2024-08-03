package org.example.doaapiproject.repositories;

import org.example.doaapiproject.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {
    User findUserByEmail(String email);
    User findUserByUserId (Integer id);
}
