package org.example.doaapiproject.services;

import org.example.doaapiproject.models.Campaign;
import org.example.doaapiproject.models.Institution;
import org.example.doaapiproject.models.Publication;
import org.example.doaapiproject.repositories.CampaignRepository;
import org.example.doaapiproject.repositories.InstitutionIdRepository;
import org.example.doaapiproject.repositories.InstitutionRepository;
import org.example.doaapiproject.repositories.PublicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;

@Service
public class InstitutionService {
    private final InstitutionRepository institutionRepository;
    private final CampaignRepository campaignRepository;
    private final PublicationRepository publicationRepository;
    private final InstitutionIdRepository institutionIdRepository;
    public InstitutionService(InstitutionRepository institutionRepository, CampaignRepository campaignRepository, PublicationRepository publicationRepository, InstitutionIdRepository institutionIdRepository) {
        this.institutionRepository = institutionRepository;
        this.campaignRepository = campaignRepository;
        this.publicationRepository = publicationRepository;
        this.institutionIdRepository = institutionIdRepository;
    }

    // create
    @Transactional
    public Institution createInstitution(Institution institution) {
        institution.setId(institutionIdRepository.findInstitutionId());
        return institutionRepository.save(institution);
    }

    // find institution by id
    public Institution findInstitution(String id) {
        return institutionRepository.findById(Integer.parseInt(id)).orElseThrow(() ->
                new RuntimeException("institution not found"));
    }

    // find campaigns by institution's id
    public List<Campaign> findCampaignsByInstitutionId(String id) throws RuntimeException{
        List<Campaign> campaigns = campaignRepository.findCampaignsByInstitutionId(id);
        if (campaigns.isEmpty()) {
            return campaigns;
        } else {
            throw new RuntimeException("institution cannot be deleted because it has campaign(s)");
        }
    }

    // find publications by institution's id
    public List<Publication> findPublicationsByInstitutionId(String id) throws RuntimeException{
        List<Publication> publications = publicationRepository.findPublicationsByInstitutionId(id);
        if (publications.isEmpty()) {
            return publications;
        } else {
            throw new RuntimeException("institution cannot be deleted because it has publication(s)");
        }
    }

    // delete
    @Transactional
    public Institution deleteInstitution(String id) {
        Institution institution = findInstitution(id);
        findCampaignsByInstitutionId(String.valueOf(institution.getId()));
        findPublicationsByInstitutionId(String.valueOf(institution.getId()));

        institutionRepository.deleteById(Integer.parseInt(id));
        return institution;
    }
}
