package org.example.doaapiproject.repositories;

import org.example.doaapiproject.models.Publication;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PublicationRepository extends MongoRepository<Publication, Integer> {
}
