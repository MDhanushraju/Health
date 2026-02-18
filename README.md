# Webhook App (Spring Boot)

Production-ready Spring Boot app that runs the webhook flow **on startup**. No REST controllers, no UI, no manual trigger.

## Flow (automatic on startup)

1. POST to `https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA` with `name`, `regNo`, `email`.
2. Parse response for `webhook` URL and `accessToken` (JWT).
3. Last 2 digits of `regNo`: **odd** → Question 1, **even** → Question 2. SQL solution is returned by `SqlService` (no web fetch).
4. POST to `https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA` with `Authorization: <accessToken>`, body `{ "finalQuery": "YOUR_SQL_QUERY_HERE" }`.

## Project structure

```
src/main/java/com/example/webhookapp/
├── WebhookAppApplication.java
├── runner/
│   └── StartupRunner.java
├── service/
│   ├── WebhookService.java
│   └── SqlService.java
├── dto/
│   ├── WebhookRequest.java
│   ├── WebhookResponse.java
│   └── SubmitRequest.java
└── config/
    └── RestTemplateConfig.java
```

## Configuration

In `src/main/resources/application.properties` (or env):

- `webhook.name` – your name  
- `webhook.regNo` – your registration number (last 2 digits decide Q1 vs Q2)  
- `webhook.email` – your email  

## SQL solutions

Edit `SqlService.getFinalQuery(boolean isQuestion1)`: replace the placeholder `SELECT 1` with your actual SQL for Question 1 (odd) and Question 2 (even).

## Build & run

```bash
# Build (produces target/webhookapp.jar)
.\mvnw.cmd clean package -DskipTests

# Run JAR (stop any running instance first if you need to rebuild)
java -jar target/webhookapp.jar
```

Or run without building JAR:

```bash
.\mvnw.cmd spring-boot:run
```

## Submission

- Push code to GitHub (public repo).
- Build JAR and upload / host a **raw downloadable link** to the JAR.
- Submit GitHub repo URL + public JAR link as required by the hiring form.
