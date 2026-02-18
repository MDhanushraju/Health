package com.example.webhookapp.runner;

import com.example.webhookapp.service.WebhookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Triggers the webhook flow on application startup. No manual trigger, no controller.
 */
@Component
public class StartupRunner implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(StartupRunner.class);

    private final WebhookService webhookService;

    public StartupRunner(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            log.info("Starting webhook flow");
            webhookService.runFlow();
        } catch (Exception e) {
            log.error("Webhook flow failed", e);
            throw new RuntimeException("Webhook flow failed", e);
        }
    }
}
