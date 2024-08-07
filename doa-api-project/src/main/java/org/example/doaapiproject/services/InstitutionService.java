package org.example.doaapiproject.services;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.example.doaapiproject.models.Campaign;
import org.example.doaapiproject.models.Institution;
import org.example.doaapiproject.models.InstitutionId;
import org.example.doaapiproject.models.Publication;
import org.example.doaapiproject.repositories.CampaignRepository;
import org.example.doaapiproject.repositories.InstitutionIdRepository;
import org.example.doaapiproject.repositories.InstitutionRepository;
import org.example.doaapiproject.repositories.PublicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (institution.getInstitutionId() != null) {
            return institutionRepository.save(institution);
        } else {
            Integer id = institutionIdRepository.findInstitutionId();
            institution.setInstitutionId(id);
            institutionIdRepository.save(new InstitutionId(id + 1));
            return institutionRepository.save(institution);
        }
    }

    // find institution by id
    public Institution findInstitution(String id) {
        Institution institution = institutionRepository.findInstitutionByInstitutionId(Integer.parseInt(id));
        if (institution != null) {
            return institution;
        } else {
            throw new RuntimeException("institution not found");
        }
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

    // find institution by email
    public Institution findInstitutionByEmail (String email) throws RuntimeException {
        Institution institution = institutionRepository.findInstitutionByEmail(email);
        if (institution != null) {
            return institution;
        } else {
            throw new RuntimeException("institution not found");
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
