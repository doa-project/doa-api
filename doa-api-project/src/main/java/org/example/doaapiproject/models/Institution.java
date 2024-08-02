package org.example.doaapiproject.models;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Base64;

@Document("institution")
public class Institution {
    @Id
    @Min(value = 0, message = "the id must be bigger then or equal to zero")
    private Integer id;
    @NotNull(message = "name cannot be null")
    @Size(max = 50, message = "the number of characters in the name must be between 0 and 50")
    private String name;
    @NotNull(message = "e-mail cannot be null")
    @Email(message = "invalid e-mail")
    @Size(max = 100, message = "the number of characters in the e-mail must be between 0 and 100")
    private String email;
    @NotNull(message = "description cannot be null")
    @Size(max = 500, message = "the number of characters in the description must be between 0 and 500")
    private String description;
    @NotNull(message = "local cannot be null")
    @Size(max = 50, message = "the number of characters in the local must be between 0 and 50")
    private String local;
    @NotNull(message = "phone cannot be null")
    @Pattern(regexp = "^(?:\\+55\\s?)?(?:\\(\\d{2}\\)\\s?|\\d{2}\\s?)?\\d{5}-?\\d{4}$",
            message = "Invalid phone number format")
    private String phone;
    // converter para Base64
    @NotNull(message = "photo cannot be null")
    private String photo;

    // construtores
    public Institution(){}

    public Institution(Integer id, String name, String email, String description, String local, String phone, String photo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.description = description;
        this.local = local;
        this.phone = phone;
        this.photo = photo;
    }

    // getters e setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
                "\n ,name='" + this.name +
                "'\n ,email='" + this.email +
                "'\n ,description='" + this.description +
                "'\n ,local='" + this.local +
                "'\n ,phone='" + this.phone +
                "'\n ,photo='" + this.photo +
                "'\n}";
    }
}
