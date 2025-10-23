# mcronaut — Micronaut Starter / Template

## Overview

- A minimal, opinionated Micronaut 4.x starter template intended as the basis for new services.
- Includes example integrations (MongoDB, OAuth2 via Auth0), a lightweight colored Logger utility, OpenAPI metadata, and example auth endpoints.
- Designed for fast bootstrapping: clone, provide runtime secrets (env or .env), build, run.

### Quickstart (local development)

1. Clone:
  
   `git clone https://github.com/mananshr/Micronaut-Template.git`

   `cd Micronaut-Template`

2. Provide runtime configuration (do not commit):
   Create `.env` at project root or provide environment variables in your shell / IDE run configuration.

   Example `.env` (DO NOT COMMIT):

```bash
# .env
MONGODB_URI="mongodb+srv://<user>:<pass>@cluster.mongodb.net/dbname?retryWrites=true&w=majority"
OAUTH_ISSUER="https://your-tenant.us.auth0.com/"
OAUTH_CLIENT_ID="your-client-id"
OAUTH_CLIENT_SECRET="your-client-secret"
JWT_SECRET="pleaseChangeThisSecret"
```

4. Build:
   - Unix / macOS: ./gradlew build
   - Windows: gradlew.bat build

5. Run:
   - ./gradlew run
   - or: java -jar build/libs/<artifact>.jar
   Ensure environment variables are available to the JVM (IDE run configuration or export in shell).

#### Runtime configuration priority

1. application.yml / application-<env>.yml in src/main/resources
2. Environment variables
3. System properties

#### Secrets handling and .gitignore

- Do not commit credentials. The repository .gitignore excludes:
  - gradle.properties, .env, src/main/resources/application.yml, src/test/resources/application.properties, IDE files.
- If secrets were accidentally committed:
  git rm --cached gradle.properties src/main/resources/application.yml .env
  git commit -m "Remove tracked secrets" and rotate credentials.

### Project structure (where to look)

- Application entry: src/main/java/io/github/mananshr/crud/Application.java
- Auth endpoints: src/main/java/io/github/mananshr/crud/controller/AuthController.java
- LoginRequest DTO: src/main/java/io/github/mananshr/crud/controller/LoginRequest.java
- User model: src/main/java/io/github/mananshr/crud/model/User.java
- User repository: src/main/java/io/github/mananshr/crud/repository/UserRepository.java
- Exception handling: src/main/java/io/github/mananshr/crud/exception/
- Logger utility: src/main/java/io/github/mananshr/crud/util/Logger.java
- README, .gitignore, and .env example at project root

### Features included (pre-existing Micronaut features in this template)

