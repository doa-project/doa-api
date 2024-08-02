package org.example.doaapiproject.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Base64;
import java.util.List;

@Document("campaign")
public class Capaign {
    @Id
    @Min(value = 0, message = "the id must be bigger then or equal to zero")
    private Integer id;
    @NotNull(message = "name cannot be null")
    @Size(max = 50, message = "the number of characters in the name of the institution must be between 0 and 50")
    private String institutionName;
    @NotNull(message = "the photo of the institution cannot be null")
    private String institutionPhoto;
    @NotNull(message = "description cannot be null")
    @Size(max = 500, message = "the number of characters in the description must be between 0 and 500")
    private String description;
    @NotNull(message = "images cannot be null")
    private List<String> images;
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$",
            message = "Invalid date format. The expected format is dd/MM/yyyy")
    private String endDate;
    @NotNull(message = "local cannot be null")
    @Size(max = 50, message = "the number of characters in the local must be between 0 and 50")
    private String local;

    // construtores
    public Capaign(){}

    public Capaign(Integer id, String institutionName, String institutionPhoto, String description, List<String> images, String endDate, String local) {
        this.id = id;
        this.institutionName = institutionName;
        this.institutionPhoto = institutionPhoto;
        this.description = description;
        this.images = images;
        this.endDate = endDate;
        this.local = local;
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    // toString
    public String toString() {
        return "Campaign{\n id=" + this.id +
                "\n ,institutionName='" + this.institutionName +
                "'\n ,institutionPhoto='" + this.institutionPhoto +
                "'\n ,description='" + this.description +
                "'\n ,images='" + this.images +
                "'\n ,endDate='" + this.endDate +
                "'\n ,local='" + this.local +
                "'\n}";
    }
}
