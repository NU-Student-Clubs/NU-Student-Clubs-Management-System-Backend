# NU Student Clubs Management System - Backend

Backend implementation for the Nile University Clubs Hub — a centralized platform that brings all NU clubs together. Built with Spring Boot and Java.

## 📋 Overview
This project exposes RESTful APIs for managing clubs, committees, events, memberships, board members, and admin operations with JWT-based security.

## 🛠️ Tech Stack
- Java 17
- Spring Boot 4.0.0 (Web, Data JPA, Security)
- MySQL (dev/prod), H2 (tests)
- JWT (io.jsonwebtoken)
- Maven Wrapper

## 📁 Project Structure

```
src/main/java/com/nu/clubs/clubs_backend/
├── controller/          # REST Controllers
├── service/             # Business Logic Layer
├── repository/          # Data Access Layer
├── model/               # Entities
├── dto/                 # Data Transfer Objects
└── exception/           # Exception Handling
```

Note: There is also a legacy package path `clubs_bakend/`. Prefer `clubs_backend/` going forward.

## 🚀 Getting Started

### Prerequisites
- JDK 17+
- MySQL 8.x (if running locally)
- Internet access (Maven dependencies)

### Database Configuration (Recommended via Environment Variables)
Avoid committing secrets. Override defaults using environment variables:
- `SPRING_DATASOURCE_URL` (e.g., `jdbc:mysql://localhost:3306/clubs?createDatabaseIfNotExist=true`)
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `SERVER_PORT` (default: `8081`)
- `JWT_SECRET` (long, random string)

Windows PowerShell session example:
```powershell
$env:SPRING_DATASOURCE_URL = "jdbc:mysql://localhost:3306/clubs?createDatabaseIfNotExist=true"
$env:SPRING_DATASOURCE_USERNAME = "root"
$env:SPRING_DATASOURCE_PASSWORD = "your_password"
$env:SERVER_PORT = "8081"
$env:JWT_SECRET = "replace-with-a-strong-random-secret"
```

The file `src/main/resources/application.properties` contains default values that you can override via env vars.

### Build & Run (Windows)
- Clean compile (skip tests):
```powershell
.\mvnw.cmd -DskipTests compile
```
- Run the app (dev):
```powershell
.\mvnw.cmd spring-boot:run
```
- Package JAR:
```powershell
.\mvnw.cmd -DskipTests package
```
- Run packaged JAR:
```powershell
java -jar target\clubs-bakend-0.0.1-SNAPSHOT.jar
```

The API starts on http://localhost:8081 by default (configured in `application.properties`).

## 🔌 API Endpoints
Typical resources include: Clubs, Events, Memberships, Board Members, Committees, Admins. See controllers under `src/main/java/com/nu/clubs/clubs_backend/controller`.

## 🧪 Tests
Tests run against an in-memory H2 database via `src/test/resources/application-test.properties`:
```powershell
.\mvnw.cmd test
```

## 🔐 Security
JWT-based authentication is configured. Provide a strong `JWT_SECRET` via environment variable for non-test runs.

## 🧭 Profiles
- `test`: activated automatically in tests; uses H2 with `create-drop`.
- default: uses MySQL (values from `application.properties` or env vars).
- A `CommandLineRunner` that seeds a default admin is disabled during tests via `@Profile("!test")`.

## 🤝 Contributing
1. Create a feature branch: `git checkout -b feature/your-change`
2. Commit: `git commit -m "feat: your change"`
3. Push: `git push origin feature/your-change`
4. Open a Pull Request

## 🐛 Troubleshooting
- DB connection issues: verify `SPRING_DATASOURCE_*`, DB availability, firewall rules.
- Port in use: set `SERVER_PORT` to a free port (e.g., 9090).
- Build problems: ensure JDK 17 and run `./mvnw -v`.

## 📄 Notes
- The artifact ID is `clubs-bakend`. Keep docs consistent, or rename later if you prefer `backend`.
