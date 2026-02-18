package com.example.webhookapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request body for generateWebhook API.
 */
public class WebhookRequest {

    private String name;
    private String regNo;
    private String email;

    public WebhookRequest() {
    }

    public WebhookRequest(String name, String regNo, String email) {
        this.name = name;
        this.regNo = regNo;
        this.email = email;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("regNo")
    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