| Feature | Present | Documentation / Usage |
|---|:---:|---|
| OpenAPI | ✅ | [Micronaut OpenAPI](https://micronaut-projects.github.io/micronaut-openapi/latest/guide/index.html) |
| GraphQL | ✅ | [Micronaut GraphQL](https://micronaut-projects.github.io/micronaut-graphql/latest/guide/index.html) |
| problem-json | ✅ | [Micronaut Problem JSON](https://micronaut-projects.github.io/micronaut-problem-json/latest/guide/index.html) |
| security-jwt | ✅ | [Micronaut Security (JWT)](https://micronaut-projects.github.io/micronaut-security/latest/guide/index.html#jwt) |
| views-velocity | ✅ | [Micronaut Views (Velocity)](https://micronaut-projects.github.io/micronaut-views/latest/guide/index.html#velocity) and [Velocity](https://velocity.apache.org) |
| multi-tenancy | ✅ | [Micronaut Multitenancy](https://docs.micronaut.io/latest/guide/index.html#multitenancy) |
| microstream-rest | ✅ | [Micronaut MicroStream REST](https://micronaut-projects.github.io/micronaut-microstream/latest/guide/#rest) and [MicroStream REST](https://docs.microstream.one/manual/storage/rest-interface/index.html) |
| control-panel | ✅ | [Micronaut Control Panel](https://micronaut-projects.github.io/micronaut-control-panel/latest/guide/index.html) |
| http-client | ✅ | [Micronaut HTTP Client](https://docs.micronaut.io/latest/guide/index.html#nettyHttpClient) |
| lombok | ✅ | [Project Lombok](https://projectlombok.org/features/all) and [Micronaut Lombok docs](https://docs.micronaut.io/latest/guide/index.html#lombok) |
| micronaut-test-rest-assured | ✅ | [Micronaut Test (REST-assured)](https://micronaut-projects.github.io/micronaut-test/latest/guide/#restAssured) and [Rest Assured](https://rest-assured.io/#docs) |
| mongo-sync (Data MongoDB) | ✅ | [Micronaut Data MongoDB](https://micronaut-projects.github.io/micronaut-mongodb/latest/guide/index.html) |
| validation | ✅ | [Micronaut Validation](https://micronaut-projects.github.io/micronaut-validation/latest/guide/) |
| elasticsearch | ✅ | [Micronaut Elasticsearch](https://micronaut-projects.github.io/micronaut-elasticsearch/latest/guide/index.html) |
| management | ✅ | [Micronaut Management](https://docs.micronaut.io/latest/guide/index.html#management) |
| test-resources | ⚠️ Disabled | [Micronaut Test Resources](https://micronaut-projects.github.io/micronaut-test-resources/latest/guide/) (template avoids auto-start) |
| microstream | ✅ | [Micronaut MicroStream](https://micronaut-projects.github.io/micronaut-microstream/latest/guide) |
| security (OAuth2) | ✅ | [Micronaut Security OAuth2](https://micronaut-projects.github.io/micronaut-security/latest/guide/index.html#oauth) |
| openapi-explorer | ✅ | [OpenAPI Explorer](https://micronaut-projects.github.io/micronaut-openapi/latest/guide/#openapiExplorer) and [OpenAPI Explorer](https://github.com/Authress-Engineering/openapi-explorer) |
| serialization-jackson | ✅ | [Micronaut Serialization / Jackson](https://micronaut-projects.github.io/micronaut-serialization/latest/guide/) |
| security-session | ✅ | [Micronaut Security Session](https://micronaut-projects.github.io/micronaut-security/latest/guide/index.html#session) |
| tomcat-server | ✅ | [Micronaut Servlet / Tomcat](https://micronaut-projects.github.io/micronaut-servlet/latest/guide/index.html#tomcat) |
| discovery-client / discovery-core | ✅ | [Micronaut Discovery Client](https://micronaut-projects.github.io/micronaut-discovery-client/latest/guide/) |
| data-mongodb | ✅ | [Micronaut Data (MongoDB)](https://micronaut-projects.github.io/micronaut-data/latest/guide/#mongo) |
| annotation-api | ✅ | [Jakarta Annotations](https://jakarta.ee/specifications/annotations/) |
| micronaut-aot | ✅ | [Micronaut AOT](https://micronaut-projects.github.io/micronaut-aot/latest/guide/) |
| Shadow Plugin | ✅ | [Shadow Gradle Plugin](https://gradleup.com/shadow/) |
| Micronaut Gradle Plugin | ✅ | [Micronaut Gradle Plugin docs](https://micronaut-projects.github.io/micronaut-gradle-plugin/latest/) |
| GraalVM Gradle Plugin | ✅ | [GraalVM Native Build Tools](https://graalvm.github.io/native-build-tools/latest/gradle-plugin.html) |

Notes on features

- Many features above are included as links so you can enable them by adding the corresponding Gradle dependencies and config. The template intentionally keeps runtime surface minimal to be adaptable.
- Test resources were disabled/avoided earlier due to GraalVM / Docker initialization issues encountered; when required, follow Micronaut Test Resources or Testcontainers docs to enable.

Auth example (this template includes an AuthController)

- Signup: POST /auth/signup

  - Request:
  
    ```json
    {
      "email": "user@example.com",
      "name": "John Doe",
      "password": "secure-password@123"
    }
    ```

  - Example curl:

    ```bash
    curl -X POST http://localhost:8080/auth/signup \
      -H "Content-Type: application/json" \
      -d '{"email":"user@example.com","name":"John Doe","password":"secure-password@123"}'
    ```

- Login (lookup only in template; real auth via Auth0):
  - Request:

    ```json
    {
      "email": "user@example.com",
      "password": "secure-password@123"
    }
    ```

  - Example curl:
  
    ```bash
    curl -X POST http://localhost:8080/auth/login \
      -H "Content-Type: application/json" \
      -d '{"email":"user@example.com","password":"secure-password@123"}'
      ```

### Logging

- Use the built-in colored Logger utility: `io.github.mananshr.crud.util.Logger`.
- Controls:
  - Disable all logging programmatically: Logger.setEnabled(false)
  - Disable colors: `NO_COLOR=true` env var or `-Dmcronaut.color=false`
  - Enable debug logs: `-Dmcronaut.debug=true`
- Avoid logging secrets (passwords, client secrets, tokens).

#### Testing guidance

- The template avoids automatic test container/startup to prevent local GraalVM/Docker issues.
- Add tests and enable Testcontainers or Micronaut Test Resources when required.
- To silence logs during tests: call `Logger.setEnabled(false)` in test setup.

#### Extending the template

- Add dependencies to `build.gradle` for features you need (e.g. GraphQL, Kafka, Redis, Elasticsearch).
- Use Micronaut CLI or Guides to add modules or features.
- Replace example Auth0 config with your identity provider or add a local dev auth mock.

#### Contributing

- Fork and submit PRs to improve the template.
- Keep the template small and easy to adapt; prefer configuration via env vars.

#### Security reminder

- Rotate credentials if accidentally committed.
- Use secret stores for production (Vault/Cloud provider secrets).
- Do not log sensitive data.

#### Contact / Author

- Maintained by [Dr. Manan Sharma](https://github.com/mananshr)
