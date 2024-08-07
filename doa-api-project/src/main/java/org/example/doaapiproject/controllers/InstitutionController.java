package org.example.doaapiproject.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.example.doaapiproject.models.Campaign;
import org.example.doaapiproject.models.Institution;
import org.example.doaapiproject.models.InstitutionRegistrationRequest;
import org.example.doaapiproject.models.Login;
import org.example.doaapiproject.services.InstitutionService;
import org.example.doaapiproject.services.LoginService;
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
    private final LoginService loginService;
    public InstitutionController(InstitutionService institutionService, LoginService loginService) {
        this.institutionService = institutionService;
        this.loginService = loginService;
    }

    // create
    @PostMapping("/create")
    @Operation(summary = "Create an institution", description = "Returns the institution created")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Institution created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Campaign.class))),
            @ApiResponse(responseCode = "500", description = "Intern error in the system",
                    content = @Content())
    })
    public ResponseEntity<?> createInstitution(@Valid @RequestBody InstitutionRegistrationRequest institution, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            for (FieldError erro : result.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }

            return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
        } else {
            Institution institutionCreated = institutionService.createInstitution(new Institution(institution.getName(), institution.getEmail(), institution.getDescription(), institution.getLocal(), institution.getPhone(), institution.getPhoto()));
            loginService.createLogin(new Login(institutionCreated.getInstitutionId(), institution.getEmail(), institution.getPassword()));
            return new ResponseEntity<>(institutionCreated, HttpStatus.OK);
        }
    }

    // update
    @PutMapping("/edit/{id}")
    @Operation(summary = "Update an institution", description = "Returns the institution updated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Institution updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Campaign.class))),
            @ApiResponse(responseCode = "500", description = "Intern error in the system",
                    content = @Content()),
            @ApiResponse(responseCode = "404", description = "The institution was not found",
                    content = @Content())
    })
    public ResponseEntity<?> updateInstitution(@Parameter(name = "id_institution", description = "Requires the id of the institution", required = true) @PathVariable String id, @Valid @RequestBody InstitutionRegistrationRequest institutionUpdated, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            for (FieldError erro : result.getFieldErrors()) {
                // Coloque o nome do campo e a mensagem de erro no mapa
                erros.put(erro.getField(), erro.getDefaultMessage());
            }

            return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
        } else {
            try {
                Institution institution = institutionService.findInstitution(id);
                institution.setName(institutionUpdated.getName());
                institution.setEmail(institutionUpdated.getEmail());
                institution.setDescription(institutionUpdated.getDescription());
                institution.setLocal(institutionUpdated.getLocal());
                institution.setPhone(institutionUpdated.getPhone());
                institution.setPhoto(institutionUpdated.getPhoto());
                institutionService.createInstitution(institution);

                Login login = loginService.findLoginByUserIdAndEmail(id, institutionUpdated.getEmail());
                login.setPassword(institutionUpdated.getPassword());
                loginService.createLogin(login);
                return new ResponseEntity<>(institution, HttpStatus.OK);
            } catch (RuntimeException r) {
                return new ResponseEntity<>(r.getLocalizedMessage(), HttpStatus.NOT_FOUND);
            }
        }
    }

    // delete
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete an institution", description = "Returns the institution updated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Institution deleted",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Campaign.class))),
            @ApiResponse(responseCode = "500", description = "Intern error in the system",
                    content = @Content())
    })
    public ResponseEntity<?> deleteInstitution(@Parameter(name = "id_institution", description = "Requires the id of the institution", required = true) @PathVariable String id) {
        try {
            Institution institutionDeleted = institutionService.deleteInstitution(id);
            return ResponseEntity.status(HttpStatus.OK).body(institutionDeleted);
        } catch (RuntimeException r){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r.getMessage());
        }
    }
}
