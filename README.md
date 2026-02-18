# Webhook App (Spring Boot)

Production-ready Spring Boot app that runs the webhook flow **on startup**. No REST controllers, no UI, no manual trigger.

---

## How to run

**Prerequisites:** Java 17+, Maven (or use the included Maven Wrapper `mvnw.cmd`).

### Option 1: Run the JAR (recommended)

```bash
# 1. Build the JAR (from project root)
.\mvnw.cmd clean package -DskipTests

# 2. Run the app
java -jar target\webhookapp.jar
```

### Option 2: Run without building JAR

```bash
.\mvnw.cmd spring-boot:run
```

**Note:** Stop any already running instance (Ctrl+C in the terminal) before rebuilding or running again, to avoid port-in-use errors.

---

## Where to see the output

All output appears in the **same terminal** where you ran the app (no log file by default).

**On success you will see:**

```
  .   ____          _            __ _ _
 ...
 :: Spring Boot ::                (v3.2.5)

INFO  ... WebhookAppApplication     : Started WebhookAppApplication in X.XXX seconds
INFO  ... StartupRunner             : Starting webhook flow
INFO  ... WebhookService             : Webhook received, submitting solution
INFO  ... WebhookService             : testWebhook response: <server response here>
INFO  ... WebhookService             : Flow completed successfully
```

- **"Flow completed successfully"** = the SQL solution was submitted to the API.
- **"testWebhook response: ..."** = the evaluator’s response (e.g. success/score message).

**To save output to a file:**

```bash
java -jar target\webhookapp.jar > output.txt 2>&1
```

Then open `output.txt` to read the logs.

---

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
