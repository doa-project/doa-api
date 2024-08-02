package org.example.doaapiproject.services;

import org.example.doaapiproject.models.Campaign;
import org.example.doaapiproject.models.Institution;
import org.example.doaapiproject.repositories.CampaignRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class CampaignService {
    private final CampaignRepository campaignRepository;
    private final InstitutionService institutionService;
    public CampaignService(CampaignRepository campaignRepository, InstitutionService institutionService) {
        this.campaignRepository = campaignRepository;
        this.institutionService = institutionService;
    }

    // create
    @Transactional
    public Campaign createCampaign(Campaign campaign) throws RuntimeException {
        try {
            institutionService.findInstitutionByName(campaign.getInstitutionName());
        } catch (RuntimeException r) {
            throw new RuntimeException(r.getMessage());
        }

        if (Date.valueOf(campaign.getEndDate()).before(Date.valueOf(LocalDate.now())) || Date.valueOf(campaign.getEndDate()).equals(Date.valueOf(LocalDate.now()))) {
            throw new RuntimeException("the end date must be after today");
        }
        return campaignRepository.save(campaign);
    }

    // find campaign by id
    public Campaign findCampaign(String id) {
        return campaignRepository.findById(Integer.parseInt(id)).orElseThrow(() ->
                new RuntimeException("campaign not found"));
    }

    // delete
    @Transactional
    public Campaign deleteCampaign(String id) {
        Campaign campaign = findCampaign(id);
        campaignRepository.deleteById(Integer.parseInt(id));
        return campaign;
    }
}
