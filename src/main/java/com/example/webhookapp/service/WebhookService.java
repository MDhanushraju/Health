package com.example.webhookapp.service;

import com.example.webhookapp.dto.SubmitRequest;
import com.example.webhookapp.dto.WebhookRequest;
import com.example.webhookapp.dto.WebhookResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * API communication: generateWebhook and testWebhook. No SQL logic here.
 */
@Service
public class WebhookService {

    private static final Logger log = LoggerFactory.getLogger(WebhookService.class);

    private static final String GENERATE_WEBHOOK_URL =
            "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
    private static final String TEST_WEBHOOK_URL =
            "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";

    private final RestTemplate restTemplate;
    private final SqlService sqlService;

    @Value("${webhook.name:John Doe}")
    private String name;

    @Value("${webhook.regNo:REG12347}")
    private String regNo;

    @Value("${webhook.email:john@example.com}")
    private String email;

    public WebhookService(RestTemplate restTemplate, SqlService sqlService) {
        this.restTemplate = restTemplate;
        this.sqlService = sqlService;
    }

    /**
     * Full flow: generate webhook → determine question → get SQL → submit. Called by StartupRunner.
     */
    public void runFlow() {
        WebhookRequest request = new WebhookRequest(name, regNo, email);
        ResponseEntity<WebhookResponse> responseEntity = restTemplate.postForEntity(
                GENERATE_WEBHOOK_URL,
                request,
                WebhookResponse.class
        );
        WebhookResponse response = responseEntity.getBody();
        if (response == null || response.getAccessToken() == null) {
            log.error("Generate webhook failed: missing response or accessToken");
            throw new RuntimeException("Generate webhook failed");
        }

        String accessToken = response.getAccessToken();
        String webhookUrl = response.getWebhook();
        log.info("Webhook received, submitting solution");

        int lastTwo = getLastTwoDigits(regNo);
        boolean isQuestion1 = (lastTwo % 2) != 0;
        String finalQuery = sqlService.getFinalQuery(isQuestion1);

        submitSolution(accessToken, finalQuery);
        log.info("Flow completed successfully");
    }

    private int getLastTwoDigits(String regNo) {
        if (regNo == null || regNo.length() < 2) {
            return 0;
        }
        try {
            return Integer.parseInt(regNo.substring(regNo.length() - 2));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void submitSolution(String accessToken, String finalQuery) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", accessToken);

        SubmitRequest body = new SubmitRequest(finalQuery);
        HttpEntity<SubmitRequest> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(TEST_WEBHOOK_URL, entity, String.class);
        if (response.getBody() != null && !response.getBody().isEmpty()) {
            log.info("testWebhook response: {}", response.getBody());
        }
    }
}
