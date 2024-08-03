package org.example.doaapiproject.repositories;

import org.example.doaapiproject.models.InstitutionId;
import org.example.doaapiproject.models.PublicationId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PublicationIdRepository extends MongoRepository<PublicationId, Integer> {
    @Query(value = "{}", sort = "{ 'publicationId': -1 }")
    Integer findPublicationId();
}
