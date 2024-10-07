package org.example.doaapiproject.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.example.doaapiproject.models.Campaign;
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
    @Operation(summary = "Create a publication", description = "Returns the publication created")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publication created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Campaign.class))),
            @ApiResponse(responseCode = "500", description = "Intern error in the system",
                    content = @Content())
    })
    public ResponseEntity<?> createPublication(@Valid @RequestBody Publication publication, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            for (FieldError erro : result.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }

            return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
        } else {
            try {
                publicationService.createPublication(new Publication(publication.getInstitutionId(),
                                                                     publication.getInstitutionName(),
                                                                     publication.getInstitutionPhoto(),
                                                                     publication.getDescription(),
                                                                     publication.getImages()));
            } catch (RuntimeException r) {
                return new ResponseEntity<>(r.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(publication, HttpStatus.OK);
        }
    }

    // get
    @GetMapping("/get")
    @Operation(summary = "Get all publications", description = "Returns a list of the publications found")
    public List<Publication> getAllPublication() {
        return publicationService.getAllPublication();
    }

    // get campain by institution's id
    @GetMapping("/get/{id_institution}")
    @Operation(summary = "Get a publication", description = "Returns the publication found")
    public List<Publication> getAllPublicationOfInstitution(@Parameter(name = "id_institution", description = "Requires the id of the institution", required = true) @PathVariable String id_institution) {
        return publicationService.getAllPublicationOfInstitution(id_institution);
    }
}
