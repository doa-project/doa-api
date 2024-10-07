package org.example.doaapiproject.models;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Base64;
import java.util.List;

@Document("publication")
@Schema(description = "Represents a publication in the system")
public class Publication {
    @Id
    @Schema(description = "Represents the automatic id generated by mongo", example = "f51bb4362e")
    private String id;
    @Min(value = 0, message = "the id must be bigger then or equal to zero")
    @Schema(description = "Represents the id of the publication", example = "0")
    private Integer publicationId;
    @NotNull(message = "the id of the institution cannot be null")
    @Size(message = "the id of the institution must be bigger then or equal to zero")
    @Schema(description = "Represents the id of the institution of the publication", example = "0")
    private String institutionId;
    @Size(max = 50, message = "the number of characters in the name of the institution must be between 0 and 50")
    @Schema(description = "Represents the name of the institution of the publication", example = "0")
    private String institutionName;

    // converter para Base64
    @Schema(description = "Represents the photo of the institution of the publication", example = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACoAAAAoCAYAAACIC2hQAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAANXSURBVHgB3ZhNTttAFIDfs5100UqN1CCxdNQQqStgR6FSzQmAEzQ9QdMTQG4AJ2g4AekJSFWglbqArioVkLOslCBl0wXxz+uMcV0nOPZz4oSonxQpst/MfP6beW8QUvKjYOiq2q8pCm4Bge4fbrlEh5Wbs0ZcHAFcENHBbyffXO21epACTBN8XXxZI1B2xd9CZABR23LczbyG20lxrgI7lc7ZBWQtel1c3yXAPUaovFMFTpxlO6svel/bjFhQOEHyMTIlJRxJLy6nqR+YsTxRVbX2YDoYl8VXBieQJ4qwDFOCyN3mxLFExde6AlNCBXjKiWOJ4t0H8qBw7yh7GkmLi/SdE8cUpU8wJTSNmpw4lqhm5/cRoQ0Zg0iN0q8M59GSWO4UoJ0s31Uh2VZUt86NZ4lKSmK5I1LeQgb4kpvcuylhi0rKN5+bgDSR7DiSXjsYg6uF9SoQspe/YLAxJb22MCZpZSeR9NrDBHBlJ5X0+oAJSZLNQlKS6mOKotwRWb34wKKmrqwkvb4gI8xFQ3ft222iuwRGLBAXiv2oUUpZcowiUtQsrOm2Bjqh06t0vg2s8+cFo/BEu60SYS9cIyVxWVwzxAM0xIW007SLFJWZfE6z5PtmBAf9OuhvyXD1bMMUrXT/ZLPcPduBBPx+zaBLcN8vdb/sy/8/F9ZXVBfekYKGd9KllhivPlyiKAOdqdbxgKR3KSgGUc9lh+bimv5P0jtpAAM11x+Ku2tnPtt4oxAeE2LVq1TlT/wX45nDmX8g6pUbAxIDFGSHjpUbTqC59dG9/qSkg9AY1QcCHcnXDO6JImxBQueA7hFkAAIu+5Kx4z1W+0GZEojS+HdnHFhjYegJh+fRFswdFExtgaj4/D/CnBHO/gPR593TfRfhEOYEseFRD69oA0topXNanQdZKbnUPdkLH7u31ktZkWQcwAMRJSmJTErKNyc12QBmzChJycjsyWsww9cgTlISm+aJTdc2zAjNthtx5yfOR2fF/yGqhFaGKGRyDBlRSth5jhcVGXrc7ojYk2JNYzkr34zrR27tQAKxot5WDtJm1CByYfDqJQZ+OVIfIcna2mHVTLIecqx+jRBWUMwEhGpDTCUtSImsWEUpUhVp3mt58a7YJdQ0t8Yp/v4ASgd3Jx9qygkAAAAASUVORK5CYII=")
    private String institutionPhoto;
    @NotNull(message = "description cannot be null")
    @Size(max = 500, message = "the number of characters in the description must be between 0 and 500")
    @Schema(description = "Represents the description of the publication", example = "This publication is about")
    private String description;
    @NotNull(message = "images cannot be null")
    @Schema(description = "Represents the images of the publication", example = "['image1', 'image2']")
    private List<String> images;

    // construtores
    public Publication(){}

    public Publication(String id, Integer publicationId, String institutionId, String institutionName, String institutionPhoto, String description, List<String> images) {
        this.id = id;
        this.publicationId = publicationId;
        this.institutionId = institutionId;
        this.institutionName = institutionName;
        this.institutionPhoto = institutionPhoto;
        this.description = description;
        this.images = images;
    }

    public Publication(String institutionId, String description, List<String> images) {
        this.institutionId = institutionId;
        this.description = description;
        this.images = images;
    }

    // getters e setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Integer publicationId) {
        this.publicationId = publicationId;
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
                "\n ,publicationId=" + this.publicationId +
                "\n ,institutionId=" + this.institutionId +
                "\n ,institutionName='" + this.institutionName +
                "'\n ,institutionPhoto='" + this.institutionPhoto +
                "'\n ,description='" + this.description +
                "'\n ,images='" + this.images +
                "'\n}";
    }
}
