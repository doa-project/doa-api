package org.example.doaapiproject.repositories;

import org.example.doaapiproject.models.Campaign;
import org.example.doaapiproject.models.InstitutionId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface InstitutionIdRepository extends MongoRepository<InstitutionId, Integer> {
    @Query(value = "{}", sort = "{ 'institutionId': -1 }")
    Integer findInstitutionId();
}
