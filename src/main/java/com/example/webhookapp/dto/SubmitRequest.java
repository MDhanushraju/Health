package com.example.webhookapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request body for testWebhook API.
 */
public class SubmitRequest {

    private String finalQuery;

    public SubmitRequest() {
    }

    public SubmitRequest(String finalQuery) {
        this.finalQuery = finalQuery;
    }

    @JsonProperty("finalQuery")
    public String getFinalQuery() {
        return finalQuery;
    }

    public void setFinalQuery(String finalQuery) {
        this.finalQuery = finalQuery;
    }
}
