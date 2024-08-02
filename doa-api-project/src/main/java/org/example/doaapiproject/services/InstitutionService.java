package org.example.doaapiproject.services;

import org.example.doaapiproject.models.Institution;
import org.example.doaapiproject.repositories.InstitutionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;

@Service
public class InstitutionService {
    private final InstitutionRepository institutionRepository;
    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
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

    // delete
    @Transactional
    public Institution deleteInstitution(String id) {
        Institution institution = findInstitution(id);
        institutionRepository.deleteById(Integer.parseInt(id));
        return institution;
    }
}
