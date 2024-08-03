package org.example.doaapiproject.services;

import org.example.doaapiproject.models.Institution;
import org.example.doaapiproject.models.Publication;
import org.example.doaapiproject.repositories.InstitutionIdRepository;
import org.example.doaapiproject.repositories.PublicationIdRepository;
import org.example.doaapiproject.repositories.PublicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PublicationService {
    private final PublicationRepository publicationRepository;
    private final InstitutionService institutionService;
    private final PublicationIdRepository publicationIdRepository;
    public PublicationService(PublicationRepository publicationRepository, InstitutionService institutionService, PublicationIdRepository publicationIdRepository) {
        this.publicationRepository = publicationRepository;
        this.institutionService = institutionService;
        this.publicationIdRepository = publicationIdRepository;
    }

    // create
    @Transactional
    public Publication createPublication(Publication publication) {
        Institution institution = institutionService.findInstitution(publication.getInstitutionId());
        publication.setInstitutionName(institution.getName());
        publication.setInstitutionPhoto(institution.getPhoto());

        institution.setId(publicationIdRepository.findPublicationId());

        return publicationRepository.save(publication);
    }

    // find publication by id
    public Publication findPublication(String id) {
        return publicationRepository.findById(Integer.parseInt(id)).orElseThrow(() ->
                new RuntimeException("publication not found"));
    }

    // delete
    @Transactional
    public Publication deletePublication(String id) {
        Publication publication = findPublication(id);
        publicationRepository.deleteById(Integer.parseInt(id));
        return publication;
    }

    // get all publication
    public List<Publication> getAllPublication() {
        return publicationRepository.findAll();
    }

    // get publication by institution's id
    public List<Publication> getAllPublicationOfInstitution(String id_institution) {
        return publicationRepository.findPublicationsByInstitutionId(id_institution);
    }
}
