package org.example.doaapiproject.services;

import org.example.doaapiproject.models.Campaign;
import org.example.doaapiproject.models.Institution;
import org.example.doaapiproject.repositories.CampaignIdRepository;
import org.example.doaapiproject.repositories.CampaignRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class CampaignService {
    private final CampaignRepository campaignRepository;
    private final InstitutionService institutionService;
    private final CampaignIdRepository campaignIdRepository;
    public CampaignService(CampaignRepository campaignRepository, InstitutionService institutionService, CampaignIdRepository campaignIdRepository) {
        this.campaignRepository = campaignRepository;
        this.institutionService = institutionService;
        this.campaignIdRepository = campaignIdRepository;
    }

    // create
    @Transactional
    public Campaign createCampaign(Campaign campaign) throws RuntimeException{
        Institution institution = institutionService.findInstitution(campaign.getInstitutionId());
        campaign.setInstitutionName(institution.getName());
        campaign.setInstitutionPhoto(institution.getPhoto());

        if (Date.valueOf(campaign.getEndDate()).before(Date.valueOf(LocalDate.now())) || Date.valueOf(campaign.getEndDate()).equals(Date.valueOf(LocalDate.now()))) {
            throw new RuntimeException("the end date must be after today");
        }

        Integer id = campaignIdRepository.findCampaignId();
        campaign.setId(id);

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

    // get all campaign
    public List<Campaign> getAllCampaign() {
        return campaignRepository.findAll();
    }

    // get campaign by institution's id
    public List<Campaign> getAllCampaignOfInstitution(String id_institution) {
        return campaignRepository.findCampaignsByInstitutionId(id_institution);
    }
}
