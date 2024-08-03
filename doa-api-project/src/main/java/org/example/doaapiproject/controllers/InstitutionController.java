package org.example.doaapiproject.controllers;

import jakarta.validation.Valid;
import org.example.doaapiproject.models.Institution;
import org.example.doaapiproject.services.InstitutionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
@RequestMapping("/institution")
public class InstitutionController {
    private final InstitutionService institutionService;
    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    // create
    @PostMapping("/create")
    public ResponseEntity<?> createInstitution(@Valid @RequestBody Institution institution, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            for (FieldError erro : result.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }

            return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
        } else {
            institutionService.createInstitution(institution);
            return new ResponseEntity<>(institution, HttpStatus.OK);
        }
    }

    // update
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateInstitution(@PathVariable String id, @Valid @RequestBody Institution institutionUpdated, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            for (FieldError erro : result.getFieldErrors()) {
                // Coloque o nome do campo e a mensagem de erro no mapa
                erros.put(erro.getField(), erro.getDefaultMessage());
            }

            return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
        } else {
            Institution institution = institutionService.findInstitution(id);
            institution.setName(institutionUpdated.getName());
            institution.setEmail(institutionUpdated.getEmail());
            institution.setDescription(institutionUpdated.getDescription());
            institution.setLocal(institutionUpdated.getLocal());
            institution.setPhone(institutionUpdated.getPhone());
            institution.setPhoto(institutionUpdated.getPhoto());
            institutionService.createInstitution(institution);
            return new ResponseEntity<>(institution, HttpStatus.OK);
        }
    }

    // delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteInstitution(@PathVariable String id) {
        try {
            Institution institutionDeleted = institutionService.deleteInstitution(id);
            return ResponseEntity.status(HttpStatus.OK).body(institutionDeleted);
        } catch (RuntimeException r){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r.getMessage());
        }
    }
}
