package org.example.doaapiproject.repositories;

import org.example.doaapiproject.models.InstitutionId;
import org.example.doaapiproject.models.PublicationId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PublicationIdRepository extends MongoRepository<PublicationId, Integer> {
    @Aggregation(pipeline = {
            "{ '$sort': { 'publicationId': -1 } }",
            "{ '$limit': 1 }",
            "{ '$project': { 'publicationId': 1 } }"
    })
    Integer findPublicationId();
}
