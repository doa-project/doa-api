package org.example.doaapiproject.repositories;

import org.example.doaapiproject.models.Campaign;
import org.example.doaapiproject.models.Institution;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface InstitutionRepository extends MongoRepository<Institution, Integer> {
    Institution findInstitutionByInstitutionId (Integer id);
    Institution findInstitutionByEmail (String email);
}
