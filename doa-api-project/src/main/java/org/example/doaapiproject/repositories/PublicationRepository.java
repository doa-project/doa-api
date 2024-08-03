package org.example.doaapiproject.repositories;

import org.example.doaapiproject.models.Publication;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PublicationRepository extends MongoRepository<Publication, Integer> {
    List<Publication> findPublicationsByInstitutionId (String id);
    Publication findPublicationByPublicationId (Integer id);
}
