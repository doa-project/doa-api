package org.example.doaapiproject.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public class UserRegistrationRequest {
    @NotNull(message = "name cannot be null")
    @Size(max = 50, message = "the number of characters in the name must be between 0 and 50")
    @Schema(description = "Represents the name of the user", example = "User")
    private String name;

    @NotNull(message = "e-mail cannot be null")
    @Email(message = "invalid e-mail")
    @Size(max = 100, message = "the number of characters in the e-mail must be between 0 and 100")
    @Schema(description = "Represents the e-mail of the user", example = "user.24@gmail.com")
    private String email;

    @NotNull(message = "password cannot be null")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    @Schema(description = "Represents the password of the login", example = "T3r&vQ6z@")
    private String password;
    @Schema(description = "Represents the photo of the user", example = "https://firebasestorage.googleapis.com/v0/b/doa-app-447b2.appspot.com/o/doaUser.png?alt=media&token=c17080a3-7af1-4bba-931b-6efa18cf9e44")
    private String userPhoto;

    // getters e setters

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserPhoto() {
        return userPhoto;
    }
    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }
}
