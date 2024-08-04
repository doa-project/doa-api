package org.example.doaapiproject.repositories;

import org.example.doaapiproject.models.Campaign;
import org.example.doaapiproject.models.UserId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserIdRepository extends MongoRepository<UserId, Integer> {
    @Aggregation(pipeline = {
            "{ '$sort': { 'userId': -1 } }",
            "{ '$limit': 1 }",
            "{ '$project': { 'userId': 1 } }"
    })
    Integer findUserId();
}
