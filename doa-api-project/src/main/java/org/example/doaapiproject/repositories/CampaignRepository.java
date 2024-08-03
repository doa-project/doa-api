package org.example.doaapiproject.repositories;

import org.example.doaapiproject.models.Campaign;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CampaignRepository extends MongoRepository<Campaign, Integer> {
    List<Campaign> findCampaignsByInstitutionId (String id);
    Campaign findCampaignByCampaignId (Integer id);
}
