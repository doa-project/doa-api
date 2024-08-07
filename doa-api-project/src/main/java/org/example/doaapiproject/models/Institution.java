package org.example.doaapiproject.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("institution")
@Schema(description = "Represents an institution in the system")
public class Institution {
    @Id
    @Schema(description = "Represents the automatic id generated by mongo", example = "f51bb4362e")
    private String id;
    @Min(value = 0, message = "the id must be bigger then or equal to zero")
    @Schema(description = "Represents the id of the institution of the campaign", example = "0")
    private Integer institutionId;
    @NotNull(message = "name cannot be null")
    @Size(max = 50, message = "the number of characters in the name must be between 0 and 50")
    @Schema(description = "Represents the name of the institution of the campaign", example = "0")
    private String name;
    @NotNull(message = "e-mail cannot be null")
    @Email(message = "invalid e-mail")
    @Size(max = 100, message = "the number of characters in the e-mail must be between 0 and 100")
    @Schema(description = "Represents the e-mail of the institution", example = "email.24@gmail.com")
    private String email;
    @NotNull(message = "description cannot be null")
    @Size(max = 500, message = "the number of characters in the description must be between 0 and 500")
    @Schema(description = "Represents the description of the institution", example = "This is the institution")
    private String description;
    @NotNull(message = "local cannot be null")
    @Size(max = 50, message = "the number of characters in the local must be between 0 and 50")
    @Schema(description = "Represents the local of the institution", example = "São Paulo")
    private String local;
    @NotNull(message = "phone cannot be null")
    @Pattern(regexp = "^(?:\\+55\\s?)?(?:\\(\\d{2}\\)\\s?|\\d{2}\\s?)?\\d{5}-?\\d{4}$",
            message = "Invalid phone number format")
    @Schema(description = "Represents the phone of the institution", example = "(11) 98765-9876")
    private String phone;
    // converter para Base64
    @NotNull(message = "photo cannot be null")
    @Schema(description = "Represents the photo of the institution", example = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACoAAAAoCAYAAACIC2hQAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAANXSURBVHgB3ZhNTttAFIDfs5100UqN1CCxdNQQqStgR6FSzQmAEzQ9QdMTQG4AJ2g4AekJSFWglbqArioVkLOslCBl0wXxz+uMcV0nOPZz4oSonxQpst/MfP6beW8QUvKjYOiq2q8pCm4Bge4fbrlEh5Wbs0ZcHAFcENHBbyffXO21epACTBN8XXxZI1B2xd9CZABR23LczbyG20lxrgI7lc7ZBWQtel1c3yXAPUaovFMFTpxlO6svel/bjFhQOEHyMTIlJRxJLy6nqR+YsTxRVbX2YDoYl8VXBieQJ4qwDFOCyN3mxLFExde6AlNCBXjKiWOJ4t0H8qBw7yh7GkmLi/SdE8cUpU8wJTSNmpw4lqhm5/cRoQ0Zg0iN0q8M59GSWO4UoJ0s31Uh2VZUt86NZ4lKSmK5I1LeQgb4kpvcuylhi0rKN5+bgDSR7DiSXjsYg6uF9SoQspe/YLAxJb22MCZpZSeR9NrDBHBlJ5X0+oAJSZLNQlKS6mOKotwRWb34wKKmrqwkvb4gI8xFQ3ft222iuwRGLBAXiv2oUUpZcowiUtQsrOm2Bjqh06t0vg2s8+cFo/BEu60SYS9cIyVxWVwzxAM0xIW007SLFJWZfE6z5PtmBAf9OuhvyXD1bMMUrXT/ZLPcPduBBPx+zaBLcN8vdb/sy/8/F9ZXVBfekYKGd9KllhivPlyiKAOdqdbxgKR3KSgGUc9lh+bimv5P0jtpAAM11x+Ku2tnPtt4oxAeE2LVq1TlT/wX45nDmX8g6pUbAxIDFGSHjpUbTqC59dG9/qSkg9AY1QcCHcnXDO6JImxBQueA7hFkAAIu+5Kx4z1W+0GZEojS+HdnHFhjYegJh+fRFswdFExtgaj4/D/CnBHO/gPR593TfRfhEOYEseFRD69oA0topXNanQdZKbnUPdkLH7u31ktZkWQcwAMRJSmJTErKNyc12QBmzChJycjsyWsww9cgTlISm+aJTdc2zAjNthtx5yfOR2fF/yGqhFaGKGRyDBlRSth5jhcVGXrc7ojYk2JNYzkr34zrR27tQAKxot5WDtJm1CByYfDqJQZ+OVIfIcna2mHVTLIecqx+jRBWUMwEhGpDTCUtSImsWEUpUhVp3mt58a7YJdQ0t8Yp/v4ASgd3Jx9qygkAAAAASUVORK5CYII=")
    private String photo;

    // construtores
    public Institution(){}

    public Institution(String id, Integer institutionId, String name, String email, String description, String local, String phone, String photo) {
        this.id = id;
        this.institutionId = institutionId;
        this.name = name;
        this.email = email;
        this.description = description;
        this.local = local;
        this.phone = phone;
        this.photo = photo;
    }

    public Institution(String name, String email, String description, String local, String phone, String photo) {
        this.name = name;
        this.email = email;
        this.description = description;
        this.local = local;
        this.phone = phone;
        this.photo = photo;
    }

    // getters e setters

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    // toString
    public String toString() {
        return "Institution{\n id=" + this.id +
                "\n ,institutionId=" + this.institutionId +
                "\n ,name='" + this.name +
                "'\n ,email='" + this.email +
                "'\n ,description='" + this.description +
                "'\n ,local='" + this.local +
                "'\n ,phone='" + this.phone +
                "'\n ,photo='" + this.photo +
                "'\n}";
    }
}
