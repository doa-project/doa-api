package org.example.doaapiproject.repositories;

import org.example.doaapiproject.models.Institution;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InstitutionRepository extends MongoRepository<Institution, Integer> {
}
