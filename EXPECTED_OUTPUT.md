# Expected console output when you run the app

When you run the application (e.g. `mvn spring-boot:run` or `java -jar target/hiring-webhook-1.0.0.jar`), you should see output similar to:

---

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.5)

2025-02-18T12:00:00.000+05:30  INFO 12345 --- [           main] c.h.hiring.HiringWebhookApplication       : Starting HiringWebhookApplication...
2025-02-18T12:00:00.100+05:30  INFO 12345 --- [           main] c.h.hiring.HiringWebhookApplication       : No active profile set, falling back to 1 default profile: "default"
2025-02-18T12:00:00.500+05:30  INFO 12345 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080
2025-02-18T12:00:00.501+05:30  INFO 12345 --- [           main] c.h.hiring.HiringWebhookApplication       : Started HiringWebhookApplication in 1.234 seconds
2025-02-18T12:00:00.502+05:30  INFO 12345 --- [           main] c.h.hiring.service.WebhookFlowService     : Starting webhook flow: generateWebhook -> solve SQL -> testWebhook
2025-02-18T12:00:00.800+05:30  INFO 12345 --- [           main] c.h.hiring.service.WebhookFlowService     : Received accessToken and webhook from generateWebhook
2025-02-18T12:00:00.801+05:30  INFO 12345 --- [           main] c.h.hiring.service.WebhookFlowService     : Question: Q1 (last two digits=47), finalQuery length=9
2025-02-18T12:00:01.100+05:30  INFO 12345 --- [           main] c.h.hiring.service.WebhookFlowService     : Webhook flow completed successfully
```

---

- **Question: Q1** appears when the last two digits of `regNo` are odd (e.g. REG**12347** → 47 → Q1). For even digits you’d see **Question: Q2**.
- **finalQuery length=9** is for the placeholder `SELECT 1`; it will change when you use your real SQL.

If the generate-webhook or test-webhook API fails, you’ll see something like:

```
2025-02-18T12:00:01.100+05:30 ERROR 12345 --- [           main] c.h.hiring.service.WebhookFlowService     : Webhook flow failed
org.springframework.web.client.HttpClientErrorException$Unauthorized: 401 Unauthorized
    ...
```

To see **your** actual output, build and run locally (with Maven in PATH):

```bash
mvn clean package -DskipTests
java -jar target/hiring-webhook-1.0.0.jar
```

Or:

```bash
mvn spring-boot:run
```
