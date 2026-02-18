package com.example.webhookapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response from generateWebhook API: webhook URL and JWT accessToken.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebhookResponse {

    private String webhook;
    private String accessToken;

    public WebhookResponse() {
    }

    @JsonProperty("webhook")
    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    @JsonProperty("accessToken")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
