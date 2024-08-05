package org.example.doaapiproject.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.example.doaapiproject.models.Campaign;
import org.example.doaapiproject.models.User;
import org.example.doaapiproject.services.CampaignService;
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
@RequestMapping("/campaign")
public class CampaignController {
    private final CampaignService campaignService;
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }
    // create
    @PostMapping("/create")
    public ResponseEntity<?> createCampaign(@Valid @RequestBody Campaign campaign, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            for (FieldError erro : result.getFieldErrors()) {
                erros.put(erro.getField(), erro.getDefaultMessage());
            }

            return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
        } else {
            try {
                campaignService.createCampaign(campaign);
            } catch (RuntimeException r) {
                return new ResponseEntity<>(r.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(campaign, HttpStatus.OK);
        }
    }

    // get
    @GetMapping("/get")
    public List<Campaign> getAllCampaign() {
        return campaignService.getAllCampaign();
    }

    // get campain by institution's id
    @GetMapping("/get/{id_institution}")
    public List<Campaign> getAllCampaignOfInstitution(@PathVariable String id_institution) {
        return campaignService.getAllCampaignOfInstitution(id_institution);
    }
}
