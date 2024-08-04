package org.example.doaapiproject.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("institutionid")
public class InstitutionId {
    @Id
    private String id;

    private Integer institutionId;
    // construtor
    public InstitutionId(){}

    public InstitutionId(String id, Integer institutionId) {
        this.id = id;
        this.institutionId = institutionId;
    }
    public InstitutionId(Integer institutionId) {
        this.id = id;
        this.institutionId = institutionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }
}
