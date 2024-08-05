package org.example.doaapiproject.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("campaignid")
public class CampaignId {

    @Id
    private String id;
    private Integer campaignId;

    // construtor
    public CampaignId(){}

    public CampaignId(String id, Integer campaignId) {
        this.id = id;
        this.campaignId = campaignId;
    }
    public CampaignId(Integer campaignId) {
        this.id = id;
        this.campaignId = campaignId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }
}
