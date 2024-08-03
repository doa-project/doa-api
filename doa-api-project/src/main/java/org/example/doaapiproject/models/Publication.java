package org.example.doaapiproject.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Base64;
import java.util.List;

@Document("publication")
public class Publication {
    @Id
    @Min(value = 0, message = "the id must be bigger then or equal to zero")
    private Integer id;
    @NotNull(message = "the id of the institution cannot be null")
    @Size(message = "the id of the institution must be bigger then or equal to zero")
    private String institutionId;
    @Size(max = 50, message = "the number of characters in the name of the institution must be between 0 and 50")
    private String institutionName;

    // converter para Base64
    private String institutionPhoto;
    @NotNull(message = "description cannot be null")
    @Size(max = 500, message = "the number of characters in the description must be between 0 and 500")
    private String description;
    @NotNull(message = "images cannot be null")
    private List<String> images;

    // construtores
    public Publication(){}

    public Publication(Integer id, String institutionId, String institutionName, String institutionPhoto, String description, List<String> images) {
        this.id = id;
        this.institutionId = institutionId;
        this.institutionName = institutionName;
        this.institutionPhoto = institutionPhoto;
        this.description = description;
        this.images = images;
    }

    // getters e setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getInstitutionPhoto() {
        return institutionPhoto;
    }

    public void setInstitutionPhoto(String institutionPhoto) {
        this.institutionPhoto = institutionPhoto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    // toString
    public String toString() {
        return "Publication{\n id=" + this.id +
                "\n ,institutionId=" + this.institutionId +
                "\n ,institutionName='" + this.institutionName +
                "'\n ,institutionPhoto='" + this.institutionPhoto +
                "'\n ,description='" + this.description +
                "'\n ,images='" + this.images +
                "'\n}";
    }
}
