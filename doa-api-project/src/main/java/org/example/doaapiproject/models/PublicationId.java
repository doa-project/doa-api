package org.example.doaapiproject.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("publicationid")
public class PublicationId {
    @Id
    private String id;
    private Integer publicationId;
    // construtor
    public PublicationId(){}

    public PublicationId(String id, Integer publicationId) {
        this.id = id;
        this.publicationId = publicationId;
    }

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
}
