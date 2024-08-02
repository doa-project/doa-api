package org.example.doaapiproject.services;

import org.example.doaapiproject.models.Campaign;
import org.example.doaapiproject.models.Institution;
import org.example.doaapiproject.repositories.CampaignRepository;
import org.example.doaapiproject.repositories.InstitutionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;

@Service
public class InstitutionService {
    private final InstitutionRepository institutionRepository;
    private final CampaignRepository campaignRepository;
    public InstitutionService(InstitutionRepository institutionRepository, CampaignRepository campaignRepository) {
        this.institutionRepository = institutionRepository;
        this.campaignRepository = campaignRepository;
    }

    // create
    @Transactional
    public Institution createInstitution(Institution institution) {
        return institutionRepository.save(institution);
    }

    // find institution by id
    public Institution findInstitution(String id) {
        return institutionRepository.findById(Integer.parseInt(id)).orElseThrow(() ->
                new RuntimeException("institution not found"));
    }

    // find institution by name
    public Institution findInstitutionByName(String name) throws RuntimeException{
        Institution institution = institutionRepository.findInstitutionByNameIgnoreCase(name);
        if (institution != null) {
            return institution;
        } else {
            throw new RuntimeException("institution not found");
        }
    }

    // find campaigns by institution's name
    public List<Campaign> findCampaignsByInstitutionName(String name) throws RuntimeException{
        List<Campaign> campaigns = campaignRepository.findCampaignsByInstitutionNameIgnoreCase(name);
        if (campaigns.isEmpty()) {
            return campaigns;
        } else {
            throw new RuntimeException("institution cannot be deleted because it has campaign(s)");
        }
    }

    // delete
    @Transactional
    public Institution deleteInstitution(String id) throws RuntimeException{
        Institution institution = findInstitution(id);
        try {
            findCampaignsByInstitutionName(institution.getName());
        } catch (RuntimeException r) {
            throw new RuntimeException(r.getMessage());
        }
        institutionRepository.deleteById(Integer.parseInt(id));
        return institution;
    }
}
