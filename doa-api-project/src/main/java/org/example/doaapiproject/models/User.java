package org.example.doaapiproject.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.StringJoiner;

@Document("user")
public class User {
    @Id
    private String id;

    @Min(value = 0, message = "the id must be bigger then or equal to zero")
    private Integer userId;
    @NotNull(message = "name cannot be null")
    @Size(max = 50, message = "the number of characters in the name must be between 0 and 50")
    private String name;
    @NotNull(message = "e-mail cannot be null")
    @Email(message = "invalid e-mail")
    @Size(max = 100, message = "the number of characters in the e-mail must be between 0 and 100")
    private String email;

    // construtores
    public User(){}

    public User(String id, Integer userId, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    // getters e setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    // toString
    public String toString() {
        return "User{\n id=" + this.id +
                "\n ,userId=" + this.userId +
                "\n ,name='" + this.name +
                "'\n ,email='" + this.email +
                "'\n}";
    }
}
