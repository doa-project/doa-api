package org.example.doaapiproject.repositories;

import org.example.doaapiproject.models.Capaign;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CapaignRepository extends MongoRepository<Capaign, Integer> {
}
