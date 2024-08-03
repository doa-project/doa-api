package org.example.doaapiproject.repositories;

import org.example.doaapiproject.models.CampaignId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CampaignIdRepository extends MongoRepository<CampaignId, Integer> {
    @Query(value = "{}", sort = "{ 'campaignId': -1 }")
    Integer findCampaignId();
}
