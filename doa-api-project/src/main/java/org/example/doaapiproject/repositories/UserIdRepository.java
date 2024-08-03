package org.example.doaapiproject.repositories;

import org.example.doaapiproject.models.Campaign;
import org.example.doaapiproject.models.UserId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserIdRepository extends MongoRepository<UserId, Integer> {
    @Query(value = "{}", sort = "{ 'userId': -1 }")
    Integer findUserId();
}
