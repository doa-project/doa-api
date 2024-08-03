package org.example.doaapiproject.controllers;

import jakarta.validation.Valid;
import org.example.doaapiproject.models.Publication;
import org.example.doaapiproject.services.PublicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@RestController
@RequestMapping("/publication")
public class PublicationController {
    private final PublicationService publicationService;
    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }
    // create
    @PostMapping("/create")
    public ResponseEntity<?> createPublication(@Valid @RequestBody Publication publication, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            for (FieldError erro : result.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }

            return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
        } else {
            publicationService.createPublication(publication);
            return new ResponseEntity<>(publication, HttpStatus.OK);
        }
    }

    // get
    @GetMapping("/get")
    public List<Publication> getAllPublication() {
        return publicationService.getAllPublication();
    }

    // get campain by institution's id
    @GetMapping("/get/{id_institution}")
    public List<Publication> getAllPublicationOfInstitution(@PathVariable String id_institution) {
        return publicationService.getAllPublicationOfInstitution(id_institution);
    }
}
